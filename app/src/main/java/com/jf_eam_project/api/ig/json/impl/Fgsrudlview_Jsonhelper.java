package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Fgsrudlview;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 分公司当月单日电量
 **/

public final class Fgsrudlview_Jsonhelper
        implements JsonHelper<Fgsrudlview> {
    private static final String TAG = "Fgsrudlview_Jsonhelper";

    /**
     * 解析List*
     */
    public static ArrayList<Fgsrudlview> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Fgsrudlview> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Fgsrudlview>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Fgsrudlview parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Fgsrudlview
     */
    public static Fgsrudlview parseFromJson(JsonParser jp)
            throws IOException {
        Fgsrudlview instance = new Fgsrudlview();

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

    public static boolean processSingleField(Fgsrudlview instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("BRANCH".equals(fieldName)) {
            instance.BRANCH = jp.getValueAsString();
            return true;
        } else if ("FGSDES".equals(fieldName)) {
            instance.FGSDES = jp.getValueAsString();
            return true;
        } else if ("DAY".equals(fieldName)) {
            instance.DAY = jp.getValueAsString();
        }
        else if ("MONTH".equals(fieldName)) {
            instance.MONTH = jp.getValueAsString();
        } else if ("SWDL".equals(fieldName)) {
            instance.SWDL = jp.getValueAsInt();
        } else if ("XDL".equals(fieldName)) {
            instance.XDL = jp.getValueAsInt();
        }

        else if ("YEAR".equals(fieldName)) {
            instance.YEAR = jp.getValueAsString();
        }
        return false;
    }

    /**
     * 解析Fgsrudlview_List*
     */
    public static ArrayList<Fgsrudlview> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Fgsrudlview*
     */
    public static Fgsrudlview parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
