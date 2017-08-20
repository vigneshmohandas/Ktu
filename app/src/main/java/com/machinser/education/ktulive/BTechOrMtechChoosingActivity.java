package com.machinser.education.ktulive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BTechOrMtechChoosingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                Intent i1 = new Intent(this, BranchChoosingActivity.class);
                startActivity(i1);
                break;
            case R.id.button2:
                Intent i2 = new Intent(this, mtechbranch.class);
                startActivity(i2);
                break;
            default:
                break;
        }
    }
}
