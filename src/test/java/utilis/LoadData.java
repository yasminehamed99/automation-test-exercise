package utilis;

import filesReader.FilesReader;

import java.util.Properties;

public class LoadData {
    public static String url;
    public static String email;
    public static String password;
    public static void loadData(){
        FilesReader filesReader=new FilesReader();
        Properties properties= filesReader.readFromPropertyFile("src/test/java/configData/configData.properties");
         url=properties.getProperty("WEB_URL");
         email=properties.getProperty("USER_EMAIL");
         password=properties.getProperty("USER_PASSWORD");
    }



}
