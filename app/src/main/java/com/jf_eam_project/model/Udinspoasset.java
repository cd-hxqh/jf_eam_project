package com.jf_eam_project.model;


import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;


/**
 * 设备及部件
 *
 */
@JsonType
public class Udinspoasset extends Entity {


    @JsonField(fieldName = "assetdesc")
    public String assetdesc;//设备描述
    @JsonField(fieldName = "assetnum")
    public String assetnum;//设备描述
    @JsonField(fieldName = "childassetnum")
    public String childassetnum;//设备部件
    @JsonField(fieldName = "insponum")
    public String insponum;//巡检单
    @JsonField(fieldName = "location")
    public String location;//位置
    @JsonField(fieldName = "udinspoassetlinenum")
    public String udinspoassetlinenum;//序号
    @JsonField(fieldName = "locationsdesc")
    public String locationsdesc;//位置描述


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
