package com.ktulive.models;

import android.util.Log;

/**
 * Created by asnim on 18/07/17.
 */

public class Branch {
    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    public int getBranch_semesters() {
        return branch_semesters;
    }

    public void setBranch_semesters(int branch_semesters) {
        this.branch_semesters = branch_semesters;
    }

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
