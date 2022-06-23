package com.example.tpsoa.dtos.responses;

public class RegisterEventResponse {
    private boolean success;
    private String env;
    private Event event;

    public RegisterEventResponse(boolean success, String env, Event event) {
        this.success = success;
        this.env = env;
        this.event = event;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "EventResponse{" +
                "success=" + success +
                ", env='" + env + '\'' +
                ", event=" + event.toString() +
                '}';
    }
}
