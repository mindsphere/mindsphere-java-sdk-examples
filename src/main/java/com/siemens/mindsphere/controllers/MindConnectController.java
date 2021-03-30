package com.siemens.mindsphere.controllers;

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

	
	@Autowired
	MindConnectService mindConnectService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/diagnosticActivationsGet")
	public String diagnosticActivationsGetTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.diagnosticActivationsGet();
	}

	
	@RequestMapping(method = RequestMethod.GET, value = "/diagnosticActivationsPost")
	public String diagnosticActivationsPostTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.diagnosticActivationsPostTest();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/diagnosticActivationsIdDelete/{id}")
	public String diagnosticActivationsIdDeleteTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.diagnosticActivationsDeleteTest(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/diagnosticActivationsIdGet/{id}")
	public String diagnosticActivationsIdGetTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.diagnosticActivationsIdGetTest(id);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/diagnosticActivationsIdMessagesGet/{id}")
	public String diagnosticActivationsIdMessagesGetTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.diagnosticActivationsIdMessagesGetTest(id);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/diagnosticActivationsIdPut/{id}")
	public String diagnosticActivationsIdPutTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.diagnosticActivationsIdPutTest(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/dataPointMappingsGet")
	public String dataPointMappingsGetTest(@RequestHeader(required = false, value = "Authorization") String token,HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.dataPointMappingsGetTest();
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/dataPointMappingsIdGet/{id}")
	public String dataPointMappingsIdGetTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.dataPointMappingsIdGetTest(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/dataPointMappingsPost")
	public String dataPointMappingsPostTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.dataPointMappingsPostTest();
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/dataPointMappingsIdDelete/{id}")
	public String dataPointMappingsIdDeleteTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.dataPointMappingsIdDeleteTest(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/recoverableRecordsGet")
	public String recoverableRecordsGetTest(@RequestHeader(required = false, value = "Authorization") String token,
			HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.recoverableRecordsGetTest();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/recoverableRecordsIdReplayPost/{id}")
	public String recoverableRecordsIdReplayPostTet(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.recoverableRecordsIdReplayPost(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/recoverableRecordsIdDownloadLinkGet/{id}")
	public String recoverableRecordsIdDownloadLinkGetTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.recoverableRecordsIdDownloadLinkGetTest(id);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/recoverableRecordsIdDelete/{id}")
	public String recoverableRecordsIdDeleteTest(@RequestHeader(required = false, value = "Authorization") String token,
			@PathVariable(required = true, value = "id") String id,HttpServletRequest request) throws MindsphereException {

		MindConnectHelper.selectToken(mindConnectService, token, request.getRequestURL().toString());
		return mindConnectService.recoverableRecordsIdDelete(id);
	}
	
	

	
}
