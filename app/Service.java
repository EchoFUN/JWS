package app;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import configrations.RequestConf;
import configrations.SysConf;
import request.RequestThread;
import utils.ArrayUtils;
import utils.Logger;

import static configrations.Constant.PRODUCTION_ENV_FLAG;
import static configrations.SysConf.WEB_PORT;

class Service {

    public static void main(String[] args) {
        initConfiguration(args);
        intiService();
        initServiceListener();
    }

    private static void initServiceListener() {
        await();
    }

    private static void initConfiguration(String[] args) {
        if (ArrayUtils.contains(args, PRODUCTION_ENV_FLAG)) {
            SysConf.build = PRODUCTION_ENV_FLAG;
        }
    }

    public static void intiService() {
        RequestConf.inst().init();
    }

    public static void await() {
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



