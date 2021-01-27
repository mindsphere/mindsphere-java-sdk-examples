package com.siemens.mindsphere.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
        }else if(MindsphereService.presentTokenType.equals(MindsphereService.TokenType.TENANT)) {
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
        }else if(MindsphereService.presentTokenType.equals(MindsphereService.TokenType.TENANT)) {
            return billboardService.getEnCodedToken(request.getRequestURL().toString());
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<String> index(Model map) {
        return billboardService.getAPIs();
    }

}
