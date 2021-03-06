package com.ktulive.actvities;

import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ktulive.R;
import com.ktulive.adapters.ModuleAndReferenceArrayAdapter;
import com.ktulive.db.SubjectDetails;
import com.ktulive.extra.CusUtils;
import com.ktulive.models.IndividualModule;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.ktulive.models.Sub;

import java.util.ArrayList;

public class ModuleandReferenceDisplayActivity extends Fragment {

    DatabaseReference databaseReference;
    ArrayList<IndividualModule> individualModuleArrayList;
    ModuleAndReferenceArrayAdapter moduleAndReferenceArrayAdapter=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.modules_listing_without_recycler,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        individualModuleArrayList = new ArrayList<>();
        moduleAndReferenceArrayAdapter = new ModuleAndReferenceArrayAdapter(getContext(),R.layout.row_singlemodule,individualModuleArrayList);
        ListView mv  = (ListView)view.findViewById(R.id.mv);
        mv.setAdapter(moduleAndReferenceArrayAdapter);
        String subject_code =  getArguments().getString("subject_code");
        String subject_name = getArguments().getString("subject_name");
        TextView sub_name = (TextView) view.findViewById(R.id.subject_name);
        sub_name.setText(subject_name);

       final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Please wait..");
        pd.setCancelable(true);
        pd.setIndeterminate(true);
        pd.show();


        SubjectDetails subjectDetails = new SubjectDetails(getContext());
        subjectDetails.getSubjectDetails(subject_code.trim());



        databaseReference = CusUtils.getDatabase().getReference().child("btech").child("syllbus").child(subject_code).child("modules");
        databaseReference.keepSynced(true);
        databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    individualModuleArrayList.clear();
                    Iterable<DataSnapshot> dataSnapshotIterable = dataSnapshot.getChildren();
                    int i = 1;
                    for (DataSnapshot sub:dataSnapshotIterable) {
                        IndividualModule individualModule = sub.getValue(IndividualModule.class);
                        individualModuleArrayList.add(individualModule);
                        TextView module_title = new TextView(getContext());
                        TextView module_contents  = new TextView(getContext());
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
