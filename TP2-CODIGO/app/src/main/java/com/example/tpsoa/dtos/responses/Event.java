package com.example.tpsoa.dtos.responses;

public class Event {
    private String type_events;
    private String description;
    private int dni;
    private int id;

    public Event(String type_events, String description, int dni, int id) {
        this.type_events = type_events;
        this.description = description;
        this.dni = dni;
        this.id = id;
    }

    public String getType_events() {
        return type_events;
    }

    public void setType_events(String type_events) {
        this.type_events = type_events;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Event{" +
                "type_events='" + type_events + '\'' +
                ", description='" + description + '\'' +
                ", dni=" + dni +
                ", id=" + id +
                '}';
    }
}
