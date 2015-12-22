package com.jf_eam_project.model;


import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;


/**
 * Created by think on 2015/12/10
 * 发票表
 *
 */
@JsonType
public class Invoice extends Entity {


    @JsonField(fieldName = "invoicenum")
    public String invoicenum;//发票
    @JsonField(fieldName = "description")
    public String description;//描述
    @JsonField(fieldName = "siteid")
    public String siteid;//地点
    @JsonField(fieldName = "documenttype")
    public String documenttype;//类型
    @JsonField(fieldName = "status")
    public String status;//状态
    @JsonField(fieldName = "enterby")
    public String enterby;//输入人
    @JsonField(fieldName = "enterdate")
    public String enterdate;//输入日期
    @JsonField(fieldName = "invoicedate")
    public String invoicedate;//发票日期
    @JsonField(fieldName = "glpostdate")
    public String glpostdate;//发布日期
    @JsonField(fieldName = "vendor")
    public String vendor;//公司
    @JsonField(fieldName = "pretaxtotalforui")
    public String pretaxtotalforui;//税前总计
    @JsonField(fieldName = "totaltax1forui")
    public String totaltax1forui;//税款总计
    @JsonField(fieldName = "totalcostforui")
    public String totalcostforui;//发票总计
    @JsonField(fieldName = "currencycode")
    public String currencycode;//货币



    public String getInvoicenum() {
        return invoicenum;
    }

    public void setInvoicenum(String invoicenum) {
        this.invoicenum = invoicenum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSiteid() {
        return siteid;
    }

    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    public String getDocumenttype() {
        return documenttype;
    }

    public void setDocumenttype(String documenttype) {
        this.documenttype = documenttype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEnterby() {
        return enterby;
    }

    public void setEnterby(String enterby) {
        this.enterby = enterby;
    }

    public String getEnterdate() {
        return enterdate;
    }

    public void setEnterdate(String enterdate) {
        this.enterdate = enterdate;
    }

    public String getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(String invoicedate) {
        this.invoicedate = invoicedate;
    }

    public String getGlpostdate() {
        return glpostdate;
    }

    public void setGlpostdate(String glpostdate) {
        this.glpostdate = glpostdate;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }


    public String getPretaxtotalforui() {
        return pretaxtotalforui;
    }

    public void setPretaxtotalforui(String pretaxtotalforui) {
        this.pretaxtotalforui = pretaxtotalforui;
    }

    public String getTotaltax1forui() {
        return totaltax1forui;
    }

    public void setTotaltax1forui(String totaltax1forui) {
        this.totaltax1forui = totaltax1forui;
    }

    public String getTotalcostforui() {
        return totalcostforui;
    }

    public void setTotalcostforui(String totalcostforui) {
        this.totalcostforui = totalcostforui;
    }

    public String getCurrencycode() {
        return currencycode;
    }

    public void setCurrencycode(String currencycode) {
        this.currencycode = currencycode;
    }
}
