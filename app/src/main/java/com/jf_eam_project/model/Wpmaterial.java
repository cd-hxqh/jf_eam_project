package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/4.
 * 物料
 */
@JsonType
public class Wpmaterial extends Entity{
    private static final String TAG = "Wpmaterial";
    private static final long serialVersionUID = 2015050105L;

    @JsonField(fieldName = "itemnum")
    public String itemnum;//项目
    @JsonField(fieldName = "itemqty")
    public String itemqty;//数量
    @JsonField(fieldName = "location")
    public String location;//库房
    @JsonField(fieldName = "requiredate")
    public String requiredate;//要求日期
    @JsonField(fieldName = "restype")
    public String restype;//预留类型
    @JsonField(fieldName = "storelocsite")
    public String storelocsite;//库房地点
    @JsonField(fieldName = "unitcost")
    public String unitcost;//单位成本
}
