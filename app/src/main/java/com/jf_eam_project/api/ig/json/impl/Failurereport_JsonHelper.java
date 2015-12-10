package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Failurereport;

import java.io.IOException;
import java.util.ArrayList;


public final class Failurereport_JsonHelper
        implements JsonHelper<Failurereport> {
    private static final String TAG = "Failurereport_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Failurereport> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Failurereport> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Failurereport>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Failurereport parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Failurereport
     */
    public static Failurereport parseFromJson(JsonParser jp)
            throws IOException {
        Failurereport instance = new Failurereport();

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

    public static boolean processSingleField(Failurereport instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("TYPE".equals(fieldName)&&jp.getValueAsString().equals("PROBLEM")) {
            instance.problem = jp.getValueAsString();
            return true;
        } else if ("TYPE".equals(fieldName)&&jp.getValueAsString().equals("CAUSE")) {
            instance.cause = jp.getValueAsString();
            return true;
        } else if ("TYPE".equals(fieldName)&&jp.getValueAsString().equals("REMEDY")) {
            instance.remedy = jp.getValueAsString();
        }
        return false;
    }

    /**
     * 解析Failurereport_List*
     */
    public static ArrayList<Failurereport> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Failurereport*
     */
    public static Failurereport parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
