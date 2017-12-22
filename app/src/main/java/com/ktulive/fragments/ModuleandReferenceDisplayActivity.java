package com.ktulive.fragments;

import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ktulive.R;
import com.ktulive.adapters.ModuleAndReferenceAdapter;
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

    RecyclerView recyclerViewModule;
    ModuleAndReferenceAdapter moduleAndReferenceAdapter;

    TextView modules[]  = null;
    TextView moduleTitle [] = null;

    LinearLayout linearLayout = null;
    ModuleAndReferenceArrayAdapter moduleAndReferenceArrayAdapter=null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modules_listing_without_recycler);
        modules = new TextView[6];
        moduleTitle = new TextView[6];


        individualModuleArrayList = new ArrayList<>();
        moduleAndReferenceArrayAdapter = new ModuleAndReferenceArrayAdapter(this,R.layout.template_sem_number_layout,individualModuleArrayList);
        ListView mv  = (ListView)findViewById(R.id.mv);
        mv.setAdapter(moduleAndReferenceArrayAdapter);





//        linearLayout = (LinearLayout) findViewById(R.id.ll);

//        individualModuleArrayList = new ArrayList<>();
        moduleAndReferenceAdapter = new ModuleAndReferenceAdapter(individualModuleArrayList);

//        recyclerViewModule = (RecyclerView)findViewById(R.id.modules);
//        recyclerViewModule.setHasFixedSize(true);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//
//        recyclerViewModule.setLayoutManager(linearLayoutManager);
//        recyclerViewModule.setAdapter(moduleAndReferenceAdapter);

        String subject_code =  getIntent().getStringExtra("subject_code");

//        String subject_code = getArguments().getString("subject_code");
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

//                        linearLayout.addView(module_title);
//                        linearLayout.addView(module_contents);

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
