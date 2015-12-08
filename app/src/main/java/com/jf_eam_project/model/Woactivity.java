package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/3.
 * 工单任务
 */
@JsonType
public class Woactivity extends Entity{
    private static final String TAG = "Woactivity";
    private static final long serialVersionUID = 2015050105L;

    @JsonField(fieldName = "assetnum")
    public String assetnum;//资产
    @JsonField(fieldName = "assetdesc")
    public String assetdesc;//资产描述
    @JsonField(fieldName = "location")
    public String location;//位置
    @JsonField(fieldName = "locationdesc")
    public String locationdesc;//位置描述
    @JsonField(fieldName = "woclass")
    public String woclass;//记录类
    @JsonField(fieldName = "wosequence")
    public String wosequence;//序号
    @JsonField(fieldName = "wonum")
    public String wonum;//记录

}
