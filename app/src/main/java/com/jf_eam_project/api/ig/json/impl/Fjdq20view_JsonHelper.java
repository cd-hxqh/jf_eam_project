package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.FJDQ20VIEW;

import java.io.IOException;
import java.util.ArrayList;

/**故障统计20天以上**/
public final class Fjdq20view_JsonHelper
        implements JsonHelper<FJDQ20VIEW> {
    private static final String TAG = "Fjdq20view_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<FJDQ20VIEW> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<FJDQ20VIEW> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<FJDQ20VIEW>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                FJDQ20VIEW parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析FJDQ20VIEW
     */
    public static FJDQ20VIEW parseFromJson(JsonParser jp)
            throws IOException {
        FJDQ20VIEW instance = new FJDQ20VIEW();

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

    public static boolean processSingleField(FJDQ20VIEW instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("APPTYPE".equals(fieldName)) {
            instance.apptype = jp.getValueAsString();
            return true;
        } else if ("ASSETTYPE".equals(fieldName)) {
            instance.assettype = jp.getValueAsString();
            return true;
        } else if ("BRANCH".equals(fieldName)) {
            instance.branch = jp.getValueAsString();
        } else if ("CREATEDATE".equals(fieldName)) {
            instance.createdate = jp.getValueAsString();
        }else if ("CULEVEL".equals(fieldName)) {
            instance.culevel = jp.getValueAsString();
        }else if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
        }else if ("REPORTNUM".equals(fieldName)) {
            instance.reportnum = jp.getValueAsString();
        }else if ("STATUSTYPE".equals(fieldName)) {
            instance.statustype = jp.getValueAsString();
        }else if ("UDBELONG".equals(fieldName)) {
            instance.udbelong = jp.getValueAsString();
        }
        return false;
    }

    /**
     * 解析FJDQ20VIEW_List*
     */
    public static ArrayList<FJDQ20VIEW> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析FJDQ20VIEW*
     */
    public static FJDQ20VIEW parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
