package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Udbr;
import com.jf_eam_project.model.Uditemreq;

import java.io.IOException;
import java.util.ArrayList;

/**物资借用归还主表**/

public final class Udbr_JsonHelper
        implements JsonHelper<Uditemreq> {
    private static final String TAG = "Udbr_JsonHelper     ";

    /**
     * 解析List*
     */
    public static ArrayList<Udbr> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Udbr> results = null;

        // validate that we're on the right token


        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Udbr>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Udbr parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Udbr
     */
    public static Udbr parseFromJson(JsonParser jp)
            throws IOException {
        Udbr instance = new Udbr();

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

    public static boolean processSingleField(Udbr instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("UDBRNUM".equals(fieldName)) {
            instance.udbrnum = jp.getValueAsString();
            return true;
        } else if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
            return true;
        } else if ("BRANCH".equals(fieldName)) {
            instance.branch = jp.getValueAsString();
        } else if ("UDBELONG".equals(fieldName)) {
            instance.udbelong = jp.getValueAsString();
            return true;
        } else if ("PERSON.DISPLAYNAME".equals(fieldName)) {
            instance.person_displayname = jp.getValueAsString();
            return true;
        } else if ("CREATEDATE".equals(fieldName)) {
            instance.createdate = jp.getValueAsString();
            return true;
        } else if ("FROMBRANCH".equals(fieldName)) {
            instance.frombranch = jp.getValueAsString();
            return true;
        } else if ("FROMBELONG".equals(fieldName)) {
            instance.frombelong = jp.getValueAsString();
            return true;
        }else if ("STATUS".equals(fieldName)) {
            instance.status = jp.getValueAsString();
            return true;
        }


        return false;
    }

    /**
     * 解析Udbr_List*
     */
    public static ArrayList<Udbr> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Udbr*
     */
    public static Udbr parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
