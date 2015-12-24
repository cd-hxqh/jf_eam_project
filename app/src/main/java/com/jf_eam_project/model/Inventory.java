package com.jf_eam_project.model;


import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;


/**
 * Created by think on 2015/12/10
 * 库存
 *
 */
@JsonType
public class Inventory extends Entity {


    @JsonField(fieldName = "inventoryid")
    public String inventoryid;//项目
    @JsonField(fieldName = "itemnum")
    public String itemnum;//项目
    @JsonField(fieldName = "description")
    public String description;//描述
    @JsonField(fieldName = "siteid")
    public String siteid;//地点
    @JsonField(fieldName = "locationdesc")
    public String locationdesc;//库房描述
    @JsonField(fieldName = "status")
    public String status;//状态
    @JsonField(fieldName = "location")
    public String location;//库房
    @JsonField(fieldName = "issueunit")
    public String issueunit;//发放单位
    @JsonField(fieldName = "curbal")
    public String curbal;//余量
    @JsonField(fieldName = "lottype")
    public String lottype;//批次类型

    public String getLottype() {
        return lottype;
    }

    public void setLottype(String lottype) {
        this.lottype = lottype;
    }

    public String getInventoryid() {
        return inventoryid;
    }

    public void setInventoryid(String inventoryid) {
        this.inventoryid = inventoryid;
    }

    public String getItemnum() {
        return itemnum;
    }

    public void setItemnum(String itemnum) {
        this.itemnum = itemnum;
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

    public String getLocationdesc() {
        return locationdesc;
    }

    public void setLocationdesc(String locationdesc) {
        this.locationdesc = locationdesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIssueunit() {
        return issueunit;
    }

    public void setIssueunit(String issueunit) {
        this.issueunit = issueunit;
    }

    public String getCurbal() {
        return curbal;
    }

    public void setCurbal(String curbal) {
        this.curbal = curbal;
    }
}
