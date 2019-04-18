package handlers;

import request.Request;
import response.Response;

public class NotFoundController extends Controller {

    @Override
    public void process(Request request, Response response) {
        response.write("Request 404 Found !");
    }
}
