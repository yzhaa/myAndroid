package com.yzh.myanroid.Bean;

/**
 * 轮播图对应的实体
 */
public class Banner  {
    private String mId;
    private String mTitle;
    private String mImagePath;
    private String mUrl;

    public Banner() {
    }


    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setImagePath(String imagePath) {
        this.mImagePath = imagePath;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public String getImagePath() {
        return mImagePath;
    }



    public String getUrl() {
        return mUrl;
    }


}
