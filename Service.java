import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

class Service {

    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    public static void main(String[] args) {
        (new Service()).await();
    }

    public void await() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
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

                Request request = new Request(input);
                request.parse();
                if (request.getUri().equals(SHUTDOWN_COMMAND)) {
                    break;
                }

                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();

                socket.close();

            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}



