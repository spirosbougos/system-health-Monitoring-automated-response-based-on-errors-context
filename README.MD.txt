mews System health monitoring & automated response, based on errors & context.

We schedule the server every 5 seconds providing a pool of web services called ENDPOINTS and we run them depending on the frequency we want,so we get a response time
and an http status.If something goes wrong it is saved in a table called FAILACTIONS.We have a table called SERVICESLASTRECORD that holds the last record of its service 
and a table with name TEMPORALRECORDS that holds all the requests.

HERE ARE SOME SERVICES INSIDE THIS PROJECT tested in postman
	Get the last Record of each service
	http://localhost:8080/MEWS/api/service/getLastServices
	
	Get a list of services that has been executed with one or more requested properties
	http://localhost:8080/MEWS/api/temporalRecords/getResponseTime?serviceName=name?sinceDate=sincedate&upToDate=uptodate
		
	Used to Delete an endpoint with the requested name
	http://localhost:8080/MEWS/api/endpoints/deleteEndpoint/name
	
	Deletes a requested service providing its name from the whole database.
	http://localhost:8080/MEWS/api/commonService/delete/SERVICE1
	
	Adds an endpoint 
	http://localhost:8080/MEWS/api/endpoints/addEndpoint
		

•Prerequisites

	wildfly 10.1 0 final 
	jdk11
	intellij IDEA community edition
	Apache Derby
	SQuirrel SQL client

•How to start project 

1.Download wildfly 10.1.0 final and extract it to a folder.
2.Make sure enviroment viriable are set up properly.{Variable Name: JAVA_HOME / Variable value : {the path to jdks location(example -> C:\JDK1.8)}}
3.Go to the Wildfly/bin/standalone.bat to start the server
4.write localhost:9990 on your browser and run it to make sure application servwer is running
5.Go to wildfly/bin/add-user.bat to add the management user so that admin console will be accesible and follow the istructions
6.Now go to your browser an refresh the page and insert username and password created in the previous step.
7.From the deployments tab we are going to deploy our project on the server.

Now we have to set up our database

8.Download derby and extract to a folder.
9.Go to db-derby/bin and startNetworkserver.bat
10.Downaload SQuirrel SQL client and create a new alias.providing this jdbc:derby://localhost:1527/SystemDB/create=true; connection-url
and as driver the Apache Derby Client.As username and password i have used "derbyuser"
	

11.Go to wildfly/standalone/configuration and open the standalone.xml with a text editor of your preference.
12.You have to add your datasource or mine whatever you like
--------------------------------------------------------------------------------------------------
<datasource jndi-name="java:/SystemDS" pool-name="SystemDS" enabled="true" use-ccm="false">
                    <connection-url>jdbc:derby://localhost:1527/SystemDB</connection-url>
                    <driver-class>org.apache.derby.jdbc.ClientDriver</driver-class>
                    <driver>derbyclient.jar</driver>
                    <security>
                        <user-name>derbyuser</user-name>
                        <password>derbyuser</password>
                    </security>
                    <validation>
                        <validate-on-match>false</validate-on-match>
                        <background-validation>false</background-validation>
                    </validation>
                    <statement>
                        <share-prepared-statements>false</share-prepared-statements>
                    </statement>
                </datasource> 
----------------------------------------------------------------------------------------------------------

12.We have to create some tables in the SQuirrel SQL Client.Below you will find the sql commands to execute.
		
					CREATE TABLE SERVICESLASTRECORD(
					SERVICE_NAME VARCHAR(255) PRIMARY KEY,
					ENDPOINT VARCHAR(255),
					METHOD_TYPE VARCHAR(255),
					HTTP_RESPONSE_CODE INT,
					REQUESTED_DATE TIMESTAMP,
					RESPONSE_TIME INT )
					
					CREATE TABLE ENDPOINTS(
					ID INT PRIMARY KEY,
					NAME VARCHAR(255),
					ENDPOINT VARCHAR(255),
					FREQUENCY INT,
					METHOD_TYPE VARCHAR(255))
					
					CREATE TABLE TEMPORALRECORDS (
					ID INT PRIMARY KEY  GENERATED ALWAYS AS IDENTITY,
					SERVICE_NAME VARCHAR(255),
					ENDPOINT VARCHAR(255),
					METHOD_TYPE VARCHAR(255),
					HTTP_RESPONSE_CODE INT,
					REQUESTED_DATE TIMESTAMP,
					RESPONSE_TIME INT)
					
					CREATE TABLE FAILACTIONS (
					NAME VARCHAR(255),
					ENDPOINT VARCHAR(255),
					ERROR_DESCRIPTION VARCHAR(255),
					RESPONSE_TIME INT,
					RESPONSE_CODE INT)
					
					insert into endpoints values(1,'serviceTest1','http://localhost:8080/MEWS/api/service/getLastServices',20,'get')

Now we are all set to package and deploy our project.


