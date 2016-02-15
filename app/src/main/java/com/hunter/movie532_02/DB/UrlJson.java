package com.hunter.movie532_02.DB;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by zxt on 2016/2/6.
 */
@Table(name = "UrlJson")
public class UrlJson extends Model{
    @Column(name = "urlString")
    public String urlString;
    @Column(name = "jsonString")
    public String jsonString;

    public UrlJson() {
    }

    public UrlJson( String urlString,String jsonString) {
        this.jsonString = jsonString;
        this.urlString = urlString;
    }
}
