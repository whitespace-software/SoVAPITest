package stepDefinitions;

import helper.IResponseString;
import helper.JSONChecker;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class StepBase implements IResponseString {
    protected Response lastResponse;
    protected String lastURL;
    protected Boolean verbose = false;
    JSONChecker lastChecker = new JSONChecker(this);
    String token = "";

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


        lastResponse = given().contentType(ContentType.JSON).header("Authorization", "Bearer " + token ).when()
                .get(lastURL)
                .then().extract().response();
    }

    public String getLastResponse() {
        if( lastResponse == null )
            return "{}";
        else
            return lastResponse.asString();
    }

    protected String getString(String path) {
        JsonPath jsonPath = new JsonPath(lastResponse.asString());
        return jsonPath.getString(path);
    }
    protected List<Object> getElement (String path) {
        JsonPath jsonPath = new JsonPath(lastResponse.asString());
        return jsonPath.getList(path);
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
