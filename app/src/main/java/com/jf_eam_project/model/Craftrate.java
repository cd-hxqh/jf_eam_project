package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2016/1/4.
 * 工种
 */
@JsonType
@DatabaseTable(tableName = "Craftrate")
public class Craftrate extends Entity {
    private static final String TAG = "Craftrate";
    private static final long serialVersionUID = 2015050105L;
    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "craft")
    @DatabaseField(columnName = "craft")
    public String craft;//工种
    @JsonField(fieldName = "skilllevel")
    @DatabaseField(columnName = "skilllevel")
    public String skilllevel;//技能级别
    @JsonField(fieldName = "contractnum")
    @DatabaseField(columnName = "contractnum")
    public String contractnum;//外部供应商编号
    @JsonField(fieldName = "orgid")
    @DatabaseField(columnName = "orgid")
    public String orgid;//组织
    @JsonField(fieldName = "standardrate")
    @DatabaseField(columnName = "standardrate")
    public String standardrate;//标准费率
    @JsonField(fieldName = "vendor")
    @DatabaseField(columnName = "vendor")
    public String vendor;//外部供应商
}
