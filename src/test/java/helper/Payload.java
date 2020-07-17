package helper;

import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Payload {
    public static String build(String filename, HashMap<String,String> map ) throws Throwable {
        String result = "";
        String jsonTemplate = getJsonResource( filename );
        result = replaceUsingHashmap(jsonTemplate, map);
        return result;
    }

    private static String replaceUsingHashmap(String template, HashMap<String, String> map) throws Throwable {
        String s = template;
        for (String key : map.keySet()) {
            s = s.replace( "[[" + key + "]]", map.get( key ) );
        }
        if (s.contains("[["))
            throw new Exception("ERROR in replaceUsingHashmap: spot the [[ in " + s );
        return s;
    }
    private static String getJsonResource(String filename ) {
        String pathToFile = "src/test/resources/json/" + filename;
        try {
            return new String(Files.readAllBytes(Paths.get(pathToFile)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return pathToFile;
        }
    }
}
