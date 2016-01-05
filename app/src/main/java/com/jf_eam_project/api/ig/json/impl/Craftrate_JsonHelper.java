package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Craftrate;

import java.io.IOException;
import java.util.ArrayList;


public final class Craftrate_JsonHelper
        implements JsonHelper<Craftrate> {
    private static final String TAG = "Craftrate_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Craftrate> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Craftrate> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Craftrate>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Craftrate parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Labor
     */
    public static Craftrate parseFromJson(JsonParser jp)
            throws IOException {
        Craftrate instance = new Craftrate();

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

    public static boolean processSingleField(Craftrate instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("CRAFT".equals(fieldName)) {
            instance.craft = jp.getValueAsString();
            return true;
        } else if ("SKILLLEVEL".equals(fieldName)) {
            instance.skilllevel = jp.getValueAsString();
            return true;
        }else if ("CONTRACTNUM".equals(fieldName)) {
            instance.contractnum = jp.getValueAsString();
            return true;
        }else if ("ORGID".equals(fieldName)) {
            instance.orgid = jp.getValueAsString();
            return true;
        }else if ("STANDARDRATE".equals(fieldName)) {
            instance.standardrate = jp.getValueAsString();
            return true;
        } else if ("VENDOR".equals(fieldName)) {
            instance.vendor = jp.getValueAsString();
            return true;
        }
        return false;
    }

    /**
     * 解析Craftrate_List*
     */
    public static ArrayList<Craftrate> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Craftrate*
     */
    public static Craftrate parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
