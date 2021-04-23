package com.siemens.mindsphere.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.siemens.mindsphere.helpers.MindConnectHelper;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.services.MindConnectService;

@RestController
@RequestMapping(value = "/mindconnect")
public class MindConnectController {
	
	/**
	 * MindConnect API provides following data ingestion functionalities:

			Data Point Mappings
			Exchange
			Diagnostic Activations
	 */
	
	/**
	 * For complete API specification of mindconnect service refer :
	 * https://developer.mindsphere.io/apis/connectivity-mindconnect/api-mindconnect-api.html
	 */


	
	@Autowired
	MindConnectService mindConnectService;
	
	/**
	 * @route /diagnosticActivationsGet
	 
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Diagnostic activations data in String format.
	 * @returnType String
	 * @description This method - diagnosticActivationsGetTest internally calls method diagnosticActivationsGet of DiagnosticActivationsClient class.
	 * 				This class is available as dependency in mindconnect-sdk-<version-here>.jar.
	 * 				The method diagnosticActivationsGet of DiagnosticActivationsClient class takes DiagnosticActivationsGetRequest as a parameter.
	
	 
	 * @apiEndpoint : GET /api/mindconnect/v3/diagnosticActivations of mindconnect service.
	 *              service.
	 * @apiNote Gets diagnostic activations.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/diagnosticActivationsGet")
	public String diagnosticActivationsGetTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.diagnosticActivationsGet();
	}

	
	/**
	 * @route /diagnosticActivationsPost
	 
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Created diagnostic activation data in String format.
	 * @returnType String
	 * @description This method - diagnosticActivationsGetTest internally calls method diagnosticActivationsPost of DiagnosticActivationsClient class.
	 * 				This class is available as dependency in mindconnect-sdk-<version-here>.jar.
	 * 				The method diagnosticActivationsPost of DiagnosticActivationsClient class takes DiagnosticActivationsPostRequest as a parameter.
	
	 
	 * @apiEndpoint : POST /api/mindconnect/v3/diagnosticActivations of mindconnect service.
	 *              service.
	 * @apiNote Creates a new diagnostic activation
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/diagnosticActivationsPost")
	public String diagnosticActivationsPostTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.diagnosticActivationsPostTest();
	}
	
	
	/**
	 * @route /diagnosticActivationsIdDelete/{id}
	 * @param id - Unique identifier of diagnostic activation resource. (required)
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Created diagnostic activation data in String format.
	 * @returnType String
	 * @description This method - diagnosticActivationsIdDeleteTest internally calls method diagnosticActivationsIdDelete of DiagnosticActivationsClient class.
	 * 				This class is available as dependency in mindconnect-sdk-<version-here>.jar.
	 * 				The method diagnosticActivationsIdDelete of DiagnosticActivationsClient class takes DiagnosticActivationsIdDeleteRequest as a parameter.
	
	 
	 * @apiEndpoint : DELETE /api/mindconnect/v3/diagnosticActivations/{id} of mindconnect service.
	 *              service.
	 * @apiNote Delete a diagnostic activation.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/diagnosticActivationsIdDelete/{id}")
	public String diagnosticActivationsIdDeleteTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.diagnosticActivationsDeleteTest(id);
	}
	
	
	/**
	 * @route /diagnosticActivationsIdGet/{id}
	 * @param id - Unique identifier of diagnostic activation resource. (required)
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Diagnostic activation data for given id in String format.
	 * @returnType String
	 * @description This method - diagnosticActivationsIdGetTest internally calls method diagnosticActivationsIdGet of DiagnosticActivationsClient class.
	 * 				This class is available as dependency in mindconnect-sdk-<version-here>.jar.
	 * 				The method diagnosticActivationsIdGet of DiagnosticActivationsClient class takes DiagnosticActivationsIdGetRequest as a parameter.
	
	 
	 * @apiEndpoint : GET /api/mindconnect/v3/diagnosticActivations/{id} of mindconnect service.
	 *              service.
	 * @apiNote Gets a diagnostic activation.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/diagnosticActivationsIdGet/{id}")
	public String diagnosticActivationsIdGetTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.diagnosticActivationsIdGetTest(id);
	}
	
	
	
	/**
	 * @route /diagnosticActivationsIdMessagesGet/{id}
	 * @param id - Unique identifier of diagnostic activation resource. (required)
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Paged diagnostic information messages in String format.
	 * @returnType String
	 * @description This method - diagnosticActivationsIdGetTest internally calls method diagnosticActivationsIdMessagesGet of DiagnosticActivationsClient class.
	 * 				This class is available as dependency in mindconnect-sdk-<version-here>.jar.
	 * 				The method diagnosticActivationsIdMessagesGet of DiagnosticActivationsClient class takes DiagnosticActivationsIdMessagesGetRequest as a parameter.
	
	 
	 * @apiEndpoint : GET /api/mindconnect/v3/diagnosticActivations/{id}/messages of mindconnect service.
	 *              service.
	 * @apiNote Get a diagnostic messages of specific activation resource.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/diagnosticActivationsIdMessagesGet/{id}")
	public String diagnosticActivationsIdMessagesGetTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.diagnosticActivationsIdMessagesGetTest(id);
	}
	
	
	/**
	 * @route /diagnosticActivationsIdPut/{id}
	 * @param id - Unique identifier of diagnostic activation resource. (required)
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Updated diagnostic  activation information in String format on successful execution.
	 * @returnType String
	 * @description This method - diagnosticActivationsIdPutTest internally calls method diagnosticActivationsIdPut of DiagnosticActivationsClient class.
	 * 				This class is available as dependency in mindconnect-sdk-<version-here>.jar.
	 * 				The method diagnosticActivationsIdPut of DiagnosticActivationsClient class takes DiagnosticActivationsIdPutRequest as a parameter.
	
	 
	 * @apiEndpoint : PUT /api/mindconnect/v3/diagnosticActivations/{id} of mindconnect service.
	 *              service.
	 * @apiNote Update status of Diagnostic Activation.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/diagnosticActivationsIdPut/{id}")
	public String diagnosticActivationsIdPutTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.diagnosticActivationsIdPutTest(id);
	}
	
	
	
	/**
	 * @route /dataPointMappingsGet
	 
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Data point mapping data in String format.
	 * @returnType String
	 * @description This method - dataPointMappingsGetTest internally calls method dataPointMappingsGet of MappingsClient class.
	 * 				This class is available as dependency in mindconnect-sdk-<version-here>.jar.
	 * 				The method dataPointMappingsGet of MappingsClient class takes DataPointMappingsGetRequest as a parameter.
	
	 
	 * @apiEndpoint : GET /api/mindconnect/v3/dataPointMappings of mindconnect service.
	 *              service.
	 * @apiNote Get mappings.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/dataPointMappingsGet")
	public String dataPointMappingsGetTest(@RequestHeader(required = false, value = "Authorization") String token,HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.dataPointMappingsGetTest();
	}
	
	
	/**
	 * @route /dataPointMappingsIdGet/{id}
	 * @param id - Unique identifier of the mapping resource.
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Data point mapping data for Id in String format.
	 * @returnType String
	 * @description This method - dataPointMappingsIdGetTest internally calls method dataPointMappingsIdGet of MappingsClient class.
	 * 				This class is available as dependency in mindconnect-sdk-<version-here>.jar.
	 * 				The method dataPointMappingsIdGet of MappingsClient class takes DataPointMappingsIdGetRequest as a parameter.
	
	 
	 * @apiEndpoint : GET /api/mindconnect/v3/dataPointMappings/{id} of mindconnect service.
	 *              service.
	 * @apiNote Get a mapping by id.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/dataPointMappingsIdGet/{id}")
	public String dataPointMappingsIdGetTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.dataPointMappingsIdGetTest(id);
	}
	
	/**
	 * @route /dataPointMappingsPost
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Created data point in mapping in String format.
	 * @returnType String
	 * @description This method - dataPointMappingsPostTest internally calls method dataPointMappingsPost of MappingsClient class.
	 * 				This class is available as dependency in mindconnect-sdk-<version-here>.jar.
	 * 				The method dataPointMappingsPost of MappingsClient class takes DataPointMappingsPostRequest as a parameter.
	
	 
	 * @apiEndpoint : POST /api/mindconnect/v3/dataPointMappings of mindconnect service.
	 *              service.
	 * @apiNote Create single mapping.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/dataPointMappingsPost")
	public String dataPointMappingsPostTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.dataPointMappingsPostTest();
	}
	
	/**
	 * @route /dataPointMappingsIdDelete/{id}
	 * @param id - Unique identifier of the mapping resource.
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return "deleted" on successful execution.
	 * @returnType String
	 * @description This method - dataPointMappingsIdDeleteTest internally calls method dataPointMappingsIdDelete of MappingsClient class.
	 * 				This class is available as dependency in mindconnect-sdk-<version-here>.jar.
	 * 				The method dataPointMappingsIdDelete of MappingsClient class takes DataPointMappingsIdDeleteRequest as a parameter.
	
	 
	 * @apiEndpoint : DELETE /api/mindconnect/v3/dataPointMappings/{id} of mindconnect service.
	 *              service.
	 * @apiNote Delete a mapping.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/dataPointMappingsIdDelete/{id}")
	public String dataPointMappingsIdDeleteTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.dataPointMappingsIdDeleteTest(id);
	}
	
	
	/**
	 * @route /recoverableRecordsGet
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Recoverable record data in String format on successful execution.
	 * @returnType String
	 * @description This method - recoverableRecordsGetTest internally calls method recoverableRecordsGet of RecordRecoveryClient class.
	 * 				This class is available as dependency in mindconnect-sdk-<version-here>.jar.
	 * 				The method recoverableRecordsGet of RecordRecoveryClient class takes RecoverableRecordsGetRequest as a parameter.
	
	 
	 * @apiEndpoint : GET /api/mindconnect/v3/recoverableRecords of mindconnect service.
	 *              service.
	 * @apiNote Get all recoverable records.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/recoverableRecordsGet")
	public String recoverableRecordsGetTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.recoverableRecordsGetTest();
	}
	
	
	/**
	 * @route /recoverableRecordsIdReplayPost/{id}
	 * @param id - Unique identifier of the recoverable record.
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return "created" on successful execution.
	 * @returnType String
	 * @description This method - recoverableRecordsIdReplayPostTet internally calls method recoverableRecordsIdReplayPost of RecordRecoveryClient class.
	 * 				This class is available as dependency in mindconnect-sdk-<version-here>.jar.
	 * 				The method recoverableRecordsIdReplayPost of RecordRecoveryClient class takes RecoverableRecordsIdReplayPostRequest as a parameter.
	
	 
	 * @apiEndpoint : POST /api/mindconnect/v3/recoverableRecords/{id}/replay of mindconnect service.
	 *              service.
	 * @apiNote Re-play a recoverable record.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/recoverableRecordsIdReplayPost/{id}")
	public String recoverableRecordsIdReplayPostTet(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.recoverableRecordsIdReplayPost(id);
	}
	
	/**
	 * @route /recoverableRecordsIdDownloadLinkGet/{id}
	 * @param id - Unique identifier of the recoverable record.
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return Response of download link in String format.
	 * @returnType String
	 * @description This method - recoverableRecordsIdDownloadLinkGetTest internally calls method recoverableRecordsIdDownloadLinkGet of RecordRecoveryClient class.
	 * 				This class is available as dependency in mindconnect-sdk-<version-here>.jar.
	 * 				The method recoverableRecordsIdDownloadLinkGet of RecordRecoveryClient class takes RecoverableRecordsIdDownloadLinkGetRequest as a parameter.
	
	 
	 * @apiEndpoint : GET /api/mindconnect/v3/recoverableRecords/{id}/downloadLink of mindconnect service.
	 *              service.
	 * @apiNote Get download link of record payload.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/recoverableRecordsIdDownloadLinkGet/{id}")
	public String recoverableRecordsIdDownloadLinkGetTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.recoverableRecordsIdDownloadLinkGetTest(id);
	}
	
	/**
	 * @route /recoverableRecordsIdDelete/{id}
	 * @param id - Unique identifier of the recoverable record.
	 * @param token - User token (optional)
	 * @param request - HttpServletRequest object
	 * @return "deleted" upon successful execution.
	 * @returnType String
	 * @description This method - recoverableRecordsIdDeleteTest internally calls method recoverableRecordsIdDelete of RecordRecoveryClient class.
	 * 				This class is available as dependency in mindconnect-sdk-<version-here>.jar.
	 * 				The method recoverableRecordsIdDelete of RecordRecoveryClient class takes RecoverableRecordsIdDeleteRequest as a parameter.
	
	 
	 * @apiEndpoint : DELETE /api/mindconnect/v3/recoverableRecords/{id} of mindconnect service.
	 *              service.
	 * @apiNote Delete a recoverable record.
	 * @throws MindsphereException if an error occurs while attempting to invoke the sdk call.
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/recoverableRecordsIdDelete/{id}")
	public String recoverableRecordsIdDeleteTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.recoverableRecordsIdDelete(id);
	}
	
	

	
}
