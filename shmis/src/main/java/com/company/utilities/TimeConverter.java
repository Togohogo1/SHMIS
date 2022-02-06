package com.company.utilities;

public class TimeConverter {
    public static long strToLongTime(String strTime) {
        return 0;
    }

    public static String longToStrTime(long time) {
        return null;
    }

    public static int getStartRow(long time) {
        return (int)((time-540) / 30);
    }

    public static int getEndRow(long time) {
        return 0;
    }

    public static int getColumnIndex(String date) {
        switch (date) {
            case "Monday":
                return 0;
            case "Tuesday":
                return 1;
            case "Wednesday":
                return 2;
            case "Thursday":
                return 3;
            case "Friday":
                return 4;
        }

        return -1;
    }
}
