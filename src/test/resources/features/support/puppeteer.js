const {When, Then, Given, setDefaultTimeout} = require("cucumber")
const puppeteer = require("puppeteer")
var PropertiesReader = require('properties-reader');
var properties = PropertiesReader('src/main/resources/qa.properties');
setDefaultTimeout(60 * 50000)

Given('User visits login page', async() => {
  this.browser = await puppeteer.launch({headless:properties.get('headless')})
  this.page = await this.browser.newPage()
  await this.page.goto(properties.get('root.url') + '/login.htm')
})

When("User enters right details", async () => {
  await this.page.type('#username', properties.get('username'))
  await this.page.type('#password', properties.get('password'))
})

When("User Selects Pharmacy Login Location", async () => {
  await this.page.click('#Pharmacy')
})

When("System Passes Login", async () => {
  await this.page.click('#loginButton')
  await this.page.waitForFunction(
    'document.querySelector("body").innerText.includes("Logout")'
  );
})

Then("System Closes browser", async () => {
  this.browser.close();
})

When("User visits home page", async () => {
  this.page = await this.browser.newPage()
  await this.page.goto(properties.get('root.url') + '/referenceapplication/home.page')
})

When("User enters wrong details", async () => {
  await this.page.type('#username', 'admin')
  await this.page.type('#password', 'wrongPassword')
})

Then("System Fails Login", async () => {
  await this.page.click('#loginButton')
  await this.page.waitForFunction(
    'document.querySelector("body").innerText.includes("Invalid username/password. Please try again.")'
  );
})

When("User logs out", async () => {
  await this.page.click('.logout')
})

Then("User Is logged out", async () => {
  await this.page.waitForFunction(
    'document.querySelector("body").innerText.includes("LOGIN")'
  );
})

When("User enters {string} username", async (username) => {
  await this.page.type('#username', username)
})

When("User enters {string} password", async (password) => {
  await this.page.type('#password', password)
})

Then("System Evaluates Login {string}", async (status) => {
  await this.page.click('#loginButton')
  if(status == "true") {
    await this.page.waitForFunction(
      'document.querySelector("body").innerText.includes("Logout")'
    );
  } else if(status == "false") {
    await this.page.waitForFunction(
      'document.querySelector("body").innerText.includes("Invalid username/password. Please try again.")'
    );
  }
})

Then("User should be on the Home page with all app links", async () => {
  await this.page.exposeFunction('arrayContainsArray', (superset, subset) => {
    if (0 === subset.length) {
      return false;
    }
    return subset.every(function (value) {
      return (superset.indexOf(value) >= 0);
    });
  });
  await this.page.waitForFunction(
    'arrayContainsArray(document.querySelector("body").innerText, ["Find Patient Record", "Active Visits", "Register a patient", "Capture Vitals", "Appointment Scheduling", "Reports", "Data Management", "Configure Metadata", "System Administration"])'
  );
})