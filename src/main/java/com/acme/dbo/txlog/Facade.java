package com.acme.dbo.txlog;

import static java.lang.String.valueOf;

public class Facade {
    private static void logToConsole(String message) {
        System.out.println(message);
    }

    private static void logPrimitive(String message) {
        logToConsole("primitive: " + message);
    }

    public static void log(int message) {
        logPrimitive(valueOf(message));
    }

    public static void log(boolean message) {
        logPrimitive(valueOf(message));
    }

    public static void log(byte message) {
        logPrimitive(valueOf(message));
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
