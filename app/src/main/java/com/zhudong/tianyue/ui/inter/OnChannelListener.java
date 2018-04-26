package com.zhudong.tianyue.ui.inter;

/**
 * Created by dzhu on 18-1-15.
 */

public interface OnChannelListener {

    void onItemMove(int starPos,int endPos);

    void onMoveToMyChannel(int starPos,int endPos);

    void onMoveToOtherChannel(int starPos,int endPos);

    void onFinish(String selectedChannelName);
}
