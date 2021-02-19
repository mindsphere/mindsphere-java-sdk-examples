# MindSphere SDK for Java # 
API clients and References.md

## Full documentation

The full documentation can be found at [https://developer.mindsphere.io/resources/mindsphere-sdk-node/jsdoc/index.html](https://developer.mindsphere.io/resources/mindsphere-sdk-node/jsdoc/index.html)

## Preparation
### Prerequisites to use the MindSphere SDK for Java ###
- Java version 8 or higher
- User authorization token or service credentials with required scopes for Mindsphere Service APIs
- Environment variables added to your local to run application in local. When hosting an application in Cloud Foundry, the variable must be added to the manifest file.
#### Environment Variables ####
| Environment Variable | Description |
|--------------|--------------|
| HOST_ENVIRONMENT | Store the region in environment variable named `HOST_ENVIRONMENT`. If not specified, HOST_ENVIRONMENT defaults to `eu1` in region Europe 1 SDK and to `cn1` in region China 1 SDK.
| MDSP_USER_TENANT | Store the user tenant name in environment variable named `MDSP_USER_TENANT` |.
| MDSP_OS_VM_APP_VERSION | Store App Version in environment variable named `MDSP_OS_VM_APP_VERSION`. | 
| MINDSPHERE_CLIENT_ID | Store the mindsphere client id in environment variable named `MINDSPHERE_CLIENT_ID`. |
| MINDSPHERE_CLIENT_SECRET | Store the mindsphere client secret in environment variable named `MINDSPHERE_CLIENT_SECRET`. |
| MINDSPHERE_SUB_TENANT | Store the mindsphere subtenant name in environment variable named `MINDSPHERE_SUB_TENANT`. |



### Download
##### Downloading the MindSphere SDK for Java
Download the MindSphere SDK for Java from the [Siemens Industry Online Support (SIOS) Portal](https://support.industry.siemens.com/cs/document/109757603/mindsphere-sdk-for-java-and-node-js?dti=0&lc=en-US).

## Set up Java Sample Project

The following steps describe the way to set up a sample project to test . You can of course further create .

### Step 1: Clone this repository and Install dependencies
```
git clone https://github.com/mindsphere/mindsphere-java-sdk-examples.git
```
```
cd mindsphere-java-sdk-examples
```
##### Adding MindSphere SDK for Java Dependency¶
Copy the jar files into a folder named `repo` in the root of your project, e.g. repo.
Add the modules into the build.gradle file.

###### Note
> {x.y.z} is the version number of the MindSphere Core or Service SDK for Java (e.g. 1.0.0).

### Step 2: Upload app to CloudFoundry and fetch app URL
1. Build your application for deploying on cloud foundry. using `gradlew build` command
2. Login into cf using this cmd  : `cf login -a [cloudfoundry_login_url] -sso - AWS INT`
3. Select the `org` and `create space` based on the appname you've created with version and target it
4. Once it is targeted, push the app into cf using `cf push` cmd
5. Get the app url by using the cmd `cf apps` and store it for use in further steps

### Step 3: Login to Mindsphere Application
> Your tenant application url will be in the format : https://[tenantName].[region].mindsphere.io
##### Login using your tenant Credentials #####
Login to your tenant on this sign in page for mindsphere.
![alt text](https://github.com/mindsphere/mindsphere-java-sdk-examples/images/LoginPage.PNG)


### Step 4: Create a subtenant, an aspect type and asset types

##### Subtenant

In launchpad go to `Settings` -> `SubTenants` -> `Create SubTenant`

##### Aspects, Types and Assets

1. **Creating Aspects**

In launchpad go to `Asset Manager` -> `Aspects` -> `Create Aspects`

a)   Choose category as `dynamic`.

b)  Add variables to the aspect as follows and save it.

| Name | DataType | Unit | Max. Length |
|--------------|--------------|--------------|--------------|
| FLWHEEL | STRING | C | 255 |
| FRWHEEL | STRING | C | 255 |
| RLWHEEL | STRING | C | 255 |
| RRWHEEL | STRING | C | 255 |

2. **Creating Types**

a) Under `Aspects` -> Browse `Aspects`.

b) Browse the created Aspect and add it to the `Types`.

c) Save it.

3. **Creating Assets**

a)  Search for the created `Type` and add it to the `Asset`.

b) Save it.

### Step 5 : Creating SDK App
1. Login to your tenant
2. Open `Developer Cockpit`.
3. `Create new application` -> `Standard` -> `CloudFoundry` -> `AppName`, `Version` and `Description`
4. Add component url with routi as app name
5. Add Roles and Scopes
6. Save it and register after adding roles and scopes