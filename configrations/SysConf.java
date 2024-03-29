package configrations;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static configrations.Constant.CONNECTION_POOL_SIZE;
import static configrations.Constant.DEVELOPMENT_ENV_FLAG;
import static configrations.Constant.PASSWORD;
import static configrations.Constant.PRODUCTION_ENV_FLAG;
import static configrations.Constant.URL;
import static configrations.Constant.USERNAME;

public class SysConf {

  public static String build = DEVELOPMENT_ENV_FLAG;

  public static int WEB_PORT = 8082;

  public static String WEB_ROOT = java.lang.System.getProperty("user.dir") + File.separator + "webroot";

  private static Map<String, String> databaseInfo = new HashMap<>();

  public static Map<String, String> fetchDatabaseInfo() {
    databaseInfo.put(URL, "jdbc:mysql://127.0.0.1:3306/yymg?useUnicode=true&characterEncoding=UTF-8");
    databaseInfo.put(USERNAME, "root");
    databaseInfo.put(PASSWORD, (build.equals(PRODUCTION_ENV_FLAG) ? "" : "123456"));

    databaseInfo.put(CONNECTION_POOL_SIZE, "100");
    return databaseInfo;
  }
}
