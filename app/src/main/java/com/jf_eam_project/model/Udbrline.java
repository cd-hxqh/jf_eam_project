package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by think on 2015/12/25.
 * 物资借用归还行表
 */
@JsonType
public class Udbrline extends Entity {
    private static final String TAG = "Udbrline";
    private static final long serialVersionUID = 2015050105L;
    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "UDBRLINENUM")
    public String udbrlinenum;//行号
    @JsonField(fieldName = "FROMSTORELOC")
    public String fromstoreloc;//借出库房
    @JsonField(fieldName = "ITEMNUM")
    public String itemnum;//物资编码
    @JsonField(fieldName = "ITEM_DESCRIPTION")
    public String item_description;//描述
    @JsonField(fieldName = "ITEM_ORDERUNIT")
    public String item_orderunit;//单位
    @JsonField(fieldName = "BORROWQTY")
    public String borrowqty;//借用数量
    @JsonField(fieldName = "RETURNQTY")
    public String returnqty;//归还数量
    @JsonField(fieldName = "UDBRNUM")
    public String udbrnum;//主表编号
}
