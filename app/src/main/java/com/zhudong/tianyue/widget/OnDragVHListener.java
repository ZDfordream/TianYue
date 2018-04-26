package com.zhudong.tianyue.widget;

/**
 * ViewHolder 被选中 以及 拖拽释放 触发监听器
 * Created by dzhu on 18-1-16.
 */

public interface OnDragVHListener {

    /**
     * Item被选中时触发
     */
    void onItemSelected();

    /**
     * Item在拖拽结束/滑动结束后触发
     */
    void onItemFinish();
}
