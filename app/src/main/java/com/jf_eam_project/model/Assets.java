package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/12/25.
 * 资产
 *
 */
@JsonType
@DatabaseTable(tableName = "Assets")
public class Assets extends Entity {
    private static final String TAG = "Assets";
    private static final long serialVersionUID = 2015050105L;
    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "assetnum")
    @DatabaseField(columnName = "assetnum")
    public String assetnum;//资产编号
    @JsonField(fieldName = "description")
    @DatabaseField(columnName = "description")
    public String description;//资产描述
    @JsonField(fieldName = "siteid")
    @DatabaseField(columnName = "siteid")
    public String siteid;//地点
    @JsonField(fieldName = "location")
    @DatabaseField(columnName = "location")
    public String location;//位置
}
