package org.openmrs.contrib.qa.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"html:target/cucumber-html-report"}, snippets = SnippetType.CAMELCASE,tags = {"@single"}, features = "src/test/resources/features")
public class Launcher { }
