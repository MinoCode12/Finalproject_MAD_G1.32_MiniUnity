package com.example.askinghelping;

public class askhelp {
    private static String description;
    private static String loaction;
    private static String telNo;


    public askhelp() {
    }

    public askhelp(String description, String loaction, String telNo) {
        this.description = description;
        this.loaction = loaction;
        this.telNo = telNo;

    }

    public static String getDescription() {
        return description;
    }

    public static String getLoaction() {
        return loaction;
    }

    public static String getTelNo() {
        return telNo;
    }
}
