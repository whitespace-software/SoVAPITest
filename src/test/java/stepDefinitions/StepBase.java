package stepDefinitions;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class StepBase {
    protected Response lastResponse;
    protected String lastURL;
    protected Boolean verbose = false;

    protected void checkStatusCodeIs(int code) {
        try {
            lastResponse.then().assertThat().statusCode(code);
        } catch (Error error) {
            System.out.println("lastURL " + lastURL);
            if( lastResponse != null ) {
                System.out.println("status code " + lastResponse.statusCode());
                System.out.println("lastResponse " + lastResponse.asString());
            }
            throw error;
        }
    }

    protected void checkContentTypeIs(ContentType type) {
        lastResponse.then().assertThat().contentType(type);
    }

    protected void getResponse() {
        lastResponse = given().contentType(ContentType.JSON).when()
                .get(lastURL)
                .then().extract().response();
    }

    protected void printLast() {
        if (verbose) {
            System.out.println("lastURL " + lastURL);
            if( lastResponse != null ) {
                System.out.println("lastResponse code " + lastResponse.statusCode());
                System.out.println("lastResponse " + lastResponse.asString());
            }
        }
    }

    protected void printLine(String str) {
        if(verbose)
            System.out.println( str + "\n");
    }

    protected void printMessages() {
        if(verbose)
            System.out.println(lastURL + " response: "+ lastResponse.asString());
    }
}
