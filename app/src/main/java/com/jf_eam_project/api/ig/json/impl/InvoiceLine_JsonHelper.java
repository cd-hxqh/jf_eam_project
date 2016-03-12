package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Invoice;
import com.jf_eam_project.model.InvoiceLine;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 解析发票行数据*
 */
public final class InvoiceLine_JsonHelper
        implements JsonHelper<InvoiceLine> {
    private static final String TAG = "InvoiceLine_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<InvoiceLine> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<InvoiceLine> results = null;

        // validate that we're on the right token


        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<InvoiceLine>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                InvoiceLine parsed = parseFromJson(jp);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析InvoiceLine
     */
    public static InvoiceLine parseFromJson(JsonParser jp)
            throws IOException {
        InvoiceLine instance = new InvoiceLine();

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

    public static boolean processSingleField(InvoiceLine instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("INVOICELINENUM".equals(fieldName)) {
            instance.invoicelinenum = jp.getValueAsString();
            return true;
        } else if ("INVOICENUM".equals(fieldName)) {
            instance.invoicenum = jp.getValueAsString();
            return true;
        } else if ("LINETYPE".equals(fieldName)) {
            instance.linetype = jp.getValueAsString();
        } else if ("ITEMNUM".equals(fieldName)) {
            instance.itemnum = jp.getValueAsString();
            return true;
        } else if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
            return true;
        } else if ("ENTERBY".equals(fieldName)) {
            instance.enterby = jp.getValueAsString();
            return true;
        } else if ("ENTERDATE".equals(fieldName)) {
            instance.enterdate = jp.getValueAsString();
            return true;
        } else if ("CATEGORY".equals(fieldName)) {
            instance.category = jp.getValueAsString();
            return true;
        } else if ("QTYFORUI".equals(fieldName)) {
            instance.qtyforui = jp.getValueAsString();
            return true;
        } else if ("INVOICEUNIT".equals(fieldName)) {
            instance.invoiceunit = jp.getValueAsString();
            return true;
        } else if ("INVENTORY_ISSUEUNIT".equals(fieldName)) {
            instance.inventory_issueunit = jp.getValueAsString();
            return true;
        } else if ("CONVERSION".equals(fieldName)) {
            instance.conversion = jp.getValueAsString();
            return true;
        } else if ("UNITCOST".equals(fieldName)) {
            instance.unitcost = jp.getValueAsString();
            return true;
        }else if ("LINECOSTFORUI".equals(fieldName)) {
            instance.linecostforui = jp.getValueAsString();
            return true;
        }else if ("TAX1FORUI".equals(fieldName)) {
            instance.tax1forui = jp.getValueAsString();
            return true;
        }else if ("PRORATECOST".equals(fieldName)) {
            instance.proratecost = jp.getValueAsString();
            return true;
        }else if ("POLINE_STORELOC".equals(fieldName)) {
            instance.poline_storeloc = jp.getValueAsString();
            return true;
        }else if ("INVOICECOST_TOSITEIDNONPER".equals(fieldName)) {
            instance.invoicecost_tositeidnonper = jp.getValueAsString();
            return true;
        }


        return false;
    }

    /**
     * 解析InvoiceLine_List*
     */
    public static ArrayList<InvoiceLine> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析InvoiceLine*
     */
    public static InvoiceLine parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
