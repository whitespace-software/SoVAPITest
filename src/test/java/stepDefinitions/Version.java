package stepDefinitions;

import config.Config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Version extends StepBase {

    public Response getVersion() {
        lastURL = "/version";
        getResponse();
        checkStatusCodeIs(200);
        checkContentTypeIs(ContentType.JSON);
        printLast();
        return lastResponse;
    }

    @Then("check SOVAPI version")
    public void checkSOVAPIVersion() {
        getVersion();
        lastChecker.pathContains("buildDate", "2020-");
        printLine( "buildDate = " + getString("buildDate") );
    }

    @Given("Verbose Version")
    public void beVerbose() {
        verbose = true;
    }

    @And("set environment")
    public void setEnvironment() {
        RestAssured.baseURI = new Config().getBaseUrl();
    }
}