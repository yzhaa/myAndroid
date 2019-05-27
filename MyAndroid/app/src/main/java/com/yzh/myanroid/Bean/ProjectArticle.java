package com.yzh.myanroid.Bean;

/**
 * 项目的文章
 */
public class ProjectArticle {
    private String mId;
    private String mChapterName;
    private String mDesc;
    private String mEnvelopePic;
    private String mLink;
    private String mTitle;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getChapterName() {
        return mChapterName;
    }

    public void setChapterName(String chapterName) {
        this.mChapterName = chapterName;
    }

    public String getDesc() {
        return mDesc;
    }

    public String getEnvelopePic() {
        return mEnvelopePic;
    }

    public String getLink() {
        return mLink;
    }

    public String getTitle() {
        return mTitle;
    }


    public void setDesc(String desc) {
        this.mDesc = desc;
    }

    public void setEnvelopePic(String envelopePic) {
        this.mEnvelopePic = envelopePic;
    }

    public void setLink(String link) {
        this.mLink = link;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }
}
