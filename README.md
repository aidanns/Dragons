# Dragons

The Dragon Game. An educational game designed to run on the MUGLE platform to teach students the basics of genetics. Designed and developed at The University of Melbourne.

# Build & Run the project

## Setup

### Tools

Install the following tools:

- Maven 3.0.3 or newer.

### Dependencies

Install the following jars to your local maven repo.

- https://github.com/threerings/tripleplay
- https://github.com/threerings/playn (need to use their fork to use tripleplay)
- https://github.com/aidanns/migraine (only need the core sub-module)

## Running

    cd dragons

To run the HTML (GWT) version (Jetty will automatically reload new .war files)

	mvn -Phtml integration-test

To run the Java version

	mvn test -Pjava

To clean and build a new HTML version (once Jetty is already running)
    cd core; mvn clean; cd ..; mvn -Phtml clean package





