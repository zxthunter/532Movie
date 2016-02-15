package com.hunter.movie532_02.bean;

import com.hunter.movie532_02.constants.UrlConstant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by zxt on 2016/1/12.
 */
public class Video {
    int id;
    String title;
    String picurl;
    ArrayList<String> actor;
    ArrayList<String> director;
    ArrayList<String> writer;
    String area;
    Long addTime;
    int hits;
    float score;
    int year;
    String content;

    public Video() {
    }

    public Video(int id, String title, String picurl, ArrayList<String> actor, ArrayList<String> director, ArrayList<String> writer, String area, Long addTime, int hits, float score, int year, String content) {
        this.id = id;
        this.title = title;
        this.picurl = picurl;
        this.actor = actor;
        this.director = director;
        this.writer = writer;
        this.area = area;
        this.addTime = addTime;
        this.hits = hits;
        this.score = score;
        this.year = year;
        this.content = content;
    }

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

    public ArrayList<String> getActor() {
        return actor;
    }

    public void setActor(ArrayList<String> actor) {
        this.actor = actor;
    }

    public ArrayList<String> getDirector() {
        return director;
    }

    public void setDirector(ArrayList<String> director) {
        this.director = director;
    }

    public ArrayList<String> getWriter() {
        return writer;
    }

    public void setWriter(ArrayList<String> writer) {
        this.writer = writer;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
