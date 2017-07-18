package com.example.vignesh.ktu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                Intent i1=new Intent(this,semester1.class);
                startActivity(i1);
                break;
            case R.id.button2:
                Intent i2=new Intent(this,semester2.class);
                startActivity(i2);
                break;
            case R.id.button3:
                Intent i3=new Intent(this,semester3.class);
                startActivity(i3);
                break;
            case R.id.button4:
                Intent i4=new Intent(this,semester4.class);
                startActivity(i4);
                break;
            case R.id.button5:
                Intent i5=new Intent(this,semester5.class);
                startActivity(i5);
                break;
            case R.id.button6:
                Intent i6=new Intent(this,semester6.class);
                startActivity(i6);
                break;
            case R.id.button7:
                Intent i7=new Intent(this,semester7.class);
                startActivity(i7);
                break;
            case R.id.button8:
                Intent i8=new Intent(this,semester8.class);
                startActivity(i8);
                break;
            default:
                break;
        }

        }
    }




