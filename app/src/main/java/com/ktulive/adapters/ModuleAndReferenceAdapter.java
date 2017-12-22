package com.ktulive.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ktulive.R;
import com.ktulive.extra.Constants;
import com.ktulive.models.IndividualModule;
import com.ktulive.models.Subjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asnimpansari on 8/26/17.
 */

public class ModuleAndReferenceAdapter extends RecyclerView.Adapter<ModuleAndReferenceAdapter.ViewHolder>{

   static List<IndividualModule> module_list;
    String branch_name;



    public ModuleAndReferenceAdapter(ArrayList<IndividualModule> branchList) {
        this.module_list = branchList;
        this.context = context;
        this.fm = fm;
    }

    private Context context;
    private FragmentManager fm;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.template_singlemodule,parent,false);
        return new ViewHolder(v,this.context,fm);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {



        holder.module_title.setText("MODULE " + (position + 1));
        holder.module_body.setText(module_list.get(position).description);
//        holder.wholeConstrain.setBackgroundColor(Color.GRAY);


    }

    @Override
    public int getItemCount() {
        return this.module_list.size();

    }


    public   List<IndividualModule> getBranchList(){
        return module_list;
    }

    /**
     * Created by asnim on 04/09/17.
     */

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView module_title;
        public TextView module_body;
        public ConstraintLayout wholeConstrain;
//       ClickListener clickListener;
        Context context;
        FragmentManager fragmentManager;

        public ViewHolder(View itemView, Context context, FragmentManager fragmentManager) {
            super(itemView);

            module_title = (TextView)itemView.findViewById(R.id.module_title);
            module_body = (TextView)itemView.findViewById(R.id.module_body);
            wholeConstrain = (ConstraintLayout) itemView.findViewById(R.id.wholeConstrain);

            this.context = context;
            this.fragmentManager = fragmentManager;
        }
    }
}


