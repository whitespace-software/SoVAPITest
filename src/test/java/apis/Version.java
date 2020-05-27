package apis;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import stepDefinitions.StepBase;

public class Version extends StepBase {

    public Response getVersion() {
        lastURL = "/sovapi/version";
        getResponse();
        checkStatusCodeIs(200);
        checkContentTypeIs(ContentType.JSON);
        printLast();
        return lastResponse;
    }
}
