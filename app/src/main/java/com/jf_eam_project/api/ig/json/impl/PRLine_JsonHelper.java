package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.PR;
import com.jf_eam_project.model.PRLine;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 解析采购计划数据*
 */
public final class PRLine_JsonHelper
        implements JsonHelper<PRLine> {
    private static final String TAG = "PRLine_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<PRLine> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<PRLine> results = null;

        // validate that we're on the right token


        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<PRLine>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                PRLine parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析prline
     */
    public static PRLine parseFromJson(JsonParser jp)
            throws IOException {
        PRLine instance = new PRLine();

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

    public static boolean processSingleField(PRLine instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("CATEGORY".equals(fieldName)) {
            instance.category = jp.getValueAsString();
            return true;
        } else if ("CONVERSION".equals(fieldName)) {
            instance.conversion = jp.getValueAsString();
            return true;
        } else if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
        } else if ("ENTERBY".equals(fieldName)) {
            instance.enterby = jp.getValueAsString();
            return true;
        } else if ("ENTERDATE".equals(fieldName)) {
            instance.enterdate = jp.getValueAsString();
            return true;
        } else if ("ITEMNUM".equals(fieldName)) {
            instance.itemnum = jp.getValueAsString();
            return true;
        } else if ("LINECOST".equals(fieldName)) {
            instance.linecost = jp.getValueAsString();
            return true;
        } else if ("PRLINENUM".equals(fieldName)) {
            instance.prlinenum = jp.getValueAsString();
            return true;
        } else if ("PRNUM".equals(fieldName)) {
            instance.prnum = jp.getValueAsString();
            return true;
        } else if ("TAX1".equals(fieldName)) {
            instance.tax1 = jp.getValueAsString();
            return true;
        } else if ("ORDERUNIT".equals(fieldName)) {
            instance.orderunit = jp.getValueAsString();
            return true;
        } else if ("ORDERQTY".equals(fieldName)) {
            instance.orderqty = jp.getValueAsString();
            return true;
        } else if ("UNITCOST".equals(fieldName)) {
            instance.unitcost = jp.getValueAsString();
            return true;
        }else if ("LINETYPE".equals(fieldName)) {
            instance.linetype = jp.getValueAsString();
            return true;
        }else if ("STORELOC".equals(fieldName)) {
            instance.storeloc = jp.getValueAsString();
            return true;
        }
        else if ("REQUESTEDBY".equals(fieldName)) {
            instance.requestedby = jp.getValueAsString();
            return true;
        }


        return false;
    }

    /**
     * 解析PR_List*
     */
    public static ArrayList<PRLine> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析PR*
     */
    public static PRLine parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
