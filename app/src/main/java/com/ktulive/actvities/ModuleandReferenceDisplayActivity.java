package com.ktulive.actvities;

import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.ktulive.R;
import com.ktulive.adapters.ModuleAndReferenceArrayAdapter;
import com.ktulive.extra.CusUtils;
import com.ktulive.models.IndividualModule;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ModuleandReferenceDisplayActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    ArrayList<IndividualModule> individualModuleArrayList;

    ModuleAndReferenceArrayAdapter moduleAndReferenceArrayAdapter=null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modules_listing_without_recycler);
        individualModuleArrayList = new ArrayList<>();
        moduleAndReferenceArrayAdapter = new ModuleAndReferenceArrayAdapter(this,R.layout.row_singlemodule,individualModuleArrayList);
        ListView mv  = (ListView)findViewById(R.id.mv);
        mv.setAdapter(moduleAndReferenceArrayAdapter);
        String subject_code =  getIntent().getStringExtra("subject_code");
        String subject_name = getIntent().getStringExtra("subject_name");
        TextView sub_name = (TextView) findViewById(R.id.subject_name);
        sub_name.setText(subject_name);

       final ProgressDialog pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Please wait..");
        pd.setCancelable(true);
        pd.setIndeterminate(true);
        pd.show();


        databaseReference = CusUtils.getDatabase().getReference().child("btech").child("syllbus").child(subject_code).child("modules");
        databaseReference.keepSynced(true);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    individualModuleArrayList.clear();
                    Iterable<DataSnapshot> dataSnapshotIterable = dataSnapshot.getChildren();

                    int i = 1;

                    for (DataSnapshot sub:dataSnapshotIterable) {
                        IndividualModule individualModule = sub.getValue(IndividualModule.class);
                        individualModuleArrayList.add(individualModule);
                        TextView module_title = new TextView(ModuleandReferenceDisplayActivity.this);
                        TextView module_contents  = new TextView(ModuleandReferenceDisplayActivity.this);
                        module_title.setText(" Module - " + i);
                        module_contents.setText(individualModule.description);
                    }
                    moduleAndReferenceArrayAdapter.notifyDataSetChanged();
                    pd.dismiss();

                }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
