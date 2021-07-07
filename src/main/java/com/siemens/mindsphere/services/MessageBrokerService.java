package com.siemens.mindsphere.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.siemens.mindsphere.model.MsgBrokerSubscriptionResponse;
import com.siemens.mindsphere.helpers.MsgBrokerHelper;
import com.siemens.mindsphere.model.MsgBrokerSubscriptionRequest;

import com.siemens.mindsphere.sdk.core.RestClientConfig;

import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.sdk.core.exception.MindsphereServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MessageBrokerService {

	String basePath = "api/messagebroker/v4/";

	RestClientConfig config = RestClientConfig.builder().build();
	MsgBrokerHelper meBrokerHelper = MsgBrokerHelper.builder().restClientConfig(config).build();

	public MsgBrokerSubscriptionResponse subscribetoMsgBroker(MsgBrokerSubscriptionRequest postbody,
			String bakcnedappName, String version, String topicName, String token) throws MindsphereException {
		log.info("Subcription call to msgBroker...");
		MsgBrokerSubscriptionResponse response = meBrokerHelper.createsubscription(postbody, bakcnedappName, version,
				topicName, token);
		log.info("Subcription done successfully to msgBroker " + response);

		return response;

	}

	public void unsubscribetoMsgBroker(String bakcnedappName, String version, String topicName, String token)
			throws MindsphereException {
		log.info("delete subcription call to msgBroker...");
		meBrokerHelper.unsubscribeMsgBroker(bakcnedappName, version, topicName, token);
		log.info("unsubscribed successfully.");

	}

	public String storeNotificationDatatofile(String body) throws IOException {
		log.info("Writing data to file....");
		try {
			File file = new File("msgBrokerResponse.json");

			if (!file.exists())
				file.createNewFile();
			if (file.length() != 0)
				body = "," + body;
			FileWriter myWriter = new FileWriter("msgBrokerResponse.json", true);
			myWriter.write(body);
			myWriter.close();
			log.info("Successfully wrote to the file.");
			return "Successfully wrote to the file.";
		} catch (IOException e) {
			log.info("An error occurred while writing file. " + e.getMessage());
			return e.getMessage();
		}
	}

	public String readNotificationData() throws IOException, MindsphereServiceException {
		log.info("Reading data from file....");
		String response = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader("msgBrokerResponse.json"));

			String line;
			while ((line = in.readLine()) != null) {
				response = response + line;
			}
			in.close();
		} catch (IOException e) {
			log.info("An error occurred while reading file. " + e.getMessage());
			throw new MindsphereServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		return response;
	}

	public String deleteContent() throws MindsphereServiceException {
		log.info("Reopen file to delete content");
		try {
			new FileWriter("msgBrokerResponse.json", false).close();
			log.info("Content deleted successfully");
			return "Content deleted successfully";
		} catch (Exception e) {
			log.info("An error occurred while reopen file. " + e.getMessage());
			throw new MindsphereServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, e);

		}

	}

}
