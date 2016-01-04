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
@DatabaseTable(tableName = "Labor")
public class Labor extends Entity {
    private static final String TAG = "Labor";
    private static final long serialVersionUID = 2015050105L;
    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "laborcode")
    @DatabaseField(columnName = "laborcode")
    public String laborcode;//员工
    @JsonField(fieldName = "orgid")
    @DatabaseField(columnName = "orgid")
    public String orgid;//组织
}
