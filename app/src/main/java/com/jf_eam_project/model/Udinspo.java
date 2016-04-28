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
    public String branch;//

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
    public String inspplannum;//计划编号

    @DatabaseField(columnName = "lastrundate")
    @JsonField(fieldName = "lastrundate")
    public String lastrundate;//本次生成日期

    @DatabaseField(columnName = "nextrundate")
    @JsonField(fieldName = "nextrundate")
    public String nextrundate;//下次运行时间

    @DatabaseField(columnName = "udinspplandescription")
    @JsonField(fieldName = "udinspplan.description")
    public String udinspplandescription;//计划编号描述

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


    @DatabaseField(columnName = "inspschemenum")
    @JsonField(fieldName = "inspschemenum")
    public String inspschemenum;//检修方案编号


    @DatabaseField(columnName = "startdate")
    @JsonField(fieldName = "startdate")
    public String startdate;//开始时间


    @DatabaseField(columnName = "status")
    @JsonField(fieldName = "status")
    public String status;//状态

    @DatabaseField(columnName = "temperature")
    @JsonField(fieldName = "temperature")
    public String temperature;//温度

    @DatabaseField(columnName = "udbelong")
    @JsonField(fieldName = "udbelong")
    public String udbelong;//风电场

    @DatabaseField(columnName = "weather")
    @JsonField(fieldName = "weather")
    public String weather;//湿度


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

    public String getUdinspplandescription() {
        return udinspplandescription;
    }

    public void setUdinspplandescription(String udinspplandescription) {
        this.udinspplandescription = udinspplandescription;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}
