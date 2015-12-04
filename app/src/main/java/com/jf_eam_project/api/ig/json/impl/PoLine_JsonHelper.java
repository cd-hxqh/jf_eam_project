package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Po;
import com.jf_eam_project.model.PoLine;

import java.io.IOException;
import java.util.ArrayList;

/**采购单行**/
public final class PoLine_JsonHelper
        implements JsonHelper<PoLine> {
    private static final String TAG = "PoLine_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<PoLine> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<PoLine> results = null;

        // validate that we're on the right token


        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<PoLine>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                PoLine parsed = parseFromJson(jp);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析poline
     */
    public static PoLine parseFromJson(JsonParser jp)
            throws IOException {
        PoLine instance = new PoLine();

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

    public static boolean processSingleField(PoLine instance, String fieldName, JsonParser jp)
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
        } else if ("POLINENUM".equals(fieldName)) {
            instance.polinenum = jp.getValueAsString();
            return true;
        } else if ("PONUM".equals(fieldName)) {
            instance.ponum = jp.getValueAsString();
            return true;
        } else if ("TAX1".equals(fieldName)) {
            instance.tax1 = jp.getValueAsString();
            return true;
        } else if ("TOSITEID".equals(fieldName)) {
            instance.tositeid = jp.getValueAsString();
            return true;
        } else if ("ORDERUNIT".equals(fieldName)) {
            instance.orderunit = jp.getValueAsString();
            return true;
        } else if ("ORDERQTY".equals(fieldName)) {
            instance.orderqty = jp.getValueAsString();
            return true;
        } else if ("UNITCOSTTAX".equals(fieldName)) {
            instance.unitcosttax = jp.getValueAsString();
            return true;
        } else if ("UNITCOST".equals(fieldName)) {
            instance.unitcost = jp.getValueAsString();
            return true;
        } else if ("LINETYPE".equals(fieldName)) {
            instance.linetype = jp.getValueAsString();
            return true;
        } else if ("SHIPTOATTN".equals(fieldName)) {
            instance.shiptoattn = jp.getValueAsString();
            return true;
        } else if ("REQUESTEDBY".equals(fieldName)) {
            instance.requestedby = jp.getValueAsString();
            return true;
        } else if ("STORELOC".equals(fieldName)) {
            instance.storeloc = jp.getValueAsString();
            return true;
        }


        return false;
    }

    /**
     * 解析PO_List*
     */
    public static ArrayList<PoLine> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析PO*
     */
    public static PoLine parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
