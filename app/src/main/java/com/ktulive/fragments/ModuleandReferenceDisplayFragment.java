package com.ktulive.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ktulive.R;
import com.ktulive.adapters.ModuleAndReferenceAdapter;
import com.ktulive.extra.CusUtils;
import com.ktulive.models.IndividualModule;
import com.ktulive.models.Subjects;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.zip.ZipEntry;

public class ModuleandReferenceDisplayFragment extends Fragment {

    DatabaseReference databaseReference;
    ArrayList<IndividualModule> individualModuleArrayList;

    RecyclerView recyclerViewModule;
    ModuleAndReferenceAdapter moduleAndReferenceAdapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.modules_listing,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        individualModuleArrayList = new ArrayList<>();
        moduleAndReferenceAdapter = new ModuleAndReferenceAdapter(individualModuleArrayList,getContext(),getFragmentManager());

        recyclerViewModule = (RecyclerView) view.findViewById(R.id.modules);
        recyclerViewModule.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerViewModule.setLayoutManager(linearLayoutManager);
        recyclerViewModule.setAdapter(moduleAndReferenceAdapter);

        String subject_code = getArguments().getString("subject_code");
        String subject_name = getArguments().getString("subject_name");


        TextView sub_name = (TextView) view.findViewById(R.id.subject_name);
        sub_name.setText(subject_name + subject_code);




       final ProgressDialog pd = new ProgressDialog(getContext());
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

                    for (DataSnapshot sub:dataSnapshotIterable) {
                        IndividualModule individualModule = sub.getValue(IndividualModule.class);
                        individualModuleArrayList.add(individualModule);
                    }


                    moduleAndReferenceAdapter.notifyDataSetChanged();


                    Handler handler = new Handler(){
                        @Override
                        public void handleMessage(Message msg) {
                            pd.dismiss();
                        }
                    };
                    handler.sendMessageDelayed(new Message(),1000);
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
    





}
