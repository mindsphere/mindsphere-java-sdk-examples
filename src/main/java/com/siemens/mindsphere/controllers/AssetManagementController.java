package com.siemens.mindsphere.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siemens.mindsphere.helpers.AssetManagementHelper;
import com.siemens.mindsphere.sdk.assetmanagement.model.AspectTypeResource;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetListResource;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetResourceWithHierarchyPath;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetTypeResource;
import com.siemens.mindsphere.sdk.assetmanagement.model.FileMetadataResource;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.services.AssetManagementService;

@RestController
@RequestMapping(value = "/assets")
public class AssetManagementController {

	@Autowired
	private AssetManagementService assetManagaementService;

	@RequestMapping(method = RequestMethod.GET, value = "/createAsset")
	public String createAsset(@RequestParam("tenantName") String tenantName,
			@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException, IOException {

		AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
		return assetManagaementService.createAssetPackage(tenantName);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/getAssets")
	public String getAssets(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
		return assetManagaementService.getAssetPackage();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/deleteAsset")
	public String deleteAsset(@RequestParam("ID") String id, @RequestParam("AssetKey") String key1,
			@RequestParam("AssetTypeKey") String key2, @RequestParam("from") String from, @RequestParam("to") String to,
			@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {

		AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
		assetManagaementService.deleteAssetPackage(id, key1, key2, from, to);
		return "Successfully deleted";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/aspects")
    public AspectTypeResource createAspect(@RequestParam("tenantName") String tenantName,
            @RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
            throws MindsphereException, IOException {

        AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
        return assetManagaementService.createAspect(tenantName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/assettype/{tenantName}")
    public AssetTypeResource createAssetTypes(@PathVariable("tenantName") String tenantName,
            @RequestParam("aspectname") String aspectname, @RequestParam("aspectid") String aspectid,
            @RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
            throws MindsphereException, IOException {

        AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
        return assetManagaementService.createAssetType(tenantName, aspectname, aspectid);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/assets/{assetTypeId}")
    public AssetResourceWithHierarchyPath createAsset(@PathVariable("assetTypeId") String assetTypeId,@RequestParam("parentid") String parentId,
            @RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
            throws MindsphereException, IOException {

        AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
        return assetManagaementService.createAssets(assetTypeId, parentId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/assetfiles")
    public FileMetadataResource createAssets(
            @RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
            throws MindsphereException, IOException {

        AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
        return assetManagaementService.createFile();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/assetlocation/{asset_id}/{asset_etag}")
    public String createAssets(@PathVariable("asset_id") String assetid,
            @PathVariable("asset_etag") String asset_etag,
            @RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
            throws MindsphereException, IOException {

        AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
        return assetManagaementService.saveAssetLocation(assetid, asset_etag);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/assetfiles/{asset_id}")
    public AssetResourceWithHierarchyPath fileAssignmentToAsset(@PathVariable("asset_id") String assetid,
            @RequestParam("ifmatch") String ifmatch, @RequestParam("key") String key,
            @RequestParam("fileid") String fileid,
            @RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
            throws MindsphereException, IOException {

        AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
        return assetManagaementService.fileAssignmentToAsset(assetid, ifmatch, key, fileid);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/assetsget/{asset_id}")
    public AssetResourceWithHierarchyPath assetGet(@PathVariable("asset_id") String assetid,
            @RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
            throws MindsphereException, IOException {

        AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
        return assetManagaementService.getAssetById(assetid);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/assetsdelete/{asset_id}/{etag}")
    public String deleteGet(@PathVariable("asset_id") String assetid,@PathVariable("etag") String etag,
            @RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
            throws MindsphereException, IOException {

        AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
        return assetManagaementService.deleteAsset(assetid, etag);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/assets")
    public AssetListResource getListAsset(
            @RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
            throws MindsphereException, IOException {

        AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
        return assetManagaementService.listAssets();
    }
}