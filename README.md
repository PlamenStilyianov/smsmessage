#**Twilio SMS application**#

*Stand-alone Spring Security MVC internationalization application with Twilio Java API that allows you to send/receive SMS messages and store the messages into a database.*
#

*TWilio properties are in the main resource folder*

#

*To interact with the application you need to send a text message to +441633901152 and then login as an USER role (fingenius/welcome) @ http://95.149.51.171:8080/smsmessage/*
#
*WARNING!!! as the IP address is dynamic it could often be changed...*

###**Security - Implemented by using Spring 3 Security Module.**###

*The are two Roles Admin and User. The Admin role creates/edits/deletes users' details username and password. The USER role can only access the application to receive and send SMS messages. The ADMIN role is setup and validated at properties file, while the USER role is store and validated at a database level.*

###** Text Internationalization - Implemented by using Spring 4 MVC: Internationalization (I18N) & Localization(i18n)** ###

*The text Internalization has been implemented at the moment as it is just for English and Spanish languages. Text mapping properties files are used to allow any text at application level to be quickly edited and translated.(NO hard-coding TEXT)*

###** RESTful Web Services - Implemented by using Spring 4 REST & JSON**###

*There is a situation when an USER is typing a responding message and at the same time a new message arrives so the User needs a notification. The client - server interaction has been implemented by using Spring 4 RESTfull with JSON on the server side and AngularJS on client side.*


###** User Interface Design Patterns - implemented by using Spring 4 MVC & Tiles **###

*The consistent Web GUI Template style allows easy maintainability and rapid development*

###** Application lyer testing - implemented by using Spring 4 Test & HSQL**###

*The Spring 4 Test module has been used for testing and mocking on all lyers (presentation, service, persistence, rest, security), HSQL has been used for Spring ORM persistence layer.*

###** Continuous Integration with Build and Test Automation - Jenkins, Maven and Tomcat (It presumes that Open SSH, GIT, Bitbucket, Github,  Maven, Jenkins, Tomcat and corresponding plugins have been installed & configured)**###

*The application Maven pom file has been implemented with 3 profiles for GlassFish 4, JBoss 7 and Tomcat 7/8 application servers. This allows when a developer commit and push any changes to the repository an PRE event trigger has been fired for testing and automation build after successful built a Post event trigger fires to deploy the snapshot to the corresponding (GlassFish/JBoss/Tomcat). The all dependence library and repository have been configured and mapped within the pom , which will be downloaded during the build. The project does no contain any jar libraries.*

###** Summary of set up **###

*This application uses a Maven project structure, because easily can be imported into IntelliJ IDEA or Eclipse IDE projects and  quickly integrated with Jenkins*

###** Configuration (Maven/Git/OpenSSH must be installed and configured)**###

* git clone git@bitbucket.org:orajava/smsmessage.git
* cd smsmessage
* mvn clean
* mvn install

###** Dependencies **###

* Maven
* Git
* OppenSSH
* Tomcat
* GlassFish

###** Database configuration - Oracle 12c **###

*The pom file downloads ojdbs7.jar file and the configuration of datasource details like (driver,url,user,password) are configure at the application.properties file ( The are NO any hard coded property for the application)*

###** How to run tests - run Maven **###

*look at Configuration (Maven/Git/OpenSSH must be installed and configured)*

###** Deployment instructions - look at the profiles at POM.xml **###

*There are three profiles:*

#
<!--    ========    JBoss AS 7 Deployment   =========  -->
            <!--    ====    run mvn:deploy as: jboss-as:deploy -Denv=jboss ====-->
#
<!--    ========   GlassFish 4.1 Deployment   ==========  -->
            <!--    ====    run mvn:deploy as: glassfish:deploy -Denv=glassfish ===-->
            <!--    ====    run in jenkins as: clean install -Denv=glassfish -X === -->
            <!--    ====    copy asm-all-3.3.jar to .m/repository/ogr/glassfish/external/... === -->

#

 <!--    ========    Tomcat 8 Deployment   ==========  -->
            <!--    ====    run mvn:deploy as: tomcat7:deploy -Denv=tomcat ===-->
            <!--    =======     tomcat-users.xml  =====           -->
            <!--    <user username="admin" password="xxxxxx" roles="manager-script"/>    -->