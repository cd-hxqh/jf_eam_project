package com.jf_eam_project.model;


import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;


/**
 * Created by think on 2015/12/10
 * 流程审批
 *
 */
@JsonType
public class Wfassignment extends Entity {


    @JsonField(fieldName = "app")
    public String app;//应用程序的名称
    @JsonField(fieldName = "assigncode")
    public String assigncode;//任务分配人
    @JsonField(fieldName = "description")
    public String description;//描述
    @JsonField(fieldName = "duedate")
    public String duedate;//到期日期
    @JsonField(fieldName = "origperson")
    public String origperson;//进行委派之前的任务分配的原始人员
    @JsonField(fieldName = "ownerid")
    public String ownerid;//受控制记录的唯一标识
    @JsonField(fieldName = "ownertable")
    public String ownertable;//表名
    @JsonField(fieldName = "processname")
    public String processname;//过程名称
    @JsonField(fieldName = "roleid")
    public String roleid;//任务角色
    @JsonField(fieldName = "startdate")
    public String startdate;//当前日期
    @JsonField(fieldName = "wfassignmentid ")
    public String wfassignmentid ;//编号


    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getAssigncode() {
        return assigncode;
    }

    public void setAssigncode(String assigncode) {
        this.assigncode = assigncode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getOrigperson() {
        return origperson;
    }

    public void setOrigperson(String origperson) {
        this.origperson = origperson;
    }

    public String getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }

    public String getOwnertable() {
        return ownertable;
    }

    public void setOwnertable(String ownertable) {
        this.ownertable = ownertable;
    }

    public String getProcessname() {
        return processname;
    }

    public void setProcessname(String processname) {
        this.processname = processname;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getWfassignmentid() {
        return wfassignmentid;
    }

    public void setWfassignmentid(String wfassignmentid) {
        this.wfassignmentid = wfassignmentid;
    }
}
