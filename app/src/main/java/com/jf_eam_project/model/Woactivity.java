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

    @JsonField(fieldName = "taskid")
    public String taskid;//任务
    @JsonField(fieldName = "description")
    public String description;//摘要
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
    public String wonum;//工单号
    @JsonField(fieldName = "estdur")
    public String estdur;//估计持续时间
    @JsonField(fieldName = "status")
    public String status;//状态
    @JsonField(fieldName = "owner")
    public String owner;//所有者
    @JsonField(fieldName = "ownergroup")
    public String ownergroup;//所有者组
    @JsonField(fieldName = "targstartdate")
    public String targstartdate;//目标开始时间
    @JsonField(fieldName = "targcompdate")
    public String targcompdate;//目标完成时间
    @JsonField(fieldName = "actstart")
    public String actstart;//实际开始时间
    @JsonField(fieldName = "actfinish")
    public String actfinish;//实际完成时间

}
