package com.hunter.movie532_02.bean;

import android.support.v4.app.Fragment;

/**
 * Created by zxt on 2016/1/31.
 */
public class Tab_main {
    private int name;
    private Class fragmentClass;
    private int image;

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public Class getFragmentClass() {
        return fragmentClass;
    }

    public void setFragmentClass(Class fragmentClass) {
        this.fragmentClass = fragmentClass;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Tab_main(int name, Class fragmentClass, int image) {

        this.name = name;
        this.fragmentClass = fragmentClass;
        this.image = image;
    }
}
