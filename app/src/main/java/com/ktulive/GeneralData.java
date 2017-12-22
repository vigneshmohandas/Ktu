package com.ktulive;

import java.util.HashMap;

/**
 * Created by asnimansari on 22/12/17.
 */

public class GeneralData {


    public static HashMap<String,String> branchAndCode;
    public static String getBranchCode(String branchShort){

        branchAndCode = new HashMap<>();

        branchAndCode.put("CSE","r");
        branchAndCode.put("ARCH","a");
        branchAndCode.put("ECE","t");
        branchAndCode.put("MECH","m");
        branchAndCode.put("CHEM","h");
        branchAndCode.put("EEE","e");
        branchAndCode.put("CIVIL","c");
        return branchAndCode.get(branchShort);



    }
}
