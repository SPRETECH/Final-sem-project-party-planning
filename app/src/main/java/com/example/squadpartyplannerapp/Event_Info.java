package com.example.squadpartyplannerapp;

public class Event_Info {
    String hostID,eventImage,eventName,eventDate,eventStartTime,eventEndTime,eventPlace,eventType,eventInstruction,noOfGuest,extras;


    public Event_Info(String hostID,String url, String eventName, String eventDate, String eventStartTime, String eventEndTime, String eventPlace, String eventType, String eventInstruction, String noOfGuest, String extras)
    {
        this.hostID = hostID;
        eventImage = url;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventPlace = eventPlace;
        this.eventType = eventType;
        this.eventInstruction = eventInstruction;
        this.noOfGuest = noOfGuest;
        this.extras = extras;
    }

    public String getHostID() {
        return hostID;
    }

    public String getEventImage() {
        return eventImage;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public String getEventEndTime() {
        return eventEndTime;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public String getEventType() {
        return eventType;
    }

    public String getEventInstruction() {
        return eventInstruction;
    }

    public String getNoOfGuest() {
        return noOfGuest;
    }

    public String getExtras() {
        return extras;
    }
}
