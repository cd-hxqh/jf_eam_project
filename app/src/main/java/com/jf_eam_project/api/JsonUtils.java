package com.jf_eam_project.api;

import android.content.Context;
import android.util.Log;

import com.jf_eam_project.bean.Results;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Assignment;
import com.jf_eam_project.model.Labtrans;
import com.jf_eam_project.model.Webservice_result;
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.model.Wplabor;
import com.jf_eam_project.model.Wpmaterial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Json数据解析封装类
 */
public class JsonUtils {
    private static final String TAG = "JsonUtils";


    /**
     * 解析登录信息*
     */
    public static String parsingAuthStr(final Context cxt, String data) {
        Log.i(TAG, "data=" + data);
        String isSuccess = null;
        String errmsg = null;
        try {
            JSONObject json = new JSONObject(data);
            String jsonString = json.getString("errcode");
            if (jsonString.equals(Constants.LOGINSUCCESS) || jsonString.equals(Constants.CHANGEIMEI)) {
                errmsg = json.getString("errmsg");
            }

            return errmsg;


        } catch (JSONException e) {
            e.printStackTrace();
            return isSuccess;
        }
    }

    public static Webservice_result parsingWebservice_result(String data) {
        Log.i(TAG, "data=" + data);
        Webservice_result webserviceResult = null;
        try {
            JSONObject object = new JSONObject(data);
            webserviceResult.setWoNum(object.getString("woNum"));
            webserviceResult.setErrorMsg(object.getString("errorMsg"));
            webserviceResult.setErrorNo(object.getString("errorNo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return webserviceResult;
    }

    /**
     * 分页解析返回的结果*
     */
    public static Results parsingResults(Context ctx, String data) {
        Log.i(TAG, "data=" + data);
        String result = null;
        Results results = null;
        try {
            JSONObject json = new JSONObject(data);
            String jsonString = json.getString("errcode");
            if (jsonString.equals(Constants.GETDATASUCCESS)) {
                result = json.getString("result");
                JSONObject rJson = new JSONObject(result);
                String curpage = rJson.getString("curpage");
                String totalresult = rJson.getString("totalresult");
                String resultlist = rJson.getString("resultlist");
                String totalpage = rJson.getString("totalpage");
                String showcount = rJson.getString("showcount");
                results = new Results();
                results.setCurpage(Integer.valueOf(curpage));
                results.setTotalresult(totalresult);
                results.setResultlist(resultlist);
                results.setTotalpage(totalpage);
                results.setShowcount(Integer.valueOf(showcount));
            } else {
                results = new Results();
            }

            return results;


        } catch (JSONException e) {
            e.printStackTrace();
            return results;
        }

    }

    /**
     * 不分页解析返回的结果*
     */
    public static String parsingResults1(Context ctx, String data) {

        String result = null;
        try {
            JSONObject json = new JSONObject(data);
            String jsonString = json.getString("errcode");
            if (jsonString.equals(Constants.GETDATASUCCESS)) {
                result = json.getString("result");
            }

            return result;


        } catch (JSONException e) {
            e.printStackTrace();
            return result;
        }
    }

    public static String WorkToJson(WorkOrder workOrder, ArrayList<Woactivity> woactivities, ArrayList<Wplabor> wplabors,
                                    ArrayList<Wpmaterial> wpmaterials, ArrayList<Assignment> assignments, ArrayList<Labtrans> labtranses) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (!workOrder.wonum.equals("")) {
                jsonObject.put("wonum", workOrder.wonum);
            }
            jsonObject.put("UDAPPTYPE", "UDWOTRACK");
            jsonObject.put("description", workOrder.description);
            jsonObject.put("udwotype", workOrder.udwotype);
            jsonObject.put("assetnum", workOrder.assetnum);
            jsonObject.put("assetdesc", workOrder.assetdesc);
            jsonObject.put("location", workOrder.location);
            jsonObject.put("locationdesc", workOrder.locationdesc);
            jsonObject.put("status", workOrder.status);
            jsonObject.put("statusdate", workOrder.statusdate);
            jsonObject.put("lctype", workOrder.lctype);
            jsonObject.put("failurecode", workOrder.failurecode);
            jsonObject.put("problemcode", workOrder.problemcode);
            jsonObject.put("displayname", workOrder.displayname);
            jsonObject.put("createdate", workOrder.createdate);
            jsonObject.put("jpnum", workOrder.jpnum);
            jsonObject.put("targstartdate", workOrder.targstartdate);
            jsonObject.put("targcompdate", workOrder.targcompdate);
            jsonObject.put("actstart", workOrder.actstart);
            jsonObject.put("actfinish", workOrder.actfinish);
            jsonObject.put("reportedby", workOrder.reportedby);
            jsonObject.put("reportdate", workOrder.reportdate);

            JSONObject object = new JSONObject();
            if (woactivities != null && woactivities.size() != 0) {
                object.put("WOACTIVITY", "");
                JSONArray woactivityArray = new JSONArray();
                JSONObject woactivityObj;
                for (int i = 0; i < woactivities.size(); i++) {
                    woactivityObj = new JSONObject();
                    woactivityObj.put("taskid", woactivities.get(i).taskid);
                    woactivityObj.put("description", woactivities.get(i).description);
                    woactivityObj.put("targstartdate", woactivities.get(i).targstartdate);
                    woactivityObj.put("targcompdate", woactivities.get(i).targcompdate);
                    woactivityObj.put("actstart", woactivities.get(i).actstart);
                    woactivityObj.put("actfinish", woactivities.get(i).actfinish);
                    woactivityObj.put("estdur", woactivities.get(i).estdur);
                    woactivityArray.put(woactivityObj);
                }
                jsonObject.put("WOACTIVITY", woactivityArray);
            }
            if (wplabors != null && wplabors.size() != 0) {
                object.put("WPLABOR", "");
                JSONArray wplaborArray = new JSONArray();
                JSONObject wplaborObj;
                for (int i = 0; i < wplabors.size(); i++) {
                    wplaborObj = new JSONObject();
                    wplaborObj.put("taskid", wplabors.get(i).taskid);
                    wplaborObj.put("craft", wplabors.get(i).craft);
                    wplaborObj.put("quantity", wplabors.get(i).quantity);
                    wplaborObj.put("laborhrs", wplabors.get(i).laborhrs);
                    wplaborArray.put(wplaborObj);
                }
                jsonObject.put("WPLABOR", wplaborArray);
            }
            if (wpmaterials != null && wpmaterials.size() != 0) {
                object.put("WPMATERIAL", "");
                JSONArray wpmaterialArray = new JSONArray();
                JSONObject wpmaterialObj;
                for(int i = 0;i < wpmaterials.size();i ++){
                    wpmaterialObj = new JSONObject();
                    wpmaterialObj.put("taskid",wpmaterials.get(i).taskid);
                    wpmaterialObj.put("itemnum",wpmaterials.get(i).itemnum);
                    wpmaterialObj.put("itemqty",wpmaterials.get(i).itemqty);
                    wpmaterialObj.put("location",wpmaterials.get(i).location);
                    wpmaterialObj.put("storelocsite",wpmaterials.get(i).storelocsite);
                    wpmaterialObj.put("requestby",wpmaterials.get(i).requestby);
                    wpmaterialObj.put("requiredate",wpmaterials.get(i).requiredate);
                    wpmaterialArray.put(wpmaterialObj);
                }
                jsonObject.put("WPMATERIAL",wpmaterialArray);
            }
            if (assignments != null && assignments.size() != 0) {
                object.put("ASSIGNMENT", "");
                JSONArray assignmentArray = new JSONArray();
                JSONObject assignmentObj;
                for(int i = 0;i < assignments.size();i ++){
                    assignmentObj = new JSONObject();
                    assignmentObj.put("taskid",assignments.get(i).taskid);
                    assignmentObj.put("laborcode",assignments.get(i).laborcode);
                    assignmentObj.put("craft",assignments.get(i).craft);
                    assignmentObj.put("laborhrs",assignments.get(i).laborhrs);
                    assignmentArray.put(assignmentObj);
                }
                jsonObject.put("ASSIGNMENT",assignmentArray);
            }
            if (labtranses != null && labtranses.size() != 0) {
                object.put("LABTRANS", "");
                JSONArray labtransArray = new JSONArray();
                JSONObject labtransObj;
                for(int i = 0;i < labtranses.size();i ++){
                    labtransObj = new JSONObject();
                    labtransObj.put("taskid",labtranses.get(i).taskid);
                    labtransObj.put("laborcode",labtranses.get(i).laborcode);
                    labtransObj.put("startdate",labtranses.get(i).startdate);
                    labtransObj.put("regularhrs",labtranses.get(i).regularhrs);
                    labtransObj.put("craft",labtranses.get(i).craft);
                    labtransObj.put("payrate",labtranses.get(i).payrate);
                    labtransObj.put("transtype",labtranses.get(i).transtype);
                    labtransArray.put(labtransObj);
                }
                jsonObject.put("LABTRANS",labtransArray);
            }
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(object);
            jsonObject.put("relationShip",jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

}