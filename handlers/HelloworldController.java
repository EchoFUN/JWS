package handlers;

import java.sql.Connection;

import database.DBUtils;
import request.Request;
import response.Response;
import utils.Logger;

public class HelloworldController extends Controller {

    @Override
    public void process(Request request, Response response) {

        Connection connection;
        try {
            connection = DBUtils.getConnection();

            if (connection != null) {
                Logger.logger("yes!");
            } else {
                Logger.logger("no!");
            }
        } catch (Exception e) {
            Logger.error(e);
        }

        response.write("Hello,world!");
    }
}
