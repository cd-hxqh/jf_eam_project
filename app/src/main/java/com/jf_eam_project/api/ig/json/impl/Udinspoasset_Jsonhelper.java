package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Udinspo;
import com.jf_eam_project.model.Udinspoasset;

import java.io.IOException;
import java.util.ArrayList;


/**
 * 设备备件*
 */

public final class Udinspoasset_Jsonhelper
        implements JsonHelper<Udinspoasset> {
    private static final String TAG = "Udinspoasset_Jsonhelper";

    /**
     * 解析List*
     */
    public static ArrayList<Udinspoasset> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Udinspoasset> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Udinspoasset>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Udinspoasset parsed = parseFromJson(jp);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Udinspoasset
     */
    public static Udinspoasset parseFromJson(JsonParser jp)
            throws IOException {
        Udinspoasset instance = new Udinspoasset();

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

    public static boolean processSingleField(Udinspoasset instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("ASSETDESC".equals(fieldName)) {
            instance.assetdesc = jp.getValueAsString();
            return true;
        } else if ("ASSETNUM".equals(fieldName)) {
            instance.assetnum = jp.getValueAsString();
            return true;
        } else if ("CHILDASSETNUM".equals(fieldName)) {
            instance.childassetnum = jp.getValueAsString();
        } else if ("INSPONUM".equals(fieldName)) {
            instance.insponum = jp.getValueAsString();
            return true;
        } else if ("LOCATION".equals(fieldName)) {
            instance.location = jp.getValueAsString();
            return true;
        } else if ("UDINSPOASSETLINENUM".equals(fieldName)) {
            instance.udinspoassetlinenum = jp.getValueAsString();
            return true;
        } else if ("LOCATIONSDESC".equals(fieldName)) {
            instance.locationsdesc = jp.getValueAsString();
            return true;
        } else if ("UDINSPOASSETNUM".equals(fieldName)) {
            instance.udinspoassetnum = jp.getValueAsString();
            return true;
        }

        return false;
    }

    /**
     * 解析Udinspoasset_List*
     */
    public static ArrayList<Udinspoasset> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Udinspoasset*
     */
    public static Udinspoasset parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }



}
