package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Wpmaterial;

import java.io.IOException;
import java.util.ArrayList;


public final class Wpmaterial_JsonHelper
        implements JsonHelper<Wpmaterial> {
    private static final String TAG = "WorkOrder_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Wpmaterial> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Wpmaterial> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Wpmaterial>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Wpmaterial parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Wplabor
     */
    public static Wpmaterial parseFromJson(JsonParser jp)
            throws IOException {
        Wpmaterial instance = new Wpmaterial();

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

    public static boolean processSingleField(Wpmaterial instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("ITEMNUM".equals(fieldName)) {
            instance.itemnum = jp.getValueAsString();
            return true;
        } else if ("REQUESTBY".equals(fieldName)) {
            instance.requestby = jp.getValueAsString();
            return true;
        }else if ("TASKID".equals(fieldName)) {
            instance.taskid = jp.getValueAsString();
            return true;
        } else if ("ITEMQTY".equals(fieldName)) {
            instance.itemqty = jp.getValueAsString();
            return true;
        } else if ("LOCATION".equals(fieldName)) {
            instance.location = jp.getValueAsString();
        } else if ("REQUIREDATE".equals(fieldName)) {
            instance.requiredate = jp.getValueAsString();
            return true;
        } else if ("RESTYPE".equals(fieldName)) {
            instance.restype = jp.getValueAsString();
            return true;
        } else if ("STORELOCSITE".equals(fieldName)) {
            instance.storelocsite = jp.getValueAsString();
            return true;
        } else if ("UNITCOST".equals(fieldName)) {
            instance.unitcost = jp.getValueAsString();
            return true;
        }


        return false;
    }

    /**
     * 解析Wplabor_List*
     */
    public static ArrayList<Wpmaterial> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Wplabor*
     */
    public static Wpmaterial parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
