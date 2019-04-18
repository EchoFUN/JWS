package app;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import request.RequestThread;
import utils.Logger;

import static configrations.System.WEB_PORT;

class Service {

    public static void main(String[] args) {
        (new Service()).await();
    }

    public void await() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(WEB_PORT, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException except) {
            Logger.error(except);
            System.exit(1);
        }

        while (true) {
            Socket socket;
            try {
                socket = serverSocket.accept();
                new Thread(new RequestThread(socket)).start();
            } catch (Exception except) {
                Logger.error(except);
                continue;
            }
        }
    }
}



