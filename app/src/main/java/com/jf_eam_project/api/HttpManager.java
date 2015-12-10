package com.jf_eam_project.api;


import android.content.Context;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.jf_eam_project.R;
import com.jf_eam_project.application.BaseApplication;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.config.Constants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;


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


        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("loginid", username);
        params.put("password", password);
        params.put("imei", imei);
        client.post(Constants.SIGN_IN_URL, params, new TextHttpResponseHandler() {


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, IMErrorType.errorMessage(cxt, IMErrorType.ErrorLoginFailure));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "SstatusCode=" + statusCode + "responseString=" + responseString);
                if (statusCode == 200) {
                    String errmsg = JsonUtils.parsingAuthStr(cxt, responseString);
                    SafeHandler.onSuccess(handler, errmsg);
                }
            }
        });
    }

    /**
     * 设置工单接口*
     */
    public static String getworkorderUrl(String type, String search, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'UDWOTYPE':'" + type + "'}}";
        } else {
            return "{'appid':'" + Constants.UDWOCM_APPID + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'UDWOTYPE':'" + type + "','WONUM':'" + search + "'}}";
        }

    }

    /**
     * 设置计划任务接口*
     */
    public static String getwoactivityUrl(int curpage, int showcount) {
        return "{'appid':'" + Constants.UDWOCM_APPID +  "','objectname':'" +
                Constants.WOACTIVITY_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置计划员工接口*
     */
    public static String getWplaborUrl(int curpage, int showcount) {
        return "{'appid':'" + Constants.UDWOCM_APPID +  "','objectname':'" +
                Constants.WPLABOR_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置计划物料接口*
     */
    public static String getWpmaterialUrl(int curpage, int showcount) {
        return "{'appid':'" + Constants.UDWOCM_APPID +  "','objectname':'" +
                Constants.WPMATERIAL_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置计划服务接口*
     */
    public static String getWpserviceUrl(int curpage, int showcount) {
        return "{'appid':'" + Constants.UDWOCM_APPID +  "','objectname':'" +
                Constants.WPSERVICE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置计划工具接口*
     */
    public static String getWptoolUrl(int curpage, int showcount) {
        return "{'appid':'" + Constants.UDWOCM_APPID +  "','objectname':'" +
                Constants.WPTOOL_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置任务分配接口*
     */
    public static String getAssignmentUrl(int curpage, int showcount) {
        return "{'appid':'" + Constants.UDWOCM_APPID +  "','objectname':'" +
                Constants.ASSIGNMENT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置故障报告接口*
     */
    public static String getFailurereportUrl(String wonum,int curpage, int showcount) {
        return "{'appid':'" + Constants.UDWOCM_APPID +  "','objectname':'" +
                Constants.FAILUREREPORT_NAME + "','curpage':" + curpage + ",'showcount':" +
                showcount + ",'option':'read','condition':{'WONUM':'" + wonum + "'}}";
    }

    /**
     * 设置采购 计划接口*
     */
    public static String getPrUrl(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.PR_APPID + "','objectname':'" +
                    Constants.PR_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.PR_APPID + "','objectname':'" +
                    Constants.PR_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read','condition':{'PRNUM':'" + vlaue + "'}}";
        }
    }

    /**
     * 设置采购计划行接口*
     */
    public static String getPrLineUrl(String vlaue, String search, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.PRLINE_APPID + "','objectname':'" +
                    Constants.PRLINE_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read','condition':{'PRNUM':'" + vlaue + "'}}";
        } else {
            return "{'appid':'" + Constants.PRLINE_APPID + "','objectname':'" +
                    Constants.PRLINE_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read','condition':{'PRNUM':'" + vlaue + "','PRLINENUM':'" + search + "'}}";
        }
    }


    /**
     * 设置发票数据*
     */
    public static String getInvoiceUrl(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.INVOICE_APPID + "','objectname':'" + Constants.INVOICE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.INVOICE_APPID + "','objectname':'" + Constants.INVOICE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'INVOICENUM':'" + vlaue + "'}}";
        }
    }


    /**
     * 设置采购订单接口*
     */
    public static String getPoUrl(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.PO_APPID + "','objectname':'" +
                    Constants.PO_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.PO_APPID + "','objectname':'" +
                    Constants.PO_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read','condition':{'PONUM':'" + vlaue + "'}}";
        }
    }


    /**
     * 设置采购单行接口*
     */
    public static String getPoLineUrl(String vlaue, String search, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.POLINE_APPID + "','objectname':'" +
                    Constants.POLNE_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read','condition':{'PONUM':'" + vlaue + "'}}";
        } else {
            return "{'appid':'" + Constants.POLINE_APPID + "','objectname':'" +
                    Constants.POLNE_NAME + "','curpage':" + curpage + ",'showcount':" +
                    showcount + ",'option':'read','condition':{'PONUM':'" + vlaue + "','POLINENUM':'" + search + "'}}";
        }
    }


//    /**
//     * 不分页获取信息方法*
//     */
//    public static void getData(final Context cxt, String data, final HttpRequestHandler<Results> handler) {
//        AsyncHttpClient client = new AsyncHttpClient();
//        RequestParams params = new RequestParams();
//        params.put("data", data);
//        client.get(Constants.BASE_URL, params, new TextHttpResponseHandler() {
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                SafeHandler.onFailure(handler, cxt.getString(R.string.get_data_info_fail));
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, String responseString) {
//
//                Results result = JsonUtils.parsingResults1(cxt, responseString);
//
//                SafeHandler.onSuccess(handler, result, result.getCurpage(), result.getShowcount());
//
//            }
//        });
//    }
//
//

    /**
     * 解析返回的结果--分页*
     */
    public static void getDataPagingInfo(final Context cxt, String data, final HttpRequestHandler<Results> handler) {
        Log.i(TAG, "data=" + data);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("data", data);
        client.get(Constants.BASE_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, cxt.getString(R.string.get_data_info_fail));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "statusCode" + "responseString=" + responseString);
                Results result = JsonUtils.parsingResults(cxt, responseString);

                SafeHandler.onSuccess(handler, result, result.getCurpage(), result.getShowcount());
            }
        });
    }


}
