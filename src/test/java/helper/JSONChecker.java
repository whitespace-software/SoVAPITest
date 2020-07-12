package helper;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.List;

public class JSONChecker {
    IResponseString myBase;
    public JSONChecker(IResponseString base) {
        this.myBase = base;
    }

    public void jsonPathIs(String path, String value) {
        JsonPath js = new JsonPath( myBase.getLastResponse());
        String val = js.getString(path);
        Assert.assertEquals( val, value );
    }

    public void pathContains( String path, String value ) {
        JsonPath js = new JsonPath(myBase.getLastResponse());
        String val = js.getString(path);
        Assert.assertTrue( val.contains(value), value + " not in " + val );
    }

    public void jsonPathArrayLengthIsAtLeast(String path, String countString) {
        JsonPath js = new JsonPath(myBase.getLastResponse());
        int count = Integer.parseInt( countString );
        List<Object> list = js.getList(path);
        Assert.assertTrue( list.size() >= count, "Only " + list.size() + " items in " + path );
    }
    public void json_path_array_length_is(String path, String countString) {
        JsonPath js = new JsonPath(myBase.getLastResponse());
        int count = Integer.parseInt( countString );
        List<Object> list = js.getList(path);
        Assert.assertTrue( list.size() >= count, "Sorry, " + list.size() + " items in " + path );
    }

}
