package Data;


import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class LoaderCongFile {
    private static String token;
    private static String userName;

    public static void loadConfFile(){
        Properties loader = new Properties();
        try {
            File file = new File("C:\\Users\\golis\\Intelij IDEA\\Java\\Bot\\src\\Data\\telegram.conf");
            FileReader reader = new FileReader(file);
            loader.load(reader);
            token = loader.getProperty("token");
            userName = loader.getProperty("userName");
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static String getToken(){
        return token;
    }

    public static String getUserName(){
        return userName;
    }
}
