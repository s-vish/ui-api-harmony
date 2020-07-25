# ui-api-harmony
Test dummy project

Deployment Plan

#Resources needed to support the product
1. IDE - Eclipse/IntelliJ
2. Windows/Max Machine
3. Browser Installed - Chrome, Firefox, Edge (in case of Windows OS)
4. Internet Connection

#Project can be imported from zip file or can be cloned from the git-hub (link shared on e-mail)

#Project Structure
1. src/test/java - all sub-packages contains only Test executable methods.

2. src/main/java 
com.api.* - this namining convension is used for all files related to API 
com.ui.* - this naming convension is used for all the files related to UI
com.base - contains TestBase class, which is extended by all Test executable classes.
com.utilities - All common functionalities which are useful for API and UI are mentioned here.
com.gui.pom.pages - All the UI pages with their intended elements are mentioned here.
com.report - report generatering utility class.

#com.config - contains config. properties file which is the source of configurable parameters.
city_name - will be running the test for the city value specified.
browser - will run the test on the browser mentioned here.
range - it's a varience range value.

#implemented extended reports and can be found inside 'extendedReport' folder. 

