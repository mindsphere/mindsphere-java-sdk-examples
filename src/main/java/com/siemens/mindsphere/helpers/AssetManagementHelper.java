package com.siemens.mindsphere.helpers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StreamUtils;

import com.siemens.mindsphere.sdk.assetmanagement.apiclient.AspecttypeClient;
import com.siemens.mindsphere.sdk.assetmanagement.apiclient.AssetsClient;
import com.siemens.mindsphere.sdk.assetmanagement.apiclient.AssettypeClient;
import com.siemens.mindsphere.sdk.assetmanagement.apiclient.FilesClient;
import com.siemens.mindsphere.sdk.assetmanagement.apiclient.LocationsClient;
import com.siemens.mindsphere.sdk.assetmanagement.model.AddAssetRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.AspectType;
import com.siemens.mindsphere.sdk.assetmanagement.model.AspectTypeResource;
import com.siemens.mindsphere.sdk.assetmanagement.model.AspectVariable;
import com.siemens.mindsphere.sdk.assetmanagement.model.AspectVariable.DataTypeEnum;
import com.siemens.mindsphere.sdk.assetmanagement.model.Asset;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetType;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetType.ScopeEnum;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetTypeAspects;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetTypeResource;
import com.siemens.mindsphere.sdk.assetmanagement.model.DeleteAssetRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.FileAssignment;
import com.siemens.mindsphere.sdk.assetmanagement.model.GetAssetRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.KeyedFileAssignment;
import com.siemens.mindsphere.sdk.assetmanagement.model.ListAssetsRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.Location;
import com.siemens.mindsphere.sdk.assetmanagement.model.SaveAspectTypeRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.SaveAssetFileAssignmentRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.SaveAssetLocationRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.SaveAssetTypeRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.UploadFileRequest;
import com.siemens.mindsphere.sdk.assetmanagement.model.VariableDefinition;
import com.siemens.mindsphere.sdk.timeseries.apiclient.TimeSeriesClient;

public class AssetManagementHelper extends ControllerHelper {
	String assetId;
	String aspectId;

	public AspecttypeClient getAspectTypeClient(String token, String host) {

		AspecttypeClient aspectTypeClient = AspecttypeClient.builder().restClientConfig(getConfig(host))
				.mindsphereCredentials(getCreds(token)).build();
		return aspectTypeClient;

	}

	public AssettypeClient getAssetTypeClient(String token, String host) {

		AssettypeClient assetTypeClient = AssettypeClient.builder().restClientConfig(getConfig(host))
				.mindsphereCredentials(getCreds(token)).build();
		return assetTypeClient;
	}

	public AssetsClient getAssetClient(String token, String host) {

		AssetsClient assetClient = AssetsClient.builder().restClientConfig(getConfig(host))
				.mindsphereCredentials(getCreds(token)).build();
		return assetClient;
	}

	public FilesClient getFileClient(String token, String host) {
		FilesClient fileClient = FilesClient.builder().restClientConfig(getConfig(host))
				.mindsphereCredentials(getCreds(token)).build();
		return fileClient;
	}

	public LocationsClient getAssetLocation(String token, String host) {
        LocationsClient locationClient = LocationsClient.builder().restClientConfig(getConfig(host)).mindsphereCredentials(getCreds(token)).build();
        return locationClient;
    }


	public TimeSeriesClient getTimeseriesClient(String token, String host) {
		TimeSeriesClient timeseriesClient = TimeSeriesClient.builder().restClientConfig(getConfig(host))
				.mindsphereCredentials(getCreds(token)).build();

		return timeseriesClient;
	}

	public AspectType createAspectType(String tenantName) {
		int aspectResult = getRandomNumber();
		setAspectId(tenantName, aspectResult);
		aspectId = getAspectId();

		String aspectName = "voltage_" + aspectResult;
		AspectType aspectType = new AspectType();
		aspectName = aspectName + aspectResult;

		// Setting up aspectType
		aspectType.setCategory(com.siemens.mindsphere.sdk.assetmanagement.model.AspectType.CategoryEnum.DYNAMIC);
		aspectType.setDescription("");
		aspectType.setName(aspectName);
		aspectType.setScope(com.siemens.mindsphere.sdk.assetmanagement.model.AspectType.ScopeEnum.PRIVATE);
		List<AspectVariable> aspectVariableList = new ArrayList<AspectVariable>();
		AspectVariable aspectVariable = new AspectVariable();
		aspectVariable.setDataType(DataTypeEnum.DOUBLE);
		aspectVariable.setSearchable(Boolean.FALSE);
		aspectVariable.setName("Current12");
		aspectVariable.setUnit("A");
		aspectVariable.setLength(null);
		aspectVariable.setQualityCode(Boolean.FALSE);
		aspectVariableList.add(aspectVariable);
		aspectType.setVariables(aspectVariableList);
		return aspectType;
	}

	public AssetType createAssetType(String tenantName, AspectTypeResource aspect) {
		int assetResult = getRandomNumber();
		setAssetId(tenantName, assetResult);
		assetId = getAssetId();
		String assetName = "Wheel" + assetResult;
		assetName = assetName + assetResult;

		// setting up assetType object
		AssetType assetTypeDTO = new AssetType();
		assetTypeDTO.setParentTypeId("core.basicasset");
		assetTypeDTO.setName(assetName);
		assetTypeDTO.setDescription("Basic agent type for the Asset Management Service.");
		assetTypeDTO.setScope(ScopeEnum.PRIVATE);

		AssetTypeAspects aspect1 = new AssetTypeAspects();
		aspect1.setName(aspect.getName());
		aspect1.setAspectTypeId(aspect.getId());
		assetTypeDTO.addAspectsItem(aspect1);
		List<VariableDefinition> defList = new ArrayList<VariableDefinition>();
		assetTypeDTO.setVariables(defList);
		List<FileAssignment> fileAssignments = new ArrayList<>();
		assetTypeDTO.setFileAssignments(fileAssignments);
		return assetTypeDTO;
	}

	public AssetType createAssetType(String tenantName, String aspectname, String aspectid) {
        int assetResult = getRandomNumber();
        setAssetId(tenantName, assetResult);
        assetId = getAssetId();
        String assetName = "Wheel" + assetResult;
        assetName = assetName + assetResult;

        // setting up assetType object
        AssetType assetTypeDTO = new AssetType();
        assetTypeDTO.setParentTypeId("core.basicasset");
        assetTypeDTO.setName(assetName);
        assetTypeDTO.setDescription("Basic agent type for the Asset Management Service.");
        assetTypeDTO.setScope(ScopeEnum.PRIVATE);

        AssetTypeAspects aspect1 = new AssetTypeAspects();
        aspect1.setName(aspectname);
        aspect1.setAspectTypeId(aspectid);
        assetTypeDTO.addAspectsItem(aspect1);
        List<VariableDefinition> defList = new ArrayList<VariableDefinition>();
        assetTypeDTO.setVariables(defList);
        List<FileAssignment> fileAssignments = new ArrayList<>();
        assetTypeDTO.setFileAssignments(fileAssignments);
        return assetTypeDTO;
    }

	public Asset createAsset(String tenantName, AssetTypeResource resource) {

		Asset assetDTO = new Asset();
		assetDTO.setName("BMW");
		assetDTO.setParentId("0ba09fbe8bad486893125b1156aa2eaa");
		assetDTO.setTypeId(resource.getId());
		return assetDTO;

	}

	public Asset createAsset(String assetTypeId, String parentId) {

        Asset assetDTO = new Asset();
        assetDTO.setName("BMW");
        assetDTO.setParentId(parentId);
        assetDTO.setTypeId(assetTypeId);
        return assetDTO;

    }

    public Location assetLocation() {
        Location location = new Location();
        location.setCountry("Austria");
        location.setRegion("dd 2");
        location.setLongitude(new BigDecimal("53.5125546"));
        location.setLocality("Innsbruck");
        location.setLatitude(new BigDecimal("9.9763411"));
        location.setPostalCode(null);
        return location;
    }

	private void setAssetId(String tenantName, int assetResult) {
		this.assetId = tenantName + ".tyre" + assetResult;
	}

	public String getAssetId() {
		return assetId;
	}

	private void setAspectId(String tenantName, int aspectResult) {
		this.aspectId = tenantName + ".voltage_" + aspectResult;
	}

	public String getAspectId() {
		return aspectId;
	}

	private static File stream2file(InputStream in) throws IOException {
        final File tempFile = File.createTempFile("prefixstrinf", ".txt");
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            StreamUtils.copy(in, out);
        }
        return tempFile;
    }

    public SaveAspectTypeRequest creatAspectTypeRequestModel(String aspectId2, AspectType aspectType) {
        SaveAspectTypeRequest saveAspectTypeRequest = new SaveAspectTypeRequest();
        saveAspectTypeRequest.setId(getAspectId());
        saveAspectTypeRequest.setAspecttype(aspectType);
        saveAspectTypeRequest.setIfMatch(null);
        return saveAspectTypeRequest;
    }

    public SaveAssetTypeRequest createAspectTypeRequestModel(String assetId2, AssetType aspectType) {
        SaveAssetTypeRequest saveAssetTypeRequest = new SaveAssetTypeRequest();
        saveAssetTypeRequest.setId(getAssetId());
        saveAssetTypeRequest.setAssettype(aspectType);
        saveAssetTypeRequest.setIfMatch(null);
        return saveAssetTypeRequest;
    }

    public AddAssetRequest createAssetRequestModel(Asset asset) {
        AddAssetRequest addAssetRequest = new AddAssetRequest();
        addAssetRequest.setAsset(asset);
        return addAssetRequest;
    }

    public UploadFileRequest uploadFileRequestModel(File currFile, String fileName, String scope, String description) {
        UploadFileRequest uploadFileRequest = new UploadFileRequest();
        uploadFileRequest.setFile(currFile);
        uploadFileRequest.setName(fileName);
        uploadFileRequest.setScope("private");
        uploadFileRequest.setDescription("test file uploading");
        return uploadFileRequest;
    }

    public SaveAssetLocationRequest saveLocationRequestModel(String assetid2, String asset_etag,
            Location assetLocation) {
        SaveAssetLocationRequest saveAssetLocationRequest = new SaveAssetLocationRequest();
        saveAssetLocationRequest.setId(assetid2);
        saveAssetLocationRequest.setIfMatch(asset_etag);
        saveAssetLocationRequest.setLocation(assetLocation());
        return saveAssetLocationRequest;
    }

    public SaveAssetFileAssignmentRequest fileAssignmentToAssetRequestModel(String assetid, String fileId,
            KeyedFileAssignment assignment, String ifmatch, String key) {
        SaveAssetFileAssignmentRequest saveAssetFileAssignmentRequest = new SaveAssetFileAssignmentRequest();
        saveAssetFileAssignmentRequest.setId(assetid);
        assignment.setFileId(fileId);
        saveAssetFileAssignmentRequest.setAssignment(assignment);
        saveAssetFileAssignmentRequest.setIfMatch(ifmatch);
        saveAssetFileAssignmentRequest.setKey(key);
        return saveAssetFileAssignmentRequest;
    }

    public GetAssetRequest getAssetRequestModel(String assetId2) {
        GetAssetRequest getAssetRequest = new GetAssetRequest();
        getAssetRequest.setId(assetId2);
        return getAssetRequest;
    }

    public DeleteAssetRequest deleteAssetRequestModel(String assetid2, String etag) {
        DeleteAssetRequest deleteAssetRequest = new DeleteAssetRequest();
        deleteAssetRequest.setId(assetid2);
        deleteAssetRequest.setIfMatch(etag);
        return deleteAssetRequest;
    }

    public ListAssetsRequest listAssetRequestModel(int page, int size) {
        ListAssetsRequest listAssetsRequest = new ListAssetsRequest();
        listAssetsRequest.setPage(1);
        listAssetsRequest.setSize(10);
        return listAssetsRequest;
    }
}
