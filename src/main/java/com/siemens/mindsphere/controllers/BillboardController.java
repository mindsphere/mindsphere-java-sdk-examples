package com.siemens.mindsphere.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.services.BillboardService;
import com.siemens.mindsphere.services.MindsphereService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BillboardController {
	/**
	 * This controller class is used to handle token related operations : toggle token, get token.
	 * 
	 */

    @Autowired
    private BillboardService billboardService;

    @RequestMapping(method = RequestMethod.GET, value = "/tokenType/toggle")
    public String toggle() {
    	log.info("/tokenType/toggle endpoint invoked");
        MindsphereService.presentTokenType = MindsphereService.getNextTokenType();
        log.info("The token type is changed to : " + MindsphereService.presentTokenType);
        return "The token type is changed to : " + MindsphereService.presentTokenType;
    }
    
    
    
    @RequestMapping(method = RequestMethod.GET, value = "tenanttype/gettenant")
    public String gettenant() {
    	
        return System.getenv("MDSP_USER_TENANT");
    }
    
    
    

    @RequestMapping(method = RequestMethod.GET, value = "/token")
    public List<String> token(@RequestHeader(required = false, value = "Authorization") String userToken,
            HttpServletRequest request) throws MindsphereException {
    	 log.info("/token endpoint invoked");
        if(MindsphereService.presentTokenType.equals(MindsphereService.TokenType.USER) && userToken!=null) {
            billboardService.getUserToken(userToken);
            log.info("User Token returned");
        }else if(MindsphereService.presentTokenType.equals(MindsphereService.TokenType.APP)) {
        	log.info("App Token returned");
            return billboardService.getTechnicalToken(request.getRequestURL().toString());
        }
        return null;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/jwt")
    public String jwt(@RequestHeader(required = false, value = "Authorization") String userToken,
            HttpServletRequest request) throws MindsphereException {
    	log.info("/jwt endpoint invoked");
        if(MindsphereService.presentTokenType.equals(MindsphereService.TokenType.USER) && userToken!=null) {
        	 log.info("User Token returned");
            return userToken;
        }else if(MindsphereService.presentTokenType.equals(MindsphereService.TokenType.APP)) {
        	log.info("App Token returned");
            return billboardService.getEnCodedToken(request.getRequestURL().toString());
        }
        return null;
    }

    /** 
     *
     * @param map
     * @return set of available APIs for demo-app.
     */
	/*
	 * @RequestMapping(method = RequestMethod.GET, value = "/",
	 * produces={"text/plain"}) public ResponseEntity<Object> index(Model map) {
	 * List<String> apis = billboardService.getAPIs(); String finalResponse = "";
	 * for (String string : apis) { finalResponse += string + "\n"; }
	 * 
	 * 
	 * return new ResponseEntity<Object>(finalResponse, HttpStatus.OK); }
	 */
}
