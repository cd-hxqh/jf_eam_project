package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/4.
 * 任务分配
 */
@JsonType
public class Assignment extends Entity {
    private static final String TAG = "Assignment";
    private static final long serialVersionUID = 2015050105L;

    @JsonField(fieldName = "taskid")
    public String taskid;//任务
    @JsonField(fieldName = "laborcode")
    public String laborcode;//员工
    @JsonField(fieldName = "laborhrs")
    public String laborhrs;//时数
    @JsonField(fieldName = "amcrew")
    public String amcrew;//班组
    @JsonField(fieldName = "amcrewtype")
    public String amcrewtype;//班组类型
    @JsonField(fieldName = "contractnum")
    public String contractnum;//合同
    @JsonField(fieldName = "craft")
    public String craft;//工种
    @JsonField(fieldName = "crewworkgroup")
    public String crewworkgroup;//班组工作组
    @JsonField(fieldName = "scheduledate")
    public String scheduledate;//调度开始时间
    @JsonField(fieldName = "skilllevel")
    public String skilllevel;//技能级别
    @JsonField(fieldName = "status")
    public String status;//状态
    @JsonField(fieldName = "vendor")
    public String vendor;//供应商
}
