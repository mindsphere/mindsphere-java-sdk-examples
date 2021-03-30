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
        listOfAPIs.add(" your-domain-url-here/assets/aspects - Creates an aspect (RequestParam : tenantName)");
        listOfAPIs.add(" your-domain-url-here/assets/assettype/{tenantName} - Creates as Asset Type. (RequestParams : aspectname, aspectid - from step above.)");
        listOfAPIs.add(" your-domain-url-here/assets/assets/{assetTypeId} - Creates an asset with provided asset type ID. (Request param : parentid).");
        listOfAPIs.add(" your-domain-url-here/assets/createAsset - creates an Asset (RequestParam : tenantName) ");
        listOfAPIs.add(" your-domain-url-here/assets/assets - list all assets. ");
        listOfAPIs.add(" your-domain-url-here/assets/assetsget/{asset_id} - Get an asset by ID. ");
        listOfAPIs.add(" your-domain-url-here/assets/assetsdelete/{asset_id}/{etag - Delete an asset by ID. ");listOfAPIs.add(" your-domain-url-here/deleteAsset - Delete file assignment, timeseriesdata and asset itself. (Request param - ID, AssetKey, AssetTypeKey, from, to). ");
        listOfAPIs.add(" your-domain-url-here/assets/assetfiles - Create file. ");
        listOfAPIs.add(" your-domain-url-here/assets/assetfiles/{asset_id} - Assign file to an asset. (Request parameters - fileid, key, ifmatch). ");
        listOfAPIs.add("=========================================================");


        listOfAPIs.add(" Event Analytics APIs ");
        listOfAPIs.add(" your-domain-url-here/eventAnalytics/topevents ");
        listOfAPIs.add(" your-domain-url-here/eventAnalytics/countEvents ");
        listOfAPIs.add(" your-domain-url-here/eventAnalytics/removeDuplicateEvents ");
        listOfAPIs.add(" your-domain-url-here/eventAnalytics/matchEventPatterns ");


        listOfAPIs.add("=========================================================");
        listOfAPIs.add(" File Services APIs ");
        listOfAPIs.add(" your-domain-url-here/files/fileservicecreate/{entityId} ");
        listOfAPIs.add(" your-domain-url-here/files/deleteFile/{entityId}/{filepath} ");
        listOfAPIs.add(" your-domain-url-here/files/fileservicegetfile/{entityId}/{file_path} ");
        listOfAPIs.add(" your-domain-url-here/files/fileservicesearch/{entityId} ");
        listOfAPIs.add(" your-domain-url-here/files/fileservicecreatemultipartfile/{entityId}/{file_path}");
        listOfAPIs.add("=========================================================");
        listOfAPIs.add(" Time Series APIs ");
        listOfAPIs.add(" your-domain-url-here/iottimeseries/puttimeseriesdata/{entityId}/{propertySetName}");
        listOfAPIs.add(" your-domain-url-here/iottimeseries/gettimeseries/{entityId}/{propertySetName} ");
        listOfAPIs.add(" your-domain-url-here/iottimeseries/gettimeserieswithfromto/{entityId}/{propertySetName} ");
        listOfAPIs.add(" your-domain-url-here/iottimeseries/deletetimeserieswithfromto/{entityId}/{propertySetName} ");
        listOfAPIs.add("=========================================================");
        listOfAPIs.add(" TS Aggregates APIs ");
        listOfAPIs.add(" your-domain-url-here/iottsaggregates/gettimeseries/{entityId}/{propertySetName} ");
        listOfAPIs.add(" your-domain-url-here/iottsaggregates/gettimeserieswithfromandto/{entityId}/{propertySetName} (Request Parameters - from, to)");
        listOfAPIs.add("=========================================================");
        listOfAPIs.add(" Event Management APIs ");
        listOfAPIs.add(" your-domain-url-here/eventMgmt/createtEventType. - Creates an event. ");
        listOfAPIs.add(" your-domain-url-here/eventMgmt/createCustomEvent/{typeId}/{entityId} - Creates custom event with provided typeId and AssetId. ");

        listOfAPIs.add("=========================================================");
        listOfAPIs.add(" IoT Bulk APIs ");
        listOfAPIs.add(" your-domain-url-here/iotbulkdata/importjobs ");
        listOfAPIs.add(" your-domain-url-here/iotbulkdata/importjobs/{id}  ");
        listOfAPIs.add(" your-domain-url-here/iotbulkdata/gettimeseries/{entityId}/{propertySetName}  ");


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
