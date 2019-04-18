package handlers;

import request.Request;
import response.Response;

public class NotFoundController extends Controller {

    @Override
    public void process(Request request, Response response) {
        response.write("HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html; charset=utf-8\r\n" +
                "Content-Length: 23\r\n" +
                "Date: Thu, 18 Apr 2019 08:27:58 GMT\r\n" +
                "Connection: keep-alive\r\n" +
                "\r\n" +
                "<h1>File Not Found</h1>");

    }
}
