package tests.nio;


import static tests.nio.Server.PORT;

public class Main {


    public static void main(String[] args) {

        try {
            System.out.println("");

            Server server = new Server(PORT);
            server.listen();
        } catch (Exception except) {
            except.printStackTrace();
        }
    }
}
