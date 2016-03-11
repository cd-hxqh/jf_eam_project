package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by think on 2015/12/25.
 * 物资编码申请行表
 */
@JsonType
public class Uditemreqline extends Entity {
    private static final String TAG = "Uditemreqline";
    private static final long serialVersionUID = 2015050105L;
    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "UDLINENUM")
    public String udlinenum;//序号
    @JsonField(fieldName = "NAME")
    public String name;//物资名称
    @JsonField(fieldName = "UDUNIT")
    public String udunit;//单位
    @JsonField(fieldName = "UDITEMREQLINE1")
    public String uditemreqline1;//生产厂家
    @JsonField(fieldName = "SPECIFY")
    public String specify;//物资型号
    @JsonField(fieldName = "UDUNITCOST")
    public String udunitcost;//预估单价
    @JsonField(fieldName = "CLASSSTRUCTUREID")
    public String classstructureid;//物资分类
    @JsonField(fieldName = "ITEMNUM")
    public String itemnum;//物资编码
    @JsonField(fieldName = "UDITEMREQNUM")
    public String uditemreqnum;//编码申请编号
    @JsonField(fieldName = "MEMO")
    public String memo;//备注
}
