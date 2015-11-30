package com.jf_eam_project.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by think on 2015/11/26.
 * 采购单表
 */
public class Po extends Entity {

    private static final String TAG = "PO";
    private static final long serialVersionUID = 2015050105L;

    private String buyercompany;//买方公司
    private String contact;//联系人
    private String currencycode;//货币
    private String customernum;//客户编号
    private String description;//描述
    private String fob;//离岸点
    private String freightterms;//运输条款
    private String inclusive1;//向供应商付税
    private String inspectionrequired;//需要检查
    private String internal;//指定供应商是否在您公司范围内
    private String orderdate;//订购日期
    private String paymentterms;//支付条款
    private String ponum;// 采购单编号
    private String potype;// 类型
    private String pretaxtotal;//税前总计
    private String priority;// 优先级
    private String purchaseagent;//买方
    private String requireddate;//要求日期
    private String shipvia;//装运方式
    private String sitedesc;//地点描述
    private String siteid;// 地点
    private String status;// 状态
    private String statusdate;//状态日期
    private String storeloc;//库房
    private String storelocdesc;//库房描述
    private String storelocsiteid;//库房地点
    private String totalbasecost;//成本总计（以基本货币为单位）
    private String totalcost;//成本总计
    private String totaltax1;//税款总计
    private String vendeliverydate;//供应商日期
    private String vendor;//公司
    private String vendordesc;//公司描述


    public String getBuyercompany() {
        return buyercompany;
    }

    public void setBuyercompany(String buyercompany) {
        this.buyercompany = buyercompany;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCurrencycode() {
        return currencycode;
    }

    public void setCurrencycode(String currencycode) {
        this.currencycode = currencycode;
    }

    public String getCustomernum() {
        return customernum;
    }

    public void setCustomernum(String customernum) {
        this.customernum = customernum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFob() {
        return fob;
    }

    public void setFob(String fob) {
        this.fob = fob;
    }

    public String getFreightterms() {
        return freightterms;
    }

    public void setFreightterms(String freightterms) {
        this.freightterms = freightterms;
    }

    public String getInclusive1() {
        return inclusive1;
    }

    public void setInclusive1(String inclusive1) {
        this.inclusive1 = inclusive1;
    }

    public String getInspectionrequired() {
        return inspectionrequired;
    }

    public void setInspectionrequired(String inspectionrequired) {
        this.inspectionrequired = inspectionrequired;
    }

    public String getInternal() {
        return internal;
    }

    public void setInternal(String internal) {
        this.internal = internal;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getPaymentterms() {
        return paymentterms;
    }

    public void setPaymentterms(String paymentterms) {
        this.paymentterms = paymentterms;
    }

    public String getPonum() {
        return ponum;
    }

    public void setPonum(String ponum) {
        this.ponum = ponum;
    }

    public String getPotype() {
        return potype;
    }

    public void setPotype(String potype) {
        this.potype = potype;
    }

    public String getPretaxtotal() {
        return pretaxtotal;
    }

    public void setPretaxtotal(String pretaxtotal) {
        this.pretaxtotal = pretaxtotal;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPurchaseagent() {
        return purchaseagent;
    }

    public void setPurchaseagent(String purchaseagent) {
        this.purchaseagent = purchaseagent;
    }

    public String getRequireddate() {
        return requireddate;
    }

    public void setRequireddate(String requireddate) {
        this.requireddate = requireddate;
    }

    public String getShipvia() {
        return shipvia;
    }

    public void setShipvia(String shipvia) {
        this.shipvia = shipvia;
    }

    public String getSitedesc() {
        return sitedesc;
    }

    public void setSitedesc(String sitedesc) {
        this.sitedesc = sitedesc;
    }

    public String getSiteid() {
        return siteid;
    }

    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusdate() {
        return statusdate;
    }

    public void setStatusdate(String statusdate) {
        this.statusdate = statusdate;
    }

    public String getStoreloc() {
        return storeloc;
    }

    public void setStoreloc(String storeloc) {
        this.storeloc = storeloc;
    }

    public String getStorelocdesc() {
        return storelocdesc;
    }

    public void setStorelocdesc(String storelocdesc) {
        this.storelocdesc = storelocdesc;
    }

    public String getStorelocsiteid() {
        return storelocsiteid;
    }

    public void setStorelocsiteid(String storelocsiteid) {
        this.storelocsiteid = storelocsiteid;
    }

    public String getTotalbasecost() {
        return totalbasecost;
    }

    public void setTotalbasecost(String totalbasecost) {
        this.totalbasecost = totalbasecost;
    }

    public String getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(String totalcost) {
        this.totalcost = totalcost;
    }

    public String getTotaltax1() {
        return totaltax1;
    }

    public void setTotaltax1(String totaltax1) {
        this.totaltax1 = totaltax1;
    }

    public String getVendeliverydate() {
        return vendeliverydate;
    }

    public void setVendeliverydate(String vendeliverydate) {
        this.vendeliverydate = vendeliverydate;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getVendordesc() {
        return vendordesc;
    }

    public void setVendordesc(String vendordesc) {
        this.vendordesc = vendordesc;
    }
}
