package com.jf_eam_project.utils;

import android.util.Log;

import com.jf_eam_project.model.Createreport;
import com.jf_eam_project.model.Udreport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * json工具解析类
 */
public class JsonUtils {
    private final static String TAG = "JsonUtils";


    /**
     * 巡检单故障，缺陷提报单
     **/
    public static String saveReport(Createreport createreport) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            jsonObject.put("udinspojxxmid", createreport.getUdinspojxxmid());
            jsonObject.put("apptype", createreport.getReporttype());
            jsonObject.put("culevel", createreport.getCulevel());
            jsonObject.put("assettype", createreport.getAssettype());
            jsonObject.put("assetnum", createreport.getAssetnum());
            jsonObject.put("location", createreport.getLocation());
            jsonObject.put("description", createreport.getDescription());
            jsonObject.put("descriptionxx", createreport.getDescriptionxx());
            jsonObject.put("CREATEBY", createreport.getReportby());
            jsonObject.put("createdate", createreport.getReporttime());
            jsonObject.put("branch", createreport.getBranck());
            jsonObject.put("udbelong", createreport.getCubelong());
            jsonObject.put("failurecode", createreport.getFailurecode());

            JSONObject json = new JSONObject();
            json.put("", "");
            array.put(json);
            jsonObject.put("relationShip", array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i(TAG, jsonObject.toString());
        return jsonObject.toString();
    }


    /**
     * 故障提报单
     **/
    public static String saveUdreport(Udreport udreport) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            jsonObject.put("apptype", udreport.getApptype()); //类型

            jsonObject.put("assettype", udreport.getAssettype()); //设备专业

            jsonObject.put("culevel", udreport.getCulevel()); //故障等级

            jsonObject.put("location", udreport.getLocation()); //位置

            jsonObject.put("assetnum", udreport.getAssetnum()); //设备

            jsonObject.put("stoptime", udreport.getStoptime()); //停机时间

            jsonObject.put("failurecode", udreport.getFailurecode()); //故障类

            jsonObject.put("description", udreport.getDescription()); //描述

            jsonObject.put("cudescribe", udreport.getCudescribe()); //故障，隐患描述

            jsonObject.put("branch", udreport.getBranch_description()); //分公司

            jsonObject.put("udbelong", udreport.getUdbelong()); //运行单位

            jsonObject.put("CREATEBY", udreport.getCreateby_displayname()); //提报人

            jsonObject.put("createdate", udreport.getCreatedate()); //提报时间

            jsonObject.put("status", udreport.getStatus()); //状态


            JSONObject json = new JSONObject();
            json.put("", "");
            array.put(json);
            jsonObject.put("relationShip", array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i(TAG, jsonObject.toString());
        return jsonObject.toString();
    }

    /**
     * 缺陷提报单
     **/
    public static String saveQxUdreport(Udreport udreport) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            jsonObject.put("apptype", udreport.getApptype()); //类型

            jsonObject.put("assettype", udreport.getAssettype()); //设备专业

            jsonObject.put("qxtype", udreport.getQxtype()); //缺陷类型

            jsonObject.put("qxsjly", udreport.getQxsjly()); //数据来源

            jsonObject.put("culevel", udreport.getCulevel()); //缺陷等级

            jsonObject.put("location", udreport.getLocation()); //位置

            jsonObject.put("assetnum", udreport.getAssetnum()); //设备


            jsonObject.put("failurecode", udreport.getFailurecode()); //故障类

            jsonObject.put("description", udreport.getDescription()); //描述

            jsonObject.put("cudescribe", udreport.getCudescribe()); //故障，隐患描述

            jsonObject.put("branch", udreport.getBranch_description()); //分公司

            jsonObject.put("udbelong", udreport.getUdbelong()); //运行单位

            jsonObject.put("CREATEBY", udreport.getCreateby_displayname()); //提报人

            jsonObject.put("createdate", udreport.getCreatedate()); //提报时间
            jsonObject.put("status", udreport.getStatus()); //状态


            JSONObject json = new JSONObject();
            json.put("", "");
            array.put(json);
            jsonObject.put("relationShip", array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i(TAG, jsonObject.toString());
        return jsonObject.toString();
    }
}
