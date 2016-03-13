package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Assets;

import java.io.IOException;
import java.util.ArrayList;


public final class Asset_JsonHelper
        implements JsonHelper<Assets> {
    private static final String TAG = "Asset_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Assets> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Assets> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Assets>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Assets parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Asset
     */
    public static Assets parseFromJson(JsonParser jp)
            throws IOException {
        Assets instance = new Assets();

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

    public static boolean processSingleField(Assets instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("LOCATION".equals(fieldName)) {
            instance.location = jp.getValueAsString();
            return true;
        } else if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
            return true;
        } else if ("SITEID".equals(fieldName)) {
            instance.siteid = jp.getValueAsString();
        } else if ("ASSETNUM".equals(fieldName)) {
            instance.assetnum = jp.getValueAsString();
        }else if ("AZRQ".equals(fieldName)) {
            instance.azrq = jp.getValueAsString();
        }else if ("BXDQR".equals(fieldName)) {
            instance.bxdqr = jp.getValueAsString();
        }else if ("FIRSTCLASS".equals(fieldName)) {
            instance.firstclass = jp.getValueAsString();
        }else if ("PURCHASEPRICE".equals(fieldName)) {
            instance.purchaseprice = jp.getValueAsString();
        }else if ("SECONDCLASS".equals(fieldName)) {
            instance.secondclass = jp.getValueAsString();
        }else if ("THIRDCLASS".equals(fieldName)) {
            instance.thirdclass = jp.getValueAsString();
        }else if ("TYRQ".equals(fieldName)) {
            instance.tyrq = jp.getValueAsString();
        }else if ("UDASSETTYPE".equals(fieldName)) {
            instance.udassettype = jp.getValueAsString();
        }else if ("YSYNX".equals(fieldName)) {
            instance.ysynx = jp.getValueAsString();
        }
        return false;
    }

    /**
     * 解析Asset_List*
     */
    public static ArrayList<Assets> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Location*
     */
    public static Assets parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
