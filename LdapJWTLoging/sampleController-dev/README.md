# Sample Controller for Netprizm
This is a starter project for Netprizm Stack Controller micro-service. 
The project is designed to support **one type** of stack controller (ie: UE, DU, etc.) but for multiple vendors. 
There should be **only one instance** of this service on **each** stack server for which this is controlling. 
However, the stack server may be supporting more than one entity. Therefore, the one instance of the micro-service 
maybe controlling more than one stack (ie: UE_1 and UE_2) but of the same type and vendor.

This project uses:
   - Java 17
   - Maven 3.8
   - Springboot

**NOTE:** IntelliJ IDE 2021.2.3 (Community Edition)

<p><br/></p>

## Getting Started

#### Project Setup
After cloning the project, you need to set up some files in the project which **SHOULD NOT** be checked into source control.
 - `.idea/workspace.xml`
 - `src/main/resources/application.properties`

**NOTE:** Run the script `.setup.cmd` to create these files automatically for you.  
<p><br/></p>



#### Build and Run `Shift+F10`
The following instruction are based on IntelliJ IDEA (Community Edition).  
 - `mvn clean install -U -Dmaven.test.skip=true`
 - `mvn spring-boot:start`
 - `mvn spring-boot:run`

**NOTE:** If the project fails to run: Select the **pom.xml** file then right-click and
select the **Maven > Reimport** option.

Your service will be available at port **9020** and can be accessed:
`http://localhost:9020/sample-controller/api/v1/version`

Use Postman to verify the system works:
`GET:    http://localhost:9020/sample-controller/api/v1/version`


<p><br/></p>


#### REST API Specifications
The following URL documents the REST API for this service:
``` http://localhost:9020/sample-controller/swagger-ui.html ``` 


<p><br/><br/></p>

## Converting Project
The following steps are base on converting this sample project into 
a new project for controlling UE Stacks for multiple vendors. 
Before you begin be sure you have verified the cloned sample project works. 
(See instruction above). 

<p></p>

**UE Controller** for vendors: Acme and Globex

 - Remove .git folder:  `.git`
 - Run script:  `.setup.cmd` (Creates two files: application.properties, workspace.xml) 
 - Move the folder "sampleController" under the appropriate subfolder:  `/StackControllers/ueController`
 - Open project with IntelliJ IDEA
 - Update the **pom.xml** file:
    - artifactId (`ueController`)	 
    - name (`ueController`)
    - description (`UE controller for Netprizm`)
 - Update the **application.example.properties** and **application.properties** files:
	- server.port (`90##`)
	- server.servlet.contextPath  (`/ue-controller`)
	- controller.props.vendor={{controller-vendor-name}}  (`acme` or `globex`)
 - Update the **.deploy.sh** file:
    - port number (`90##`)	 
- Update the **model/AppProperties/Props** file:
    - version (`0.0.1`)
	- name (`UE Controller`)
	- description (`UE stack controller service for Netprizm`)
- Update the **README.md** file
	- title
	- description
	- URL (port and service name)
		- RUN Service URL
		- REST API Specs URL
	- Remove these section
	    - Converting Project
	    - Check In New Project
	    - Deployment
- Verify the new service works
    	
<p><br/></p>

#### Check In New Project
**NOTE:** You should not be on any Verizon VPN. Otherwise, it is very slow.

<p>Run the following terminal commands:</p>

 - git init
 - git add .
 - git commit -m "First Commit"
 - git remote add origin ```https://gitlab-prod.vzsme.com/netprizm/stackcontrollers/xyzController.git``` (Update URL)
 - git push -u origin master
 
 
 <p><br/><br/></p>

## Deployment
These projects are based on existing micro-service projects in which
the DevOps team has already created automated deployment scripts. Therefore, let
them know your new service name and any application properties tokens that need
to be set for each environment.
<br/>
<br/>

#### Package Into JAR File

Run the following in IntelliJ IDEA (Community Edition) to generate a clean new JAR file each time.<br />
The JAR will be in the "target" folder.
- `mvn clean package -Dmaven.test.skip=true`


<p><br/></p>


#### Manual Setup
###### Java 17 Installation and Setup:
* Copy Services\_dependencies\java\jdk-17_linux-x64_bin.rpm to \tmp folder on destination server
* Run command: `sudo rpm -ivh jdk-17_linux-x64_bin.rpm` in the \tmp folder on destination server
* Run command: `java -version` to make sure the correct version is installed and setup


###### Maven 3.8.3 Installation and Setup:
* Copy Services\_dependencies\maven\apache-maven-3.8.3-bin.tar.gz to \tmp folder on destination server
* Unpack content: `sudo tar xf /tmp/apache-maven-3.8.3-bin.tar.gz  -C /tmp/maven`
* Move content: `sudo mv /tmp/maven/apache-maven-3.8.3/  /usr/share/maven-3.8.3`<br />
  `mv /usr/share/maven /usr/share/maven-3.6.0`<br/>
  `cp -r /usr/share/maven-3.8.3 /usr/share/maven`
