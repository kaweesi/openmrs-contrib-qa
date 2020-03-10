# openmrs-contrib-qa


- Install webDrivers e.g into drivers folder
https://github.com/mozilla/geckodriver/releases/
or
http://chromedriver.storage.googleapis.com/index.html

- set `qa.properties`

- Run `mvn clean install` to run tests in selenium and puppeteer wrapped in cucumber

- Run `node_modules/.bin/cypress open -d` to run cypress tests wrapped in cucumber