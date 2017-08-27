package com.ktulive.extra;

import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by asnim on 27/05/17.
 */


public class CusUtils {
    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);



//
        }
        return mDatabase;
    }

}