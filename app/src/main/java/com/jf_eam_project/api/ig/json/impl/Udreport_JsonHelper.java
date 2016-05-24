package com.jf_eam_project.api.ig.json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.jf_eam_project.api.ig.json.JsonHelper;
import com.jf_eam_project.model.Udinspo;
import com.jf_eam_project.model.Udreport;

import java.io.IOException;
import java.util.ArrayList;

/**故障，缺陷提报单**/
public final class Udreport_JsonHelper
        implements JsonHelper<Udreport> {
    private static final String TAG = "Udreport_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Udreport> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Udreport> results = null;

        // validate that we're on the right token


        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Udreport>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Udreport parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Udreport
     */
    public static Udreport parseFromJson(JsonParser jp)
            throws IOException {
        Udreport instance = new Udreport();

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

    public static boolean processSingleField(Udreport instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("REPORTNUM".equals(fieldName)) {
            instance.reportnum = jp.getValueAsString();
            return true;
        }

        else if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
            return true;
        }
        else if ("UDREPORTID".equals(fieldName)) {
            instance.udreportid = jp.getValueAsString();
            return true;
        }

        else if ("ASSETTYPE".equals(fieldName)) {
            instance.assettype = jp.getValueAsString();
        } else if ("QXTYPE".equals(fieldName)) {
            instance.qxtype = jp.getValueAsString();
            return true;
        } else if ("QXSJLY".equals(fieldName)) {
            instance.qxsjly = jp.getValueAsString();
            return true;
        }else if ("CULEVEL".equals(fieldName)) {
            instance.culevel = jp.getValueAsString();
            return true;
        }
        else if ("UDWORKTYPE".equals(fieldName)) {
            instance.udworktype = jp.getValueAsString();
            return true;
        } else if ("BRANCH_DESCRIPTION".equals(fieldName)) {
            instance.branch_description = jp.getValueAsString();
            return true;
        } else if ("UDBELONG".equals(fieldName)) {
            instance.udbelong = jp.getValueAsString();
            return true;
        } else if ("UDBELONG_DESCRIPTION".equals(fieldName)) {
            instance.udbelong_description = jp.getValueAsString();
            return true;
        } else if ("STATUSTYPE".equals(fieldName)) {
            instance.statustype = jp.getValueAsString();
            return true;
        } else if ("CREATEBY_DISPLAYNAME".equals(fieldName)) {
            instance.createby_displayname = jp.getValueAsString();
            return true;
        } else if ("CREATEDATE".equals(fieldName)) {
            instance.createdate = jp.getValueAsString();
            return true;
        }
        else if ("XCDATE".equals(fieldName)) {
            instance.xcdate = jp.getValueAsString();
            return true;
        }

        else if ("ASSETNUM".equals(fieldName)) {
            instance.assetnum = jp.getValueAsString();
            return true;
        }
        else if ("ASSETNUM_DESCRIPTION".equals(fieldName)) {
            instance.assetnum_description = jp.getValueAsString();
            return true;
        }
        else if ("LOCATION".equals(fieldName)) {
            instance.location = jp.getValueAsString();
            return true;
        }
        else if ("LOCATION_DESCRIPTION".equals(fieldName)) {
            instance.location_description = jp.getValueAsString();
            return true;
        }
        else if ("FAILURECODE".equals(fieldName)) {
            instance.failurecode = jp.getValueAsString();
            return true;
        }
        else if ("PROBLEMCODE".equals(fieldName)) {
            instance.problemcode = jp.getValueAsString();
            return true;
        }
        else if ("STOPTIME".equals(fieldName)) {
            instance.stoptime = jp.getValueAsString();
            return true;
        }
        else if ("STARTTIME".equals(fieldName)) {
            instance.starttime = jp.getValueAsString();
            return true;
        }
        else if ("BUGTIME".equals(fieldName)) {
            instance.bugtime = jp.getValueAsString();
            return true;
        }

        else if ("SBPJNUM".equals(fieldName)) {
            instance.sbpjnum = jp.getValueAsString();
            return true;
        }
        else if ("SBPJNUM_DESCRIPTION".equals(fieldName)) {
            instance.sbpjnum_description = jp.getValueAsString();
            return true;
        }
        else if ("WONUM".equals(fieldName)) {
            instance.wonum = jp.getValueAsString();
            return true;
        }
        else if ("CUDESCRIBE".equals(fieldName)) {
            instance.cudescribe = jp.getValueAsString();
            return true;
        }
        else if ("REMARK".equals(fieldName)) {
            instance.remark = jp.getValueAsString();
            return true;
        }
        else if ("APPTYPE".equals(fieldName)) {
            instance.apptype = jp.getValueAsString();
            return true;
        }

        return false;
    }

    /**
     * 解析Udreport_List*
     */
    public static ArrayList<Udreport> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Udreport*
     */
    public static Udreport parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
