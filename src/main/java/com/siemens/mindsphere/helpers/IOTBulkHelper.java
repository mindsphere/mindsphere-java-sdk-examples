package com.siemens.mindsphere.helpers;

import java.util.ArrayList;
import java.util.List;

import com.siemens.mindsphere.sdk.iotbulk.apiclient.BulkImportOperationsClient;
import com.siemens.mindsphere.sdk.iotbulk.apiclient.ReadOperationsClient;
import com.siemens.mindsphere.sdk.iotbulk.model.BulkImportInput;
import com.siemens.mindsphere.sdk.iotbulk.model.CreateImportJobRequest;
import com.siemens.mindsphere.sdk.iotbulk.model.Data;
import com.siemens.mindsphere.sdk.iotbulk.model.FileInfo;

public class IOTBulkHelper extends ControllerHelper  {

	public BulkImportOperationsClient getBulkImportOperationsClient(String token, String host) {
		BulkImportOperationsClient timeseriesClient = BulkImportOperationsClient.builder().restClientConfig(getConfig(host))
				.mindsphereCredentials(getCreds(token)).build();

		return timeseriesClient;
	}
	
	public ReadOperationsClient getReadOperationsClient(String token, String host) {
		ReadOperationsClient timeseriesClient = ReadOperationsClient.builder().restClientConfig(getConfig(host))
				.mindsphereCredentials(getCreds(token)).build();

		return timeseriesClient;
	}

	public CreateImportJobRequest createImportJobRequest() {
		CreateImportJobRequest requestObject = new CreateImportJobRequest();
		BulkImportInput bulkImportInput = new BulkImportInput();
		List<Data> listData = new ArrayList<Data>();

		Data data = new Data();
		data.setEntity("4ab1c8c37a3c47c48447057766ba4107");
		List<FileInfo> timeseriesFiles = new ArrayList<FileInfo>();
		data.setPropertySetName("testaspect1011");

		FileInfo fileInfo = new FileInfo();
		fileInfo.setFilePath("test1011.json");
		fileInfo.setFrom("2020-06-16T02:00:00.01Z");
		fileInfo.setTo("2020-06-16T02:00:00.30Z");
		timeseriesFiles.add(fileInfo);
		data.setTimeseriesFiles(timeseriesFiles);
		listData.add(data);

		bulkImportInput.setData(listData);
		requestObject.setBulkImportInput(bulkImportInput);
		return requestObject;
	}
	
	
	
	
}
