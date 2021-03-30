package com.siemens.mindsphere.helpers;

import java.util.ArrayList;
import java.util.List;

import com.siemens.mindsphere.sdk.integrateddatalake.apiclient.ObjectAccessOperationsClient;
import com.siemens.mindsphere.sdk.integrateddatalake.apiclient.ObjectEventSubscriptionOperationsClient;
import com.siemens.mindsphere.sdk.integrateddatalake.apiclient.ObjectOperationsClient;
import com.siemens.mindsphere.sdk.integrateddatalake.apiclient.ObjectOperationsWithAccessTokenClient;
import com.siemens.mindsphere.sdk.integrateddatalake.apiclient.ObjectsMetadataCatalogOperationsClient;
import com.siemens.mindsphere.sdk.integrateddatalake.apiclient.TimeSeriesBulkImportClient;
import com.siemens.mindsphere.sdk.integrateddatalake.model.AccessPermission;
import com.siemens.mindsphere.sdk.integrateddatalake.model.AccessTokenPermissionRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.AccessTokenPermissionsRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CreateCrossAccountAccessRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CreateCrossAccountRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CreateDeleteObjectsJobRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CreateObjectEventSubscriptionRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CreateOrUpdateObjectMetadataRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CreateTimeSeriesImportJobRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CrossAccount;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CrossAccountAccess;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CrossAccountAccessRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CrossAccountAccessRequest.StatusEnum;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CrossAccountRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.CrossAccountUpdateRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.DeleteObjectsJobRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.DeleteObjectsJobRequestObjects;
import com.siemens.mindsphere.sdk.integrateddatalake.model.GenerateDownloadObjectUrlsRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.GenerateUploadObjectUrlsRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.GenerateUrlPayload;
import com.siemens.mindsphere.sdk.integrateddatalake.model.ImportJobRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.ImportJobResponse;
import com.siemens.mindsphere.sdk.integrateddatalake.model.Metadata;
import com.siemens.mindsphere.sdk.integrateddatalake.model.PatchObjectEventSubscriptionRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.Path;
import com.siemens.mindsphere.sdk.integrateddatalake.model.Paths;
import com.siemens.mindsphere.sdk.integrateddatalake.model.Permission;
import com.siemens.mindsphere.sdk.integrateddatalake.model.Subscription;
import com.siemens.mindsphere.sdk.integrateddatalake.model.SubscriptionResponse;
import com.siemens.mindsphere.sdk.integrateddatalake.model.SubscriptionUpdate;
import com.siemens.mindsphere.sdk.integrateddatalake.model.UpdateCrossAccountAccessRequest;
import com.siemens.mindsphere.sdk.integrateddatalake.model.UpdateCrossAccountRequest;

public class IDLHelper extends ControllerHelper {

	public ObjectAccessOperationsClient getObjectAccessOperationsClient(String token, String host) {
		ObjectAccessOperationsClient eventOperationsClient = ObjectAccessOperationsClient.builder().restClientConfig(getConfig(host))
				.mindsphereCredentials(getCreds(token)).build();

		return eventOperationsClient;
	}
	
	
	public ObjectEventSubscriptionOperationsClient getObjectEventSubscriptionOperationsClient(String token, String host) {
		ObjectEventSubscriptionOperationsClient objectEventSubscriptionOperationsClient = ObjectEventSubscriptionOperationsClient.builder().restClientConfig(getConfig(host))
				.mindsphereCredentials(getCreds(token)).build();

		return objectEventSubscriptionOperationsClient;
	}
	
	
	public ObjectOperationsClient getObjectOperationsClient(String token, String host) {
		ObjectOperationsClient objectOperationsClient = ObjectOperationsClient.builder().restClientConfig(getConfig(host))
				.mindsphereCredentials(getCreds(token)).build();

		return objectOperationsClient;
	}
	
	public ObjectOperationsWithAccessTokenClient getObjectOperationsWithAccessTokenClient(String token, String host) {
		ObjectOperationsWithAccessTokenClient objectOperationsWithAccessTokenClient = ObjectOperationsWithAccessTokenClient.builder().restClientConfig(getConfig(host))
				.mindsphereCredentials(getCreds(token)).build();

		return objectOperationsWithAccessTokenClient;
	}
	
	public ObjectsMetadataCatalogOperationsClient getObjectsMetadataCatalogOperationsClient(String token, String host) {
		ObjectsMetadataCatalogOperationsClient objectsMetadataCatalogOperationsClient = ObjectsMetadataCatalogOperationsClient.builder().restClientConfig(getConfig(host))
				.mindsphereCredentials(getCreds(token)).build();

		return objectsMetadataCatalogOperationsClient;
	}
	
	public TimeSeriesBulkImportClient getTimeSeriesBulkImportClient(String token, String host) {
		TimeSeriesBulkImportClient timeSeriesBulkImportClient = TimeSeriesBulkImportClient.builder().restClientConfig(getConfig(host))
				.mindsphereCredentials(getCreds(token)).build();

		return timeSeriesBulkImportClient;
	}
	
	
	

	public GenerateUploadObjectUrlsRequest getGenerateUploadObjectUrlsRequest() {
		GenerateUploadObjectUrlsRequest requestObj = new GenerateUploadObjectUrlsRequest();
		GenerateUrlPayload generateUrlPayload = new GenerateUrlPayload();
		Path path = new Path();
		path.setPath("myfolder/mysubfolder/myobject.objext");
		Paths paths = new Paths();
		paths.add(path);
		generateUrlPayload.setPaths(paths);
		requestObj.setGenerateUrlPayload(generateUrlPayload);
		return requestObj;
	}

	public GenerateDownloadObjectUrlsRequest getGenerateDownloadObjectUrlsRequest() {
		GenerateDownloadObjectUrlsRequest requestObj = new GenerateDownloadObjectUrlsRequest();
		GenerateUrlPayload generateUrlPayload = new GenerateUrlPayload();
		Path path = new Path();
		path.setPath("myfolder/mysubfolder/myobject.objext");
		Paths paths = new Paths();
		paths.add(path);
		generateUrlPayload.setPaths(paths);
		requestObj.setGenerateUrlPayload(generateUrlPayload);
		return requestObj;
		
	}

	public CreateCrossAccountRequest getCreateCrossAccountRequest(String accessId) {
		CreateCrossAccountRequest requestObj = new CreateCrossAccountRequest();
		CrossAccountRequest crossAccountRequest = new CrossAccountRequest();
		crossAccountRequest.setAccessorAccountId(accessId);
		crossAccountRequest.setName("test");
		crossAccountRequest.setDescription("Testing");
		requestObj.setCrossAccountRequest(crossAccountRequest);
		
		return requestObj;
	}
	public UpdateCrossAccountRequest getUpdateCrossAccountRequest(CrossAccount crossAccount) {
		UpdateCrossAccountRequest requestObj = new UpdateCrossAccountRequest();
		CrossAccountUpdateRequest crossAccountUpdateRequest = new CrossAccountUpdateRequest();
		crossAccountUpdateRequest.setName("update test");
		crossAccountUpdateRequest.setDescription("updating test");
		requestObj.setCrossAccountRequest(crossAccountUpdateRequest);
		requestObj.setId(crossAccount.getId());
		requestObj.setIfMatch(crossAccount.getETag().intValue());
		return requestObj;
	}

	public CreateCrossAccountAccessRequest getCreateCrossAccountAccessRequest(String id) {
		CreateCrossAccountAccessRequest requestObj = new CreateCrossAccountAccessRequest();
		requestObj.setId(id);
		CrossAccountAccessRequest crossAccountAccessRequest = new CrossAccountAccessRequest();
		crossAccountAccessRequest.setDescription("creating account access");
		crossAccountAccessRequest.setPath("myfolder/mysubfolder");
		crossAccountAccessRequest.setPermission(Permission.DELETE);
		crossAccountAccessRequest.setStatus(StatusEnum.DISABLED);
		requestObj.setCrossAccountAccessRequest(crossAccountAccessRequest);
		return requestObj;
	}

	public UpdateCrossAccountAccessRequest getUpdateCrossAccountAccessRequest(String id,
			CrossAccountAccess crossAccountAccess) {
		UpdateCrossAccountAccessRequest requestObj = new UpdateCrossAccountAccessRequest();
		requestObj.setId(id);
		requestObj.setAccessId(crossAccountAccess.getId());
		CrossAccountAccessRequest crossAccountAccessRequest = new CrossAccountAccessRequest();
		crossAccountAccessRequest.setDescription("updating");
		crossAccountAccessRequest.setPath("myfolder/mysubfolder");
		crossAccountAccessRequest.setPermission(Permission.READ);
		crossAccountAccessRequest.setStatus(StatusEnum.DISABLED);
		requestObj.setCrossAccountAccessRequest(crossAccountAccessRequest);
		requestObj.setIfMatch(crossAccountAccess.getETag().intValue());
		return requestObj;
	}


	public CreateObjectEventSubscriptionRequest getCreateObjectEventSubscriptionRequest(
			SubscriptionResponse subscriptionResponse) {
		CreateObjectEventSubscriptionRequest requestObj = new CreateObjectEventSubscriptionRequest();
		Subscription subscription = new Subscription();
		subscription.setDestination(subscriptionResponse.getDestination());
		subscription.setPath(subscriptionResponse.getStoragePath());
		requestObj.setSubscription(subscription);
		return requestObj;
	}


	public PatchObjectEventSubscriptionRequest getPatchObjectEventSubscriptionRequest(
			SubscriptionResponse subscriptionResponse) {
		PatchObjectEventSubscriptionRequest requestObj = new PatchObjectEventSubscriptionRequest();
		requestObj.setId(subscriptionResponse.getId());
		requestObj.setIfMatch(subscriptionResponse.getETag().intValue());
		SubscriptionUpdate subscription = new SubscriptionUpdate();
		subscription.setDestination(subscriptionResponse.getDestination());
		subscription.setPath(subscriptionResponse.getStoragePath());
		requestObj.setSubscription(subscription);
		return requestObj;
	}


	public CreateDeleteObjectsJobRequest getCreateDeleteObjectsJobRequest() {
		 CreateDeleteObjectsJobRequest requestObj = new CreateDeleteObjectsJobRequest();
		 DeleteObjectsJobRequest deleteObjectsJob = new DeleteObjectsJobRequest();
		 List<DeleteObjectsJobRequestObjects> objects = new ArrayList<DeleteObjectsJobRequestObjects>();
		 DeleteObjectsJobRequestObjects deleteObjectsJobRequestObjects = new DeleteObjectsJobRequestObjects();
		 deleteObjectsJobRequestObjects.setPath("data/ten=dide2/e2eMetaTag-DONOTDELETE.txt");
		 objects.add(deleteObjectsJobRequestObjects);
		 deleteObjectsJob.setObjects(objects);
		 requestObj.setDeleteObjectsJob(deleteObjectsJob);
		return requestObj;
	}


	public AccessTokenPermissionsRequest getAccessTokenPermissionsRequest() {
		AccessTokenPermissionsRequest requestObj = new AccessTokenPermissionsRequest();
		AccessTokenPermissionRequest accessTokenPermissionRequest = new AccessTokenPermissionRequest();
		accessTokenPermissionRequest.setPath("customFolder");
		accessTokenPermissionRequest.setPermission(AccessPermission.WRITE);
		requestObj.setWritePathPayload(accessTokenPermissionRequest);
		return requestObj;
	}


	public CreateOrUpdateObjectMetadataRequest getCreateOrUpdateObjectMetadataRequest() {
		CreateOrUpdateObjectMetadataRequest requestObj = new CreateOrUpdateObjectMetadataRequest();
		requestObj.setPath("myfolder/mysubfolder/myobject.objext");
		Metadata metadata = new Metadata();
		List<String> tags = new ArrayList<String>();
		tags.add("tag1");
		tags.add("tag2");
		metadata.setTags(tags);
		requestObj.setMetadata(metadata);
		return requestObj;
	}


	public CreateTimeSeriesImportJobRequest CreateTimeSeriesImportJobRequest(String name) {
		CreateTimeSeriesImportJobRequest requestObj = new CreateTimeSeriesImportJobRequest();
		ImportJobRequest importJobRequest = new ImportJobRequest();
		importJobRequest.setName(name);
		importJobRequest.setDestination("myfolder/mysubfolder");
		importJobRequest.setFrom("2017-12-11T13:36:00.000Z");
		importJobRequest.setTo("2018-01-11T13:36:00.000Z");
		List<String> aspectNames = new ArrayList<String>();
		List<String> assetIds = new ArrayList<String>();
		aspectNames.add("string");
		assetIds.add("string");
		importJobRequest.setAspectNames(aspectNames);
		importJobRequest.setAssetIds(assetIds);
        requestObj.setImportJob(importJobRequest);
		return requestObj;
	}
		
}
