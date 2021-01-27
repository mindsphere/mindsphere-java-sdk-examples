package com.siemens.mindsphere.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.siemens.mindsphere.helpers.BillboardHelper;
import com.siemens.mindsphere.sdk.auth.TokenUtility;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;

@Service
public class BillboardService extends MindsphereService {
    BillboardHelper billboardHelper = new BillboardHelper();
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public List<String> getAPIs() {
        List<String> listOfAPIs = new ArrayList<String>();
        listOfAPIs.add("Asset Management APIs ");
        listOfAPIs.add(" http://localhost:8080/assets/createAsset ");
        listOfAPIs.add(" http://localhost:8080/assets/getAssets ");
        listOfAPIs.add(" http://localhost:8080/assets/deleteAsset ");
        listOfAPIs.add(" Event Analytics APIs ");
        listOfAPIs.add(" http://localhost:8080/eventAnalytics/selectedEvents ");
        listOfAPIs.add(" http://localhost:8080/eventAnalytics/countEvents ");
        listOfAPIs.add(" http://localhost:8080/eventAnalytics/removeDuplicateEvents ");
        listOfAPIs.add(" http://localhost:8080/eventAnalytics/matchEventPatterns ");
        listOfAPIs.add(" File Services APIs ");
        listOfAPIs.add(" http://localhost:8080/files/createFile/{entityId} ");
        listOfAPIs.add(" http://localhost:8080/files/deleteFile/{entityId}/{filepath} ");
        listOfAPIs.add(" Time Series APIs ");
        listOfAPIs.add(" http://localhost:8080/timeSeries/create/{entityId}/{propertySetName} ");
        listOfAPIs.add(" http://localhost:8080/timeSeries/get/{entityId}/{propertySetName} ");
        return listOfAPIs;
    }

    public List<String> getTechnicalToken(String url) throws MindsphereException {
        String technicalToken = null;
        technicalToken = TokenUtility.getAuthorizationToken(billboardHelper.getConfig(url),
                billboardHelper.getCreds(null));
        byte[] bytesEncoded = Base64.encodeBase64(technicalToken.getBytes());
        String base64Creds = new String(bytesEncoded);

        LOGGER.info(base64Creds);
        return billboardHelper.getRolesFromJwt(technicalToken);

    }

    public String getEnCodedToken(String url) throws MindsphereException {
        String technicalToken = TokenUtility.getAuthorizationToken(billboardHelper.getConfig(url),
                billboardHelper.getCreds(null));

        return technicalToken;

    }

    public List<String> getUserToken(String userToken) {
        byte[] bytesEncoded = Base64.encodeBase64(userToken.getBytes());
        String base64Creds = new String(bytesEncoded);
        LOGGER.info(base64Creds);
        return billboardHelper.getRolesFromJwt(userToken);
    }

}
