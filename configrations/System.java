package configrations;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static configrations.Constant.URL;
import static configrations.Constant.PASSWORD;
import static configrations.Constant.USERNAME;

public class System {


    public static int WEB_PORT = 8080;

    public static String WEB_ROOT = java.lang.System.getProperty("user.dir") + File.separator + "webroot";


    private static Map<String, String> databaseInfo = new HashMap<>();

    public static Map<String, String> fetchDatabaseInfo() {
        databaseInfo.put(URL, "jdbc:mysql://localhost:3306/yymg?useUnicode=true&characterEncoding=UTF-8");
        databaseInfo.put(USERNAME, "root");
        databaseInfo.put(PASSWORD, "123456");

        return databaseInfo;
    }
}
