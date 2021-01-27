package com.siemens.mindsphere.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siemens.mindsphere.helpers.FileServiceHelper;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.sdk.iotfileservices.model.FileResponse;
import com.siemens.mindsphere.services.FileService;

@RestController
@RequestMapping(value = "/files")
public class FileServiceController {

    @Autowired
    private FileService fileService;

    @RequestMapping(method = RequestMethod.GET, value = "/fileservicecreate/{entityId}")
    public String putFile(@PathVariable("entityId") String entityId,
            @RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
            throws IOException, MindsphereException {

        FileServiceHelper.selectToken(fileService, token, request.getRequestURL().toString());
        return fileService.createFile(entityId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteFile/{entityId}/{filepath}")
    public String deleteFile(@PathVariable("entityId") String entityId, @PathVariable("filepath") String filepath,
            @RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
            throws MindsphereException {

        FileServiceHelper.selectToken(fileService, token, request.getRequestURL().toString());
        return fileService.deleteFile(entityId, filepath);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fileservicegetfile/{entityId}/{file_path}")
    public byte[] putFile(@PathVariable("entityId") String entityId, @PathVariable("file_path") String filePath,
            @RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
            throws IOException, MindsphereException {

        FileServiceHelper.selectToken(fileService, token, request.getRequestURL().toString());
        return fileService.getFile(entityId, filePath);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fileservicesearch/{entityId}")
    public List<FileResponse> searchFile(@PathVariable("entityId") String entityId,
            @RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
            throws IOException, MindsphereException {

        FileServiceHelper.selectToken(fileService, token, request.getRequestURL().toString());
        return fileService.searchFile(entityId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fileservicecreatemultipartfile/{entityId}/{file_path}")
    public String createMultiPartFile(@PathVariable("entityId") String entityId,
            @PathVariable("file_path") String filePath,
            @RequestParam(required = false, value = "partNum") Integer partNum,
            @RequestParam(required = false, value = "upload") String upload,
            @RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
            throws IOException, MindsphereException {

        FileServiceHelper.selectToken(fileService, token, request.getRequestURL().toString());
        return fileService.createMultiPart(entityId, filePath, partNum, upload);
    }
}
