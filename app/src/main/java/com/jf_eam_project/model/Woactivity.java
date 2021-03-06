package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/12/3.
 * 工单任务
 */
@JsonType
@DatabaseTable(tableName = "Woactivity")
public class Woactivity extends Entity{
    private static final String TAG = "Woactivity";
    private static final long serialVersionUID = 2015050105L;

    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "taskid")
    @DatabaseField(columnName = "taskid")
    public String taskid;//任务
    @JsonField(fieldName = "description")
    @DatabaseField(columnName = "description")
    public String description;//摘要
    @JsonField(fieldName = "assetnum")
    @DatabaseField(columnName = "assetnum")
    public String assetnum;//资产
    @JsonField(fieldName = "assetdesc")
    @DatabaseField(columnName = "assetdesc")
    public String assetdesc;//资产描述
    @JsonField(fieldName = "location")
    @DatabaseField(columnName = "location")
    public String location;//位置
    @JsonField(fieldName = "locationdesc")
    @DatabaseField(columnName = "locationdesc")
    public String locationdesc;//位置描述
    @JsonField(fieldName = "woclass")
    @DatabaseField(columnName = "woclass")
    public String woclass;//记录类
    @JsonField(fieldName = "wosequence")
    @DatabaseField(columnName = "wosequence")
    public String wosequence;//序号
    @JsonField(fieldName = "wonum")
    @DatabaseField(columnName = "wonum")
    public String wonum;//工单号
    @JsonField(fieldName = "estdur")
    @DatabaseField(columnName = "estdur")
    public String estdur;//估计持续时间
    @JsonField(fieldName = "status")
    @DatabaseField(columnName = "status")
    public String status;//状态
    @JsonField(fieldName = "owner")
    @DatabaseField(columnName = "owner")
    public String owner;//所有者
    @JsonField(fieldName = "ownergroup")
    @DatabaseField(columnName = "ownergroup")
    public String ownergroup;//所有者组
    @JsonField(fieldName = "targstartdate")
    @DatabaseField(columnName = "targstartdate")
    public String targstartdate;//目标开始时间
    @JsonField(fieldName = "targcompdate")
    @DatabaseField(columnName = "targcompdate")
    public String targcompdate;//目标完成时间
    @JsonField(fieldName = "actstart")
    @DatabaseField(columnName = "actstart")
    public String actstart;//实际开始时间
    @JsonField(fieldName = "actfinish")
    @DatabaseField(columnName = "actfinish")
    public String actfinish;//实际完成时间
    @DatabaseField(columnName = "type")
    public String type;//add/update
    @DatabaseField(columnName = "belongid")
    public int belongid;//所属工单本地存储id

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWoclass() {
        return woclass;
    }

    public void setWoclass(String woclass) {
        this.woclass = woclass;
    }

    public String getLocationdesc() {
        return locationdesc;
    }

    public void setLocationdesc(String locationdesc) {
        this.locationdesc = locationdesc;
    }

    public String getWosequence() {
        return wosequence;
    }

    public void setWosequence(String wosequence) {
        this.wosequence = wosequence;
    }

    public String getEstdur() {
        return estdur;
    }

    public void setEstdur(String estdur) {
        this.estdur = estdur;
    }

    public String getWonum() {
        return wonum;
    }

    public void setWonum(String wonum) {
        this.wonum = wonum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnergroup() {
        return ownergroup;
    }

    public void setOwnergroup(String ownergroup) {
        this.ownergroup = ownergroup;
    }

    public String getTargstartdate() {
        return targstartdate;
    }

    public void setTargstartdate(String targstartdate) {
        this.targstartdate = targstartdate;
    }

    public String getTargcompdate() {
        return targcompdate;
    }

    public void setTargcompdate(String targcompdate) {
        this.targcompdate = targcompdate;
    }

    public String getActstart() {
        return actstart;
    }

    public void setActstart(String actstart) {
        this.actstart = actstart;
    }

    public String getActfinish() {
        return actfinish;
    }

    public void setActfinish(String actfinish) {
        this.actfinish = actfinish;
    }
}
