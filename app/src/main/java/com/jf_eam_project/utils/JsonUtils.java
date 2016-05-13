package com.jf_eam_project.utils;

import android.util.Log;

import com.jf_eam_project.model.Createreport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * json工具解析类
 */
public class JsonUtils {
    private final static String TAG = "JsonUtils";

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
            jsonObject.put("branck", createreport.getBranck());
            jsonObject.put("cubelong", createreport.getCubelong());
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
}
