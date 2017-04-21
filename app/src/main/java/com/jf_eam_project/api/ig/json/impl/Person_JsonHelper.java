package com.jf_eam_project.api.ig.json.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Person;

import java.io.IOException;
import java.util.ArrayList;


public final class Person_JsonHelper
        implements JsonHelper<Person> {

    /**
     * 解析List*
     */
    public static ArrayList<Person> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Person> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Person>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Person parsed = parseFromJson(jp);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Person
     */
    public static Person parseFromJson(JsonParser jp)
            throws IOException {
        Person instance = new Person();

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

    public static boolean processSingleField(Person instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("PERSONID".equals(fieldName)) {
            instance.personid = jp.getValueAsString();
            return true;
        } else if ("DISPLAYNAME".equals(fieldName)) {
            instance.displayname = jp.getValueAsString();
            return true;
        } else if ("DEPARTMENT".equals(fieldName)) {
            instance.department = jp.getValueAsString();
        } else if ("DEPARTMENTMS".equals(fieldName)) {
            instance.departmentms = jp.getValueAsString();
        } else if ("LOCATION".equals(fieldName)) {
            instance.location = jp.getValueAsString();
        } else if ("LOCATIONORG".equals(fieldName)) {
            instance.locationorg = jp.getValueAsString();
        } else if ("LOCATIONSITE".equals(fieldName)) {
            instance.locationsite = jp.getValueAsString();
        } else if ("TITLE".equals(fieldName)) {
            instance.title = jp.getValueAsString();
        }
        return false;
    }

    /**
     * 解析Person_List*
     */
    public static ArrayList<Person> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Person*
     */
    public static Person parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
