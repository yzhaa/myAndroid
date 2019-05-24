package com.yzh.myanroid.Bean;



public class System {
    private String name;
    private String id;

    public System( String id,String name) {
        this.name = name;
        this.id = id;
    }

    public System() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
