package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Wpservice;

import java.io.IOException;
import java.util.ArrayList;


public final class Wpservice_JsonHelper
        implements JsonHelper<Wpservice> {
    private static final String TAG = "Wpservice_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Wpservice> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Wpservice> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Wpservice>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Wpservice parsed = parseFromJson(jp);
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
    public static Wpservice parseFromJson(JsonParser jp)
            throws IOException {
        Wpservice instance = new Wpservice();

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

    public static boolean processSingleField(Wpservice instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("LINETYPE".equals(fieldName)) {
            instance.linetype = jp.getValueAsString();
            return true;
        } else if ("REQUESTBY".equals(fieldName)) {
            instance.requestby = jp.getValueAsString();
            return true;
        } else if ("REQUIREDATE".equals(fieldName)) {
            instance.requiredate = jp.getValueAsString();
        } else if ("TASKID".equals(fieldName)) {
            instance.taskid = jp.getValueAsString();
            return true;
        }

        return false;
    }

    /**
     * 解析Wplabor_List*
     */
    public static ArrayList<Wpservice> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Wplabor*
     */
    public static Wpservice parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
