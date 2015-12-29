package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Jobplan;

import java.io.IOException;
import java.util.ArrayList;


public final class Jobplan_JsonHelper
        implements JsonHelper<Jobplan> {
    private static final String TAG = "Jobplan_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Jobplan> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Jobplan> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Jobplan>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Jobplan parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Jobplan
     */
    public static Jobplan parseFromJson(JsonParser jp)
            throws IOException {
        Jobplan instance = new Jobplan();

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

    public static boolean processSingleField(Jobplan instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("JPNUM".equals(fieldName)) {
            instance.jpnum = jp.getValueAsString();
            return true;
        } else if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
            return true;
        } else if ("TEMPLATETYPE".equals(fieldName)) {
            instance.templatetype = jp.getValueAsString();
        } else if ("ORGID".equals(fieldName)) {
            instance.orgid = jp.getValueAsString();
        } else if ("SITEID".equals(fieldName)) {
            instance.siteid = jp.getValueAsString();
        }
        return false;
    }

    /**
     * 解析Jobplan_List*
     */
    public static ArrayList<Jobplan> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Jobplan*
     */
    public static Jobplan parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
