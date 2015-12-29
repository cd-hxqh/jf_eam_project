package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/12/29.
 * 作业计划
 */
@JsonType
@DatabaseTable(tableName = "Jobplan")
public class Jobplan extends Entity {
    private static final String TAG = "Jobplan";
    private static final long serialVersionUID = 2015050105L;
    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "jpnum")
    @DatabaseField(columnName = "jpnum")
    public String jpnum;//作业计划
    @JsonField(fieldName = "description")
    @DatabaseField(columnName = "description")
    public String description;//描述
    @JsonField(fieldName = "templatetype")
    @DatabaseField(columnName = "templatetype")
    public String templatetype;//模板类型
    @JsonField(fieldName = "siteid")
    @DatabaseField(columnName = "siteid")
    public String siteid;//地点
    @JsonField(fieldName = "orgid")
    @DatabaseField(columnName = "orgid")
    public String orgid;//组织标识
}
