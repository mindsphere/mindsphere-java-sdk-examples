package com.siemens.mindsphere.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siemens.mindsphere.sdk.assetmanagement.apiclient.AssetsClient;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetListResource;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetResource;
import com.siemens.mindsphere.sdk.auth.TokenScope;
import com.siemens.mindsphere.sdk.auth.TokenUtility;
import com.siemens.mindsphere.sdk.core.MindsphereCredentials;
import com.siemens.mindsphere.sdk.core.RestClientConfig;
import com.siemens.mindsphere.sdk.core.constants.Constants;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;

@RestController
@RequestMapping(value = "/sub")
public class SubTenantController {

    private RestClientConfig config;
    private MindsphereCredentials cred;

    @RequestMapping(method = RequestMethod.GET, value = "/Tenant")
    public String subTenantToken(@RequestParam("clientId") String clientId,
            @RequestParam("clientSecret") String clientSecret, @RequestParam("tenant") String tenant,
            @RequestParam("subTenant") String subTenant, @RequestParam("tokenType") String tokenType) {
        config = new RestClientConfig.Builder().build();
        if (tokenType.equals("sub")) {
            cred = new MindsphereCredentials.Builder().clientId(clientId).clientSecret(clientSecret).tenant(tenant)
                    .subTenant(subTenant).tokenType(TokenScope.SUB_TENANT).build();
        } else {
            cred = new MindsphereCredentials.Builder().build();
        }
        String token;
        try {
            token = TokenUtility.getAuthorizationToken(config, cred);
        } catch (MindsphereException e) {

            token = e.getErrorMessage();
        }
        return token;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/Tenant/env")
    public String subTenantToken() {
        config = new RestClientConfig.Builder().build();
        cred = new MindsphereCredentials.Builder().tokenType(TokenScope.SUB_TENANT).build();

        String token;
        try {
            token = TokenUtility.getAuthorizationToken(config, cred);
        } catch (MindsphereException e) {

            token = e.getErrorMessage();
        }
        return token;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/asset")
    public List<String> getAssetsList() {
        AssetsClient assetClient = getAssetClient();
        List<String> assetIdList = new ArrayList<String>();

        try {
            AssetListResource assets = assetClient.listAssets(0, 10, null, null, null);
            List<AssetResource> assetList = assets.getEmbedded().getAssets();
            for (AssetResource assetResource : assetList) {
                assetIdList
                        .add("Asset Id: " + assetResource.getAssetId() + "\t Asset Name: " + assetResource.getName());

            }
        } catch (MindsphereException e) {
            System.out.println(e.getErrorMessage());
        }

        return assetIdList;
    }

    private AssetsClient getAssetClient() {
        RestClientConfig config = RestClientConfig.builder().build();
        MindsphereCredentials creds = new MindsphereCredentials.Builder().tokenType(TokenScope.SUB_TENANT).build();
        AssetsClient assetClient = AssetsClient.builder().restClientConfig(config).mindsphereCredentials(creds).build();
        return assetClient;
    }
}
