package com.siemens.mindsphere.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.siemens.mindsphere.helpers.AgentMangamentHelper;
import com.siemens.mindsphere.sdk.agentmanagement.apiclient.AgentsDefaultSettingClient;
import com.siemens.mindsphere.sdk.agentmanagement.apiclient.BoardingClient;
import com.siemens.mindsphere.sdk.agentmanagement.apiclient.RegistrationClient;
import com.siemens.mindsphere.sdk.agentmanagement.model.AccessToken;
import com.siemens.mindsphere.sdk.agentmanagement.model.BoardingConfiguration;
import com.siemens.mindsphere.sdk.agentmanagement.model.ClientIdentifier;
import com.siemens.mindsphere.sdk.agentmanagement.model.CreatedObjects;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;

@Service
public class AgentManagementService extends MindsphereService{
	
	private AgentMangamentHelper agentMangamentHelper = new AgentMangamentHelper();
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public CreatedObjects getAccesstokenDefaultSetting(String assetId) throws MindsphereException {
		AgentsDefaultSettingClient client=agentMangamentHelper.getAgentMngmtdefaultSettingClient( getToken(),getHostName());
		return client.getAccessToken(assetId);
	}
	
	public BoardingConfiguration getBoardingConfig(String agentID) throws MindsphereException {
		BoardingClient client=agentMangamentHelper.getBoardingClient( getToken(),getHostName());
		return client.getBoardingConfiguration(agentID);
	}
	
	public AccessToken getAccessTokenFromClientAssertion(String clientAssertion) throws MindsphereException {
		AgentsDefaultSettingClient client=agentMangamentHelper.getAgentMngmtdefaultSettingClient( getToken(),getHostName());
		return client.getAccessTokenFromClientAssertion(clientAssertion);
	}
	
	public String getClientAssertionSharedSecret(String agentID, String clientSecret, String tenant) throws MindsphereException {
		AgentsDefaultSettingClient client=agentMangamentHelper.getAgentMngmtdefaultSettingClient( getToken(),getHostName());
		return client.getClientAssertionForSharedSecret(agentID, tenant, clientSecret);
	}

	public String getClientAssertion(String agentID, String tenant) throws MindsphereException {
		AgentsDefaultSettingClient client=agentMangamentHelper.getAgentMngmtdefaultSettingClient( getToken(),getHostName());
		return client.getClientAssertionForRSAProfile(agentID, tenant);
	}
	
	public String getClientAssertionRSA(String agentID, String tenant) throws MindsphereException {
		AgentsDefaultSettingClient client=agentMangamentHelper.getAgentMngmtdefaultSettingClient( getToken(),getHostName());
		return client.getClientAssertionForRSAProfile(agentID, tenant);
	}
	

	
	public ClientIdentifier onBoard(String initialAccessToken, String keys) throws MindsphereException {
		RegistrationClient client=agentMangamentHelper.getRegistrationClient( getToken(),getHostName());
		return client.onBoardAgent(initialAccessToken, keys);
	}
}
