// Copyright 2004-present Facebook. All Rights Reserved.

package com.jf_eam_project.api.ig.json;

import android.util.Log;

import com.jf_eam_project.api.ig.json.impl.Asset_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Assignment_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Companies_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Compcontact_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Craftrate_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Currencycode_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Failurecode_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Failurelist_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Failurereport_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Fdcnudlview_jsonhelper;
import com.jf_eam_project.api.ig.json.impl.Fgsnudlview_Jsonhelper;
import com.jf_eam_project.api.ig.json.impl.Fgsnussdlview_Jsonhelper;
import com.jf_eam_project.api.ig.json.impl.Fgsrudlview_Jsonhelper;
import com.jf_eam_project.api.ig.json.impl.Fgsyudlview_Jsonhelper;
import com.jf_eam_project.api.ig.json.impl.Fgsyussdlview_Jsonhelper;
import com.jf_eam_project.api.ig.json.impl.Fjdq20view_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Inventory_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.InvoiceLine_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Invoice_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Item_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Jobplan_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Labor_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Laborcraftrate_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Labtrans_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Location_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Locations_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.PO_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.PRLine_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.PR_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Person_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.PoLine_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.UdbrLine_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Udbr_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Udinspo_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Udinspoasset_Jsonhelper;
import com.jf_eam_project.api.ig.json.impl.Udinspojxxm_Jsonhelper;
import com.jf_eam_project.api.ig.json.impl.Uditemreq_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Uditemreqline_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Udreport_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Wfm_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Woactivity_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.WorkOrder_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Wplabor_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Wpmaterial_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Wpservice_JsonHelper;
import com.jf_eam_project.api.ig.json.impl.Wptool_JsonHelper;
import com.jf_eam_project.model.Assets;
import com.jf_eam_project.model.Assignment;
import com.jf_eam_project.model.Companies;
import com.jf_eam_project.model.Compcontact;
import com.jf_eam_project.model.Craftrate;
import com.jf_eam_project.model.Currency;
import com.jf_eam_project.model.FJDQ20VIEW;
import com.jf_eam_project.model.Failurecode;
import com.jf_eam_project.model.Failurelist;
import com.jf_eam_project.model.Failurereport;
import com.jf_eam_project.model.Fdcnudlview;
import com.jf_eam_project.model.Fgsnudlview;
import com.jf_eam_project.model.Fgsnussdlview;
import com.jf_eam_project.model.Fgsrudlview;
import com.jf_eam_project.model.Fgsyudlview;
import com.jf_eam_project.model.Fgsyussdlview;
import com.jf_eam_project.model.Inventory;
import com.jf_eam_project.model.Invoice;
import com.jf_eam_project.model.InvoiceLine;
import com.jf_eam_project.model.Item;
import com.jf_eam_project.model.Jobplan;
import com.jf_eam_project.model.Labor;
import com.jf_eam_project.model.Laborcraftrate;
import com.jf_eam_project.model.Labtrans;
import com.jf_eam_project.model.Location;
import com.jf_eam_project.model.Locations;
import com.jf_eam_project.model.PR;
import com.jf_eam_project.model.PRLine;
import com.jf_eam_project.model.Person;
import com.jf_eam_project.model.Po;
import com.jf_eam_project.model.PoLine;
import com.jf_eam_project.model.Udbr;
import com.jf_eam_project.model.Udbrline;
import com.jf_eam_project.model.Udinspo;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.model.Udinspojxxm;
import com.jf_eam_project.model.Uditemreq;
import com.jf_eam_project.model.Uditemreqline;
import com.jf_eam_project.model.Udreport;
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
        return Udinspoasset_Jsonhelper.parseFromJsonList(input);
    }
    /**
     * 检修项目标准
     */
    public static ArrayList<Udinspojxxm> parseUdinspojxxmString(String input) throws IOException {
        return Udinspojxxm_Jsonhelper.parseFromJsonList(input);
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
     * 解析发票行*
     */
    public static ArrayList<InvoiceLine> parseFromInvoiceLineString(String input) throws IOException {
        return InvoiceLine_JsonHelper.parseFromJsonList(input);
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
        return Woactivity_JsonHelper.parseFromJsonList(input);
    }

    /**
     * 解析计划员工*
     */
    public static ArrayList<Wplabor> parsingWplabor(String input) throws IOException {
        return Wplabor_JsonHelper.parseFromJsonList(input);
    }

    /**
     * 解析计划物料*
     */
    public static ArrayList<Wpmaterial> parsingWpmaterial(String input) throws IOException {
        return Wpmaterial_JsonHelper.parseFromJsonList(input);
    }

    /**
     * 解析计划服务*
     */
    public static ArrayList<Wpservice> parsingWpservice(String input) throws IOException {
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

    /**解析资产**/
    public static ArrayList<Assets> parsingAsset(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return Asset_JsonHelper.parseFromJsonList(input);
    }

    /**解析故障类**/
    public static ArrayList<Failurecode> parsingFailurecode(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return Failurecode_JsonHelper.parseFromJsonList(input);
    }

    /**解析问题代码**/
    public static ArrayList<Failurelist> parsingFailurelist(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return Failurelist_JsonHelper.parseFromJsonList(input);
    }

    /**解析作业计划**/
    public static ArrayList<Jobplan> parsingJobplan(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return Jobplan_JsonHelper.parseFromJsonList(input);
    }

    /**解析人员**/
    public static ArrayList<Person> parsingPerson(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return Person_JsonHelper.parseFromJsonList(input);
    }

    /**解析员工**/
    public static ArrayList<Labor> parsingLabor(String input) throws IOException {
        return Labor_JsonHelper.parseFromJsonList(input);
    }

    /**解析工种**/
    public static ArrayList<Craftrate> parsingCraftrate(String input) throws IOException {
        return Craftrate_JsonHelper.parseFromJsonList(input);
    }

    /**解析项目**/
    public static ArrayList<Item> parsingItem(String input) throws IOException {
        return Item_JsonHelper.parseFromJsonList(input);
    }

    /**解析员工工种**/
    public static ArrayList<Laborcraftrate> parsingLaborcraftrate(String input) throws IOException {
        return Laborcraftrate_JsonHelper.parseFromJsonList(input);
    }
    /**解析物资编码申请主表**/
    public static ArrayList<Uditemreq> parsingUditemreq(String input) throws IOException {
        return Uditemreq_JsonHelper.parseFromJsonList(input);
    }
    /**解析物资编码申请行表**/
    public static ArrayList<Uditemreqline> parsingUditemreqline(String input) throws IOException {
        return Uditemreqline_JsonHelper.parseFromJsonList(input);
    }
    /**解析物资借用归还主表**/
    public static ArrayList<Udbr> parsingUdbr(String input) throws IOException {
        return Udbr_JsonHelper.parseFromJsonList(input);
    }
    /**解析物资借用归还行表**/
    public static ArrayList<Udbrline> parsingUdbrline(String input) throws IOException {
        return UdbrLine_JsonHelper.parseFromJsonList(input);
    }


    /**解析故障，缺陷**/
    public static ArrayList<Udreport> parsingUdreport(String input) throws IOException {
        return Udreport_JsonHelper.parseFromJsonList(input);
    }

    /**解析分公司年度上网电量**/
    public static ArrayList<Fgsnudlview> parsingFgsnudlview(String input) throws IOException {
        return Fgsnudlview_Jsonhelper.parseFromJsonList(input);
    }
    /**解析分公司月度上网电量**/
    public static ArrayList<Fgsyudlview> parsingFgsyudlview(String input) throws IOException {
        return Fgsyudlview_Jsonhelper.parseFromJsonList(input);
    }
    /**解析分公司月度上网电量**/
    public static ArrayList<Fgsrudlview> parsingFgsrudlview(String input) throws IOException {
        return Fgsrudlview_Jsonhelper.parseFromJsonList(input);
    }
    /**解析分公司年度损失电量**/
    public static ArrayList<Fgsnussdlview> parsingFgsnussdlview(String input) throws IOException {
        return Fgsnussdlview_Jsonhelper.parseFromJsonList(input);
    }
    /**解析分公司月度损失电量**/
    public static ArrayList<Fgsyussdlview> parsingFgsyussdlview(String input) throws IOException {
        return Fgsyussdlview_Jsonhelper.parseFromJsonList(input);
    }

    /**解析风电场年度上网电量**/
    public static ArrayList<Fdcnudlview> parsingFdcnudlview(String input) throws IOException {
        return Fdcnudlview_jsonhelper.parseFromJsonList(input);
    }
    /**解析故障统计20天以上**/
    public static ArrayList<FJDQ20VIEW> parsingFJDQ20VIEW(String input) throws IOException {
        return Fjdq20view_JsonHelper.parseFromJsonList(input);
    }
    /**解析货币**/
    public static ArrayList<Currency> parsingCurrency(String input) throws IOException {
        return Currencycode_JsonHelper.parseFromJsonList(input);
    }
    /**解析供应商**/
    public static ArrayList<Companies> parsingCompanies(String input) throws IOException {
        return Companies_JsonHelper.parseFromJsonList(input);
    }
    /**解析联系人**/
    public static ArrayList<Compcontact> parsingCompcontact(String input) throws IOException {
        return Compcontact_JsonHelper.parseFromJsonList(input);
    }

    /**解析物资发放**/
    public static ArrayList<Locations> parsingLocations(String input) throws IOException {
        return Locations_JsonHelper.parseFromJsonList(input);
    }


}
