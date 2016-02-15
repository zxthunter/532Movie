package com.hunter.movie532_02.bean;

import com.hunter.movie532_02.constants.UrlConstant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by zxt on 2016/1/8.
 */
public class ShowMovie extends Header {
    private int id;
    private String title;
    private String picurl;
    private Long addtime;
    private int hits;
    private double score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public Long getAddtime() {
        return addtime;
    }

    public void setAddtime(Long addtime) {
        this.addtime = addtime;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public ShowMovie() {
    }

    public ShowMovie(int id, String title, String picurl, Long addtime, int hits, double score) {
        this.id = id;
        this.title = title;
        this.picurl = picurl;
        this.addtime = addtime;
        this.hits = hits;
        this.score = score;
    }
    public String getUrlString(){
        String urlString =  UrlConstant.imgBaseUrl+picurl;
        try {
            urlString = URLEncoder.encode(urlString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        urlString =  urlString.replaceAll("\\+", "%20");
        urlString =urlString .replaceAll("%3A",":").replaceAll("%2F","/");
        return urlString;
    }
}
