package org.openmrs.contrib.qa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.util.StringUtils;

import java.util.Properties;

public class Browse {

  private static Properties qaProperties;

  private static Properties getQAProperties() {
    if(qaProperties != null) {
      return qaProperties;
    } else {
      qaProperties = QAProperties.loadQAProperties();
      return qaProperties;
    }
  }

  private static WebDriver driver() {
    if(getQAProperties().getProperty(QAProperties.PROP_WEBDRIVER).indexOf("chromedriver") >= 1) {
      System.setProperty("webdriver.gecko.driver", "drivers/chromedriver");
      return new ChromeDriver();
    } else {
      System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
      return new FirefoxDriver();
    }
  }

  public static WebDriver loadRootURL() {
    WebDriver driver = driver();
    driver.get(getQAProperties().getProperty(QAProperties.URL));
    return driver;
  }

  public static WebDriver referenceApplicationLogin(String user, String pass) {
    WebDriver driver = driver();
    driver.get(getQAProperties().getProperty(QAProperties.URL) + "/openmrs/login.htm");
    //select session location
    driver.findElement(By.id("Pharmacy")).click();
    driver.findElement(By.id("username")).sendKeys(StringUtils.isEmpty(user) ? getQAProperties().getProperty(QAProperties.USER) : user);
    driver.findElement(By.id("password")).sendKeys(StringUtils.isEmpty(pass) ? getQAProperties().getProperty(QAProperties.PASS): pass);
    driver.findElement(By.id("loginButton")).click();
    return driver;
  }

  public static WebDriver legacyLogin(String user, String pass) {
    WebDriver driver = driver();
    driver.get("http://localhost:8083/openmrs-standalone/");
    driver.findElement(By.id("username")).sendKeys(StringUtils.isEmpty(user) ? getQAProperties().getProperty(QAProperties.USER) : user);
    driver.findElement(By.id("password")).sendKeys(StringUtils.isEmpty(pass) ? getQAProperties().getProperty(QAProperties.PASS): pass);
    driver.findElement(By.cssSelector("input[type=submit]")).click();
    return driver;
  }
}
