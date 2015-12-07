package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/10/28.
 */
@JsonType
public class WorkOrder extends Entity {
    private static final String TAG = "WorkOrder";
    private static final long serialVersionUID = 2015050105L;

    @JsonField(fieldName = "wonum")
    public String wonum;//工单号
    @JsonField(fieldName = "actfinish")
    public String actfinish;//实际完成时间
    @JsonField(fieldName = "actstart")
    public String actstart;//实际开始时间
    @JsonField(fieldName = "assetdesc")
    public String assetdesc;//设备描述
    @JsonField(fieldName = "assetnum")
    public String assetnum;//设备编号
    @JsonField(fieldName = "description")
    public String description;//描述
    @JsonField(fieldName = "estdur")
    public String estdur; //剩余时间
    @JsonField(fieldName = "jpnum")
    public String jpnum; //作业计划
    @JsonField(fieldName = "jpnumdesc")
    public String jpnumdesc;//作业计划描述
    @JsonField(fieldName = "location")
    public String location; //位置
    @JsonField(fieldName = "locationdesc")
    public String locationdesc;//位置描述
    @JsonField(fieldName = "onbehalfof")
    public String onbehalfof; //录入人工号
    @JsonField(fieldName = "pmdesc")
    public String pmdesc;
    @JsonField(fieldName = "pmnum")
    public String pmnum;
    @JsonField(fieldName = "reportdate")
    public String reportdate; //汇报日期
    @JsonField(fieldName = "status")
    public String status; //状态
    @JsonField(fieldName = "statusdesc")
    public String statusdesc; //状态描述
    @JsonField(fieldName = "udwotype")
    public String udwotype; //工单类型
    @JsonField(fieldName = "udwotypedesc")
    public String udwotypedesc; //工单类型描述
    @JsonField(fieldName = "worktype")
    public String worktype; //工作类型


    public String getWonum() {
        return wonum;
    }

    public void setWonum(String wonum) {
        this.wonum = wonum;
    }

    public String getActfinish() {
        return actfinish;
    }

    public void setActfinish(String actfinish) {
        this.actfinish = actfinish;
    }

    public String getActstart() {
        return actstart;
    }

    public void setActstart(String actstart) {
        this.actstart = actstart;
    }

    public String getAssetdesc() {
        return assetdesc;
    }

    public void setAssetdesc(String assetdesc) {
        this.assetdesc = assetdesc;
    }

    public String getAssetnum() {
        return assetnum;
    }

    public void setAssetnum(String assetnum) {
        this.assetnum = assetnum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEstdur() {
        return estdur;
    }

    public void setEstdur(String estdur) {
        this.estdur = estdur;
    }

    public String getJpnum() {
        return jpnum;
    }

    public void setJpnum(String jpnum) {
        this.jpnum = jpnum;
    }

    public String getJpnumdesc() {
        return jpnumdesc;
    }

    public void setJpnumdesc(String jpnumdesc) {
        this.jpnumdesc = jpnumdesc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationdesc() {
        return locationdesc;
    }

    public void setLocationdesc(String locationdesc) {
        this.locationdesc = locationdesc;
    }

    public String getOnbehalfof() {
        return onbehalfof;
    }

    public void setOnbehalfof(String onbehalfof) {
        this.onbehalfof = onbehalfof;
    }

    public String getPmdesc() {
        return pmdesc;
    }

    public void setPmdesc(String pmdesc) {
        this.pmdesc = pmdesc;
    }

    public String getPmnum() {
        return pmnum;
    }

    public void setPmnum(String pmnum) {
        this.pmnum = pmnum;
    }

    public String getReportdate() {
        return reportdate;
    }

    public void setReportdate(String reportdate) {
        this.reportdate = reportdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusdesc() {
        return statusdesc;
    }

    public void setStatusdesc(String statusdesc) {
        this.statusdesc = statusdesc;
    }

    public String getUdwotype() {
        return udwotype;
    }

    public void setUdwotype(String udwotype) {
        this.udwotype = udwotype;
    }

    public String getUdwotypedesc() {
        return udwotypedesc;
    }

    public void setUdwotypedesc(String udwotypedesc) {
        this.udwotypedesc = udwotypedesc;
    }

    public String getWorktype() {
        return worktype;
    }

    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }
}