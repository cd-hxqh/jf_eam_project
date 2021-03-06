package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Woactivity;

import java.io.IOException;
import java.util.ArrayList;


public final class Woactivity_JsonHelper
        implements JsonHelper<Woactivity> {
    private static final String TAG = "Woactivity_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Woactivity> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Woactivity> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Woactivity>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Woactivity parsed = parseFromJson(jp);
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
    public static Woactivity parseFromJson(JsonParser jp)
            throws IOException {
        Woactivity instance = new Woactivity();

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

    public static boolean processSingleField(Woactivity instance, String fieldName, JsonParser jp)
            throws IOException {

        if ("TASKID".equals(fieldName)) {
            instance.taskid = jp.getValueAsString();
            return true;
        } else if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
            return true;
        } else if ("ASSETNUM".equals(fieldName)) {
            instance.assetnum = jp.getValueAsString();
            return true;
        } else if ("ASSETDESC".equals(fieldName)) {
            instance.assetdesc = jp.getValueAsString();
            return true;
        } else if ("LOCATION".equals(fieldName)) {
            instance.location = jp.getValueAsString();
        } else if ("LOCATIONDESC".equals(fieldName)) {
            instance.locationdesc = jp.getValueAsString();
            return true;
        } else if ("WOCLASS".equals(fieldName)) {
            instance.woclass = jp.getValueAsString();
            return true;
        } else if ("WOSEQUENCE".equals(fieldName)) {
            instance.wosequence = jp.getValueAsString();
            return true;
        } else if ("WONUM".equals(fieldName)) {
            instance.wonum = jp.getValueAsString();
            return true;
        } else if ("ESTDUR".equals(fieldName)) {
            instance.estdur = jp.getValueAsString();
            return true;
        } else if ("STATUS".equals(fieldName)) {
            instance.status = jp.getValueAsString();
            return true;
        } else if ("OWNER".equals(fieldName)) {
            instance.owner = jp.getValueAsString();
            return true;
        } else if ("OWNERGROUP".equals(fieldName)) {
            instance.ownergroup = jp.getValueAsString();
            return true;
        } else if ("TARGSTARTDATE".equals(fieldName)) {
            instance.targstartdate = jp.getValueAsString();
            return true;
        } else if ("TARGCOMPDATE".equals(fieldName)) {
            instance.targcompdate = jp.getValueAsString();
            return true;
        } else if ("ACTSTART".equals(fieldName)) {
            instance.actstart = jp.getValueAsString();
            return true;
        } else if ("ACTFINISH".equals(fieldName)) {
            instance.actfinish = jp.getValueAsString();
            return true;
        }

        return false;
    }

    /**
     * 解析WorkOrder_List*
     */
    public static ArrayList<Woactivity> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析WorkOrder*
     */
    public static Woactivity parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
