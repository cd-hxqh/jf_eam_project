package com.jf_eam_project.model;


import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;


/**
 * Created by think on 2015/12/10
 * 发票表
 *
 */
@JsonType
public class Udinspo extends Entity {


    @JsonField(fieldName = "branch")
    public String branch;//
    @JsonField(fieldName = "createby")
    public String createby;//创建人
    @JsonField(fieldName = "createdate")
    public String createdate;//创建时间
    @JsonField(fieldName = "description")
    public String description;//描述
    @JsonField(fieldName = "inspoby")
    public String inspoby;//巡检人
    @JsonField(fieldName = "inspodate")
    public String inspodate;//巡检日期
    @JsonField(fieldName = "insponum")
    public String insponum;//巡检单编号
    @JsonField(fieldName = "inspotype")
    public String inspotype;//巡检单类型
    @JsonField(fieldName = "inspplannum")
    public String inspplannum;//计划编号
    @JsonField(fieldName = "lastrundate")
    public String lastrundate;//本次生成日期
    @JsonField(fieldName = "nextrundate")
    public String nextrundate;//下次运行时间
    @JsonField(fieldName = "udinspplan.description")
    public String udinspplandescription;//计划编号描述

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
}
