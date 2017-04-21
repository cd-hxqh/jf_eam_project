package com.jf_eam_project.api;


import android.content.Context;
import android.util.Log;

import com.jf_eam_project.R;
import com.jf_eam_project.application.BaseApplication;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.utils.AccountUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;


/**
 * Created by apple on 15/5/27.
 */
public class HttpManager {

    private static BaseApplication mApp = BaseApplication.getInstance();
    private static AsyncHttpClient sClient = null;
    private static final String TAG = "HttpManager";


    /**
     * 使用用户名密码登录
     *
     * @param cxt
     * @param username 用户名
     * @param password 密码
     * @param imei     密码
     * @param handler  返回结果处理
     */
    public static void loginWithUsername(final Context cxt, final String username, final String password, String imei,
                                         final HttpRequestHandler<String> handler) {


        String loginIp = AccountUtils.getIpAddress(cxt) + "mobile/system/login";

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("loginid", username);
        params.put("password", password);
        params.put("imei", imei);
        client.post(loginIp, params, new TextHttpResponseHandler() {


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, IMErrorType.errorMessage(cxt, IMErrorType.ErrorLoginFailure));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                if (statusCode == 200) {
                    String errmsg = JsonUtils.parsingAuthStr(cxt, responseString);
                    SafeHandler.onSuccess(handler, errmsg);
                }
            }
        });
    }


    /**
     * 设置流程审批*
     */
    public static String getWfmUrl(String persionid, String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.WFM_APPID + "','objectname':'" + Constants.WFM_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'WFASSIGNMENTID DESC','condition':{'ASSIGNCODE':'" + persionid + "','PROCESSNAME':'=UDNPWO,=INSPODJDQ,=INSPODJFJ,=INSPOE,=INSPOB,=INSPOA,=INSPOC,=INSPOD,=INSPOF,=UDGZTB,=UDQXTB" + "','ASSIGNSTATUS':'=活动'}}";
        } else {
            return "{'appid':'" + Constants.WFM_APPID + "','objectname':'" + Constants.WFM_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'WFASSIGNMENTID DESC','condition':{'DESCRIPTION':'=" + vlaue + "','ASSIGNCODE':'=" + persionid + "','PROCESSNAME':'=UDNPWO,=INSPODJDQ,=INSPODJFJ,=INSPOE,=INSPOB,=INSPOA,=INSPOC,=INSPOD,=INSPOF,=UDGZTB,=UDQXTB" + "','ASSIGNSTATUS':'＝活动'}" + ",'sinorsearch':{'WFASSIGNMENTID':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
        }
    }

    /**
     * 设置巡检单
     */
    public static String getUdinspourl(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.UDINSPO_APPID + "','objectname':'" + Constants.UDINSPO_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.UDINSPO_APPID + "','objectname':'" + Constants.UDINSPO_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'INSPONUM':'" + vlaue + "'}}";
        }
    }

    /**
     * 设置巡检单(下载已下载的内容)
     */
    public static String getUdinspourl1(String inspotype, String assettype, String checktype, String udbelong, String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            if (inspotype.equals("05")) {
                return "{'appid':'" + Constants.UDINSPO_APPID + "','objectname':'" + Constants.UDINSPO_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'to_number(INSPONUM,999999999) desc','condition':{'ASSETTYPE':'=" + assettype + "','CHECKTYPE':'=" + checktype + "','STATUS':'=DRAFT','UDBELONG':'=" + udbelong + "'}}";
            } else {
                return "{'appid':'" + Constants.UDINSPO_APPID + "','objectname':'" + Constants.UDINSPO_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'to_number(INSPONUM,999999999) desc','condition':{'INSPOTYPE':'=" + inspotype + "','STATUS':'=DRAFT','UDBELONG':'=" + udbelong + "'}}";
            }
        } else {
            if (inspotype.equals("05")) {
                return "{'appid':'" + Constants.UDINSPO_APPID + "','objectname':'" + Constants.UDINSPO_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'to_number(INSPONUM,999999999) desc','condition':{'ASSETTYPE':'=" + assettype + "','CHECKTYPE':'=" + checktype + "','STATUS':'=DRAFT','UDBELONG':'=" + udbelong + "'},'sinorsearch':{'INSPONUM':'" + vlaue + "','ASSETTYPE':'" + vlaue + "','CHECKTYPE':'" + vlaue + "'}}";
            } else {
                return "{'appid':'" + Constants.UDINSPO_APPID + "','objectname':'" + Constants.UDINSPO_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'to_number(INSPONUM,999999999) desc','condition':{'INSPONUM':'=" + vlaue + "','INSPOTYPE':'=" + inspotype + "','STATUS':'=DRAFT','UDBELONG':'=" + udbelong + "'},'sinorsearch':{'INSPONUM':'" + vlaue + "','INSPOTYPE':'" + vlaue + "'}}";
            }
        }
    }

    /**
     * 设置根据编号精确查询巡检单
     */
    public static String getUdinspourl1(String vlaue, int curpage, int showcount) {
        return "{'appid':'" + Constants.UDINSPO_APPID + "','objectname':'" + Constants.UDINSPO_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'to_number(INSPONUM,999999999) desc','condition':{'INSPONUM':'=" + vlaue + "'}}";
    }


    /**
     * 根据巡检单编号下载数据*
     */

    public static String getUdinspo(String inspotype, String assettype, String checktype, String vlaue, int curpage, int showcount) {

        if (inspotype.equals("05")) {
            return "{'appid':'" + Constants.UDINSPO_APPID + "','objectname':'" + Constants.UDINSPO_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'INSPONUM':'=" + vlaue + "','ASSETTYPE':'=" + assettype + "','CHECKTYPE':'=" + checktype + "'}}";
        } else {
            return "{'appid':'" + Constants.UDINSPO_APPID + "','objectname':'" + Constants.UDINSPO_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'INSPONUM':'=" + vlaue + "','INSPOTYPE':'" + inspotype + "'}}";
        }
    }


    /**
     * 设置设备备件*
     */
    public static String getUdinspoasseturl(String insponum, String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.UDINSPO_APPID + "','objectname':'" + Constants.UDINSPOASSET_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'UDINSPOASSETLINENUM ASC','condition':{'INSPONUM':'=" + insponum + "'}}";
        } else {
            return "{'appid':'" + Constants.UDINSPO_APPID + "','objectname':'" + Constants.UDINSPOASSET_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'UDINSPOASSETLINENUM ASC','condition':{'INSPONUM':'=" + insponum + "'},'sinorsearch':{'INSPONUM':'" + vlaue + "','UDINSPOASSETLINENUM':'" + vlaue + "'}}";
        }
    }


    /**
     * 设置设备备件*
     */
    public static String getUdinspoasseturl1(String insponum) {
        return "{'appid':'" + Constants.UDINSPO_APPID + "','objectname':'" + Constants.UDINSPOASSET_NAME + "','option':'read','orderby':'UDINSPOASSETLINENUM ASC','condition':{'INSPONUM':'=" + insponum + "'}}";

    }


    /**
     * 项目检修标准*
     */
    public static String getUdinspojxxmUrl(String insponum, String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.UDINSPO_APPID + "','objectname':'" + Constants.UDINSPOJXXM_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'UDINSPOJXXMLINENUM ASC','condition':{'UDINSPOASSETNUM':'=" + insponum + "'}}";
        } else {
            return "{'appid':'" + Constants.UDINSPO_APPID + "','objectname':'" + Constants.UDINSPOJXXM_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'UDINSPOJXXMLINENUM ASC','condition':{'UDINSPOASSETNUM':'=" + insponum + "'},'sinorsearch':{'UDINSPOASSETNUM':'" + vlaue + "','UDINSPOJXXMLINENUM':'" + vlaue + "'}}";
        }
    }

    /**
     * 项目检修标准*
     */
    public static String getUdinspojxxmUrl1(String udinspoassetnum) {
        return "{'appid':'" + Constants.UDINSPO_APPID + "','objectname':'" + Constants.UDINSPOJXXM_NAME + "','option':'read','orderby':'UDINSPOJXXMLINENUM ASC','condition':{'UDINSPOASSETNUM':'" + udinspoassetnum + "'}}";
    }


    /**
     * 设置故障，巡检提报单
     */
    public static String getUdreport(String apptype, String udbelong, String statustype, String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {

            return "{'appid':'" + Constants.UDREPORT_APPID + "','objectname':'" + Constants.UDREPORT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'to_number(REPORTNUM,999999999) DESC','condition':{'APPTYPE':'=" + apptype + "','UDBELONG':'=" + udbelong + "','STATUSTYPE':'" + statustype + "'}}";

        } else {

            return "{'appid':'" + Constants.UDREPORT_APPID + "','objectname':'" + Constants.UDREPORT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'to_number(REPORTNUM,999999999) DESC','condition':{'APPTYPE':'=" + apptype + "','UDBELONG':'=" + udbelong + "','STATUSTYPE':''},'sinorsearch':{'REPORTNUM':'" + vlaue + ",'DESCRIPTION':'" + vlaue + "'}}";
        }
    }

    /**
     * 根据编号获取提报单信息
     */
    public static String getUdreport(String reportnum) {

        return "{'appid':'" + Constants.UDREPORT_APPID + "','objectname':'" + Constants.UDREPORT_NAME + "','curpage':" + 1 + ",'showcount':" + 10 + ",'option':'read','condition':{'REPORTNUM':'=" + reportnum + "'}}";


    }


    /**
     * 根据udreportid获取提报单信息
     */
    public static String getUdreportId(String udreportid) {

        return "{'appid':'" + Constants.UDREPORT_APPID + "','objectname':'" + Constants.UDREPORT_NAME + "','curpage':" + 1 + ",'showcount':" + 10 + ",'option':'read','condition':{'UDREPORTID':'=" + udreportid + "'}}";


    }


    /**
     * 设置工单接口*
     */
    public static String getworkorderUrl(String type, String udbelong, String search, String assetNum, int curpage, int showcount) {
        if (search.equals("") && assetNum == null) {
            return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'to_number(WONUM,999999999) DESC','condition':{'UDWOTYPE':'=" + type + "','STATUS':'=等待核准,=已核准,=完成','UDBELONG':'=" + udbelong + "'}}";
        } else if (search.equals("") && assetNum != null) {
            return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'to_number(WONUM,999999999) DESC','condition':{'ASSETNUM':'=" + assetNum + "','STATUS':'=等待核准,=已核准,=完成','UDBELONG':'=" + udbelong + "'}}";
        } else if (!search.equals("")) {
            if (assetNum == null) {
                return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                        "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'to_number(WONUM,999999999) DESC','condition':{'STATUS':'=等待核准,=已核准,=完成','UDBELONG':'=" + udbelong + "'},'sinorsearch':{'WONUM':'" + search + "','DESCRIPTION':'" + search + "'}}";
            } else {
                return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                        "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'to_number(WONUM,999999999) DESC','condition':{'ASSETNUM':'=" + assetNum + "','STATUS':'=等待核准,=已核准,=完成','UDBELONG':'=" + udbelong + "'},'sinorsearch':{'WONUM':'" + search + "','DESCRIPTION':'" + search + "'}}";
            }
        } else {
            return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'to_number(WONUM,999999999) DESC','condition':{'UDWOTYPE':'=" + type + "','STATUS':'=等待核准,=已核准,=完成','UDBELONG':'=" + udbelong + "'},'sinorsearch':{'WONUM':'" + search + "','DESCRIPTION':'" + search + "'}}";
        }

    }


    /**
     * 根据工单编号接口*
     */
    public static String getWorkorderByNumUrl(String wonum, int curpage, int showcount) {
        return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'to_number(WONUM,999999999) DESC','condition':{'WONUM':'=" + wonum + "','STATUS':'=等待核准,=已核准,=完成'}}";


    }


    /**
     * 设置工单历史接口*
     */
    public static String getWORKORDERHisUrl(String search, String udwotype, String udbelong, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'to_number(WONUM,999999999) DESC','condition':{'UDWOTYPE':'=" + udwotype + "','STATUS':'=关闭','UDBELONG':'=" + udbelong + "'}}";
        } else {
            return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'to_number(WONUM,999999999) DESC','condition':{'UDWOTYPE':'=" + udwotype + "','STATUS':'=关闭','UDBELONG':'=" + udbelong + "'},'sinorsearch':{'WONUM':'" + search + "'DESCRIPTION':'" + search + "'}}";
        }

    }


    /**
     * 设置计划任务接口*
     */
    public static String getwoactivityUrl(String parent, int curpage, int showcount) {
        return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" +
                Constants.WOACTIVITY_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'PARENT':'=" + parent + "'}}";
    }

    /**
     * 设置计划员工接口*
     */
    public static String getWplaborUrl(int curpage, int showcount, String wonum) {
        return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" +
                Constants.WPLABOR_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'," +
                "'condition':{'WONUM':'=" + wonum + "'}}";
    }

    /**
     * 设置计划物料接口*
     */
    public static String getWpmaterialUrl(String search, int curpage, int showcount, String wonum) {
        if (search.equals("")) {
            return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" +
                    Constants.WPMATERIAL_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'WONUM':'=" + wonum + "'}}";
        } else {
            return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" +
                    Constants.WPMATERIAL_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'WONUM':'=" + wonum + "'},'sinorsearch':{'ITEMNUM':'" + search + "'}}";
        }
    }

    /**
     * 设置计划服务接口*
     */
    public static String getWpserviceUrl(int curpage, int showcount) {
        return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" +
                Constants.WPSERVICE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置计划工具接口*
     */
    public static String getWptoolUrl(int curpage, int showcount) {
        return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" +
                Constants.WPTOOL_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置任务分配接口*
     */
    public static String getAssignmentUrl(int curpage, int showcount, String wonum) {
        return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" +
                Constants.ASSIGNMENT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'WONUM':'=" + wonum + "'}}";
    }

    /**
     * 设置实际员工接口*
     */
    public static String getLabtransUrl(int curpage, int showcount, String refwo) {
        return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" +
                Constants.LABTRANS_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'refwo':'=" + refwo + "'}}";
    }

    /**
     * 设置获取工作流状态接口*
     */
    public static String getWfStatusUrl(int curpage, int showcount, String ownerid, String processname, String ownertable) {
        return "{'appid':'" + Constants.WFINSTANCE_NAME + "','objectname':'" +
                Constants.WFINSTANCE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'PROCESSNAME':'=" + processname + "','OWNERTABLE':'=" + ownertable + "','OWNERID':'=" + ownerid + "'}}";
    }

    /**
     * 设置故障报告接口*
     */
    public static String getFailurereportUrl(String wonum, int curpage, int showcount) {
        return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" +
                Constants.FAILUREREPORT_NAME + "','curpage':" + curpage + ",'showcount':" +
                showcount + ",'option':'read','condition':{'WONUM':'=" + wonum + "'}}";
    }

    /**
     * 设置采购计划接口*
     */
    public static String getPrUrl(String vlaue, String udbelong, int curpage, int showcount) {
        if (udbelong.length() == 2) {
            if (vlaue.equals("")) {
                return "{'appid':'" + Constants.PR_APPID + "','objectname':'" +
                        Constants.PR_NAME + "','curpage':" + curpage + ",'showcount':" +
                        showcount + ",'option':'read','orderby':'to_number(PRNUM,999999999) desc','sinorsearch':{'PRNUM':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
            } else {
                return "{'appid':'" + Constants.PR_APPID + "','objectname':'" +
                        Constants.PR_NAME + "','curpage':" + curpage + ",'showcount':" +
                        showcount + ",'option':'read','orderby':'to_number(PRNUM,999999999) desc','sinorsearch':{'PRNUM':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
            }
        } else {
            if (vlaue.equals("")) {
                return "{'appid':'" + Constants.PR_APPID + "','objectname':'" +
                        Constants.PR_NAME + "','curpage':" + curpage + ",'showcount':" +
                        showcount + ",'option':'read','orderby':'to_number(PRNUM,999999999) desc','condition':{'UDBELONG':'=" + udbelong + "'},'sinorsearch':{'PRNUM':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
            } else {
                return "{'appid':'" + Constants.PR_APPID + "','objectname':'" +
                        Constants.PR_NAME + "','curpage':" + curpage + ",'showcount':" +
                        showcount + ",'option':'read','orderby':'to_number(PRNUM,999999999) desc','condition':{'UDBELONG':'=" + udbelong + "'},'sinorsearch':{'PRNUM':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
            }
        }
    }

    /**
     * 设置采购计划行接口*
     */
    public static String getPrLineUrl(String prnum, String search, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + Constants.PRLINE_APPID + "','objectname':'" +
                    Constants.PRLINE_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read','condition':{'PRNUM':'=" + prnum + "'}}";
        } else {
            return "{'appid':'" + Constants.PRLINE_APPID + "','objectname':'" +
                    Constants.PRLINE_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read','condition':{'PRNUM':'=" + prnum + "'},'sinorsearch':{'PRLINENUM':'" + search + "'}}";
        }
    }


    /**
     * 设置发票数据*
     */
    public static String getInvoiceUrl(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.INVOICE_APPID + "','objectname':'" + Constants.INVOICE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.INVOICE_APPID + "','objectname':'" + Constants.INVOICE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'INVOICENUM':'" + vlaue + "'}}";
        }
    }

    /**
     * 设置发票行数据*
     */
    public static String getInvoiceLineUrl(String vlaue, String invoicenum, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.INVOICE_APPID + "','objectname':'" + Constants.INVOICELINE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'INVOICENUM':'=" + invoicenum + "'}}";
        } else {
            return "{'appid':'" + Constants.INVOICE_APPID + "','objectname':'" + Constants.INVOICELINE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'INVOICENUM':'=" + invoicenum + "'},'sinorsearch':{'REPORTNUM':'" + vlaue + "'}}";
        }
    }


    /**
     * 设置采购订单接口*
     */
    public static String getPoUrl(String vlaue, String udbelong, int curpage, int showcount) {
        if (udbelong.length() == 2) {
            if (vlaue.equals("")) {
                return "{'appid':'" + Constants.PO_APPID + "','objectname':'" +
                        Constants.PO_NAME + "','curpage':" + curpage + ",'showcount':" +
                        showcount + ",'option':'read','orderby':'to_number(PONUM,999999999) desc','condition':{'UDAPPNAME':'=UDPO'}}";
            } else {
                return "{'appid':'" + Constants.PO_APPID + "','objectname':'" +
                        Constants.PO_NAME + "','curpage':" + curpage + ",'showcount':" +
                        showcount + ",'option':'read','orderby':'to_number(PONUM,999999999) desc','condition':{'UDAPPNAME':'=UDPO'},'sinorsearch':{'PONUM':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
            }
        } else {
            if (vlaue.equals("")) {
                return "{'appid':'" + Constants.PO_APPID + "','objectname':'" +
                        Constants.PO_NAME + "','curpage':" + curpage + ",'showcount':" +
                        showcount + ",'option':'read','orderby':'to_number(PONUM,999999999) desc','condition':{'UDBELONG':'=" + udbelong + "','UDAPPNAME':'=UDPO'}}";
            } else {
                return "{'appid':'" + Constants.PO_APPID + "','objectname':'" +
                        Constants.PO_NAME + "','curpage':" + curpage + ",'showcount':" +
                        showcount + ",'option':'read','orderby':'to_number(PONUM,999999999) desc','condition':{'UDBELONG':'=" + udbelong + "','UDAPPNAME':'=UDPO'},'sinorsearch':{'PONUM':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
            }
        }
    }


    /**
     * 设置货币*
     */
    public static String getCurrencyUrl(String vlaue, int curpage, int showcount) {

        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.PO_APPID + "','objectname':'" +
                    Constants.CURRENCY_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.PO_APPID + "','objectname':'" +
                    Constants.CURRENCY_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read','sinorsearch':{'CURRENCYCODE':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
        }
    }

    /**
     * 设置供应商*
     */
    public static String getCompaniesUrl(String vlaue, int curpage, int showcount) {

        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.UDCOMPANY_APPID + "','objectname':'" +
                    Constants.COMPANIES_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.UDCOMPANY_APPID + "','objectname':'" +
                    Constants.COMPANIES_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read','sinorsearch':{'COMPANY':'" + vlaue + "','NAME':'" + vlaue + "'}}";
        }
    }


    /**
     * 设置管理工单号*
     */
    public static String getWorkOrder(String vlaue, String udbelong, int curpage, int showcount) {

        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" +
                    Constants.WORKORDER_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read','condition':{'STATUS':'=已核准','UDBELONG':'=" + udbelong + "'}}";
        } else {
            return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" +
                    Constants.WORKORDER_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read','condition':{'STATUS':'=已核准','UDBELONG':'=" + udbelong + "'},'sinorsearch':{'WONUM':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
        }
    }

    /**
     * 联系人选择*
     */
    public static String getCompcontactUrl(String vlaue, int curpage, int showcount) {

        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.UDCOMPANY_APPID + "','objectname':'" +
                    Constants.COMPCONTACT_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.UDCOMPANY_APPID + "','objectname':'" +
                    Constants.COMPCONTACT_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read','sinorsearch':{'CONTACT':'" + vlaue + "','VOICEPHONE':'" + vlaue + "'}}";
        }
    }


    /**
     * 设置库存接口*
     */
    public static String getInventoryUrl(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.INVENTORY_APPID + "','objectname':'" +
                    Constants.INVENTORY_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.INVENTORY_APPID + "','objectname':'" +
                    Constants.INVENTORY_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read','sinorsearch':{'ITEMNUM':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
        }
    }

    /**
     * 设置库存接口*
     */
    public static String getInventory1Url(String vlaue, int curpage, int showcount) {

        return "{'appid':'" + Constants.INVENTORY_APPID + "','objectname':'" +
                Constants.INVENTORY_NAME + "','curpage':" + curpage + ",'showcount':" +
                showcount + ",'option':'read','condition':{'ITEMNUM':'=" + vlaue + "'}}";
    }


    /**
     * 设置采购单行接口*
     */
    public static String getPoLineUrl(String vlaue, String search, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + Constants.POLINE_APPID + "','objectname':'" +
                    Constants.POLNE_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read','condition':{'PONUM':'=" + vlaue + "'}}";
        } else {
            return "{'appid':'" + Constants.POLINE_APPID + "','objectname':'" +
                    Constants.POLNE_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read','condition':{'PONUM':'=" + vlaue + "'},'sinorsearch':{'POLINENUM':'" + vlaue + "'}}";
        }
    }

    /**
     * 获取物资发放的接口*
     */


    public static String getLocationUrl(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.LOCATION_APPID + "','objectname':'" +
                    Constants.LOCATION_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read','condition':{'type':'=库房'}}";
        } else {
            return "{'appid':'" + Constants.LOCATION_APPID + "','objectname':'" +
                    Constants.LOCATION_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read','condition':{'type':'=库房'},'sinorsearch':{'LOCATION':'" + vlaue + "'}}";
        }
    }


    /**
     * 设置领料单接口*
     */
    public static String getMaterialUrl(String search, String udbelong, int curpage, int showcount) {
        if (udbelong.length() == 2) {
            if (search.equals("")) {
                return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                        "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'to_number(WONUM,999999999) desc','condition':{'UDAPPTYPE':'=UDMATAPP'}}";
            } else {
                return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                        "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'to_number(WONUM,999999999) desc','condition':{'UDAPPTYPE':'=UDMATAPP'},'sinorsearch':{'WONUM':'" + search + "','DESCRIPTION':'" + search + "'}}";
            }
        } else {
            if (search.equals("")) {
                return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                        "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'to_number(WONUM,999999999) desc','condition':{'UDAPPTYPE':'=UDMATAPP','UDBELONG':'=" + udbelong + "'}}";
            } else {
                return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                        "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'to_number(WONUM,999999999) desc','condition':{'UDAPPTYPE':'=UDMATAPP','UDBELONG':'=" + udbelong + "'},'sinorsearch':{'WONUM':'" + search + "','DESCRIPTION':'" + search + "'}}";
            }
        }

    }

    /**
     * 设置物资编码申请接口*
     */
    public static String getUditemreqUrl(String search, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + Constants.UDITEMREQ_APPID + "','objectname':'" + Constants.UDITEMREQ_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.UDITEMREQ_APPID + "','objectname':'" + Constants.UDITEMREQ_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'UDITEMREQNUM':'" + search + "'}}";
        }

    }

    /**
     * 设置物资编码申请行表接口*
     */
    public static String getUditemreqlineUrl(String search, String uditemreqnum, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + Constants.UDITEMREQ_APPID + "','objectname':'" + Constants.UDITEMREQLINE_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'UDITEMREQNUM':'=" + uditemreqnum + "'}}";
        } else {
            return "{'appid':'" + Constants.UDITEMREQ_APPID + "','objectname':'" + Constants.UDITEMREQLINE_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'UDITEMREQNUM':'=" + uditemreqnum + "'},'sinorsearch':{'NAME':'" + search + "'}}";
        }

    }

    /**
     * 设置物资调出单*
     */
    public static String getMaterialUpUrl(String search, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'udapptype':'=UDTRANSOUT'}}";
        } else {
            return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'udapptype':'=UDTRANSOUT'},'sinorsearch':{'WONUM':'" + search + "'}}";
        }

    }


    /**
     * 设置物资调入单*
     */
    public static String getMaterialInUrl(String search, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + Constants.PO_APPID + "','objectname':'" + Constants.PO_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'udappname':'=UDTRANSIN'}}";
        } else {
            return "{'appid':'" + Constants.PO_APPID + "','objectname':'" + Constants.PO_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'udappname':'=UDTRANSIN'},'sinorsearch':{'PONUM':'" + search + "'}}";
        }

    }


    /**
     * 设置物资借用归还主表*
     */
    public static String getUdbrUrl(String search, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + Constants.UDITEM_APPID + "','objectname':'" + Constants.UDBR_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.UDITEM_APPID + "','objectname':'" + Constants.UDBR_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'UDBRNUM':'" + search + "'}}";
        }

    }

    /**
     * 设置物资借用归还行表*
     */
    public static String getUdbrLineUrl(String search, String udbrnum, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + Constants.UDITEM_APPID + "','objectname':'" + Constants.UDBRLINE_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'UDBRNUM':'=" + udbrnum + "'}}";
        } else {
            return "{'appid':'" + Constants.UDITEM_APPID + "','objectname':'" + Constants.UDBRLINE_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'UDBRNUM':'=" + udbrnum + "'},'sinorsearch':{'UDBRLINENUM':'" + search + "'}}";
        }

    }


    /**
     * 获取资产的数据信息
     */
    public static String getAssetUrl(String assetnum) {
        return "{'appid':'" + Constants.ASSET_APPID + "','objectname':'" + Constants.ASSET_NAME + "'," +
                "'curpage':1" + ",'showcount':10" + ",'option':'read','condition':{'ASSETNUM':'=" + assetnum + "'}}";
    }


    /**
     * 根据Perisonid获取Persion信息
     */
    public static String getPersion(String perisonid) {
        return "{'appid':'" + Constants.PERSON_APPID + "','objectname':'" + Constants.PERSON_NAME + "','option':'read','condition':{'PERSONID':'=" + perisonid + "'}}";
    }


    /**
     * 设置基础数据接口
     */
    public static String getUrl(String appid, String objectname) {
        return "{'appid':'" + appid + "','objectname':'" + objectname + "','option':'read'}";
    }

    /**
     * 设置基础数据接口(分页)
     */
    public static String getUrlPaging(String appid, String objectname) {
        return "{'appid':'" + appid + "','objectname':'" + objectname + "'," +
                "'curpage':" + "1" + ",'showcount':" + "1000" + ",'option':'read'}";
    }


    /**
     * 分公司年度上网电量(总公司查询)
     */
    public static String Fgsnudlview() {
        return "{'appid':'" + Constants.UDRUNLOG_APPID + "','objectname':'" + Constants.FGSNUDLVIEW_NAME + "','option':'read'}";
    }

    /**
     * 分公司月度上网电量(总公司查询)
     */
    public static String Fgsyudlview() {
        return "{'appid':'" + Constants.UDRUNLOG_APPID + "','objectname':'" + Constants.FGSYUDLVIEW_NAME + "','option':'read'}";
    }

    /**
     * 分公司当月单日电量(总公司查询)
     */
    public static String Fgsrudlview() {
        return "{'appid':'" + Constants.UDRUNLOG_APPID + "','objectname':'" + Constants.FGSRUDLVIEW_NAME + "','option':'read'}";
    }

    /**
     * 分公司年度损失电量(总公司查询)
     */
    public static String Fgsnussdlview() {
        return "{'appid':'" + Constants.UDRUNLOG_APPID + "','objectname':'" + Constants.FGSNUSSDLVIEW_NAME + "','option':'read'}";
    }

    /**
     * 分公司月度损失电量(总公司查询)
     */
    public static String Fgsyussdlview() {
        return "{'appid':'" + Constants.UDRUNLOG_APPID + "','objectname':'" + Constants.FGSYUSSDLVIEW_NAME + "','option':'read'}";
    }


    /**
     * 设置电气故障20天*
     */
    public static String getFjdq20VIEW(String branch, String assettype, String apptype, int curpage, int showcount) {
        if (branch.length() == 5) {//分公司
            return "{'appid':'" + Constants.UDRUNLOG_APPID + "','objectname':'" + Constants.FJDQ20VIEW_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ASSETTYPE':'=" + assettype + "','apptype':'=" + apptype + "','BRANCH':'=" + branch + "'}}";
        } else if (branch.length() == 8) {//风电场
            return "{'appid':'" + Constants.UDRUNLOG_APPID + "','objectname':'" + Constants.FJDQ20VIEW_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ASSETTYPE':'=" + assettype + "','apptype':'=" + apptype + "','UDBELONG':'=" + branch + "'}}";
        } else { //总公司
            return "{'appid':'" + Constants.UDRUNLOG_APPID + "','objectname':'" + Constants.FJDQ20VIEW_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ASSETTYPE':'=" + assettype + "','apptype':'=" + apptype + "'}}";
        }
    }


    /**
     * 设置电气故障10至20天*
     */
    public static String getFjdq10VIEW(String branch, String assettype, String apptype, int curpage, int showcount) {
        if (branch.length() == 5) {//分公司
            return "{'appid':'" + Constants.UDRUNLOG_APPID + "','objectname':'" + Constants.FJDQ10VIEW_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ASSETTYPE':'=" + assettype + "','apptype':'=" + apptype + "','BRANCH':'=" + branch + "'}}";
        } else if (branch.length() == 8) {//风电场
            return "{'appid':'" + Constants.UDRUNLOG_APPID + "','objectname':'" + Constants.FJDQ10VIEW_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ASSETTYPE':'=" + assettype + "','apptype':'=" + apptype + "','UDBELONG':'=" + branch + "'}}";
        } else {
            return "{'appid':'" + Constants.UDRUNLOG_APPID + "','objectname':'" + Constants.FJDQ10VIEW_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ASSETTYPE':'=" + assettype + "','apptype':'=" + apptype + "'}}";
        }
    }

    /**
     * 根据编号获取提报单*
     */
    public static String getUdreport(String reportnum, int curpage, int showcount) {
        return "{'appid':'" + Constants.UDREPORT_APPID + "','objectname':'" + Constants.UDREPORT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'to_number(REPORTNUM,999999999) DESC','condition':{'REPORTNUM':'=" + reportnum + "'}}";

    }


    /**
     * 设置物资发放*
     */
    public static String getLocatiosUrl(String search, String udbelong, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + Constants.LOCATION_APPID + "','objectname':'" + Constants.LOCATION_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'udbelong':'=" + udbelong + "','type':'=库房'}}";
        } else if (search.matches("[0-9]+")) {
            return "{'appid':'" + Constants.LOCATION_APPID + "','objectname':'" + Constants.LOCATION_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'LOCATION':'=" + search + "','udbelong':'=" + udbelong + "','type':'=库房'}}";
        } else {
            return "{'appid':'" + Constants.LOCATION_APPID + "','objectname':'" + Constants.LOCATION_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'udbelong':'=" + udbelong + "','type':'=库房'},'sinorsearch':{'LOCATION':'" + search + "','DESCRIPTION':'" + search + "'}}";
        }

    }


    /**
     * 设置选择物资数据*
     */
    public static String getINVENTORYUrl(String vlaue, String location, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.INVENTORY_APPID + "','objectname':'" + Constants.INVENTORY_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'LOCATION':'=" + location + "','STATUS':'=活动'}}";
        } else {
            return "{'appid':'" + Constants.INVENTORY_APPID + "','objectname':'" + Constants.INVENTORY_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'LOCATION':'=" + location + "','STATUS':'=活动'},'sinorsearch':{'ITEMNUM':'" + vlaue + "','ITEMDESC':'" + vlaue + "'}}";
        }
    }


    /**
     * 不分页获取信息方法*
     */
    public static void getData(final Context cxt, String data, final HttpRequestHandler<String> handler) {
        Log.i(TAG, "data" + data);

        String base_url = AccountUtils.getIpAddress(cxt) + "mobile/" + "common/api";

        Log.i(TAG, "base_url=" + base_url);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("data", data);
        client.setMaxConnections(5);
        client.setTimeout(600000);
        client.get(base_url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, "查询失败");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "responseString=" + responseString);
                String result = JsonUtils.parsingResults1(cxt, responseString);
                Log.i(TAG, "result=" + result);
                SafeHandler.onSuccess(handler, result);
            }

        });
    }


    /**
     * 不分页获取信息方法*
     */
    public static void getData1(final Context cxt, String data, final HttpRequestHandler<String> handler) {
        String base_url = AccountUtils.getIpAddress(cxt) + "mobile/" + "common/api";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("data", data);
        client.setMaxConnections(5);
        client.setTimeout(600000);
        client.get(base_url, params, new AsyncHttpResponseHandler() {


            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                super.onProgress(bytesWritten, totalSize);

                int count = (int) ((bytesWritten * 1.0 / totalSize) * 100);
            }


            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.i(TAG, "responseString=" + responseBody.length);
                String result = JsonUtils.parsingResults1(cxt, responseBody + "");
                SafeHandler.onSuccess(handler, result);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                SafeHandler.onFailure(handler, "查询失败");
            }
        });
    }

    /**
     * 不分页获取信息方法*
     */
    public static void getData2(final Context cxt, String data, final HttpRequestHandler<String> handler) {
        AsyncHttpClient client = new AsyncHttpClient();

        client.get("https://www.baidu.com", new AsyncHttpResponseHandler() {

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                super.onProgress(bytesWritten, totalSize);
                int count = (int) ((bytesWritten * 1.0 / totalSize) * 100);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


    /**
     * 解析返回的结果--分页*
     */
    public static void getDataPagingInfo(final Context cxt, String data, final HttpRequestHandler<Results> handler) {
        String base_url = AccountUtils.getIpAddress(cxt) + "mobile/" + "common/api";
        Log.i(TAG, "base_url=" + base_url + ",data=" + data);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("data", data);
        client.setMaxConnections(5);
        client.setTimeout(600000);
        client.get(base_url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, cxt.getString(R.string.get_data_info_fail));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "responseString=" + responseString);
                Results result = JsonUtils.parsingResults(cxt, responseString);
                if (result.getResultlist() == null) {
                    SafeHandler.onFailure(handler, cxt.getString(R.string.get_data_info_fail));
                } else {
                    SafeHandler.onSuccess(handler, result, result.getCurpage(), result.getShowcount());
                }


            }
        });
    }


}
