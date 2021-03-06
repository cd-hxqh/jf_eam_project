package com.jf_eam_project.webserviceclient;


import android.content.Context;
import android.util.Log;

import com.jf_eam_project.api.JsonUtils;
import com.jf_eam_project.utils.AccountUtils;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by think on 2015/8/11.
 * webservice方法
 */
public class AndroidClientService {
    private static final String TAG = "AndroidClientService";
    public String NAMESPACE = "http://www.ibm.com/maximo";
    public static String NAMESPACE1 = "http://www.ibm.com/maximo";
    /**
     * 旧*
     */
//    public String url = "http://1.202.243.112:7001/meaweb/services/WOSERVICE";
    public String url = "http://1.202.243.112:7001/meaweb/wsdl/WFSERVICE.wsdl";
    /**
     * 新*
     */
//    public String url = "http://10.1.29.155:7001/meaweb/services/WFSERVICE";
    public int timeOut = 1200000;

    public AndroidClientService() {
    }

    public AndroidClientService(String url) {
        this.url = url;
    }

    public void setTimeOut(int seconds) {
        this.timeOut = seconds * 1000;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 新增工单
     *
     * @param string
     * @return
     */
    public String InsertWO(Context context, String string, String personId) {
        String url = AccountUtils.getIpAddress(context) + "meaweb/services/MOBILESERVICE";
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "mobileserviceInsertMbo");
        soapReq.addProperty("json", string);
        soapReq.addProperty("flag", 1);
        soapReq.addProperty("mboObjectName", "WORKORDER");
        soapReq.addProperty("mboKey", "WONUM");
        soapReq.addProperty("personId", personId);
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        String obj = null;
        String wonum = null;
        try {
            obj = soapEnvelope.getResponse().toString();
            wonum = JsonUtils.parsingInsertWO(obj);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return wonum;
    }


    /**
     * 新增采购订单
     *
     * @param string
     * @return
     */
    public String InsertGENERAL(Context context, String string, String mboObjectName, String mboKey, String personId) {
        Log.i(TAG, "string=" + string);
        String url = AccountUtils.getIpAddress(context) + "meaweb/services/MOBILESERVICE";
        Log.i(TAG, "url=" + url);
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "mobileserviceInsertMbo");
        soapReq.addProperty("json", string);
        soapReq.addProperty("flag", 1);
        soapReq.addProperty("mboObjectName", mboObjectName);
        soapReq.addProperty("mboKey", mboKey);
        soapReq.addProperty("personId", personId);
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        String obj = null;
        String ponum = null;
        try {
            obj = soapEnvelope.getResponse().toString();
            ponum = JsonUtils.parsingInsertPO(obj);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return ponum;
    }


    /**
     * 更新工单
     */
    public String UpdataWO(Context context, String string, String wonum) {
        String url = AccountUtils.getIpAddress(context) + "meaweb/services/MOBILESERVICE";
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "mobileserviceUpdateMbo");
        soapReq.addProperty("json", string);
        soapReq.addProperty("mboObjectName", "WORKORDER");
        soapReq.addProperty("mboKeyValue", wonum);
        soapReq.addProperty("mboKey", "WONUM");
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url, timeOut);
        try {
            httpTransport.call("", soapEnvelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        String obj = null;
        String result = null;
        try {
            obj = soapEnvelope.getResponse().toString();
            result = JsonUtils.parsingUpdataWO(obj);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return result;
    }

    /**
     * 开始工作流
     *
     * @return
     */
    public String startwf(Context context, String processname, String mbo, String keyValue, String key) {
        Log.i(TAG, "processname=" + processname + ",mbo=" + mbo + ",keyValue=" + keyValue.replace(",", "") + ",key=" + key);
        String url = AccountUtils.getIpAddress(context) + "meaweb/services/WFSERVICE";
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "wfservicestartWF");
        soapReq.addProperty("processname", processname);//工单：UDFJHWO，采购申请（含零星和集中采购风电场部分审批）：UDPR，集中汇总采购计划流程（分公司发起）：UDPRHZ
        soapReq.addProperty("mbo", mbo);//工单WORKORDER,采购申请pr
        soapReq.addProperty("keyValue", keyValue.replace(",", ""));//对应的表ID的值，如工单需要传送workorderid的值，采购申请prnum的值
        soapReq.addProperty("key", key);//对应的表ID，如工单：wonum，采购申请，prnum
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        String obj = null;
        String result = null;
        try {
            obj = soapEnvelope.getResponse().toString();
            Log.i(TAG, "obj=" + obj);
//            result = JsonUtils.parsingwfserviceResult(obj);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return obj;
    }

    /**
     * 审批工作流
     *
     * @return
     */
    public String wfGoOn(Context context, String processname, String mbo, String keyValue, String key, String zx, String desc) {

        String url = AccountUtils.getIpAddress(context) + "meaweb/services/WFSERVICE";

        Log.i(TAG, "url=" + url);
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "wfservicewfGoOn");
        soapReq.addProperty("processname", processname);//工单：UDFJHWO，采购申请（含零星和集中采购风电场部分审批）：UDPR，集中汇总采购计划流程（分公司发起）：UDPRHZ
        soapReq.addProperty("mboName", mbo);//工单WORKORDER,采购申请pr
        soapReq.addProperty("keyValue", keyValue);//对应的表ID的值，如工单需要传送wonum的值，采购申请prnum的值
        soapReq.addProperty("key", key);//对应的表ID，如工单：wonum，采购申请，prnum
        soapReq.addProperty("zx", zx);//审批的结果，1为审批通过，0为审批不通过
        if (!desc.equals("")) {
            soapReq.addProperty("desc", desc);//审批意见
        }
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException e) {
            return null;
        } catch (XmlPullParserException e) {
            return null;
        }
        String obj = null;
        String result = null;
        try {
            obj = soapEnvelope.getResponse().toString();
            result = JsonUtils.parsingwfserviceGoOnResult(obj);
        } catch (SoapFault soapFault) {
            Log.i(TAG, "ssssss");
            return null;
        }
        return result;
    }

    /**
     * 审批工作流
     *
     * @return
     */
    public static String wfGoOn1(Context context, String processname, String mbo, String keyValue, String key, String zx, String desc) {

        String url = AccountUtils.getIpAddress(context) + "meaweb/services/WFSERVICE";

        Log.i(TAG, "url=" + url);
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE1, "wfservicewfGoOn");
        soapReq.addProperty("ownertable", processname);//工单：UDFJHWO，采购申请（含零星和集中采购风电场部分审批）：UDPR，集中汇总采购计划流程（分公司发起）：UDPRHZ
        soapReq.addProperty("mboName", mbo);//工单WORKORDER,采购申请pr
        soapReq.addProperty("memo", keyValue);//对应的表ID的值，如工单需要传送wonum的值，采购申请prnum的值
        soapReq.addProperty("key", key);//对应的表ID，如工单：wonum，采购申请，prnum
        soapReq.addProperty("zx", zx);//审批的结果，1为审批通过，0为审批不通过
        if (!desc.equals("")) {
            soapReq.addProperty("desc", desc);//审批意见
        }
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url, 1200000);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException e) {
            return null;
        } catch (XmlPullParserException e) {
            return null;
        }
        String obj = null;
        String result = null;
        try {
            obj = soapEnvelope.getResponse().toString();
            result = JsonUtils.parsingwfserviceGoOnResult(obj);
        } catch (SoapFault soapFault) {
            Log.i(TAG, "ssssss");
            return null;
        }
        return result;
    }


    /**
     * 新增巡检
     *
     * @param string
     * @return
     */
    public String InsertPO(Context context, String string) {
        String url = AccountUtils.getIpAddress(context) + "meaweb/services/COSERVICE";
        Log.i(TAG, "string=" + string);
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "coserviceinsertCheckOrder");
        soapReq.addProperty("json", string);
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        }
        String obj = null;
        String result = null;
        try {
            obj = soapEnvelope.getResponse().toString();
            result = obj;
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return result;
    }


    /**
     * 更新巡检
     *
     * @return
     */
    public String UpdatePO(Context context, String string, String key) {

        Log.i(TAG, "string=" + string + ",key=" + key);
        String url = AccountUtils.getIpAddress(context) + "meaweb/services/COSERVICE";
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "coserviceUpdateCheckOrder");
        soapReq.addProperty("json", string);
        if (!key.equals("")) {
            soapReq.addProperty("key", key);
        }
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        }
        String obj = null;
        String result = null;
        try {
            obj = soapEnvelope.getResponse().toString();
            result = obj;
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return result;
    }

    /**
     * 新增提报单
     *
     * @param string
     * @return
     */
    public String addReport(Context context, String string, String key) {
        String url = AccountUtils.getIpAddress(context) + "meaweb/services/UDRPSERVICE";
//        String url = AccountUtils.getIpAddress(context) + "meaweb/wsdl/UDRPSERVICE";

        Log.i(TAG, "url=" + url);
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "udrpserviceinsertReport");
        soapReq.addProperty("json", string);
        if (!key.equals("")) {
            soapReq.addProperty("key", key);
        }
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        }
        String obj = null;
        String result = null;
        try {
            obj = soapEnvelope.getResponse().toString();
            result = obj;
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return result;
    }


}
