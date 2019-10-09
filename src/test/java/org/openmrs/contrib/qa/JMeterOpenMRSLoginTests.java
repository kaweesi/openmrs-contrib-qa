package org.openmrs.contrib.qa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openmrs.contrib.qa.jmeter.JmeterInitializer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
@SpringBootTest
public class JMeterOpenMRSLoginTests {

  @Test
  public void runJmeterScripts() throws IOException {
    // Load existing .jmx Test Plans
    for(File script: JmeterInitializer.getAvailableScripts()) {
      JmeterInitializer.runScript(script);
    }
  }

  @Test
  public void testLoadingOpenMRSOnJMeter() throws Exception {
    JmeterInitializer.loadOpenMRS();
  }

}
