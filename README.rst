=====================================================================
Getting Started with Jook
=====================================================================



Install Git
=====================================================================
http://code.google.com/p/msysgit/downloads/detail?name=Git-1.7.3.1-preview20101002.exe&can=3&q=



Fork the Project
=====================================================================

* Read - http://help.github.com/ (I generally use the https protocol fyi), You needn't necessarily use the key pair right off the bat. You can do that later.
* Read the "Everyday git" section under http://help.github.com
* Fork the trevershick/jook project

Get the Source from GitHub
=====================================================================

Clone your new project locally::

	git clone https://<username>@github.com/vijaysivaji/jook.git

Clone the test project::
 
	git clone https://<username>@github.com/trevershick/testjook.git

Play with Git a little
=====================================================================
Create a branch called 'playground' on your newly forked project
	git branch playground

List the available branches::

	git branch
	
	> * master
  	> playground

Switch your brach to playground::

	git checkout playground

Create a test file::

	notepad test.txt

Add it to your changeset::

	git add test.txt
	
Commit it to your local repository::

	git commit

Push your branch up to the remote repository::

	git push origin playground

*Note - everything is in your local repository until you push it up.
	

Switch back to the master branch and clean up::

	git branch
	>   master
	> * playground
	
	git checkout master

	git branch
	> * master
	>   playground
	
	# remove the branch from the local repository
	git branch -D playground
	
	# remove the branch from the remote repository
	git push origin :playground


Install MySQL 5
=====================================================================
http://dev.mysql.com/downloads/mysql/

1) Run the install, let it run as a service.

2) create the jook database::

	mysqladmin -u<username> -p<password> create jook
	
3) setup the jook user::

	mysql -u<username> -p<password> jook
	
	grant all privileges on jook.* to 'jook'@'localhost' identified by 'jook'
	
4) load the data::
	
	# while still in mysql
	source src\data\initial_data.sql
	# hopefully all goes well.

Setup Apache Tomcat 6 as a server Runtime Environment
=====================================================================
! Steps not listed here
Use the name "Apache tomcat 6.0 on JDK 6" (it will make things easier)
	
	
	
Import the project into Eclipse
=====================================================================
The JGit and EGit plugins for Eclipse don't work very well with
private repositories on github.  It works fine with public repositories though.

It's better to use git from the command line to learn it anyhow..

1) In Eclipse do File>Import>Maven...Existing Maven Projects
	Choose your project root that you cloned above and import all the projects.
	
2) You may see errors in the jook-webapp project...
	Right click the project and choose Properties
	Select Apache Tomcat 6 for the Targeted Runtime.
	
3) repeat 1 & 2 for testjook

4) Create a server
	add jook-ssodevhack, jook-webapp and jooktest-webapp to the server
	ensure the paths in the modules tab are /sso /jook and /jooktest
	change the server to 'Serve Modules without Publishing'
	
5) Setup the server.xml for jook
	open server.xml
	copy in jook/src/config/server.xml into the GlobalNamingResources
	dont' forget to copy mysql connector driver into <tomcat_home>/lib
	
	Note: ensure that the path of your Web Modules are set to the following
	
	1. Path: /jook

 	2. Path: /jookTest
 	
	3. Path: /sso
 	
	4. Path: /drawer-myapps

Open the Project Pages
=======================================================================
Navigate to http://localhost:8080/jooktest

Navigate to http://localhost:8080/jook

