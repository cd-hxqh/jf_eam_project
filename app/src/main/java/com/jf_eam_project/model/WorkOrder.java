package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/10/28.
 */
@JsonType
@DatabaseTable(tableName = "WorkOrder")
public class WorkOrder extends Entity {
    private static final String TAG = "WorkOrder";
    private static final long serialVersionUID = 2015050105L;
    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "wonum")
    @DatabaseField(columnName = "wonum")
    public String wonum;//工单号
    @JsonField(fieldName = "workorderid")
    @DatabaseField(columnName = "workorderid")
    public String workorderid;//工单号
    @JsonField(fieldName = "actfinish")
    @DatabaseField(columnName = "actfinish")
    public String actfinish;//实际完成时间
    @JsonField(fieldName = "actstart")
    @DatabaseField(columnName = "actstart")
    public String actstart;//实际开始时间
    @JsonField(fieldName = "assetdesc")
    @DatabaseField(columnName = "assetdesc")
    public String assetdesc;//资产描述
    @JsonField(fieldName = "assetnum")
    @DatabaseField(columnName = "assetnum")
    public String assetnum;//资产
    @JsonField(fieldName = "description")
    @DatabaseField(columnName = "description")
    public String description;//描述
    @JsonField(fieldName = "estdur")
    @DatabaseField(columnName = "estdur")
    public String estdur; //剩余时间
    @JsonField(fieldName = "jpnum")
    @DatabaseField(columnName = "jpnum")
    public String jpnum; //作业计划
    @JsonField(fieldName = "jpnumdesc")
    @DatabaseField(columnName = "jpnumdesc")
    public String jpnumdesc;//作业计划描述
    @JsonField(fieldName = "location")
    @DatabaseField(columnName = "location")
    public String location; //位置
    @JsonField(fieldName = "locationdesc")
    @DatabaseField(columnName = "locationdesc")
    public String locationdesc;//位置描述
    @JsonField(fieldName = "onbehalfof")
    @DatabaseField(columnName = "onbehalfof")
    public String onbehalfof; //代表
    @JsonField(fieldName = "pmdesc")
    @DatabaseField(columnName = "pmdesc")
    public String pmdesc;//PM描述
    @JsonField(fieldName = "pmnum")
    @DatabaseField(columnName = "pmnum")
    public String pmnum;//PM
    @JsonField(fieldName = "reportdate")
    @DatabaseField(columnName = "reportdate")
    public String reportdate; //汇报日期
    @JsonField(fieldName = "status")
    @DatabaseField(columnName = "status")
    public String status; //状态
    @JsonField(fieldName = "statusdesc")
    @DatabaseField(columnName = "statusdesc")
    public String statusdesc; //状态描述
    @JsonField(fieldName = "udwotype")
    @DatabaseField(columnName = "udwotype")
    public String udwotype; //工单类型
    @JsonField(fieldName = "udwotypedesc")
    @DatabaseField(columnName = "udwotypedesc")
    public String udwotypedesc; //工单类型描述
    @JsonField(fieldName = "worktype")
    @DatabaseField(columnName = "worktype")
    public String worktype; //工作类型
    @JsonField(fieldName = "parent")
    @DatabaseField(columnName = "parent")
    public String parent; //父工单
    @JsonField(fieldName = "udprojectnum")
    @DatabaseField(columnName = "udprojectnum")
    public String udprojectnum; //项目编号
    @JsonField(fieldName = "uudprojectnumdesc")
    @DatabaseField(columnName = "uudprojectnumdesc")
    public String udprojectnumdesc; //项目编号描述
    @JsonField(fieldName = "statusdate")
    @DatabaseField(columnName = "statusdate")
    public String statusdate; //状态日期
    @JsonField(fieldName = "lctype")
    @DatabaseField(columnName = "lctype")
    public String lctype; //风机/电气
    @JsonField(fieldName = "woclass")
    @DatabaseField(columnName = "woclass")
    public String woclass; //类
    @JsonField(fieldName = "failurecode")
    @DatabaseField(columnName = "failurecode")
    public String failurecode; //故障类
    @JsonField(fieldName = "problemcode")
    @DatabaseField(columnName = "problemcode")
    public String problemcode; //问题代码
    @JsonField(fieldName = "displayname")
    @DatabaseField(columnName = "displayname")
    public String displayname; //创建人CREATEBY.DISPLAYNAME
    @JsonField(fieldName = "createdate")
    @DatabaseField(columnName = "createdate")
    public String createdate; //创建日期
    @JsonField(fieldName = "targstartdate")
    @DatabaseField(columnName = "targstartdate")
    public String targstartdate; //目标开始时间
    @JsonField(fieldName = "targcompdate")
    @DatabaseField(columnName = "targcompdate")
    public String targcompdate; //目标结束时间
    @JsonField(fieldName = "reportedby")
    @DatabaseField(columnName = "reportedby")
    public String reportedby; //报告人
    @DatabaseField(columnName = "isnew")
    public boolean isnew;//是否是新增工单
    @DatabaseField(columnName = "ishistory")
    public boolean ishistory;//是否是本地历史工单


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
