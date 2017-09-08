package com.ktulive.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ktulive.R;
import com.ktulive.extra.Constants;

/**
 * Created by asnim on 06/09/17.
 */

public class SemsterTilesGridAdapter extends BaseAdapter{


    int count = 0;
    Context context;


    public SemsterTilesGridAdapter(Context context,int count){
        this.count = count;

        this.context = context;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View grid;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null){


//            grid = new View(context);context
            grid = inflater.inflate(R.layout.template_sem_number_layout,null);
            TextView tv = (TextView) grid.findViewById(R.id.sem_tile_text);
            tv.setText(i+1+"");
            tv.setBackgroundColor(Constants.grid_colors[i]);
            Log.e("I = ",Constants.grid_colors[i]+"");
        }
        else {
            grid =  view;
        }
        return grid;
    }
}
