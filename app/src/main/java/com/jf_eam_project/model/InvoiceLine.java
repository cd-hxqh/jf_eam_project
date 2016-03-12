package com.jf_eam_project.model;


import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;


/**
 * Created by think on 2015/12/10
 * 发票行表
 *
 */
@JsonType
public class InvoiceLine extends Entity {


    @JsonField(fieldName = "INVOICELINENUM")
    public String invoicelinenum;// 行
    @JsonField(fieldName = "INVOICENUM")
    public String invoicenum;//发票编号
    @JsonField(fieldName = "LINETYPE")
    public String linetype;//行类型
    @JsonField(fieldName = "ITEMNUM")
    public String itemnum;//项目
    @JsonField(fieldName = "DESCRIPTION")
    public String description;//描述
    @JsonField(fieldName = "ENTERBY")
    public String enterby;//输入人
    @JsonField(fieldName = "ENTERDATE")
    public String enterdate;//输入日期
    @JsonField(fieldName = "CATEGORY")
    public String category;//类别
    @JsonField(fieldName = "QTYFORUI")
    public String qtyforui;//数量
    @JsonField(fieldName = "INVOICEUNIT")
    public String invoiceunit;//订购单位
    @JsonField(fieldName = "INVENTORY_ISSUEUNIT")
    public String inventory_issueunit;//发放单位
    @JsonField(fieldName = "CONVERSION")
    public String conversion;//换算系数
    @JsonField(fieldName = "UNITCOST")
    public String unitcost;//单位成本
    @JsonField(fieldName = "LINECOSTFORUI")
    public String linecostforui;//行成本
    @JsonField(fieldName = "TAX1FORUI")
    public String tax1forui;//税
    @JsonField(fieldName = "PRORATECOST")
    public String proratecost;//摊派成本
    @JsonField(fieldName = "POLINE_STORELOC")
    public String poline_storeloc;//库房
    @JsonField(fieldName = "INVOICECOST_TOSITEIDNONPER")
    public String invoicecost_tositeidnonper;//地点



}
