const {When, Then, Given, setDefaultTimeout} = require("cucumber")
const puppeteer = require("puppeteer")
var PropertiesReader = require('properties-reader');
var properties = PropertiesReader('src/main/resources/qa.properties');
const chai = require("chai")
setDefaultTimeout(60 * 50000)

Given('User visits login page', async() => {
  this.browser = await puppeteer.launch({headless:properties.get('headless')})
  this.page = await this.browser.newPage()
  await this.page.goto(properties.get('root.url') + '/login.htm')
})

When("User enters right details", async() => {
  await this.page.type('#username', properties.get('username'))
  await this.page.type('#password', properties.get('password'))
})

When("User Selects Pharmacy Login Location", async() => {
  await this.page.click('#Pharmacy')
})

When("System Passes Login", async() => {
  await this.page.click('#loginButton')
  chai.expect('#loginButton').to.not.be.null;
})

Then("System Closes browser", async() => {
  this.browser.close();
})

When("User visits home page", async() => {
  this.page = await this.browser.newPage()
  await this.page.goto(properties.get('root.url') + '/referenceapplication/home.page')
})

When("User enters wrong details", async() => {
  await this.page.type('#username', 'admin')
  await this.page.type('#password', 'wrongPassword')
})

Then("System Fails Login", async() => {
  await this.page.click('#loginButton')
  chai.expect('.logout').to.not.be.null;
})

When("User logs out", async() => {
  await this.page.click('.logout')
})

Then("User Is logged out", async() => {
  chai.expect('.logout').to.not.be.null;
})

When("User enters {string} username", async(username) => {
  await this.page.type('#username', username)
})

When("User enters {string} password", async(password) => {
  await this.page.type('#password', password)
})

Then("System Evaluates Login {string}", async(status) => {
  await this.page.click('#loginButton')
  if(status == "true") {
    chai.expect('#loginButton').to.not.be.null;
  } else if(status == "false") {
    chai.expect('.logout').to.not.be.null;
  }
})

Then("User should be on the Home page with all app links", async() => {
  for (app of ["coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension", "org-openmrs-module-coreapps-activeVisitsHomepageLink-org-openmrs-module-coreapps-activeVisitsHomepageLink-extension", "org-openmrs-module-coreapps-awaitingAdmissionHomepageLink-org-openmrs-module-coreapps-awaitingAdmissionHomepageLink-extension", "referenceapplication-registrationapp-registerPatient-homepageLink-referenceapplication-registrationapp-registerPatient-homepageLink-extension", "referenceapplication-vitals-referenceapplication-vitals-extension",
    "appointmentschedulingui-homeAppLink-appointmentschedulingui-homeAppLink-extension", "registrationapp-basicRegisterPatient-homepageLink-registrationapp-basicRegisterPatient-homepageLink-extension", "reportingui-reports-homepagelink-reportingui-reports-homepagelink-extension", "coreapps-datamanagement-homepageLink-coreapps-datamanagement-homepageLink-extension", "org-openmrs-module-adminui-configuremetadata-homepageLink-org-openmrs-module-adminui-configuremetadata-homepageLink-extension", "coreapps-systemadministration-homepageLink-coreapps-systemadministration-homepageLink-extension"]) {
    chai.expect('#' + app).to.not.be.null;
  }
})