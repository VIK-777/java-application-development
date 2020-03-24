package com.acme.dbo.txlog;

import static java.lang.String.valueOf;

public class Facade {
    private static messageType currentType = messageType.UNDEFINED;
    private static int currentInt;
    private static byte currentByte;
    private static int stringCounter;
    private static String currentString;

    public static void log(int message) {
        if (currentType != messageType.INT) {
            flush();
            currentType = messageType.INT;
        }
        if (isOverflow(currentInt, message)) {
            flush();
            currentInt = 0;
        }
        currentInt += message;
    }

    public static void log(boolean message) {
        logPrimitive(valueOf(message));
    }

    public static void log(byte message) {
        if (currentType != messageType.BYTE) {
            flush();
            currentType = messageType.BYTE;
        }
        if (isOverflow(currentByte, message)) {
            flush();
            currentByte = (byte) 0;
        }
        currentByte += message;
    }

    public static void log(char message) {
        logToConsole("char: " + message);
    }

    public static void log(String message) {
        if (currentType != messageType.STRING) {
            flush();
            stringCounter = 0;
            currentType = messageType.STRING;
        }
        if (!message.equals(currentString)) {
            flush();
            stringCounter = 0;
        }
        currentString = message;
        stringCounter++;
    }

    public static void log(Object message) {
        logToConsole("reference: " + message);
    }

    private static void logToConsole(String message) {
        System.out.println(message);
    }

    private static void logPrimitive(String message) {
        logToConsole("primitive: " + message);
    }

    private static void logString(String message) {
        logToConsole("string: " + message);
    }

    private static boolean isOverflow(int baseValue, int valueToCheck) {
        return valueToCheck > 0 && baseValue + valueToCheck < baseValue || valueToCheck < 0 && baseValue + valueToCheck > baseValue;
    }

    private static boolean isOverflow(byte baseValue, byte valueToCheck) {
        return valueToCheck > 0 && (byte) (baseValue + valueToCheck) < baseValue
                || valueToCheck < 0 && (byte) (baseValue + valueToCheck) > baseValue;
    }

    private static boolean isOverflow(byte valueToCheck) {
        return valueToCheck < (byte) 0;
    }

    public static void flush() {
        switch (currentType) {
            case INT:
                logPrimitive(String.valueOf(currentInt));
                currentInt = 0;
                break;
            case BYTE:
                logPrimitive(String.valueOf(currentByte));
                currentByte = (byte) 0;
                break;
            case STRING:
                if (currentString != null) {
                    logString(currentString + (stringCounter > 1 ? " (x" + stringCounter + ")" : ""));
                }
                currentString = null;
                break;
            default:
                break;
        }
    }

    private enum messageType {
        UNDEFINED,
        INT,
        BYTE,
        STRING
    }
}
