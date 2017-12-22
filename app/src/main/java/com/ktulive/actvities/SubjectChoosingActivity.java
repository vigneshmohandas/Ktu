package com.ktulive.actvities;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import com.ktulive.R;
import com.ktulive.adapters.SubjectListingAdapter;
import com.ktulive.extra.CusUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.ktulive.models.Subjects;
import android.graphics.drawable.ColorDrawable;

import java.util.ArrayList;

public class SubjectChoosingActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    Subjects individial_sub;
    ArrayList<Subjects> subjectses = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectlist);

        android.support.v7.widget.Toolbar toolbar= (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        toolbar.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

        final SubjectListingAdapter subjectListingAdapter  = new SubjectListingAdapter(getBaseContext(),R.layout.support_simple_spinner_dropdown_item,subjectses);

        ListView lv = (ListView)findViewById(R.id.subjectListRecycler) ;
        lv.setAdapter(subjectListingAdapter);

      String branch_sem =  getIntent().getStringExtra("branch_sem");
        databaseReference  = CusUtils.getDatabase().getReference().child("btech").child("subjects").child(branch_sem);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> dataSnapshotIterable = dataSnapshot.getChildren();
                subjectses.clear();

                for (DataSnapshot sub:dataSnapshotIterable){
                    individial_sub = sub.getValue(Subjects.class);

                    Log.e("Sub",individial_sub.getSubject_name());
                    subjectses.add(individial_sub);
                }
                subjectListingAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
