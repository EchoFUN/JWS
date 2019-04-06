import java.net.Socket;

public class RequestThread implements Runnable {

    Socket socket;

    RequestThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

    }
}
