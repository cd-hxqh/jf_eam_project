package com.jf_eam_project.api.ig.json.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Fgsnussdlview;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 分公司年度损失电量
 **/

public final class Fgsnussdlview_Jsonhelper
        implements JsonHelper<Fgsnussdlview> {
    private static final String TAG = "Fgsnussdlview_Jsonhelper";

    /**
     * 解析List*
     */
    public static ArrayList<Fgsnussdlview> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Fgsnussdlview> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Fgsnussdlview>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Fgsnussdlview parsed = parseFromJson(jp);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Fgsnussdlview
     */
    public static Fgsnussdlview parseFromJson(JsonParser jp)
            throws IOException {
        Fgsnussdlview instance = new Fgsnussdlview();

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

    public static boolean processSingleField(Fgsnussdlview instance, String fieldName, JsonParser jp)
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
        } else if ("ZRZH".equals(fieldName)) {
            instance.ZRZH = jp.getValueAsInt();
        }
        return false;
    }

    /**
     * 解析Fgsnussdlview_List*
     */
    public static ArrayList<Fgsnussdlview> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Fgsnussdlview*
     */
    public static Fgsnussdlview parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
