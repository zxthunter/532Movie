package com.hunter.movie532_02.bean;

/**
 * Created by zxt on 2016/1/16.
 */
public class Channel {
    int id;
    int pid;
    int oid;
    String cname;

    public Channel() {
    }

    public Channel(int id, int pid, int oid, String cname) {
        this.id = id;
        this.pid = pid;
        this.oid = oid;
        this.cname = cname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
