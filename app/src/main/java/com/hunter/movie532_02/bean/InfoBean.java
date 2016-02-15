package com.hunter.movie532_02.bean;

import java.util.ArrayList;

/**
 * Created by zxt on 2016/1/17.
 */
public class InfoBean {
    long last_addtime;
    ArrayList<ShowMovie> videos;

    public InfoBean() {
    }

    public InfoBean(long last_addtime, ArrayList<ShowMovie> videos) {
        this.last_addtime = last_addtime;
        this.videos = videos;
    }

    public long getLast_addtime() {
        return last_addtime;
    }

    public void setLast_addtime(long last_addtime) {
        this.last_addtime = last_addtime;
    }

    public ArrayList<ShowMovie> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<ShowMovie> videos) {
        this.videos = videos;
    }
}
