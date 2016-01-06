package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Laborcraftrate;

import java.io.IOException;
import java.util.ArrayList;


public final class Laborcraftrate_JsonHelper
        implements JsonHelper<Laborcraftrate> {
    private static final String TAG = "Laborcraftrate_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Laborcraftrate> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Laborcraftrate> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Laborcraftrate>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Laborcraftrate parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Laborcraftrate
     */
    public static Laborcraftrate parseFromJson(JsonParser jp)
            throws IOException {
        Laborcraftrate instance = new Laborcraftrate();

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

    public static boolean processSingleField(Laborcraftrate instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("LABORCODE".equals(fieldName)) {
            instance.laborcode = jp.getValueAsString();
            return true;
        } else if ("CONTRACTNUM".equals(fieldName)) {
            instance.contractnum = jp.getValueAsString();
            return true;
        } else if ("CRAFT".equals(fieldName)) {
            instance.craft = jp.getValueAsString();
            return true;
        } else if ("SKILLLEVEL".equals(fieldName)) {
            instance.skilllevel = jp.getValueAsString();
            return true;
        } else if ("VENDOR".equals(fieldName)) {
            instance.vendor = jp.getValueAsString();
            return true;
        }
        return false;
    }

    /**
     * 解析Laborcraftrate_List*
     */
    public static ArrayList<Laborcraftrate> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Laborcraftrate*
     */
    public static Laborcraftrate parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
