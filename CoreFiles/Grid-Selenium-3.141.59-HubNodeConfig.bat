REM Hub Config //comment nelow line if the machine is used as node
start java -jar selenium-server-standalone-3.141.59.jar -role hub -hubConfig gridHub.json

TIMEOUT 5
REM Node Config
start java -jar -Dwebdriver.gecko.driverC:\BMCSelenium\CoreFiles\geckodriver.exe -Dwebdriver.chrome.driver=C:\BMCSelenium\CoreFiles\chromedriver.exe -Dwebdriver.ie.driver=C:\BMCSelenium\CoreFiles\IEDriverServer.exe selenium-server-standalone-3.141.59.jar -role webdriver -nodeConfig gridNode.json 

