package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/12/25.
 * 人员
 */
@JsonType
@DatabaseTable(tableName = "Person")
public class Person extends Entity {
    private static final String TAG = "Person";
    private static final long serialVersionUID = 2015050105L;
    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "personid")
    @DatabaseField(columnName = "personid")
    public String personid;//人员
    @JsonField(fieldName = "displayname")
    @DatabaseField(columnName = "displayname")
    public String displayname;//名称
    @JsonField(fieldName = "department")
    @DatabaseField(columnName = "department")
    public String department;//部门
    @JsonField(fieldName = "location")
    @DatabaseField(columnName = "location")
    public String location;//人员的位置
    @JsonField(fieldName = "locationorg")
    @DatabaseField(columnName = "locationorg")
    public String locationorg;//组织
    @JsonField(fieldName = "locationsite")
    @DatabaseField(columnName = "locationsite")
    public String locationsite;//人员的地点
    @JsonField(fieldName = "title")
    @DatabaseField(columnName = "title")
    public String title;//头衔
}
