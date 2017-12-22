package com.ktulive.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.ktulive.R;
import com.ktulive.models.IndividualModule;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by asnim on 26/05/17.
 */

public class ModuleCardAdapter extends ArrayAdapter<IndividualModule> {
    Context context;
    int layoutResourseID;
    ArrayList<IndividualModule> data  = null;
    Date news_date;
    public ModuleCardAdapter(Context context, int layoutResourceId, ArrayList<IndividualModule> data){
        super(context,layoutResourceId,data);
        data = new ArrayList<>();
        this.layoutResourseID = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        super.getView(position, convertView, parent);
        View row = convertView;
        IndividualModuleHolder holder = null;
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourseID,parent,false);
            holder = new IndividualModuleHolder();
            row.setTag(holder);

        }
        else{
            holder = (IndividualModuleHolder)row.getTag();
        }
        IndividualModule individualModule = getItem(position);
        holder.moduleName.setText("MODULE " + (position + 1));
            holder.moduleContent.setText(individualModule.description);

        return  row;


    }


   public static class IndividualModuleHolder{
        TextView moduleName;
        TextView moduleContent;
    }
}
