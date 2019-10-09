package org.openmrs.contrib.qa.selenium;

import org.openmrs.contrib.qa.QATool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.util.StringUtils;

public class Browse extends QATool {

  public static WebDriver driver(DriverProvider driverProvider) {
    if(DriverProvider.CHROME.equals(driverProvider)) {
      System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
      return new ChromeDriver();
    } else {
      System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
      return new FirefoxDriver();
    }
  }

  public static WebDriver loadRootURL() {
    WebDriver driver = driver(DriverProvider.FIREFOX);
    driver.get(getQAProperties().getProperty(QAProperties.URL));
    return driver;
  }

  public static WebDriver referenceApplicationLogin(String user, String pass) {
    WebDriver driver = driver(DriverProvider.FIREFOX);
    driver.get(getQAProperties().getProperty(QAProperties.URL) + "/login.htm");
    //select session location
    driver.findElement(By.id("Pharmacy")).click();
    driver.findElement(By.id("username")).sendKeys(StringUtils.isEmpty(user) ? getQAProperties().getProperty(QAProperties.USER) : user);
    driver.findElement(By.id("password")).sendKeys(StringUtils.isEmpty(pass) ? getQAProperties().getProperty(QAProperties.PASS): pass);
    driver.findElement(By.id("loginButton")).click();
    return driver;
  }

  public static WebDriver legacyLogin(String user, String pass) {
    WebDriver driver = driver(DriverProvider.FIREFOX);
    driver.get("http://localhost:8083/openmrs-standalone/");
    driver.findElement(By.id("username")).sendKeys(StringUtils.isEmpty(user) ? getQAProperties().getProperty(QAProperties.USER) : user);
    driver.findElement(By.id("password")).sendKeys(StringUtils.isEmpty(pass) ? getQAProperties().getProperty(QAProperties.PASS): pass);
    driver.findElement(By.cssSelector("input[type=submit]")).click();
    return driver;
  }

  public static WebDriver runRecordedScript() {
    WebDriver driver = driver(DriverProvider.FIREFOX);
    new FluentWait<>(driver)
            .ignoring(WebDriverException.class)
            .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
    return driver;
  }
}
