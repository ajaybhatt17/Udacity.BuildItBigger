# FinalProject

## Feature includes
- java library to provide joke data
- android library to display intent to activity
- backend app engine to retrieve joke from java library and provide access through api
- android module to show joke through backend app engine api
- module also display google admob and interstial ad in free flavour and not in paid version
- android test case to verify retrieve joke from backend app engine is not empty
- automate running app engine server then running tests and then stopping server through gradle

## Custom Gradle task
run following command
<pre>./gradlew -b custom_gradle_task.gradle stopServer</pre>

## Put your local ip in key.properties file
path app/src/main/assets/key.properties
<pre>backend.ip=xx.xx.xx.xx</pre>

## Maintainer
ajayabhtt17@gmail.com