package com.siemens.mindsphere.controllers;

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

@RestController
@RequestMapping(value = "/datalake")
public class IDLController {

	
	@Autowired
	IDLService idlService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/generateUploadObjectUrls")
	public String generateUploadObjectUrlsTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.generateUploadObjectUrlsTest();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/generateDownloadObjectUrls")
	public String generateDownloadObjectUrlsTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.generateDownloadObjectUrlsTest();
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/createcrossAccounts")
	public String createCrossAccount(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.createCrossAccount();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/updatecrossAccounts")
	public String updateCrossAccount(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.updateCrossAccount();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/createCrossAccountAccess")
	public String createCrossAccountAccess(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.createCrossAccountAccess();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/updateCrossAccountAccess")
	public String updateCrossAccountAccess(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.updateCrossAccountAccess();
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/objectEventSubscriptions")
	public String createObjectEventSubscriptionTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.createObjectEventSubscriptionTest();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/patchobjectEventSubscriptions")
	public String patchObjectEventSubscriptionTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.patchObjectEventSubscriptionTest();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/objects")
	public String queryObjectsOperation(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.queryObjectsOperation();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/deleteObjectsJobs")
	public String createDeleteObjectsJob(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.createDeleteObjectsJob();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getalldeleteObjectsJobs")
	public String getallDeleteObjectsJob(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.getallDeleteObjectsJob();
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/getdeleteObjectsJobs/{id}")
	public String getDeleteObjectsJob(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.getDeleteObjectsJob(id);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/getdeleteObjectsJoberrors/{id}")
	public String getdeleteObjectsJoberrors(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.getdeleteObjectsJoberrors(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/accessTokenPermissions")
	public String listAccessTokenPermissionsTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.listAccessTokenPermissionsTest();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/accessTokenPermissions/{id}")
	public String getAccessTokenPermissionsTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.getAccessTokenPermissionsTest(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/deleteaccessTokenPermissions/{id}")
	public String deleteAccessTokenPermissionsTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.deleteAccessTokenPermissionsTest(id);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/createaccessTokenPermissions")
	public String createAccessTokenPermissionsTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.createAccessTokenPermissionsTest();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/generateAccessToken")
	public String generateAccessTokenTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.generateAccessTokenTest();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/objectMetadata")
	public String createOrUpdateObjectMetadataTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.createOrUpdateObjectMetadataTest();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/retrieveobjectMetadata")
	public String retrieveObjectMetadataTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.retrieveObjectMetadataTest();
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/timeSeriesImportJobs")
	public String createTimeSeriesImportJobTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.createTimeSeriesImportJobTest();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listtimeSeriesImportJobs")
	public String queryTimeSeriesImportJobsTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.queryTimeSeriesImportJobsTest();
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/timeSeriesImportJobsdetails/{id}")
	public String retrieveTimeSeriesImportJobDetailsTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.retrieveTimeSeriesImportJobDetailsTest(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/deletetimeSeriesImportJobs/{id}")
	public String deleteTimeSeriesImportJobDetailsTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.deleteTimeSeriesImportJobDetailsTest(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/timeSeriesImportJobs/{id}")
	public String retrieveTimeSeriesImportJobTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,
			HttpServletRequest request) throws MindsphereException {

		IDLHelper.selectToken(idlService, token, request.getRequestURL().toString());
		return idlService.retrieveTimeSeriesImportJobTest(id);
	}
	
	
	
	
}
