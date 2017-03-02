package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Compcontact;

import java.io.IOException;
import java.util.ArrayList;

/**解析联系人**/
public final class Compcontact_JsonHelper
        implements JsonHelper<Compcontact> {
    private static final String TAG = "Compcontact_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Compcontact> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Compcontact> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Compcontact>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Compcontact parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Compcontact
     */
    public static Compcontact parseFromJson(JsonParser jp)
            throws IOException {
        Compcontact instance = new Compcontact();

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

    public static boolean processSingleField(Compcontact instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("COMPANY".equals(fieldName)) {
            instance.company = jp.getValueAsString();
            return true;
        } else if ("CONTACT".equals(fieldName)) {
            instance.contact = jp.getValueAsString();
            return true;
        }else if ("VOICEPHONE".equals(fieldName)) {
            instance.voicephone = jp.getValueAsString();
            return true;
        }
        return false;
    }

    /**
     * 解析Compcontact*
     */
    public static ArrayList<Compcontact> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Compcontact*
     */
    public static Compcontact parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
