package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/18.
 * 实际员工
 */
@JsonType
public class Labtrans extends Entity{
    private static final String TAG = "Labtrans";
    private static final long serialVersionUID = 2015050105L;

    @JsonField(fieldName = "taskid")
    public String taskid;//任务
    @JsonField(fieldName = "regularhrs")
    public String regularhrs;//常规时数
    @JsonField(fieldName = "payrate")
    public String payrate;//费率
    @JsonField(fieldName = "craft")
    public String craft;//工种
    @JsonField(fieldName = "actualstaskid")
    public String actualstaskid;//
    @JsonField(fieldName = "laborcode")
    public String laborcode;//员工
    @JsonField(fieldName = "startdate")
    public String startdate;//开始日期
    @JsonField(fieldName = "transtype")
    public String transtype;//类型
}
