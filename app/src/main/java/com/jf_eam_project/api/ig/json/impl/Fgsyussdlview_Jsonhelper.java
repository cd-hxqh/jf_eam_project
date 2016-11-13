package com.jf_eam_project.api.ig.json.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Fgsyussdlview;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 分公司月度损失电量
 **/

public final class Fgsyussdlview_Jsonhelper
        implements JsonHelper<Fgsyussdlview> {
    private static final String TAG = "Fgsyussdlview_Jsonhelper";

    /**
     * 解析List*
     */
    public static ArrayList<Fgsyussdlview> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Fgsyussdlview> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Fgsyussdlview>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Fgsyussdlview parsed = parseFromJson(jp);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Fgsyussdlview
     */
    public static Fgsyussdlview parseFromJson(JsonParser jp)
            throws IOException {
        Fgsyussdlview instance = new Fgsyussdlview();

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

    public static boolean processSingleField(Fgsyussdlview instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("BRANCH".equals(fieldName)) {
            instance.BRANCH = jp.getValueAsString();
            return true;
        } else if ("FGSDES".equals(fieldName)) {
            instance.FGSDES = jp.getValueAsString();
            return true;
        } else if ("DWGZ".equals(fieldName)) {
            instance.DWGZ = jp.getValueAsInt();
        } else if ("FJFJH".equals(fieldName)) {
            instance.FJFJH = jp.getValueAsInt();
        } else if ("FJJH".equals(fieldName)) {
            instance.FJJH = jp.getValueAsInt();
        } else if ("SBDFJH".equals(fieldName)) {
            instance.SBDFJH = jp.getValueAsInt();
        } else if ("SBDJH".equals(fieldName)) {
            instance.SBDJH = jp.getValueAsInt();
        } else if ("TOTAL".equals(fieldName)) {
            instance.TOTAL = jp.getValueAsInt();
        } else if ("YEAR".equals(fieldName)) {
            instance.YEAR = jp.getValueAsString();
        }else if ("MONTH".equals(fieldName)) {
            instance.MONTH = jp.getValueAsString();
        } else if ("ZRZH".equals(fieldName)) {
            instance.ZRZH = jp.getValueAsInt();
        }
        return false;
    }

    /**
     * 解析Fgsyussdlview_List*
     */
    public static ArrayList<Fgsyussdlview> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Fgsyussdlview*
     */
    public static Fgsyussdlview parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
