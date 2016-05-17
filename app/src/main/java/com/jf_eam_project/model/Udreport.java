package com.jf_eam_project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/**
 * Created by think on 2015/12/10
 * 故障提报单
 *
 */
@JsonType
@DatabaseTable(tableName = "Udreport")
public class Udreport extends Entity {

    @DatabaseField(generatedId = true)
    @JsonIgnore
    public int id;

    @JsonField(fieldName = "reportnum")
    @DatabaseField(columnName = "reportnum")
    public String reportnum;// 编号

    @JsonField(fieldName = "description")
    @DatabaseField(columnName = "description")
    public String description;//描述

    @JsonField(fieldName = "assettype")
    @DatabaseField(columnName = "assettype")
    public String assettype;//设备专业

    @JsonField(fieldName = "qxtype")
    @DatabaseField(columnName = "qxtype")
    public String qxtype;//缺陷类型

    @JsonField(fieldName = "qxsjly")
    @DatabaseField(columnName = "qxsjly")
    public String qxsjly;//缺陷数据来源

    @JsonField(fieldName = "culevel")
    @DatabaseField(columnName = "culevel")
    public String culevel;//缺陷等级

    @JsonField(fieldName = "udworktype")
    @DatabaseField(columnName = "udworktype")
    public String udworktype;//工单类型

    @JsonField(fieldName = "branch_description")
    @DatabaseField(columnName = "branch_description")
    public String branch_description;//分公司

    @JsonField(fieldName = "udbelong")
    @DatabaseField(columnName = "udbelong")
    public String udbelong;//运行单位

    @DatabaseField(columnName = "udbelong_description")
    @JsonField(fieldName = "udbelong_description")
    public String udbelong_description;//运行单位中文名称



    @DatabaseField(columnName = "statustype")
    @JsonField(fieldName = "statustype")
    public String statustype;//状态

    @DatabaseField(columnName = "createby_displayname")
    @JsonField(fieldName = "createby_displayname")
    public String createby_displayname;//提报人



    @DatabaseField(columnName = "createdate")
    @JsonField(fieldName = "createdate")
    public String createdate;//提报时间


    @DatabaseField(columnName = "xcdate")
    @JsonField(fieldName = "xcdate")
    public String xcdate;//消除时间

    @DatabaseField(columnName = "assetnum")
    @JsonField(fieldName = "assetnum")
    public String assetnum;//设备

    @DatabaseField(columnName = "assetnum_description")
    @JsonField(fieldName = "assetnum_description")
    public String assetnum_description;//设备名称

    @DatabaseField(columnName = "location")
    @JsonField(fieldName = "location")
    public String location;//位置


    @DatabaseField(columnName = "location_description")
    @JsonField(fieldName = "location_description")
    public String location_description;//位置名称
    @DatabaseField(columnName = "failurecode")
    @JsonField(fieldName = "failurecode")
    public String failurecode;//故障类


    @DatabaseField(columnName = "problemcode")
    @JsonField(fieldName = "problemcode")
    public String problemcode;//问题代码


    @DatabaseField(columnName = "stoptime")
    @JsonField(fieldName = "stoptime")
    public String stoptime;//设备停机时间

    @DatabaseField(columnName = "starttime")
    @JsonField(fieldName = "starttime")
    public String starttime;//设备恢复时间

    @DatabaseField(columnName = "bugtime")
    @JsonField(fieldName = "bugtime")
    public String bugtime;//故障耗时(小时)

    @DatabaseField(columnName = "sbpjnum")
    @JsonField(fieldName = "sbpjnum")
    public String sbpjnum;//设备评级编号

    @DatabaseField(columnName = "sbpjnum_description")
    @JsonField(fieldName = "sbpjnum_description")
    public String sbpjnum_description;//设备评级编号

    @DatabaseField(columnName = "wonum")
    @JsonField(fieldName = "wonum")
    public String wonum;//关联工单号

    @DatabaseField(columnName = "cudescribe")
    @JsonField(fieldName = "cudescribe")
    public String cudescribe;//关联工单号

    @DatabaseField(columnName = "remark")
    @JsonField(fieldName = "remark")
    public String remark;//备注

    @DatabaseField(columnName = "apptype")
    @JsonField(fieldName = "apptype")
    public String apptype;//类型


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReportnum() {
        return reportnum;
    }

    public void setReportnum(String reportnum) {
        this.reportnum = reportnum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssettype() {
        return assettype;
    }

    public void setAssettype(String assettype) {
        this.assettype = assettype;
    }

    public String getQxtype() {
        return qxtype;
    }

    public void setQxtype(String qxtype) {
        this.qxtype = qxtype;
    }

    public String getQxsjly() {
        return qxsjly;
    }

    public void setQxsjly(String qxsjly) {
        this.qxsjly = qxsjly;
    }

    public String getCulevel() {
        return culevel;
    }

    public void setCulevel(String culevel) {
        this.culevel = culevel;
    }

    public String getUdworktype() {
        return udworktype;
    }

    public void setUdworktype(String udworktype) {
        this.udworktype = udworktype;
    }

    public String getBranch_description() {
        return branch_description;
    }

    public void setBranch_description(String branch_description) {
        this.branch_description = branch_description;
    }

    public String getUdbelong() {
        return udbelong;
    }

    public void setUdbelong(String udbelong) {
        this.udbelong = udbelong;
    }

    public String getUdbelong_description() {
        return udbelong_description;
    }

    public void setUdbelong_description(String udbelong_description) {
        this.udbelong_description = udbelong_description;
    }

    public String getStatustype() {
        return statustype;
    }

    public void setStatustype(String statustype) {
        this.statustype = statustype;
    }

    public String getCreateby_displayname() {
        return createby_displayname;
    }

    public void setCreateby_displayname(String createby_displayname) {
        this.createby_displayname = createby_displayname;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getXcdate() {
        return xcdate;
    }

    public void setXcdate(String xcdate) {
        this.xcdate = xcdate;
    }

    public String getAssetnum() {
        return assetnum;
    }

    public void setAssetnum(String assetnum) {
        this.assetnum = assetnum;
    }

    public String getAssetnum_description() {
        return assetnum_description;
    }

    public void setAssetnum_description(String assetnum_description) {
        this.assetnum_description = assetnum_description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation_description() {
        return location_description;
    }

    public void setLocation_description(String location_description) {
        this.location_description = location_description;
    }

    public String getFailurecode() {
        return failurecode;
    }

    public void setFailurecode(String failurecode) {
        this.failurecode = failurecode;
    }

    public String getProblemcode() {
        return problemcode;
    }

    public void setProblemcode(String problemcode) {
        this.problemcode = problemcode;
    }

    public String getStoptime() {
        return stoptime;
    }

    public void setStoptime(String stoptime) {
        this.stoptime = stoptime;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getBugtime() {
        return bugtime;
    }

    public void setBugtime(String bugtime) {
        this.bugtime = bugtime;
    }

    public String getSbpjnum() {
        return sbpjnum;
    }

    public void setSbpjnum(String sbpjnum) {
        this.sbpjnum = sbpjnum;
    }

    public String getSbpjnum_description() {
        return sbpjnum_description;
    }

    public void setSbpjnum_description(String sbpjnum_description) {
        this.sbpjnum_description = sbpjnum_description;
    }

    public String getWonum() {
        return wonum;
    }

    public void setWonum(String wonum) {
        this.wonum = wonum;
    }

    public String getCudescribe() {
        return cudescribe;
    }

    public void setCudescribe(String cudescribe) {
        this.cudescribe = cudescribe;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApptype() {
        return apptype;
    }

    public void setApptype(String apptype) {
        this.apptype = apptype;
    }
}
