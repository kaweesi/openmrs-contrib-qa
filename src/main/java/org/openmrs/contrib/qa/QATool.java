package org.openmrs.contrib.qa;

import org.openmrs.contrib.qa.selenium.QAProperties;

import java.util.Properties;

public class QATool {
  private static Properties qaProperties;

  public static Properties getQAProperties() {
    if(qaProperties != null) {
      return qaProperties;
    } else {
      qaProperties = QAProperties.loadQAProperties();
      return qaProperties;
    }
  }
}
