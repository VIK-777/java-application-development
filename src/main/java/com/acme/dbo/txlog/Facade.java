package com.acme.dbo.txlog;

public class Facade {
    private static void logToConsole(String message) {
        System.out.println(message);
    }

    private static void logPrimitive(Object message) {
        logToConsole("primitive: " + message);
    }

    public static void log(int message) {
        logPrimitive(message);
    }

    public static void log(boolean message) {
        logPrimitive(message);
    }

    public static void log(byte message) {
        logPrimitive(message);
    }

    public static void log(char message) {
        logToConsole("char: " + message);
    }

    public static void log(String message) {
        logToConsole("string: " + message);
    }

    public static void log(Object message) {
        logToConsole("reference: " + message);
    }
}
