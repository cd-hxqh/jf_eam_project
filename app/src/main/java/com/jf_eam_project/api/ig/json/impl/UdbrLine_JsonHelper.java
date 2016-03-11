package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Udbr;
import com.jf_eam_project.model.Udbrline;
import com.jf_eam_project.model.Uditemreq;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 物资借用归还行表*
 */

public final class UdbrLine_JsonHelper
        implements JsonHelper<Udbrline> {
    private static final String TAG = "Udbrline_JsonHelper     ";

    /**
     * 解析List*
     */
    public static ArrayList<Udbrline> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Udbrline> results = null;

        // validate that we're on the right token


        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Udbrline>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Udbrline parsed = parseFromJson(jp);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Udbrline
     */
    public static Udbrline parseFromJson(JsonParser jp)
            throws IOException {
        Udbrline instance = new Udbrline();

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

    public static boolean processSingleField(Udbrline instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("UDBRLINENUM".equals(fieldName)) {
            instance.udbrlinenum = jp.getValueAsString();
            return true;
        } else if ("FROMSTORELOC".equals(fieldName)) {
            instance.fromstoreloc = jp.getValueAsString();
            return true;
        } else if ("ITEMNUM".equals(fieldName)) {
            instance.itemnum = jp.getValueAsString();
            return true;
        } else if ("ITEM_DESCRIPTION".equals(fieldName)) {
            instance.item_description = jp.getValueAsString();
        } else if ("ITEM_ORDERUNIT".equals(fieldName)) {
            instance.item_orderunit = jp.getValueAsString();
            return true;
        } else if ("BORROWQTY".equals(fieldName)) {
            instance.borrowqty = jp.getValueAsString();
            return true;
        } else if ("RETURNQTY".equals(fieldName)) {
            instance.returnqty = jp.getValueAsString();
            return true;
        } else if ("UDBRNUM".equals(fieldName)) {
            instance.udbrnum = jp.getValueAsString();
            return true;
        }


        return false;
    }

    /**
     * 解析Udbrline*
     */
    public static ArrayList<Udbrline> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Udbr*
     */
    public static Udbrline parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
