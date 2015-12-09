package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Invoice;
import com.jf_eam_project.model.PRLine;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 解析发票数据*
 */
public final class Invoice_JsonHelper
        implements JsonHelper<Invoice> {
    private static final String TAG = "Invoice_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Invoice> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Invoice> results = null;

        // validate that we're on the right token


        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Invoice>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Invoice parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Invoice
     */
    public static Invoice parseFromJson(JsonParser jp)
            throws IOException {
        Invoice instance = new Invoice();

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

    public static boolean processSingleField(Invoice instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("INVOICENUM".equals(fieldName)) {
            instance.invoicenum = jp.getValueAsString();
            return true;
        } else if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
            return true;
        } else if ("SITEID".equals(fieldName)) {
            instance.siteid = jp.getValueAsString();
        } else if ("DOCUMENTTYPE".equals(fieldName)) {
            instance.documenttype = jp.getValueAsString();
            return true;
        } else if ("STATUS".equals(fieldName)) {
            instance.status = jp.getValueAsString();
            return true;
        } else if ("ENTERBY".equals(fieldName)) {
            instance.enterby = jp.getValueAsString();
            return true;
        } else if ("ENTERDATE".equals(fieldName)) {
            instance.enterdate = jp.getValueAsString();
            return true;
        } else if ("INVOICEDATE".equals(fieldName)) {
            instance.invoicedate = jp.getValueAsString();
            return true;
        } else if ("GLPOSTDATE".equals(fieldName)) {
            instance.glpostdate = jp.getValueAsString();
            return true;
        } else if ("VENDOR".equals(fieldName)) {
            instance.vendor = jp.getValueAsString();
            return true;
        } else if ("PRETAXTOTALFORUI".equals(fieldName)) {
            instance.pretaxtotalforui = jp.getValueAsString();
            return true;
        } else if ("TOTALTAX1FORUI".equals(fieldName)) {
            instance.totaltax1forui = jp.getValueAsString();
            return true;
        } else if ("TOTALCOSTFORUI".equals(fieldName)) {
            instance.totalcostforui = jp.getValueAsString();
            return true;
        }else if ("CURRENCYCODE".equals(fieldName)) {
            instance.currencycode = jp.getValueAsString();
            return true;
        }


        return false;
    }

    /**
     * 解析PR_List*
     */
    public static ArrayList<Invoice> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析PR*
     */
    public static Invoice parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
