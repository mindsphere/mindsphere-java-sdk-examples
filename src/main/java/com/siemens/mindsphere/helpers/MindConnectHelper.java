package com.siemens.mindsphere.helpers;

import com.siemens.mindsphere.sdk.mindconnect.apiclient.DiagnosticActivationsClient;
import com.siemens.mindsphere.sdk.mindconnect.apiclient.MappingsClient;
import com.siemens.mindsphere.sdk.mindconnect.apiclient.RecordRecoveryClient;
import com.siemens.mindsphere.sdk.mindconnect.model.DataPointMappingsPostRequest;
import com.siemens.mindsphere.sdk.mindconnect.model.DiagnosticActivation;
import com.siemens.mindsphere.sdk.mindconnect.model.DiagnosticActivation.StatusEnum;

import com.siemens.mindsphere.sdk.mindconnect.model.DiagnosticActivationsPostRequest;
import com.siemens.mindsphere.sdk.mindconnect.model.MappingPost;

public class MindConnectHelper extends ControllerHelper {

	public DiagnosticActivationsClient getDiagnosticActivationsClient(String token, String host) {
		DiagnosticActivationsClient diagnosticActivationsClient = DiagnosticActivationsClient.builder().restClientConfig(getConfig(host))
				.mindsphereCredentials(getCreds(token)).build();

		return diagnosticActivationsClient;
	}

	public MappingsClient getMappingsClient(String token, String host) {
		MappingsClient mappingsClient = MappingsClient.builder().restClientConfig(getConfig(host))
				.mindsphereCredentials(getCreds(token)).build();

		return mappingsClient;
	}
	
	public RecordRecoveryClient getRecordRecoveryClient(String token, String host) {
		RecordRecoveryClient recordRecoveryClient = RecordRecoveryClient.builder().restClientConfig(getConfig(host))
				.mindsphereCredentials(getCreds(token)).build();

		return recordRecoveryClient;
	}
	

	public DiagnosticActivationsPostRequest generateDiagnosticActivationsPostRequest() {
		DiagnosticActivationsPostRequest requestObject = new DiagnosticActivationsPostRequest();
		DiagnosticActivation diagnosticActivation = new DiagnosticActivation();
		diagnosticActivation.setAgentId("078b1908bc9347678168760934465587");
		diagnosticActivation.setStatus(StatusEnum.fromValue("ACTIVE"));
		requestObject.setDiagnosticActivation(diagnosticActivation);
		return requestObject; 
	}

	public DataPointMappingsPostRequest generateDataPointMappingsPostRequest() {
		DataPointMappingsPostRequest requestObject = new DataPointMappingsPostRequest();
		MappingPost mapping = new MappingPost();
		mapping.setAgentId("ad22f8a7ebb24b8fb41767afd2c63f08");
		mapping.setDataPointId("SDKDP13");
		mapping.setEntityId("078b1908bc9347678168760934465587");
		mapping.setPropertySetName("TyreTemperature");
		mapping.setPropertyName("FLWheel");
		mapping.setKeepMapping(false);
		requestObject.setMapping(mapping);
		return requestObject;
	}
	
	
}
