package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Po;
import com.jf_eam_project.model.Uditemreq;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 物资编码申请*
 */

public final class Uditemreq_JsonHelper
        implements JsonHelper<Uditemreq> {
    private static final String TAG = "Uditemreq_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Uditemreq> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Uditemreq> results = null;

        // validate that we're on the right token


        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Uditemreq>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Uditemreq parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Uditemreq
     */
    public static Uditemreq parseFromJson(JsonParser jp)
            throws IOException {
        Uditemreq instance = new Uditemreq();

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

    public static boolean processSingleField(Uditemreq instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("UDITEMREQNUM".equals(fieldName)) {
            instance.uditemreqnum = jp.getValueAsString();
            return true;
        } else if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
            return true;
        } else if ("REASON".equals(fieldName)) {
            instance.reason = jp.getValueAsString();
        } else if ("ALNDOMAIN_DESCRIPTION".equals(fieldName)) {
            instance.alndomain_description = jp.getValueAsString();
            return true;
        } else if ("BRANCH_DESCRIPTION".equals(fieldName)) {
            instance.branch = jp.getValueAsString();
            return true;
        } else if ("UDBELONG_DESCRIPTION".equals(fieldName)) {
            instance.udbelong = jp.getValueAsString();
            return true;
        } else if ("PERSON_DISPLAYNAME".equals(fieldName)) {
            instance.person_displayname = jp.getValueAsString();
            return true;
        } else if ("CREATEDATE".equals(fieldName)) {
            instance.createdate = jp.getValueAsString();
            return true;
        } else if ("STATUS_DESCRIPTION".equals(fieldName)) {
            instance.alndomain_description = jp.getValueAsString();
            return true;
        }

        return false;
    }

    /**
     * 解析Uditemreq_List*
     */
    public static ArrayList<Uditemreq> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Uditemreq*
     */
    public static Uditemreq parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
