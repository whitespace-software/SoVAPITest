package stepDefinitions;

import Utilities.TimeStampGenerator;
import config.Config;
import helper.Payload;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Risk extends StepBase {
    String payload;

    @Given("a token exists for {string}")
    public void aTokenExistsFor(String nickname) {
        Config config = new Config();
        token = config.getTokenForUser(nickname);
        RestAssured.baseURI = config.getBaseUrl();      // to avoid need for hooks.java
        Assert.assertNotNull( token );
    }

    @Then("the risk GET endpoint returns a list")
    public void theRiskGETEndpointReturnsAList() {
        lastURL = "/risk";
        getResponse();
        checkStatusCodeIs(200);
        checkContentTypeIs(ContentType.JSON);
        printLast();
        lastChecker.pathContains("[0].id", "SV");
        lastChecker.jsonPathIs("[0].type", "SVRisk");
    }

    @And("create a newrisk payload for {string}")
    public void createANewriskPayloadFor(String contractName) throws Throwable {
        HashMap<String, String> map = new HashMap<>();
        map.put("contractName", contractName + TimeStampGenerator.make(" HH:mm:ss"));
        payload = Payload.build("newrisk.json", map );
    }

    @Then("calling the risk POST endpoint returns a valid id")
    public void callingTheRiskPOSTEndpointReturnsAValidId() {
        String id;
        lastURL = "/risk";
        postPayload( payload );
        //printLast();
        id = getString("id");
        System.out.println("New Risk ID is " + id );
        Assert.assertTrue( id.startsWith("SV"));

    }

    @And("be verbose")
    public void beVerbose() {
        verbose = true;
    }
}
