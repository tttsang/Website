package com.jking.computersite.entity;

public class XxxyContentWithBLOBs extends XxxyContent {
    private String title;

    private String titleAlias;

    private String introtext;

    private String fulltext;

    private String createdByAlias;

    private String images;

    private String urls;

    private String attribs;

    private String metakey;

    private String metadesc;

    private String metadata;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTitleAlias() {
        return titleAlias;
    }

    public void setTitleAlias(String titleAlias) {
        this.titleAlias = titleAlias == null ? null : titleAlias.trim();
    }

    public String getIntrotext() {
        return introtext;
    }

    public void setIntrotext(String introtext) {
        this.introtext = introtext == null ? null : introtext.trim();
    }

    public String getFulltext() {
        return fulltext;
    }

    public void setFulltext(String fulltext) {
        this.fulltext = fulltext == null ? null : fulltext.trim();
    }

    public String getCreatedByAlias() {
        return createdByAlias;
    }

    public void setCreatedByAlias(String createdByAlias) {
        this.createdByAlias = createdByAlias == null ? null : createdByAlias.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls == null ? null : urls.trim();
    }

    public String getAttribs() {
        return attribs;
    }

    public void setAttribs(String attribs) {
        this.attribs = attribs == null ? null : attribs.trim();
    }

    public String getMetakey() {
        return metakey;
    }

    public void setMetakey(String metakey) {
        this.metakey = metakey == null ? null : metakey.trim();
    }

    public String getMetadesc() {
        return metadesc;
    }

    public void setMetadesc(String metadesc) {
        this.metadesc = metadesc == null ? null : metadesc.trim();
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata == null ? null : metadata.trim();
    }

    @Override
    public String toString() {
        return "XxxyContentWithBLOBs{" +
                "title='" + title + '\'' +
                ", titleAlias='" + titleAlias + '\'' +
                ", introtext='" + introtext + '\'' +
                ", fulltext='" + fulltext + '\'' +
                ", createdByAlias='" + createdByAlias + '\'' +
                ", images='" + images + '\'' +
                ", urls='" + urls + '\'' +
                ", attribs='" + attribs + '\'' +
                ", metakey='" + metakey + '\'' +
                ", metadesc='" + metadesc + '\'' +
                ", metadata='" + metadata + '\'' +
                '}';
    }
}