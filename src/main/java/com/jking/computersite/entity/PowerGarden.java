package com.jking.computersite.entity;

public class PowerGarden {
    private Integer id;

    private Integer major;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMajor() {
        return major;
    }

    public void setMajor(Integer major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "PowerGarden{" +
                "id=" + id +
                ", major=" + major +
                ", name='" + name + '\'' +
                '}';
    }
}