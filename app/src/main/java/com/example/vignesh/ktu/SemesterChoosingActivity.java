package com.example.vignesh.ktu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class SemesterChoosingActivity extends AppCompatActivity{
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
    ImageView imageView;
    TextView textview;
    FirebaseAuth auth;
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
        imageView = (ImageView)findViewById(R.id.imageView);
        textview =(TextView)findViewById(R.id.name);

        auth = FirebaseAuth.getInstance();

        Picasso.with(getApplicationContext()).load(auth.getCurrentUser().getPhotoUrl()).into(imageView);
        String name = auth.getCurrentUser().getDisplayName();
        textview.setText(name);

        LinearLayout.LayoutParams layoutParamsImageView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams layoutParamsCardView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsImageView.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
        layoutParamsCardView.height = (int)getResources().getDimension(R.dimen.square_card_w_h);
        layoutParamsCardView.width = (int)getResources().getDimension(R.dimen.square_card_w_h);
        layoutParamsCardView.gravity = Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL ;

        sems = new ArrayList<>();
        for(int j = 0;j<branch_name_i;j++){
            sems.add(j+1+"");
        }
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.semester_grid_layout,sems);
        gv.setAdapter(arrayAdapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String branch_and_semster = branch_code+(position+1)+"";
                Toast.makeText(SemesterChoosingActivity.this, branch_and_semster, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(SemesterChoosingActivity.this,Semester.class);

                i.putExtra("branch_and_semster",branch_and_semster.toLowerCase());
                startActivity(i);
            }
        });

    }

    }




