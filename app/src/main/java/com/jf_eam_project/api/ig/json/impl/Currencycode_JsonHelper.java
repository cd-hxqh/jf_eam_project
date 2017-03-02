package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Currency;

import java.io.IOException;
import java.util.ArrayList;

/**解析货币**/
public final class Currencycode_JsonHelper
        implements JsonHelper<Currency> {
    private static final String TAG = "Currencycode_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Currency> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Currency> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Currency>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Currency parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Currency
     */
    public static Currency parseFromJson(JsonParser jp)
            throws IOException {
        Currency instance = new Currency();

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

    public static boolean processSingleField(Currency instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("CURRENCYCODE".equals(fieldName)) {
            instance.currencycode = jp.getValueAsString();
            return true;
        } else if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
            return true;
        }
        return false;
    }

    /**
     * 解析Currency*
     */
    public static ArrayList<Currency> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Currency*
     */
    public static Currency parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
