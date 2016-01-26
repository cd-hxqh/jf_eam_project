package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/12/4.
 * 任务分配
 */
@JsonType
@DatabaseTable(tableName = "Assignment")
public class Assignment extends Entity {
    private static final String TAG = "Assignment";
    private static final long serialVersionUID = 2015050105L;

    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "taskid")
    @DatabaseField(columnName = "taskid")
    public String taskid;//任务
    @JsonField(fieldName = "assignmentid")
    @DatabaseField(columnName = "assignmentid")
    public String assignmentid;//任务分配id
    @JsonField(fieldName = "laborcode")
    @DatabaseField(columnName = "laborcode")
    public String laborcode;//员工
    @JsonField(fieldName = "laborhrs")
    @DatabaseField(columnName = "laborhrs")
    public String laborhrs;//时数
    @JsonField(fieldName = "amcrew")
    @DatabaseField(columnName = "amcrew")
    public String amcrew;//班组
    @JsonField(fieldName = "amcrewtype")
    @DatabaseField(columnName = "amcrewtype")
    public String amcrewtype;//班组类型
    @JsonField(fieldName = "contractnum")
    @DatabaseField(columnName = "contractnum")
    public String contractnum;//合同
    @JsonField(fieldName = "craft")
    @DatabaseField(columnName = "craft")
    public String craft;//工种
    @JsonField(fieldName = "crewworkgroup")
    @DatabaseField(columnName = "crewworkgroup")
    public String crewworkgroup;//班组工作组
    @JsonField(fieldName = "scheduledate")
    @DatabaseField(columnName = "scheduledate")
    public String scheduledate;//调度开始时间
    @JsonField(fieldName = "skilllevel")
    @DatabaseField(columnName = "skilllevel")
    public String skilllevel;//技能级别
    @JsonField(fieldName = "status")
    @DatabaseField(columnName = "status")
    public String status;//状态
    @JsonField(fieldName = "vendor")
    @DatabaseField(columnName = "vendor")
    public String vendor;//供应商
    @DatabaseField(columnName = "type")
    public String type;//add/update
    @DatabaseField(columnName = "belongid")
    public int belongid;//
}
