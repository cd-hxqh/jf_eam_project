package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Po;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public final class PO_JsonHelper
        implements JsonHelper<Po> {
    private static final String TAG = "PO_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Po> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Po> results = null;

        // validate that we're on the right token


        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Po>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Po parsed = parseFromJson(jp);
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
    public static Po parseFromJson(JsonParser jp)
            throws IOException {
        Po instance = new Po();

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

    public static boolean processSingleField(Po instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("BUYERCOMPANY".equals(fieldName)) {
            instance.buyercompany = jp.getValueAsString();
            return true;
        } else if ("CONTACT".equals(fieldName)) {
            instance.contact = jp.getValueAsString();
            return true;
        } else if ("CURRENCYCODE".equals(fieldName)) {
            instance.currencycode = jp.getValueAsString();
        } else if ("CUSTOMERNUM".equals(fieldName)) {
            instance.customernum = jp.getValueAsString();
            return true;
        } else if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
            return true;
        } else if ("FOB".equals(fieldName)) {
            instance.fob = jp.getValueAsString();
            return true;
        } else if ("FREIGHTTERMS".equals(fieldName)) {
            instance.freightterms = jp.getValueAsString();
            return true;
        } else if ("INCLUSIVE1".equals(fieldName)) {
            instance.inclusive1 = jp.getValueAsString();
            return true;
        } else if ("INSPECTIONREQUIRED".equals(fieldName)) {
            instance.inspectionrequired = jp.getValueAsString();
            return true;
        } else if ("INTERNAL".equals(fieldName)) {
            instance.internal = jp.getValueAsString();
            return true;
        } else if ("ORDERDATE".equals(fieldName)) {
            instance.orderdate = jp.getValueAsString();
            return true;
        } else if ("PAYMENTTERMS".equals(fieldName)) {
            instance.paymentterms = jp.getValueAsString();
            return true;
        } else if ("PONUM".equals(fieldName)) {
            instance.ponum = jp.getValueAsString();
            return true;
        } else if ("POTYPE".equals(fieldName)) {
            instance.potype = jp.getValueAsString();
            return true;
        } else if ("PRETAXTOTAL".equals(fieldName)) {
            instance.pretaxtotal = jp.getValueAsString();
            return true;
        } else if ("PRIORITY".equals(fieldName)) {
            instance.priority = jp.getValueAsString();
            return true;
        } else if ("PURCHASEAGENT".equals(fieldName)) {
            instance.purchaseagent = jp.getValueAsString();
            return true;
        } else if ("REQUIREDDATE".equals(fieldName)) {
            instance.requireddate = jp.getValueAsString();
            return true;
        } else if ("SHIPVIA".equals(fieldName)) {
            instance.shipvia = jp.getValueAsString();
            return true;
        } else if ("SITEDESC".equals(fieldName)) {
            instance.sitedesc = jp.getValueAsString();
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
        } else if ("STORELOC".equals(fieldName)) {
            instance.storeloc = jp.getValueAsString();
            return true;
        } else if ("STORELOCDESC".equals(fieldName)) {
            instance.storelocdesc = jp.getValueAsString();
            return true;
        } else if ("STORELOCSITEID".equals(fieldName)) {
            instance.storelocsiteid = jp.getValueAsString();
            return true;
        } else if ("TOTALBASECOST".equals(fieldName)) {
            instance.totalbasecost = jp.getValueAsString();
            return true;
        } else if ("TOTALCOST".equals(fieldName)) {
            instance.totalcost = jp.getValueAsString();
            return true;
        } else if ("TOTALTAX1".equals(fieldName)) {
            instance.totaltax1 = jp.getValueAsString();
            return true;
        } else if ("VENDELIVERYDATE".equals(fieldName)) {
            instance.vendeliverydate = jp.getValueAsString();
            return true;
        } else if ("VENDOR".equals(fieldName)) {
            instance.vendor = jp.getValueAsString();
            return true;
        } else if ("VENDORDESC".equals(fieldName)) {
            instance.vendordesc = jp.getValueAsString();
            return true;
        }


        return false;
    }

    /**
     * 解析PO_List*
     */
    public static ArrayList<Po> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析PO*
     */
    public static Po parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
