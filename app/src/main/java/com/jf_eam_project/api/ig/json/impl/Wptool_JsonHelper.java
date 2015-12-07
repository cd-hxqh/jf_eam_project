package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Wptool;

import java.io.IOException;
import java.util.ArrayList;


public final class Wptool_JsonHelper
        implements JsonHelper<Wptool> {
    private static final String TAG = "Wptool_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Wptool> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Wptool> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Wptool>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Wptool parsed = parseFromJson(jp);
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
    public static Wptool parseFromJson(JsonParser jp)
            throws IOException {
        Wptool instance = new Wptool();

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

    public static boolean processSingleField(Wptool instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("ITEMNUM".equals(fieldName)) {
            instance.itemnum = jp.getValueAsString();
            return true;
        } else if ("ITEMQTY".equals(fieldName)) {
            instance.itemqty = jp.getValueAsString();
            return true;
        } else if ("RATE".equals(fieldName)) {
            instance.rate = jp.getValueAsString();
        } else if ("TASKID".equals(fieldName)) {
            instance.taskid = jp.getValueAsString();
            return true;
        }

        return false;
    }

    /**
     * 解析Wplabor_List*
     */
    public static ArrayList<Wptool> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Wplabor*
     */
    public static Wptool parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
