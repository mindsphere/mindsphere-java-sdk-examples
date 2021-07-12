package com.siemens.mindsphere.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.google.gson.Gson;
import com.siemens.mindsphere.helpers.AssetManagementHelper;
import com.siemens.mindsphere.sdk.assetmanagement.model.AddAssetRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.AspectType;
import com.siemens.mindsphere.sdk.assetmanagement.model.AspectTypeResource;
import com.siemens.mindsphere.sdk.assetmanagement.model.Asset;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetListResource;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetResourceWithHierarchyPath;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetType;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetTypeResource;
import com.siemens.mindsphere.sdk.assetmanagement.model.FileMetadataResource;
import com.siemens.mindsphere.sdk.assetmanagement.model.RootAssetResource;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.services.AssetManagementService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/assets")
@Slf4j
public class AssetManagementController {

	/**
	 * For complete API specification of asset management service refer :
	 * https://developer.mindsphere.io/apis/advanced-assetmanagement/api-assetmanagement-api.html
	 */
	
	
	

	@Autowired
	private AssetManagementService assetManagaementService;

	
	/**
	 * @route /createAsset
	 * @param token      - User token (optional)
	 * @param request    - HttpServletRequest object
	 * @return Data in the form of String.
	 * @returnType : String
	 * @description This method - createAsset internally creates aspect type first. Further creates assettype based on created aspect type.
	 * 			    It then creates an asset with created assettype. Also generates file resource and assign that file assignment to created asset.
	 * 				It finally returns String which includes IDs of created resources.
	 * @apiEndpoint : POST /api/assetmanagement/v3/assets, PUT /api/assetmanagement/v3/assettypes/{id}, PUT /api/assetmanagement/v3/aspecttypes/{id},
	 * 			      PUT /api/assetmanagement/v3/assets/{id}/fileAssignments/{key}, POST /api/assetmanagement/v3/files of
	 *              assetmanagement service.
	 
	 * @throws MindsphereException if an error occurs while attempting to invoke the
	 *         sdk call.
	 * @throws IOException
	 */
	
	@RequestMapping(method = RequestMethod.PUT, value = "/putaspect/{id}/{ifmatch}",consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
	public AspectTypeResource putaspect(@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request,@PathVariable("id") String id, @PathVariable("ifmatch") String ifmatch
			,@RequestBody AspectType aspecttype)
			throws MindsphereException, IOException {
		log.info("/assets/putaspect invoked.");
		AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
		return assetManagaementService.putAspectType(aspecttype,id,ifmatch);

	}
	
	
	@RequestMapping(method = RequestMethod.PUT, value = "/putassettype/{id}/{ifmatch}",consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
	public AssetTypeResource putassettype(@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request,@PathVariable("id") String id, @PathVariable("ifmatch") String ifmatch
			,@RequestBody AssetType assetType)
			throws MindsphereException, IOException {
		log.info("/assets/putassettype invoked.");
		AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
		return assetManagaementService.putAssetType(assetType,id,ifmatch);

	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/postasset",consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
	public AssetResourceWithHierarchyPath postasset(@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request,@RequestBody Asset asset)
			throws MindsphereException, IOException {
		log.info("/assets/postasset invoked.");
		AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
		AddAssetRequest addAssetRequest = new AddAssetRequest();
		addAssetRequest.setAsset(asset);
		return assetManagaementService.postAsset(addAssetRequest);

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/root")
	public RootAssetResource getrootAsset(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException, IOException {
		log.info("/assets/assets invoked.");
		AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
		return assetManagaementService.rootAssets();
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/createAsset")
	public String createAsset(@RequestParam("tenantName") String tenantName,
			@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException, IOException {
		log.info("/assets/createAsset invoked.");
		AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
		return assetManagaementService.createAssetPackage(tenantName);

	}

	
	/**
	 * @route /getAssets
	 * @param token      - User token (optional)
	 * @param request    - HttpServletRequest object
	 * @return Data in the form of String.
	 * @returnType : String
	 * @description This method - getAssets internally fetches available aspects, assettypes and assets for tenant. 
	 * 				It returns combined data in the form of String.
	 * @apiEndpoint : GET /api/assetmanagement/v3/assets, GET /api/assetmanagement/v3/assettypes, GET /api/assetmanagement/v3/aspecttypes of
	 *              assetmanagement service.
	 
	 * @throws MindsphereException if an error occurs while attempting to invoke the
	 *                             sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getAssets")
	public String getAssets(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {
		log.info("/assets/getAssets invoked.");
		AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
		return assetManagaementService.getAssetPackage();
	}

	/**
	 * @route /deleteAsset
	 * @param ID - Id of an asset to be deleted.
	 * @param key1 - Key of file assignment
	 * @param key2 - Key of file assignment
	 * @param from - Start time of timeseries to be deleted
	 * @param to - End time of timeseries to be deleted
	 * @param token      - User token (optional)
	 * @param request    - HttpServletRequest object
	 * @return "Successfully deleted" on successful execution.
	 * @returnType : String
	 * @description This method - deleteAsset internally gets an asset for given ID then deletes a file assignment with key1.
	 * 				Afterwards delete timeseries data for provided from and to window. Further deletes file assignment with key2.
	 * 				Delete the asset itself. Next it deletes the asset type from which asset was created. At the end also deletes aspect
	 * 				associated with asset type.
	 * @apiEndpoint : Multiple endpoints
	 * @note Predefined asset types cannot be deleted. Also if an asset does not have 2 file assignments then error might occur for deleting
	 * 		 file assignment with key2. Make sure all the preconditions are met for calling this endpoint.
	 * @throws MindsphereException if an error occurs while attempting to invoke the
	 *         sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/deleteAsset")
	public String deleteAsset(@RequestParam("ID") String id, @RequestParam("AssetKey") String key1,
			@RequestParam("AssetTypeKey") String key2, @RequestParam("from") String from, @RequestParam("to") String to,
			@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {
		log.info("/assets/deleteAsset invoked.");
		log.info("Request params are : ID="+id+" AssetKey="+key1+" AssetTypeKey="+key2 +" from"+from+" to"+to);
		AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
		assetManagaementService.deleteAssetPackage(id, key1, key2, from, to);
		return "Successfully deleted";
	}

	/**
	 * @route /aspects
	 * @param tenantName - tenant name on which aspect is to be created. (Tenant
	 *                   Name can be different from tenant on which application is
	 *                   hosted)
	 * @param token      - User token (optional)
	 * @param request    - HttpServletRequest object
	 * @return Created aspect on successful execution.
	 * @returnType : Object of AspectTypeResource class.
	 * @description This method - createAspect internally generates request body for
	 *              aspect and calls method saveAspectType(SaveAspectTypeRequest
	 *              object) on object of AspecttypeClient .This class is available
	 *              as part of dependency : assetmanagement-sdk-<version-here>.jar.
	 *              Dynamic request body generation is in helper class
	 *              AssetManagementHelper - createAspectType method.
	 * @apiEndpoint : PUT /api/assetmanagement/v3/aspecttypes/{id} of
	 *              assetmanagement service.
	 * @apiNote Create or Update an aspect type.
	 * @throws MindsphereException if an error occurs while attempting to invoke the
	 *                             sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/aspects")
	public AspectTypeResource createAspect(@RequestParam("tenantName") String tenantName,
			@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException, IOException {
		log.info("/assets/aspects invoked with tenantName :"+ tenantName);
		AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
		return assetManagaementService.createAspect(tenantName);
	}

	/**
	 * @route /assettype/{tenantName}
	 * @param tenantName - tenant name on which asset type is to be created. (Tenant
	 *                   Name can be different from tenant on which application is
	 *                   hosted)
	 * @param aspectname - aspectname provided by user (user's choice)
	 * @param aspectid   - aspectid provided by user (user's choice)
	 * @note Asset type requires aspectid and aspectname to be provided in request
	 *       body hence this two values cannot be empty. Non existent/Incorrect
	 *       aspectname and aspectid will result in MindsphereException.
	 * @param token   - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Created asset type on successful execution.
	 * @returnType : Object of AssetTypeResource class.
	 * @description This method - createAssetTypes internally generates request body
	 *              for asset type and calls method saveAssetType on object of
	 *              AssettypeClient.This class is available as part of dependency :
	 *              assetmanagement-sdk-<version-here>.jar. Request body generation
	 *              is in helper class AssetManagementHelper - createAssetType
	 *              method.
	 * @apiEndpoint : PUT /api/assetmanagement/v3/assettypes/{id} of assetmanagement
	 *              service
	 * @throws MindsphereException if an error occurs while attempting to invoke the
	 *                             sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/assettype/{tenantName}")
	public AssetTypeResource createAssetTypes(@PathVariable("tenantName") String tenantName,
			@RequestParam("aspectname") String aspectname, @RequestParam("aspectid") String aspectid,
			@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException, IOException {
		log.info("/assets/assettype/"+tenantName+" invoked with aspectname :"+aspectname+ " and aspectId :"+aspectid);
		AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
		return assetManagaementService.createAssetType(tenantName, aspectname, aspectid);
	}

	/**
	 * @route /assets/{assetTypeId}
	 * @param assetTypeId - Id of the assettype from which you want to create an
	 *                    Asset.
	 * @param parentId    - Desired parentId of the asset.
	 * @note Asset creation requires assetTypeId and parentId to be provided in
	 *       request body hence this two values cannot be empty. Values of this
	 *       variables are passed as provided by user hence non existent/Incorrect
	 *       values will result in MindsphereException.
	 * @param token   - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Created asset on successful execution.
	 * @returnType : Object of AssetResourceWithHierarchyPath class.
	 * @description This method - createAsset internally generates dynamic request
	 *              body for asset and calls method addAsset on object of
	 *              AssetsClient.This class is available as part of dependency :
	 *              assetmanagement-sdk-<version-here>.jar. Request body for asset
	 *              is created dynamically in AssetManagementHelper class -
	 *              createAsset method.
	 * @apiEndpoint : POST /api/assetmanagement/v3/assets of assetmanagement service
	 * @throws MindsphereException if an error occurs while attempting to invoke the
	 *                             sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/assets/{assetTypeId}")
	public AssetResourceWithHierarchyPath createAsset(@PathVariable("assetTypeId") String assetTypeId,
			@RequestParam("parentid") String parentId,
			@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException, IOException {
		log.info("/assets/assets/"+assetTypeId+" invoked with parentid :"+parentId);
		AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
		return assetManagaementService.createAssets(assetTypeId, parentId);
	}

	/**
	 * @route /assetfiles
	 * @param token   - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Metadata of created file for asset on successful execution.
	 * @returnType : Object of FileMetadataResource class.
	 * @description This method - createAssets internally generates dynamic request
	 *              body for file resource and calls method
	 *              uploadFile(UploadFileRequest) on object of FilesClient.This
	 *              class is available as part of dependency :
	 *              assetmanagement-sdk-<version-here>.jar. Request body for file
	 *              resource is created dynamically in AssetManagementHelper class -
	 *              uploadFileRequestModel method.
	 * @apiEndpoint : POST /api/assetmanagement/v3/files of assetmanagement service
	 * @apiNote : Upload files to be used in Asset Management.
	 * @throws MindsphereException if an error occurs while attempting to invoke the
	 *                             sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/assetfiles")
	public FileMetadataResource createAssets(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException, IOException {
		log.info("/assets/assetfiles invoked.");
		AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
		return assetManagaementService.createFile();
	}

	/**
	 * @route /assetlocation/{asset_id}/{asset_etag}
	 * @param assetid    - Id of an asset for which location is to be saved.
	 * @param asset_etag - etag of as asset. This can be obtained via
	 *                   /assetsget/{asset_id} endpoint.
	 * @note - Incorrect/Non-existent values of assetid and asset_etag can lead to
	 *       MindsphereException.
	 * @param token   - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return "Succesfully updated with location to assetid : " + <provided
	 *         assetid> on successful execution.
	 * @returnType : String
	 * @description This method - createAssets internally generates request body for
	 *              saving location and calls method
	 *              saveAssetLocation(SaveAssetLocationRequest object) on object of
	 *              LocationsClient .This class is available as part of dependency :
	 *              assetmanagement-sdk-<version-here>.jar. Dynamic request body
	 *              generation is in helper class AssetManagementHelper -
	 *              saveLocationRequestModel method.
	 * @apiEndpoint : PUT /api/assetmanagement/v3/assets/{id}/location  of
	 *              assetmanagement service.
	 * @apiNote Create or Update location assigned to given asset
	 * @throws MindsphereException if an error occurs while attempting to invoke the
	 *                             sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/assetlocation/{asset_id}/{asset_etag}")
	public String createAssets(@PathVariable("asset_id") String assetid, @PathVariable("asset_etag") String asset_etag,
			@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException, IOException {
		log.info("/assets/assetlocation/"+assetid+"/"+asset_etag+" invoked");
		AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
		return assetManagaementService.saveAssetLocation(assetid, asset_etag);
	}

	/**
	 * @route /assetfiles/{asset_id}
	 * @param assetid - Id of an asset to which file assignment is to be done
	 * @param ifmatch - ifmatch refers to etag of an asset and can be obtained via /assetsget/{asset_id} endpoint.
	 * @param key - key of the file. Provided at the time of creating file resource.
	 * @param fileid - fileid of the assignment.
	 * @note - Incorrect/Non-existent values of assetid,ifmatch,key,fileid can lead to
	 *       MindsphereException.
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Asset upon successful file assignment.
	 * @returnType Object of AssetResourceWithHierarchyPath class.
	 * @description This method - fileAssignmentToAsset internally calls method
	 *              saveAssetFileAssignment(SaveAssetFileAssignmentRequest object) on object of AssetsClient .This
	 *              class is available as part of dependency :
	 *              assetmanagement-sdk-<version-here>.jar. The method getAsset
	 *              takes an argument of type SaveAssetFileAssignmentRequest. Request object
	 *              generation is in helper class AssetManagementHelper -
	 *              fileAssignmentToAssetRequestModel method. Request object is created based upon values passed by user.
	* @apiEndpoint : PUT /api/assetmanagement/v3/assets/{id}/fileAssignments/{key} of assetmanagement
	 *              service.
	 * @apiNote Save an file assignment to an asset
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/assetfiles/{asset_id}")
	public AssetResourceWithHierarchyPath fileAssignmentToAsset(@PathVariable("asset_id") String assetid,
			@RequestParam("ifmatch") String ifmatch, @RequestParam("key") String key,
			@RequestParam("fileid") String fileid,
			@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException, IOException {
		log.info("/assets/assetfiles/"+assetid+" invoked with ifmatch :"+ifmatch+ " and key :"+key+" fileid"+fileid);
		AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
		return assetManagaementService.fileAssignmentToAsset(assetid, ifmatch, key, fileid);
	}

	/**
	 * @route /assetsget/{asset_id}
	 * @param assetid - Id of an asset for which details are to be retrieved.
	 * @note - Incorrect/Non-existent values of assetid can lead to
	 *       MindsphereException.
	 * @param token   - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Asset details on successful execution.
	 * @returnType : Object of AssetResourceWithHierarchyPath class.
	 * @description This method - assetGet internally calls method
	 *              getAsset(GetAssetRequest object) on object of AssetsClient .This
	 *              class is available as part of dependency :
	 *              assetmanagement-sdk-<version-here>.jar. The method getAsset
	 *              takes an argument of type GetAssetRequest. Request object
	 *              generation is in helper class AssetManagementHelper -
	 *              getAssetRequestModel method.
	 * @apiEndpoint : GET /api/assetmanagement/v3/assets/{id} of assetmanagement
	 *              service.
	 * @apiNote Returns an asset.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/assetsget/{asset_id}")
	public AssetResourceWithHierarchyPath assetGet(@PathVariable("asset_id") String assetid,
			@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException, IOException {
		log.info("/assets/assetsget/"+assetid+" invoked.");
		AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
		return assetManagaementService.getAssetById(assetid);
	}

	/**
	 * @route /assetsdelete/{asset_id}/{etag}
	 * @param assetid - id of asset to be deleted
	 * @param etag    - etag on an asset to be deleted. etag can be obtained by
	 *                /assetsget/{asset_id} call. Incorrect etag leads to exception.
	 * @note - Incorrect/Non-existent values of assetid and asset_etag can lead to
	 *       MindsphereException.
	 * @param token   - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return String - "Successfully deleted the Asset : " + <assetid> on
	 *         successful completion.
	 * @returnType - String
	 * @description This method - deleteGet internally calls method
	 *              deleteAsset(DeleteAssetRequest object) on object of AssetsClient .This
	 *              class is available as part of dependency :
	 *              assetmanagement-sdk-<version-here>.jar. The method deleteAsset
	 *              takes an argument of type DeleteAssetRequest. Request object
	 *              generation is in helper class AssetManagementHelper -
	 *              deleteAssetRequestModel method.
	 * @apiEndpoint : DELETE /api/assetmanagement/v3/assets/{id} of assetmanagement
	 *              service.
	 * @apiNote Delete an asset
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/assetsdelete/{asset_id}/{etag}")
	public String deleteGet(@PathVariable("asset_id") String assetid, @PathVariable("etag") String etag,
			@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException, IOException {
		log.info("/assets/assetsdelete/"+assetid+"/"+etag+" invoked.");
		AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
		return assetManagaementService.deleteAsset(assetid, etag);
	}

	/**
	 * 
	 * @route /assets
	 * @param token   - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return List all assets available for the authenticated user.
	 * @returnType : Object of AssetListResource class.
	 * @description This method - getListAsset internally calls listAssets() method
	 *              on object of AssetsClient class. This class is available as part
	 *              of dependency : assetmanagement-sdk-<version-here>.jar.
	 *              listAssets() takes an argument of ListAssetsRequest object which
	 *              is created with the help of helper class AssetManagementHelper
	 *              and passed.
	 * @apiEndpoint : GET /api/assetmanagement/v3/assets of assetmanagement service
	 * @apiNote List all available assets.
	 * @throws MindsphereException if an error occurs while attempting to invoke the
	 *                             sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/assets")
	public AssetListResource getListAsset(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException, IOException {
		log.info("/assets/assets invoked.");
		AssetManagementHelper.selectToken(assetManagaementService, token, request.getRequestURL().toString());
		return assetManagaementService.listAssets();
	}
}