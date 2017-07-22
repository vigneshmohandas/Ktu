package com.example.vignesh.ktu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class semester1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester1);
    }
public void onclick(View view){
    Intent i=new Intent(this,modulelist.class);
    startActivity(i);
}
}
