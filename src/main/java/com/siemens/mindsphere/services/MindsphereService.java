package com.siemens.mindsphere.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MindsphereService {

    public static enum TokenType {
        TENANT, USER, APP;
    }

    private String token;
    private String hostName;
    public static TokenType presentTokenType = TokenType.USER;
    private static List<TokenType> tokenList = new ArrayList<>(Arrays.asList(TokenType.USER,TokenType.APP,TokenType.TENANT));
    
    public static TokenType getNextTokenType() {
        int currentTokenIndex = tokenList.indexOf(MindsphereService.presentTokenType);
        int index = (currentTokenIndex+1)%tokenList.size();
        return tokenList.get(index);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

}
