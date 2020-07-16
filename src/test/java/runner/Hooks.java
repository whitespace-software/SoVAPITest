package runner;

import config.Config;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks {
    Config config = new Config();

    @Before("@dev")
    public void devEnv() {
        config.setEnv("dev");
        RestAssured.baseURI = config.getBaseUrl();
    }

}
