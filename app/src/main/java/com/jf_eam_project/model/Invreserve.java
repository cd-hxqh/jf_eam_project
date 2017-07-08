package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/25.
 * 预留项目
 *
 */
@JsonType
public class Invreserve extends Entity {
    private static final long serialVersionUID = 2015050105L;
    @JsonField(fieldName = "CONTROLACC")
    public String controlacc;//科目
    @JsonField(fieldName = "CURBALTOTAL")
    public String curbaltotal;//当前余量
    @JsonField(fieldName = "DESCRIPTION")
    public String description;//描述
    @JsonField(fieldName = "INVBALANCES.PHYSCNT")
    public String physcnt;//实际库存量
    @JsonField(fieldName = "ITEMNUM")
    public String itemnum;//项目
    @JsonField(fieldName = "LINECOST")
    public String linecost;//行成本
    @JsonField(fieldName = "LOCATION")
    public String location;//位置
    @JsonField(fieldName = "RESERVEDQTY")
    public String reservedqty;//数量
    @JsonField(fieldName = "UNITCOST")
    public String unitcost;//单位成本
    @JsonField(fieldName = "WONUM")
    public String wonum;// 工单号

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getControlacc() {
        return controlacc;
    }

    public void setControlacc(String controlacc) {
        this.controlacc = controlacc;
    }

    public String getCurbaltotal() {
        return curbaltotal;
    }

    public void setCurbaltotal(String curbaltotal) {
        this.curbaltotal = curbaltotal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhyscnt() {
        return physcnt;
    }

    public void setPhyscnt(String physcnt) {
        this.physcnt = physcnt;
    }

    public String getItemnum() {
        return itemnum;
    }

    public void setItemnum(String itemnum) {
        this.itemnum = itemnum;
    }

    public String getLinecost() {
        return linecost;
    }

    public void setLinecost(String linecost) {
        this.linecost = linecost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReservedqty() {
        return reservedqty;
    }

    public void setReservedqty(String reservedqty) {
        this.reservedqty = reservedqty;
    }

    public String getUnitcost() {
        return unitcost;
    }

    public void setUnitcost(String unitcost) {
        this.unitcost = unitcost;
    }

    public String getWonum() {
        return wonum;
    }

    public void setWonum(String wonum) {
        this.wonum = wonum;
    }
}
