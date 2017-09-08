package com.ktulive.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ktulive.ClickListener;
import com.ktulive.R;
import com.ktulive.extra.Constants;
import com.ktulive.fragments.ModuleandReferenceDisplayFragment;
import com.ktulive.fragments.SemesterChoosingFragment;
import com.ktulive.models.Branch;
import com.ktulive.models.Subjects;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

/**
 * Created by asnimpansari on 8/26/17.
 */

public class SubjectListingAdapter extends RecyclerView.Adapter<SubjectListingAdapter.ViewHolder>{

   static List<Subjects> branchList;
    String branch_name;



    public SubjectListingAdapter(ArrayList<Subjects> branchList, Context context, FragmentManager fm) {
        this.branchList = branchList;
        this.context = context;
        this.fm = fm;
    }

    private Context context;
    private FragmentManager fm;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.branch_list_item,parent,false);
        return new ViewHolder(v,this.context,fm);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        branch_name = branchList.get(position).subject_name;
        holder.branch_name.setText(branch_name);
        holder.branch_name.setBackgroundColor(Constants.grid_colors[position]);


    }

    @Override
    public int getItemCount() {
        return this.branchList.size();

    }


    public   List<Subjects> getBranchList(){
        return branchList;
    }

    /**
     * Created by asnim on 04/09/17.
     */

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView branch_name;
        ClickListener clickListener;
        Context context;
        FragmentManager fragmentManager;

        public ViewHolder(View itemView, Context context, FragmentManager fragmentManager) {
            super(itemView);

            branch_name = (TextView)itemView.findViewById(R.id.branch_list_item_text);

            branch_name.setOnClickListener(this);
            this.context = context;
            this.fragmentManager = fragmentManager;
        }

        public  void setItemClickListener(ClickListener clickListener){
            this. clickListener = clickListener;



        }

        @Override
        public void onClick(View view) {

            int pos = getAdapterPosition();
            Log.e("ADPATES",pos+"")
            ;

            Fragment fragment = new ModuleandReferenceDisplayFragment();

            Bundle bundle = new Bundle();
            bundle.putString("subject_name", SubjectListingAdapter.branchList.get(pos).subject_name);
            bundle.putString("subject_code", SubjectListingAdapter.branchList.get(pos).subject_code);

            Log.e("SUB_CODE",SubjectListingAdapter.branchList.get(pos).subject_code);
            fragment.setArguments(bundle);

            fragmentManager.beginTransaction()
                    .replace(R.id.homeFrame,fragment)
                    .commit();

        }

    }
}


