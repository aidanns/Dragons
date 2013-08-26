# Dragons

The Dragon Game. An educational game designed to run on the MUGLE platform to teach students the basics of genetics. Designed and developed at The University of Melbourne.

# Build & Run the project

## Setup

1. Install Maven 3.0.3 or newer.

### Eclipse

1. Install Eclipse 4.3 or newer.
2. Install m2e plugin for Eclipse.
3. Install Maven Natives plugin for Eclipse.
4. Install Google plugin for Eclipse with GWT tooling & SDK.
5. Install JBoss Maven Profile plugin for Eclipse.
6. Import the project in to Eclipse as an "Existing Maven Project".
7. Right click on the "dragons-html" project, select "Properties", then select "Google" > "Web Toolkit" and check "Use Google Web Toolkit".

## Running

### Command line

    cd dragons

To run the HTML (GWT) version

	mvn -Phtml integration-test

To run the Java version

	mvn test -Pjava

### Eclipse

To run the Java version

1. Select the "Java" Maven profile using the JBoss plugin.
2. Run the dragons project with a Maven target of "test".

To run the HTML (GWT) version

1. Select the "HTML" Maven profile using the JBoss plugin.
2. Run the dragons project with a Maven target of "integration-test".




