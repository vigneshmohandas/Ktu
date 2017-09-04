package com.ktulive;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by asnim on 04/09/17.
 */

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView branch_name;
    ClickListener clickListener;


    public ViewHolder(View itemView) {
        super(itemView);

        branch_name = (TextView)itemView.findViewById(R.id.branch_list_item_text);

        branch_name.setOnClickListener(this);
    }

    public  void setItemClickListener(ClickListener clickListener){
        this. clickListener = clickListener;



    }

    @Override
    public void onClick(View view) {


        clickListener.onClick(view,getAdapterPosition(),false);
    }

}