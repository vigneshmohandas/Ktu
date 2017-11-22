package com.ktulive.models;

/**
 * Created by asnimansari on 07/11/17.
 */

public class Sub {
    public String subject_code;
    public String module_1;
    public String module_2;
    public String module_3;
    public String module_4;
    public String module_5;
    public String module_6;

    public String text_book;
    public String reference;

    public Sub(){

    }

    public Sub(String subject_code, String module_1, String module_2, String module_3, String module_4, String module_5, String module_6, String text_book, String reference) {
        this.subject_code = subject_code;
        this.module_1 = module_1;
        this.module_2 = module_2;
        this.module_3 = module_3;
        this.module_4 = module_4;
        this.module_5 = module_5;
        this.module_6 = module_6;
        this.text_book = text_book;
        this.reference = reference;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public String getModule_1() {
        return module_1;
    }

    public String getModule_2() {
        return module_2;
    }

    public String getModule_3() {
        return module_3;
    }

    public String getModule_4() {
        return module_4;
    }

    public String getModule_5() {
        return module_5;
    }

    public String getModule_6() {
        return module_6;
    }

    public String getText_book() {
        return text_book;
    }

    public String getReference() {
        return reference;
    }
}
