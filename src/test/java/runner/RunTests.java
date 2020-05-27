package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/features",
    glue = {"runner", "stepDefinitions"},
    tags = "(@dev or @tess)",
    monochrome = true,
    strict = true,
    snippets = CucumberOptions.SnippetType.CAMELCASE,
    plugin = {
            "pretty",
            "summary",
            "json:target/cucumber-reports/cucumber.json",
            "de.monochromata.cucumber.report.PrettyReports:target/pretty-cucumber-report"
    }
)
public class RunTests { }
