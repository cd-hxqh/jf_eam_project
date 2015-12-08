package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.PR;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 解析采购计划数据*
 */
public final class PRLine_JsonHelper
        implements JsonHelper<PR> {
    private static final String TAG = "PR_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<PR> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<PR> results = null;

        // validate that we're on the right token


        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<PR>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                PR parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析po
     */
    public static PR parseFromJson(JsonParser jp)
            throws IOException {
        PR instance = new PR();

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

    public static boolean processSingleField(PR instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
            return true;
        } else if ("ISSUEDATE".equals(fieldName)) {
            instance.issuedate = jp.getValueAsString();
            return true;
        } else if ("PRNUM".equals(fieldName)) {
            instance.prnum = jp.getValueAsString();
        } else if ("REQUESTEDBY".equals(fieldName)) {
            instance.requestedby = jp.getValueAsString();
            return true;
        } else if ("REQUIREDDATE".equals(fieldName)) {
            instance.requireddate = jp.getValueAsString();
            return true;
        } else if ("SITEID".equals(fieldName)) {
            instance.siteid = jp.getValueAsString();
            return true;
        } else if ("STATUS".equals(fieldName)) {
            instance.status = jp.getValueAsString();
            return true;
        } else if ("STATUSDATE".equals(fieldName)) {
            instance.statusdate = jp.getValueAsString();
            return true;
        } else if ("UDPRTYPE".equals(fieldName)) {
            instance.udprtype = jp.getValueAsString();
            return true;
        } else if ("PRETAXTOTAL".equals(fieldName)) {
            instance.pretaxtotal = jp.getValueAsString();
            return true;
        } else if ("TOTALTAX1".equals(fieldName)) {
            instance.totaltax1 = jp.getValueAsString();
            return true;
        } else if ("TOTALCOST".equals(fieldName)) {
            instance.totalcost = jp.getValueAsString();
            return true;
        } else if ("CURRENCYCODE".equals(fieldName)) {
            instance.currencycode = jp.getValueAsString();
            return true;
        }


        return false;
    }

    /**
     * 解析PR_List*
     */
    public static ArrayList<PR> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析PR*
     */
    public static PR parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
