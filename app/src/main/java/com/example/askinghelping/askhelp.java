package com.example.askinghelping;

public class askhelp {
    private  String description;
    private  String location;
    private  String telNo;


    public askhelp() {
    }

    public askhelp(String description, String location, String telNo) {
        this.description = description;
        this.location = location;
        this.telNo = telNo;

    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getTelNo() {
        return telNo;
    }
}
