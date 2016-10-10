package com.ly.specialeffectsbox.view;

import java.util.List;


public class ViewTrackController {
    private List<AnimateImageView> imageViewList;
    private AnimateImageView topView;
    private AnimateImageView topFollowerView;
    private int resetPosX, resetPosY;
    private int left, top;
    private int width, height;
    private int redus = 0;

    private ViewTrackController() {
    }

    private ViewTrackController(int w, int h) {
        width = w;
        height = h;
    }

    public static ViewTrackController create() {
        return new ViewTrackController();
    }

    public static ViewTrackController create(int w, int h) {
        return new ViewTrackController(w, h);
    }

    public void init(List<AnimateImageView> imageViewList) {


        this.imageViewList = imageViewList;

        int len = imageViewList.size();
        this.topView = imageViewList.get(len - 1);
        this.topFollowerView = imageViewList.get(len - 2);

        for (int i = 1; i < len; i++) {
            AnimateImageView view1 = imageViewList.get(i - 1);
            AnimateImageView view2 = imageViewList.get(i);
            view2.getSpringX().addListener(view1.getFollowerListenerX());
            view2.getSpringY().addListener(view1.getFollowerListenerY());
        }
    }

    /**
     * 拖动view的位置改变，后面的view会自动跟着变
     */
    public void onTopViewPosChanged(int xPos, int yPos) {
        // 第一个跟随者移动了，后面的跟随者会自动移动
        left = xPos;
        top = yPos;
        topFollowerView.animTo(xPos, yPos);
    }


    /**
     * 手指松开的时候调用
     */
    public void onRelease() {
        if (left < width / 2) {
            if (top < height / 2) {
                topView.onRelease(width - resetPosX - redus, height - resetPosY - redus);
            } else {
                topView.onRelease(width - resetPosX - redus, resetPosY);
            }
        } else {
            if (top < height / 2) {
                topView.onRelease(resetPosX, height - resetPosY - redus);
            } else {
                topView.onRelease(resetPosX, resetPosY);
            }
        }
//        if (left<width/2){
//            resetPosX=width-redus-resetPosX;
//        }
//        if (top<height/2){
//            resetPosY=height-redus-resetPosY;
//        }
//        topView.onRelease(resetPosX, resetPosY);
    }

    /**
     * 设置view最初的原始位置
     */
    public void setOriginPos(int xPos, int yPos) {
        resetPosX = xPos;
        resetPosY = yPos;
        int len = imageViewList.size();
        for (int i = 0; i < len; i++) {
            imageViewList.get(i).setCurrentSpringPos(xPos, yPos);
        }
    }

    public void setOriginPos(int xPos, int yPos, int redus) {
        resetPosX = xPos;
        resetPosY = yPos;
        this.redus=redus;

        int len = imageViewList.size();
        for (int i = 0; i < len; i++) {
            imageViewList.get(i).setCurrentSpringPos(xPos, yPos);
        }
    }
}
