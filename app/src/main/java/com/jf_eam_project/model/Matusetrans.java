package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/21.
 * 实际物料
 */
@JsonType
public class Matusetrans extends Entity{
    private static final String TAG = "Matusetrans";
    private static final long serialVersionUID = 2015050105L;

    @JsonField(fieldName = "ITEMNUM")
    public String itemnum;//任务
    @JsonField(fieldName = "DESCRIPTION")
    public String description;//描述
    @JsonField(fieldName = "BINNUM")
    public String binnum;//货柜
    @JsonField(fieldName = "LOTNUM")
    public String lotnum;//批次
    @JsonField(fieldName = "POSITIVEQUANTITY")
    public String positivequantity;//数量
    @JsonField(fieldName = "INVENTORY.ISSUEUNIT")
    public String INVENTORY_ISSUEUNIT;//发放单位
    @JsonField(fieldName = "UNITCOST")
    public String unitcost;//单位成本
    @JsonField(fieldName = "LINECOST")
    public String linecost;//行成本
    @JsonField(fieldName = "TOSITEID")
    public String tositeid;//地点
    @JsonField(fieldName = "ISSUETYPE")
    public String issuetype;//交易类型
    @JsonField(fieldName = "ENTERBY")
    public String enterby;//输入人
    @JsonField(fieldName = "ACTUALDATE")
    public String actualdate;//实际日期
    @JsonField(fieldName = "TRANSDATE")
    public String transdate;//交易日期
    @JsonField(fieldName = "LOCATION")
    public String location;//交易日期
}
