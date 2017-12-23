package com.ktulive.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ktulive.R;
import com.ktulive.actvities.ModuleandReferenceDisplayActivity;
import com.ktulive.models.Subjects;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by asnim on 26/05/17.
 */

public class SubjectListingAdapter extends ArrayAdapter<Subjects> {
    Context context;
    int layoutResourseID;
    ArrayList<Subjects> data  = null;
    Date news_date;
    public SubjectListingAdapter(Context context, int layoutResourceId, ArrayList<Subjects> data){
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
            row = LayoutInflater.from(context).inflate(R.layout.row_subjectname,parent,false);
            holder = new IndividualModuleHolder();
            holder.moduleName = (TextView) row.findViewById(R.id.sem_tile_text);


            row.setTag(holder);

        }
        else{
            holder = (IndividualModuleHolder)row.getTag();
        }


        final Subjects individualModule = getItem(position);


        holder.moduleName.setText(individualModule.subject_name);


        return  row;


    }


    static class IndividualModuleHolder{
        TextView moduleName;

    }


}
