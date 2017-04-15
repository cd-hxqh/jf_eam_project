package com.jf_eam_project.api;

import android.content.Context;
import android.util.Log;

import com.jf_eam_project.bean.Results;
import com.jf_eam_project.bean.Wlh;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Assignment;
import com.jf_eam_project.model.Labtrans;
import com.jf_eam_project.model.Po;
import com.jf_eam_project.model.Udinspojxxm;
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.model.Wplabor;
import com.jf_eam_project.model.Wpmaterial;
import com.jf_eam_project.utils.AccountUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * Json数据解析封装类
 */
public class JsonUtils {
    private static final String TAG = "JsonUtils";


    /**
     * 解析登录信息*
     */
    public static String parsingAuthStr(final Context cxt, String data) {
        String isSuccess = null;
        String errmsg = null;
        try {
            JSONObject json = new JSONObject(data);
            String jsonString = json.getString("errcode");
            errmsg = json.getString("errmsg");


            if (jsonString.equals(Constants.LOGINSUCCESS) || jsonString.equals(Constants.CHANGEIMEI)) {

                String result = json.getString("result");
                JSONObject jsonObject = new JSONObject((result));

                String displayName = jsonObject.getString("displayName");
                String personId = jsonObject.getString("personId");
                AccountUtils.setDisplayName(cxt, displayName);
                AccountUtils.setPersonId(cxt, personId);
            } else if (jsonString.equals(Constants.USERNAMEERROR)) {
                errmsg = "用户名或密码错误";
            }


            return errmsg;


        } catch (JSONException e) {
            e.printStackTrace();
            return isSuccess;
        }
    }

    /**
     * 解析新增工单返回信息
     *
     * @param data
     * @return
     */
    public static String parsingInsertWO(String data) {
        String woNum = null;
        try {
            JSONObject object = new JSONObject(data);
            if (object.has("success") && object.getString("success").equals("成功")) {
                if (object.has("wonum")) {
                    woNum = object.getString("wonum");
                } else if (object.has("WONUM")) {
                    woNum = object.getString("WONUM");
                }
            } else if (object.has("errorMsg")) {
                woNum = object.getString("errorMsg");
            } else {
                woNum = "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return woNum;
    }

    /**
     * 解析修改工单返回信息
     *
     * @param data
     * @return
     */
    public static String parsingUpdataWO(String data) {
        Log.i(TAG, "data=" + data);
        String success = null;
        try {
            JSONObject object = new JSONObject(data);
            if (object.has("success") && object.getString("success").equals("成功")) {
                success = object.getString("success");
            } else if (object.has("errorMsg")) {
                success = object.getString("errorMsg");
            } else {
                success = "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static String parsingwfserviceResult(String data) {
        String result = null;
        try {
            JSONObject object = new JSONObject(data);
            if (object.has("errorMsg")) {
                result = object.getString("errorMsg");
            } else {
                result = "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String parsingwfserviceGoOnResult(String data) {
        String result = null;
        try {
            JSONObject object = new JSONObject(data);
            if (object.has("status")) {
                result = object.getString("status");
            } else {
                result = "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    //解析查询工作流返回的数据
    public static String parsingwfstatusResult(String data) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            if (jsonObject.has("ACTIVE")) {
                return jsonObject.getString("ACTIVE");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 分页解析返回的结果*
     */
    public static Results parsingResults(Context ctx, String data) {
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
            } else {
                results = new Results();
            }

            return results;


        } catch (JSONException e) {
            return null;
        }

    }

    /**
     * 分页解析返回的结果*
     */
    public static Results parsingResults2(Context ctx, String data) {
        String result = null;
        Results results = null;
        try {

            JSONObject rJson = new JSONObject(data);
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


            return results;


        } catch (JSONException e) {
            return null;
        }

    }

    /**
     * 不分页解析返回的结果*
     */
    public static String parsingResults1(Context ctx, String data) {

        String result = null;
        try {
            JSONObject json = new JSONObject(data);
            String jsonString = json.getString("errcode");
            if (jsonString.equals(Constants.GETDATASUCCESS)) {
                result = json.getString("result");
            }

            return result;


        } catch (JSONException e) {
            e.printStackTrace();
            return result;
        }
    }

    public static String WorkToJson(WorkOrder workOrder, ArrayList<Woactivity> woactivities, ArrayList<Wplabor> wplabors,
                                    ArrayList<Wpmaterial> wpmaterials, ArrayList<Assignment> assignments, ArrayList<Labtrans> labtranses) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (!workOrder.isnew) {//修改
                jsonObject.put("WONUM", workOrder.wonum);
            }
            jsonObject.put("UDAPPTYPE", "UDWOTRACK");
            jsonObject.put("DESCRIPTION", workOrder.description);
            jsonObject.put("UDWOTYPE", workOrder.udwotype);
            jsonObject.put("ASSETNUM", workOrder.assetnum);
            if (workOrder.isnew) {
                jsonObject.put("ASSETDESC", workOrder.assetdesc);
                jsonObject.put("LOCATIONDESC", workOrder.locationdesc);
                jsonObject.put("DISPLAYNAME", workOrder.displayname);
            }
            jsonObject.put("LOCATION", workOrder.location);

            jsonObject.put("STATUS", workOrder.status);
            jsonObject.put("STATUSDATE", workOrder.statusdate);
            jsonObject.put("LCTYPE", workOrder.lctype);
            jsonObject.put("FAILURECODE", workOrder.failurecode);
            jsonObject.put("PROBLEMCODE", workOrder.problemcode);
            jsonObject.put("CREATEDATE", workOrder.createdate);
            jsonObject.put("JPNUM", workOrder.jpnum);
            jsonObject.put("TARGSTARTDATE", workOrder.targstartdate);
            jsonObject.put("TARGCOMPDATE", workOrder.targcompdate);
            jsonObject.put("ACTSTART", workOrder.actstart);
            jsonObject.put("ACTFINISH", workOrder.actfinish);
            jsonObject.put("REPORTEDBY", workOrder.reportedby);
            jsonObject.put("DESCRIPTION_LONGDESCRIPTION", workOrder.description_longdescription);
            jsonObject.put("ISXQ", workOrder.isxq);
            jsonObject.put("ISYHPC", workOrder.isyhpc);
            jsonObject.put("POWERLOSS", workOrder.powerloss);
            jsonObject.put("SPEED", workOrder.speed);
            jsonObject.put("LARGEPART", workOrder.largepart);
            jsonObject.put("ISSUEMATERIAL", workOrder.issuematerial);
            jsonObject.put("SHUTDOWN", workOrder.shutdown);
            jsonObject.put("REPORTDATE", workOrder.reportdate);

            JSONObject object = new JSONObject();
            if (woactivities != null && woactivities.size() != 0) {
                object.put("WOACTIVITY", "");
                JSONArray woactivityArray = new JSONArray();
                JSONObject woactivityObj;
                for (int i = 0; i < woactivities.size(); i++) {
                    woactivityObj = new JSONObject();
                    woactivityObj.put("TASKID", woactivities.get(i).taskid);
                    woactivityObj.put("DESCRIPTION", woactivities.get(i).description);
                    woactivityObj.put("TARGSTARTDATE", woactivities.get(i).targstartdate);
                    woactivityObj.put("TARGCOMPDATE", woactivities.get(i).targcompdate);
                    woactivityObj.put("ACTSTART", woactivities.get(i).actstart);
                    woactivityObj.put("ACTFINISH", woactivities.get(i).actfinish);
                    woactivityObj.put("ESTDUR", woactivities.get(i).estdur);
                    if (!workOrder.isnew) {
                        woactivityObj.put("TYPE", woactivities.get(i).type);
                    }
                    woactivityArray.put(woactivityObj);
                }
                jsonObject.put("WOACTIVITY", woactivityArray);
            }
            if (wplabors != null && wplabors.size() != 0) {
                object.put("WPLABOR", "");
                JSONArray wplaborArray = new JSONArray();
                JSONObject wplaborObj;
                for (int i = 0; i < wplabors.size(); i++) {
                    wplaborObj = new JSONObject();
                    wplaborObj.put("CRAFT", wplabors.get(i).craft);
                    wplaborObj.put("QUANTITY", wplabors.get(i).quantity);
                    wplaborObj.put("LABORHRS", wplabors.get(i).laborhrs);
                    if (!workOrder.isnew) {
                        wplaborObj.put("TYPE", wplabors.get(i).type);
                    }
                    if (wplabors.get(i).wplaborid != null && !wplabors.get(i).wplaborid.equals("")) {
                        wplaborObj.put("WPLABORID", wplabors.get(i).wplaborid);
                    }
                    wplaborArray.put(wplaborObj);
                }
                jsonObject.put("WPLABOR", wplaborArray);
            }
            if (wpmaterials != null && wpmaterials.size() != 0) {
                object.put("WPMATERIAL", "");
                JSONArray wpmaterialArray = new JSONArray();
                JSONObject wpmaterialObj;
                for (int i = 0; i < wpmaterials.size(); i++) {
                    wpmaterialObj = new JSONObject();
                    wpmaterialObj.put("ITEMNUM", wpmaterials.get(i).itemnum);
                    wpmaterialObj.put("ITEMQTY", wpmaterials.get(i).itemqty);
                    wpmaterialObj.put("LOCATION", wpmaterials.get(i).location);
                    wpmaterialObj.put("STORELOCSITE", wpmaterials.get(i).storelocsite);
                    wpmaterialObj.put("REQUESTBY", wpmaterials.get(i).requestby);
                    wpmaterialObj.put("REQUIREDATE", wpmaterials.get(i).requiredate);
                    if (!workOrder.isnew) {
                        wpmaterialObj.put("TYPE", wpmaterials.get(i).type);
                    }
                    if (wpmaterials.get(i).wpitemid != null && !wpmaterials.get(i).wpitemid.equals("")) {
                        wpmaterialObj.put("WPITEMID", wpmaterials.get(i).wpitemid);
                    }
                    wpmaterialArray.put(wpmaterialObj);
                }
                jsonObject.put("WPMATERIAL", wpmaterialArray);
            }
            if (assignments != null && assignments.size() != 0) {
                object.put("ASSIGNMENT", "");
                JSONArray assignmentArray = new JSONArray();
                JSONObject assignmentObj;
                for (int i = 0; i < assignments.size(); i++) {
                    assignmentObj = new JSONObject();
                    assignmentObj.put("LABORCODE", assignments.get(i).laborcode);
                    assignmentObj.put("CRAFT", assignments.get(i).craft);
                    assignmentObj.put("LABORHRS", assignments.get(i).laborhrs);
                    if (!workOrder.isnew) {
                        assignmentObj.put("TYPE", assignments.get(i).type);
                    }
                    if (assignments.get(i).assignmentid != null && !assignments.get(i).assignmentid.equals("")) {
                        assignmentObj.put("ASSIGNMENTID", assignments.get(i).assignmentid);
                    }
                    assignmentArray.put(assignmentObj);
                }
                jsonObject.put("ASSIGNMENT", assignmentArray);
            }
            if (labtranses != null && labtranses.size() != 0) {
                object.put("LABTRANS", "");
                JSONArray labtransArray = new JSONArray();
                JSONObject labtransObj;
                for (int i = 0; i < labtranses.size(); i++) {
                    labtransObj = new JSONObject();
                    labtransObj.put("LABORCODE", labtranses.get(i).laborcode);
                    labtransObj.put("STARTDATE", labtranses.get(i).startdate);
                    labtransObj.put("REGULARHRS", labtranses.get(i).regularhrs);
                    labtransObj.put("CRAFT", labtranses.get(i).craft);
                    labtransObj.put("PAYRATE", "0");
                    if (labtranses.get(i).labtransid != null && !labtranses.get(i).labtransid.equals("")) {
                        labtransObj.put("LABTRANSID", labtranses.get(i).labtransid);
                    }
                    if (labtranses.get(i).type != null && !labtranses.get(i).type.equals("")) {
                        labtransObj.put("TYPE", labtranses.get(i).type);
                    }
                    labtransArray.put(labtransObj);

                }
                jsonObject.put("LABTRANS", labtransArray);
            }
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(object);
            jsonObject.put("relationShip", jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


    /**
     * 封装Udinspojxxm的json
     */
    public static String udinspojxxmJson(Udinspojxxm udinspojxxm) {

        JSONObject json = new JSONObject();

        try {
            json.put("UDINSPOJXXMID", udinspojxxm.udinspojxxmid + "");

            json.put("UDINSPOASSETNUM", udinspojxxm.udinspoassetnum);
            json.put("TYPE", Constants.UPDATE);
            json.put("UDINSPOJXXM1", udinspojxxm.udinspojxxm1);
            json.put("UDINSPOJXXM2", udinspojxxm.udinspojxxm2);
            json.put("UDINSPOJXXM3", udinspojxxm.udinspojxxm3);


            json.put("UDINSPOJXXM2", udinspojxxm.udinspojxxm2);
            json.put("UDINSPOJXXM4", udinspojxxm.udinspojxxm4);
            json.put("EXECUTION", udinspojxxm.execution);


            JSONObject jsonObject = new JSONObject();
            jsonObject.put("GRADESON", jsonUdinspojxxmInfo(json.toString()));
            Log.i(TAG, "json=" + jsonObject.toString());
            return jsonObject.toString();

        } catch (JSONException e) {
            return null;
        }

    }


    /**
     * 封装udinspojxxm信息*
     */
    private static JSONArray jsonUdinspojxxmInfo(String str) {
        JSONArray jsonArray = null;

        String json3 = "";
        try {
            json3 = "[" + str + "]";

            jsonArray = new JSONArray(json3);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return jsonArray;
    }


    /**
     * 封装Po的json
     */
    public static String potoJson(Po po) {

        JSONObject json = new JSONObject();

        try {
            json.put("DESCRIPTION", po.description);  //描述
            json.put("PURCHASEAGENT", po.purchaseagent); //录入人
            json.put("ORDERDATE", po.orderdate); //录入日期
            json.put("BUYERCOMPANY", po.buyercompany); //买方公司
            json.put("CONTRACTREFNUM", po.contractrefnum); //合同引用
            json.put("REQUIREDDATE", po.requireddate);//计划到货日期
            json.put("FOLLOWUPDATE", po.followupdate);//实际到货日期
            json.put("PRETAXTOTAL", po.pretaxtotal);////税前总计
            json.put("TOTALTAX1", po.totaltax1);//税款总计
            json.put("TOTALCOST", po.totalcost);//成本总计
            json.put("CURRENCYCODE", po.currencycode);//货币
            json.put("VENDOR", po.vendor);//供应商
            json.put("CONTACT", po.contact);//联系人
            json.put("SITEID", po.siteid);//站点
            json.put("UDAPPNAME", po.udappname);//应用程序名称
            json.put("BRANCH", po.branch);//分公司
            json.put("UDBELONG", po.udbelong);//运行单位


            JSONArray jsonArray = new JSONArray();
            json.put("relationShip", jsonArray);
            return json.toString();

        } catch (JSONException e) {
            return null;
        }

    }

    /**
     * 封装WorkOder的json
     */
    public static String potoWorlOrder(WorkOrder workOrder, List<Wlh> wlhList) {

        JSONObject json = new JSONObject();

        try {
            json.put("DESCRIPTION", workOrder.description);  //描述
            json.put("UDWONUM", workOrder.udwonum); //关联工单号
            json.put("CREATEBY", workOrder.displayname); //申请人
            json.put("CREATEDATE", workOrder.createdate); //创建时间
            json.put("BRANCH", workOrder.udbelong_description); //分公司
            json.put("UDBELONG", workOrder.udbelong);//运行单位
            json.put("UDAPPTYPE", workOrder.udapptype);//类型

            if (wlhList != null || wlhList.size() != 0) {
                JSONArray wlhArray = new JSONArray();
                JSONObject wlhObj;
                for (int i = 0; i < wlhList.size(); i++) {
                    wlhObj = new JSONObject();
                    Field[] field1 = wlhList.get(i).getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                    for (int j = 0; j < field1.length; j++) {
                        field1[j].setAccessible(true);
                        String name = field1[j].getName();//获取属性的名字
                        Method getOrSet = null;
                        try {
                            getOrSet = wlhList.get(i).getClass().getMethod("get" + name);
                            Object value = null;
                            value = getOrSet.invoke(wlhList.get(i));
                            if (value != null) {
                                wlhObj.put(name, value + "");

                            }
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    wlhArray.put(wlhObj);
                }
                try {
                    json.put("SHOWPLANMATERIAL", wlhArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            JSONArray jsonArray = new JSONArray();
            json.put("relationShip", jsonArray);


        } catch (JSONException e) {
            return null;
        }

        Log.i(TAG, "json=" + json);
        return json.toString();

    }


    /**
     * 解析新增采购单返回信息
     *
     * @param data
     * @return
     */
    public static String parsingInsertPO(String data) {
        String woNum = null;
        try {
            JSONObject object = new JSONObject(data);
            if (object.has("success") && object.getString("success").equals("成功")) {
                if (object.has("PONUM")) { //采购单
                    woNum = object.getString("PONUM");
                } else if (object.has("WONUM")) { //领料单，工单
                    woNum = object.getString("WONUM");
                } else if (object.has("REPORTNUM")) { //故障提报单
                    woNum = object.getString("REPORTNUM");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return woNum;
    }


}