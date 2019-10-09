package org.openmrs.contrib.qa.selenium;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class QAProperties {
  public static String PROPERTIES_FILE = "qa.properties";
  public static String URL = "root.url";
  public static String USER = "username";
  public static String PASS = "password";

  public static Properties loadQAProperties() {
    Properties prop = new Properties();
    try (InputStream props = QAProperties.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
      prop.load(props);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return prop;
  }
}
