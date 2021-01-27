package com.siemens.mindsphere.services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

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
import com.siemens.mindsphere.sdk.assetmanagement.model.KeyedFileAssignment;
import com.siemens.mindsphere.sdk.assetmanagement.model.ListAssetsRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.SaveAspectTypeRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.SaveAssetFileAssignmentRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.SaveAssetLocationRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.SaveAssetTypeRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.UploadFileRequest;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.sdk.timeseries.apiclient.TimeSeriesClient;

@Service
public class AssetManagementService extends MindsphereService {

	private AssetManagementHelper assetManagementHelper = new AssetManagementHelper();
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public String createAssetPackage(String tenantName) throws MindsphereException, IOException {

		LOGGER.info("Calling createAspectType()");
		AspectTypeResource aspect = createAspectType(tenantName);
		LOGGER.info("Calling createAssetType()");
		AssetTypeResource resource = createAssetType(tenantName, aspect);
		LOGGER.info("Calling createAsset()");
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
		assetFileClient.uploadFile(currFile, fileName, null, "test desc");

		AssetsClient assetClient = assetManagementHelper.getAssetClient(getToken(), getHostName());
		Asset asset = assetManagementHelper.createAsset(tenantName, resource);
		AssetResourceWithHierarchyPath assetObj = null;

		// addAsset API call
		assetObj = assetClient.addAsset(asset);

		String id = assetObj.getAssetId();
		String key = "Demo1";
		Integer ifMatch = assetObj.getEtag();
		KeyedFileAssignment assignment = new KeyedFileAssignment();
		assignment.fileId("876dcbc2381c49dd8026f23f9c608309");
		assetClient.saveAssetFileAssignment(id, key, ifMatch.toString(), assignment);
		LOGGER.info("File created with name " + fileName + " and assigned to Asset with ID " + id);
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
		assetTypeFileClient.uploadFile(currFile, fileName, null, "test description");

		AssettypeClient assetTypeClient = assetManagementHelper.getAssetTypeClient(getToken(), getHostName());
		AssetType assetTypeDTO = assetManagementHelper.createAssetType(tenantName, aspect);

		// saveAssetType API call
		AssetTypeResource resource = assetTypeClient.saveAssetType(assetManagementHelper.getAssetId(), assetTypeDTO,
				null);

		String id = resource.getId();
		String key = "Demo2";
		Integer ifMatch = resource.getEtag();
		KeyedFileAssignment assignment = new KeyedFileAssignment();

		assignment.setFileId("876dcbc2381c49dd8026f23f9c608309");
		assetTypeClient.saveAssetTypeFileAssignment(ifMatch.toString(), id, key, assignment);

		LOGGER.info("File created with name " + fileName + " and assigned to Asset Type with ID " + id);

		return resource;
	}

    private AspectTypeResource createAspectType(String tenantName) throws MindsphereException {
        AspecttypeClient aspectTypeClient = assetManagementHelper.getAspectTypeClient(getToken(), getHostName());
        AspectType aspectType = assetManagementHelper.createAspectType(tenantName);

        // creating Aspect type API call
        AspectTypeResource response = aspectTypeClient.saveAspectType(assetManagementHelper.creatAspectTypeRequestModel(assetManagementHelper.getAspectId(), aspectType));
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
		LOGGER.info("Calling getAspectType()");
		List<String> aspectList = getAspectType();
		LOGGER.info("Calling getAssetType()");
		List<List> assetTypeList = getAssetType();
		LOGGER.info("Calling getAsset()");
		List<List> assetList = getAsset();
		String finalResponse = "Aspect Type Names :: " + aspectList + "\n" + " Asset Type Names :: "
				+ assetTypeList.get(0) + "\n" + " Asset Type file assignments ::" + assetTypeList.get(1)
				+ " Asset Names :: " + assetList.get(0) + "\n" + " Asset file assignments ::" + assetList.get(1);

		return finalResponse;
	}

	private List<List> getAsset() throws MindsphereException {
		AssetsClient assetClient = assetManagementHelper.getAssetClient(getToken(), getHostName());
		List<String> assetList = new ArrayList<String>();
		List<List> assetResourceCombo = new ArrayList<>();
		List<String> fileAssignments = new ArrayList<>();
		AssetListResource assets = assetClient.listAssets(0, 10, null, null, null);
		List<AssetResource> assetsList = assets.getEmbedded().getAssets();
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
		List<String> assetTypeList = new ArrayList<String>();
		List<List> assetTypeResourceCombo = new ArrayList<>();
		List<String> fileAssignments = new ArrayList<>();
		int page = 0, size = 10;
		AssetTypeListResource assetTypes = null;

		// list of assetTypes API call
		assetTypes = assetTypeClient.listAssetTypes(page, size, null, null, null, false);

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
		List<String> aspectTypeList = new ArrayList<String>();
		int page = 0;
		AspectTypeListResource aspects = null;

		// call to listofAspectTypes API
		aspects = aspectTypeClient.listAspectTypes(page, 10, null, null, null);

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
		TimeSeriesClient timeseriesClient = assetManagementHelper.getTimeseriesClient(getToken(), getHostName());

		Integer ifMatch;
		AssetResourceWithHierarchyPath assetData = assetClient.getAsset(id, null);
		ifMatch = assetData.getEtag();

		// deleting asset file assignment API call
		assetData = assetClient.deleteAssetFileAssigment(id, key1, ifMatch.toString());
		LOGGER.info("successfully deleted Asset File Assignment");

		assetTypeId = assetData.getTypeId();
		assetTypeObj = assetTypeClient.getAssetType(assetTypeId, null, null);

		Integer ifmatch_AssetType = assetTypeObj.getEtag();

		aspectTypes = assetTypeObj.getAspects();

		for (AssetTypeResourceAspects aspect : aspectTypes) {
			// deleteTimeseries API call
			timeseriesClient.deleteTimeseries(id, aspect.getName(), from, to);
			LOGGER.info("Deleted time series data");
		}

		// deleting asset type file assignment API call
		assetTypeClient.deleteAssetTypeFileAssignment(assetTypeId, key2, ifmatch_AssetType.toString());
		LOGGER.info("successfully deleted Asset type file assignment");

		// deleting asset API call
		assetClient.deleteAsset(assetData.getEtag().toString(), assetData.getAssetId());
		LOGGER.info("successfully deleted Asset");

		assetTypeObj = assetTypeClient.getAssetType(assetTypeId, null, null);

		ifmatch_AssetType = assetTypeObj.getEtag();

		// deleting asset type API call
		assetTypeClient.deleteAssetType(ifmatch_AssetType.toString(), assetTypeObj.getId());
		LOGGER.info("successfully deleted Asset Type");

		// deleting the aspect types linked to the asset type API call
		for (AssetTypeResourceAspects aspect : aspectTypes) {

			aspectTypeClient.deleteAspectType(aspect.getAspectType().getEtag().toString(),
					aspect.getAspectType().getId());

		}
		LOGGER.info("successfully deleted Aspect Types");
	}

    public AspectTypeResource createAspect(String tenantName) throws MindsphereException {
        LOGGER.info("Calling createAspectType()");
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
        LOGGER.info("Calling createAssetType()");
        AssettypeClient assetTypeClient = assetManagementHelper.getAssetTypeClient(getToken(), getHostName());
        AssetType aspectType = assetManagementHelper.createAssetType(tenantName, aspectname, aspectid);

        // creating Aspect type API call

        AssetTypeResource response = assetTypeClient.saveAssetType(assetManagementHelper.createAspectTypeRequestModel(assetManagementHelper.getAssetId(), aspectType));
        return response;
    }

    public AssetResourceWithHierarchyPath createAssets(String assetTypeId , String parentId) throws MindsphereException {
        LOGGER.info("Calling createAsset()");
        AssetsClient assetClient = assetManagementHelper.getAssetClient(getToken(), getHostName());
        Asset asset = assetManagementHelper.createAsset(assetTypeId, parentId);
        AssetResourceWithHierarchyPath response = assetClient.addAsset(assetManagementHelper.createAssetRequestModel(asset));
        return response;
        
    }

    public FileMetadataResource createFile() throws MindsphereException, IOException {
        LOGGER.info("Calling createFile()");
        FilesClient assetFileClient = assetManagementHelper.getFileClient(getToken(), getHostName());
        String fileName = "integ_" + assetManagementHelper.getRandomNumber();
        String filePath = "/test.txt";
        InputStream in = this.getClass().getResourceAsStream(filePath);
        File currFile = stream2file(in);
        // uploading file
        FileMetadataResource response = assetFileClient.uploadFile(assetManagementHelper.uploadFileRequestModel(currFile, fileName, "private", "test file uploading"));
        return response;
    }

    public String saveAssetLocation(String assetid, String asset_etag) throws MindsphereException {
        LOGGER.info("Calling saveAssetLocation()");
        LocationsClient assetLocationClient = assetManagementHelper.getAssetLocation(getToken(), getHostName());
        assetLocationClient.saveAssetLocation(assetManagementHelper.saveLocationRequestModel(assetid,asset_etag,assetManagementHelper.assetLocation()));
        return "Succesfully updated with location to assetid : " + assetid;
    }

    public AssetResourceWithHierarchyPath fileAssignmentToAsset(String assetid, String ifmatch, String key, String fileId) throws MindsphereException {
        LOGGER.info("Calling fileAssignmentToAsset()");
        FilesClient assetFileClient = assetManagementHelper.getFileClient(getToken(), getHostName());
        AssetsClient assetClient = assetManagementHelper.getAssetClient(getToken(), getHostName());
        KeyedFileAssignment assignment = new KeyedFileAssignment();
        AssetResourceWithHierarchyPath response = assetClient.saveAssetFileAssignment(assetManagementHelper.fileAssignmentToAssetRequestModel(assetid, fileId, assignment, ifmatch, key));
        return response;
    }

    public AssetResourceWithHierarchyPath getAssetById(String assetId) throws MindsphereException {
        LOGGER.info("Calling getAssetById()");
        AssetsClient assetClient = assetManagementHelper.getAssetClient(getToken(), getHostName());
        AssetResourceWithHierarchyPath response = assetClient.getAsset(assetManagementHelper.getAssetRequestModel(assetId));
        return response;
    }

    public String deleteAsset(String assetid, String etag) throws MindsphereException {
        LOGGER.info("Calling getAssetById()");
        AssetsClient assetClient = assetManagementHelper.getAssetClient(getToken(), getHostName());
        assetClient.deleteAsset(assetManagementHelper.deleteAssetRequestModel(assetid, etag));
        return "Successfully deleted the Asset : " + assetid;
    }

    public AssetListResource listAssets() throws MindsphereException {
        LOGGER.info("Calling listAssets()");
        AssetsClient assetClient = assetManagementHelper.getAssetClient(getToken(), getHostName());
        AssetListResource response = assetClient.listAssets(assetManagementHelper.listAssetRequestModel(1,10));
        return response;
    }
}
