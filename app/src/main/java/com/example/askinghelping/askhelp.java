package com.example.askinghelping;

public class askhelp {
    private  String description;
    private  String location;
    private  String telNo;
    private String id;


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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
