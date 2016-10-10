package com.ly.specialeffectsbox.intefaces;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;



public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {

    ItemTouchCallback itemTouchCallback; // interface

    public MyItemTouchHelperCallback(ItemTouchCallback callbackItemTouch) {
        this.itemTouchCallback = callbackItemTouch;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true; // swiped disabled
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN; // movements drag
        int swipeFlags = ItemTouchHelper.START| ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags); // as parameter, action drag and flags drag
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Log.e("SAX", "onMove: ");
        itemTouchCallback.itemTouchOnMove(viewHolder.getAdapterPosition(), target.getAdapterPosition()); // information to the interface
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.e("SAX", "onSwiped: ");
        itemTouchCallback.itemTouchOnSwiped(viewHolder.getAdapterPosition());
    }
}
