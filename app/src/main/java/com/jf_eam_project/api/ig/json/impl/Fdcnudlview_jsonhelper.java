package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Fdcnudlview;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 分电场年度上网统计
 **/

public final class Fdcnudlview_jsonhelper
        implements JsonHelper<Fdcnudlview> {
    private static final String TAG = "Fdcnudlview_jsonhelper";

    /**
     * 解析List*
     */
    public static ArrayList<Fdcnudlview> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Fdcnudlview> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Fdcnudlview>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Fdcnudlview parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Fdcnudlview
     */
    public static Fdcnudlview parseFromJson(JsonParser jp)
            throws IOException {
        Fdcnudlview instance = new Fdcnudlview();

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

    public static boolean processSingleField(Fdcnudlview instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("BRANCH".equals(fieldName)) {
            instance.BRANCH = jp.getValueAsString();
            return true;
        } else if ("FDCDES".equals(fieldName)) {
            instance.FDCDES = jp.getValueAsString();
            return true;
        } else if ("SWDL".equals(fieldName)) {
            instance.SWDL = jp.getValueAsInt();
        } else if ("UDBELONG".equals(fieldName)) {
            instance.UDBELONG = jp.getValueAsInt();
        } else if ("XDL".equals(fieldName)) {
            instance.XDL = jp.getValueAsString();
        } else if ("YEAR".equals(fieldName)) {
            instance.YEAR = jp.getValueAsString();
        }
        return false;
    }

    /**
     * 解析Fdcnudlview_List*
     */
    public static ArrayList<Fdcnudlview> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Fdcnudlview*
     */
    public static Fdcnudlview parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
