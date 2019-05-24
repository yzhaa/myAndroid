package com.yzh.myanroid.controller.asy;

import android.os.AsyncTask;

import com.yzh.myanroid.controller.Do.DBCInterface;

public class DBAsy extends AsyncTask<Void,Void,Void>{
    private DBCInterface dbcInterface;

    public DBAsy(DBCInterface dbcInterface) {
        this.dbcInterface = dbcInterface;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        dbcInterface.doSome();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
       dbcInterface.result();
    }
}
