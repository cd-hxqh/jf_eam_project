package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2016/1/5.
 * 项目
 */
@JsonType
@DatabaseTable(tableName = "Item")
public class Item extends Entity {
    private static final String TAG = "Item";
    private static final long serialVersionUID = 2015050105L;
    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "itemnum")
    @DatabaseField(columnName = "itemnum")
    public String itemnum;//项目
    @JsonField(fieldName = "description")
    @DatabaseField(columnName = "description")
    public String description;//描述
}
