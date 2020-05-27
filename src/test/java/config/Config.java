package config;

import io.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class Config {
    private final String FILE_NAME = "config.json";
    private final String PATH_TO_FILE = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "config").toString();

    private static String currentEnvironment;
    private final JSONObject jsonObject = getObject(PATH_TO_FILE, FILE_NAME);
    private final JsonPath jsonPath = new JsonPath(jsonObject.toString());

    public void setEnv(String env) {
        currentEnvironment = env;
        jsonPath.getString(env);
    }

    public String getCurrentEnvironment() {
        return currentEnvironment;
    }

    public String getBaseUrl() {
        String path = String.format("%s.host", currentEnvironment);
        return jsonPath.getString(path);
    }

    public String getResource() {
        String path = String.format("%s.resource", currentEnvironment);
        return jsonPath.getString(path);
    }

    public String getToken( String user ) {
        String path = String.format("%s.tokens.%s", currentEnvironment, user);
        return jsonPath.getString(path);
    }

    private static JSONObject getObject(String path, String filename) {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        String pathToFile = Paths.get(path, filename).toString();

        try (FileReader fileReader = new FileReader(pathToFile)) {
            jsonObject = (JSONObject) jsonParser.parse(fileReader);
        } catch ( IOException | ParseException e ) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
