package org.openmrs.contrib.qa.cucumber.StepDefinitions;

import org.openmrs.contrib.qa.QATool;
import org.openmrs.contrib.qa.selenium.Browse;
import org.openmrs.contrib.qa.selenium.DriverProvider;
import org.openmrs.contrib.qa.selenium.QAProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.util.StringUtils;

import java.util.Properties;

public class Steps {
  protected static WebDriver driver;
  protected static String parentUrl;

  protected void elementClickOn(By elementBy) {
    driver.findElement(elementBy).click();
  }

  protected WebElement getElement(By elementBy) {
    try {
      return driver.findElement(elementBy);
    } catch (NoSuchElementException e) {
      return null;
    }
  }

  protected void quitBrowser() {
    driver.quit();
    driver = null;
  }

  protected void browseUrl(String path) {
    Properties props = QATool.getQAProperties();
    if(driver == null) {
      driver = Browse.driver(DriverProvider.valueOf(props.getProperty(QAProperties.BROWSER)));
    }
    if(StringUtils.isEmpty(parentUrl)) {
      parentUrl = props.getProperty(QAProperties.URL);
    }
    driver.get(parentUrl + path);
  }

}
