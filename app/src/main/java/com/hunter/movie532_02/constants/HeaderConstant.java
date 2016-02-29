package com.hunter.movie532_02.constants;

import java.util.HashMap;

/**
 * Created by zxt on 2016/1/31.
 */
public class HeaderConstant {
    public static int NUMBER_OF_MOVIE_PER_COLUMN = 6 ;
    private static HashMap<String,String> channelMap = null;
    static {
        channelMap = new HashMap<String,String>();
        channelMap.put("popular","热门影视");
        channelMap.put("fresh","最近更新");
        channelMap.put("dianying","电影");
        channelMap.put("dianshiju","电视剧");
        channelMap.put("dongman","动漫");
        channelMap.put("zongyi","综艺");
        channelMap.put("xiaoyuan","校园");
    }
    static public String parseChannelName(String name){
        return channelMap.get(name);
    }
}
