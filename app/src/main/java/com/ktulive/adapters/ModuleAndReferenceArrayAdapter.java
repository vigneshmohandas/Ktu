package com.ktulive.adapters;

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
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by asnim on 26/05/17.
 */

public class ModuleAndReferenceArrayAdapter extends ArrayAdapter<IndividualModule> {
    Context context;
    int layoutResourseID;
    ArrayList<IndividualModule> data  = null;
    Date news_date;
    public ModuleAndReferenceArrayAdapter(Context context, int layoutResourceId, ArrayList<IndividualModule> data){
        super(context,layoutResourceId,data);
        data = new ArrayList<>();
        this.layoutResourseID = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        View row = convertView;
        IndividualModuleHolder holder = null;
        if(row == null){
            row = LayoutInflater.from(context).inflate(R.layout.template_singlemodule,parent,false);
            holder = new IndividualModuleHolder();
            holder.moduleTitle = (TextView) row.findViewById(R.id.module_title);
            holder.moduleBody = (JustifiedTextView) row.findViewById(R.id.module_body);
            row.setTag(holder);

        }
        else{
            holder = (IndividualModuleHolder)row.getTag();
        }

        IndividualModule  individualModule = getItem(position);
        holder.moduleBody.setText(individualModule.description);
        holder.moduleTitle.setText("Module " + (position+1));
        return  row;
    }

    static class IndividualModuleHolder{
        TextView moduleTitle;
        JustifiedTextView moduleBody;
    }
}
