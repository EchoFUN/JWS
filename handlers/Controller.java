package handlers;

import request.Request;
import response.Response;

public abstract class Controller {

  public abstract void process(Request request, Response response);
}
