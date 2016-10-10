package com.ly.specialeffectsbox.intefaces;


public interface ItemTouchCallback {

    /**
     * Called when an item has been dragged
     * @param oldPosition start position
     * @param newPosition end position
     */
    void itemTouchOnMove(int oldPosition, int newPosition);

    /**
     * Called when an item has been dragged
     * @param position want to delete position
     */
    void itemTouchOnSwiped(int position);

}
