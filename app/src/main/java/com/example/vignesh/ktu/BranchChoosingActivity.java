package com.example.vignesh.ktu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.vignesh.ktu.extra.CusUtils;
import com.example.vignesh.ktu.models.Branch;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class BranchChoosingActivity extends AppCompatActivity {
    ArrayList<Branch> branchArrayList;
    HashMap<String,Branch> branchHashMap;
    ArrayList<String> branchdisplay;
    ArrayAdapter<String> branchadapter;

    DatabaseReference databaseReference;

    ListView branchListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
        branchListView = (ListView)findViewById(R.id.branchListView);
        databaseReference = CusUtils.getDatabase().getReference().child("btech");
        databaseReference.keepSynced(true);
        branchArrayList = new ArrayList<>();
        branchHashMap = new HashMap<>();
        Log.e("WHERE","BRANCH CHOOSER");

        Log.e("DB_REF",(databaseReference == null)+"");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> dataSnapshotIterable = dataSnapshot.getChildren();
                branchdisplay = new ArrayList<String>();

                Log.e("CHILDRED",dataSnapshot.getChildrenCount()+"");
                for (DataSnapshot branch: dataSnapshotIterable){
                    Branch branch1 = branch.getValue(Branch.class);


                    branch1.printBranchDetails();
                    branchHashMap.put(branch1.branch_name,branch1);
                    branchdisplay.add(branch1.branch_name);

                }
                branchadapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_layout,branchdisplay);
                branchListView.setAdapter(branchadapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
