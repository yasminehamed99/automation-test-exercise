package filesManager;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReaderFromFiles {
    public static Properties getProperties(String fileName){
        Properties fileProperties = null;
        try {
            FileReader fileReader = new FileReader( "src/main/resources/config/" + fileName) ;
            fileProperties=new Properties();
            fileProperties.load(fileReader);
        }
        catch (IOException ioException)
        {
            throw new RuntimeException(ioException);
        }
        return fileProperties;
    }
    public static String getPropertyByKey(String fileName, String propertyKey) {
        Properties fileProperties = getProperties (fileName);
        return fileProperties.getProperty(propertyKey);
    }
    public static JSONObject getJsonObject(String fileName)
    {
        JSONParser parser = new JSONParser();
        Object obj = null ;
        try {
            obj = parser.parse(new FileReader("./src/test/java/resources/testData/" + fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;
    }
    public static String getJsonValueByKey(String fileName, String jsonKey) {
        JSONObject jsonObject = getJsonObject(fileName);
        String value = (String) jsonObject.get(jsonKey);
        return value;
    }
}
