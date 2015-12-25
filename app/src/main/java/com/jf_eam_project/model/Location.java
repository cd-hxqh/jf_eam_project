package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/12/25.
 * 位置
 */
@JsonType
@DatabaseTable(tableName = "Location")
public class Location extends Entity {
    private static final String TAG = "Location";
    private static final long serialVersionUID = 2015050105L;
    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "location")
    @DatabaseField(columnName = "location")
    public String location;//位置
    @JsonField(fieldName = "description")
    @DatabaseField(columnName = "description")
    public String description;//描述
    @JsonField(fieldName = "siteid")
    @DatabaseField(columnName = "siteid")
    public String siteid;//地点
    @JsonField(fieldName = "type")
    @DatabaseField(columnName = "type")
    public String type;//类型
}
