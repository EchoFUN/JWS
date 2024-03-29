package request;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import request.Request;
import response.Response;

public class RequestThread implements Runnable {

  Socket socket;
  InputStream input;
  OutputStream output;

  public RequestThread(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    try {
      long threadId = Thread.currentThread().getId();
      System.out.println("thread: " + threadId + " is working for the request .");

      input = socket.getInputStream();
      output = socket.getOutputStream();

      Request request = new Request(input);
      Response response = new Response(output);
      response.setRequest(request);
      response.sendDataByController();
      socket.close();
      System.out.println("thread: " + threadId + " is finished it's job .");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
