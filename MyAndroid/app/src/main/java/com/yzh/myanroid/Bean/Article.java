package com.yzh.myanroid.Bean;

/**
 * 文章对于的实体
 */
public class Article {
    private String mId;
    private String mAuthor;
    private String mChapterName;
    private String mLink;
    private String mNiceDate;
    private String mTitle;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getChapterName() {
        return mChapterName;
    }

    public String getLink() {
        return mLink;
    }

    public String getNiceDate() {
        return mNiceDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setAuthor(String author) {
        this.mAuthor = author;
    }

    public void setChapterName(String chapterName) {
        this.mChapterName = chapterName;
    }

    public void setLink(String link) {
        this.mLink = link;
    }

    public void setNiceDate(String niceDate) {
        this.mNiceDate = niceDate;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

}
