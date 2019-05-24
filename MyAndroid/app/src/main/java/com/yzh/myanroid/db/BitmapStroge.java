package com.yzh.myanroid.db;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.yzh.myanroid.util.MyApplication;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class BitmapStroge  {
    public static final String BANNER = "bn";



    public static void readASFile(Bitmap bitmap ,String name){
        try {
            FileOutputStream fos = MyApplication.getContext().openFileOutput(name + ".jpeg",Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getBitmap(String name){
        FileInputStream fis = null;
            try {
                fis = MyApplication.getContext().openFileInput( name+".jpeg");
                return BitmapFactory.decodeStream(fis);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(fis!=null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        return null;
    }


}
