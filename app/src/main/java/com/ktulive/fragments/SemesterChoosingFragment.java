package com.ktulive.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.ktulive.R;
import com.ktulive.adapters.SemsterTilesGridAdapter;

public class SemesterChoosingFragment extends Fragment{
    String branch_name;
    String branch_code;
    String branch_semester;
    GridView branchListView;


    FirebaseAuth auth;
   TextView branch_name_on_sem_choosing_frag;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_semster_choosing,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        branch_name_on_sem_choosing_frag = (TextView) getActivity().findViewById(R.id.branch_name_on_sem_choosing_frag);


        Bundle i = this.getArguments();
        if (i!=null){
            branch_name =  i.getString("branch_name");
            branch_code= i.getString("branch_code");
            branch_name_on_sem_choosing_frag.setText(branch_name);
        }
        branchListView = (GridView) getActivity().findViewById(R.id.semesterNumberRecyclerView);
        branchListView.setAdapter(new SemsterTilesGridAdapter(getContext(),8));
        auth = FirebaseAuth.getInstance();

        branchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int sem = i + 1;
                String branch_identifier = branch_code+sem;

                Fragment fragment = new SubjectChoosingFragment();
                Bundle bundle = new Bundle();
                bundle.putString("branch_identifier",branch_identifier);
                fragment.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.homeFrame,fragment).addToBackStack("4").commit();

            }
        });

    }
}




