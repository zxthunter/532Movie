package com.hunter.movie532_02;

import android.app.Application;
import android.os.Environment;

import com.activeandroid.ActiveAndroid;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;

/**
 * Created by zxt on 2016/1/31.
 */
public class mApplication extends com.activeandroid.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        Fresco.initialize(this);
    }
}
