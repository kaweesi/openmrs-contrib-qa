package org.openmrs.contrib.qa.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        glue = "org.openmrs.contrib.qa.cucumber.StepDefinitions")
public class CucumberTest {}
