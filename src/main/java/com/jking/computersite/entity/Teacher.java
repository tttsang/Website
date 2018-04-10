package com.jking.computersite.entity;

public class Teacher {
    private Integer id;

    private String name;

    private String teacherarea;

    private String teacherpic;

    private String achievement;

    private String zhicheng;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTeacherarea() {
        return teacherarea;
    }

    public void setTeacherarea(String teacherarea) {
        this.teacherarea = teacherarea == null ? null : teacherarea.trim();
    }

    public String getTeacherpic() {
        return teacherpic;
    }

    public void setTeacherpic(String teacherpic) {
        this.teacherpic = teacherpic == null ? null : teacherpic.trim();
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement == null ? null : achievement.trim();
    }

    public String getZhicheng() {
        return zhicheng;
    }

    public void setZhicheng(String zhicheng) {
        this.zhicheng = zhicheng == null ? null : zhicheng.trim();
    }
}