package com.jf_eam_project.config;

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


    /**通用接口查询**/
    public static final String BASE_URL = HTTP_API_URL + "common/api";

    /**------------------数据库表名配置－－开始**/

    /**工单管理**/
    //工单查询的appid
    public static final String UDWOCM_APPID="UDWOTRACK";
    //工单表名
    public static final String WORKORDER_NAME = "WORKORDER";
    //工单任务
    public static final String WOACTIVITY_NAME = "WOACTIVITY";
    //工单员工
    public static final String WPLABOR_NAME = "WPLABOR";
    //工单物料
    public static final String WPMATERIAL_NAME = "WPMATERIAL";
    //工单服务
    public static final String WPSERVICE_NAME = "WPSERVICE";
    //工单工具
    public static final String WPTOOL_NAME = "WPTOOL";
    //任务分配
    public static final String ASSIGNMENT_NAME = "ASSIGNMENT";



    /**采购管理**/
    //采购单的appid
    public static final String PO_APPID="UDPO";

    //采购单的表名
    public static final String PO_NAME="PO";

    //采购单行的appid
    public static final String POLINE_APPID="UDPO";
    //采购单行的表名
    public static final String POLNE_NAME="POLINE";
    /**-----------表名配置--结束**/







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
    public static final String PROJECT = "PROJECT";//项目工单
    public static final String UNPLAN = "UNPLAN";//非计划工单
}
