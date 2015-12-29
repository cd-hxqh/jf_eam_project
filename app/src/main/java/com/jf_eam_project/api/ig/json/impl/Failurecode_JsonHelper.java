package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Failurecode;

import java.io.IOException;
import java.util.ArrayList;


public final class Failurecode_JsonHelper
        implements JsonHelper<Failurecode> {
    private static final String TAG = "Failurecode_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Failurecode> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Failurecode> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Failurecode>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Failurecode parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Failurecode
     */
    public static Failurecode parseFromJson(JsonParser jp)
            throws IOException {
        Failurecode instance = new Failurecode();

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

    public static boolean processSingleField(Failurecode instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("FAILURECODE".equals(fieldName)) {
            instance.failurecode = jp.getValueAsString();
            return true;
        } else if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
            return true;
        } else if ("FAILURECODEID".equals(fieldName)) {
            instance.failurecodeid = jp.getValueAsString();
        } else if ("ORGID".equals(fieldName)) {
            instance.orgid = jp.getValueAsString();
        }
        return false;
    }

    /**
     * 解析Failurecode_List*
     */
    public static ArrayList<Failurecode> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Failurecode*
     */
    public static Failurecode parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
