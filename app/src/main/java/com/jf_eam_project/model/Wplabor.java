package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/4.
 * 计划员工
 */
@JsonType
public class Wplabor extends Entity{
    private static final String TAG = "Wplabor";
    private static final long serialVersionUID = 2015050105L;

    @JsonField(fieldName = "taskid")
    public String taskid;//任务
    @JsonField(fieldName = "amcrew")
    public String amcrew;//班组
    @JsonField(fieldName = "amcrewtype")
    public String amcrewtype;//班组类型
    @JsonField(fieldName = "craft")
    public String craft;//工种
    @JsonField(fieldName = "crewworkgroup")
    public String crewworkgroup;//班组工作组
    @JsonField(fieldName = "laborcode")
    public String laborcode;//员工
    @JsonField(fieldName = "laborhrs")
    public String laborhrs;//常规时数
    @JsonField(fieldName = "quantity")
    public String quantity;//数量
}
