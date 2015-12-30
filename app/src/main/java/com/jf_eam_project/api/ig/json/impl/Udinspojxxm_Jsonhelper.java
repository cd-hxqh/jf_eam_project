package com.jf_eam_project.api.ig.json.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.model.Udinspojxxm;

import java.io.IOException;
import java.util.ArrayList;

/**检修项目标准**/

public final class Udinspojxxm_Jsonhelper
        implements JsonHelper<Udinspojxxm> {
    private static final String TAG = "Udinspojxxm_Jsonhelper";
    /**
     * 解析List*
     */
    public static ArrayList<Udinspojxxm> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Udinspojxxm> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Udinspojxxm>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Udinspojxxm parsed = parseFromJson(jp);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Udinspojxxm
     */
    public static Udinspojxxm parseFromJson(JsonParser jp)
            throws IOException {
        Udinspojxxm instance = new Udinspojxxm();

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

    public static boolean processSingleField(Udinspojxxm instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("UDINSPOJXXMLINENUM".equals(fieldName)) {
            instance.udinspojxxmlinenum = jp.getValueAsString();
            return true;
        } else if ("UDINSPOJXXM4".equals(fieldName)) {
            instance.udinspojxxm4 = jp.getValueAsString();
            return true;
        } else if ("UDINSPOJXXM3".equals(fieldName)) {
            instance.udinspojxxm3 = jp.getValueAsString();
        } else if ("UDINSPOJXXM2".equals(fieldName)) {
            instance.udinspojxxm2 = jp.getValueAsString();
            return true;
        } else if ("UDINSPOASSETNUM".equals(fieldName)) {
            instance.udinspoassetnum = jp.getValueAsString();
            return true;
        } else if ("FILLMETHOD".equals(fieldName)) {
            instance.fillmethod = jp.getValueAsString();
            return true;
        } else if ("EXECUTION".equals(fieldName)) {
            instance.execution = jp.getValueAsString();
            return true;
        } else if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
            return true;
        }

        return false;
    }

    /**
     * 解析Udinspojxxm_List*
     */
    public static ArrayList<Udinspojxxm> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Udinspojxxm*
     */
    public static Udinspojxxm parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
