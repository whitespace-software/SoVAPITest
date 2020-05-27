package stepDefinitions;

import apis.Version;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class HelloWorld extends StepBase {
    Version version = new Version();

    @And("print {string}")
    public void print(String text) {
        printLine(text);
    }

    @Then("get SOVAPI version")
    public void getSOVAPIVersion() {
        lastResponse = version.getVersion();
    }

    @Given("be Verbose")
    public void beVerbose() {
        verbose = true;
    }
}
