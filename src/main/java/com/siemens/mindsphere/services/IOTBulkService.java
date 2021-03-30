package com.siemens.mindsphere.services;

import org.springframework.stereotype.Service;

import com.siemens.mindsphere.helpers.IOTBulkHelper;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.sdk.iotbulk.apiclient.BulkImportOperationsClient;
import com.siemens.mindsphere.sdk.iotbulk.apiclient.ReadOperationsClient;
import com.siemens.mindsphere.sdk.iotbulk.model.CreateImportJobRequest;
import com.siemens.mindsphere.sdk.iotbulk.model.JobStatus;
import com.siemens.mindsphere.sdk.iotbulk.model.RetrieveImportJobRequest;
import com.siemens.mindsphere.sdk.iotbulk.model.RetrieveTimeseriesRequest;
import com.siemens.mindsphere.sdk.iotbulk.model.TimeSeries;

@Service
public class IOTBulkService extends MindsphereService {

	IOTBulkHelper iotBulkHelper = new IOTBulkHelper();

	public String importjobs() throws MindsphereException {
		BulkImportOperationsClient bulkImportOperationsClient = iotBulkHelper.getBulkImportOperationsClient(getToken(),
				getHostName());

		CreateImportJobRequest requestObject = iotBulkHelper.createImportJobRequest();
		JobStatus response = bulkImportOperationsClient.createImportJob(requestObject);
		if (response != null)
			return response.toString();
		else
			return null;
	}

	public String retrieveImportJobTest(String id) throws MindsphereException {
		BulkImportOperationsClient bulkImportOperationsClient = iotBulkHelper.getBulkImportOperationsClient(getToken(),
				getHostName());
		
		RetrieveImportJobRequest requestObject = new RetrieveImportJobRequest();
		requestObject.setId((id));
		JobStatus response = bulkImportOperationsClient.retrieveImportJob(requestObject);
		if (response != null)
			return response.toString();
		else
			return null;
	}

	public String retrieveTimeseriesTest(String entityId, String propertySetName, String from, String to) throws MindsphereException {
		ReadOperationsClient readOperationsClient = iotBulkHelper.getReadOperationsClient(getToken(), getHostName());
		
		RetrieveTimeseriesRequest requestObject = new RetrieveTimeseriesRequest();
		requestObject.setEntity(entityId);
		requestObject.setPropertySetName(propertySetName);
		requestObject.setFrom(from);
		requestObject.setTo(to);
		TimeSeries response = readOperationsClient.retrieveTimeseries(requestObject);
		if (response != null)
			return response.toString();
		else
			return null;
		
	}

}
