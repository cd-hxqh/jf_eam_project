package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/12/4.
 * 计划员工
 */
@JsonType
@DatabaseTable(tableName = "Wplabor")
public class Wplabor extends Entity{
    private static final String TAG = "Wplabor";
    private static final long serialVersionUID = 2015050105L;

    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "taskid")
    @DatabaseField(columnName = "taskid")
    public String taskid;//任务
    @JsonField(fieldName = "wplaborid")
    @DatabaseField(columnName = "wplaborid")
    public String wplaborid;//任务id
    @JsonField(fieldName = "amcrew")
    @DatabaseField(columnName = "amcrew")
    public String amcrew;//班组
    @JsonField(fieldName = "amcrewtype")
    @DatabaseField(columnName = "amcrewtype")
    public String amcrewtype;//班组类型
    @JsonField(fieldName = "craft")
    @DatabaseField(columnName = "craft")
    public String craft;//工种
    @JsonField(fieldName = "crewworkgroup")
    @DatabaseField(columnName = "crewworkgroup")
    public String crewworkgroup;//班组工作组
    @JsonField(fieldName = "laborcode")
    @DatabaseField(columnName = "laborcode")
    public String laborcode;//员工
    @JsonField(fieldName = "laborhrs")
    @DatabaseField(columnName = "laborhrs")
    public String laborhrs;//常规时数
    @JsonField(fieldName = "quantity")
    @DatabaseField(columnName = "quantity")
    public String quantity;//数量
    @JsonField(fieldName = "wonum")
    @DatabaseField(columnName = "wonum")
    public String wonum;//所属工单
    @DatabaseField(columnName = "type")
    public String type;//add/update
    @DatabaseField(columnName = "belongid")
    public int belongid;//
}
