package com.hunter.movie532_02.bean;

import java.util.ArrayList;

/**
 * Created by zxt on 2016/1/12.
 */
public class SearchBean {
    String keyword;
    ArrayList<Video> videos;

    public SearchBean() {
    }

    public SearchBean(String keyword, ArrayList<Video> videos) {
        this.keyword = keyword;
        this.videos = videos;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Video> videos) {
        this.videos = videos;
    }
}
