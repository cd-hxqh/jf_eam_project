package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Assignment;

import java.io.IOException;
import java.util.ArrayList;


public final class Assignment_JsonHelper
        implements JsonHelper<Assignment> {
    private static final String TAG = "Assignment_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Assignment> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Assignment> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Assignment>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Assignment parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Assignment
     */
    public static Assignment parseFromJson(JsonParser jp)
            throws IOException {
        Assignment instance = new Assignment();

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

    public static boolean processSingleField(Assignment instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("TASKID".equals(fieldName)) {
            instance.taskid = jp.getValueAsString();
            return true;
        } else if ("LABORCODE".equals(fieldName)) {
            instance.laborcode = jp.getValueAsString();
            return true;
        } else if ("LABORHRS".equals(fieldName)) {
            instance.laborhrs = jp.getValueAsString();
        } else if ("AMCREW".equals(fieldName)) {
            instance.amcrew = jp.getValueAsString();
            return true;
        }else if ("AMCREWTYPE".equals(fieldName)) {
            instance.amcrewtype = jp.getValueAsString();
            return true;
        }else if ("CONTRACTNUM".equals(fieldName)) {
            instance.contractnum = jp.getValueAsString();
            return true;
        }else if ("CRAFT".equals(fieldName)) {
            instance.craft = jp.getValueAsString();
            return true;
        }else if ("CREWWORKGROUP".equals(fieldName)) {
            instance.crewworkgroup = jp.getValueAsString();
            return true;
        }else if ("SCHEDULEDATE".equals(fieldName)) {
            instance.scheduledate = jp.getValueAsString();
            return true;
        }else if ("SKILLLEVEL".equals(fieldName)) {
            instance.skilllevel = jp.getValueAsString();
            return true;
        }else if ("STATUS".equals(fieldName)) {
            instance.status = jp.getValueAsString();
            return true;
        }else if ("VENDOR".equals(fieldName)) {
            instance.vendor = jp.getValueAsString();
            return true;
        }

        return false;
    }

    /**
     * 解析Assignment*
     */
    public static ArrayList<Assignment> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Assignment*
     */
    public static Assignment parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
