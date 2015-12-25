package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Location;

import java.io.IOException;
import java.util.ArrayList;


public final class Location_JsonHelper
        implements JsonHelper<Location> {
    private static final String TAG = "Location_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Location> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Location> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Location>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Location parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Location
     */
    public static Location parseFromJson(JsonParser jp)
            throws IOException {
        Location instance = new Location();

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

    public static boolean processSingleField(Location instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("LOCATION".equals(fieldName)) {
            instance.location = jp.getValueAsString();
            return true;
        } else if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
            return true;
        } else if ("SITEID".equals(fieldName)) {
            instance.siteid = jp.getValueAsString();
        } else if ("TYPE".equals(fieldName)) {
            instance.type = jp.getValueAsString();
        }
        return false;
    }

    /**
     * 解析Location_List*
     */
    public static ArrayList<Location> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Location*
     */
    public static Location parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
