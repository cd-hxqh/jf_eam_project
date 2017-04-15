package com.jf_eam_project.utils;

import android.content.Context;
import android.util.Log;

import com.jf_eam_project.model.Createreport;
import com.jf_eam_project.model.Udreport;
import com.jf_eam_project.model.Xzwl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

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
            jsonObject.put("statustype", createreport.getStatustype());

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


    /**根据运行单位截取分公司的编号**/
    public static String CutOutBranch(String udbelong){
            return udbelong.substring(0,5);
    }




    /**
     * 选择物料
     */
    public static ArrayList<Xzwl> parsingXzwl(Context ctx, String data) {
        ArrayList<Xzwl> list = null;
        Xzwl xzwl = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Xzwl>();
            for (int i = 0; i < jsonArray.length(); i++) {
                xzwl = new Xzwl();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = xzwl.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = xzwl.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(xzwl);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = xzwl.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(xzwl, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(xzwl);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

}
