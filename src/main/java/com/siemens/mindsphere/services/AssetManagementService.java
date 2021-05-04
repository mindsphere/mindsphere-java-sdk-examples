package com.siemens.mindsphere.services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.google.gson.GsonBuilder;
import com.siemens.mindsphere.helpers.AssetManagementHelper;
import com.siemens.mindsphere.sdk.assetmanagement.apiclient.AspecttypeClient;
import com.siemens.mindsphere.sdk.assetmanagement.apiclient.AssetsClient;
import com.siemens.mindsphere.sdk.assetmanagement.apiclient.AssettypeClient;
import com.siemens.mindsphere.sdk.assetmanagement.apiclient.FilesClient;
import com.siemens.mindsphere.sdk.assetmanagement.apiclient.LocationsClient;
import com.siemens.mindsphere.sdk.assetmanagement.model.AddAssetRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.AspectType;
import com.siemens.mindsphere.sdk.assetmanagement.model.AspectTypeListResource;
import com.siemens.mindsphere.sdk.assetmanagement.model.AspectTypeResource;
import com.siemens.mindsphere.sdk.assetmanagement.model.Asset;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetListResource;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetResource;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetResourceWithHierarchyPath;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetType;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetTypeListResource;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetTypeResource;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetTypeResourceAspects;
import com.siemens.mindsphere.sdk.assetmanagement.model.DeleteAssetRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.FileMetadataResource;
import com.siemens.mindsphere.sdk.assetmanagement.model.GetAssetRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.GetRootAssetRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.KeyedFileAssignment;
import com.siemens.mindsphere.sdk.assetmanagement.model.ListAssetsRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.RootAssetResource;
import com.siemens.mindsphere.sdk.assetmanagement.model.SaveAspectTypeRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.SaveAssetFileAssignmentRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.SaveAssetLocationRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.SaveAssetTypeRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.UploadFileRequest;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.sdk.timeseries.apiclient.TimeSeriesOperationsClient;
import com.siemens.mindsphere.sdk.timeseries.model.DeleteUpdatedTimeseriesRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AssetManagementService extends MindsphereService {

	private AssetManagementHelper assetManagementHelper = new AssetManagementHelper();
	

	public String createAssetPackage(String tenantName) throws MindsphereException, IOException {

		log.info("Calling createAspectType()");
		AspectTypeResource aspect = createAspectType(tenantName);
		log.info("Calling createAssetType()");
		AssetTypeResource resource = createAssetType(tenantName, aspect);
		log.info("Calling createAsset()");
		AssetResourceWithHierarchyPath assetObj = createAsset(tenantName, resource);
		return getFinalResponse(aspect, resource, assetObj);
	}

	private AssetResourceWithHierarchyPath createAsset(String tenantName, AssetTypeResource resource)
			throws MindsphereException, IOException {

		String fileName = "integ_" + assetManagementHelper.getRandomNumber();
		// File currFile = new File("src/main/resources/test.txt");

		String filePath = "src/main/resources/test.txt";
		InputStream in = new ByteArrayInputStream(filePath.getBytes());
		// InputStream in = this.getClass().getResourceAsStream(filePath);
		File currFile = stream2file(in);

		FilesClient assetFileClient = assetManagementHelper.getFileClient(getToken(), getHostName());
		FileMetadataResource  metadata = assetFileClient.uploadFile(currFile, fileName, null, "test desc");
		log.info("file uploaded successfully with file name"+fileName);

		AssetsClient assetClient = assetManagementHelper.getAssetClient(getToken(), getHostName());
		 log.info("assetClient initialized successfully");
	
		RootAssetResource rootAsset = assetClient.getRootAsset(new GetRootAssetRequest());
		log.info("Getting RootAsset Successfully "+rootAsset);
		Asset asset = assetManagementHelper.createAsset(tenantName,resource, rootAsset.getAssetId());
		//System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(rootAsset));
		AssetResourceWithHierarchyPath assetObj = null;

		// addAsset API call
		assetObj = assetClient.addAsset(asset);
		log.info("Asset Created successfully "+assetObj);

		String id = assetObj.getAssetId();
		String key = "Demo1";
		Integer ifMatch = assetObj.getEtag();
		KeyedFileAssignment assignment = new KeyedFileAssignment();
		assignment.fileId(metadata.getId());
		assetClient.saveAssetFileAssignment(id, key, ifMatch.toString(), assignment);
		log.info("File created with name " + fileName + " and assigned to Asset with ID " + id);
		return assetObj;
	}

	private AssetTypeResource createAssetType(String tenantName, AspectTypeResource aspect)
			throws MindsphereException, IOException {

		String fileName = "integ_" + assetManagementHelper.getRandomNumber();
		// File currFile = new File("src/main/resources/assetTypeTest.txt");

		String filePath = "src/main/resources/AssetTypeTest.txt";

		InputStream in = new ByteArrayInputStream(filePath.getBytes());
		// InputStream in = this.getClass().getResourceAsStream(filePath);
		File currFile = stream2file(in);

		FilesClient assetTypeFileClient = assetManagementHelper.getFileClient(getToken(), getHostName());
		log.info("assetTypeFileClient initialized successfully");
		FileMetadataResource metadata =  assetTypeFileClient.uploadFile(currFile, fileName, null, "test description");
		log.info("file uploaded successfully");
		AssettypeClient assetTypeClient = assetManagementHelper.getAssetTypeClient(getToken(), getHostName());
		log.info("assetTypeClient initialized successfully");
		AssetType assetTypeDTO = assetManagementHelper.createAssetType(tenantName, aspect);
		

		// saveAssetType API call
		AssetTypeResource resource = assetTypeClient.saveAssetType(assetManagementHelper.getAssetId(), assetTypeDTO,
				null);
		log.info("assetType created successfully "+resource);

		String id = resource.getId();
		String key = "Demo2";
		Integer ifMatch = resource.getEtag();
		KeyedFileAssignment assignment = new KeyedFileAssignment();

		assignment.setFileId(metadata.getId());
		assetTypeClient.saveAssetTypeFileAssignment(ifMatch.toString(), id, key, assignment);

		log.info("File created with name " + fileName + " and assigned to Asset Type with ID " + id);

		return resource;
	}

    private AspectTypeResource createAspectType(String tenantName) throws MindsphereException {
        AspecttypeClient aspectTypeClient = assetManagementHelper.getAspectTypeClient(getToken(), getHostName());
        log.info("aspectTypeClient initialized successfully");
        AspectType aspectType = assetManagementHelper.createAspectType(tenantName);

        // creating Aspect type API call
        AspectTypeResource response = aspectTypeClient.saveAspectType(assetManagementHelper.creatAspectTypeRequestModel(assetManagementHelper.getAspectId(), aspectType));
        log.info("aspectTypeClient created successfully "+ response);
        return response;
    }

	public String getFinalResponse(AspectTypeResource aspect, AssetTypeResource resource,
			AssetResourceWithHierarchyPath assetObj) {

		String finalResponse = " Created Aspect Type :: ID: " + aspect.getId() + " NAME:" + aspect.getName()
				+ " /n Created Asset Type :: ID: " + resource.getId() + " NAME: " + resource.getName()
				+ " Created Asset :: ID: " + assetObj.getAssetId() + " Parent ID: " + assetObj.getParentId();
		return finalResponse;
	}

	public String getAssetPackage() throws MindsphereException {
		log.info("Calling getAspectType()");
		List<String> aspectList = getAspectType();
		log.info("Calling getAssetType()");
		List<List> assetTypeList = getAssetType();
		log.info("Calling getAsset()");
		List<List> assetList = getAsset();
		String finalResponse = "Aspect Type Names :: " + aspectList + "\n" + " Asset Type Names :: "
				+ assetTypeList.get(0) + "\n" + " Asset Type file assignments ::" + assetTypeList.get(1)
				+ " Asset Names :: " + assetList.get(0) + "\n" + " Asset file assignments ::" + assetList.get(1);

		return finalResponse;
	}

	private List<List> getAsset() throws MindsphereException {
		AssetsClient assetClient = assetManagementHelper.getAssetClient(getToken(), getHostName());
		log.info("assetClient initialized successfully");
		List<String> assetList = new ArrayList<String>();
		List<List> assetResourceCombo = new ArrayList<>();
		List<String> fileAssignments = new ArrayList<>();
		AssetListResource assets = assetClient.listAssets(0, 10, null, null, null);
		List<AssetResource> assetsList = assets.getEmbedded().getAssets();
		log.info("Getting  listAssets successfully "+assetsList);
		for (AssetResource assetResource : assetsList) {
			assetList.add(assetResource.getName());

			fileAssignments.add(assetResource.getFileAssignments().toString());
		}
		assetResourceCombo.add(assetList);
		assetResourceCombo.add(fileAssignments);
		return assetResourceCombo;
	}

	private List<List> getAssetType() throws MindsphereException {
		AssettypeClient assetTypeClient = assetManagementHelper.getAssetTypeClient(getToken(), getHostName());
		log.info("assetTypeClient initialized successfully");
		List<String> assetTypeList = new ArrayList<String>();
		List<List> assetTypeResourceCombo = new ArrayList<>();
		List<String> fileAssignments = new ArrayList<>();
		int page = 0, size = 10;
		AssetTypeListResource assetTypes = null;

		// list of assetTypes API call
		assetTypes = assetTypeClient.listAssetTypes(page, size, null, null, null, false);
		log.info("Getting  listAssetTypes successfully "+assetTypes);
		List<AssetTypeResource> assetTypesList = assetTypes.getEmbedded().getAssetTypes();

		for (AssetTypeResource assetTypeResource : assetTypesList) {
			assetTypeList.add(assetTypeResource.getName());
			fileAssignments.add(assetTypeResource.getFileAssignments().toString());
		}
		assetTypeResourceCombo.add(assetTypeList);
		assetTypeResourceCombo.add(fileAssignments);
		return assetTypeResourceCombo;

	}

	private List<String> getAspectType() throws MindsphereException {
		AspecttypeClient aspectTypeClient = assetManagementHelper.getAspectTypeClient(getToken(), getHostName());
		 log.info("aspectTypeClient initialized successfully");
		List<String> aspectTypeList = new ArrayList<String>();
		int page = 0;
		AspectTypeListResource aspects = null;

		// call to listofAspectTypes API
		aspects = aspectTypeClient.listAspectTypes(page, 10, null, null, null);
		log.info("Getting  listAspectTypes successfully "+aspects);
		List<AspectTypeResource> aspectTypes = aspects.getEmbedded().getAspectTypes();
		for (AspectTypeResource aspectTypeResource : aspectTypes) {
			aspectTypeList.add(aspectTypeResource.getName());
		}
		return aspectTypeList;
	}

	public void deleteAssetPackage(String id, String key1, String key2, String from, String to)
			throws MindsphereException {
		List<AssetTypeResourceAspects> aspectTypes;
		AssetTypeResource assetTypeObj;
		String assetTypeId;

		AssetsClient assetClient = assetManagementHelper.getAssetClient(getToken(), getHostName());
		AssettypeClient assetTypeClient = assetManagementHelper.getAssetTypeClient(getToken(), getHostName());
		AspecttypeClient aspectTypeClient = assetManagementHelper.getAspectTypeClient(getToken(), getHostName());
		TimeSeriesOperationsClient timeseriesClient = assetManagementHelper.getTimeseriesClient(getToken(), getHostName());

		Integer ifMatch;
		AssetResourceWithHierarchyPath assetData = assetClient.getAsset(id, null);
		ifMatch = assetData.getEtag();

		// deleting asset file assignment API call
		assetData = assetClient.deleteAssetFileAssigment(id, key1, ifMatch.toString());
		log.info("successfully deleted Asset File Assignment");

		assetTypeId = assetData.getTypeId();
		assetTypeObj = assetTypeClient.getAssetType(assetTypeId, null, null);

		Integer ifmatch_AssetType = assetTypeObj.getEtag();

		aspectTypes = assetTypeObj.getAspects();

		for (AssetTypeResourceAspects aspect : aspectTypes) {
			// deleteTimeseries API call
			DeleteUpdatedTimeseriesRequest deleteUpdatedTimeseriesRequest = new DeleteUpdatedTimeseriesRequest();
			deleteUpdatedTimeseriesRequest.setEntityId(id);
			deleteUpdatedTimeseriesRequest.propertySetName(aspect.getName());
			deleteUpdatedTimeseriesRequest.setFrom(from);
			deleteUpdatedTimeseriesRequest.setTo(to);
			timeseriesClient.deleteTimeseries(deleteUpdatedTimeseriesRequest);
			
			//timeseriesClient.deleteTimeseries(id, aspect.getName(), from, to);
			log.info("Deleted time series data");
		}

		// deleting asset type file assignment API call
		assetTypeClient.deleteAssetTypeFileAssignment(assetTypeId, key2, ifmatch_AssetType.toString());
		log.info("successfully deleted Asset type file assignment");

		// deleting asset API call
		assetClient.deleteAsset(assetData.getEtag().toString(), assetData.getAssetId());
		log.info("successfully deleted Asset");

		assetTypeObj = assetTypeClient.getAssetType(assetTypeId, null, null);

		ifmatch_AssetType = assetTypeObj.getEtag();

		// deleting asset type API call
		assetTypeClient.deleteAssetType(ifmatch_AssetType.toString(), assetTypeObj.getId());
		log.info("successfully deleted Asset Type");

		// deleting the aspect types linked to the asset type API call
		for (AssetTypeResourceAspects aspect : aspectTypes) {

			aspectTypeClient.deleteAspectType(aspect.getAspectType().getEtag().toString(),
					aspect.getAspectType().getId());

		}
		log.info("successfully deleted Aspect Types");
	}

    public AspectTypeResource createAspect(String tenantName) throws MindsphereException {
        log.info("Calling createAspectType()");
        AspectTypeResource aspect = createAspectType(tenantName);
        return aspect;
    }

	private static File stream2file(InputStream in) throws IOException {
		final File tempFile = File.createTempFile("prefixstrinf", ".txt");
		tempFile.deleteOnExit();
		try (FileOutputStream out = new FileOutputStream(tempFile)) {
			StreamUtils.copy(in, out);
		}
		return tempFile;
	}

    public AssetTypeResource createAssetType(String tenantName, String aspectname, String aspectid) throws MindsphereException {
        log.info("Calling createAssetType()");
        AssettypeClient assetTypeClient = assetManagementHelper.getAssetTypeClient(getToken(), getHostName());
        log.info("assetTypeClient initialized successfully.");        
        AssetType aspectType = assetManagementHelper.createAssetType(tenantName, aspectname, aspectid);

        // creating Aspect type API call

        AssetTypeResource response = assetTypeClient.saveAssetType(assetManagementHelper.createAspectTypeRequestModel(assetManagementHelper.getAssetId(), aspectType));
        log.info("assetType created successfully " +response);  
        return response;
    }

    public AssetResourceWithHierarchyPath createAssets(String assetTypeId , String parentId) throws MindsphereException {
        log.info("Calling createAsset()");
        AssetsClient assetClient = assetManagementHelper.getAssetClient(getToken(), getHostName());
        log.info("assetClient initialized successfully.");  
        Asset asset = assetManagementHelper.createAsset(assetTypeId, parentId);
        AssetResourceWithHierarchyPath response = assetClient.addAsset(assetManagementHelper.createAssetRequestModel(asset));
        log.info("Asset created successfully " +response);
        return response;
        
    }

    public FileMetadataResource createFile() throws MindsphereException, IOException {
        log.info("Calling createFile()");
        FilesClient assetFileClient = assetManagementHelper.getFileClient(getToken(), getHostName());
        log.info("assetFileClient initialized successfully.");  
        String fileName = "integ_" + assetManagementHelper.getRandomNumber();
        String filePath = "/test.txt";
        InputStream in = this.getClass().getResourceAsStream(filePath);
        File currFile = stream2file(in);
        // uploading file
        FileMetadataResource response = assetFileClient.uploadFile(assetManagementHelper.uploadFileRequestModel(currFile, fileName, "private", "test file uploading"));
        log.info("File uploaded successfully with filename" +fileName);
        return response;
    }

    public String saveAssetLocation(String assetid, String asset_etag) throws MindsphereException {
        log.info("Calling saveAssetLocation()");
        LocationsClient assetLocationClient = assetManagementHelper.getAssetLocation(getToken(), getHostName());
        log.info("assetLocationClient initialized successfully.");  
        assetLocationClient.saveAssetLocation(assetManagementHelper.saveLocationRequestModel(assetid,asset_etag,assetManagementHelper.assetLocation()));
        log.info("Succesfully updated with location to assetid : " + assetid);
        return "Succesfully updated with location to assetid : " + assetid;
    }

    public AssetResourceWithHierarchyPath fileAssignmentToAsset(String assetid, String ifmatch, String key, String fileId) throws MindsphereException {
        log.info("Calling fileAssignmentToAsset()");
        AssetsClient assetClient = assetManagementHelper.getAssetClient(getToken(), getHostName());
        log.info("assetClient initialized successfully."); 
        KeyedFileAssignment assignment = new KeyedFileAssignment();
        AssetResourceWithHierarchyPath response = assetClient.saveAssetFileAssignment(assetManagementHelper.fileAssignmentToAssetRequestModel(assetid, fileId, assignment, ifmatch, key));
        log.info("successfully save fileassignement "+response); 
        return response;
    }

    public AssetResourceWithHierarchyPath getAssetById(String assetId) throws MindsphereException {
        log.info("Calling getAssetById()");
        AssetsClient assetClient = assetManagementHelper.getAssetClient(getToken(), getHostName());
        log.info("assetClient initialized successfully."); 
        AssetResourceWithHierarchyPath response = assetClient.getAsset(assetManagementHelper.getAssetRequestModel(assetId));
        log.info("getting asset successfully with id"+assetId); 
        return response;
    }

    public String deleteAsset(String assetid, String etag) throws MindsphereException {
        log.info("Calling getAssetById()");
        AssetsClient assetClient = assetManagementHelper.getAssetClient(getToken(), getHostName());
        log.info("assetClient initialized successfully."); 
        assetClient.deleteAsset(assetManagementHelper.deleteAssetRequestModel(assetid, etag));
        log.info("Successfully deleted the Asset : " + assetid);
        return "Successfully deleted the Asset : " + assetid;
    }

    public AssetListResource listAssets() throws MindsphereException {
        log.info("Calling listAssets()");
        AssetsClient assetClient = assetManagementHelper.getAssetClient(getToken(), getHostName());
        log.info("assetClient initialized successfully."); 
        AssetListResource response = assetClient.listAssets(assetManagementHelper.listAssetRequestModel(0,10));
        log.info("getting listAssets successfully "+response); 
        return response;
    }
}
