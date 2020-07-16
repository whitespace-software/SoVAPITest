package stepDefinitions;

import com.sun.xml.xsom.impl.scd.Step;
import config.Config;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import org.testng.Assert;

public class Risk extends StepBase {
    @Given("a token exists for {string}")
    public void aTokenExistsFor(String nickname) {
        Config config = new Config();
        token = config.getTokenForUser(nickname);
        System.out.println(token);  // sout
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
}
