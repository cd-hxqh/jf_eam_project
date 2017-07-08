package com.jf_eam_project.config;

import android.content.Context;

/**
 * Created by think on 2015/10/19.
 */
public class Constants {

    /**
     * 测试版*
     */
//    public static final String HTTP_API_IP = "http://1.202.243.112:7001/";
    /**
     * 正式版*
     */
    public static final String HTTP_API_IP = "http://10.1.3.45:7003/";


    /**
     * 旧*
     */
    public static final String HTTP_API_URL = HTTP_API_IP + "maximo/mobile/";


    /**
     * 登陆URL*
     */

    public static final String SIGN_IN_URL = HTTP_API_IP + "system/login";


    /**
     * 工单URL*
     */

    public static String webserviceURL = HTTP_API_IP + "meaweb/services/MOBILESERVICE";//webservice接口地址
    /**
     * 巡检Url*
     */

    public static String webserviceUdinsPoURL = HTTP_API_IP + "meaweb/services/COSERVICE";//巡检单接口地址
    /**
     * 工作流审批地址*
     */

    public static String webserviceWfserviceURL = HTTP_API_IP + "meaweb/services/WFSERVICE";//审批工作流地址

    /**
     * 故障权限提报单*
     */
    public static String webserviceCreatereportURL = HTTP_API_IP + "meaweb/services/UDRPSERVICE";//故障缺陷提报单

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
    //工作流状态
    public static final String WFINSTANCE_NAME = "WFINSTANCE";

    /**
     * 巡检管理*
     */
    //巡检单的appid
    public static final String UDINSPO_APPID = "UDINSPOA";
    //巡检单的表名
    public static final String UDINSPO_NAME = "UDINSPO";
    //设备备件
    public static final String UDINSPOASSET_NAME = "UDINSPOASSET";
    //检修项目标准
    public static final String UDINSPOJXXM_NAME = "UDINSPOJXXM";

    /**
     * 故障,缺陷*
     */
    //故障缺陷的appid
    public static final String UDREPORT_APPID = "UDUPRAPP";

    //故障缺陷的表名
    public static final String UDREPORT_NAME = "UDREPORT";

    /**
     * 基本信息配置
     */
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
    //人员
    public static final String PERSON_APPID = "PERSON";
    public static final String PERSON_NAME = "PERSON";
    //员工
    public static final String LABOR_APPID = "LABOR";
    public static final String LABOR_NAME = "LABOR";
    //工种
    public static final String CRAFTRATE_APPID = "CRAFTRATE";
    public static final String CRAFTRATE_NAME = "CRAFTRATE";
    //项目
    public static final String ITEM_APPID = "ITEM";
    public static final String ITEM_NAME = "ITEM";
    //员工工种
    public static final String LABORCRAFTRATE_APPID = "LABORCRAFTRATE";
    public static final String LABORCRAFTRATE_NAME = "LABORCRAFTRATE";

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
    //发票的行表名
    public static final String INVOICELINE_NAME = "INVOICELINE";


    /**
     * 库存管理*
     */
    //库存查询的appid
    public static final String INVENTORY_APPID = "UDINVEN";

    //库存查询的表名
    public static final String INVENTORY_NAME = "INVENTORY";

    /**
     * 物资编码申请*
     */
    //物资编码申请appid
    public static final String UDITEMREQ_APPID = "UDITEM";
    //物资编码申请主表
    public static final String UDITEMREQ_NAME = "UDITEMREQ";
    //物资编码申请主表
    public static final String UDITEMREQLINE_NAME = "UDITEMREQLINE";

    /**
     * 物资借用归还主表*
     */
    //物资借用归还主表appid
    public static final String UDITEM_APPID = "UDITEM";
    //物资借用归还主表
    public static final String UDBR_NAME = "UDBR";
    //物资借用归还主表
    public static final String UDBRLINE_NAME = "UDBRLINE";


    /**上网电量统计**/
    //上网电量统计appid
    public static final String UDRUNLOG_APPID = "UDRUNLOG";
    //分公司年度上网电量
    public static final String FGSNUDLVIEW_NAME = "FGSNUDLVIEW";
    //分公司月度上网电量
    public static final String FGSYUDLVIEW_NAME = "FGSYUDLVIEW";
    //分公司当月单日电量
    public static final String FGSRUDLVIEW_NAME = "FGSRUDLVIEW";
    //分公司年度损失电量
    public static final String FGSNUSSDLVIEW_NAME = "FGSNUSSDLVIEW";
    //分公司年度损失电量
    public static final String FGSYUSSDLVIEW_NAME = "FGSYUSSDLVIEW";
    //故障统计20天以上
    public static final String FJDQ20VIEW_NAME = "FJDQ20VIEW";
    //故障统计10-20天以上
    public static final String FJDQ10VIEW_NAME = "FJDQ10VIEW";


    /**货币选择**/
    public static final String CURRENCY_NAME = "CURRENCY";
    /**供应商appid**/
    public static final String UDCOMPANY_APPID = "UDCOMPANY";
    /**供应商选择表名**/
    public static final String COMPANIES_NAME = "COMPANIES";
    /**联系人选择表名**/
    public static final String COMPCONTACT_NAME = "COMPCONTACT";


    /**物资发放**/
    //已预留项目表名
    public static final String INVRESERVE_NAME = "INVRESERVE";







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
    public static final int ASSETCODE = 100; //资产
    public static final int LOCATIONCODE = 110;//位置
    public static final int LOCATIONSCODE = 111;//库房
    public static final int FAILURECODE = 120;
    public static final int FAILURELIST = 130;
    public static final int JOBPLAN = 140;
    public static final int CRAFTRATE = 170;
    public static final int ITEM = 180;
    public static final int LABORCRAFTRATE = 190;
    public static final int PERSON = 160;


    public static final String WAIT_APPROVAL = "等待核准";
    public static final String APPROVALED = "已核准";
    public static final String APPDONE = "完成";

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
    /**
     * 数据库名称 *
     */
    public static final String TB_NAME = "sqlite-jf.db";

    public static String getWsUrl(Context context) {
        return webserviceURL;
    }

    public static String getWfUrl(Context context) {
        return webserviceWfserviceURL;
    }


    /**
     * 交互类型*
     */
    public static final String ADD = "add"; //新增
    public static final String UPDATE = "update"; //更新
    public static final String DELETE = "delete"; //删除


    /**
     * 刷新标识*
     */
    public static final int REFRESH = 10000;

    /**
     * 巡检单入口*
     */
    public static final int ENTRANCE_1 = 1000;
    public static final int ENTRANCE_2 = 1001;

    /**
     * 修改*
     */
    public static final int MODIFICATION_MARK = 10001;
    /**
     * 删除*
     */
    public static final int DELETE_MARK = 10002;
}
