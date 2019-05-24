package com.yzh.myanroid.Bean;

public class Article {
    private String id;
    private String author;
    private String chapterName;
    private String link;
    private String niceDate;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public String getChapterName() {
        return chapterName;
    }

    public String getLink() {
        return link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
