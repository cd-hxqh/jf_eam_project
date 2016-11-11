package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Fgsyudlview;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 分公司月度上网电量
 **/

public final class Fgsyudlview_Jsonhelper
        implements JsonHelper<Fgsyudlview> {
    private static final String TAG = "Fgsnudlview_Jsonhelper";

    /**
     * 解析List*
     */
    public static ArrayList<Fgsyudlview> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Fgsyudlview> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Fgsyudlview>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Fgsyudlview parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Fgsnudlview
     */
    public static Fgsyudlview parseFromJson(JsonParser jp)
            throws IOException {
        Fgsyudlview instance = new Fgsyudlview();

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

    public static boolean processSingleField(Fgsyudlview instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("BRANCH".equals(fieldName)) {
            instance.BRANCH = jp.getValueAsString();
            return true;
        } else if ("FGSDES".equals(fieldName)) {
            instance.FGSDES = jp.getValueAsString();
            return true;
        } else if ("MONTH".equals(fieldName)) {
            instance.MONTH = jp.getValueAsString();
        } else if ("SWDL".equals(fieldName)) {
            instance.SWDL = jp.getValueAsInt();
        } else if ("YEAR".equals(fieldName)) {
            instance.YEAR = jp.getValueAsString();
        }
        return false;
    }

    /**
     * 解析Fgsyudlview_List*
     */
    public static ArrayList<Fgsyudlview> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Fgsyudlview*
     */
    public static Fgsyudlview parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
