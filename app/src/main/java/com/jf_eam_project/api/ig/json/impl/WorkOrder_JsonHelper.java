package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.WorkOrder;

import java.io.IOException;
import java.util.ArrayList;


public final class WorkOrder_JsonHelper
        implements JsonHelper<WorkOrder> {
    private static final String TAG = "WorkOrder_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<WorkOrder> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<WorkOrder> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<WorkOrder>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                WorkOrder parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析WorkOrder
     */
    public static WorkOrder parseFromJson(JsonParser jp)
            throws IOException {
        WorkOrder instance = new WorkOrder();

        // validate that we're on the right token
        if (jp.getCurrentToken() != JsonToken.START_OBJECT) {
            jp.skipChildren();
            return null;
        }

        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jp.getCurrentName();
            jp.nextToken();
            processSingleField(instance, fieldName, jp);
            jp.skipChildren();
        }

        return instance;
    }

    public static boolean processSingleField(WorkOrder instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("WONUM".equals(fieldName)) {
            instance.setWonum(jp.getValueAsString());
            return true;
        } else if ("ACTSTART".equals(fieldName)) {
            instance.setActstart(jp.getValueAsString());
            return true;
        } else if ("ACTFINISH".equals(fieldName)) {
            instance.setActfinish(jp.getValueAsString());
        } else if ("ASSETNUM".equals(fieldName)) {
            instance.setAssetnum(jp.getValueAsString());
            return true;
        } else if ("ASSETDESC".equals(fieldName)) {
            instance.setAssetdesc(jp.getValueAsString());
            return true;
        } else if ("DESCRIPTION".equals(fieldName)) {
            instance.setDescription(jp.getValueAsString());
            return true;
        } else if ("ESTDUR".equals(fieldName)) {
            instance.setEstdur(jp.getValueAsString());
            return true;
        } else if ("JPNUM".equals(fieldName)) {
            instance.setJpnum(jp.getValueAsString());
            return true;
        } else if ("JPNUMDESC".equals(fieldName)) {
            instance.setJpnumdesc(jp.getValueAsString());
            return true;
        } else if ("LOCATION".equals(fieldName)) {
            instance.setLocation(jp.getValueAsString());
            return true;
        } else if ("LOCATIONDESC".equals(fieldName)) {
            instance.setLocationdesc(jp.getValueAsString());
            return true;
        } else if ("ONBEHALFOF".equals(fieldName)) {
            instance.setOnbehalfof(jp.getValueAsString());
            return true;
        } else if ("PONUM".equals(fieldName)) {
            instance.setPmnum(jp.getValueAsString());
            return true;
        } else if ("PMDESC".equals(fieldName)) {
            instance.setPmdesc(jp.getValueAsString());
            return true;
        } else if ("REPORTDATE".equals(fieldName)) {
            instance.setReportdate(jp.getValueAsString());
            return true;
        } else if ("STATUS".equals(fieldName)) {
            instance.setStatus(jp.getValueAsString());
            return true;
        } else if ("STATUSDESC".equals(fieldName)) {
            instance.setStatusdesc(jp.getValueAsString());
            return true;
        } else if ("UDWOTYPE".equals(fieldName)) {
            instance.setUdwotype(jp.getValueAsString());
            return true;
        } else if ("UDWOTYPEDESC".equals(fieldName)) {
            instance.setUdwotypedesc(jp.getValueAsString());
            return true;
        } else if ("WORKTYPE".equals(fieldName)) {
            instance.setWorktype(jp.getValueAsString());
            return true;
        } else if ("WORKTYPE".equals(fieldName)) {
            instance.setWorktype(jp.getValueAsString());
            return true;
        } else if ("PARENT".equals(fieldName)) {
            instance.parent = jp.getValueAsString();
            return true;
        } else if ("UDPROJECTNUM".equals(fieldName)) {
            instance.udprojectnum = jp.getValueAsString();
            return true;
        } else if ("STATUSDATE".equals(fieldName)) {
            instance.statusdate = jp.getValueAsString();
            return true;
        } else if ("LCTYPE".equals(fieldName)) {
            instance.lctype = jp.getValueAsString();
            return true;
        } else if ("WOCLASS".equals(fieldName)) {
            instance.woclass = jp.getValueAsString();
            return true;
        } else if ("FAILURECODE".equals(fieldName)) {
            instance.failurecode = jp.getValueAsString();
            return true;
        } else if ("PROBLEMCODE".equals(fieldName)) {
            instance.problemcode = jp.getValueAsString();
            return true;
        } else if ("CREATEBY.DISPLAYNAME".equals(fieldName)) {
            instance.displayname = jp.getValueAsString();
            return true;
        } else if ("CREATEDATE".equals(fieldName)) {
            instance.createdate = jp.getValueAsString();
            return true;
        } else if ("TARGSTARTDATE".equals(fieldName)) {
            instance.targstartdate = jp.getValueAsString();
            return true;
        } else if ("TARGCOMPDATE".equals(fieldName)) {
            instance.targcompdate = jp.getValueAsString();
            return true;
        } else if ("REPORTEDBY".equals(fieldName)) {
            instance.reportedby = jp.getValueAsString();
            return true;
        }


        return false;
    }

    /**
     * 解析WorkOrder_List*
     */
    public static ArrayList<WorkOrder> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析WorkOrder*
     */
    public static WorkOrder parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
