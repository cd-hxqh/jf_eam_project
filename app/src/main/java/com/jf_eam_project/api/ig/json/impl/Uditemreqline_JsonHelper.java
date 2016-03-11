package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Uditemreq;
import com.jf_eam_project.model.Uditemreqline;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 物资编码申请行表*
 */

public final class Uditemreqline_JsonHelper
        implements JsonHelper<Uditemreqline> {
    private static final String TAG = "Uditemreqline_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Uditemreqline> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Uditemreqline> results = null;

        // validate that we're on the right token


        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Uditemreqline>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Uditemreqline parsed = parseFromJson(jp);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Uditemreqline
     */
    public static Uditemreqline parseFromJson(JsonParser jp)
            throws IOException {
        Uditemreqline instance = new Uditemreqline();

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

    public static boolean processSingleField(Uditemreqline instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("UDLINENUM".equals(fieldName)) {
            instance.udlinenum = jp.getValueAsString();
            return true;
        } else if ("NAME".equals(fieldName)) {
            instance.name = jp.getValueAsString();
            return true;
        } else if ("UDUNIT".equals(fieldName)) {
            instance.udunit = jp.getValueAsString();
        } else if ("UDITEMREQLINE1".equals(fieldName)) {
            instance.uditemreqline1 = jp.getValueAsString();
            return true;
        } else if ("SPECIFY".equals(fieldName)) {
            instance.specify = jp.getValueAsString();
            return true;
        } else if ("UDUNITCOST".equals(fieldName)) {
            instance.udunitcost = jp.getValueAsString();
            return true;
        } else if ("CLASSSTRUCTUREID".equals(fieldName)) {
            instance.classstructureid = jp.getValueAsString();
            return true;
        } else if ("ITEMNUM".equals(fieldName)) {
            instance.itemnum = jp.getValueAsString();
            return true;
        } else if ("UDITEMREQNUM".equals(fieldName)) {
            instance.uditemreqnum = jp.getValueAsString();
            return true;
        } else if ("MEMO".equals(fieldName)) {
            instance.memo = jp.getValueAsString();
            return true;
        }


        return false;
    }

    /**
     * 解析Uditemreqline_List*
     */
    public static ArrayList<Uditemreqline> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Uditemreqline*
     */
    public static Uditemreqline parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
