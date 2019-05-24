package com.yzh.myanroid.Bean;

public class Banner  {
    private String id;
    private String title;
    private String imagePath;
    private String url;

    public Banner() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImagePath() {
        return imagePath;
    }



    public String getUrl() {
        return url;
    }


}
