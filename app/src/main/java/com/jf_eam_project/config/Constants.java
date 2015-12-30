package com.jf_eam_project.config;

import android.content.Context;

/**
 * Created by think on 2015/10/19.
 */
public class Constants {

    /**
     * 基础接口*
     */
    public static final String HTTP_API_URL = "http://61.49.28.246:7001/maximo/mobile/";

    /**
     * 登陆URL*
     */

    public static final String SIGN_IN_URL = HTTP_API_URL + "system/login";

    /**
     * webserviceURL*
     */
    public static String webserviceURL = "http://61.49.28.246:7001/meaweb/services/MOBILESERVICE";//webservice接口地址


    /**
     * 通用接口查询*
     */
    public static final String BASE_URL = HTTP_API_URL + "common/api";

    /**------------------数据库表名配置－－开始**/
    /**
     * 流程审批*
     */
    //流程审批的appid
    public static final String WFM_APPID = "WFDESIGN";
    //流程审批表名
    public static final String WFM_NAME = "WFASSIGNMENT";
    /**
     * 工单管理*
     */
    //工单查询的appid
    public static final String UDWOCM_APPID = "UDWOTRACK";
    //工单表名
    public static final String WORKORDER_NAME = "WORKORDER";
    //工单任务
    public static final String WOACTIVITY_NAME = "WOACTIVITY";
    //工单计划员工
    public static final String WPLABOR_NAME = "WPLABOR";
    //工单计划物料
    public static final String WPMATERIAL_NAME = "WPMATERIAL";
    //工单计划服务
    public static final String WPSERVICE_NAME = "WPSERVICE";
    //工单计划工具
    public static final String WPTOOL_NAME = "WPTOOL";
    //任务分配
    public static final String ASSIGNMENT_NAME = "ASSIGNMENT";
    //工单实际员工
    public static final String LABTRANS_NAME = "LABTRANS";
    //工单实际物料
    public static final String MATUSETRANS_NAME = "MATUSETRANS";
    //工单实际服务
    public static final String SERVRECTRANS_NAME = "SERVRECTRANS";
    //工单实际工具
    public static final String TOOLTRANS_NAME = "TOOLTRANS";
    //故障报告
    public static final String FAILUREREPORT_NAME = "FAILUREREPORT";

    /**巡检管理**/
    //巡检单的appid
    public static final String UDINSPO_APPID = "UDINSPOA";
    //巡检单的表名
    public static final String UDINSPO_NAME = "UDINSPO";
    //设备备件
    public static final String UDINSPOASSET_NAME = "UDINSPOASSET";
    /**
     * 基本信息配置
     */
    //位置
    public static final String LOCATION_APPID = "LOCATION";
    public static final String LOCATION_NAME = "LOCATIONS";
    //资产
    public static final String ASSET_APPID = "ASSET";
    public static final String ASSET_NAME = "ASSET";
    //故障类
    public static final String FAILURECODE_NAME = "FAILURECODE";
    //问题代码
    public static final String FAILURELIST_NAME = "FAILURELIST";
    //作业计划
    public static final String JOBPLAN_NAME = "JOBPLAN";

    /**
     * 采购管理*
     */
    //采购申请单的appid
    public static final String PR_APPID = "UDPR";
    //采购申请单的表名
    public static final String PR_NAME = "PR";
    //采购计划行的appid
    public static final String PRLINE_APPID = "UDPR";
    //采购计划行的表名
    public static final String PRLINE_NAME = "PRLINE";


    //采购单的appid
    public static final String PO_APPID = "UDPO";

    //采购单的表名
    public static final String PO_NAME = "PO";

    //采购单行的appid
    public static final String POLINE_APPID = "UDPO";
    //采购单行的表名
    public static final String POLNE_NAME = "POLINE";


    //发票的appid
    public static final String INVOICE_APPID = "UDINVOICE";

    //发票的表名
    public static final String INVOICE_NAME = "INVOICE";


    /**
     * 库存管理*
     */
    //库存查询的appid
    public static final String INVENTORY_APPID = "UDINVEN";

    //库存查询的表名
    public static final String INVENTORY_NAME = "INVENTORY";
    /**
     * -----------表名配置--结束*
     */


    public static final String USER_INFO = "userinfo";
    public static final String NAME_KEY = "name_key";
    public static final String PASS_KEY = "pass_key";
    public static final String ISREMENBER = "isRemenber";


    /**
     * 用户登录表识--开始*
     */
    public static final String LOGINSUCCESS = "USER-S-101"; //登录成功

    public static final String CHANGEIMEI = "USER-S-104"; //登录成功,检测到用户更换手机登录

    public static final String USERNAMEERROR = "USER-E-100";//用户名密码错误

    public static final String GETDATASUCCESS = "GLOBAL-S-0";//获取数据成功


    /**
     * 工单跳转类型标识
     */
    public static final String PLAN = "PLAN";//计划工单
//    public static final String PROJECT = "PROJECT";//项目工单
    public static final String UNPLAN = "UNPLAN";//非计划工单

    /**
     * 选项跳转请求值
     */
    public static final int ASSETCODE = 100;
    public static final int LOCATIONCODE = 110;
    public static final int FAILURECODE = 120;
    public static final int FAILURELIST = 130;
    public static final int JOBPLAN = 140;
    public static final int WOACTIVITY = 150;


    public static final String WAIT_APPROVAL = "等待核准";
    public static final String APPROVALED = "已核准";

    /**设置数据库参数-开始**/
    /**
     * 数据库路径
     */
    public static final String PATH_DB = android.os.Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + "/Android/data/";
    /**
     * 无SD卡的数据库路径
     */
    public static final String NOT_SDCARD_PATH_DB = "/data/data/";
    /** 数据库名称 **/
    public static final String TB_NAME = "sqlite-bowei.db";

    public static String getWsUrl(Context context) {
        return webserviceURL;
    }
}
