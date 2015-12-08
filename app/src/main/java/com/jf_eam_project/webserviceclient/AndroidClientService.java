package com.jf_eam_project.webserviceclient;


import com.jf_eam_project.api.JsonUtils;
import com.jf_eam_project.model.Webservice_result;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by think on 2015/8/11.
 * webservice���ӷ���
 */
public class AndroidClientService {
    private static final String TAG = "AndroidClientService";
    public String NAMESPACE = "http://www.ibm.com/maximo";
    public String url = "http://61.49.28.246:7001/meaweb/services/WOSERVICE";
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
     * ��ӹ�������
     * @param string
     * @return
     */
    public Webservice_result InsertWO(String string){
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "woserviceInsertWO");
        soapReq.addProperty("json", string);
        soapReq.addProperty("flag",1);
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url,timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        String obj = null;
        Webservice_result result = null;
        try {
            obj = soapEnvelope.getResponse().toString();
            result = JsonUtils.parsingWebservice_result(obj);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return result;
    }

//    /**
//     * Ԥ�ù�������
//     * @param string
//     * @return
//     */
//    public String InsertWOyz(String string){
//        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//        soapEnvelope.implicitTypes = true;
//        soapEnvelope.dotNet = true;
//        SoapObject soapReq = new SoapObject(NAMESPACE, "InsertWO");
//        soapReq.addProperty("in0", string);
//        soapReq.addProperty("in1", 0);
//        soapEnvelope.setOutputSoapObject(soapReq);
//        HttpTransportSE httpTransport = new HttpTransportSE(url,timeOut);
//        try {
//            httpTransport.call("", soapEnvelope);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (XmlPullParserException e) {
//            e.printStackTrace();
//        }
//        String obj = null;
//        try {
//            obj = soapEnvelope.getResponse().toString();
//        } catch (SoapFault soapFault) {
//            soapFault.printStackTrace();
//        }
//        return obj;
//    }

    /**
     * �޸Ĺ�������
     * @param string
     * @return
     */
    public String UpdataWO(String string){
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "UpdateWO");
        soapReq.addProperty("in0", string);
        soapReq.addProperty("in1", 1);
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url,timeOut);
        try {
            httpTransport.call("", soapEnvelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        String obj = null;
        try {
            obj = soapEnvelope.getResponse().toString();
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return obj;
    }

//    /**
//     * �޸�Ԥ�ù�������
//     * @param string
//     * @return
//     */
//    public String UpdataWOyz(String string){
//        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//        soapEnvelope.implicitTypes = true;
//        soapEnvelope.dotNet = true;
//        SoapObject soapReq = new SoapObject(NAMESPACE, "UpdateWO");
//        soapReq.addProperty("in0", string);
//        soapReq.addProperty("in1", 0);
//        soapEnvelope.setOutputSoapObject(soapReq);
//        HttpTransportSE httpTransport = new HttpTransportSE(url,timeOut);
//        try {
//            httpTransport.call("", soapEnvelope);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (XmlPullParserException e) {
//            e.printStackTrace();
//        }
//        String obj = null;
//        try {
//            obj = soapEnvelope.getResponse().toString();
//        } catch (SoapFault soapFault) {
//            soapFault.printStackTrace();
//        }
//        return obj;
//    }
}
