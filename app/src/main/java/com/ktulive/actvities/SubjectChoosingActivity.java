package com.ktulive.actvities;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ktulive.R;
import com.ktulive.adapters.SubjectListingAdapter;
import com.ktulive.extra.CusUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.ktulive.models.Subjects;

import java.util.ArrayList;

public class SubjectChoosingActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    ListView listView;
    TextView tv1;
    Subjects individial_sub;


    RecyclerView subjectLisitingRecycler;



    ArrayList<Subjects> subjectses = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectlist);


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

//    @Nullable
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        Bundle i = this.getArguments();
//        String subject_name = i.getString("subject_name");
//        String branch_identifier = i.getString("branch_identifier").toLowerCase();
//
//        listView = (ListView)view.findViewById(R.id.lvv);
//        tv1= (TextView)view.findViewById(R.id.branch_name_and_semester_name);
//        subjectLisitingRecycler = (RecyclerView) view.findViewById(R.id.subjectListRecycler);
//
//        subjectLisitingRecycler.setHasFixedSize(true);
//        subjectLisitingRecycler.setItemAnimator(new DefaultItemAnimator());
//        subjectLisitingRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
//        final SubjectListingAdapter subjectListingAdapter = new SubjectListingAdapter(subjectses,getContext(),getFragmentManager());
//        subjectLisitingRecycler.setAdapter(subjectListingAdapter);
//
//        databaseReference = CusUtils.getDatabase().getReference().child("btech").child("subjects").child(branch_identifier);
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Iterable<DataSnapshot> dataSnapshotIterable = dataSnapshot.getChildren();
//                subjectses.clear();
//                for (DataSnapshot sub:dataSnapshotIterable){
//                    individial_sub = sub.getValue(Subjects.class);
//                    subjectses.add(individial_sub);
//                }
//                subjectListingAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
}
