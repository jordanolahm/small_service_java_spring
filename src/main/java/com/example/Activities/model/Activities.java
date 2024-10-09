package com.example.Activities.model;

public class Activities {
    private Long id;
    private String description;
    private boolean complete;

    public Activities (Long id, String description, boolean complete) {
        this.id = id;
        this.description = description;
        this.complete = complete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String describe) {
        this.description = describe;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean completed) {
        this.complete = completed;
    }
}
