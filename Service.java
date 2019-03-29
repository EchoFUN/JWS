import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

class Service {

    public static int REQUEST_PORT = 8080;
    public static String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    public static void main(String[] args) {
        (new Service()).await();
    }

    public void await() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(REQUEST_PORT, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException except) {
            Logger.error(except);
            System.exit(1);
        }

        while (true) {
            Socket socket;
            InputStream input;
            OutputStream output;
            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                // Handle the request actions .
                Request request = new Request(input);
                request.parse();

                // Handle the response actions .
                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();

                socket.close();
            } catch (Exception except) {
                Logger.error(except);
                continue;
            }
        }
    }
}



