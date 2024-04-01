# R3-Demo API Testing Project
## The main Frameworks included in the project:
- Selenium Webdriver 
- JUnit
- Cucumber
- Allure Reports
- Log file

## Project Design:
- Page Object Model (POM) design pattern

## List of test cases:
- API call is successful and returns valid price.
- Check the status code and status retuned by the API response.
- API could return multiple statuses like SUCCESS, FAILURE etc. Make sure this
is catered for.
- Fetch the USD price against the AED and make sure the prices are in range on 3.6 –
3.7.
- Make sure API response time is not less then 3 seconds then current time in second.
- Verify that 162 currency pairs are retuned by the API.
- Make sure API response matches the Json schema.

## Prerequisites:
- Java jdk version 11 or latest
- IntelliJ or Eclipse IDE

## Setup must be done before execution:
- Open a terminal and type below command line
    - sudo vim ~/.zshrc
- Press I and paste the below two lines at end of the lines 
    - export JAVA_HOME=/usr/libexec/java_home
    - export PATH=$PATH:$JAVA_HOME/bin
- Press escape key and press : and type wq and press enter
- Type below command line to refresh the environment file.
    - source ~/.zshrc
    
## How to run the project main test cases locally:
- Import or clone the maven project on any java IDE (Eclipse or IntelliJ installed)
- Install all the Maven Dependencies and configure the Java Buildpath for Junit5
- Open the terminal in the IDE and enter below comment based on your target testing device and press enter.
      - mvn test 
- Search for RunCucumberTest.java file in src/test/java to run as a feature file
- Cucumber Reports can be fetched in project under /target folder for the Results for the run. Open in System Editor Browser mode
- Allure reports can be fetched in project under /allure-results by executing the command in terminal 
       - allure serve (path of allure results)
 
## Credits
- Cucumber
- JUnit

## Related URL
- https://open.er-api.com/v6/latest/USD (API Testing URL)
- 



