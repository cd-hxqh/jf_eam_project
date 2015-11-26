package com.jf_eam_project.api;

import android.content.Context;
import android.util.Log;

import com.jf_eam_project.bean.Results;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.WorkOrder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Json数据解析类
 */
public class JsonUtils {
    private static final String TAG = "JsonUtils";


    /**
     * 解析登录信息*
     */
    public static String parsingAuthStr(final Context cxt, String data) {
        Log.i(TAG, "data=" + data);
        String isSuccess = null;
        String errmsg = null;
        try {
            JSONObject json = new JSONObject(data);
            String jsonString = json.getString("errcode");
            if (jsonString.equals(Constants.LOGINSUCCESS) || jsonString.equals(Constants.CHANGEIMEI)) {
                errmsg = json.getString("errmsg");
            }

            return errmsg;


        } catch (JSONException e) {
            e.printStackTrace();
            return isSuccess;
        }
    }

    /**
     * 分页解析返回的结果*
     */
    public static Results parsingResults(Context ctx, String data) {
        Log.i(TAG, "data=" + data);
        String result = null;
        Results results = null;
        try {
            JSONObject json = new JSONObject(data);
            String jsonString = json.getString("errcode");
            if (jsonString.equals(Constants.GETDATASUCCESS)) {
                result = json.getString("result");
                JSONObject rJson = new JSONObject(result);
                String curpage = rJson.getString("curpage");
                String totalresult = rJson.getString("totalresult");
                String resultlist = rJson.getString("resultlist");
                String totalpage = rJson.getString("totalpage");
                String showcount = rJson.getString("showcount");
                results = new Results();
                results.setCurpage(Integer.valueOf(curpage));
                results.setTotalresult(totalresult);
                results.setResultlist(resultlist);
                results.setTotalpage(totalpage);
                results.setShowcount(Integer.valueOf(showcount));
            }

            return results;


        } catch (JSONException e) {
            e.printStackTrace();
            return results;
        }

    }

//    /**
//     * 不分页解析返回的结果*
//     */
//    public static Results parsingResults1(Context ctx, String data) {
//
//        String result = null;
//        Results results = null;
//        try {
//            JSONObject json = new JSONObject(data);
//            String jsonString = json.getString("errcode");
//            if (jsonString.equals(Constants.GETDATASUCCESS)) {
//                result = json.getString("result");
//                Log.i(TAG, "result=" + result);
//                results = new Results();
//                results.setResultlist(result);
//            }
//
//            return results;
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return results;
//        }
//
//    }
//
//
//    /**
//     * 解析待办事项信息
//     */
//    public static ArrayList<Wfassignment> parsingWfassignment(Context ctx, String data) {
//        Log.i(TAG, "Wfassignment data=" + data);
//        ArrayList<Wfassignment> list = null;
//        Wfassignment wfassignment = null;
//        try {
//            JSONArray jsonArray = new JSONArray(data);
//            JSONObject jsonObject;
//            list = new ArrayList<Wfassignment>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                wfassignment = new Wfassignment();
//                jsonObject = jsonArray.getJSONObject(i);
//                wfassignment.wfassignmentid = jsonObject.getInt("WFASSIGNMENTID"); //wfassignmentid
//                wfassignment.app = jsonObject.getString("APP"); //应用程序
//                wfassignment.assigncode = jsonObject.getString("ASSIGNCODE"); //已分配任务的人员代码
//                wfassignment.assigncodedesc = jsonObject.getString("ASSIGNCODEDESC"); //描述
//                wfassignment.assignstatus = jsonObject.getString("ASSIGNSTATUS"); //任务分配状态
//                wfassignment.description = jsonObject.getString("DESCRIPTION"); //描述
//                wfassignment.ownerid = jsonObject.getString("OWNERID"); //所有者标识
//                wfassignment.ownertable = jsonObject.getString("OWNERTABLE"); //所有者表
//                wfassignment.processname = jsonObject.getString("PROCESSNAME"); //过程
//                wfassignment.startdate = jsonObject.getString("STARTDATE"); //开始日期
//
//                list.add(wfassignment);
//            }
//
//            return list;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
    /**
     * 解析工单信息
     */
    public static ArrayList<WorkOrder> parsingWorkOrder(Context ctx, String data,String type) {
        Log.i(TAG, "WorkOrder data=" + data);
        ArrayList<WorkOrder> list = null;
        WorkOrder workOrder = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<WorkOrder>();
            for (int i = 0; i < jsonArray.length(); i++) {
                workOrder = new WorkOrder();
                jsonObject = jsonArray.getJSONObject(i);
                workOrder.setWonum(jsonObject.getString("WONUM"));//工单号
                workOrder.setActfinish(jsonObject.getString("ACTFINISH"));//计划完成时间
                workOrder.setActstart(jsonObject.getString("ACTSTART"));//实际开始时间
                workOrder.setAssetdesc(jsonObject.getString("ASSETDESC"));//设备描述
                workOrder.setAssetnum(jsonObject.getString("ASSETNUM"));//设备编号
                workOrder.setDescription(jsonObject.getString("DESCRIPTION"));//描述
                workOrder.setEstdur(jsonObject.getString("ESTDUR"));//剩余时间
                workOrder.setJpnum(jsonObject.getString("JPNUM"));//作业计划
                workOrder.setJpnumdesc(jsonObject.getString("JPNUMDESC"));//作业计划描述
                workOrder.setLocation(jsonObject.getString("LOCATION"));//位置
                workOrder.setLocationdesc(jsonObject.getString("LOCATIONDESC"));//位置描述
                workOrder.setOnbehalfof(jsonObject.getString("ONBEHALFOF"));//录入人工号
                workOrder.setPmdesc(jsonObject.getString("PMDESC"));//
                workOrder.setPmnum(jsonObject.getString("PMNUM"));//
                workOrder.setReportdate(jsonObject.getString("REPORTDATE"));//汇报日期
                workOrder.setStatus(jsonObject.getString("STATUS"));//状态
                workOrder.setStatusdesc(jsonObject.getString("STATUSDESC"));//状态描述
                workOrder.setUdwotype(jsonObject.getString("UDWOTYPE"));//工单类型
                workOrder.setUdwotypedesc(jsonObject.getString("UDWOTYPEDESC"));//工单类型描述
                workOrder.setWorktype(jsonObject.getString("WORKTYPE"));//工作类型
                list.add(workOrder);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
//
//    /**
//     * 解析工单计划任务信息
//     */
//    public static ArrayList<Woactivity> parsingWoactivity(Context ctx, String data) {
//        Log.i(TAG, "Woactivity data=" + data);
//        ArrayList<Woactivity> list = null;
//        Woactivity woactivity = null;
//        try {
//            JSONArray jsonArray = new JSONArray(data);
//            JSONObject jsonObject;
//            list = new ArrayList<Woactivity>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                woactivity = new Woactivity();
//                jsonObject = jsonArray.getJSONObject(i);
//                woactivity.taskid = jsonObject.getString("TASKID"); //任务
//                woactivity.description = jsonObject.getString("DESCRIPTION");//描述
//                woactivity.wojo1 = jsonObject.getString("WOJO1");//编号
//                woactivity.wojo2 = jsonObject.getString("WOJO2");//需要安检
//                woactivity.targstartdate = jsonObject.getString("TARGSTARTDATE");//计划开始时间
//                woactivity.targcompdate = jsonObject.getString("TARGCOMPDATE");//计划完成时间
//                woactivity.actstart = jsonObject.getString("ACTSTART");//时间开始时间
//                woactivity.actfinish = jsonObject.getString("ACTFINISH");//实际完成时间
//                woactivity.estdur = jsonObject.getString("ESTDUR");//持续时间
//                list.add(woactivity);
//            }
//            return list;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//    /**
//     * 解析工单计划员工信息
//     */
//    public static ArrayList<Wplabor> parsingWplabor(Context ctx, String data) {
//        Log.i(TAG, "Wplabor data=" + data);
//        ArrayList<Wplabor> list = null;
//        Wplabor wplabor = null;
//        try {
//            JSONArray jsonArray = new JSONArray(data);
//            JSONObject jsonObject;
//            list = new ArrayList<Wplabor>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                wplabor = new Wplabor();
//                jsonObject = jsonArray.getJSONObject(i);
//                wplabor.craft = jsonObject.getString("CRAFT"); //工种
//                wplabor.skilllevel = jsonObject.getString("SKILLLEVEL");//技能级别
//                wplabor.laborcode = jsonObject.getString("LABORCODE");//员工
//                wplabor.vendor = jsonObject.getString("VENDOR");//供应商
//                wplabor.contractnum = jsonObject.getString("CONTRACTNUM");//员工合同
//                wplabor.quantity = jsonObject.getString("QUANTITY");//数量
//                wplabor.laborhrs = jsonObject.getString("LABORHRS");//常规时数
//                wplabor.orgid = jsonObject.getString("ORGID");//组织
//                wplabor.siteid = jsonObject.getString("SITEID");//地点
//                wplabor.wplaborid = jsonObject.getString("WPLABORID");//
//                list.add(wplabor);
//            }
//            return list;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//    /**
//     * 解析工单计划物料信息
//     */
//    public static ArrayList<Wpitem> parsingWpitem(Context ctx, String data) {
//        Log.i(TAG, "Wpitem data=" + data);
//        ArrayList<Wpitem> list = null;
//        Wpitem wpitem = null;
//        try {
//            JSONArray jsonArray = new JSONArray(data);
//            JSONObject jsonObject;
//            list = new ArrayList<Wpitem>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                wpitem = new Wpitem();
//                jsonObject = jsonArray.getJSONObject(i);
//                wpitem.taskid = jsonObject.getString("TASKID"); //任务
//                wpitem.itemnum = jsonObject.getString("ITEMNUM");//项目
//                wpitem.itemqty = jsonObject.getString("ITEMQTY");//项目数量
//                wpitem.orderunit = jsonObject.getString("ORDERUNIT");//订购单位
//                wpitem.unitcost = jsonObject.getString("UNITCOST");//单位成本
//                wpitem.linecost = jsonObject.getString("LINECOST");//行成本
//                wpitem.location = jsonObject.getString("LOCATION");//库房
//                wpitem.storelocsite = jsonObject.getString("STORELOCSITE");//库房地点
//                wpitem.requestnum = jsonObject.getString("REQUESTNUM");//请求
//                wpitem.requiredate = jsonObject.getString("REQUIREDATE");//要求的日期
//                wpitem.orgid = jsonObject.getString("ORGID");//组织标识
//                wpitem.siteid = jsonObject.getString("SITEID");//地点
//                list.add(wpitem);
//            }
//            return list;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//    /**
//     * 解析任务分配信息
//     */
//    public static ArrayList<Assignment> parsingAssignment(Context ctx, String data) {
//        Log.i(TAG, "Wpitem data=" + data);
//        ArrayList<Assignment> list = null;
//        Assignment assignment = null;
//        try {
//            JSONArray jsonArray = new JSONArray(data);
//            JSONObject jsonObject;
//            list = new ArrayList<Assignment>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                assignment = new Assignment();
//                jsonObject = jsonArray.getJSONObject(i);
//                assignment.taskid = jsonObject.getString("TASKID"); //任务
//                assignment.laborcode = jsonObject.getString("LABORCODE");//员工
//                assignment.craftcode = jsonObject.getString("CRAFTCODE");//工种
//                assignment.skilllevel = jsonObject.getString("SKILLLEVEL");//技能级别
//                assignment.contractnum = jsonObject.getString("CONTRACTNUM");//员工合同
//                assignment.vendor = jsonObject.getString("VENDOR");//供应商
//                assignment.scheduledate = jsonObject.getString("SCHEDULEDATE");//调度开始时间
//                assignment.laborhrs = jsonObject.getString("LABORHRS");//时数
//                assignment.status = jsonObject.getString("STATUS");//状态
//                assignment.orgid = jsonObject.getString("ORGID");//组织
//                assignment.siteid = jsonObject.getString("SITEID");//地点
//                assignment.assignmentid = jsonObject.getString("ASSIGNMENTID");//
//                list.add(assignment);
//            }
//            return list;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 解析故障汇报信息
//     */
//    public static ArrayList<Failurereport> parsingFailurereport(Context ctx, String data) {
//        Log.i(TAG, "Failurereport data=" + data);
//        ArrayList<Failurereport> list = null;
//        Failurereport failurereport = null;
//        try {
//            JSONArray jsonArray = new JSONArray(data);
//            JSONObject jsonObject;
//            list = new ArrayList<Failurereport>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                failurereport = new Failurereport();
//                jsonObject = jsonArray.getJSONObject(i);
//                failurereport.wonum = jsonObject.getString("WONUM"); //工单号
//                failurereport.assetnum = jsonObject.getString("ASSETNUM"); //资产
//                failurereport.failurecode = jsonObject.getString("FAILURECODE");//故障代码
//                failurereport.linenum = jsonObject.getString("LINENUM");//行
//                failurereport.type = jsonObject.getString("TYPE");//类型
//                failurereport.orgid = jsonObject.getString("ORGID");//组织
//                failurereport.siteid = jsonObject.getString("SITEID");//地点
//                failurereport.failurereportid = jsonObject.getString("FAILUREREPORTID");//供应商
//                list.add(failurereport);
//            }
//            return list;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 解析实际员工信息
//     */
//    public static ArrayList<Labtrans> parsingLabtrans(Context ctx, String data) {
//        Log.i(TAG, "Labtrans data=" + data);
//        ArrayList<Labtrans> list = null;
//        Labtrans labtrans = null;
//        try {
//            JSONArray jsonArray = new JSONArray(data);
//            JSONObject jsonObject;
//            list = new ArrayList<Labtrans>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                labtrans = new Labtrans();
//                jsonObject = jsonArray.getJSONObject(i);
//                labtrans.actualstaskid = jsonObject.getString("ACTUALSTASKID"); //任务
//                labtrans.craft = jsonObject.getString("CRAFT"); //任务
//                labtrans.skilllevel = jsonObject.getString("SKILLLEVEL");//技能级别
//                labtrans.laborcode = jsonObject.getString("LABORCODE");//员工
//                labtrans.startdate = jsonObject.getString("STARTDATE");//工种
//                labtrans.starttime = jsonObject.getString("STARTTIME");//工种
//                labtrans.finishdate = jsonObject.getString("FINISHDATE");//员工合同
//                labtrans.finishtime = jsonObject.getString("FINISHTIME");//供应商
//                labtrans.regularhrs = jsonObject.getString("REGULARHRS");//调度开始时间
//                labtrans.enterby = jsonObject.getString("ENTERBY");//时数
//                labtrans.enterdate = jsonObject.getString("ENTERDATE");//状态
//                labtrans.payrate = jsonObject.getString("PAYRATE");//状态
//                labtrans.linecost = jsonObject.getString("LINECOST");//状态
//                labtrans.assetnum = jsonObject.getString("ASSETNUM");//状态
//                labtrans.transdate = jsonObject.getString("TRANSDATE");//状态
//                labtrans.transtype = jsonObject.getString("TRANSTYPE");//状态
//                labtrans.orgid = jsonObject.getString("ORGID");//组织
//                labtrans.siteid = jsonObject.getString("SITEID");//地点
//                labtrans.labtransid = jsonObject.getString("LABTRANSID");//
//                list.add(labtrans);
//            }
//            return list;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 解析库存信息
//     */
//    public static ArrayList<Inventory> parsingInventory(Context ctx, String data) {
//        Log.i(TAG, "Inventory data=" + data);
//        ArrayList<Inventory> list = null;
//        Inventory inventory = null;
//        try {
//            JSONArray jsonArray = new JSONArray(data);
//            JSONObject jsonObject;
//            list = new ArrayList<Inventory>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                inventory = new Inventory();
//                jsonObject = jsonArray.getJSONObject(i);
//                inventory.avgcost = jsonObject.getString("AVGCOST"); //平均项目成本
//                inventory.curbal = jsonObject.getString("CURBAL"); //当前余量
//                inventory.issueunit = jsonObject.getString("ISSUEUNIT"); //发放单位
//                inventory.itemnum = jsonObject.getString("ITEMNUM"); //项目编号
//                inventory.lastcost = jsonObject.getString("LASTCOST"); //项目成本
//                inventory.location = jsonObject.getString("LOCATION"); //库房
//                inventory.orgid = jsonObject.getString("ORGID"); //组织标识
//                inventory.siteid = jsonObject.getString("SITEID"); //站点
//                inventory.stdcost = jsonObject.getString("STDCOST"); //项目成本
//
//                list.add(inventory);
//            }
//
//            return list;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//
//    /**
//     * 解析库存成本
//     */
//    public static ArrayList<Invcost> parsingInvcost(Context ctx, String data) {
//        Log.i(TAG, "Invcost data=" + data);
//        ArrayList<Invcost> list = null;
//        Invcost invcost = null;
//        try {
//            JSONArray jsonArray = new JSONArray(data);
//            JSONObject jsonObject;
//            list = new ArrayList<Invcost>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                invcost = new Invcost();
//                jsonObject = jsonArray.getJSONObject(i);
//                invcost.avgcost = jsonObject.getString("AVGCOST"); //平均项目成本
//                invcost.itemnum = jsonObject.getString("ITEMNUM"); //项目编号
//                invcost.lastcost = jsonObject.getString("LASTCOST"); //项目成本
//                invcost.location = jsonObject.getString("LOCATION"); //库房
//                invcost.orgid = jsonObject.getString("ORGID"); //组织标识
//                invcost.siteid = jsonObject.getString("SITEID"); //站点
//                invcost.stdcost = jsonObject.getString("STDCOST"); //项目成本
//
//                list.add(invcost);
//            }
//
//            return list;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//    /**
//     * 解析库存余量
//     */
//    public static ArrayList<Invbalances> parsingInvbalances(Context ctx, String data) {
//        Log.i(TAG, "Invbalances data=" + data);
//        ArrayList<Invbalances> list = null;
//        Invbalances invbalances = null;
//        try {
//            JSONArray jsonArray = new JSONArray(data);
//            JSONObject jsonObject;
//            list = new ArrayList<Invbalances>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                invbalances = new Invbalances();
//                jsonObject = jsonArray.getJSONObject(i);
//                invbalances.binnum = jsonObject.getString("BINNUM"); //货柜编号
//                invbalances.curbal = jsonObject.getString("CURBAL"); //当前余量
//                invbalances.itemnum = jsonObject.getString("ITEMNUM"); //项目编号
//                invbalances.location = jsonObject.getString("LOCATION"); //库房
//                invbalances.orgid = jsonObject.getString("ORGID"); //组织标识
//                invbalances.physcnt = jsonObject.getString("PHYSCNT"); //实际库存量
//                invbalances.physcntdate = jsonObject.getString("PHYSCNTDATE"); //盘点日期
//                invbalances.siteid = jsonObject.getString("SITEID"); //位置
//                invbalances.stagedcurbal = jsonObject.getString("STAGEDCURBAL"); //暂存余量
//
//                list.add(invbalances);
//            }
//
//            return list;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//    /**
//     * 解析入库
//     */
//    public static ArrayList<Matusetrans> parsingMatusetrans(Context ctx, String data) {
//        Log.i(TAG, "Matusetrans data=" + data);
//        ArrayList<Matusetrans> list = null;
//        Matusetrans matusetrans = null;
//        try {
//            JSONArray jsonArray = new JSONArray(data);
//            JSONObject jsonObject;
//            list = new ArrayList<Matusetrans>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                matusetrans = new Matusetrans();
//                jsonObject = jsonArray.getJSONObject(i);
//                matusetrans.actualcost = jsonObject.getString("ACTUALCOST"); //实际成本
//                matusetrans.actualdate = jsonObject.getString("ACTUALDATE"); //实际日期
//                matusetrans.assetnum = jsonObject.getString("ASSETNUM"); //资产编号
//                matusetrans.curbal = jsonObject.getString("CURBAL"); //当前余量
//                matusetrans.enterby = jsonObject.getString("ENTERBY"); //输入人
//                matusetrans.issuetype = jsonObject.getString("ISSUETYPE"); //交易类型
//                matusetrans.itemnum = jsonObject.getString("ITEMNUM"); //资产
//                matusetrans.linecost = jsonObject.getString("LINECOST"); //行成本
//                matusetrans.location = jsonObject.getString("LOCATION"); //位置
//                matusetrans.matusetransid = jsonObject.getString("MATUSETRANSID"); //唯一id
//                matusetrans.orgid = jsonObject.getString("ORGID"); //组织标识
//                matusetrans.physcnt = jsonObject.getString("PHYSCNT"); //实际盘点
//                matusetrans.quantity = jsonObject.getString("QUANTITY"); //数量
//                matusetrans.refwo = jsonObject.getString("REFWO"); //工单
//                matusetrans.siteid = jsonObject.getString("SITEID"); //站点
//                matusetrans.storeloc = jsonObject.getString("STORELOC"); //位置
//                matusetrans.transdate = jsonObject.getString("TRANSDATE"); //交易日期
//                matusetrans.unitcost = jsonObject.getString("UNITCOST"); //单位成本
//
//                list.add(matusetrans);
//            }
//
//            return list;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//    /**
//     * 解析出库
//     */
//    public static ArrayList<Matrectrans> parsingMatrectrans(Context ctx, String data) {
//        Log.i(TAG, "Matrectrans data=" + data);
//        ArrayList<Matrectrans> list = null;
//        Matrectrans matrectrans = null;
//        try {
//            JSONArray jsonArray = new JSONArray(data);
//            JSONObject jsonObject;
//            list = new ArrayList<Matrectrans>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                matrectrans = new Matrectrans();
//                jsonObject = jsonArray.getJSONObject(i);
//                matrectrans.actualcost = jsonObject.getString("ACTUALCOST"); //实际成本
//                matrectrans.actualdate = jsonObject.getString("ACTUALDATE"); //实际日期
//                matrectrans.fromsiteid = jsonObject.getString("FROMSITEID"); //原地点
//                matrectrans.fromstoreloc = jsonObject.getString("FROMSTORELOC"); //原位置
//                matrectrans.issuetype = jsonObject.getString("ISSUETYPE"); //交易类型
//                matrectrans.itemnum = jsonObject.getString("ITEMNUM"); //项目编号
//                matrectrans.linecost = jsonObject.getString("LINECOST"); //行成本
//                matrectrans.loadedcost = jsonObject.getString("LOADEDCOST"); //记入成本
//                matrectrans.quantity = jsonObject.getString("QUANTITY"); //数量
//                matrectrans.tostoreloc = jsonObject.getString("TOSTORELOC"); //目标位置
//                matrectrans.transdate = jsonObject.getString("TRANSDATE"); //交易日期
//                matrectrans.unitcost = jsonObject.getString("UNITCOST"); //单位成本
//
//                list.add(matrectrans);
//            }
//
//            return list;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//    /**
//     * 解析领料单信息
//     */
//    public static ArrayList<Invuse> parsingInvuse(Context ctx, String data) {
//        Log.i(TAG, "Invuse data=" + data);
//        ArrayList<Invuse> list = null;
//        Invuse invuse = null;
//        try {
//            JSONArray jsonArray = new JSONArray(data);
//            JSONObject jsonObject;
//            list = new ArrayList<Invuse>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                invuse = new Invuse();
//                jsonObject = jsonArray.getJSONObject(i);
//                invuse.description = jsonObject.getString("DESCRIPTION"); //描述
//                invuse.fromstoreloc = jsonObject.getString("FROMSTORELOC"); //库房
//                invuse.invuseid = jsonObject.getString("INVUSEID"); //唯一ID
//                invuse.invusenum = jsonObject.getString("INVUSENUM"); //领料单号
//                invuse.orgid = jsonObject.getString("ORGID"); //组织标识
//                invuse.siteid = jsonObject.getString("SITEID"); //地点
//                invuse.status = jsonObject.getString("STATUS"); //状态
//                invuse.statusdate = jsonObject.getString("STATUSDATE"); //状态的日期
//                invuse.udisjj = jsonObject.getString("UDISJJ"); //部门
//                invuse.udissueto = jsonObject.getString("UDISSUETO"); //领料人
//                invuse.wonum = jsonObject.getString("WONUM"); //工单
//
//                list.add(invuse);
//            }
//
//            return list;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//    /**
//     * 解析领料单行信息
//     */
//    public static ArrayList<Invuseline> parsingInvuseline(Context ctx, String data) {
//        Log.i(TAG, "Invuseline data=" + data);
//        ArrayList<Invuseline> list = null;
//        Invuseline invuseline = null;
//        try {
//            JSONArray jsonArray = new JSONArray(data);
//            JSONObject jsonObject;
//            list = new ArrayList<Invuseline>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                invuseline = new Invuseline();
//                jsonObject = jsonArray.getJSONObject(i);
//                invuseline.actualdate = jsonObject.getString("ACTUALDATE"); //实际日期
//                invuseline.description = jsonObject.getString("DESCRIPTION"); //描述
//                invuseline.enterby = jsonObject.getString("ENTERBY"); //输入人
//                invuseline.fromstoreloc = jsonObject.getString("FROMSTORELOC"); //库房
//                invuseline.invuselineid = jsonObject.getString("INVUSELINEID"); //唯一Id
//                invuseline.invuselinenum = jsonObject.getString("INVUSELINENUM"); //唯一Id
//                invuseline.invusenum = jsonObject.getString("INVUSENUM"); //领料单号
//                invuseline.issueunit = jsonObject.getString("ISSUEUNIT"); //发放单位
//                invuseline.itemnum = jsonObject.getString("ITEMNUM"); //备件编码
//                invuseline.linecost = jsonObject.getString("LINECOST"); //行成本
//                invuseline.orgid = jsonObject.getString("ORGID"); //组织标识
//                invuseline.quantity = jsonObject.getString("QUANTITY"); //数量
//                invuseline.refwo = jsonObject.getString("REFWO"); //工单
//                invuseline.returnedqty = jsonObject.getString("RETURNEDQTY"); //已退回数量
//                invuseline.siteid = jsonObject.getString("SITEID"); //地点
//                invuseline.tositeid = jsonObject.getString("TOSITEID"); //目标地点
//                invuseline.tostoreloc = jsonObject.getString("TOSTORELOC"); //目标位置
//                invuseline.unitcost = jsonObject.getString("UNITCOST"); //单位成本
//                invuseline.usetype = jsonObject.getString("USETYPE"); //使用情况类型
//                invuseline.wonum = jsonObject.getString("WONUM"); //工单
//
//                list.add(invuseline);
//            }
//
//            return list;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }


}