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

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/files")
@Slf4j
public class FileServiceController {

	/**
	 * The IoT File API enables storing and retrieving files for asset (entity)
	 * instances.
	 */

	/**
	 * For complete API specification of IoT File service refer :
	 * https://developer.mindsphere.io/apis/iot-iotfile/api-iotfile-api.html
	 */

	@Autowired
	private FileService fileService;

	/**
	 * @route /fileservicecreate/{entityId}
	 * @param entityId - An Asset Id for which file needs to be stored.
	 * @note Non existent/Incorrect entityId will result in MindsphereException.
	 * @param token   - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Content of file as a String if upload/store of file is successful.
	 * @returnType String
	 * @description This method - putFile internally calls method putFile of
	 *              FileServiceClient class. This class is available as dependency
	 *              in iotfileservices-sdk-<version-here>.jar.
	 *              putFile method of FileServiceClient class takes PutFileRequest
	 *              as a parameter. Fields of PutFileRequest object are set in
	 *              FileServiceHelper class's putFileObjectModel method. The required
	 *              fields of PutFileRequest are : 
	 *              1)file string($binary) the file attached content 
	 *              2)entityId - unique identifier of the asset (entity) 
	 *              3)filepath - url path of the file along with filename
	 * @apiEndpoint : PUT /api/iotfile/v3/files/{entityId}/{filepath} of iot file
	 *              service. service.
	 * @apiNote Create or update a file for the specified asset (entity) and path,
	 *          with the provided content.
	 * @throws MindsphereException if an error occurs while attempting to invoke the
	 *                             sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/fileservicecreate/{entityId}")
	public String putFile(@PathVariable("entityId") String entityId,
			@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws IOException, MindsphereException {
		log.info("/files/fileservicecreate/"+entityId+" invoked.");
		FileServiceHelper.selectToken(fileService, token, request.getRequestURL().toString());
		return fileService.createFile(entityId);
	}

	/**
	 * @route /deleteFile/{entityId}/{filepath}
	 * @param entityId - An Asset Id for which file needs to be deleted.
	 * @note Non existent/Incorrect entityId will result in MindsphereException.
	 * @param filepath - path of the file along with filename.
	 * @param token    - User token (optional)
	 * @param request  - HttpServletRequest object
	 * @return "Deleted file successfully" upon successful execution.
	 * @returnType String
	 * @description This method - deleteFile internally calls method deleteFile of
	 *              FileServiceClient class. This class is available as dependency
	 *              in iotfileservices-sdk-<version-here>.jar.
	 * @apiEndpoint : DELETE /api/iotfile/v3/files/{entityId}/{filepath} of iot file
	 *              service. service.
	 * @apiNote Delete a file for the specified asset (entity) and path.
	 * @throws MindsphereException if an error occurs while attempting to invoke the
	 *                             sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/deleteFile/{entityId}/{filepath}")
	public String deleteFile(@PathVariable("entityId") String entityId, @PathVariable("filepath") String filepath,
			@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws MindsphereException {
		log.info("/files/deleteFile/"+entityId+"/"+filepath+" invoked.");
		FileServiceHelper.selectToken(fileService, token, request.getRequestURL().toString());
		return fileService.deleteFile(entityId, filepath);
	}

	/**
	 * @route /fileservicegetfile/{entityId}/{file_path}
	 * @param entityId - An Asset Id for which file needs to be retrieved.
	 * @note Non existent/Incorrect entityId will result in MindsphereException.
	 * @param file_path - path of the file along with filename.
	 * @param token     - User token (optional)
	 * @param request   - HttpServletRequest object
	 * @return Content of file as a byte array.
	 * @returnType byte array
	 * @description This method - getFile internally calls method getFile of
	 *              FileServiceClient class. This class is available as dependency
	 *              in iotfileservices-sdk-<version-here>.jar.
	 * 
	 * @apiEndpoint : GET /api/iotfile/v3/files/{entityId}/{filepath} of iot file
	 *              service. service.
	 * @apiNote Read a file for the specified asset (entity) and path.
	 * @throws MindsphereException if an error occurs while attempting to invoke the
	 *                             sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/fileservicegetfile/{entityId}/{file_path}")
	public byte[] getFile(@PathVariable("entityId") String entityId, @PathVariable("file_path") String filePath,
			@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws IOException, MindsphereException {
		log.info("/files/fileservicegetfile/"+entityId+"/"+filePath+" invoked.");
		FileServiceHelper.selectToken(fileService, token, request.getRequestURL().toString());
		return fileService.getFile(entityId, filePath);
	}

	/**
	 * @route /fileservicesearch/{entityId}
	 * @param entityId - An Asset Id for which file needs to be searched.
	 * @note Non existent/Incorrect entityId will result in MindsphereException.
	 * 
	 * @param token   - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return List of Files.
	 * @returnType List<FileResponse>
	 * @description This method - searchFile internally calls method searchFiles of
	 *              FileServiceClient class. This class is available as dependency
	 *              in iotfileservices-sdk-<version-here>.jar.
	 * @apiEndpoint : GET /api/iotfile/v3/files/{entityId} of iot file service.
	 *              service.
	 * @apiNote Search files for the specified asset (entity).
	 * @throws MindsphereException if an error occurs while attempting to invoke the
	 *                             sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/fileservicesearch/{entityId}")
	public List<FileResponse> searchFile(@PathVariable("entityId") String entityId,
			@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws IOException, MindsphereException {
		log.info("/files/fileservicesearch/"+entityId+" invoked.");
		FileServiceHelper.selectToken(fileService, token, request.getRequestURL().toString());
		return fileService.searchFile(entityId);
	}

	/**
	 * @route /fileservicecreatemultipartfile/{entityId}/{file_path}
	 * @param entityId - An Asset Id for which file needs to be stored.
	 * @note Non existent/Incorrect entityId will result in MindsphereException.
	 * @param file_path - url path of the file along with filename
	 * @param partNum - Part number to upload
	 * @param upload - upload status to start, complete, and abort multi-part uploads
	 * 				   Available values : start, complete, abort
	 * @param token    - User token (optional)
	 * @param request  - HttpServletRequest object
	 * @return String indicating status of upload
	 * @returnType String
	 * @description This method - createMultiPartFile internally calls method
	 *              putFile of FileServiceClient class. This class is available as
	 *              dependency in iotfileservices-sdk-<version-here>.jar.putFile method of FileServiceClient class takes
	 *              PutFileRequest as a parameter. Fields of PutFileRequest object
	 *              are set in FileSegiceHelper class's putFileObjectModel method.
	 *              The required fields of PutFileRequest are : 
	 *              1)file string($binary) the file attached content 
	 *              2)entityId - unique identifier of the asset (entity) 
	 *              3)filepath - url path of the file along with filename 
	 *              In order achieve multipart upload, this endpoint can be hit in following order : 
	 *              1) upload = "start" ---> initiate multipart upload, partNum --> optional 
	 *              2) partNum to appropriate value, upload --> No value needs to be provided for upload field.
	 *              3) upload = "complete" ---> complete multipart upload, partNum --> optional
	 * 
	 * 
	 * @apiEndpoint : PUT /api/iotfile/v3/files/{entityId}/{filepath} of iot file
	 *              service. service.
	 * @apiNote Create or update a file for the specified asset (entity) and path,
	 *          with the provided content.
	 * @throws MindsphereException if an error occurs while attempting to invoke the
	 *                             sdk call.
	 * @throws IOException
	 */

	@RequestMapping(method = RequestMethod.GET, value = "/fileservicecreatemultipartfile/{entityId}/{file_path}")
	public String createMultiPartFile(@PathVariable("entityId") String entityId,
			@PathVariable("file_path") String filePath,
			@RequestParam(required = false, value = "partNum") Integer partNum,
			@RequestParam(required = false, value = "upload") String upload,
			@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request)
			throws IOException, MindsphereException {
		log.info("/files/fileservicecreatemultipartfile/"+entityId+"/"+filePath+" invoked.");
		FileServiceHelper.selectToken(fileService, token, request.getRequestURL().toString());
		return fileService.createMultiPart(entityId, filePath, partNum, upload);
	}
}
