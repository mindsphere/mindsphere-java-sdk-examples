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

Tenant Credentials
| Sr. No. | Environment Variable | Description |
|-----|--------------|--------------|
|1 | HOST_ENVIRONMENT | Store the region in environment variable named `HOST_ENVIRONMENT`. If not specified, HOST_ENVIRONMENT defaults to `eu1` in region Europe 1 SDK and to `cn1` in region China 1 SDK.
|2 | MDSP_USER_TENANT | Store the user tenant name in environment variable named `MDSP_USER_TENANT` |.
|3 | MINDSPHERE_CLIENT_ID | Store the mindsphere client id in environment variable named `MINDSPHERE_CLIENT_ID`. |
|4 | MINDSPHERE_CLIENT_SECRET | Store the mindsphere client secret in environment variable named `MINDSPHERE_CLIENT_SECRET`. |
|5 | MINDSPHERE_SUB_TENANT | Store the mindsphere subtenant name in environment variable named `MINDSPHERE_SUB_TENANT`. |
#### OR
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

- Either of 2 credentials (Tenant Credentials or App Credentials ) will suffice to use SDKs.
- For more information about credentials please visit [Token Handling](https://developer.mindsphere.io/resources/mindsphere-sdk-java-v2/token_handling_v2.html)
###### Note 
> App Credentials and Application Credentials refers to same concept. These terms might be used interchangeably in the document.

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
- Copy .jar files of required dependent service/services in 'repo' folder. 'repo' folder is already created for you in the root directory. (For this project(mindsphere-sdk-java-examples) we will need all the .jar files but you can choose to use only required subset of all avaiable SDKs for your project.)
- For convinience, build.gradle is populated with relative path to copied dependencies.

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
- .jar file for application will be generated at build/libs/<jar-file-name>.jar


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
- `path` specifies where to look for application. Here in this case, our app is mindsphere-sdk-v2-demoapp-2.2.2-SNAPSHOT.jar file.
- Environment variables are listed under `env`. Since sample application demonstrates use of MindSphere SDKs, environemnt variables  are only specific for Token Generation. In case of other application, user can append the list with his/her own environment variables.
- As mentioned in 1 - Prerequisites, either of Tenant Credentials/ Application Credentials would suffice for getting token.
- As here opting for App Credentials, user will not have values of all environment variables at this point. In this scenario either put some dummy values or do not add variables at all. CF provides command to set environment variables hence they can be set later on.
- Now run the command `cf push`.
- Once application is successfully deployed check for app status using command `cf app routi`.
- Note down app URL displayed on screen.
<p>
    <img src="https://github.com/mindsphere/mindsphere-python-sdk-examples/blob/master/images/cfappurl.PNG" width="400">
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
<img src="https://github.com/mindsphere/mindsphere-python-sdk-examples/blob/master/images/apiroles.PNG" width="400">
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
    <img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/issueaccess.png" width="400">
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
    <img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/AccessAPPMP.PNG" width="400">
    </p>


4. Domain url is **Application URL** displayed on Application details page.
    <p>
    <img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/appurl.PNG" width="400">
    </p>
5. You can test endpoint by replacing 'your-domain-url-here' with appropriate values. For example topevents endpoint from EventAnalytics API is tested like this. :
<p>
<img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/eventeg.PNG" width="400">
</p>

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
- Copy .jar files of required dependent service/services in 'repo' folder. 'repo' folder is already created for you in the root directory. (For this project(mindsphere-sdk-java-examples) we will need all the .jar files but you can choose to use only required subset of all avaiable SDKs for your project.)
- For convinience, build.gradle is populated with relative path to copied dependencies.

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
1. Navigate to 'http://localhost:8080' (You can use any browswer of your choice).
2. Domain URL in this case will be 'http://localhost:8080'.
<p>
<img src="https://github.com/mindsphere/mindsphere-python-sdk-examples/blob/master/images/AccessAP.PNG" width="400">
</p>

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
<img src="https://github.com/mindsphere/mindsphere-node-sdk-examples/blob/master/images/sc.png" width="400">
</p>

- Create service credentials by providing details asked on page.
- Generated service credentials(combination of username and password) are displayed on screen. Store them in secure location as they displayed only once.
- Use command `cf login -a [cloudfoundry_login_url] -u <username> -p <password>`

###### Note 
> Service Credentials application is accessible to Tenant Admins only. If you are not a Tenant Admin then contact your Tenant Admin to generate these Service Credentials for you.

## 4 - Prepare the app to hand it over to Operator Cockpit
Please refer [Handing over app to Operator Cockpit](https://developer.mindsphere.io/howto/howto-develop-and-register-an-application.html#handover-the-application-to-the-operator-tenant)