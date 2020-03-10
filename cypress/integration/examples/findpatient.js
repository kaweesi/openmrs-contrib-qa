
var PropertiesReader = require('properties-reader');
var properties = PropertiesReader('src/main/resources/qa.properties');
import {Given, When, Then} from "cypress-cucumber-preprocessor/steps"

Given("Cypress User visits login page", async () => {
  cy.visit(properties.get('root.url') + '/login.htm');
})

When("Cypress User enters right details", async () => {
  cy.get('#username').type(properties.get('username'))
  cy.get('#password').type(properties.get('password'))
})

When("Cypress User Selects Pharmacy Login Location", async () => {
  cy.get('#Pharmacy').click()
})

When("Cypress System Passes Login", async () => {
  cy.get('#loginButton').click()
  cy.contains('Logout')
})

Then("Cypress User should be on the Home page with all app links", async () => {
  for (app of ["Find Patient Record", "Active Visits", "Register a patient", "Capture Vitals", "Appointment Scheduling", "Reports", "Data Management", "Configure Metadata", "System Administration"]) {
    cy.contains(app)
  }
})

Then("Cypress User presses find patient", async () => {
  cy.get('#coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension').click()
})

Then("Cypress User should be on the find patient page with all app links", async () => {
  cy.contains('Find Patient Record')
})

Then("Cypress System Closes browser", async () => {

})