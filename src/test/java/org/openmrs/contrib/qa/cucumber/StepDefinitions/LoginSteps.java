package org.openmrs.contrib.qa.cucumber.StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openmrs.contrib.qa.selenium.Browse;
import org.openmrs.contrib.qa.selenium.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginSteps {
  private WebDriver driver;

  public LoginSteps() {
    //driver = new ChromeDriver();
    driver = Browse.driver(DriverProvider.CHROME);
    driver.manage().window().setSize(new Dimension(600, 320));
  }

  private void logout() {
    driver.findElement(By.linkText("Logout")).click();
  }

  private void correctLogin() {
    enterUsername("admin");
    enterPassword("Admin123");
  }

  private void login() {
    getLoginButton().click();
  }

  private WebElement getLoginButton() {
    return getElement(By.id("loginButton"));
  }

  private WebElement getElement(By elementBy) {
    try {
      return driver.findElement(elementBy);
    } catch (NoSuchElementException e) {
      return null;
    }
  }

  private void enterUsername(String username) {
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).sendKeys(username);
  }

  private void enterPassword(String password) {
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys(password);
  }

  private void elementClickOn(By elementBy) {
    driver.findElement(elementBy).click();
  }

  private void wrongLogin() {
    enterUsername("admin");
    enterPassword("WrongPassword");
  }

  private void initLoginPage() {
    driver.get("https://uat-refapp.openmrs.org/openmrs/login.htm");
  }

  private void quitBrowser() {
    driver.quit();
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

  @And("Close browser")
  public void closeBrowser() {
    quitBrowser();
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
}
