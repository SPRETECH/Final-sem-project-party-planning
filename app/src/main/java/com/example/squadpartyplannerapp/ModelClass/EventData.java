package com.example.squadpartyplannerapp.ModelClass;

public class EventData {
    String eventDate,eventStartTime,eventEndTime,eventImageURL,eventName;

    public EventData() {
    }

    public EventData(String eventDate, String eventStartTime, String eventEndTime, String eventImageURL, String eventName) {
        this.eventDate = eventDate;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventImageURL = eventImageURL;
        this.eventName = eventName;
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

    public String getEventImageURL() {
        return eventImageURL;
    }

    public String getEventName() {
        return eventName;
    }
}
