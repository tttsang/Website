package com.jking.computersite.VO;

import java.util.List;
import java.util.Map;

public class ArticleVO {

    private Integer isshow;

    private String title;

    private String author;

    private String auditor;

    private String content;

    private List<String> publishTo;

    public Integer getIsshow() {
        return isshow;
    }

    public void setIsshow(Integer isshow) {
        this.isshow = isshow;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getPublishTo() {
        return publishTo;
    }

    public void setPublishTo(List<String> publishTo) {
        this.publishTo = publishTo;
    }

    @Override
    public String toString() {
        return "ArticleVO{" +
                "isshow=" + isshow +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", auditor='" + auditor + '\'' +
                ", content='" + content + '\'' +
                ", publishTo=" + publishTo +
                '}';
    }
}
