package configrations;

import java.io.File;

public class System {


    public static int WEB_PORT = 8080;

    public static String WEB_ROOT = java.lang.System.getProperty("user.dir") + File.separator + "webroot";
}
