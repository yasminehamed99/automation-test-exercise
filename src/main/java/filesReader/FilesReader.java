package filesReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FilesReader {
public Properties readFromPropertyFile(String path)  {
    FileReader fileReader= null;
    Properties properties=null;
    try {
        fileReader = new FileReader(path);
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    }
     properties=new Properties();
    try {
        properties.load(fileReader);

    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    return properties;


}
}
