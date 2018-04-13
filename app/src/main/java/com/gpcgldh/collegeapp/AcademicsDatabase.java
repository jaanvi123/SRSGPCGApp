package com.gpcgldh.collegeapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by hp on 13-Nov-16.
 */
public class AcademicsDatabase extends SQLiteOpenHelper {
    public static final String DB_NAME="achievements";
    public static final Integer DB_VERSION=1;
    public AcademicsDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public ArrayList<String> getCourses(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> list = new ArrayList<>();
        Cursor c = db.rawQuery("select distinct trade from academics",null);
        while(c.moveToNext()){
            list.add(c.getString(c.getColumnIndex("trade")));
        }
        return list;
    }

    public ArrayList<Integer> getYears(String course){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Integer> list = new ArrayList<>();
        Cursor c = db.rawQuery("select year from academics where trade='"+course+"'",null);
        while(c.moveToNext()){
            list.add(c.getInt(c.getColumnIndex("year")));
        }
        return list;
    }
    public AchievementsData getAchievementsData(String course,int year)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("select * from academics where trade='"+course+"' and year='"+year+"'",null);
        Log.d("log_tag_q","select * from academics where trade='"+course+"' and year='"+year+"'");
        c.moveToNext();
        AchievementsData ad=new AchievementsData();
        ad.setName(c.getString(c.getColumnIndex("name")));
        ad.setFname(c.getString(c.getColumnIndex("fathername")));
        return ad;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
