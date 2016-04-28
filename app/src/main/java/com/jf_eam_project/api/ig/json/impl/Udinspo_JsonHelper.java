package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Po;
import com.jf_eam_project.model.Udinspo;

import java.io.IOException;
import java.util.ArrayList;


public final class Udinspo_JsonHelper
        implements JsonHelper<Udinspo> {
    private static final String TAG = "Udinspo_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Udinspo> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Udinspo> results = null;

        // validate that we're on the right token


        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Udinspo>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Udinspo parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Udinspo
     */
    public static Udinspo parseFromJson(JsonParser jp)
            throws IOException {
        Udinspo instance = new Udinspo();

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

    public static boolean processSingleField(Udinspo instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("BRANCH".equals(fieldName)) {
            instance.branch = jp.getValueAsString();
            return true;
        } else if ("CREATEBY".equals(fieldName)) {
            instance.createby = jp.getValueAsString();
            return true;
        } else if ("CREATEDATE".equals(fieldName)) {
            instance.createdate = jp.getValueAsString();
        } else if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
            return true;
        } else if ("INSPOBY".equals(fieldName)) {
            instance.inspoby = jp.getValueAsString();
            return true;
        } else if ("INSPODATE".equals(fieldName)) {
            instance.inspodate = jp.getValueAsString();
            return true;
        } else if ("INSPONUM".equals(fieldName)) {
            instance.insponum = jp.getValueAsString();
            return true;
        } else if ("INSPOTYPE".equals(fieldName)) {
            instance.inspotype = jp.getValueAsString();
            return true;
        } else if ("INSPPLANNUM".equals(fieldName)) {
            instance.inspplannum = jp.getValueAsString();
            return true;
        } else if ("LASTRUNDATE".equals(fieldName)) {
            instance.lastrundate = jp.getValueAsString();
            return true;
        } else if ("NEXTRUNDATE".equals(fieldName)) {
            instance.nextrundate = jp.getValueAsString();
            return true;
        } else if ("UDINSPPLAN.DESCRIPTION".equals(fieldName)) {
            instance.udinspplandescription = jp.getValueAsString();
            return true;
        }
        else if ("ENDDATE".equals(fieldName)) {
            instance.enddate = jp.getValueAsString();
            return true;
        }

        else if ("ASSETTYPE".equals(fieldName)) {
            instance.assettype = jp.getValueAsString();
            return true;
        }
        else if ("CHECKTYPE".equals(fieldName)) {
            instance.checktype = jp.getValueAsString();
            return true;
        }
        else if ("INSPMAINPLANNUM".equals(fieldName)) {
            instance.inspmainplannum = jp.getValueAsString();
            return true;
        }
        else if ("INSPSCHEMENUM".equals(fieldName)) {
            instance.inspschemenum = jp.getValueAsString();
            return true;
        }
        else if ("STARTDATE".equals(fieldName)) {
            instance.startdate = jp.getValueAsString();
            return true;
        }
        else if ("STATUS".equals(fieldName)) {
            instance.status = jp.getValueAsString();
            return true;
        }
        else if ("TEMPERATURE".equals(fieldName)) {
            instance.temperature = jp.getValueAsString();
            return true;
        }

        else if ("UDBELONG".equals(fieldName)) {
            instance.udbelong = jp.getValueAsString();
            return true;
        }
        else if ("WEATHER".equals(fieldName)) {
            instance.weather = jp.getValueAsString();
            return true;
        }

        return false;
    }

    /**
     * 解析Udinspo_List*
     */
    public static ArrayList<Udinspo> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Udinspo*
     */
    public static Udinspo parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
