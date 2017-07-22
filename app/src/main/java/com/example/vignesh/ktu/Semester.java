package com.example.vignesh.ktu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vignesh.ktu.extra.CusUtils;
import com.example.vignesh.ktu.models.Subjects;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Semester extends AppCompatActivity {

    DatabaseReference databaseReference;
    ArrayList<Subjects> arrayListSubjects;
    ArrayList<String> subjectstoBeDisplayed;
    ArrayAdapter<String> subjectsAdapter;
    ListView subjectList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester1);
        Intent i = getIntent();
        String branch_and_semster = i.getStringExtra("branch_and_semster");

        subjectList  = (ListView)findViewById(R.id.subjectList);

        databaseReference = CusUtils.getDatabase().getReference().child("btech").child("subjects").child(branch_and_semster);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> dataSnapshotIterable = dataSnapshot.getChildren();

                arrayListSubjects = new ArrayList<Subjects>();
                subjectstoBeDisplayed = new ArrayList<String>();
                for (DataSnapshot sub:dataSnapshotIterable){
                    Subjects individial_sub = sub.getValue(Subjects.class);
                    arrayListSubjects.add(individial_sub);
                    subjectstoBeDisplayed.add(individial_sub.getSubject_name());

                    Toast.makeText(Semester.this, individial_sub.getSubject_name(), Toast.LENGTH_SHORT).show();

                }
                subjectsAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_layout,subjectstoBeDisplayed);
                subjectList.setAdapter(subjectsAdapter);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        subjectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Subjects su = arrayListSubjects.get(position);

                Intent i = new Intent(Semester.this,ModuleList.class);
                i.putExtra("subject_name",su.getSubject_name());
                i.putExtra("subject_code",su.getSubject_code());
                startActivity(i);
            }
        });



    }





}
