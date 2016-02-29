package com.hunter.movie532_02.bean;

/**
 * Created by zxt on 2016/2/16.
 */
public class UpdateBean {
    private String version;
    private String downloadPath;
    private String updateDesc;

    public UpdateBean() {
    }

    public UpdateBean(String version, String downloadPath, String updateDesc) {
        this.version = version;
        this.downloadPath = downloadPath;
        this.updateDesc = updateDesc;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public String getUpdateDesc() {
        return updateDesc;
    }

    public void setUpdateDesc(String updateDesc) {
        this.updateDesc = updateDesc;
    }
}
