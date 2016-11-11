package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Fgsnudlview;

import java.io.IOException;
import java.util.ArrayList;
/**分公司年度上网统计**/

public final class Fgsnudlview_Jsonhelper
        implements JsonHelper<Fgsnudlview> {
    private static final String TAG = "Fgsnudlview_Jsonhelper";

    /**
     * 解析List*
     */
    public static ArrayList<Fgsnudlview> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Fgsnudlview> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Fgsnudlview>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Fgsnudlview parsed = parseFromJson(jp);
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
    public static Fgsnudlview parseFromJson(JsonParser jp)
            throws IOException {
        Fgsnudlview instance = new Fgsnudlview();

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

    public static boolean processSingleField(Fgsnudlview instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("BRANCH".equals(fieldName)) {
            instance.BRANCH = jp.getValueAsString();
            return true;
        } else if ("FGSDES".equals(fieldName)) {
            instance.FGSDES = jp.getValueAsString();
            return true;
        } else if ("SWDL".equals(fieldName)) {
            instance.SWDL = jp.getValueAsInt();
        } else if ("YEAR".equals(fieldName)) {
            instance.YEAR = jp.getValueAsString();
        }
        return false;
    }

    /**
     * 解析Fgsnudlview_List*
     */
    public static ArrayList<Fgsnudlview> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Fgsnudlview*
     */
    public static Fgsnudlview parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
