package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2016/1/4.
 * 故障提报单
 */
@JsonType
@DatabaseTable(tableName = "Createreport")
public class Createreport extends Entity {
    private static final String TAG = "Createreport";
    private static final long serialVersionUID = 2015050105L;
    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "reporttype")
    @DatabaseField(columnName = "reporttype")
    public String reporttype;//类别
    @JsonField(fieldName = "culevel")
    @DatabaseField(columnName = "culevel")
    public String culevel;//故障等级
    @JsonField(fieldName = "assettype")
    @DatabaseField(columnName = "assettype")
    public String assettype;//设备类别
    @JsonField(fieldName = "assetnum")
    @DatabaseField(columnName = "assetnum")
    public String assetnum;//设备
    @JsonField(fieldName = "asset_description")
    @DatabaseField(columnName = "asset_description")
    public String asset_description;//设备描述
    @JsonField(fieldName = "location")
    @DatabaseField(columnName = "location")
    public String location;//位置
    @JsonField(fieldName = "locations_description")
    @DatabaseField(columnName = "locations_description")
    public String locations_description;//位置描述
    @JsonField(fieldName = "description")
    @DatabaseField(columnName = "description")
    public String description;//描述
    @JsonField(fieldName = "descriptionxx")
    @DatabaseField(columnName = "descriptionxx")
    public String descriptionxx;//详细描述
    @JsonField(fieldName = "reportby")
    @DatabaseField(columnName = "reportby")
    public String reportby;//提报人
    @JsonField(fieldName = "reporttime")
    @DatabaseField(columnName = "reporttime")
    public String reporttime;//提报时间

    @JsonField(fieldName = "branck")
    @DatabaseField(columnName = "branck")
    public String branck;//分公司

    @JsonField(fieldName = "cubelong")
    @DatabaseField(columnName = "cubelong")
    public String cubelong;//运行单位

    @JsonField(fieldName = "udinspojxxmid")
    @DatabaseField(columnName = "udinspojxxmid")
    public String udinspojxxmid;//巡检项目标准Id

    @JsonField(fieldName = "failurecode")
    @DatabaseField(columnName = "failurecode")
    public String failurecode;//故障类


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReporttype() {
        return reporttype;
    }

    public void setReporttype(String reporttype) {
        this.reporttype = reporttype;
    }

    public String getCulevel() {
        return culevel;
    }

    public void setCulevel(String culevel) {
        this.culevel = culevel;
    }

    public String getAssettype() {
        return assettype;
    }

    public void setAssettype(String assettype) {
        this.assettype = assettype;
    }

    public String getAssetnum() {
        return assetnum;
    }

    public void setAssetnum(String assetnum) {
        this.assetnum = assetnum;
    }

    public String getAsset_description() {
        return asset_description;
    }

    public void setAsset_description(String asset_description) {
        this.asset_description = asset_description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocations_description() {
        return locations_description;
    }

    public void setLocations_description(String locations_description) {
        this.locations_description = locations_description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionxx() {
        return descriptionxx;
    }

    public void setDescriptionxx(String descriptionxx) {
        this.descriptionxx = descriptionxx;
    }

    public String getReportby() {
        return reportby;
    }

    public void setReportby(String reportby) {
        this.reportby = reportby;
    }

    public String getReporttime() {
        return reporttime;
    }

    public void setReporttime(String reporttime) {
        this.reporttime = reporttime;
    }

    public String getUdinspojxxmid() {
        return udinspojxxmid;
    }

    public void setUdinspojxxmid(String udinspojxxmid) {
        this.udinspojxxmid = udinspojxxmid;
    }

    public String getBranck() {
        return branck;
    }

    public void setBranck(String branck) {
        this.branck = branck;
    }

    public String getCubelong() {
        return cubelong;
    }

    public void setCubelong(String cubelong) {
        this.cubelong = cubelong;
    }

    public String getFailurecode() {
        return failurecode;
    }

    public void setFailurecode(String failurecode) {
        this.failurecode = failurecode;
    }
}
