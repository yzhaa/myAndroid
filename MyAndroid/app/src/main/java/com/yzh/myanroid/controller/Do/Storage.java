package com.yzh.myanroid.controller.Do;

import com.yzh.myanroid.db.MyData;

import java.util.List;

public class Storage implements DBCInterface {
    private Class aClass;
    private String page;
    private List list;


    public Storage(Class aClass, String page, List list) {
        this.aClass = aClass;
        this.page = page;
        this.list = list;
    }

    public void doSome() {
        MyData.write(list, aClass, page);
    }

    @Override
    public void result() {

    }
}
