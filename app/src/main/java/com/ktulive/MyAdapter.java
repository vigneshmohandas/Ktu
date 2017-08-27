package com.ktulive;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ktulive.models.Branch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asnimpansari on 8/26/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    @ColorInt int colors[] = {
            0x80ff5718,
            0x800fb7a1,
            0x8080b352,
            0x80438ff5,
            0x80fbba2d,
            0x80d81d60,
            0x80cddc39
           

    };

    public MyAdapter(ArrayList<String> branchList, Context context) {
        this.branchList = branchList;
        this.context = context;
    }

    private List<String> branchList;
    private Context context;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.branch_list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String branch_name = branchList.get(position);
        holder.branch_name.setText(branch_name);
        holder.branch_name.setBackgroundColor(colors[position]);
//
//        switch (position){
//            case 0:
//                holder.branch_name.setBackgroundColor(Color.GREEN);
//                break;
//            case 1:
//                holder.branch_name.setBackgroundColor(Color.RED);
//
//                break;
//            case 2:
//                break;
//            case 3:
//                break;
//            case 4:
//                break;
//            case 5:
//                break;
//            case 6:
//                break;
//        }



    }

    @Override
    public int getItemCount() {
        return this.branchList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

       public TextView branch_name;

        public ViewHolder(View itemView) {
            super(itemView);
            branch_name = (TextView)itemView.findViewById(R.id.branch_list_item_text);
        }
    }
}
