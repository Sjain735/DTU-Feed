package com.yatin.whatshappeningdtu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yatin on 31-03-2017.
 */

public class RItemClickListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener mlistener;

    public interface OnItemClickListener {

        public void onItemClick(View view, int position);

        public void onLongItemClick(View view, int position);
    }

    GestureDetector mgesturedetector;

    public RItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
      mlistener = listener;
       mgesturedetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
           @Override
           public boolean onSingleTapUp(MotionEvent e) {
              return true;
            }

          @Override
          public void onLongPress(MotionEvent e) {
               View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
               if (child != null && mlistener != null) {
                   mlistener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
               }
            }
        });

   }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
         View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mlistener != null && mgesturedetector.onTouchEvent(e)) {
            mlistener.onItemClick(childView, rv.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {}

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) { }
}
