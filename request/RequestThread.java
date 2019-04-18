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
            input = socket.getInputStream();
            output = socket.getOutputStream();

            // Handle the request actions .
            Request request = new Request(input);
            request.parse();

            // Handle the response actions .
            Response response = new Response(output);
            response.setRequest(request);


            response.sendDataByController();

            // response.sendStaticResource();

            socket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
