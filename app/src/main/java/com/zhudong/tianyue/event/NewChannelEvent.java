package com.zhudong.tianyue.event;

import com.zhudong.tianyue.bean.Channel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dzhu on 18-1-16.
 */
public class NewChannelEvent {
    public List<Channel> selectedDatas;

    public List<Channel> unSelectedDatas;

    public List<Channel> allChannels;

    /**
     * 添加的第一个频道名称
     */
    public String firstChannelName;

    public NewChannelEvent(List<Channel> allChannels, String firstChannelName) {
        if (allChannels == null) return;
        this.allChannels = allChannels;
        this.firstChannelName = firstChannelName;

        selectedDatas = new ArrayList<>();
        unSelectedDatas = new ArrayList<>();

        Iterator iterator = allChannels.iterator();
        while (iterator.hasNext()) {
            Channel channel = (Channel) iterator.next();
            if (channel.getItemType() == Channel.TYPE_MY || channel.getItemType() == Channel.TYPE_OTHER) {
                iterator.remove();
            } else if (channel.getItemType() == Channel.TYPE_MY_CHANNEL) {
                selectedDatas.add(channel);
            } else {
                unSelectedDatas.add(channel);
            }
        }
    }
}
