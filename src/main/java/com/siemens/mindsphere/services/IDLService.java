package com.siemens.mindsphere.services;

import org.springframework.stereotype.Service;

import com.siemens.mindsphere.helpers.IDLHelper;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.sdk.integrateddatalake.apiclient.ObjectAccessOperationsClient;
import com.siemens.mindsphere.sdk.integrateddatalake.apiclient.ObjectEventSubscriptionOperationsClient;
import com.siemens.mindsphere.sdk.integrateddatalake.apiclient.ObjectOperationsClient;
import com.siemens.mindsphere.sdk.integrateddatalake.apiclient.ObjectOperationsWithAccessTokenClient;
import com.siemens.mindsphere.sdk.integrateddatalake.apiclient.ObjectsMetadataCatalogOperationsClient;
import com.siemens.mindsphere.sdk.integrateddatalake.apiclient.TimeSeriesBulkImportClient;
import com.siemens.mindsphere.sdk.integrateddatalake.model.AccessTokenPermissionResource;
import com.siemens.mindsphere.sdk.integrateddatalake.model.AccessTokenPermissionResources;
import com.siemens.mindsphere.sdk.integrateddatalake.model.AccessTokenPermissionsRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.AccessTokens;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CreateCrossAccountAccessRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CreateCrossAccountRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CreateDeleteObjectsJobRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CreateObjectEventSubscriptionRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CreateOrUpdateObjectMetadataRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CreateTimeSeriesImportJobRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CrossAccount;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CrossAccountAccess;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CrossAccountAccessListResource;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CrossAccountListResource;
import com.siemens.mindsphere.sdk.integrateddatalake.model.DeleteAccessTokenPermissionsRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.DeleteCrossAccountAccessRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.DeleteCrossAccountRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.DeleteObjectEventSubscriptionRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.DeleteObjectsJobErrorDetailsResponse;
import com.siemens.mindsphere.sdk.integrateddatalake.model.DeleteObjectsJobList;
import com.siemens.mindsphere.sdk.integrateddatalake.model.DeleteObjectsJobResponse;
import com.siemens.mindsphere.sdk.integrateddatalake.model.DeleteTimeSeriesImportJobRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.GenerateAccessTokenRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.GenerateDownloadObjectUrlsRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.GenerateSTSPayload;
import com.siemens.mindsphere.sdk.integrateddatalake.model.GenerateUploadObjectUrlsRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.GetAccessTokenPermissionsRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.GetAllDeleteObjectsJobRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.GetCrossAccountAccessRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.GetCrossAccountRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.GetDeleteObjectsJobErrorsRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.GetDeleteObjectsJobRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.ImportJobDetails;
import com.siemens.mindsphere.sdk.integrateddatalake.model.ImportJobListResource;
import com.siemens.mindsphere.sdk.integrateddatalake.model.ImportJobResponse;
import com.siemens.mindsphere.sdk.integrateddatalake.model.ListAccessTokenPermissionsRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.ListCrossAccountAccessRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.ListCrossAccountRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.ObjectListResponse;
import com.siemens.mindsphere.sdk.integrateddatalake.model.ObjectMetaDataResponse;
import com.siemens.mindsphere.sdk.integrateddatalake.model.PatchObjectEventSubscriptionRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.QueryObjectEventSubscriptionsRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.QueryObjectsRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.QueryTimeSeriesImportJobsRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.RetrieveObjectEventSubscriptionRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.RetrieveObjectMetadataRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.RetrieveTimeSeriesImportJobDetailsRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.RetrieveTimeSeriesImportJobRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.SignedUrlResponse;
import com.siemens.mindsphere.sdk.integrateddatalake.model.Subscription;
import com.siemens.mindsphere.sdk.integrateddatalake.model.SubscriptionListResource;
import com.siemens.mindsphere.sdk.integrateddatalake.model.SubscriptionResponse;
import com.siemens.mindsphere.sdk.integrateddatalake.model.UpdateCrossAccountAccessRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.UpdateCrossAccountRequest;

@Service
public class IDLService extends MindsphereService {

	IDLHelper idlHelper = new IDLHelper();

	public String generateUploadObjectUrlsTest() throws MindsphereException {
		ObjectAccessOperationsClient objectAccessOperationsClient = idlHelper
				.getObjectAccessOperationsClient(getToken(), getHostName());

		GenerateUploadObjectUrlsRequest generateUploadObjectUrlsRequest = idlHelper
				.getGenerateUploadObjectUrlsRequest();
		SignedUrlResponse response = objectAccessOperationsClient
				.generateUploadObjectUrls(generateUploadObjectUrlsRequest);
		if (response != null)
			return response.toString();
		else
			return null;

	}

	public String generateDownloadObjectUrlsTest() throws MindsphereException {
		ObjectAccessOperationsClient objectAccessOperationsClient = idlHelper
				.getObjectAccessOperationsClient(getToken(), getHostName());

		GenerateDownloadObjectUrlsRequest generateDownloadObjectUrlsRequest = idlHelper
				.getGenerateDownloadObjectUrlsRequest();
		SignedUrlResponse response = objectAccessOperationsClient
				.generateDownloadObjectUrls(generateDownloadObjectUrlsRequest);
		if (response != null)
			return response.toString();
		else
			return null;

	}

	public String createCrossAccount() throws MindsphereException {
		String finalresponse = null;
		ObjectAccessOperationsClient objectAccessOperationsClient = idlHelper
				.getObjectAccessOperationsClient(getToken(), getHostName());
		ListCrossAccountRequest requestObj = new ListCrossAccountRequest();
		CrossAccountListResource response = objectAccessOperationsClient.listCrossAccount(requestObj);
		if (response != null && response.getCrossAccounts().size() > 0) {
			CrossAccount crossaccount = response.getCrossAccounts().get(0);
			String id = crossaccount.getId();
			String accessId = crossaccount.getAccessorAccountId();
			DeleteCrossAccountRequest delrequestObj = new DeleteCrossAccountRequest();
			delrequestObj.setId(crossaccount.getId());
			delrequestObj.setIfMatch(crossaccount.getETag().intValue());
			objectAccessOperationsClient.deleteCrossAccount(delrequestObj);
			CreateCrossAccountRequest crossaccountrequestObj = idlHelper.getCreateCrossAccountRequest(accessId);
			CrossAccount response1 = objectAccessOperationsClient.createCrossAccount(crossaccountrequestObj);
			if (response != null && response != null)
				finalresponse = getselectedOutput(response, response1);
		} else {
			CreateCrossAccountRequest crossaccountrequestObj = idlHelper.getCreateCrossAccountRequest("565037524705");
			CrossAccount response1 = objectAccessOperationsClient.createCrossAccount(crossaccountrequestObj);
			if (response1 != null)
				return response1.toString();
			else
				return null;

		}
		return finalresponse;
	}

	private String getselectedOutput(CrossAccountListResource response, CrossAccount response1) {
		String finalresponse = null;

		finalresponse = "listCrossAccount " + response.getCrossAccounts().get(0).toString() + " CreateCrossAccount "
				+ response1.toString();
		return finalresponse;
	}

	public String updateCrossAccount() throws MindsphereException {
		String finalResponse = null;
		ObjectAccessOperationsClient objectAccessOperationsClient = idlHelper
				.getObjectAccessOperationsClient(getToken(), getHostName());
		ListCrossAccountRequest requestObj = new ListCrossAccountRequest();
		CrossAccountListResource response = objectAccessOperationsClient.listCrossAccount(requestObj);
		if (response != null && response.getCrossAccounts().size() > 0) {
			GetCrossAccountRequest getrequestObj = new GetCrossAccountRequest();
			getrequestObj.setId(response.getCrossAccounts().get(0).getId());
			CrossAccount getresponse = objectAccessOperationsClient.getCrossAccount(getrequestObj);
			UpdateCrossAccountRequest updaterequestObj = idlHelper
					.getUpdateCrossAccountRequest(response.getCrossAccounts().get(0));
			CrossAccount updatedresponse = objectAccessOperationsClient.updateCrossAccount(updaterequestObj);
			if (updatedresponse != null && getresponse != null)
				finalResponse = "GetCrossAccount " + getresponse.toString() + " UpdateCrossAccount "
						+ updatedresponse.toString();
		} else {
			if(response!=null)
				return response.toString();
			finalResponse = "No Crossaccount found , First create  Crossaccount";
		}
		return finalResponse;
	}

	public String createCrossAccountAccess() throws MindsphereException {
		ObjectAccessOperationsClient objectAccessOperationsClient = idlHelper
				.getObjectAccessOperationsClient(getToken(), getHostName());
		ListCrossAccountRequest requestObj = new ListCrossAccountRequest();
		CrossAccountAccess crossAccountAccessresponse = null;
		CrossAccountListResource response = objectAccessOperationsClient.listCrossAccount(requestObj);
		String finalresponse = null;
		if (response != null && response.getCrossAccounts().size() > 0) {
			ListCrossAccountAccessRequest listrequestObj = new ListCrossAccountAccessRequest();
			listrequestObj.setId(response.getCrossAccounts().get(0).getId());
			CrossAccountAccessListResource listresponse = objectAccessOperationsClient
					.listCrossAccountAccess(listrequestObj);
			if (listresponse != null && listresponse.getCrossAccountAccesses().size() > 0) {
				CrossAccountAccess crossAccountAccess = listresponse.getCrossAccountAccesses().get(0);
				DeleteCrossAccountAccessRequest delrequestObj = new DeleteCrossAccountAccessRequest();
				delrequestObj.setId(response.getCrossAccounts().get(0).getId());
				delrequestObj.setAccessId(crossAccountAccess.getId());
				delrequestObj.setIfMatch(crossAccountAccess.getETag().intValue());
				objectAccessOperationsClient.deleteCrossAccountAccess(delrequestObj);
				CreateCrossAccountAccessRequest createaccessrequestObj = idlHelper
						.getCreateCrossAccountAccessRequest(response.getCrossAccounts().get(0).getId());
				crossAccountAccessresponse = objectAccessOperationsClient
						.createCrossAccountAccess(createaccessrequestObj);

			}

			else {
				CreateCrossAccountAccessRequest createaccessrequestObj = idlHelper
						.getCreateCrossAccountAccessRequest(response.getCrossAccounts().get(0).getId());
				crossAccountAccessresponse = objectAccessOperationsClient
						.createCrossAccountAccess(createaccessrequestObj);

			}
			if (listresponse != null && crossAccountAccessresponse != null)
				finalresponse = "ListcrossAccountAccess " + listresponse + " CreatecrossAccountAccess "
						+ crossAccountAccessresponse;

		} else {
			if(response!=null)
				return response.toString();
			finalresponse = "No Crossaccount found , First create  Crossaccount";
		}
		return finalresponse;
	}

	public String updateCrossAccountAccess() throws MindsphereException {
		String finalresponse = null;
		ObjectAccessOperationsClient objectAccessOperationsClient = idlHelper
				.getObjectAccessOperationsClient(getToken(), getHostName());
		ListCrossAccountRequest requestObj = new ListCrossAccountRequest();
		CrossAccountListResource response = objectAccessOperationsClient.listCrossAccount(requestObj);
		if (response != null && response.getCrossAccounts().size() > 0) {
			ListCrossAccountAccessRequest listrequestObj = new ListCrossAccountAccessRequest();
			listrequestObj.setId(response.getCrossAccounts().get(0).getId());
			CrossAccountAccessListResource listresponse = objectAccessOperationsClient
					.listCrossAccountAccess(listrequestObj);
			if (listresponse != null && listresponse.getCrossAccountAccesses().size() > 0) {
				CrossAccountAccess crossAccountAccess = listresponse.getCrossAccountAccesses().get(0);
				GetCrossAccountAccessRequest getrequestObj = new GetCrossAccountAccessRequest();
				getrequestObj.setId(response.getCrossAccounts().get(0).getId());
				getrequestObj.setAccessId(crossAccountAccess.getId());
				CrossAccountAccess getresponse = objectAccessOperationsClient.getCrossAccountAccess(getrequestObj);
				UpdateCrossAccountAccessRequest updaterequestObj = idlHelper.getUpdateCrossAccountAccessRequest(
						response.getCrossAccounts().get(0).getId(), crossAccountAccess);
				CrossAccountAccess updateresponse = objectAccessOperationsClient
						.updateCrossAccountAccess(updaterequestObj);
				if (getresponse != null && updateresponse != null)
					finalresponse = "GetcrossAccountAccess " + getresponse.toString() + " UpdatecrossAccountAccess "
							+ updateresponse.toString();
				return finalresponse;
			}

		} else {
			if(response!=null)
				return response.toString();
			finalresponse = "No CrossaccountAccess found , First create one CrossaccountAccess";
		}

		return finalresponse;
	}

	public String createObjectEventSubscriptionTest() throws MindsphereException {
		String finalresponse = null;
		ObjectEventSubscriptionOperationsClient objectEventSubscriptionOperationsClient = idlHelper
				.getObjectEventSubscriptionOperationsClient(getToken(), getHostName());
		QueryObjectEventSubscriptionsRequest requestObj = new QueryObjectEventSubscriptionsRequest();
		SubscriptionListResource response = objectEventSubscriptionOperationsClient
				.queryObjectEventSubscriptions(requestObj);
		if (response != null && response.getSubscriptions().size() > 0) {
			SubscriptionResponse subscriptionResponse = response.getSubscriptions().get(0);
			DeleteObjectEventSubscriptionRequest delrequestObj = new DeleteObjectEventSubscriptionRequest();
			delrequestObj.setId(subscriptionResponse.getId());
			delrequestObj.setIfMatch(subscriptionResponse.getETag().intValue());
			objectEventSubscriptionOperationsClient.deleteObjectEventSubscription(delrequestObj);
			CreateObjectEventSubscriptionRequest createrequestObj = idlHelper
					.getCreateObjectEventSubscriptionRequest(subscriptionResponse);
			SubscriptionResponse createresponse = objectEventSubscriptionOperationsClient
					.createObjectEventSubscription(createrequestObj);
			if (subscriptionResponse != null && createresponse != null)
				finalresponse = "ListobjectEventSubscriptions" + subscriptionResponse.toString()
						+ "CreateobjectEventSubscriptions" + createresponse.toString();
			return finalresponse;
		} else {
			CreateObjectEventSubscriptionRequest requestObj1 = new CreateObjectEventSubscriptionRequest();
			Subscription subscription = new Subscription();
			subscription.setDestination("aws-sns://arn:aws:sns:eu-central-1:210761511742:dl-test");
			subscription.setPath("myfolder/mysubfolder");
			requestObj1.setSubscription(subscription);
			SubscriptionResponse response1 = objectEventSubscriptionOperationsClient
					.createObjectEventSubscription(requestObj1);
			if (response1 != null)
				return response1.toString();
			else
				return null;

		}
	}

	public String patchObjectEventSubscriptionTest() throws MindsphereException {
		String finalresponse = null;
		ObjectEventSubscriptionOperationsClient objectEventSubscriptionOperationsClient = idlHelper
				.getObjectEventSubscriptionOperationsClient(getToken(), getHostName());
		QueryObjectEventSubscriptionsRequest requestObj = new QueryObjectEventSubscriptionsRequest();
		SubscriptionListResource response = objectEventSubscriptionOperationsClient
				.queryObjectEventSubscriptions(requestObj);
		if (response != null && response.getSubscriptions().size() > 0) {
			SubscriptionResponse subscriptionResponse = response.getSubscriptions().get(0);
			RetrieveObjectEventSubscriptionRequest retrieverequestObj = new RetrieveObjectEventSubscriptionRequest();
			retrieverequestObj.setId(subscriptionResponse.getId());
			SubscriptionResponse retrieveresponse = objectEventSubscriptionOperationsClient
					.retrieveObjectEventSubscription(retrieverequestObj);
			PatchObjectEventSubscriptionRequest patchrequestObj = idlHelper
					.getPatchObjectEventSubscriptionRequest(subscriptionResponse);
			SubscriptionResponse patchresponse = objectEventSubscriptionOperationsClient
					.patchObjectEventSubscription(patchrequestObj);
			if (retrieveresponse != null && patchresponse != null)
				finalresponse = "RetrieveobjectEventSubscriptions" + retrieveresponse.toString()
						+ "PatchobjectEventSubscriptions" + patchresponse.toString();
			return finalresponse;

		} else {
			if(response!=null)
				return response.toString();
			finalresponse = "No EventSubscriptions found , First create  EventSubscriptions";
		}

		return finalresponse;
	}

	public String queryObjectsOperation() throws MindsphereException {
		ObjectOperationsClient objectOperationsClient = idlHelper.getObjectOperationsClient(getToken(), getHostName());
		QueryObjectsRequest requestObj = new QueryObjectsRequest();
		ObjectListResponse response = objectOperationsClient.queryObjects(requestObj);
		if (response != null)
			return response.toString();
		else
			return null;

	}

	public String createDeleteObjectsJob() throws MindsphereException {
		ObjectOperationsClient objectOperationsClient = idlHelper.getObjectOperationsClient(getToken(), getHostName());
		CreateDeleteObjectsJobRequest requestObj = idlHelper.getCreateDeleteObjectsJobRequest();
		DeleteObjectsJobResponse response = objectOperationsClient.createDeleteObjectsJob(requestObj);
		if (response != null)
			return response.toString();
		else
			return null;
	}

	public String getallDeleteObjectsJob() throws MindsphereException {
		ObjectOperationsClient objectOperationsClient = idlHelper.getObjectOperationsClient(getToken(), getHostName());
		GetAllDeleteObjectsJobRequest requestObj = new GetAllDeleteObjectsJobRequest();
		DeleteObjectsJobList response = objectOperationsClient.getAllDeleteObjectsJob(requestObj);
		if (response != null)
			return response.toString();
		else
			return null;
	}

	public String getDeleteObjectsJob(String id) throws MindsphereException {
		ObjectOperationsClient objectOperationsClient = idlHelper.getObjectOperationsClient(getToken(), getHostName());
		GetDeleteObjectsJobRequest requestObj = new GetDeleteObjectsJobRequest();
		requestObj.setId(id);
		DeleteObjectsJobResponse response = objectOperationsClient.getDeleteObjectsJob(requestObj);
		if (response != null)
			return response.toString();
		else
			return null;
	}

	public String getdeleteObjectsJoberrors(String id) throws MindsphereException {
		ObjectOperationsClient objectOperationsClient = idlHelper.getObjectOperationsClient(getToken(), getHostName());
		GetDeleteObjectsJobErrorsRequest requestObj = new GetDeleteObjectsJobErrorsRequest();
		requestObj.setId(id);
		DeleteObjectsJobErrorDetailsResponse response = objectOperationsClient.getDeleteObjectsJobErrors(requestObj);
		if (response != null)
			return response.toString();
		else
			return null;
	}

	public String listAccessTokenPermissionsTest() throws MindsphereException {
		ObjectOperationsWithAccessTokenClient accessTokenClient = idlHelper
				.getObjectOperationsWithAccessTokenClient(getToken(), getHostName());
		ListAccessTokenPermissionsRequest requestObj = new ListAccessTokenPermissionsRequest();
		AccessTokenPermissionResources response = accessTokenClient.listAccessTokenPermissions(requestObj);
		if (response != null)
			return response.toString();
		else
			return null;
	}

	public String getAccessTokenPermissionsTest(String id) throws MindsphereException {
		ObjectOperationsWithAccessTokenClient accessTokenClient = idlHelper
				.getObjectOperationsWithAccessTokenClient(getToken(), getHostName());
		GetAccessTokenPermissionsRequest requestObj = new GetAccessTokenPermissionsRequest();
		requestObj.setId(id);
		AccessTokenPermissionResource response = accessTokenClient.getAccessTokenPermissions(requestObj);
		if (response != null)
			return response.toString();
		else
			return null;
	}

	public String deleteAccessTokenPermissionsTest(String id) throws MindsphereException {
		ObjectOperationsWithAccessTokenClient accessTokenClient = idlHelper
				.getObjectOperationsWithAccessTokenClient(getToken(), getHostName());
		DeleteAccessTokenPermissionsRequest requestObj = new DeleteAccessTokenPermissionsRequest();
		requestObj.setId(id);
		accessTokenClient.deleteAccessTokenPermissions(requestObj);
		return "deleted";
	}

	public String createAccessTokenPermissionsTest() throws MindsphereException {
		ObjectOperationsWithAccessTokenClient accessTokenClient = idlHelper
				.getObjectOperationsWithAccessTokenClient(getToken(), getHostName());
		AccessTokenPermissionsRequest requestObj = idlHelper.getAccessTokenPermissionsRequest();
		AccessTokenPermissionResource response = accessTokenClient.accessTokenPermissions(requestObj);
		if (response != null)
			return response.toString();
		else
			return null;
	}

	public String generateAccessTokenTest() throws MindsphereException {
		ObjectOperationsWithAccessTokenClient accessTokenClient = idlHelper
				.getObjectOperationsWithAccessTokenClient(getToken(), getHostName());
		GenerateAccessTokenRequest requestObj = new GenerateAccessTokenRequest();
		GenerateSTSPayload generateSTSPayload = new GenerateSTSPayload();
		requestObj.setStsPayload(generateSTSPayload);
		AccessTokens response = accessTokenClient.generateAccessToken(requestObj);
		if (response != null)
			return response.toString();
		else
			return null;
	}

	public String createOrUpdateObjectMetadataTest() throws MindsphereException {
		ObjectsMetadataCatalogOperationsClient oCatalogOperationsClient = idlHelper
				.getObjectsMetadataCatalogOperationsClient(getToken(), getHostName());
		CreateOrUpdateObjectMetadataRequest requestObj = idlHelper.getCreateOrUpdateObjectMetadataRequest();
		oCatalogOperationsClient.createOrUpdateObjectMetadata(requestObj);
		return "created/updated";

	}

	public String retrieveObjectMetadataTest() throws MindsphereException {
		ObjectsMetadataCatalogOperationsClient oCatalogOperationsClient = idlHelper
				.getObjectsMetadataCatalogOperationsClient(getToken(), getHostName());
		RetrieveObjectMetadataRequest requestObj = new RetrieveObjectMetadataRequest();
		requestObj.setPath("myfolder/mysubfolder/myobject.objext");
		ObjectMetaDataResponse response = oCatalogOperationsClient.retrieveObjectMetadata(requestObj);
		if (response != null)
			return response.toString();
		else
			return null;
	}

	public String createTimeSeriesImportJobTest() throws MindsphereException {
		String finalresponse = null;
		TimeSeriesBulkImportClient timeSeriesBulkImportClient = idlHelper.getTimeSeriesBulkImportClient(getToken(),
				getHostName());
		QueryTimeSeriesImportJobsRequest requestObj = new QueryTimeSeriesImportJobsRequest();
		ImportJobListResource response = timeSeriesBulkImportClient.queryTimeSeriesImportJobs(requestObj);
		if (response != null && response.getTimeSeriesImportJobs().size() > 0) {
			ImportJobResponse importJobResponse = response.getTimeSeriesImportJobs().get(0);
			DeleteTimeSeriesImportJobRequest delrequestObj = new DeleteTimeSeriesImportJobRequest();
			delrequestObj.setId(importJobResponse.getId());
			timeSeriesBulkImportClient.deleteTimeSeriesImportJob(delrequestObj);
			CreateTimeSeriesImportJobRequest createrequestObj = idlHelper
					.CreateTimeSeriesImportJobRequest(importJobResponse.getName());
			ImportJobResponse createresponse = timeSeriesBulkImportClient.createTimeSeriesImportJob(createrequestObj);
			if (importJobResponse != null && createresponse != null)
				finalresponse = "ListTimeSeriesImportJob " + importJobResponse.toString()
						+ " CreateTimeSeriesImportJob " + createresponse.toString();
			return finalresponse;
		} else {
			CreateTimeSeriesImportJobRequest createrequestObj = idlHelper.CreateTimeSeriesImportJobRequest("Test");
			ImportJobResponse createresponse = timeSeriesBulkImportClient.createTimeSeriesImportJob(createrequestObj);
			if (createresponse != null)
				finalresponse = "CreateTimeSeriesImportJob " + createresponse.toString();
			return finalresponse;
		}
	}

	public String queryTimeSeriesImportJobsTest() throws MindsphereException {
		TimeSeriesBulkImportClient timeSeriesBulkImportClient = idlHelper.getTimeSeriesBulkImportClient(getToken(),
				getHostName());
		QueryTimeSeriesImportJobsRequest requestObj = new QueryTimeSeriesImportJobsRequest();
		ImportJobListResource response = timeSeriesBulkImportClient.queryTimeSeriesImportJobs(requestObj);
		if (response != null)
			return response.toString();
		else
			return null;
	}

	public String retrieveTimeSeriesImportJobDetailsTest(String id) throws MindsphereException {
		TimeSeriesBulkImportClient timeSeriesBulkImportClient = idlHelper.getTimeSeriesBulkImportClient(getToken(),
				getHostName());
		RetrieveTimeSeriesImportJobDetailsRequest requestObj = new RetrieveTimeSeriesImportJobDetailsRequest();
		requestObj.setId(id);
		ImportJobDetails response = timeSeriesBulkImportClient.retrieveTimeSeriesImportJobDetails(requestObj);
		if (response != null)
			return response.toString();
		else
			return null;
	}

	public String deleteTimeSeriesImportJobDetailsTest(String id) throws MindsphereException {
		TimeSeriesBulkImportClient timeSeriesBulkImportClient = idlHelper.getTimeSeriesBulkImportClient(getToken(),
				getHostName());
		DeleteTimeSeriesImportJobRequest requestObj = new DeleteTimeSeriesImportJobRequest();
		requestObj.setId(id);
		timeSeriesBulkImportClient.deleteTimeSeriesImportJob(requestObj);
		return "deleted";
	}

	public String retrieveTimeSeriesImportJobTest(String id) throws MindsphereException {
		TimeSeriesBulkImportClient timeSeriesBulkImportClient = idlHelper.getTimeSeriesBulkImportClient(getToken(),
				getHostName());
		RetrieveTimeSeriesImportJobRequest requestObj = new RetrieveTimeSeriesImportJobRequest();
		requestObj.setId(id);
		ImportJobResponse response = timeSeriesBulkImportClient.retrieveTimeSeriesImportJob(requestObj);
		if (response != null)
			return response.toString();
		else
			return null;
	}

}
