package handlers;

import request.Request;
import response.Response;

public class HelloworldController extends Controller {

    @Override
    public void process(Request request, Response response) {
        response.write("HTTP/1.1 200 OK\n" +
                "Content-Type: text/plain; charset=utf-8\n" +
                "Content-Length: 9\n" +
                "Date: Thu, 18 Apr 2019 08:27:58 GMT\n" +
                "Connection: keep-alive" +
                "\n" +
                "Not found");
    }
}
