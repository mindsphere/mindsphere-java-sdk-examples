package com.siemens.mindsphere.services;

import org.springframework.stereotype.Service;

import com.siemens.mindsphere.helpers.MindConnectHelper;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.sdk.mindconnect.apiclient.DiagnosticActivationsClient;
import com.siemens.mindsphere.sdk.mindconnect.apiclient.MappingsClient;
import com.siemens.mindsphere.sdk.mindconnect.apiclient.RecordRecoveryClient;
import com.siemens.mindsphere.sdk.mindconnect.model.DataPointMappingsGetRequest;
import com.siemens.mindsphere.sdk.mindconnect.model.DataPointMappingsIdDeleteRequest;
import com.siemens.mindsphere.sdk.mindconnect.model.DataPointMappingsIdGetRequest;
import com.siemens.mindsphere.sdk.mindconnect.model.DataPointMappingsPostRequest;
import com.siemens.mindsphere.sdk.mindconnect.model.DiagnosticActivation;
import com.siemens.mindsphere.sdk.mindconnect.model.DiagnosticActivationStatus;
import com.siemens.mindsphere.sdk.mindconnect.model.DiagnosticActivationsGetRequest;
import com.siemens.mindsphere.sdk.mindconnect.model.DiagnosticActivationsIdDeleteRequest;
import com.siemens.mindsphere.sdk.mindconnect.model.DiagnosticActivationsIdGetRequest;
import com.siemens.mindsphere.sdk.mindconnect.model.DiagnosticActivationsIdMessagesGetRequest;
import com.siemens.mindsphere.sdk.mindconnect.model.DiagnosticActivationsIdPutRequest;
import com.siemens.mindsphere.sdk.mindconnect.model.DiagnosticActivationsPostRequest;
import com.siemens.mindsphere.sdk.mindconnect.model.Mapping;
import com.siemens.mindsphere.sdk.mindconnect.model.PagedDiagnosticActivation;
import com.siemens.mindsphere.sdk.mindconnect.model.PagedDiagnosticInformationMessages;
import com.siemens.mindsphere.sdk.mindconnect.model.PagedMapping;
import com.siemens.mindsphere.sdk.mindconnect.model.PagedRecoverableRecords;
import com.siemens.mindsphere.sdk.mindconnect.model.RecoverableRecordsGetRequest;
import com.siemens.mindsphere.sdk.mindconnect.model.RecoverableRecordsIdDeleteRequest;
import com.siemens.mindsphere.sdk.mindconnect.model.RecoverableRecordsIdDownloadLinkGetRequest;
import com.siemens.mindsphere.sdk.mindconnect.model.RecoverableRecordsIdReplayPostRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MindConnectService extends MindsphereService {

	MindConnectHelper mindConnectHelper = new MindConnectHelper();

	public String diagnosticActivationsGet() throws MindsphereException {

		DiagnosticActivationsClient diagnosticActivationsClient = mindConnectHelper
				.getDiagnosticActivationsClient(getToken(), getHostName());

		log.info("diagnosticActivationsClient object created successfully : {}");

		DiagnosticActivationsGetRequest requestObject = new DiagnosticActivationsGetRequest();
		PagedDiagnosticActivation response = diagnosticActivationsClient.diagnosticActivationsGet(requestObject);
		if (response != null) {
			log.info("Getting response Successfully for diagnosticActivationsGet :" + response);
			return response.toString();
		} else {
			log.info("Getting null response for diagnosticActivationsGet");
			return null;
		}

	}

	public String diagnosticActivationsPostTest() throws MindsphereException {

		DiagnosticActivationsClient diagnosticActivationsClient = mindConnectHelper
				.getDiagnosticActivationsClient(getToken(), getHostName());
		DiagnosticActivationsPostRequest requestObject = mindConnectHelper.generateDiagnosticActivationsPostRequest();
		log.info("Request generated Successfully for diagnosticActivationsPost: {}");
		DiagnosticActivation response = diagnosticActivationsClient.diagnosticActivationsPost(requestObject);
		if (response != null) {
			log.info("Getting response Successfully for diagnosticActivationsPost :" + response);
			return response.toString();
		} else {
			log.info("Getting null response for diagnosticActivationsPost");
			return null;
		}

	}

	public String diagnosticActivationsDeleteTest(String id) throws MindsphereException {
		DiagnosticActivationsClient diagnosticActivationsClient = mindConnectHelper
				.getDiagnosticActivationsClient(getToken(), getHostName());

		DiagnosticActivationsIdDeleteRequest requestObject = new DiagnosticActivationsIdDeleteRequest();
		requestObject.setId(id);
		diagnosticActivationsClient.diagnosticActivationsIdDelete(requestObject);
		log.info("diagnosticActivation deleted Successfully.");
		return "deleted";
	}

	public String diagnosticActivationsIdGetTest(String id) throws MindsphereException {
		DiagnosticActivationsClient diagnosticActivationsClient = mindConnectHelper
				.getDiagnosticActivationsClient(getToken(), getHostName());

		DiagnosticActivationsIdGetRequest requestObject = new DiagnosticActivationsIdGetRequest();
		requestObject.setId(id);
		DiagnosticActivation response = diagnosticActivationsClient.diagnosticActivationsIdGet(requestObject);
		if (response != null) {
			log.info("Getting response Successfully for diagnosticActivationsIdGet:" + response);
			return response.toString();
		} else {
			log.info("Getting null response for diagnosticActivationsIdGet");
			return null;
		}
	}

	public String diagnosticActivationsIdMessagesGetTest(String id) throws MindsphereException {
		DiagnosticActivationsClient diagnosticActivationsClient = mindConnectHelper
				.getDiagnosticActivationsClient(getToken(), getHostName());

		DiagnosticActivationsIdMessagesGetRequest requestObject = new DiagnosticActivationsIdMessagesGetRequest();
		requestObject.setId(id);
		PagedDiagnosticInformationMessages response = diagnosticActivationsClient
				.diagnosticActivationsIdMessagesGet(requestObject);
		if (response != null) {
			log.info("Getting response Successfully for diagnosticActivationsIdMessagesGet :" + response);
			return response.toString();
		} else {
			log.info("Getting null response for diagnosticActivationsIdMessagesGet");
			return null;
		}

	}

	public String diagnosticActivationsIdPutTest(String id) throws MindsphereException {
		DiagnosticActivationsClient diagnosticActivationsClient = mindConnectHelper
				.getDiagnosticActivationsClient(getToken(), getHostName());

		DiagnosticActivationsIdPutRequest requestObject = new DiagnosticActivationsIdPutRequest();
		requestObject.setId(id);
		DiagnosticActivationStatus diagnosticActivationStatus = new DiagnosticActivationStatus();
		diagnosticActivationStatus.setStatus(DiagnosticActivationStatus.StatusEnum.fromValue("ACTIVE"));
		requestObject.setDiagnosticActivationStatus(diagnosticActivationStatus);
		DiagnosticActivation response = diagnosticActivationsClient.diagnosticActivationsIdPut(requestObject);
		if (response != null) {
			log.info("Getting response Successfully for diagnosticActivationsIdPut :" + response);
			return response.toString();
		} else {
			log.info("Getting null response for diagnosticActivationsIdPut");
			return null;
		}
	}

	public String dataPointMappingsGetTest() throws MindsphereException {
		MappingsClient mappingClient = mindConnectHelper.getMappingsClient(getToken(), getHostName());

		DataPointMappingsGetRequest dataPointMappingsGetRequest = new DataPointMappingsGetRequest();
		PagedMapping response = mappingClient.dataPointMappingsGet(dataPointMappingsGetRequest);
		if (response != null) {
			log.info("Getting response Successfully for dataPointMappingsGet :" + response);
			return response.toString();
		} else {
			log.info("Getting null response for dataPointMappingsGet");
			return null;
		}

	}

	public String dataPointMappingsIdGetTest(String id) throws MindsphereException {
		MappingsClient mappingClient = mindConnectHelper.getMappingsClient(getToken(), getHostName());

		DataPointMappingsIdGetRequest requestObject = new DataPointMappingsIdGetRequest();
		requestObject.setId(id);
		Mapping response = mappingClient.dataPointMappingsIdGet(requestObject);
		if (response != null) {
			log.info("Getting response Successfully for dataPointMappingsIdGet:" + response);
			return response.toString();
		} else {
			log.info("Getting null response for dataPointMappingsIdGet");
			return null;
		}
	}

	public String dataPointMappingsPostTest() throws MindsphereException {
		MappingsClient mappingClient = mindConnectHelper.getMappingsClient(getToken(), getHostName());

		DataPointMappingsPostRequest requestObject = mindConnectHelper.generateDataPointMappingsPostRequest();
		log.info("Request generated Successfully for dataPointMappingsPost: {}");
		Mapping response = mappingClient.dataPointMappingsPost(requestObject);
		if (response != null) {
			log.info("Getting response Successfully for dataPointMappingsPost :" + response);
			return response.toString();
		} else {
			log.info("Getting null response for dataPointMappingsPost");
			return null;
		}

	}

	public String dataPointMappingsIdDeleteTest(String id) throws MindsphereException {
		MappingsClient mappingClient = mindConnectHelper.getMappingsClient(getToken(), getHostName());

		DataPointMappingsIdDeleteRequest requestObject = new DataPointMappingsIdDeleteRequest();
		requestObject.setId(id);
		mappingClient.dataPointMappingsIdDelete(requestObject);
		log.info("dataPointMapping deleted successfully :");
		return "deleted";
	}

	public String recoverableRecordsGetTest() throws MindsphereException {
		RecordRecoveryClient recoveryClient = mindConnectHelper.getRecordRecoveryClient(getToken(), getHostName());

		RecoverableRecordsGetRequest requestObject = new RecoverableRecordsGetRequest();
		PagedRecoverableRecords response = recoveryClient.recoverableRecordsGet(requestObject);
		if (response != null) {
			log.info("Getting response Successfully for recoverableRecordsGet :" + response);
			return response.toString();
		} else {
			log.info("Getting null response for recoverableRecordsGet");
			return null;
		}

	}

	public String recoverableRecordsIdReplayPost(String id) throws MindsphereException {

		RecordRecoveryClient recoveryClient = mindConnectHelper.getRecordRecoveryClient(getToken(), getHostName());
		RecoverableRecordsIdReplayPostRequest requestObject = new RecoverableRecordsIdReplayPostRequest();
		requestObject.setId(id);
		recoveryClient.recoverableRecordsIdReplayPost(requestObject);
		log.info("recoverableRecordsIdReplayPost created successfully");
		return "created";
	}

	public String recoverableRecordsIdDownloadLinkGetTest(String id) throws MindsphereException {
		RecordRecoveryClient recoveryClient = mindConnectHelper.getRecordRecoveryClient(getToken(), getHostName());
		RecoverableRecordsIdDownloadLinkGetRequest requestObject = new RecoverableRecordsIdDownloadLinkGetRequest();
		requestObject.setId(id);
		String response = recoveryClient.recoverableRecordsIdDownloadLinkGet(requestObject);
		if (response != null)
			log.info("Getting response Successfully for recoverableRecordsIdDownloadLinkGet :" + response);
		return response;

	}

	public String recoverableRecordsIdDelete(String id) throws MindsphereException {
		RecordRecoveryClient recoveryClient = mindConnectHelper.getRecordRecoveryClient(getToken(), getHostName());
		RecoverableRecordsIdDeleteRequest requestObject = new RecoverableRecordsIdDeleteRequest();
		requestObject.setId(id);
		recoveryClient.recoverableRecordsIdDelete(requestObject);
		log.info("recoverableRecords deleted successfully");
		return "deleted";
	}

}
