package com.ktulive.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ktulive.models.Sub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by asnimansari on 07/11/17.
 */


public class SubjectDetails extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ktulive.db";
    public static final String TABLE_NAME = "subject_details";
    public static final String COLUMN_ID = "id";
    public static final String SUBJECT_CODE = "subject_code";
    public static final String MODULE_1 = "module_1";
    public static final String MODULE_2 = "module_2";
    public static final String MODULE_3 = "module_3";
    public static final String MODULE_4 = "module_4";
    public static final String MODULE_5 = "module_5";
    public static final String MODULE_6 = "module_6";
    public static final String TEXT_BOOK = "text_book";
    public static final String REFERENCE = "reference";





    public SubjectDetails(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.e("DB","ON CREATE");
        db.execSQL(
                "create table  " +TABLE_NAME+" "+
                        "("+COLUMN_ID+" integer primary key," +
                        SUBJECT_CODE+" text,"+
                        MODULE_1+" text,"+
                        MODULE_2+" text,"+
                        MODULE_3+" text,"+
                        MODULE_4+" text,"+
                        MODULE_5+" text,"+
                        MODULE_6+" text,"+
                        TEXT_BOOK+" text,"+
                        REFERENCE + " text)"
        );

        Log.e("DB","CREATED TABLE");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertSubjects (ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();

        long i =  db.insert(TABLE_NAME, null, contentValues);
        Log.e("DB","inserted " + i);
        return true;
    }
    public Sub getSubjectDetailsWithSubjectCode(int subject_code) {

        Sub sub;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME+" where " + SUBJECT_CODE + " = '"+subject_code+"'" , null );
        res.moveToFirst();
        sub = new Sub(res.getString(res.getColumnIndex(SUBJECT_CODE)),
                res.getString(res.getColumnIndex(MODULE_1)),
                res.getString(res.getColumnIndex(MODULE_2)),
                res.getString(res.getColumnIndex(MODULE_3)),
                res.getString(res.getColumnIndex(MODULE_4)),
                res.getString(res.getColumnIndex(MODULE_5)),
                res.getString(res.getColumnIndex(MODULE_6)),
                res.getString(res.getColumnIndex(TEXT_BOOK)),
                res.getString(res.getColumnIndex(REFERENCE))
        );

        db.close();
        return sub;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public void getSubjectDetails(String  subject_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where "+ SUBJECT_CODE + " in ('"+subject_id+"')",null);
        res.moveToFirst();
        while(!res.isAfterLast()){
            Log.e("M1",res.getString(res.getColumnIndex(SUBJECT_CODE)));
            res.moveToNext();
        }

    }

}