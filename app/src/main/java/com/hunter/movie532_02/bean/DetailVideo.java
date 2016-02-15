package com.hunter.movie532_02.bean;

import com.hunter.movie532_02.constants.UrlConstant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by zxt on 2016/1/13.
 */
public class DetailVideo {
    private int id;
    private long doubanid;
    int cid;
    String title;
    ArrayList<String> tags;
    ArrayList<String> actor;
    ArrayList<String> director;
    ArrayList<String> writer;
    String content;
    String picurl;
    String area;
    String language;
    String duration;
    int year;
    String pubdate;
    String serial;
    long addtime;
    private int hits;
    private int monthhits;
    private int weekhits;
    private int dayhits;
    private int hitstime;
    private int stars;
    private int status;
    private int up;
    private int down;
    private int base;
    private ArrayList<String> hlsurl;
    private String letter;
    float score;
    private int scoreer;
    private int genuine;
    private String trailer;
    private ArrayList<String> posters;
    private ArrayList<String> stills;

    public DetailVideo() {
    }

    public DetailVideo(int id, long doubanid, int cid, String title, ArrayList<String> tags, ArrayList<String> actor, ArrayList<String> director, ArrayList<String> writer, String content, String picurl, String area, String language, String duration, int year, String pubdate, String serial, long addtime, int hits, int monthhits, int weekhits, int dayhits, int hitstime, int stars, int status, int up, int down, int base, ArrayList<String> hlsurl, String letter, float score, int scoreer, int genuine, String trailer, ArrayList<String> posters, ArrayList<String> stills) {
        this.id = id;
        this.doubanid = doubanid;
        this.cid = cid;
        this.title = title;
        this.tags = tags;
        this.actor = actor;
        this.director = director;
        this.writer = writer;
        this.content = content;
        this.picurl = picurl;
        this.area = area;
        this.language = language;
        this.duration = duration;
        this.year = year;
        this.pubdate = pubdate;
        this.serial = serial;
        this.addtime = addtime;
        this.hits = hits;
        this.monthhits = monthhits;
        this.weekhits = weekhits;
        this.dayhits = dayhits;
        this.hitstime = hitstime;
        this.stars = stars;
        this.status = status;
        this.up = up;
        this.down = down;
        this.base = base;
        this.hlsurl = hlsurl;
        this.letter = letter;
        this.score = score;
        this.scoreer = scoreer;
        this.genuine = genuine;
        this.trailer = trailer;
        this.posters = posters;
        this.stills = stills;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDoubanid() {
        return doubanid;
    }

    public void setDoubanid(long doubanid) {
        this.doubanid = doubanid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public long getAddtime() {
        return addtime;
    }

    public void setAddtime(long addtime) {
        this.addtime = addtime;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getMonthhits() {
        return monthhits;
    }

    public void setMonthhits(int monthhits) {
        this.monthhits = monthhits;
    }

    public int getWeekhits() {
        return weekhits;
    }

    public void setWeekhits(int weekhits) {
        this.weekhits = weekhits;
    }

    public int getDayhits() {
        return dayhits;
    }

    public void setDayhits(int dayhits) {
        this.dayhits = dayhits;
    }

    public int getHitstime() {
        return hitstime;
    }

    public void setHitstime(int hitstime) {
        this.hitstime = hitstime;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public ArrayList<String> getHlsurl() {
        return hlsurl;
    }

    public void setHlsurl(ArrayList<String> hlsurl) {
        this.hlsurl = hlsurl;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getScoreer() {
        return scoreer;
    }

    public void setScoreer(int scoreer) {
        this.scoreer = scoreer;
    }

    public int getGenuine() {
        return genuine;
    }

    public void setGenuine(int genuine) {
        this.genuine = genuine;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public ArrayList<String> getPosters() {
        return posters;
    }

    public void setPosters(ArrayList<String> posters) {
        this.posters = posters;
    }

    public ArrayList<String> getStills() {
        return stills;
    }

    public void setStills(ArrayList<String> stills) {
        this.stills = stills;
    }
    public int getEpisodeNum(){ return hlsurl.size();}
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
    public String getEpisodeUrlString(int index){
        String urlString = UrlConstant.playBaseUrl+hlsurl.get(index);
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
