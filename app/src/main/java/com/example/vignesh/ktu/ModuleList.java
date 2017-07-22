package com.example.vignesh.ktu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.vignesh.ktu.extra.CusUtils;
import com.example.vignesh.ktu.models.IndividualModule;
import com.example.vignesh.ktu.models.ModuleCardAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ModuleList extends AppCompatActivity {

    DatabaseReference databaseReferenceModules;
    DatabaseReference databaseReferenceReference;
    ArrayList<IndividualModule> individualModuleArrayList;
    ModuleCardAdapter moduleCardAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulelist);

        Intent i = getIntent();
        String subject_name = i.getStringExtra("subject_name");
        String subject_code = i.getStringExtra("subject_code");
        listView = (ListView)findViewById(R.id.listView);

        databaseReferenceModules = CusUtils.getDatabase().getReference().child("btech").child("syllbus").child(subject_code).child("modules");
        databaseReferenceReference = CusUtils.getDatabase().getReference().child("btech").child("syllbus").child(subject_code).child("reference");


        databaseReferenceModules.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                individualModuleArrayList = new ArrayList<IndividualModule>();
                Iterable<DataSnapshot> dataSnapshotIterable = dataSnapshot.getChildren();
                for(DataSnapshot dataSnapshot1:dataSnapshotIterable){
                    IndividualModule individualModule =  dataSnapshot1.getValue(IndividualModule.class);
                    individualModuleArrayList.add(individualModule);

                    Log.e("TAG",individualModule.description);
                }
                moduleCardAdapter = new ModuleCardAdapter(ModuleList.this,R.layout.individual_module,individualModuleArrayList);
                listView.setAdapter(moduleCardAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}