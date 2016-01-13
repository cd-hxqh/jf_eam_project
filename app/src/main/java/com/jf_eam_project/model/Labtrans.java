package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/12/18.
 * 实际员工
 */
@JsonType
@DatabaseTable(tableName = "Labtrans")
public class Labtrans extends Entity{
    private static final String TAG = "Labtrans";
    private static final long serialVersionUID = 2015050105L;

    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "taskid")
    @DatabaseField(columnName = "taskid")
    public String taskid;//任务
    @JsonField(fieldName = "regularhrs")
    @DatabaseField(columnName = "regularhrs")
    public String regularhrs;//常规时数
    @JsonField(fieldName = "payrate")
    @DatabaseField(columnName = "payrate")
    public String payrate;//费率
    @JsonField(fieldName = "craft")
    @DatabaseField(columnName = "craft")
    public String craft;//工种
    @JsonField(fieldName = "actualstaskid")
    @DatabaseField(columnName = "actualstaskid")
    public String actualstaskid;//
    @JsonField(fieldName = "laborcode")
    @DatabaseField(columnName = "laborcode")
    public String laborcode;//员工
    @JsonField(fieldName = "startdate")
    @DatabaseField(columnName = "startdate")
    public String startdate;//开始日期
    @JsonField(fieldName = "transtype")
    @DatabaseField(columnName = "transtype")
    public String transtype;//类型
    @DatabaseField(columnName = "wonum")
    public String wonum;//所属工单
    public String type;//add/update
}
