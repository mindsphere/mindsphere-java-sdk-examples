package com.siemens.mindsphere.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.siemens.mindsphere.helpers.IDLHelper;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.services.IDLService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/datalake")
@Slf4j
public class IDLController {
	
	/**
	 * IDL : Service for storing Objects, download objects, add extended metadata tags,
			subscribe for notifications, import tenant specific MindSphere Time
			Series data, and enable data access using cross account access and STS in Integrated MindSphere Data Lake.
	 */

	/**
	 * For complete API specification of IDL service refer :
	 * https://developer.mindsphere.io/apis/iot-integrated-data-lake/api-integrated-data-lake-api.html
	 */
	
	@Autowired
	IDLService idlService;
	
	
	/**
	 * @route /generateUploadObjectUrls
	 
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Signed url for uploaded object()SignedUrlResponse in String format
	 * @returnType String
	 * @description This method - generateUploadObjectUrlsTest internally calls method generateUploadObjectUrls of ObjectAccessOperationsClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method generateUploadObjectUrls of ObjectAccessOperationsClient class takes GenerateUploadObjectUrlsRequest as a parameter.
	
	 
	 * @apiEndpoint : POST /api/datalake/v3/generateUploadObjectUrls of integrated-datalake service.
	 *              service.
	 * @apiNote Generate signed URL to upload an object.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/generateUploadObjectUrls")
	public String generateUploadObjectUrlsTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		log.info("/datalake/generateUploadObjectUrls invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.generateUploadObjectUrlsTest();
	}

	/**
	 * @route /generateDownloadObjectUrls
	 
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Download object URL in String format
	 * @returnType String
	 * @description This method - generateUploadObjectUrlsTest internally calls method generateDownloadObjectUrls of ObjectAccessOperationsClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method generateDownloadObjectUrls of ObjectAccessOperationsClient class takes GenerateDownloadObjectUrlsRequest as a parameter.
	
	 
	 * @apiEndpoint : POST /api/datalake/v3/generateDownloadObjectUrls of integrated-datalake service.
	 *              service.
	 * @apiNote Generate signed URL to download for single object.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/generateDownloadObjectUrls")
	public String generateDownloadObjectUrlsTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		log.info("/datalake/generateDownloadObjectUrls invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.generateDownloadObjectUrlsTest();
	}
	
	
	/**
	 * @route /createcrossAccounts
	 
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Cross account creation response in String format.
	 * @returnType String
	 * @description This method - createCrossAccount internally calls method createCrossAccount of ObjectAccessOperationsClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method createCrossAccount of ObjectAccessOperationsClient class takes CreateCrossAccountRequest as a parameter.
	
	 
	 * @apiEndpoint : POST /api/datalake/v3/crossAccounts of integrated-datalake service.
	 *              service.
	 * @apiNote Create a cross account.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/createcrossAccounts")
	public String createCrossAccount(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		log.info("/datalake/createcrossAccounts invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.createCrossAccount();
	}
	
	
	/**
	 * @route /updatecrossAccounts
	 
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Updated cross account information in String format.
	 * @returnType String
	 * @description This method - updateCrossAccount internally calls method updateCrossAccount of ObjectAccessOperationsClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method updateCrossAccount of ObjectAccessOperationsClient class takes UpdateCrossAccountRequest as a parameter.
	
	 
	 * @apiEndpoint : PATCH /api/datalake/v3/crossAccounts/{id} of integrated-datalake service.
	 *              service.
	 * @apiNote Update a cross account.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/updatecrossAccounts")
	public String updateCrossAccount(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {
		log.info("/datalake/updatecrossAccounts invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.updateCrossAccount();
	}
	
	/**
	 * @route /createCrossAccountAccess
	 
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Cross account access information in String format upon successful execution.
	 * @returnType String
	 * @description This method - createCrossAccountAccess internally calls method createCrossAccountAccess of ObjectAccessOperationsClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method createCrossAccountAccess of ObjectAccessOperationsClient class takes CreateCrossAccountAccessRequest as a parameter.
	
	 
	 * @apiEndpoint : POST /api/datalake/v3/crossAccounts/{id}/accesses of integrated-datalake service.
	 *              service.
	 * @apiNote Create a cross account access.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/createCrossAccountAccess")
	public String createCrossAccountAccess(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {
		
		log.info("/datalake/createCrossAccountAccess invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.createCrossAccountAccess();
	}
	
	
	/**
	 * @route /updateCrossAccountAccess
	 
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Updated cross account access information in String format upon successful execution.
	 * @returnType String
	 * @description This method - updateCrossAccountAccess internally calls method updateCrossAccountAccess of ObjectAccessOperationsClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method updateCrossAccountAccess of ObjectAccessOperationsClient class takes UpdateCrossAccountAccessRequest as a parameter.
	
	 
	 * @apiEndpoint : PATCH /api/datalake/v3/crossAccounts/{id}/accesses/{accessId} of integrated-datalake service.
	 *              service.
	 * @apiNote Update cross account access.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/updateCrossAccountAccess")
	public String updateCrossAccountAccess(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {
		log.info("/datalake/updateCrossAccountAccess invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.updateCrossAccountAccess();
	
	}
	
	/**
	 * @route /objectEventSubscriptions
	 
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Created object event subscription in String format upon successful execution.
	 * @returnType String
	 * @description This method - createObjectEventSubscriptionTest internally calls method createObjectEventSubscription of ObjectEventSubscriptionOperationsClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method createObjectEventSubscription of ObjectEventSubscriptionOperationsClient class takes CreateObjectEventSubscriptionRequest as a parameter.
	
	 
	 * @apiEndpoint : POST /api/datalake/v3/objectEventSubscriptions of integrated-datalake service.
	 *              service.
	 * @apiNote Create object event subscription.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/objectEventSubscriptions")
	public String createObjectEventSubscriptionTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {
		log.info("/datalake/objectEventSubscriptions invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.createObjectEventSubscriptionTest();
	}
	
	
	/**
	 * @route /patchobjectEventSubscriptions
	 
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Updated object event subscription in String format upon successful execution.
	 * @returnType String
	 * @description This method - patchObjectEventSubscriptionTest internally calls method patchObjectEventSubscription of ObjectEventSubscriptionOperationsClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method patchObjectEventSubscription of ObjectEventSubscriptionOperationsClient class takes PatchObjectEventSubscriptionRequest as a parameter.
	
	 
	 * @apiEndpoint : PATCH /api/datalake/v3/objectEventSubscriptions/{id} of integrated-datalake service.
	 *              service.
	 * @apiNote Update object event subscription by id.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/patchobjectEventSubscriptions")
	public String patchObjectEventSubscriptionTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {
		log.info("/datalake/patchobjectEventSubscriptions invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.patchObjectEventSubscriptionTest();
	}
	
	
	/**
	 * @route /objects
	 
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return - Qbjects information in String format upon successful execution.
	 * @returnType String
	 * @description This method - queryObjectsOperation internally calls method queryObjects of ObjectOperationsClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method queryObjects of ObjectOperationsClient class takes QueryObjectsRequest as a parameter.
	
	 
	 * @apiEndpoint : GET /api/datalake/v3/objects of integrated-datalake service.
	 *              service.
	 * @apiNote Query objects.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/objects")
	public String queryObjectsOperation(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {
		log.info("/datalake/objects invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.queryObjectsOperation();
	}
	
	
	/**
	 * @route /deleteObjectsJobs
	 
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return - Return delete object job response in String format upon successful execution.
	 * @returnType String
	 * @description This method - createDeleteObjectsJob internally calls method createDeleteObjectsJob of ObjectOperationsClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method createDeleteObjectsJob of ObjectOperationsClient class takes CreateDeleteObjectsJobRequest as a parameter.
	
	 
	 * @apiEndpoint : POST /api/datalake/v3/deleteObjectsJobs of integrated-datalake service.
	 *              service.
	 * @apiNote Delete multiple objects in bulk.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/deleteObjectsJobs")
	public String createDeleteObjectsJob(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {
		log.info("/datalake/deleteObjectsJobs invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.createDeleteObjectsJob();
	}
	
	
	/**
	 * @route /getalldeleteObjectsJobs
	 
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return - Return information of deleted objects in String format upon successful execution.
	 * @returnType String
	 * @description This method - getallDeleteObjectsJob internally calls method getAllDeleteObjectsJob of ObjectOperationsClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method getAllDeleteObjectsJob of ObjectOperationsClient class takes GetAllDeleteObjectsJobRequest as a parameter.
	
	 
	 * @apiEndpoint : GET /api/datalake/v3/deleteObjectsJobs of integrated-datalake service.
	 *              service.
	 * @apiNote Fetch details of all the Delete Objects Jobs.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getalldeleteObjectsJobs")
	public String getallDeleteObjectsJob(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {
		log.info("/datalake/getalldeleteObjectsJobs invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.getallDeleteObjectsJob();
	}
	
	
	/**
	 * @route /getdeleteObjectsJobs/{id}
	 * @param id - Unique identifier of the Delete Objects Job
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return - Return information of delete object job for provided id in String format upon successful execution.
	 * @returnType String
	 * @description This method - getDeleteObjectsJob internally calls method getDeleteObjectsJob of ObjectOperationsClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method getDeleteObjectsJob of ObjectOperationsClient class takes GetDeleteObjectsJobRequest as a parameter.
	
	 
	 * @apiEndpoint : GET /api/datalake/v3/deleteObjectsJobs/{id} of integrated-datalake service.
	 *              service.
	 * @apiNote Fetch status and details of Delete Objects Job.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getdeleteObjectsJobs/{id}")
	public String getDeleteObjectsJob(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,
			HttpServletRequest request) throws MindsphereException {
		log.info("/datalake/getdeleteObjectsJobs/"+id+" invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.getDeleteObjectsJob(id);
	}
	
	/**
	 * @route /getdeleteObjectsJoberrors/{id}
	 * @param id - Unique identifier of the Delete Objects Job
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return - Return information of errors for delete object job for provided id in String format upon successful execution.
	 * @returnType String
	 * @description This method - getdeleteObjectsJoberrors internally calls method getDeleteObjectsJobErrors of ObjectOperationsClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method getDeleteObjectsJobErrors of ObjectOperationsClient class takes GetDeleteObjectsJobErrorsRequest as a parameter.
	
	 
	 * @apiEndpoint : GET /api/datalake/v3/deleteObjectsJobs/{id}/errors of integrated-datalake service.
	 *              service.
	 * @apiNote Fetch errors of Delete Objects Job.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getdeleteObjectsJoberrors/{id}")
	public String getdeleteObjectsJoberrors(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,
			HttpServletRequest request) throws MindsphereException {
		log.info("/datalake/getdeleteObjectsJoberrors/"+id+" invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.getdeleteObjectsJoberrors(id);
	}
	
	
	/**
	 * @route /accessTokenPermissions
	 
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return - List all folders having write premission in String format upon successful execution.
	 * @returnType String
	 * @description This method - listAccessTokenPermissionsTest internally calls method listAccessTokenPermissions of ObjectOperationsWithAccessTokenClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method listAccessTokenPermissions of ObjectOperationsWithAccessTokenClient class takes ListAccessTokenPermissionsRequest as a parameter.
	
	 
	 * @apiEndpoint : GET /api/datalake/v3/accessTokenPermissions of integrated-datalake service.
	 *              service.
	 * @apiNote List all folders having write premission
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/accessTokenPermissions")
	public String listAccessTokenPermissionsTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {
		log.info("/datalake/accessTokenPermissions invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.listAccessTokenPermissionsTest();
	}
	
	/**
	 * @route /accessTokenPermissions/{id}
	 * @param id - Unique identifier of the write enabled folders
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return - Details of the write folder request for the given id in String format upon successful execution.
	 * @returnType String
	 * @description This method - getAccessTokenPermissionsTest internally calls method getAccessTokenPermissions of ObjectOperationsWithAccessTokenClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method getAccessTokenPermissions of ObjectOperationsWithAccessTokenClient class takes GetAccessTokenPermissionsRequest as a parameter.
	
	 
	 * @apiEndpoint : GET /api/datalake/v3/accessTokenPermissions/{id} of integrated-datalake service.
	 *              service.
	 * @apiNote Details of the write folder request for the given id
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/accessTokenPermissions/{id}")
	public String getAccessTokenPermissionsTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,
			HttpServletRequest request) throws MindsphereException {
		log.info("/datalake/accessTokenPermissions/"+id+" invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.getAccessTokenPermissionsTest(id);
	}
	
	
	/**
	 * @route /deleteaccessTokenPermissions/{id}
	 * @param id - Unique identifier of the write enabled folders
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return - "deleted" upon successful execution.
	 * @returnType String
	 * @description This method - deleteAccessTokenPermissionsTest internally calls method deleteAccessTokenPermissions of ObjectOperationsWithAccessTokenClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method deleteAccessTokenPermissions of ObjectOperationsWithAccessTokenClient class takes DeleteAccessTokenPermissionsRequest as a parameter.
	
	 
	 * @apiEndpoint : DELETE /api/datalake/v3/accessTokenPermissions/{id} of integrated-datalake service.
	 *              service.
	 * @apiNote Delete write permission on folder for the given id
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/deleteaccessTokenPermissions/{id}")
	public String deleteAccessTokenPermissionsTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,
			HttpServletRequest request) throws MindsphereException {
		log.info("/datalake/deleteaccessTokenPermissions/"+id+" invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.deleteAccessTokenPermissionsTest(id);
	}
	
	/**
	 * @route /createaccessTokenPermissions
	
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return - AccessTokenPermissionResource in string format upon successful execution.
	 * @returnType String
	 * @description This method - createAccessTokenPermissionsTest internally calls method accessTokenPermissions of ObjectOperationsWithAccessTokenClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method accessTokenPermissions of ObjectOperationsWithAccessTokenClient class takes AccessTokenPermissionsRequest as a parameter.
	
	 
	 * @apiEndpoint : POST /api/datalake/v3/accessTokenPermissions of integrated-datalake service.
	 *              service.
	 * @apiNote Allows to give write permission on folder/path
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/createaccessTokenPermissions")
	public String createAccessTokenPermissionsTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		log.info("/datalake/createaccessTokenPermissions invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.createAccessTokenPermissionsTest();
	}
	
	
	/**
	 * @route /generateAccessToken
	
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return - AccessTokens object in string format upon successful execution.
	 * @returnType String
	 * @description This method - generateAccessTokenTest internally calls method generateAccessToken of ObjectOperationsWithAccessTokenClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method generateAccessToken of ObjectOperationsWithAccessTokenClient class takes GenerateAccessTokenRequest as a parameter.
	
	 
	 * @apiEndpoint : POST /api/datalake/v3/generateAccessToken of integrated-datalake service.
	 *              service.
	 * @apiNote Generate AWS STS token
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/generateAccessToken")
	public String generateAccessTokenTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		log.info("/datalake/generateAccessToken invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.generateAccessTokenTest();
	}
	
	
	/**
	 * @route /objectMetadata
	 
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return - "created/updated" upon successful execution.
	 * @returnType String
	 * @description This method - createOrUpdateObjectMetadataTest internally calls method createOrUpdateObjectMetadata of ObjectsMetadataCatalogOperationsClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method createOrUpdateObjectMetadata of ObjectsMetadataCatalogOperationsClient class takes CreateOrUpdateObjectMetadataRequest as a parameter.
	
	 
	 * @apiEndpoint : PUT /api/datalake/v3/objectMetadata/{path} of integrated-datalake service.
	 *              service.
	 * @apiNote Create/Update Metadata for the object
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/objectMetadata")
	public String createOrUpdateObjectMetadataTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {
		log.info("/datalake/objectMetadata invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.createOrUpdateObjectMetadataTest();
	}
	
	
	/**
	 * @route /retrieveobjectMetadata
	 
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return - Object metadata in String format upon successful execution.
	 * @returnType String
	 * @description This method - retrieveObjectMetadataTest internally calls method retrieveObjectMetadata of ObjectsMetadataCatalogOperationsClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method retrieveObjectMetadata of ObjectsMetadataCatalogOperationsClient class takes RetrieveObjectMetadataRequest as a parameter.
	
	 
	 * @apiEndpoint : GET /api/datalake/v3/objectMetadata/{path} of integrated-datalake service.
	 *              service.
	 * @apiNote Get Metadata for the object.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/retrieveobjectMetadata")
	public String retrieveObjectMetadataTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {
		log.info("/datalake/retrieveobjectMetadata invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.retrieveObjectMetadataTest();
	}
	
	
	/**
	 * @route /timeSeriesImportJobs
	 
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return - Created import job in String format upon successful execution.
	 * @returnType String
	 * @description This method - createTimeSeriesImportJobTest internally calls method createTimeSeriesImportJob of TimeSeriesBulkImportClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method createTimeSeriesImportJob of TimeSeriesBulkImportClient class takes CreateTimeSeriesImportJobRequest as a parameter.
	
	 
	 * @apiEndpoint : POST /api/datalake/v3/timeSeriesImportJobs of integrated-datalake service.
	 *              service.
	 * @apiNote Creates a bulk import job of time series data into data lake.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/timeSeriesImportJobs")
	public String createTimeSeriesImportJobTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {
		log.info("/datalake/timeSeriesImportJobs invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.createTimeSeriesImportJobTest();
	}
	
	
	
	/**
	 * @route /listtimeSeriesImportJobs
	 
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return - Details of all import jobs in String format upon successful execution.
	 * @returnType String
	 * @description This method - queryTimeSeriesImportJobsTest internally calls method queryTimeSeriesImportJobs of TimeSeriesBulkImportClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method queryTimeSeriesImportJobs of TimeSeriesBulkImportClient class takes QueryTimeSeriesImportJobsRequest as a parameter.
	
	 
	 * @apiEndpoint : GET /api/datalake/v3/timeSeriesImportJobs of integrated-datalake service.
	 *              service.
	 * @apiNote Query all time series bulk import jobs.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/listtimeSeriesImportJobs")
	public String queryTimeSeriesImportJobsTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {
		log.info("/datalake/listtimeSeriesImportJobs invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.queryTimeSeriesImportJobsTest();
	}
	
	/**
	 * @route /timeSeriesImportJobsdetails/{id}
	 * @param id - Unique id for getting status of the time series bulk import job.
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return - Import job information for provided id in String format upon successful execution.
	 * @returnType String
	 * @description This method - retrieveTimeSeriesImportJobDetailsTest internally calls method retrieveTimeSeriesImportJobDetails of TimeSeriesBulkImportClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method retrieveTimeSeriesImportJobDetails of TimeSeriesBulkImportClient class takes RetrieveTimeSeriesImportJobDetailsRequest as a parameter.
	
	 
	 * @apiEndpoint : GET /api/datalake/v3/timeSeriesImportJobs/{id}/details of integrated-datalake service.
	 *              service.
	 * @apiNote Retrieve details of a time series bulk import job.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	
	@RequestMapping(method = RequestMethod.GET, value = "/timeSeriesImportJobsdetails/{id}")
	public String retrieveTimeSeriesImportJobDetailsTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,
			HttpServletRequest request) throws MindsphereException {
		log.info("/datalake/timeSeriesImportJobsdetails"+id+" invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.retrieveTimeSeriesImportJobDetailsTest(id);
	}
	
	
	/**
	 * @route /deletetimeSeriesImportJobs/{id}
	 * @param id  - Unique id for getting status of the time series bulk import job.
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return - "deleted" upon successful execution.
	 * @returnType String
	 * @description This method - deleteTimeSeriesImportJobDetailsTest internally calls method deleteTimeSeriesImportJob of TimeSeriesBulkImportClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method deleteTimeSeriesImportJob of TimeSeriesBulkImportClient class takes DeleteTimeSeriesImportJobRequest as a parameter.
	
	 
	 * @apiEndpoint : DELETE /api/datalake/v3/timeSeriesImportJobs/{id} of integrated-datalake service.
	 *              service.
	 * @apiNote Delete time series bulk import job by id.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/deletetimeSeriesImportJobs/{id}")
	public String deleteTimeSeriesImportJobDetailsTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,
			HttpServletRequest request) throws MindsphereException {
		log.info("/datalake/deletetimeSeriesImportJobs"+id+" invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.deleteTimeSeriesImportJobDetailsTest(id);
	}
	
	
	/**
	 * @route /timeSeriesImportJobs/{id}
	 * @param id - Unique id for getting status of the time series bulk import job.
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return - Import job information for provided id in String format upon successful execution.
	 * @returnType String
	 * @description This method - retrieveTimeSeriesImportJobTest internally calls method retrieveTimeSeriesImportJob of TimeSeriesBulkImportClient class.
	 * 				This class is available as dependency in integrateddatalake-sdk-<version-here>.jar.
	 * 				The method retrieveTimeSeriesImportJob of TimeSeriesBulkImportClient class takes RetrieveTimeSeriesImportJobRequest as a parameter.
	
	 
	 * @apiEndpoint : GET /api/datalake/v3/timeSeriesImportJobs/{id} of integrated-datalake service.
	 *              service.
	 * @apiNote Retrieve status of time series bulk import job.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/timeSeriesImportJobs/{id}")
	public String retrieveTimeSeriesImportJobTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,
			HttpServletRequest request) throws MindsphereException {
		log.info("/datalake/timeSeriesImportJobs"+id+" invoked.");
		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.retrieveTimeSeriesImportJobTest(id);
	}
	
	
	
	
}
