package org.openmrs.contrib.qa;// Generated by Selenium IDE

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.contrib.qa.selenium.Browse;
import org.openmrs.contrib.qa.selenium.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

//Exported from selenium ide (extension) to java test from src/test/resources/openmrs.slide
public class ChromeLoginAndOutTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    //driver = new ChromeDriver();
    driver = Browse.driver(DriverProvider.CHROME);
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void loginAndOut() {
    driver.get("https://demo.openmrs.org/openmrs/login.htm");
    driver.manage().window().setSize(new Dimension(1280, 773));
    driver.findElement(By.id("username")).sendKeys("admin");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("Admin123");
    driver.findElement(By.id("Pharmacy")).click();
    driver.findElement(By.id("loginButton")).click();
    driver.findElement(By.linkText("Logout")).click();
    driver.findElement(By.id("username")).sendKeys("admin");
    driver.findElement(By.cssSelector(".left:nth-child(3)")).click();
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("Admin");
    driver.findElement(By.id("Pharmacy")).click();
    driver.findElement(By.id("loginButton")).click();
    //driver.findElement(By.cssSelector("html")).click();
    //driver.findElement(By.cssSelector("html")).click();
  }
}