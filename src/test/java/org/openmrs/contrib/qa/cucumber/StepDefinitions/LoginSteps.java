package org.openmrs.contrib.qa.cucumber.StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openmrs.contrib.qa.QATool;
import org.openmrs.contrib.qa.selenium.QAProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Properties;

public class LoginSteps extends Steps {

  private void logout() {
    logoutElement().click();
  }

  private void correctLogin() {
    Properties props = QATool.getQAProperties();
    enterUsername(props.getProperty(QAProperties.USER));
    enterPassword(props.getProperty(QAProperties.PASS));
  }

  private void wrongLogin() {
    enterUsername("admin");
    enterPassword("WrongPassword");
  }

  private WebElement logoutElement() {
    return getElement(By.linkText("Logout"));
  }

  private void login() {
    getLoginButton().click();
  }

  private WebElement getLoginButton() {
    return getElement(By.id("loginButton"));
  }


  private void enterUsername(String username) {
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).sendKeys(username);
  }

  private void enterPassword(String password) {
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys(password);
  }

  private void initLoginPage() {
    browseUrl("/login.htm");
  }

  @When("I visit login page")
  public void visitLoginPage() {
    initLoginPage();
  }

  @When("I enter wrong details")
  public void enterWrongDetails() {
    wrongLogin();
  }

  @When("I enter right details")
  public void enterRightDetails() {
    correctLogin();
  }

  @Then("Fail Login")
  public void failLogin() {
    login();
    Assert.assertNotNull(getLoginButton());
  }

  @Then("Pass Login")
  public void passLogin() {
    login();

    Assert.assertNull(getLoginButton());
  }

  @And("I Select Pharmacy Login Location")
  public void selectPharmacyLogin() {
    elementClickOn(By.id("Pharmacy"));
  }

  @When("I enter \"([^\"]*)\" username")
  public void anyUsername(String username) {
    enterUsername(username);
  }

  @And("I enter \"([^\"]*)\" password")
  public void anyPassword(String password) {
    enterPassword(password);
  }

  @And("Evaluate Login \"([^\"]*)\"")
  public void evaluateLogin(String status) {
    login();
    if(status.trim().endsWith("true")) {
      Assert.assertNull(getLoginButton());
    } else if(status.trim().endsWith("false")) {
      Assert.assertNotNull(getLoginButton());
    }
  }

  @And("I log out")
  public void iLogOut() {
   logout();
  }

  @Then("Is logged out")
  public void isLoggedOut() {
    Assert.assertNull(logoutElement());
  }

  @And("Close browser")
  public void closeBrowser() {
    quitBrowser();
  }
}
