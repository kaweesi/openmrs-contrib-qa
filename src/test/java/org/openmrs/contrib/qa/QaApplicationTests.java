package org.openmrs.contrib.qa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openmrs.contrib.qa.selenium.Browse;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QaApplicationTests {

  private WebDriver login(WebDriver loginIntoOpenMRS) {
    new FluentWait<>(loginIntoOpenMRS)
            .ignoring(WebDriverException.class)
            .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
    return loginIntoOpenMRS;
  }

  private WebDriver wrongLogin(WebDriver loginIntoOpenMRS, By element) {
    login(loginIntoOpenMRS);
    loginIntoOpenMRS.findElement(element);
    endWebDriver(loginIntoOpenMRS);
    return loginIntoOpenMRS;
  }

  private WebDriver rightLogin(WebDriver loginIntoOpenMRS, By element) {
    login(loginIntoOpenMRS);
    loginIntoOpenMRS.findElement(element);
    endWebDriver(loginIntoOpenMRS);
    return loginIntoOpenMRS;
  }

  private void endWebDriver(WebDriver webDriver) {
    webDriver.quit();
  }

  @Test
  public void testLoadingOpenMRSOnSelenium() throws Exception {
    WebDriver loadOpenMRS = Browse.loadRootURL();
    new FluentWait<>(loadOpenMRS)
            .ignoring(WebDriverException.class)
            .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
    loadOpenMRS.findElement(By.id("cantLogin"));
    endWebDriver(loadOpenMRS);
  }

  @Test
  public void testSuccessfulOpenMRSLoginOnSelenium() throws Exception {
    WebDriver loginIntoOpenMRS = Browse.referenceApplicationLogin(null, null);
    rightLogin(loginIntoOpenMRS, By.className("logout"));
  }

  @Test
  public void testSuccessfulOpenMRSLegacyLoginOnSelenium() throws Exception {
    WebDriver loginIntoOpenMRS = Browse.legacyLogin("admin", "eSaude123");
    rightLogin(loginIntoOpenMRS, By.id("userLogout"));
  }

  @Test
  public void testFailedOpenMRSLoginOnSelenium() throws Exception {
    WebDriver loginIntoOpenMRS = Browse.referenceApplicationLogin(null, "wrongPassword");
    wrongLogin(loginIntoOpenMRS, By.id("cantLogin"));
  }

  @Test
  public void testFailedOpenMRSLegacyLoginOnSelenium() throws Exception {
    WebDriver loginIntoOpenMRS = Browse.legacyLogin(null, "wrongPassword");
    wrongLogin(loginIntoOpenMRS, By.className("forgotPasswordLink"));
  }

  @Test(expected = TimeoutException.class)
  public void testLockOutUserOnWrongOpenMRSLegacyLoginOnSelenium() throws TimeoutException {
    WebDriver loginIntoOpenMRS = Browse.legacyLogin(null, "wrongPassword");
    for(int i = 0; i < 10 ; i++) {
      wrongLogin(loginIntoOpenMRS, By.className("forgotPasswordLink"));
    }
    endWebDriver(loginIntoOpenMRS);
  }

}
