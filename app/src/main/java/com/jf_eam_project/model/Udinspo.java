package com.jf_eam_project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/**
 * Created by think on 2015/12/10
 * 巡检单
 *
 */
@JsonType
@DatabaseTable(tableName = "Udinspo")
public class Udinspo extends Entity {

    @DatabaseField(generatedId = true)
    @JsonIgnore
    public int id;

    @JsonField(fieldName = "branch")
    @DatabaseField(columnName = "branch")
    public String branch;//分公司

    @JsonField(fieldName = "createby")
    @DatabaseField(columnName = "createby")
    public String createby;//创建人

    @JsonField(fieldName = "createdate")
    @DatabaseField(columnName = "createdate")
    public String createdate;//创建时间

    @JsonField(fieldName = "description")
    @DatabaseField(columnName = "description")
    public String description;//描述

    @JsonField(fieldName = "inspoby")
    @DatabaseField(columnName = "inspoby")
    public String inspoby;//巡检人

    @JsonField(fieldName = "inspobydisplayname")
    @DatabaseField(columnName = "inspobydisplayname")
    public String inspobydisplayname;//巡检人名称

    @JsonField(fieldName = "inspodate")
    @DatabaseField(columnName = "inspodate")
    public String inspodate;//巡检日期

    @JsonField(fieldName = "insponum")
    @DatabaseField(columnName = "insponum")
    public String insponum;//巡检单编号

    @JsonField(fieldName = "inspotype")
    @DatabaseField(columnName = "inspotype")
    public String inspotype;//巡检单类型

    @DatabaseField(columnName = "inspplannum")
    @JsonField(fieldName = "inspplannum")
    public String inspplannum;//检修计划单号



    @DatabaseField(columnName = "lastrundate")
    @JsonField(fieldName = "lastrundate")
    public String lastrundate;//本次生成日期

    @DatabaseField(columnName = "nextrundate")
    @JsonField(fieldName = "nextrundate")
    public String nextrundate;//下次运行时间



    @DatabaseField(columnName = "enddate")
    @JsonField(fieldName = "enddate")
    public String enddate;//计划结束时间


    @DatabaseField(columnName = "assettype")
    @JsonField(fieldName = "assettype")
    public String assettype;//类型

    @DatabaseField(columnName = "checktype")
    @JsonField(fieldName = "checktype")
    public String checktype;//类型

    @DatabaseField(columnName = "inspmainplannum")
    @JsonField(fieldName = "inspmainplannum")
    public String inspmainplannum;//计划编号

    @DatabaseField(columnName = "udinspmainplandesc")
    @JsonField(fieldName = "udinspmainplandesc")
    public String udinspmainplandesc;//计划编号描述


    @DatabaseField(columnName = "inspschemenum")
    @JsonField(fieldName = "inspschemenum")
    public String inspschemenum;//检修方案编号

    @DatabaseField(columnName = "inspschemenumdesc")
    @JsonField(fieldName = "inspschemenumdesc")
    public String inspschemenumdesc;//检修方案描述


    @DatabaseField(columnName = "startdate")
    @JsonField(fieldName = "startdate")
    public String startdate;//开始时间


    @DatabaseField(columnName = "status")
    @JsonField(fieldName = "status")
    public String status;//状态

    @DatabaseField(columnName = "statusdesc")
    @JsonField(fieldName = "statusdesc")
    public String statusdesc;//状态描述

    @DatabaseField(columnName = "temperature")
    @JsonField(fieldName = "temperature")
    public String temperature;//温度

    @DatabaseField(columnName = "udbelong")
    @JsonField(fieldName = "udbelong")
    public String udbelong;//运行单位

    @DatabaseField(columnName = "udbelongdesc")
    @JsonField(fieldName = "udbelongdesc")
    public String udbelongdesc;//运行单位描述

    @DatabaseField(columnName = "weather")
    @JsonField(fieldName = "weather")
    public String weather;//天气

    @DatabaseField(columnName = "operation")
    @JsonField(fieldName = "operation")
    public String operation;//执行状态



    public String getAssettype() {
        return assettype;
    }

    public void setAssettype(String assettype) {
        this.assettype = assettype;
    }

    public String getChecktype() {
        return checktype;
    }

    public void setChecktype(String checktype) {
        this.checktype = checktype;
    }

    public String getInspmainplannum() {
        return inspmainplannum;
    }

    public void setInspmainplannum(String inspmainplannum) {
        this.inspmainplannum = inspmainplannum;
    }

    public String getInspschemenum() {
        return inspschemenum;
    }

    public void setInspschemenum(String inspschemenum) {
        this.inspschemenum = inspschemenum;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getUdbelong() {
        return udbelong;
    }

    public void setUdbelong(String udbelong) {
        this.udbelong = udbelong;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInspoby() {
        return inspoby;
    }

    public void setInspoby(String inspoby) {
        this.inspoby = inspoby;
    }

    public String getInspodate() {
        return inspodate;
    }

    public void setInspodate(String inspodate) {
        this.inspodate = inspodate;
    }

    public String getInsponum() {
        return insponum;
    }

    public void setInsponum(String insponum) {
        this.insponum = insponum;
    }

    public String getInspotype() {
        return inspotype;
    }

    public void setInspotype(String inspotype) {
        this.inspotype = inspotype;
    }

    public String getInspplannum() {
        return inspplannum;
    }

    public void setInspplannum(String inspplannum) {
        this.inspplannum = inspplannum;
    }

    public String getLastrundate() {
        return lastrundate;
    }

    public void setLastrundate(String lastrundate) {
        this.lastrundate = lastrundate;
    }

    public String getNextrundate() {
        return nextrundate;
    }

    public void setNextrundate(String nextrundate) {
        this.nextrundate = nextrundate;
    }

    public String getUdinspmainplandesc() {
        return udinspmainplandesc;
    }

    public void setUdinspmainplandesc(String udinspmainplandesc) {
        this.udinspmainplandesc = udinspmainplandesc;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }


    public String getInspobydisplayname() {
        return inspobydisplayname;
    }

    public void setInspobydisplayname(String inspobydisplayname) {
        this.inspobydisplayname = inspobydisplayname;
    }

    public String getInspschemenumdesc() {
        return inspschemenumdesc;
    }

    public void setInspschemenumdesc(String inspschemenumdesc) {
        this.inspschemenumdesc = inspschemenumdesc;
    }

    public String getStatusdesc() {
        return statusdesc;
    }

    public void setStatusdesc(String statusdesc) {
        this.statusdesc = statusdesc;
    }

    public String getUdbelongdesc() {
        return udbelongdesc;
    }

    public void setUdbelongdesc(String udbelongdesc) {
        this.udbelongdesc = udbelongdesc;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
