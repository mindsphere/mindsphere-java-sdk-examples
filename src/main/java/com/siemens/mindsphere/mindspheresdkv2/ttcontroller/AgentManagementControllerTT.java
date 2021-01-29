package com.siemens.mindsphere.mindspheresdkv2.ttcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siemens.mindsphere.helpers.AgentMangamentHelper;
import com.siemens.mindsphere.sdk.agentmanagement.apiclient.AgentsClient;
import com.siemens.mindsphere.sdk.agentmanagement.apiclient.BoardingClient;
import com.siemens.mindsphere.sdk.agentmanagement.apiclient.DataSourceConfigurationClient;
import com.siemens.mindsphere.sdk.agentmanagement.apiclient.RegistrationClient;
import com.siemens.mindsphere.sdk.agentmanagement.model.AccessToken;
import com.siemens.mindsphere.sdk.agentmanagement.model.Agent;
import com.siemens.mindsphere.sdk.agentmanagement.model.Agent.SecurityProfileEnum;
import com.siemens.mindsphere.sdk.agentmanagement.model.AgentInput;
import com.siemens.mindsphere.sdk.agentmanagement.model.BoardingConfiguration;
import com.siemens.mindsphere.sdk.agentmanagement.model.ClientIdentifier;
import com.siemens.mindsphere.sdk.agentmanagement.model.CreatedObjects;
import com.siemens.mindsphere.sdk.agentmanagement.model.DataPoint;
import com.siemens.mindsphere.sdk.agentmanagement.model.DataPoint.TypeEnum;
import com.siemens.mindsphere.sdk.agentmanagement.model.DataSource;
import com.siemens.mindsphere.sdk.agentmanagement.model.DataSourceConfiguration;
import com.siemens.mindsphere.sdk.agentmanagement.model.DataSourceConfigurationInput;
import com.siemens.mindsphere.sdk.agentmanagement.model.OnboardingStatus;
import com.siemens.mindsphere.sdk.agentmanagement.util.ClientFactory;
import com.siemens.mindsphere.sdk.assetmanagement.apiclient.AssetsClient;
import com.siemens.mindsphere.sdk.assetmanagement.model.Asset;
import com.siemens.mindsphere.sdk.assetmanagement.model.AssetResourceWithHierarchyPath;
import com.siemens.mindsphere.sdk.core.MindsphereCredentials;
import com.siemens.mindsphere.sdk.core.RestClientConfig;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.services.AgentManagementService;

@RestController
@RequestMapping(value = "/agents")
public class AgentManagementControllerTT {

	@Autowired
	private AgentManagementService agentManagementService;
	private static final Logger LOGGER = LoggerFactory.getLogger(AgentManagementControllerTT.class);
	private RestClientConfig config=RestClientConfig.builder().build();
	private MindsphereCredentials creds=MindsphereCredentials.builder().build();
	//private RestClientConfig config=RestClientConfig.builder().build();
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/createAgent/{assetId}")
	public Agent createAgent(@RequestHeader(required = false, value = "Authorization") String token,@PathVariable("assetId") String assetId,
			HttpServletRequest request) throws MindsphereException {
		AgentsClient agentClient = ClientFactory.getAgentClient(config, creds);


		AgentInput inp = new AgentInput();
		inp.setEntityId(assetId);
		inp.securityProfile(SecurityProfileEnum.SHARED_SECRET);
		inp.name("sdk_test_" + new Random().nextInt());
		try {
		return agentClient.createAgent(inp);
		}catch(MindsphereException e) {
		    System.out.println(e.getErrorMessage());
		    return null;
		}

	}
	

	@RequestMapping(method = RequestMethod.GET, value = "/configuredata")
	public DataSourceConfigurationInput configureData(
			@RequestHeader(required = false, value = "Authorization") String token, @RequestParam("id") String agentID,
			HttpServletRequest request) throws MindsphereException {


		AgentsClient agentsClient = ClientFactory.getAgentClient(config, creds);
		DataSourceConfigurationClient api = ClientFactory.getDataSourceConfigurationClient(config, creds);

		Agent agentOutput = agentsClient.getAgentByID(agentID);

		DataSourceConfigurationInput configuration = new DataSourceConfigurationInput();

		DataPoint dp = new DataPoint();
		dp.setId("SDKDP14");
		dp.setDescription("Temp");
		dp.setName("temp");
		dp.setType(TypeEnum.DOUBLE);
		dp.setUnit("C");
		//dp.setCustomData("Nominal", "~220 C");
		List<DataPoint> dpList = new ArrayList<>();
		dpList.add(dp);
		DataSource ds1 = new DataSource();
		ds1.setDescription("IOT DEVICE 2 installed on BGLR.");
		ds1.setName("IOT DEVICE 2");
		ds1.setDataPoints(dpList);
		List<DataSource> dsList = new ArrayList<DataSource>();
		dsList.add(ds1);
		configuration.setConfigurationId("SDK_CONFIG_" + new Random().nextInt());
		configuration.setDataSources(dsList);

		DataSourceConfiguration response = api.createDataSourceConfiguration(agentOutput.getEntityId(), configuration);
		return response;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/status")
	public OnboardingStatus status(@RequestHeader(required = false, value = "Authorization") String token,
			@RequestParam("id") String agentID, HttpServletRequest request) throws MindsphereException {

		BoardingClient cli = ClientFactory.getBoardingClient(config, creds);
		return cli.getBoardingStatus(agentID);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/boardingconfig")
	public BoardingConfiguration boardingconfig(@RequestHeader(required = false, value = "Authorization") String token,
			@RequestParam("id") String agentID, HttpServletRequest request) throws MindsphereException {

		BoardingClient cli = ClientFactory.getBoardingClient(config, creds);
		return cli.getBoardingConfiguration(agentID);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/onboardsharedsecret")
	public ClientIdentifier clientAssertionFromBoardingConfig(
			@RequestHeader(required = false, value = "Authorization") String token, @RequestParam("id") String agentID,
			@RequestParam("iat") String iat, HttpServletRequest request) throws MindsphereException {

		RegistrationClient registrationClient = ClientFactory.getRegistrationClient(config, creds);

		return registrationClient.onBoardAgentWithSharedSecretProfile(iat);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getclientassertion")
	public String getAccesstoken(
			@RequestHeader(required = false, value = "Authorization") String token, @RequestParam("id") String agentID,
			@RequestParam("tenant") String tenant,
			@RequestParam("clientSecret") String clientSecret, HttpServletRequest request) throws MindsphereException {
		String on = null;
		AgentMangamentHelper.selectToken(agentManagementService, token, request.getRequestURL().toString());
		try {
			on = agentManagementService.getClientAssertionSharedSecret(agentID, clientSecret, tenant);
		} catch (Exception e) {
			LOGGER.error(e.getClass().toString(), e.getStackTrace(), e);
		}
		return on;		
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/getaccesstoken")
	public AccessToken getAccesstoken( @RequestParam("clientassertion") String clientassertion,
			HttpServletRequest request) throws MindsphereException {
		AccessToken on = null;
		AgentMangamentHelper.selectToken(agentManagementService, null, request.getRequestURL().toString());
		try {
			on = agentManagementService.getAccessTokenFromClientAssertion(clientassertion);
		} catch (Exception e) {
			LOGGER.error(e.getClass().toString(), e.getStackTrace(), e);
		}
		return on;		
		
	}

	@RequestMapping(method = RequestMethod.GET, value = "/offboard")
	public OnboardingStatus offboard(@RequestHeader(required = false, value = "Authorization") String token,
			@RequestParam("id") String agentID, HttpServletRequest request) throws MindsphereException {

		BoardingClient agentsClient = ClientFactory.getBoardingClient(config, creds);

		OnboardingStatus agentOutput = agentsClient.offBoard(agentID);
		return agentOutput;
	}
	



@RequestMapping(method = RequestMethod.GET, value = "/getAccessTokenDefaultSetting/{assetId}")
	public CreatedObjects getAccesstokenDefaultSetting(
			@RequestHeader(required = false, value = "Authorization") String token, HttpServletRequest request, @PathVariable("assetId") String assetId)
			throws MindsphereException {
		CreatedObjects on = null;
		AgentMangamentHelper.selectToken(agentManagementService, token, request.getRequestURL().toString());
		try {
			on = agentManagementService.getAccesstokenDefaultSetting(assetId);
		} catch (Exception e) {
			LOGGER.error(e.getClass().toString(), e.getStackTrace(), e);
		}
		return on;
	}


private  AssetsClient getAssetClient() {
    //RestClientConfig config = RestClientConfig.builder().build();
    //MindsphereCredentials creds = MindsphereCredentials.builder().build();
    AssetsClient assetClient = AssetsClient.builder().restClientConfig(config).mindsphereCredentials(creds).build();
    return assetClient;
}

    @RequestMapping(method = RequestMethod.GET, value = "/createassetforagent/{tenant}")
    public AssetResourceWithHierarchyPath createAsset(@PathVariable("tenant") String tenant,
            @RequestParam(required = false, value = "assetType") String assetType) throws MindsphereException {
        try {
            AssetsClient assetsClient = getAssetClient();
            Asset assetDto = new Asset();

            assetDto.setName(tenant + "_" + new Random().nextInt());

            assetDto.setParentId(assetsClient.getRootAsset("0").getAssetId());

            if (assetType != null) {
                assetDto.setTypeId(assetType);
            } else {
                assetDto.setTypeId("core.mclib");
            }

            AssetResourceWithHierarchyPath asset = assetsClient.addAsset(assetDto);
            return asset;
        } catch (MindsphereException e) {
            AssetResourceWithHierarchyPath err = new AssetResourceWithHierarchyPath();
            err.setDescription(e.getErrorMessage());
            return err;
        }

    }


}
