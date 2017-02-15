## EAR Setup Verifier

This project is a sample for verifying correct system development setup.

If it can be built and run, the system has the correct setup and is ready for the B6B33EAR course.

This file contains:
1. A list of libraries and platforms necessary for developing applications in the course,
2. Guide for running this project,
3. Description of some common problems and their solutions,
4. Description of the structure of the application.

### Software Requirements

* **Java 8** (download [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html))
* **NetBeans 8.2** (download [here](https://netbeans.org/downloads/)) **or any other Java IDE**
* **Apache Maven 3** (download [here](https://maven.apache.org/download.cgi))
* **NodeJS 6** or later (download [here](https://nodejs.org/en/))
* **npm** (should be installed together with NodeJS)
* **PostgreSQL 9** and later (download [here](https://www.postgresql.org/download/))
* **Apache Tomcat 8** (download [here](http://tomcat.apache.org/download-80.cgi)) or any other Java EE 7 Web compatible application server

Feel free to install the software using your system's package manager.

### Application Setup

This is the shortest path setup without any customizations. Feel free to modify your setup in any way you like.

We are assuming that PostgreSQL is running at `localhost:5432` (see `src/main/resources/jdbc.properties`).
We are also assuming that Apache Tomcat is used with its default port - 8080.

#### PostgreSQL database setup

	1. start `psql`
	2. `CREATE USER ear WITH password 'ear';`
	3. `CREATE DATABASE ear WITH OWNER ear;`

#### Setup without IDE

1. Create a database named _ear_, owned by user _ear_ identified by password _ear_ (again, see `src/main/resources/jdbc.properties`).
2. Go to `src/main/webapp` and run `npm install` - this downloads JavaScript dependencies used by the UI implementation. This operation takes some time.
3. In the same folder, run `npm run build` - this packs the whole JavaScript-based UI into one bundle - `js/bundle.js`.
4. Go back to the project's root (i.e. the directory where this README file is located) and run `mvn clean package` - 
this uses maven to download Java dependencies of the project and builds a deployable _war_ archive with the application.
5. Copy the resulting archive - `ear-setup.war` from `target` into your Tomcat's `webapps` directory.
    1. E.g. if you unpacked Tomcat into `/opt/apache-tomcat-8.0.37`, copy the war file into `/opt/apache-tomcat-8.0.37/webapps`
6. Start Tomcat by running `startup.sh` (or `startup.bat`) in the `bin` directory of your Tomcat.
7. Go to [http://localhost:8080/ear-setup](http://localhost:8080/ear-setup) - you should see a table of people who will 
be teaching you, including their contact emails and rooms where to find them.
8. Congratulations, you are done!

#### Setup with NetBeans IDE

1. Same as above.
2. Same as above.
3. Same as above.
4. Open NetBeans, go to _Services_, right click on _Servers_ and add your Tomcat installation if it is not already present (NetBeans installer enables you to automatically download and install Tomcat).
    1. Enter some user credentials in Step 2 (e.g. admin/admin) and check the `Create user if it does not exist` checkbox, otherwise NetBeans won't be able to start Tomcat.
5. Click _Open Project_ and select this project.
6. _Clean and Build_ the project (`Shift + F11`).
7. Click _Run Project_.
8. If a server selection dialog opens, select the server you added in Step 4 (and also select the `Remember Permanently` option).
9. A browser will automatically open at [http://localhost:8080/ear-setup](http://localhost:8080/ear-setup).
    1. If not, open it manually.
10. Congratulations, you are done!

### Common Problems
If you are unable to install npm dependencies or run the application, make sure of the following things:
* If you are running on Windows, move the project directory as far up the directory tree as possible. `npm install` tends to run into the
file path limit on Windows (260 characters), because it creates a relatively deep file structure. 
Or, you can tell Windows to support longer paths - see [https://msdn.microsoft.com/en-us/library/windows/desktop/aa365247(v=vs.85).aspx](https://msdn.microsoft.com/en-us/library/windows/desktop/aa365247(v=vs.85).aspx), section **Maximum Path Length Limitation**.
* If `npm install` fails with errors on _Unmet peer dependencies_, make sure you are running npm at least version 3, which no longer treats missing peer dependencies as error.
* Make sure sufficient rights are set both on the project directory and on the directory of the Tomcat server, which is used to deploy your application.
* Make sure there are no spaces in paths to both your project and the Tomcat. Especially on Windows, where Tomcat is often installed into `Program Files`.


### Application Structure

The application's backend contains the following packages:

* `config` package contains configuration of the application, mainly Spring configuration,
* `dao` contains Data access objects,
* `model` contains our entity classes,
* `rest` contains REST web services,
* `service` contains Spring services - business logic belongs here. In case the business logic consists of service interfaces 
and separate implementations, it will probably require separation of the implementations into a subpackage.

Of course, the package naming and structure is up to the developer.
