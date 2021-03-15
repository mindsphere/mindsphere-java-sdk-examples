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
<p>
<img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/CFLoginCapture.PNG" width="1000">
</p>

3. Select the `org` and `create space` based on the appname you've created with version and target it
4. Once it is targeted, push the app into cf using `cf push` cmd
5. Get the app url by using the cmd `cf apps` and store it for use in further steps

### Step 3: Login to Mindsphere
> Your tenant application url will be in the format : https://[tenantName].[region].mindsphere.io
##### Login using your tenant Credentials #####
Login to your tenant on this sign in page for mindsphere.
<p>
<img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/LoginPage.PNG" width="400">
</p>

##### Mindsphere Dashboard #####
After successful login mindsphere dashboard will appear.
<p>
<img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/MindsphereDashboard.PNG" width="600">
</p>

### Step 4: Create a subtenant, an aspect type and asset types ###

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

a) Search for the created `Type` and add it to the `Asset`.

b) Save it.


### Step 5: Register App on Mindsphere ###

##### Open Developer Cockpit #####
Open Developer Cockpit Application in the Dashboard
<p>
<img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/DeveloperCockpit.PNG" width="200">
</p>

##### Add New Application #####
On the Developer Cockpit Dashboard Add new Application
<p>
<img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/NewApplication.PNG" width="800">
</p>

##### Enter Application Name, Description, App Type, and App Url #####
<p>
<img src="https://github.com/mindsphere/mindsphere-java-sdk-examples/blob/master/images/NewApplicationForm.PNG" width="1000">
</p>

On the New Application Form follow the following steps as marked on the image:
1. Select App Type [ `Standard`, `Fleet Manager Plugin`, `Mobile` or `API`].
2. Select App Infrastructure Type [`Mindsphere Cloud Foundry` or `Self Hosted`].
3. Enter App Name.
4. Enter version of Application. [`1.0.0`, etc]
5. Add Description.
6. Enter app component name
7. Enter componenet url which is the url obtained from cloud foundry after pushing app to cf.

Lastly Save It.

##### Add Roles and Scopes #####

Add Roles and Scopes to identify user and admin roles and scopes for users.