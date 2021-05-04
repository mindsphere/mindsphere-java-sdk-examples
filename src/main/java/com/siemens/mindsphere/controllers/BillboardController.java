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

@RestController
public class BillboardController {
	/**
	 * This controller class is used to handle token related operations : toggle token, get token.
	 * 
	 */

    @Autowired
    private BillboardService billboardService;

    @RequestMapping(method = RequestMethod.GET, value = "/tokenType/toggle")
    public String toggle() {

        MindsphereService.presentTokenType = MindsphereService.getNextTokenType();
        return "The token type is changed to : " + MindsphereService.presentTokenType;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/token")
    public List<String> token(@RequestHeader(required = false, value = "Authorization") String userToken,
            HttpServletRequest request) throws MindsphereException {
        if(MindsphereService.presentTokenType.equals(MindsphereService.TokenType.USER) && userToken!=null) {
            billboardService.getUserToken(userToken);
        }else if(MindsphereService.presentTokenType.equals(MindsphereService.TokenType.APP)) {
            return billboardService.getTechnicalToken(request.getRequestURL().toString());
        }
        return null;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/jwt")
    public String jwt(@RequestHeader(required = false, value = "Authorization") String userToken,
            HttpServletRequest request) throws MindsphereException {
        if(MindsphereService.presentTokenType.equals(MindsphereService.TokenType.USER) && userToken!=null) {
            return userToken;
        }else if(MindsphereService.presentTokenType.equals(MindsphereService.TokenType.APP)) {
            return billboardService.getEnCodedToken(request.getRequestURL().toString());
        }
        return null;
    }

    /** 
     *
     * @param map
     * @return set of available APIs for demo-app.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/", produces={"text/plain"})
    public ResponseEntity<Object> index(Model map) {
        List<String> apis = billboardService.getAPIs();
        String finalResponse = "";
        for (String string : apis) {
            finalResponse += string + "\n";
        }
       
        
        return new ResponseEntity<Object>(finalResponse, HttpStatus.OK);
    }

}
