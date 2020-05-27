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

    @Before("@tess")
    public void tessEnv() {
        config.setEnv("tess");
        RestAssured.baseURI = config.getBaseUrl();
    }

    @Before("@staging")
    public void stagingEnv() {
        config.setEnv("staging");
        RestAssured.baseURI = config.getBaseUrl();
    }
}
