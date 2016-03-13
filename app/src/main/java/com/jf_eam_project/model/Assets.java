package com.jf_eam_project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/12/25.
 * 资产
 *
 */
@JsonType
@DatabaseTable(tableName = "Assets")
public class Assets extends Entity {
    private static final String TAG = "Assets";
    private static final long serialVersionUID = 2015050105L;
    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "assetnum")
    @DatabaseField(columnName = "assetnum")
    public String assetnum;//资产编号
    @JsonField(fieldName = "description")
    @DatabaseField(columnName = "description")
    public String description;//资产描述
    @JsonField(fieldName = "siteid")
    @DatabaseField(columnName = "siteid")
    public String siteid;//地点
    @JsonField(fieldName = "location")
    @DatabaseField(columnName = "location")
    public String location;//位置

    @JsonField(fieldName = "AZRQ")
    public String azrq;//安装日期
    @JsonField(fieldName = "BXDQR")
    public String bxdqr;//保修到期日
    @JsonField(fieldName = "FIRSTCLASS")
    public String firstclass; //大类
    @JsonField(fieldName = "PURCHASEPRICE")
    public String purchaseprice; //采购价格
    @JsonField(fieldName = "SECONDCLASS")
    public String secondclass; //中类
    @JsonField(fieldName = "THIRDCLASS")
    public String thirdclass; //小类
    @JsonField(fieldName = "TYRQ")
    public String tyrq; //投运日期
    @JsonField(fieldName = "UDASSETTYPE")
    public String udassettype; //设备类型
    @JsonField(fieldName = "YSYNX")
    public String ysynx; //已使用年限


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSiteid() {
        return siteid;
    }

    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
