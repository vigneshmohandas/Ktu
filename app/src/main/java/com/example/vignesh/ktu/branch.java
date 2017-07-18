package com.example.vignesh.ktu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class branch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
    }
    public void onClick(View view){
        Intent i1=new Intent(this,MainActivity.class);
        startActivity(i1);
    }
}
