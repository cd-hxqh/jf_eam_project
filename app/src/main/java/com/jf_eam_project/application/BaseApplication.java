package com.jf_eam_project.application;

import android.app.Application;

import com.jf_eam_project.config.Constants;
import com.jf_eam_project.webserviceclient.AndroidClientService;


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

        mContext = this;
    }


}
