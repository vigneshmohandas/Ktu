package com.ktulive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ktulive.extra.CusUtils;
import com.ktulive.models.Branch;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BranchChoosingActivity extends AppCompatActivity {
    ArrayList<Branch> branchArrayList;
    ArrayList<String> branchdisplay;
    ArrayAdapter<String> branchadapter;
    Branch choosedBranch;

    DatabaseReference databaseReference;

    ListView branchListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
        branchListView = (ListView) findViewById(R.id.branchListView);
        databaseReference = CusUtils.getDatabase().getReference().child("btech").child("courses");
        databaseReference.keepSynced(true);
        branchArrayList = new ArrayList<>();

        Log.e("WHERE", "BRANCH CHOOSER");

        Log.e("DB_REF", (databaseReference == null) + "");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> dataSnapshotIterable = dataSnapshot.getChildren();
                branchdisplay = new ArrayList<String>();

                Log.e("CHILDRED", dataSnapshot.getChildrenCount() + "");
                for (DataSnapshot branch : dataSnapshotIterable) {
                    Branch branch1 = branch.getValue(Branch.class);
                    branchArrayList.add(branch1);


                    branch1.printBranchDetails();

                    branchdisplay.add(branch1.branch_name);

                }
                branchadapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_layout, branchdisplay);
                branchListView.setAdapter(branchadapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        branchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choosedBranch = branchArrayList.get(position);
                Intent i = new Intent(BranchChoosingActivity.this,SemesterChoosingActivity.class);
                i.putExtra("branch_name",choosedBranch.branch_name);
                i.putExtra("branch_code",choosedBranch.branch_code);
                i.putExtra("branch_semester",choosedBranch.branch_semesters+"");
                startActivity(i);

            }
        });
    }

    public static class BTechOrMtechChoosingActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
        }
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button1:
                    Intent i1 = new Intent(this, BranchChoosingActivity.class);
                    startActivity(i1);
                    break;
                case R.id.button2:
                    Intent i2 = new Intent(this, mtechbranch.class);
                    startActivity(i2);
                    break;
                default:
                    break;
            }
        }
    }
}
