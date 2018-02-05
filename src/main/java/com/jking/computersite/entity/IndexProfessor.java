package com.jking.computersite.entity;

public class IndexProfessor {
    private Integer id;

    private String pictureurl;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPictureurl() {
        return pictureurl;
    }

    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl == null ? null : pictureurl.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "IndexProfessor{" +
                "id=" + id +
                ", pictureurl='" + pictureurl + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}