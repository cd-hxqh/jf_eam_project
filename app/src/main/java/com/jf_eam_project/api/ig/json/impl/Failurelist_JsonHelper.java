package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Failurelist;

import java.io.IOException;
import java.util.ArrayList;


public final class Failurelist_JsonHelper
        implements JsonHelper<Failurelist> {
    private static final String TAG = "Failurelist_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Failurelist> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Failurelist> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Failurelist>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Failurelist parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Failurelist
     */
    public static Failurelist parseFromJson(JsonParser jp)
            throws IOException {
        Failurelist instance = new Failurelist();

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

    public static boolean processSingleField(Failurelist instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("FAILURECLASS".equals(fieldName)) {
            instance.failureclass = jp.getValueAsString();
            return true;
        }else if ("FAILURECODE".equals(fieldName)) {
            instance.failurecode = jp.getValueAsString();
            return true;
        } else if ("FLCDESCRIPTION".equals(fieldName)) {
            instance.flcdescription = jp.getValueAsString();
            return true;
        } else if ("PARENT".equals(fieldName)) {
            instance.parent = jp.getValueAsString();
        } else if ("ORGID".equals(fieldName)) {
            instance.orgid = jp.getValueAsString();
        } else if ("TYPE".equals(fieldName)) {
            instance.type = jp.getValueAsString();
        }
        return false;
    }

    /**
     * 解析Failurelist_List*
     */
    public static ArrayList<Failurelist> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Failurelist*
     */
    public static Failurelist parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
