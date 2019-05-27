package com.yzh.myanroid.Bean;

public class SystemTag {
    private String mId;
    private String mName;
    private String mParentChapterId;



    public SystemTag() {
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getParentChapterId() {
        return mParentChapterId;
    }

    public void setParentChapterId(String parentChapterId) {
        this.mParentChapterId = parentChapterId;
    }
}
