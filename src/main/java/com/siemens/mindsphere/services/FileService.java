package com.siemens.mindsphere.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.siemens.mindsphere.helpers.FileServiceHelper;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.sdk.iotfileservices.apiclient.FileServiceClient;
import com.siemens.mindsphere.sdk.iotfileservices.model.FileResponse;
import com.siemens.mindsphere.sdk.iotfileservices.model.GetFileRequest;
import com.siemens.mindsphere.sdk.iotfileservices.model.PutFileRequest;
import com.siemens.mindsphere.sdk.iotfileservices.model.SearchFilesRequest;

@Service
public class FileService extends MindsphereService {

    private FileServiceHelper fileServiceHelper = new FileServiceHelper();
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private String dynamicDirectoryPath = "unitTest";

    public String createFile(String entityId) throws IOException, MindsphereException {
        FileServiceClient fileserviceClient = fileServiceHelper.getFileserviceClient(getToken(), getHostName());
        LOGGER.info("fileserviceClient initialized successfully");
        File currFile = new File(getClass().getClassLoader().getResource("testdata2.json").getFile());
        byte[] bytesArray = Files.readAllBytes(Paths.get(currFile.getPath()));
        String filePath = dynamicDirectoryPath + Math.random();

        // putFile aPI call
        fileserviceClient.putFile(fileServiceHelper.putFileObjectModel(bytesArray, entityId, filePath,
                "2018-09-10T05:03:42.363Z", "lorem test3", "txt"));
        LOGGER.info("successfully uploaded file + Filepath:" + filePath);

        byte[] readFile = null;
        // getFile API call
        readFile = fileserviceClient.getFile(fileServiceHelper.getFileObjectModel(entityId, filePath));
        String response = new String(readFile);
        LOGGER.info("get Response successfully for getFile" + response);
        return response;
    }

    public String deleteFile(String entityId, String filepath) throws MindsphereException {
        FileServiceClient fileserviceClient = fileServiceHelper.getFileserviceClient(getToken(), getHostName());
        String status;
        LOGGER.info("fileserviceClient initialized successfully");
        // deleteFile API call
        fileserviceClient.deleteFile(fileServiceHelper.deleteFile(entityId, filepath));
        fileserviceClient.deleteFile(entityId, filepath);
        status = "Deleted file successfully";
        LOGGER.info("status :"+status);
        return status;
    }

    public byte[] getFile(String entityId, String filePath) throws MindsphereException {
        FileServiceClient fileserviceClient = fileServiceHelper.getFileserviceClient(getToken(), getHostName());
        LOGGER.info("fileserviceClient initialized successfully");
        byte[] getFileResponse = fileserviceClient.getFile(fileServiceHelper.getFileObjectModel(entityId, filePath));
        String response = new String(getFileResponse);
        LOGGER.info("get Response successfully for getFile" + response);
        return getFileResponse;
    }

    public List<FileResponse> searchFile(String entityId) throws MindsphereException {
        FileServiceClient fileserviceClient = fileServiceHelper.getFileserviceClient(getToken(), getHostName());
        LOGGER.info("fileserviceClient initialized successfully");
        SearchFilesRequest searchFilesRequest = new SearchFilesRequest();
        searchFilesRequest.setEntityId(entityId);
        List<FileResponse> searchFileResponse = fileserviceClient
                .searchFiles(fileServiceHelper.searchFileObjectModel(entityId));
        LOGGER.info("get Response successfully for searchFile" + searchFileResponse);
        return searchFileResponse;
    }

    public String createMultiPart(String entityId, String filepath, Integer partNum, String upload) {
        FileServiceClient fileserviceClient = fileServiceHelper.getFileserviceClient(getToken(), getHostName());
        byte[] bytesArray = null;
        File currFile = null;
        String filePath = filepath;
        String status = null;
        try {
            if (partNum != null) {
                currFile = new File(getClass().getClassLoader().getResource("filepart" + partNum + ".txt").getFile());
                System.out.println("file size ::" + currFile.length());
                bytesArray = Files.readAllBytes(Paths.get(currFile.getPath()));
            }
            PutFileRequest request = new PutFileRequest();
            request.setDescription("Timeseries Data");
            request.setEntityId(entityId);
            request.setFile(bytesArray);
            request.setPart(partNum);
            request.setUpload(upload);
            request.setFilepath(filePath);
            request.setTimestamp(Instant.now().toString());
            request.setType("txt");
            
            if ("start".equals(upload)) {
                fileserviceClient.initiateMultiPartUpload(request);
                status = "Intitated file upload for the path : " + filepath;
                LOGGER.info("status :"+status);
            } else if (("complete").equals(upload)) {
                fileserviceClient.completeMultiPartUpload(request);
                status = "Successfully uploaded file for the path : " + filePath;
                LOGGER.info("status :"+status);
            } else {
                fileserviceClient.createMultiPartFile(request);
                status = "Uploaded file for the part : " + partNum + " in the path : " + filepath;
                LOGGER.info("status :"+status);
            }
        } catch (OutOfMemoryError ex) {
            status = ex.getMessage();
            LOGGER.info("status :"+status);
        } catch (MindsphereException ex) {
            status = ex.getErrorMessage();
            LOGGER.info("status :"+status);
        } catch (Exception e) {
            status = e.toString();
            LOGGER.error("successfully uploaded file + Filepath:" + filePath);
            LOGGER.error("Error class::" + e.getClass());
            LOGGER.error("Error message::" + e.getMessage());
            LOGGER.error("Error stack trace ::" + e.getStackTrace());
        }
        return status;
    }
}
