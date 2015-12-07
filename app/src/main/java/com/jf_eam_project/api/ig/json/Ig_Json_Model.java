// Copyright 2004-present Facebook. All Rights Reserved.

package com.jf_eam_project.api.ig.json;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import com.jf_eam_project.api.ig.json.impl.Assignment_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.PO_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Woactivity_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.WorkOrder_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Wplabor_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Wpmaterial_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Wpservice_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Wptool_JsonHelper;
import com.jf_eam_project.model.Assignment;
import com.jf_eam_project.model.Po;
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.model.Wplabor;
import com.jf_eam_project.model.Wpmaterial;
import com.jf_eam_project.model.Wpservice;
import com.jf_eam_project.model.Wptool;

/**
 * Helper class to parse the model.
 *
 */
public class Ig_Json_Model {

    private static final String TAG = "Ig_Json_Model";

    /**�ɹ�����**/
    public static ArrayList<Po> parseFromString(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return PO_JsonHelper.parseFromJsonList(input);
    }

    /**解析工单**/
    public static ArrayList<WorkOrder> parsingWorkOrder(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return WorkOrder_JsonHelper.parseFromJsonList(input);
    }

    /**解析计划任务**/
    public static ArrayList<Woactivity> parsingWoactivity(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return Woactivity_JsonHelper.parseFromJsonList(input);
    }

    /**解析计划员工**/
    public static ArrayList<Wplabor> parsingWplabor(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return Wplabor_JsonHelper.parseFromJsonList(input);
    }

    /**解析计划物料**/
    public static ArrayList<Wpmaterial> parsingWpmaterial(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return Wpmaterial_JsonHelper.parseFromJsonList(input);
    }

    /**解析计划服务**/
    public static ArrayList<Wpservice> parsingWpservice(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return Wpservice_JsonHelper.parseFromJsonList(input);
    }

    /**解析计划工具**/
    public static ArrayList<Wptool> parsingWptool(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return Wptool_JsonHelper.parseFromJsonList(input);
    }

    /**解析任务分配**/
    public static ArrayList<Assignment> parsingAssignment(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return Assignment_JsonHelper.parseFromJsonList(input);
    }
}
