package com.jf_eam_project.application;

import android.app.Application;
import android.content.Context;

import com.jf_eam_project.config.Constants;
import com.jf_eam_project.webserviceclient.AndroidClientService;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;

import java.io.File;


/**
 * Created by think on 2015/8/11.
 */
public class BaseApplication extends Application {
    private String username;
    private static BaseApplication mContext;
    private String OrderResult;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderResult() {
        return OrderResult;
    }

    public void setOrderResult(String orderResult) {
        OrderResult = orderResult;
    }

    public static BaseApplication getInstance(){
        return mContext;
    }

    public AndroidClientService getWsService() {
        return new AndroidClientService(Constants.getWsUrl(this));
    }

    public AndroidClientService getWfService() {
        return new AndroidClientService(Constants.getWfUrl(this));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
        mContext = this;
    }

    public static void initImageLoader(Context context) {
        // 获取缓存图片目录
        File cacheDir = StorageUtils.getOwnCacheDirectory(context,
                "qebb/imageloader/Cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.MAX_PRIORITY - 1)
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .tasksProcessingOrder(QueueProcessingType.FIFO)// 设置图片下载和显示的工作队列排序
                .denyCacheImageMultipleSizesInMemory()
                        //		.memoryCacheExtraOptions(400, 800)
                .threadPoolSize(2)
                .diskCacheSize(40 * 1024 * 1024)
                        //		.memoryCacheSize(1 * 1024 * 1024)
                        //		.memoryCache(new FIFOLimitedMemoryCache())
                        //		.memoryCacheSize(3 * 1024 * 1024)
                        //		.memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                        //		.diskCacheExtraOptions(320, 480, null)
                .imageDownloader(
                        new BaseImageDownloader(context, 10 * 1000, 30 * 1000)) .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }
}
