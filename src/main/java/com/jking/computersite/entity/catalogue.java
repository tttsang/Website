package com.jking.computersite.entity;

import java.util.List;

public class catalogue {
    private Integer id;

    private Integer arId;

    private String fLevel;

    private String sLevel;

    private List<news> list;

    public List<news> getList() {
        return list;
    }

    public void setList(List<news> list) {
        this.list = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArId() {
        return arId;
    }

    public void setArId(Integer arId) {
        this.arId = arId;
    }

    public String getfLevel() {
        return fLevel;
    }

    public void setfLevel(String fLevel) {
        this.fLevel = fLevel == null ? null : fLevel.trim();
    }

    public String getsLevel() {
        return sLevel;
    }

    public void setsLevel(String sLevel) {
        this.sLevel = sLevel == null ? null : sLevel.trim();
    }
}