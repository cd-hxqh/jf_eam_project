// Copyright 2004-present Facebook. All Rights Reserved.

package com.jf_eam_project.api.ig.json;

import android.util.Log;

import com.jf_eam_project.api.ig.json.impl.Assignment_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Failurereport_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Inventory_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Invoice_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Labtrans_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Location_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.PO_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.PRLine_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.PR_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.PoLine_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Udinspo_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Wfm_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Woactivity_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.WorkOrder_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Wplabor_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Wpmaterial_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Wpservice_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Wptool_JsonHelper;
import com.jf_eam_project.model.Assignment;
import com.jf_eam_project.model.Failurereport;
import com.jf_eam_project.model.Inventory;
import com.jf_eam_project.model.Invoice;
import com.jf_eam_project.model.Labtrans;
import com.jf_eam_project.model.Location;
import com.jf_eam_project.model.PR;
import com.jf_eam_project.model.PRLine;
import com.jf_eam_project.model.Po;
import com.jf_eam_project.model.PoLine;
import com.jf_eam_project.model.Udinspo;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.model.Wfassignment;
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.model.Wplabor;
import com.jf_eam_project.model.Wpmaterial;
import com.jf_eam_project.model.Wpservice;
import com.jf_eam_project.model.Wptool;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Helper class to parse the model.
 */
public class Ig_Json_Model {

    private static final String TAG = "Ig_Json_Model";


    /**
     * 流程审批表解析*
     */
    public static ArrayList<Wfassignment> parseWfmFromString(String input) throws IOException {
        return Wfm_JsonHelper.parseFromJsonList(input);
    }
    /**
     * 巡检单解析
     */
    public static ArrayList<Udinspo> parseUdinspoString(String input) throws IOException {
        return Udinspo_JsonHelper.parseFromJsonList(input);
    }
    /**
     * 设备备件
     */
    public static ArrayList<Udinspoasset> parseUdinspoassetString(String input) throws IOException {
//        return Udinspoasset_JsonHelper.parseFromJsonList(input);
        return null;
    }

    /**
     * 采购计划单解析*
     */
    public static ArrayList<PR> parsePrFromString(String input) throws IOException {
        return PR_JsonHelper.parseFromJsonList(input);
    }

    /**
     * 采购计划行解析*
     */
    public static ArrayList<PRLine> parsePrLineFromString(String input) throws IOException {
        return PRLine_JsonHelper.parseFromJsonList(input);
    }


    /**
     * 采购单解析*
     */
    public static ArrayList<Po> parseFromString(String input) throws IOException {
        return PO_JsonHelper.parseFromJsonList(input);
    }

    /**
     * 采购单行解析*
     */
    public static ArrayList<PoLine> parseFrompolineString(String input) throws IOException {
        return PoLine_JsonHelper.parseFromJsonList(input);
    }


    /**
     * 解析发票*
     */
    public static ArrayList<Invoice> parseFromInvoiceString(String input) throws IOException {
        return Invoice_JsonHelper.parseFromJsonList(input);
    }

    /**
     * 解析库存*
     */
    public static ArrayList<Inventory> parseFromInventoryString(String input) throws IOException {
        return Inventory_JsonHelper.parseFromJsonList(input);
    }


    /**
     * 解析工单*
     */
    public static ArrayList<WorkOrder> parsingWorkOrder(String input) throws IOException {
        return WorkOrder_JsonHelper.parseFromJsonList(input);
    }

    /**
     * 解析计划任务*
     */
    public static ArrayList<Woactivity> parsingWoactivity(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return Woactivity_JsonHelper.parseFromJsonList(input);
    }

    /**
     * 解析计划员工*
     */
    public static ArrayList<Wplabor> parsingWplabor(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return Wplabor_JsonHelper.parseFromJsonList(input);
    }

    /**
     * 解析计划物料*
     */
    public static ArrayList<Wpmaterial> parsingWpmaterial(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return Wpmaterial_JsonHelper.parseFromJsonList(input);
    }

    /**
     * 解析计划服务*
     */
    public static ArrayList<Wpservice> parsingWpservice(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return Wpservice_JsonHelper.parseFromJsonList(input);
    }

    /**
     * 解析计划工具*
     */
    public static ArrayList<Wptool> parsingWptool(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return Wptool_JsonHelper.parseFromJsonList(input);
    }

    /**
     * 解析任务分配*
     */
    public static ArrayList<Assignment> parsingAssignment(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return Assignment_JsonHelper.parseFromJsonList(input);
    }

    /**
     * 解析实际员工*
     */
    public static ArrayList<Labtrans> parsingLabtrans(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return Labtrans_JsonHelper.parseFromJsonList(input);
    }

    /**
     * 解析故障汇报*
     */
    public static ArrayList<Failurereport> parsingFailurereport(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return Failurereport_JsonHelper.parseFromJsonList(input);
    }


    /**解析位置**/
    public static ArrayList<Location> parsingLocation(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return Location_JsonHelper.parseFromJsonList(input);
    }
}
