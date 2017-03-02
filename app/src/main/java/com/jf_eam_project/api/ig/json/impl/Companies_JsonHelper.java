package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Companies;

import java.io.IOException;
import java.util.ArrayList;

/**解析供应商**/
public final class Companies_JsonHelper
        implements JsonHelper<Companies> {
    private static final String TAG = "Companies_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Companies> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Companies> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Companies>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Companies parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Companies
     */
    public static Companies parseFromJson(JsonParser jp)
            throws IOException {
        Companies instance = new Companies();

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

    public static boolean processSingleField(Companies instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("BANKACCOUNT".equals(fieldName)) {
            instance.bankaccount = jp.getValueAsString();
            return true;
        } else if ("COMPANIESCODE".equals(fieldName)) {
            instance.companiescode = jp.getValueAsString();
            return true;
        }else if ("COMPANY".equals(fieldName)) {
            instance.company = jp.getValueAsString();
            return true;
        }else if ("CURRENCYCODE".equals(fieldName)) {
            instance.currencycode = jp.getValueAsString();
            return true;
        }else if ("NAME".equals(fieldName)) {
            instance.name = jp.getValueAsString();
            return true;
        }else if ("TYPE".equals(fieldName)) {
            instance.type = jp.getValueAsString();
            return true;
        }else if ("UDKHH".equals(fieldName)) {
            instance.udkhh = jp.getValueAsString();
            return true;
        }
        return false;
    }

    /**
     * 解析Companies*
     */
    public static ArrayList<Companies> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Companies*
     */
    public static Companies parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
