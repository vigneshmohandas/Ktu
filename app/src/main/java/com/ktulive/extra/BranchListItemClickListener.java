package com.ktulive.extra;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by asnimpansari on 8/26/17.
 */

public class BranchListItemClickListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener listener;

    GestureDetector gestureDetector;



    public interface OnItemClickListener{
        public void onItemClick(View view,int position);
    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(),e.getY());
        if(childView!=null && listener!=null && gestureDetector.onTouchEvent(e)){
            listener.onItemClick(childView,rv.getChildAdapterPosition(childView));

        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public BranchListItemClickListener(Context context,OnItemClickListener listener){
        this.listener = listener;
        gestureDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                Log.e("EVE","SINGLE TAP");
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
    }
}
