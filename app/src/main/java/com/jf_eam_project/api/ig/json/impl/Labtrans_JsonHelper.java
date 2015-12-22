package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Labtrans;

import java.io.IOException;
import java.util.ArrayList;


public final class Labtrans_JsonHelper
        implements JsonHelper<Labtrans> {
    private static final String TAG = "Labtrans_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Labtrans> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Labtrans> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Labtrans>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Labtrans parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Labtrans
     */
    public static Labtrans parseFromJson(JsonParser jp)
            throws IOException {
        Labtrans instance = new Labtrans();

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

    public static boolean processSingleField(Labtrans instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("REGULARHRS".equals(fieldName)) {
            instance.regularhrs = jp.getValueAsString();
            return true;
        } else if ("PAYRATE".equals(fieldName)) {
            instance.payrate = jp.getValueAsString();
            return true;
        } else if ("CRAFT".equals(fieldName)) {
            instance.craft = jp.getValueAsString();
        } else if ("ACTUALSTASKID".equals(fieldName)) {
            instance.actualstaskid = jp.getValueAsString();
            return true;
        } else if ("LABORCODE".equals(fieldName)) {
            instance.laborcode = jp.getValueAsString();
            return true;
        } else if ("STARTDATE".equals(fieldName)) {
            instance.startdate = jp.getValueAsString();
            return true;
        } else if ("TRANSTYPE".equals(fieldName)) {
            instance.transtype = jp.getValueAsString();
            return true;
        }
        return false;
    }

    /**
     * 解析Labtrans_List*
     */
    public static ArrayList<Labtrans> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Labtrans*
     */
    public static Labtrans parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
