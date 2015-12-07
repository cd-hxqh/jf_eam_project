package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Wplabor;

import java.io.IOException;
import java.util.ArrayList;


public final class Wplabor_JsonHelper
        implements JsonHelper<Wplabor> {
    private static final String TAG = "WorkOrder_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Wplabor> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Wplabor> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Wplabor>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Wplabor parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Wplabor
     */
    public static Wplabor parseFromJson(JsonParser jp)
            throws IOException {
        Wplabor instance = new Wplabor();

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

    public static boolean processSingleField(Wplabor instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("TASKID".equals(fieldName)) {
            instance.taskid = jp.getValueAsString();
            return true;
        } else if ("AMCREW".equals(fieldName)) {
            instance.amcrew = jp.getValueAsString();
            return true;
        } else if ("AMCREWTYPE".equals(fieldName)) {
            instance.amcrewtype = jp.getValueAsString();
        } else if ("CRAFT".equals(fieldName)) {
            instance.craft = jp.getValueAsString();
            return true;
        } else if ("CREWWORKGROUP".equals(fieldName)) {
            instance.crewworkgroup = jp.getValueAsString();
            return true;
        } else if ("LABORCODE".equals(fieldName)) {
            instance.laborcode = jp.getValueAsString();
            return true;
        } else if ("LABORHRS".equals(fieldName)) {
            instance.laborhrs = jp.getValueAsString();
            return true;
        } else if ("QUANTITY".equals(fieldName)) {
            instance.quantity = jp.getValueAsString();
            return true;
        }


        return false;
    }

    /**
     * 解析Wplabor_List*
     */
    public static ArrayList<Wplabor> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Wplabor*
     */
    public static Wplabor parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
