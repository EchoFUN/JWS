package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

import configrations.SysConf;

import static configrations.Constant.PASSWORD;
import static configrations.Constant.URL;
import static configrations.Constant.USERNAME;

public class DBUtils {

    private static Connection conn = null;

    public static Connection getConnection() throws Exception {
        if (conn == null) {
            Map<String, String> databaseInfo = SysConf.fetchDatabaseInfo();
            conn = DriverManager.getConnection(databaseInfo.get(URL), databaseInfo.get(USERNAME), databaseInfo.get(PASSWORD));
            return conn;
        }
        return conn;
    }
}


