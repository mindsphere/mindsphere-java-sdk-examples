package com.siemens.mindsphere.helpers;

import com.siemens.mindsphere.sdk.iotfileservices.apiclient.FileServiceClient;
import com.siemens.mindsphere.sdk.iotfileservices.model.DeleteFileRequest;
import com.siemens.mindsphere.sdk.iotfileservices.model.GetFileRequest;
import com.siemens.mindsphere.sdk.iotfileservices.model.PutFileRequest;
import com.siemens.mindsphere.sdk.iotfileservices.model.SearchFilesRequest;

public class FileServiceHelper extends ControllerHelper{
	

	public FileServiceClient getFileserviceClient(String token, String host) {
		FileServiceClient fileserviceClient = FileServiceClient.builder().restClientConfig(getConfig(host))
				.mindsphereCredentials(getCreds(token)).build();

		return fileserviceClient;
	}

    public PutFileRequest putFileObjectModel(byte[] bytesArray, String entityId, String filePath, String timeStamp,
            String description, String type) {
        PutFileRequest putFileRequest = new PutFileRequest();
        putFileRequest.setFile(bytesArray);
        putFileRequest.setEntityId(entityId);
        putFileRequest.setFilepath(filePath);
        putFileRequest.setTimestamp("2018-09-10T05:03:42.363Z");
        putFileRequest.setDescription("lorem test3");
        putFileRequest.setType("txt");
        return putFileRequest;
    }

    public GetFileRequest getFileObjectModel(String entityId, String filePath) {
        GetFileRequest getFileRequest = new GetFileRequest();
        getFileRequest.setEntityId(entityId);
        getFileRequest.setFilepath(filePath);
        return getFileRequest;
    }

    public DeleteFileRequest deleteFile(String entityId, String filepath) {
        DeleteFileRequest deleteFileRequest = new DeleteFileRequest();
        deleteFileRequest.setEntityId(entityId);
        deleteFileRequest.setFilepath(filepath);
        return deleteFileRequest;
    }

    public SearchFilesRequest searchFileObjectModel(String entityId) {
        SearchFilesRequest searchFilesRequest = new SearchFilesRequest();
        searchFilesRequest.setEntityId(entityId);
        return searchFilesRequest;
    }
	
}