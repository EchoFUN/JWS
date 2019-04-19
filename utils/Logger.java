package utils;

public class Logger {

    public static void error(Exception e) {
        e.printStackTrace();
    }

    public static void logger(String message) {
        System.out.println(message);
    }
}
