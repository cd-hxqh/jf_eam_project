package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Po;
import com.jf_eam_project.model.Wfassignment;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 流程审批表*
 */
public final class Wfm_JsonHelper
        implements JsonHelper<Wfassignment> {
    private static final String TAG = "Wfassignment_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Wfassignment> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Wfassignment> results = null;

        // validate that we're on the right token


        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Wfassignment>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Wfassignment parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Wfassignment
     */
    public static Wfassignment parseFromJson(JsonParser jp)
            throws IOException {
        Wfassignment instance = new Wfassignment();

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

    public static boolean processSingleField(Wfassignment instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("APP".equals(fieldName)) {
            instance.app = jp.getValueAsString();
            return true;
        } else if ("ASSIGNCODE".equals(fieldName)) {
            instance.assigncode = jp.getValueAsString();
            return true;
        } else if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
        } else if ("DUEDATE".equals(fieldName)) {
            instance.duedate = jp.getValueAsString();
            return true;
        } else if ("ORIGPERSON".equals(fieldName)) {
            instance.origperson = jp.getValueAsString();
            return true;
        } else if ("OWNERID".equals(fieldName)) {
            instance.ownerid = jp.getValueAsString();
            return true;
        } else if ("OWNERTABLE".equals(fieldName)) {
            instance.ownertable = jp.getValueAsString();
            return true;
        } else if ("PROCESSNAME".equals(fieldName)) {
            instance.processname = jp.getValueAsString();
            return true;
        } else if ("ROLEID".equals(fieldName)) {
            instance.roleid = jp.getValueAsString();
            return true;
        } else if ("STARTDATE".equals(fieldName)) {
            instance.startdate = jp.getValueAsString();
            return true;
        } else if ("WFASSIGNMENTID".equals(fieldName)) {
            instance.wfassignmentid = jp.getValueAsString();
            return true;
        }

        return false;
    }

    /**
     * 解析Wfassignment_List*
     */
    public static ArrayList<Wfassignment> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Wfassignment*
     */
    public static Wfassignment parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
