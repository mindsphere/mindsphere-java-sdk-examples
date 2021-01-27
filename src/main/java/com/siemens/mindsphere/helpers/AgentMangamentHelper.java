package com.siemens.mindsphere.helpers;

import com.siemens.mindsphere.sdk.agentmanagement.apiclient.AccessTokenClient;
import com.siemens.mindsphere.sdk.agentmanagement.apiclient.AgentsClient;
import com.siemens.mindsphere.sdk.agentmanagement.apiclient.AgentsDefaultSettingClient;
import com.siemens.mindsphere.sdk.agentmanagement.apiclient.BoardingClient;
import com.siemens.mindsphere.sdk.agentmanagement.apiclient.DataSourceConfigurationClient;
import com.siemens.mindsphere.sdk.agentmanagement.apiclient.RegistrationClient;
import com.siemens.mindsphere.sdk.core.MindsphereCredentials;
import com.siemens.mindsphere.sdk.core.RestClientConfig;

public class AgentMangamentHelper extends ControllerHelper{

    public  AgentsClient getAgentClient(RestClientConfig config, MindsphereCredentials cred) {
        AgentsClient api = AgentsClient.builder().restClientConfig(config).mindsphereCredentials(cred).build();
        return api;
    }

    public  BoardingClient getBoardingClient(String token, String host) {
        BoardingClient api = BoardingClient.builder().restClientConfig(getConfig(host)).mindsphereCredentials(getCreds(token)).build();
        return api;
    }

    public  RegistrationClient getRegistrationClient(String token, String host) {
        RegistrationClient api = RegistrationClient.builder().restClientConfig(getConfig(host)).mindsphereCredentials(getCreds(token))
                .build();
        return api;
    }

    public  AccessTokenClient getTokenClient(RestClientConfig config, MindsphereCredentials cred) {
        AccessTokenClient api = AccessTokenClient.builder().restClientConfig(config).mindsphereCredentials(cred)
                .build();
        return api;
    }

    public  DataSourceConfigurationClient getDataSourceConfigurationClient(RestClientConfig config,
            MindsphereCredentials cred)
    {
        DataSourceConfigurationClient api = DataSourceConfigurationClient.builder().restClientConfig(config)
                .mindsphereCredentials(cred).build();
        return api;
    }
    

    
    public  AgentsDefaultSettingClient getAgentMngmtdefaultSettingClient(String token, String host) {
    	AgentsDefaultSettingClient api = AgentsDefaultSettingClient.builder().restClientConfig(getConfig(host)).mindsphereCredentials(getCreds(token))
                .build();
        return api;
    }
}
