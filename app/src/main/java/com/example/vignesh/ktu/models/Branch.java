package com.example.vignesh.ktu.models;

import android.util.Log;

/**
 * Created by asnim on 18/07/17.
 */

public class Branch {
    public String branch_name;
    public String branch_code;
    public int branch_semesters;

    public void printBranchDetails(){
        Log.e("B_NAME",this.branch_name);
        Log.e("B_CODE",this.branch_code);
        Log.e("B_SEM",this.branch_semesters+"");

    }
    public Branch(){

    }

}
