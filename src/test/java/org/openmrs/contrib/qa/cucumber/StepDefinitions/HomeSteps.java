package org.openmrs.contrib.qa.cucumber.StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;

public class HomeSteps extends Steps {

  @Then("User should be on the Home page with all app links")
  public void shouldBeOnHomePage() {
    for(String appName : new String[] {
            "Find Patient Record", "Active Visits", "Register a patient",
            "Capture Vitals", "Appointment Scheduling", "Reports", "Data Management",
            "Configure Metadata", "System Administration"
    }) {
      Assert.assertNotNull(getElement(By.linkText(appName)));
    }
  }

  @And("User visits home page")
  public void visitHome() {
    browseUrl("/referenceapplication/home.page");
  }
}
