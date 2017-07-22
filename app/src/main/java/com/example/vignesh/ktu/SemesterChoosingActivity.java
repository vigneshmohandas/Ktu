package com.example.vignesh.ktu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SemesterChoosingActivity extends AppCompatActivity implements View.OnClickListener{
    String branch_name;
    String branch_code;
    String branch_semester;
    TextView branch_name_ed;
    int branch_name_i;

    // Dynamic Listing
    TextView inner_image;
    CardView cardView;
    TextView sem;
    LinearLayout innerLayout;
    LinearLayout outerLayout;

    LinearLayout testlayout;
    GridView gv;


    ArrayList<String> sems;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
       branch_name =  i.getStringExtra("branch_name");
        branch_code=i.getStringExtra("branch_code");
        branch_semester = i.getStringExtra("branch_semester");
        branch_name_ed = (TextView)findViewById(R.id.branch_name);
        branch_name_ed.setText(branch_name);
        branch_name_i=Integer.parseInt(branch_semester);
//        testlayout = (LinearLayout)findViewById(R.id.testlayout);
        gv = (GridView)findViewById(R.id.gv);


        LinearLayout.LayoutParams layoutParamsImageView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams layoutParamsCardView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsImageView.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
        layoutParamsCardView.height = (int)getResources().getDimension(R.dimen.square_card_w_h);
        layoutParamsCardView.width = (int)getResources().getDimension(R.dimen.square_card_w_h);
        layoutParamsCardView.gravity = Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL ;
        sems = new ArrayList<>();
        for(int j = 0;j<branch_name_i;j++){
            sems.add(j+"");
        }
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_expandable_list_item_1,sems);
        gv.setAdapter(arrayAdapter);
        for (int k =0;k<branch_name_i;k++){
//            inner_image = new TextView(getApplicationContext());
//            cardView = new CardView(getApplicationContext());
//
//            inner_image.setLayoutParams(layoutParamsImageView);
//            inner_image.setText(k+1+"");
//
//            cardView.setLayoutParams(layoutParamsCardView);
//            cardView.addView(inner_image);
//            testlayout.addView(cardView);


        }
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
//            case R.id.button3:
//                Intent i3=new Intent(this,semester3.class);
//                startActivity(i3);
//                break;
//            case R.id.button4:
//                Intent i4=new Intent(this,semester4.class);
//                startActivity(i4);
//                break;
//            case R.id.button5:
//                Intent i5=new Intent(this,semester5.class);
//                startActivity(i5);
//                break;
//            case R.id.button6:
//                Intent i6=new Intent(this,semester6.class);
//                startActivity(i6);
//                break;
//            case R.id.button7:
//                Intent i7=new Intent(this,semester7.class);
//                startActivity(i7);
//                break;
//            case R.id.button8:
//                Intent i8=new Intent(this,semester8.class);
//                startActivity(i8);
//                break;
            default:
                break;
        }

        }
    }




