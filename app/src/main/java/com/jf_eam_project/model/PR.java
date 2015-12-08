package com.jf_eam_project.model;


import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;


/**
 * Created by think on 2015/11/26.
 * 采购申请表
 */
@JsonType
public class PR extends Entity {

    private static final String TAG = "PR";

    @JsonField(fieldName = "description")
    public String description;//描述
    @JsonField(fieldName = "issuedate")
    public String issuedate;//申请日期
    @JsonField(fieldName = "prnum")
    public String prnum;//采购计划
    @JsonField(fieldName = "requestedby")
    public String requestedby;//申请人
    @JsonField(fieldName = "requireddate")
    public String requireddate;//要求的日期
    @JsonField(fieldName = "siteid")
    public String siteid;//地点
    @JsonField(fieldName = "status")
    public String status;//状态
    @JsonField(fieldName = "statusdate")
    public String statusdate;//状态日期
    @JsonField(fieldName = "udprtype")
    public String udprtype;//采购类型
    @JsonField(fieldName = "pretaxtotal")
    public String pretaxtotal;//税前总计
    @JsonField(fieldName = "totaltax1")
    public String totaltax1;//税款总计
    @JsonField(fieldName = "totalcost")
    public String totalcost;//成本总计
    @JsonField(fieldName = "currencycode")
    public String currencycode;//货币


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(String issuedate) {
        this.issuedate = issuedate;
    }

    public String getPrnum() {
        return prnum;
    }

    public void setPrnum(String prnum) {
        this.prnum = prnum;
    }

    public String getRequestedby() {
        return requestedby;
    }

    public void setRequestedby(String requestedby) {
        this.requestedby = requestedby;
    }

    public String getRequireddate() {
        return requireddate;
    }

    public void setRequireddate(String requireddate) {
        this.requireddate = requireddate;
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

    public String getUdprtype() {
        return udprtype;
    }

    public void setUdprtype(String udprtype) {
        this.udprtype = udprtype;
    }

    public String getPretaxtotal() {
        return pretaxtotal;
    }

    public void setPretaxtotal(String pretaxtotal) {
        this.pretaxtotal = pretaxtotal;
    }

    public String getTotaltax1() {
        return totaltax1;
    }

    public void setTotaltax1(String totaltax1) {
        this.totaltax1 = totaltax1;
    }

    public String getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(String totalcost) {
        this.totalcost = totalcost;
    }

    public String getCurrencycode() {
        return currencycode;
    }

    public void setCurrencycode(String currencycode) {
        this.currencycode = currencycode;
    }
}
