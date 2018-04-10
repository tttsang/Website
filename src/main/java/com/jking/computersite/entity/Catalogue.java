package com.jking.computersite.entity;

public class Catalogue {
    private String id;

    private String firstLevel;

    private String secondLevel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFirstLevel() {
        return firstLevel;
    }

    public void setFirstLevel(String firstLevel) {
        this.firstLevel = firstLevel == null ? null : firstLevel.trim();
    }

    public String getSecondLevel() {
        return secondLevel;
    }

    public void setSecondLevel(String secondLevel) {
        this.secondLevel = secondLevel == null ? null : secondLevel.trim();
    }
}