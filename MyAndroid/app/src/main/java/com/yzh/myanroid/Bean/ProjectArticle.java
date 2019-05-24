package com.yzh.myanroid.Bean;

public class ProjectArticle {
    private String id;
    private String chapterName;
    private String desc;
    private String envelopePic;
    private String link;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getDesc() {
        return desc;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
