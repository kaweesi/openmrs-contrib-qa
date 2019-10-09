package org.openmrs.contrib.qa.jmeter;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.openmrs.contrib.qa.QATool;
import org.openmrs.contrib.qa.selenium.QAProperties;

import java.io.File;
import java.io.IOException;

public class JmeterInitializer extends QATool {
  public static String JMETER_HOME = "drivers/apache-jmeter-5.1.1";

  private static String JMETER_RESULTS_FILE = "target/jmeter/%s.jtl";

  private static StandardJMeterEngine engine() throws IOException {
    // JMeter Engine
    StandardJMeterEngine jmeter = new StandardJMeterEngine();

    // Initialize Properties, locale, etc.
    JMeterUtils.loadJMeterProperties(JmeterInitializer.class.getClassLoader().getResource(QAProperties.PROPERTIES_FILE).getPath());
    JMeterUtils.setJMeterHome(JMETER_HOME);
    JMeterUtils.initLocale();
    return jmeter;
  }

  public static File[] getAvailableScripts() {
    return new File(JmeterInitializer.class.getClassLoader().getResource("jmeter").getPath()).listFiles();
  }

  private static void logResult(HashTree testPlanTree, String id) {
    Summariser summer = null;
    String summariserName = JMeterUtils.getPropDefault("summariser.name", id);//$NON-NLS-1$
    if (summariserName.length() > 0) {
      summer = new Summariser(summariserName);
    }

    ResultCollector logger = new ResultCollector(summer);
    logger.setFilename(String.format(JMETER_RESULTS_FILE, id));
    testPlanTree.add(testPlanTree.getArray()[0], logger);
  }

  public static void runScript(File script) throws IOException {
    StandardJMeterEngine jmeter = engine();
    // Initialize JMeter SaveService
    SaveService.loadProperties();
    HashTree testPlanTree = SaveService.loadTree(script);
    // Run JMeter Test
    jmeter.configure(testPlanTree);
    logResult(testPlanTree, script.getName().substring(0 , script.getName().indexOf(".")));
    jmeter.run();
  }

  public static void loadOpenMRS() throws IOException {
    StandardJMeterEngine jmeter = engine();
    // JMeter Test Plan, basic all u JOrphan HashTree
    HashTree testPlanTree = new HashTree();

    // HTTP Sampler
    HTTPSampler httpSampler = new HTTPSampler();
    httpSampler.setDomain(getQAProperties().getProperty(QAProperties.URL));
    httpSampler.setPort(80);
    httpSampler.setPath("/");
    httpSampler.setMethod("GET");

    // Loop Controller
    LoopController loopController = new LoopController();
    loopController.setLoops(1);
    loopController.addTestElement(httpSampler);
    loopController.setFirst(true);
    loopController.initialize();

    // Thread Group
    ThreadGroup threadGroup = new ThreadGroup();
    threadGroup.setNumThreads(1);
    threadGroup.setRampUp(1);
    threadGroup.setSamplerController(loopController);

    // Test Plan
    TestPlan testPlan = new TestPlan("Load OpenMRS");

    // Construct Test Plan from previously initialized elements
    testPlanTree.add("testPlan", testPlan);
    testPlanTree.add("loopController", loopController);
    testPlanTree.add("threadGroup", threadGroup);
    testPlanTree.add("httpSampler", httpSampler);

    // Run Test Plan
    jmeter.configure(testPlanTree);
    logResult(testPlanTree, "openmrsLoadWithAPI");
    jmeter.run();
  }
}