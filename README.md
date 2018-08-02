Demo of Watson Discovery Services 


##Description

This demo backend web application is built on top of Java Hello World Sample/Example.

Original git repository: https://github.com/IBM-Bluemix/java-helloworld



##Configuration 

dir: demo-watson-discovery-api\config

API settings - credentials for the discovery instance:
<discovery>

	<user>USER_ID</user>

	<password>PASS</password>

	<apiPoint>API_POINT</apiPoint>

	<apiVersion>VERSION</apiVersion>

	<authenticate>false</authenticate> - Turn off/on (false/true) base64, Basic header access authentication 

</discovery>

Collection settings - could be found in the settings of discovery collection:
<collection>

	<name>Collection</name>

	<collectionId>COLLECTION_ID</collectionId>

	<configurationId>CONFIG_ID</configurationId>

	<environmentId>ENV_ID</environmentId>

</collection>



##Deploy pre-built application / war file

Before you begin, download and install the IBM Cloud CLI - https://console.bluemix.net/docs/cli/index.html#overview

For more information how to uploading an application - https://console.bluemix.net/docs/starters/upload_app.html

In the commmand line tool:

1. Change to the directory where your code is located. 

 cd demo-watson-discovery-api

2. Connect and log in to IBM Cloud.

bluemix api https://api.ng.bluemix.net
bluemix login -u your_user_name_or_email -o org_name -s space_name
bluemix login  -o org_name -s space_name -sso

3. From demo-watson-discovery-api, redeploy demo app to IBM Cloud by using the bluemix app push command. 

bluemix app push demowdscapgemini



## Running the application using the command-line

This project can be built with [Apache Maven](http://maven.apache.org/). The project uses [Liberty Maven Plug-in][] to automatically download and install Liberty from the [Liberty repository](https://developer.ibm.com/wasdev/downloads/). Liberty Maven Plug-in is also used to create, configure, and run the application on the Liberty server. 

Use the following steps to run the application locally:

1. Execute full Maven build to create the `target/JavaHelloWorldApp.war` file:
    ```bash
    $ mvn clean install
    ```

2. Download and install Liberty, then use it to run the built application from step 1:
    ```bash
    $ mvn liberty:run-server
    ```

    Once the server is running, the application will be available under [http://localhost:9080/JavaHelloWorldApp](http://localhost:9080/JavaHelloWorldApp).

Use the following command to run the built application in Bluemix:
    ```bash
    $ cf push <appname> -p target/JavaHelloWorldApp.war
    ```
## Developing and Deploying using Eclipse

IBM® Eclipse Tools for Bluemix® provides plug-ins that can be installed into an existing Eclipse environment to assist in integrating the developer's integrated development environment (IDE) with Bluemix.

1. Download and install  [IBM Eclipse Tools for Bluemix](https://developer.ibm.com/wasdev/downloads/#asset/tools-IBM_Eclipse_Tools_for_Bluemix).

2. Import this sample into Eclipse using `File` -> `Import` -> `Maven` -> `Existing Maven Projects` option.

3. Create a Liberty server definition:
  - In the `Servers` view right-click -> `New` -> `Server`
  - Select `IBM` -> `WebSphere Application Server Liberty`
  - Choose `Install from an archive or a repository`
  - Enter a destination path (/Users/username/liberty)
  - Choose `WAS Liberty with Java EE 7 Web Profile`
  - Continue the wizard with default options to Finish

4. Run your application locally on Liberty:
  - Right click on the `JavaHelloWorldApp` sample and select `Run As` -> `Run on Server` option
  - Find and select the localhost Liberty server and press `Finish`
  - In a few seconds, your application should be running at http://localhost:9080/JavaHelloWorldApp/

5. Create a Bluemix server definition:
  - In the `Servers` view, right-click -> `New` -> `Server`
  - Select `IBM` -> `IBM Bluemix` and follow the steps in the wizard.\
  - Enter your credentials and click `Next`
  - Select your `org` and `space` and click `Finish`

6. Run your application on Bluemix:
  - Right click on the `JavaHelloWorldApp` sample and select `Run As` -> `Run on Server` option
  - Find and select the `IBM Bluemix` and press `Finish`
  - A wizard will guide you with the deployment options. Be sure to choose a unique `Name` for your application
  - In a few minutes, your application should be running at the URL you chose.