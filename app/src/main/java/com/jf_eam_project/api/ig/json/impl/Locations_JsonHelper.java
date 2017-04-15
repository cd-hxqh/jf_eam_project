package com.jf_eam_project.api.ig.json.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Locations;

import java.io.IOException;
import java.util.ArrayList;

/**解析物资发放**/
public final class Locations_JsonHelper
        implements JsonHelper<Locations> {
    private static final String TAG = "Locations_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Locations> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Locations> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Locations>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Locations parsed = parseFromJson(jp);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Locations
     */
    public static Locations parseFromJson(JsonParser jp)
            throws IOException {
        Locations instance = new Locations();

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

    public static boolean processSingleField(Locations instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("BRANCH".equals(fieldName)) {
            instance.branch = jp.getValueAsString();
            return true;
        } else if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
            return true;
        }else if ("LOCATION".equals(fieldName)) {
            instance.location = jp.getValueAsString();
            return true;
        }else if ("SITEID".equals(fieldName)) {
            instance.siteid = jp.getValueAsString();
            return true;
        }else if ("TYPE".equals(fieldName)) {
            instance.type = jp.getValueAsString();
            return true;
        }else if ("UDBELONG".equals(fieldName)) {
            instance.udbelong = jp.getValueAsString();
            return true;
        }
        return false;
    }

    /**
     * 解析Locations*
     */
    public static ArrayList<Locations> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Locations*
     */
    public static Locations parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
