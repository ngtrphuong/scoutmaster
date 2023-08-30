scoutmaster
===========

Notes from Phuong regarding on how to successfully build this App in release 1.3.2 (master branch) -

 Up to 03 libraries we need to build by ourself and install them into the Local .m2/repositories folder via maven command - they are:
 
  1. JPAContainer 3.2.1-nj just for Vaadin 7 and applicable for ScoutMaster 1.3.2 app - https://github.com/ngtrphuong/jpacontainer
  
  2. VaadinUtils 0.1.2 library for ScoutMaster 1.3.2 app - https://github.com/ngtrphuong/VaadinUtils
  
  3. WizardsForVaadin 1.1.2 library (included JPAContainer 3.2.1-nj) for ScoutMaster 1.3.2 app - https://github.com/ngtrphuong/WizardsForVaadin
  
  4. Here are the guidance on how to install libraries to local repo - 
  
  How to install JAR file into the PC's local repo (typically the .m2 folder)
  
 * https://stackoverflow.com/questions/4955635/how-to-add-local-jar-files-to-a-maven-project
 
 * https://mkyong.com/maven/how-to-include-library-manully-into-maven-local-repository/

mvn install:install-file -Dfile=<path-to-file> -DgroupId=<group-id> -DartifactId=<artifact-id> -Dversion=<version> -Dpackaging=<packaging> -DgeneratePom=true

Where each refers to:

path-to-file: the path to the file to load e.g → c:\kaptcha-2.3.jar

group-id: the group that the file should be registered under e.g → com.google.code

artifact-id: the artifact name for the file e.g → kaptcha

version: the version of the file e.g → 2.3

packaging: the packaging of the file e.g. → jar

-- Register VaadinUtils 0.1.2 (include JPAContainer 3.2.1-nj); JPAContainer 3.2.1-nj; Luben Zstd for Tomcat; SMSj special version (1.0.0); and special Wizard For Vaadin 1.1.2 (can get in the Required Third Party Libraries folder or can build yourself) Package ---

mvn install:install-file -Dfile=Real_path\VaadinUtils-0.1.2.jar -DgroupId=au.com.vaadinutils -DartifactId=VaadinUtils -Dversion=0.1.2 -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=Real_path\vaadin-jpacontainer-3.2.1-nj.jar -DgroupId=com.vaadin.addon -DartifactId=jpacontainer -Dversion=3.2.1-nj -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=Real_path\zstd-jni-1.5.5-2.jar -DgroupId=com.github.luben -DartifactId=zstd-jni -Dversion=1.5.5-2 -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=Real_path\smsj-20051126.jar -DgroupId=org.marre.smsj -DartifactId=org.marre.smsj -Dversion=1.0.0 -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=Real_path\wizards-for-vaadin-1.1.2.jar -DgroupId=org.vaadin.addons -DartifactId=wizards-for-vaadin -Dversion=1.1.2 -Dpackaging=jar -DgeneratePom=true

-------------

CRM for running a local Scout Group

Scout Master is design to allow a local Group Leader, Group Council and Group Committee to easly manage a Scout Group.

Scout Master is NOT a tool to publish a web site you can use the likes of Drupal to do that.

Scout Master is squarely focused on management activities within a Scout Group.

We need your help in making ScoutMaster great.

Check out the sites wiki at https://github.com/bsutton/scoutmaster/wiki for details on the technology we use and how you can get involved.

The aim of version one of Scout Master is the following:

Version 1.0
Contact Mangement [complete]
Allow the creation of a Contact database that hold member, leader, volenteer and prospect information. [complete]

Communication [complete]
Provide email/sms tools to allow a leader to simply and easily communicate with their Patrol and the Patrol's parents. 

Calendar [complete]
Create and publish event activities as a calendar including meeting times and places. [complete]

Raffle managmeent
Ability to allocate raffle books to members and track who has returned books. Ability to send reminders to members with tickets outstanding.

Version 1.5
Track Purchases and payments made by members [under way]


Version 2.0
Track membership and activity payments, including raising invoices, credit notes and reciepts.

Create events and invite participants to those events.

Track youth member badge progress

Version 3.0
Provide a tablet based interface to allow attendence records to be kept and for simple access to a member's badge progress.

