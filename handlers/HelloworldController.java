package handlers;

import request.Request;
import response.Response;

public class HelloworldController extends Controller {

    @Override
    public void process(Request request, Response response) {
        response.write("Hello,world!");
    }
}
