package com.jf_eam_project.model;


import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;


/**
 * Created by think on 2015/11/26.
 * 采购计划行
 */
@JsonType
public class PRLine extends Entity {


    @JsonField(fieldName = "category")
    public String category;//类别
    @JsonField(fieldName = "conversion")
    public String conversion;//换算系数
    @JsonField(fieldName = "description")
    public String description;//描述
    @JsonField(fieldName = "enterby")
    public String enterby;//输入人
    @JsonField(fieldName = "enterdate")
    public String enterdate;//输入日期
    @JsonField(fieldName = "itemnum")
    public String itemnum;//项目
    @JsonField(fieldName = "linecost")
    public String linecost;//行成本
    @JsonField(fieldName = "prlinenum")
    public String prlinenum;//行
    @JsonField(fieldName = "prnum")
    public String prnum;//采购计划编号
    @JsonField(fieldName = "tax1")
    public String tax1;//税
    @JsonField(fieldName = "orderunit")
    public String orderunit;//订购单位
    @JsonField(fieldName = "orderqty")
    public String orderqty;//数量
    @JsonField(fieldName = "unitcost")
    public String unitcost;//单位成本
    @JsonField(fieldName = "linetype")
    public String linetype;//行类型
    @JsonField(fieldName = "requestedby")
    public String requestedby;//请求者
    @JsonField(fieldName = "storeloc")
    public String storeloc;//库房


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getConversion() {
        return conversion;
    }

    public void setConversion(String conversion) {
        this.conversion = conversion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnterby() {
        return enterby;
    }

    public void setEnterby(String enterby) {
        this.enterby = enterby;
    }

    public String getEnterdate() {
        return enterdate;
    }

    public void setEnterdate(String enterdate) {
        this.enterdate = enterdate;
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

    public String getPrlinenum() {
        return prlinenum;
    }

    public void setPrlinenum(String prlinenum) {
        this.prlinenum = prlinenum;
    }

    public String getPrnum() {
        return prnum;
    }

    public void setPrnum(String prnum) {
        this.prnum = prnum;
    }

    public String getTax1() {
        return tax1;
    }

    public void setTax1(String tax1) {
        this.tax1 = tax1;
    }

    public String getOrderunit() {
        return orderunit;
    }

    public void setOrderunit(String orderunit) {
        this.orderunit = orderunit;
    }

    public String getOrderqty() {
        return orderqty;
    }

    public void setOrderqty(String orderqty) {
        this.orderqty = orderqty;
    }

    public String getUnitcost() {
        return unitcost;
    }

    public void setUnitcost(String unitcost) {
        this.unitcost = unitcost;
    }

    public String getLinetype() {
        return linetype;
    }

    public void setLinetype(String linetype) {
        this.linetype = linetype;
    }

    public String getRequestedby() {
        return requestedby;
    }

    public void setRequestedby(String requestedby) {
        this.requestedby = requestedby;
    }

    public String getStoreloc() {
        return storeloc;
    }

    public void setStoreloc(String storeloc) {
        this.storeloc = storeloc;
    }
}
