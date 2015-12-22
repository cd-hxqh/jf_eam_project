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

    @JsonField(fieldName = "actualstaskid")
    public String actualstaskid;//任务
    @JsonField(fieldName = "itemnum")
    public String itemnum;//项目
    @JsonField(fieldName = "linetype")
    public String linetype;//行类型
    @JsonField(fieldName = "storeloc")
    public String storeloc;//库房
    @JsonField(fieldName = "SITEID")
    public String SITEID;//地点
    @JsonField(fieldName = "POSITIVEQUANTITY")
    public String POSITIVEQUANTITY;//数量
    @JsonField(fieldName = "UNITCOST")
    public String UNITCOST;//单位成本
    @JsonField(fieldName = "unitcost")
    public String unitcost;//单位成本
}
