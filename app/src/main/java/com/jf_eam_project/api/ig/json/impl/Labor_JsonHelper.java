package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Labor;

import java.io.IOException;
import java.util.ArrayList;


public final class Labor_JsonHelper
        implements JsonHelper<Labor> {
    private static final String TAG = "Labor_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Labor> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Labor> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Labor>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Labor parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Labor
     */
    public static Labor parseFromJson(JsonParser jp)
            throws IOException {
        Labor instance = new Labor();

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

    public static boolean processSingleField(Labor instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("LABORCODE".equals(fieldName)) {
            instance.laborcode = jp.getValueAsString();
            return true;
        } else if ("ORGID".equals(fieldName)) {
            instance.orgid = jp.getValueAsString();
            return true;
        }
        return false;
    }

    /**
     * 解析Labor_List*
     */
    public static ArrayList<Labor> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Labor*
     */
    public static Labor parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
