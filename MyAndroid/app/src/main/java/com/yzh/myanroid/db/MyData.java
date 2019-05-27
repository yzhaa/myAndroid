package com.yzh.myanroid.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.yzh.myanroid.Bean.Article;
import com.yzh.myanroid.util.MyApplication;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MyData extends SQLiteOpenHelper {

    private static final String DATA_BASE_NAME = "MyData";

    private static MyData mMyData = new MyData(MyApplication.getmContext(), DATA_BASE_NAME, null, 1);

    private static final String HISTORY = "create table History(id integer primary key autoincrement,name text)";
    private static final String HOTKEY = "create table HotKey(name text)";
    private static final String BANNER = "create table Banner (id text ,title text,url text,imagePath  text)";
    private static final String ARTICLE = "create table MainArticle (id text ,title text,author text,chapterName text,niceDate text,link text,curPage text)";
    private static final String SYSTEM = "create table System (id text ,name text) ";
    private static final String SYSTEM_TAG = "create table SystemTag (id text ,name text,parentChapterId integer) ";
    private static final String SYSTEM_ARTICLE = "create table SystemArticle (id text ,title text,author text,chapterName text,niceDate text,link text,curPage text) ";
    private static final String PROJECT = "create table Project (id text ,name text)";
    private static final String PROJECT_ARTICLE = "create table ProjectArticle (id text ,title text,[desc] text,chapterName text,link text,envelopePic text,curPage text)";

    private MyData(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HISTORY);
        db.execSQL(BANNER);
        db.execSQL(ARTICLE);
        db.execSQL(SYSTEM);
        db.execSQL(PROJECT);
        db.execSQL(SYSTEM_ARTICLE);
        db.execSQL(PROJECT_ARTICLE);
        db.execSQL(SYSTEM_TAG);
        db.execSQL(HOTKEY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public static <T> void write(List list, Class<T> tClass, String curPage) {
            try {
                SQLiteDatabase db = mMyData.getWritableDatabase();
                Field[] fields;
                if(tClass.getSuperclass() == Article.class){
                    fields = tClass.getSuperclass().getDeclaredFields();
                }
                else {
                    fields= tClass.getDeclaredFields();
                }
                List<Method> methods = new ArrayList<>();
                ContentValues values = new ContentValues();
                for (Field f:fields) {
                    methods.add(tClass.getMethod("get" + f.getName().substring(1)));
                }

                if(curPage!=null) {
                    for (int i = 0; i < list.size(); i++) {
                        for (int k=0;k<fields.length;k++) {
                            String name = fields[k].getName();
                            values.put(name.substring(1,2).toLowerCase()+name.substring(2), methods.get(k).invoke(list.get(i))+"");
                        }
                        values.put("curPage", curPage);
                        db.insert(tClass.getSimpleName(), null, values);
                        values.clear();
                    }
                }
                else {
                    for (int i = 0; i < list.size(); i++) {
                        for (int k=0;k<fields.length;k++) {
                            String name = fields[k].getName();
                            values.put(name.substring(1,2).toLowerCase()+name.substring(2), methods.get(k).invoke(list.get(i))+"");
                        }
                        db.insert(tClass.getSimpleName(), null, values);
                        values.clear();
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
    }

    public static <T> List<T> read(Class<T> tClass,String selection,String[] selectionArgs ){
        SQLiteDatabase db = mMyData.getReadableDatabase();
        List<T> list = new ArrayList<>();
        Cursor cursor = null;
        Field[] fields;

        try {
            if(tClass.getSuperclass() == Article.class){
                fields = tClass.getSuperclass().getDeclaredFields();
            }
            else {
                fields= tClass.getDeclaredFields();
            }
            List<Method> methods = new ArrayList<>();
            for (Field f:fields) {
                methods.add(tClass.getMethod("set"  + f.getName().substring(1), f.getType()));
            }
            cursor = db.query(tClass.getSimpleName(), null, selection, selectionArgs, null, null, null);

            if(cursor.moveToFirst()){
                do {
                    T t = tClass.newInstance();
                    for (int i = 0; i < fields.length; i++) {
                        String name = fields[i].getName();
                        methods.get(i).invoke(t, cursor.getString(cursor.getColumnIndex(name.substring(1,2).toLowerCase()+name.substring(2))));
                    }
                    list.add(t);
                } while (cursor.moveToNext());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        finally {
            if(cursor!=null){
                cursor.close();
            }
        }
        return list;
    }


}
