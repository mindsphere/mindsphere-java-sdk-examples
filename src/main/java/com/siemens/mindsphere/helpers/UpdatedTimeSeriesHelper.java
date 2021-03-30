package com.siemens.mindsphere.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.siemens.mindsphere.sdk.assetmanagement.apiclient.AspecttypeClient;
import com.siemens.mindsphere.sdk.assetmanagement.apiclient.AssetsClient;
import com.siemens.mindsphere.sdk.assetmanagement.apiclient.AssettypeClient;
import com.siemens.mindsphere.sdk.assetmanagement.model.AspectTypeResource;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetResourceWithHierarchyPath;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.sdk.timeseries.apiclient.TimeSeriesOperationsClient;
import com.siemens.mindsphere.sdk.timeseries.model.CreateOrUpdateTimeseriesDataRequest;
import com.siemens.mindsphere.sdk.timeseries.model.CreateOrUpdateTimeseriesRequest;
import com.siemens.mindsphere.sdk.timeseries.model.TimeSeries;
import com.siemens.mindsphere.sdk.timeseries.model.TimeSeriesDataItem;
import com.siemens.mindsphere.sdk.timeseries.model.TimeSeriesItem;


public class UpdatedTimeSeriesHelper extends ControllerHelper {

	
	public TimeSeriesOperationsClient getTimeSeriesOperationsClient(String token, String host) {
		TimeSeriesOperationsClient timeseriesClient = TimeSeriesOperationsClient.builder().restClientConfig(getConfig(host))
				.mindsphereCredentials(getCreds(token)).build();

		return timeseriesClient;
	}

	public CreateOrUpdateTimeseriesRequest CreateOrUpdateTimeseriesRequest() {
		CreateOrUpdateTimeseriesRequest createOrUpdateTimeseriesRequest =new CreateOrUpdateTimeseriesRequest();

		TimeSeries timeseries = new TimeSeries();
		TimeSeriesItem timeSeriesItem = new TimeSeriesItem();
		List<TimeSeriesItem> liSeriesItems = new ArrayList<TimeSeriesItem>();
		/*
		 * timeSeriesItem.setEntityId("166fafc5dc164999885309762f5f2f7c");
		 * timeSeriesItem.setPropertySetName("testaspect0611"); TimeSeriesDataItem
		 * timeSeriesDataItem = new TimeSeriesDataItem(); Map<String, Object> dataMap =
		 * timeSeriesDataItem.getFields(); dataMap.put("FLwheel", 99);
		 * dataMap.put("RLwheel", 98);
		 * timeSeriesDataItem.setTime("2020-11-05T010:01:02Z"); List<TimeSeriesDataItem>
		 * lisDataItems = new ArrayList<TimeSeriesDataItem>();
		 * lisDataItems.add(timeSeriesDataItem); timeSeriesItem.setData(lisDataItems);
		 * liSeriesItems.add(timeSeriesItem);
		 */
		TimeSeriesItem timeSeriesItem1 = new TimeSeriesItem();
		timeSeriesItem1.setEntityId("166fafc5dc164999885309762f5f2f7c");
		timeSeriesItem1.setPropertySetName("test_2020_11_11");
		TimeSeriesDataItem timeSeriesDataItem1 = new TimeSeriesDataItem();
		Map<String, Object> dataMap1 = timeSeriesDataItem1.getFields();
		dataMap1.put("test", 88);
		timeSeriesDataItem1.setTime("2019-03-10T23:00:00Z");
		List<TimeSeriesDataItem> lisDataItems1 = new ArrayList<TimeSeriesDataItem>();
		lisDataItems1.add(timeSeriesDataItem1);
		timeSeriesItem1.setData(lisDataItems1);
		liSeriesItems.add(timeSeriesItem1);

		timeseries.setTimeseries(liSeriesItems);
		createOrUpdateTimeseriesRequest.setTimeseries(timeseries);
		System.out.println(new Gson().toJson(createOrUpdateTimeseriesRequest));

		return  createOrUpdateTimeseriesRequest;
		
	}

	public CreateOrUpdateTimeseriesDataRequest CreateOrUpdateTimeseriesDataRequest(String entityId,String propertySetName) {
		CreateOrUpdateTimeseriesDataRequest requestObject = new CreateOrUpdateTimeseriesDataRequest();
		requestObject.setEntityId(entityId);
		requestObject.setPropertySetName(propertySetName);
		TimeSeriesDataItem timeSeriesDataItem = new TimeSeriesDataItem();
		timeSeriesDataItem.setTime("2020-11-06T10:00:00Z");
		Map<String, Object> dataMap = timeSeriesDataItem.getFields();
		dataMap.put("test", 99);
	try {
		AssetResourceWithHierarchyPath asset = AssetsClient.builder().build().getAsset(entityId, "0");
		System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(asset));
	} catch (MindsphereException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		List<TimeSeriesDataItem> lisDataItems = new ArrayList<TimeSeriesDataItem>();
		lisDataItems.add(timeSeriesDataItem);
		requestObject.setTimeseries(lisDataItems);
		System.out.println(new Gson().toJson(requestObject));
		return requestObject; 
	}
	
	
}
