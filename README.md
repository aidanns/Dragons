# Dragons

The Dragon Game. An educational game designed to run on the MUGLE platform to teach students the basics of genetics. Designed and developed at The University of Melbourne.

# Build & Run the project

## Setup

### Tools

Install the following tools:

- Maven 3.0.3 or newer.

### Dependencies Setup

Install the following jars to your local maven repo:

Three Rings PlayN fork:

	git clone git@github.com:threerings/playn.git
	cd playn
	mvn install

Three Rings TriplePlay library:

	git clone git@github.com:threerings/tripleplay.git
	cd tripleplay
	mvn install

Aidan's MigLayout port:

	git clone git@github.com:aidanns/migraine.git
	cd migraine
	mvn install

## Running

    cd dragons

To run the HTML (GWT) version (Jetty will automatically reload new .war files)

	mvn -Phtml integration-test

To run the Java version

	mvn test -Pjava

To clean and build a new HTML version (once Jetty is already running)
    cd core; mvn clean; cd ..; mvn -Phtml clean package





