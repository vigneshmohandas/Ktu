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
import com.ktulive.fragments.SemesterChoosingFragment;
import com.ktulive.models.Branch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asnimpansari on 8/26/17.
 */

public class BranchListingAdapter extends RecyclerView.Adapter<BranchListingAdapter.ViewHolder>{

   static List<Branch> branchList;



    public BranchListingAdapter(ArrayList<Branch> branchList, Context context, FragmentManager fm) {
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
        String branch_name = branchList.get(position).branch_name;
        holder.branch_name.setText(branch_name);
        holder.branch_name.setBackgroundColor(Constants.grid_colors[position]);


    }

    @Override
    public int getItemCount() {
        return this.branchList.size();

    }


    public   List<Branch> getBranchList(){
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

            Fragment fragment = new SemesterChoosingFragment();

            Bundle bundle = new Bundle();
            bundle.putString("branch_name",BranchListingAdapter.branchList.get(pos).branch_name);
            bundle.putString("branch_code",BranchListingAdapter.branchList.get(pos).branch_code);
            fragment.setArguments(bundle);

            fragmentManager.beginTransaction()
                    .replace(R.id.homeFrame,fragment)
                    .addToBackStack("3")
                    .commit();

        }

    }
}


