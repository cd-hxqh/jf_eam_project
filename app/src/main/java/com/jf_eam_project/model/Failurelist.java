package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/12/29.
 * 问题代码
 */
@JsonType
@DatabaseTable(tableName = "Failurelist")
public class Failurelist extends Entity {
    private static final String TAG = "Failurelist";
    private static final long serialVersionUID = 2015050105L;
    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "failureclass")
    @DatabaseField(columnName = "failureclass")
    public String failureclass;//故障类
    @JsonField(fieldName = "failurecode")
    @DatabaseField(columnName = "failurecode")
    public String failurecode;//故障代码
    @JsonField(fieldName = "flcdescription")
    @DatabaseField(columnName = "flcdescription")
    public String flcdescription;//FLC描述
    @JsonField(fieldName = "parent")
    @DatabaseField(columnName = "parent")
    public String parent;//父级故障列表号
    @JsonField(fieldName = "orgid")
    @DatabaseField(columnName = "orgid")
    public String orgid;//组织标识
    @JsonField(fieldName = "type")
    @DatabaseField(columnName = "type")
    public String type;//故障代码类型
}
