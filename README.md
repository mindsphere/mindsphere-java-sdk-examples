# MindSphere SDK for Java # 

API clients and References.md

## Full documentation

The full documentation can be found at [https://developer.mindsphere.io/resources/mindsphere-sdk-node/jsdoc/index.html](https://developer.mindsphere.io/resources/mindsphere-sdk-node/jsdoc/index.html)

## Preparation
### Prerequisites to use the MindSphere SDK for Java ###
- 1. Java version 8 or higher
- 2. User authorization token or app credentials with required scopes for Mindsphere Service APIs.
    
    - 2.1 Environment variables set up in local machine to run application in local. 
    - 2.2 When application hosting type is `SELF_HOSTED`, the variables must be configured on server.
    - 2.3 When hosting an application in Cloud Foundry, the variable must be present as application's environment variables. This is achieved by adding variables in the manifest file.



#### Environment Variables ####

Application Credentials
| Sr. No. | Environment Variable | Description |
|-----|--------------|--------------|
|1 | MDSP_OS_VM_APP_VERSION| Store App Version in environment variable named `MDSP_OS_VM_APP_VERSION`. | 
|2 | MDSP_OS_VM_APP_NAME| Store App Name in environment variable named `MDSP_OS_VM_APP_VERSION`. | 
|3 | MDSP_KEY_STORE_CLIENT_ID| Store App Client ID in environment variable named `MDSP_KEY_STORE_CLIENT_ID`. |
|4 | MDSP_KEY_STORE_CLIENT_SECRET| Store App Client Secret in environment variable named `MDSP_KEY_STORE_CLIENT_SECRET`. |
|5 | MDSP_HOST_TENANT | Store the name of the tenant on which application is hosted in environment variable named `MDSP_HOST_TENANT`. |
|6 | MDSP_USER_TENANT | Store the name of the tenant from which application is being accessed in environment variable named `MDSP_USER_TENANT`. |
|7 | HOST_ENVIRONMENT | Store the region in environment variable named `HOST_ENVIRONMENT`. If not specified, HOST_ENVIRONMENT defaults to `eu1` in region Europe 1 SDK and to `cn1` in region China 1 SDK.

- App Credentials will suffice to use SDKs.
- For more information about credentials please visit [Token Handling](https://developer.mindsphere.io/resources/mindsphere-sdk-java-v2/token_handling_v2.html)
###### Note 
> App Credentials and Application Credentials refers to same concept. These terms might be used interchangeably in the document.
###### Note 
> From now, Tenant Credential support is removed from Java SDKs. Older versions with tenant credential support are still available on [Siemens Industry Online Support (SIOS) Portal](https://support.industry.siemens.com/cs/document/109757603/mindsphere-sdk-for-java-and-node-js?dti=0&lc=en-US). This application uses latest library for mindsphere-core library with version 2.4.0. Using older version of mindsphere-core library might lead to breaking behaviour of application. Hence we strongly recommend you to use latest version for smooth experience.

##### env:
  HOST_ENVIRONMENT: eu1
If not specified, HOST_ENVIRONMENT defaults to eu1 in region Europe 1 SDK and to cn1 in region China 1 SDK.

### Download
##### Downloading the MindSphere SDK for Java
Download the MindSphere SDK for Java from the [Siemens Industry Online Support (SIOS) Portal](https://support.industry.siemens.com/cs/document/109757603/mindsphere-sdk-for-java-and-node-js?dti=0&lc=en-US).


## 3 - Host Java Sample Project on MindSphere
MindSphere provides two ways to host an application - `Cloud Foundry Hosted` and `Self Hosted`.
For `Cloud Foundry Hosted` see 3A - 1 and for `Self Hosted` see 3A-2.

### 3A - 1 : Upload app to CloudFoundry and fetch app URL

The following steps describe way to deploy Node Sample Project on Cloud Foundry.
If you want to host your own application then skip to step 3(Push the App to CloudFoundry).

#### 1. Clone this repository.
####
```
git clone https://github.com/mindsphere/mindsphere-java-sdk-examples.git
```

#### 2. Install required dependencies.
- Download Java SDK from [Download](#2---download).
- Unzip the downloaded file.
- Navigate to <some path where unzipped folder is located>/mindsphere-java-sdk_2.5.0/com/siemens/mindsphere/
- Copy .jar files of required dependent service/services in 'libs' folder. 'libs' folder is already created for you in the root directory. (For this project(mindsphere-sdk-java-examples) we will need all the .jar files but you can choose to use only required subset of all available SDKs for your project.)
- Kindly note that Tenant Credential Support is removed from python SDKs from now. Hence we strongly recommend using
  latest version(2.4.0) of mindsphere-core library.
- For convenience, build.gradle is populated with relative path to copied dependencies.

###### Note 
> There are multiple versions available for few services. If you wish to use other version than specified in build.gradle, then change version in build.gradle.
> This sample app supports latest versions for Time Series Aggregate and Timeseries. Hence using lower versions will cause compile time errors.

#### 3. Generate .jar file from cloned project.
- Navigate inside the cloned project directory.
  <p>
    <img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/project-directory.PNG" width="400">
    </p>
- Now run the following command.
```
gradlew clean build
```
- .jar file for application will be generated at build/libs/jar-file-name.jar


#### 4. Push the App to CloudFoundry.

- Navigate to directory where cloned project directory is present. In this case navigate to sample-java-demo-app.
    <p>
      <img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/directory-java.PNG" width="400">
    </p>
- Copy .jar file generated in last step in this directory.
- In order to push app to CF, user must login to cloudfoundry. To login user can opt for either of two ways.
    - Jump to [Login to CF](#login-to-cf)
- At this point you are successfully logged in CF.
- Prepare manifest.yml file for pushing. File content pertinent to sample project are as :
    <p>
    <img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/manifest-java.PNG" width="400">
    </p>
- For convienience, sample manifest.yml is added in root directory of project. Please note that .jar file of application and manifest.yml exist in same directory as shown at start of 4th section.
- `path` specifies where to look for application. Here in this case, our app is mindsphere-sdk-v2-demoapp-2.2.2-SNAPSHOT.jar file.
- Environment variables are listed under `env`. Since sample application demonstrates use of MindSphere SDKs, environemnt variables  are only specific for Token Generation. In case of other application, user can append the list with his/her own environment variables.
- As mentioned in 1 - Prerequisites, either of Tenant Credentials/ Application Credentials would suffice for getting token.
- As here opting for App Credentials, user will not have values of all environment variables at this point. In this scenario either put some dummy values or do not add variables at all. CF provides command to set environment variables hence they can be set later on.
- Now run the command `cf push`.
- Once application is successfully deployed check for app status using command `cf app routi`.
- Note down app URL displayed on screen.
<p>
    <img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/cfappurl.PNG" width="400">
</p>

### 3A - 2 : Deploy the application as Self Hosted Application.
- Self Hosted Applications are deployed by user on desired server.
- User must note down URL where application is hosted.


### Step 3A - 3 : Create Application in Developer Cockpit.

#### Save the Application
1. Open the **Developer Cockpit** from the Launchpad and select the **Dashboard tab**.
2. Click on **Create new application**.
3. Select Type Standard and Infrastructure MindSphere Cloud Foundry if you have deployed application in cloud foundry. In case of self-hosted application select Self Hosted.
4. Enter an arbitrary Display Name and an Internal Name which will be part of the application URL. The Internal Name cannot be changed after initial creation!
5. Enter a version number.
    - MindSphere supports a Major.Minor.Patch scheme.
    - Versions must start with a major number >= 1.
    - The version cannot be changed after saving.
6. Upload an icon for your application.(Optional step)
7. Enter the component name. The component name must be the same as specified in the __*manifest.yml*__ file.
    - In case of sample project `mindsphere-java-sdk-examples` component name will be **routi** and component url can be obtained by 
      running `cf app routi` on command line.
    - In case of Self Hosted Application, component name and URL will be as per customer's deployment strategy.
8. Add one endpoint for your component using /** to match all of your application paths.
9. Set the content-security-policy according to the examples:
    - For Europe1 :     default-src 'self' *.eu1.mindsphere.io; style-src * 'unsafe-inline'; script-src 'self' 'unsafe-inline' *.eu1.mindsphere.io; img-src * data:;
    - For Europe2:     default-src 'self' *.eu1.mindsphere.io *.eu2.mindsphere.io; style-src * 'unsafe-inline'; script-src 'self' 'unsafe-inline' *.eu1.mindsphere.io *.eu2.mindsphere.io; img-src * data:;
10.  Click on **Save**.

#### Add roles and Scopes
1. Switch to the Authorization Management tab.
2. Select the application you just created.
3. Create an application scope, e.g. <provided-application-name>.subtenant.
4. Add the following Core roles to enable access to the respective APIs. For this project - `mindsphere-java-sdk-examples`, you will need following API roles. If required roles are not added then endpoints specific to those services will not work as expected.
<p>
<img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/apiroles.PNG" width="400">
</p>

#### Register the Application
1. Switch to the Dashboard tab.
2. Open the application details.
3. Click on Register.

#### Generate App Credentials
1. Switch to the Authorization Management tab.
2. Click on **App Credentials** tab.
    <p>
    <img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/ac.PNG" width="400">
    </p>
3. Click on **Issue access** button.
    <p>
    <img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/issueaccess.PNG" width="400">
    </p>
4. Select **Read And Write** .
    <p>
    <img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/readandwrite.PNG" width="400">
    </p>
5. Click on **Submit** button.
6. You will be presented with client ID and client secret for application.
    <p>
    <img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/cidcsecret.PNG" width="400">
    </p>
7. Store these values at secure location as they are displayed only once.

#### Set environment variables
1. In case of App Credentials, at this point you have all the required values for corresponding environment variables - 
`MDSP_OS_VM_APP_NAME`, `MDSP_OS_VM_APP_VERSION`, `MDSP_KEY_STORE_CLIENT_ID`,`MDSP_KEY_STORE_CLIENT_SECRET`,`MDSP_HOST_TENANT`, `MDSP_USER_TENANT`.

| Sr. No. | Environment Variable | Value |
|-----|--------------|--------------|
|1 | MDSP_OS_VM_APP_VERSION| Version you provided while creating application. | 
|2 | MDSP_OS_VM_APP_NAME| Internal name of your application(Can be seen in Application Details in Developer Cockpit). | 
|3 | MDSP_KEY_STORE_CLIENT_ID|  App Client ID displayed on screen in last step. |
|4 | MDSP_KEY_STORE_CLIENT_SECRET| App Client Secret displayed on screen in last step. |
|5 | MDSP_HOST_TENANT | Name of the tenant you are currently working upon. |
|6 | MDSP_USER_TENANT | This specifies the name of tenant which will use the application. Since we are currently in developing and testing phase, `MDSP_USER_TENANT` == `MDSP_HOST_TENANT`. |

2. Set the values of this environment variables in Cloud Foundry.
```
cf set-env routi <VARIABLE-NAME> <VARIABLE-VALUE>
```
If you have provided any other value for application name then modify the command accordingly.
As suggested by Cloud Foundry documentation, restage the app.
````
cf restage <APP-NAME>
````
3. In case of Self Hosted application, you need to store these values as per deployment strategy. Also make sure this values can be accessed in application.


#### Assign the App and Access via Launchpad
1. Navigate to MindSphere Launchpad -> Settings -> Users
2. Select a Developer you want to assign this application to. (You can assign it to yourself as well)
3. Scroll down a bit and click on **Edit direct assignments**.
    <p>
    <img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/assignapp.png" width="400">
    </p>
4. In the **Application Roles** section, search your application by internal name.
5. Select checkboxes for both admin and user.
    <p>
    <img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/addadminuser.PNG" width="400">
    </p>
6. Click on **Next**.
7. Click on **Save**.

Now concerned developer should be able to access the application via launchpad.

#### Access the application.
1. Navigate to MindSphere Launchpad.
2. Click on your application tile.
3. You should see something like :
    <p>
    <img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/swaggerui-changes/images/Homescreen1.png" width="400">
    </p>
4. By clicking on any endpoint showing on above image you should see like :
    <p>
    <img src="https://github.com/mindsphere/mindsphere-python-sdk-examples/blob/swaggerui-changes/images/putaspectcall.png" width="400">
    </p>
5. By clicking 'try it out' button you can make api call by putting correct parameters and requestbody. then you will get response like :
    <p>
    <img src="https://github.com/mindsphere/mindsphere-python-sdk-examples/blob/swaggerui-changes/images/respnseapi.png" width="400">
    </p>

6. Domain url is **Application URL** displayed on Application details page.
    <p>
    <img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/appurl.PNG" width="400">
    </p>

#### Create an asset via application.
1. For creating an asset we will first create aspect type. From created aspect type we create Asset type. Next we finally create an asset based on created Asset type.
2. First hit the endpoint PUT /assets/putaspect/{id}/{ifmatch}.
3.  If call is succesful, note down aspect id and aspect name from the response.
4. Next, hit PUT /assets/putaassettype /{id}/{ifmatch}. Pass noted aspect id and aspect name value in payload for creating asset type. 
5. If call is succesful, note down id from the response.
6. Next hit GET /assets/root to get root asset of the tenant. Note down id of an asset from the response.
7. Finally we will now create an asset. Pass id from step 5 and parent id from step 6 in the payload for creating an asset.
8. If asset creation is successful, you should see created asset in the call GET /assets/assets.
9. All the created resources (aspect type, asset type and asset) are visible on Asset Manager application on MindSphere Launchpad.

###### Note 
> Sample payload for endpoint is provided whenever required. For more information about payload, please refer `src/main/resources/sample-payload/<service-name>/sampleinput` file. Fields in the payload can be deleted as long as all mandatory fields are passed.
> For now, swagger endpoints are provided for **asset management, timeseries and event analytics** service only. 
> For other services, endpoints can be tried via entering url in browswer.

###### Note 
> We require XSRF token for calling PUT, POST/PATCH, DELETE APIs (For GET endpoints, XSRF_TOKEN is not compulsory). The value of XSRF token can be passed in request header. This token is available in cookies by name `XSRF-TOKEN`. We have fetched this token from cache in the application and put it in request header.  

### 4B - Set up Java Sample Project For Local Machine
The following steps describe the way to set up a sample project to test. 
##### 1. Clone this repository.
####
```
git clone https://github.com/mindsphere/mindsphere-java-sdk-examples.git
```
##### 2. Install required dependencies.
- Download Java SDK from [Download](#2---download).
- Unzip the downloaded file.
- Navigate to <some path where unzipped folder is located>/mindsphere-java-sdk_2.5.0/com/siemens/mindsphere/
- Copy .jar files of required dependent service/services in 'libs' folder. 'libs' folder is already created for you in the root directory. (For this project(mindsphere-sdk-java-examples) we will need all the .jar files but you can choose to use only required subset of all available SDKs for your project.)
- For convenience, build.gradle is populated with relative path to copied dependencies.

###### Note 
> There are multiple versions available for few services. If you wish to use other version than specified in build.gradle, then change version in build.gradle.
> This sample app supports latest versions for Time Series Aggregate and Timeseries. Hence using lower versions will cause compile time errors.

##### 3. Run the app.
- Navigate inside root directory of project
```
cd mindsphere-java-sdk-examples
```
- Run the following command to generate .jar file for application

```
gradlew clean build
```
- .jar file for application will be generated at build/libs/<jar-file-name>.jar

- Now run the application by following command
```
java -jar build/libs/<generated-jar-file-name>.jar
```
###### Note 
> You can choose to run the application in IDE of your choice.

##### 4. Access the app.
1. Navigate to 'http://localhost:8080' (You can use any browser of your choice).
2. Domain URL in this case will be 'http://localhost:8080'.
<p>
<img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/swaggerui-changes/images/homescreen.png" width="400">
</p>

###### Note 
> Sample payload for endpoint is provided whenever required. For more information about payload, please refer `src/main/resources/sample-payload/<service-name>/sampleinput` file. Fields in the payload can be deleted as long as all mandatory fields are passed.
> For now, swagger endpoints are provided for **asset management, timeseries and event analytics** service only. 
> For other services, endpoints can be tried via entering url in browser.


### Login to CF
- To login to cloudfoundry user can opt for either of two ways.
#### Using -sso
- `cf login -a [cloudfoundry_login_url] -sso`
- This command will prompt for Email and Password.
- Enter webkey credentials that you use for tenant login.
- You will be logged in as long as credentials in previous step are correct.
##### OR
#### Using Service Credentials on MindSphere.
- Navigate to MindSphere Launchpad -> Settings -> Service Credentials.
<p>
<img src="https://github.com/mindsphere/mindsphere-python-sdk-examples/blob/master/images/sc.png" width="400">
</p>

- Create service credentials by providing details asked on page.
- Generated service credentials(combination of username and password) are displayed on screen. Store them in secure location as they displayed only once.
- Use command `cf login -a [cloudfoundry_login_url] -u <username> -p <password>`

###### Note 
> Service Credentials application is accessible to Tenant Admins only. If you are not a Tenant Admin then contact your Tenant Admin to generate these Service Credentials for you.

## 4 - Prepare the app to hand it over to Operator Cockpit
Please refer [Handing over app to Operator Cockpit](https://developer.mindsphere.io/howto/howto-develop-and-register-an-application.html#handover-the-application-to-the-operator-tenant)



## 5 - Core to Third Party Supoort:

 Core to Third Party support allows MindSphere to call POST API in your API type application based on some event.
Such events could be ingestion of data to timeseries, application provisioning to tenant etc.


###### :warning: NOTE!!! This feature is only available for early access users.

 We have made this application(mindsphere-java-sdk-examples) compatible to core to third party support while maintaining support for existing capabilities.

In order to achieve this, there are few steps you will need to follow.

#### 1. Provide POST endpoint in your application which will called by MindSphere.
- In mindsphere-java-sdk-examples, we have added POST /alertNotifications endpoint to be called by MindSphere.
  
#### 2. Provide access to MindSphere to call your application endpoint.
- You need to give permission to MindSphere to call API endpoint in your API type application.

- This demo application is monolithic application i.e. it has both UI and API part clubbed as a single unit.
  There are two challenges with it:
    - For MindSphere to call endpoint in your application, it has to be of API type.
    - API type applications are not accessible via MindSphere launchpad.

-  Hence if we create this mindsphere-java-sdk-examples as Standard application, MindSphere will not be able to      call    endpoint of application and if we create it as `API` type application then it will not be accessible via launchpad.

- To tackle this issues we register same application once as Standard application and once as `API` type application.
    2A) Register application as API` type.
    -   1. Deploy the demo application on cloud foundry. Refer <section>.
    -   2. Create application in Developer Cockpit by name - `demoapiapp`. Provide application type as `API` and provide URL for application you get in i). Refer <section>
    -   3. Provide access to messagebroker (part of MindSphere which is actually going to call your endpoint).
        - 3.1 Navigate to `Authorization Management` tab.
        - 3.2 Search your application by its internal name. `
        - 3.3 Navigate to `App Roles` tab on the left vertical bar.
        - 3.4 Click on `Message Broker` tab.
        - 3.5 Click on `Grant access` button. A dialogue box should appear.
        - 3.6 Check the boxes for application scopes which you wish to provide to message broker.
        - 3.7 Click on `Save` button.
    <p>
    <img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/swaggerui-changes/images/MessageBrokerScopes.PNG" width="400">
    </p>
    
    -   4. Register the application.
       
    2B) Register the application as Standard(UI) application.
    -    1. Create application in Developer Cockpit by name - `demouiapp`. Provide application type as `Standard` and provide URL for application you get in 2A. Refer <section>
    -    2. Add `demoapiapp` as API Dependency is `demouiapp`.
        <photo>
    -    3. API access to UI app.
        <photo>
    -    4. Register the application.
        
#### 3. Subscribe to topic/event based on which you want MindSphere to call your endpoint. 
   - Follow : Assign the App and Access via Launchpad section and access the application.
   
   - First 4 endpoints are concerned with core to third party support. Other remaining endpoints are exactly same as  last delivery and are not changed.
    Those 4 endpoints are described below:
        1) `PUT` `/subscribe/{bakcendappName}/versions/{version}/topics/{topicName}` : Allows to subscribe to message broker topic.
            - 1.1 backendappName : Internal name of API type application you provided.
            - 1.2 version : version of API type application you provided.
            - 1.3 topicName: Name of the topic for which you would like to subscribe. For e.g. to subscribe for
              timeseries data ingest, topic name could be `mdsp.core.timeseries.v1.pubsub.data.ingest`.
            - 1.4 For subscribing you need to provide URL for API type application in the request body. These url should be in the form of `https://gateway.<mindsphere-zone>.mindsphere.io/api/<internal-name-of-api-app>-<tenant- name>/<version-of-application>/`.
        2) `DELETE` `/unsubscribe/{bakcendappName}/versions/{version}/topics/{topicName}` : Allows to unsubscribe to message broker topic.
        3) `GET` `/readNotification` : Read contents of messages received from messagebroker and stored in csv file.
        4) `DELETE` `/deleteContent` : Delete contents of messages received from messagebroker and stored in csv file.

   - For subscribing to message broker use first endpoint.

   
#### 4. Process the data.
-  Once we get request on POST endpoint /alertNotifications, we simply store received payload in csv file. 
-  You can  have your own business logic to process this data for e.g. any prediction algorithm to estimate    future    values or generate some alerts etc.

#### 5. View the data.
- Use `GET /readNotification` to see data stored in csv file.
 

> Note : You can use DELETE /  to wipe out csv file contents in case there are too many entries to go through. You can use /unsubscribe to unsubscribe from the topic. If you unsubscribe, MindSphere will not call endpoint POST /alertNotifications endpoint in your application. You can subscribe again whenever you want to start getting call from MindSphere to your API application.
 
