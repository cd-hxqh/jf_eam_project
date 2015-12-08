package com.jf_eam_project.model;


import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;


/**
 * Created by think on 2015/11/26.
 * 采购单表
 */
@JsonType
public class PR extends Entity {

    private static final String TAG = "PO";
    private static final long serialVersionUID = 2015050105L;

    @JsonField(fieldName = "buyercompany")
    public String buyercompany;//买方公司
    @JsonField(fieldName = "contact")
    public String contact;//联系人
    @JsonField(fieldName = "currencycode")
    public String currencycode;//货币
    @JsonField(fieldName = "customernum")
    public String customernum;//客户编号
    @JsonField(fieldName = "description")
    public String description;//描述
    @JsonField(fieldName = "fob")
    public String fob;//离岸点
    @JsonField(fieldName = "freightterms")
    public String freightterms;//运输条款
    @JsonField(fieldName = "inclusive1")
    public String inclusive1;//向供应商付税
    @JsonField(fieldName = "inspectionrequired")
    public String inspectionrequired;//需要检查
    @JsonField(fieldName = "internal")
    public String internal;//指定供应商是否在您公司范围内
    @JsonField(fieldName = "orderdate")
    public String orderdate;//订购日期
    @JsonField(fieldName = "paymentterms")
    public String paymentterms;//支付条款
    @JsonField(fieldName = "ponum")
    public String ponum;// 采购单编号
    @JsonField(fieldName = "potype")
    public String potype;// 类型
    @JsonField(fieldName = "pretaxtotal")
    public String pretaxtotal;//税前总计
    @JsonField(fieldName = "priority")
    public String priority;// 优先级
    @JsonField(fieldName = "purchaseagent")
    public String purchaseagent;//买方
    @JsonField(fieldName = "requireddate")
    public String requireddate;//要求日期
    @JsonField(fieldName = "shipvia")
    public String shipvia;//装运方式
    @JsonField(fieldName = "sitedesc")
    public String sitedesc;//地点描述
    @JsonField(fieldName = "siteid")
    public String siteid;// 地点
    @JsonField(fieldName = "status")
    public String status;// 状态
    @JsonField(fieldName = "statusdate")
    public String statusdate;//状态日期
    @JsonField(fieldName = "storeloc")
    public String storeloc;//库房
    @JsonField(fieldName = "storelocdesc")
    public String storelocdesc;//库房描述
    @JsonField(fieldName = "storelocsiteid")
    public String storelocsiteid;//库房地点
    @JsonField(fieldName = "totalbasecost")
    public String totalbasecost;//成本总计（以基本货币为单位）
    @JsonField(fieldName = "totalcost")
    public String totalcost;//成本总计
    @JsonField(fieldName = "totaltax1")
    public String totaltax1;//税款总计
    @JsonField(fieldName = "vendeliverydate")
    public String vendeliverydate;//供应商日期
    @JsonField(fieldName = "vendor")
    public String vendor;//公司
    @JsonField(fieldName = "vendordesc")
    public String vendordesc;//公司描述


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
