package database;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import configrations.SysConf;
import utils.Logger;

import static configrations.Constant.CONNECTION_POOL_SIZE;
import static configrations.Constant.PASSWORD;
import static configrations.Constant.URL;
import static configrations.Constant.USERNAME;

public class DBUtils {

  public static ComboPooledDataSource dataSource;

  public static DBUtils dbUtils;

  public static DBUtils inst() {
    if (dbUtils == null) {
      dbUtils = new DBUtils();
    }
    return dbUtils;
  }

  public void init() {
    dataSource = new ComboPooledDataSource();

    Map<String, String> databaseInfo = SysConf.fetchDatabaseInfo();
    dataSource.setJdbcUrl(databaseInfo.get(URL));
    dataSource.setUser(databaseInfo.get(USERNAME));
    dataSource.setPassword(databaseInfo.get(PASSWORD));

    dataSource.setMaxPoolSize(Integer.valueOf(databaseInfo.get(CONNECTION_POOL_SIZE)));
  }

  public static void releaseConnection(Connection connection) {
    try {
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }
    } catch (SQLException e) {
      Logger.error(e);
    }
  }


  public static Connection getConnection() throws Exception {

    /**
     * Get the connection from the database connection Pool .
     *
     *
     *
     *
     */
    return dataSource.getConnection();


    /**
     * Simple way of use the database connection .
     *
     *
     *
     *
     */

        /*
        if (conn == null) {
            conn = DriverManager.getConnection(databaseInfo.get(URL), databaseInfo.get(USERNAME), databaseInfo.get(PASSWORD));
            return conn;
        }
        return conn;
         */

  }
}


