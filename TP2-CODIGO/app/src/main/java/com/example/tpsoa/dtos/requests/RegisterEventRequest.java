package com.example.tpsoa.dtos.requests;

public class RegisterEventRequest {
    private String env;
    private String type_events;
    private String description;

    public RegisterEventRequest(String typeEvents, String description) {
        this.env = "PROD";
        this.type_events = typeEvents;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType_events() {
        return type_events;
    }

    public void setType_events(String type_events) {
        this.type_events = type_events;
    }
}
