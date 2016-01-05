package com.jf_eam_project.model;


import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/**
 * 设备及部件
 *
 */
@JsonType
@DatabaseTable(tableName = "Udinspoasset")
public class Udinspoasset extends Entity {

    @DatabaseField(generatedId = true)
    public int id;

    @DatabaseField(columnName = "assetdesc")
    @JsonField(fieldName = "assetdesc")
    public String assetdesc;//设备描述

    @DatabaseField(columnName = "assetnum")
    @JsonField(fieldName = "assetnum")
    public String assetnum;//设备描述

    @DatabaseField(columnName = "childassetnum")
    @JsonField(fieldName = "childassetnum")
    public String childassetnum;//设备部件

    @DatabaseField(columnName = "insponum")
    @JsonField(fieldName = "insponum")
    public String insponum;//巡检单

    @DatabaseField(columnName = "location")
    @JsonField(fieldName = "location")
    public String location;//位置

    @DatabaseField(columnName = "udinspoassetlinenum")
    @JsonField(fieldName = "udinspoassetlinenum")
    public String udinspoassetlinenum;//序号

    @DatabaseField(columnName = "locationsdesc")
    @JsonField(fieldName = "locationsdesc")
    public String locationsdesc;//位置描述

    @DatabaseField(columnName = "udinspoassetnum")
    @JsonField(fieldName = "udinspoassetnum")
    public String udinspoassetnum;//设备编号

    public String getUdinspoassetnum() {
        return udinspoassetnum;
    }

    public void setUdinspoassetnum(String udinspoassetnum) {
        this.udinspoassetnum = udinspoassetnum;
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

    public String getChildassetnum() {
        return childassetnum;
    }

    public void setChildassetnum(String childassetnum) {
        this.childassetnum = childassetnum;
    }

    public String getInsponum() {
        return insponum;
    }

    public void setInsponum(String insponum) {
        this.insponum = insponum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUdinspoassetlinenum() {
        return udinspoassetlinenum;
    }

    public void setUdinspoassetlinenum(String udinspoassetlinenum) {
        this.udinspoassetlinenum = udinspoassetlinenum;
    }

    public String getLocationsdesc() {
        return locationsdesc;
    }

    public void setLocationsdesc(String locationsdesc) {
        this.locationsdesc = locationsdesc;
    }
}
