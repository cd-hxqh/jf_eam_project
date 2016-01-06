package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/12/25.
 * 员工
 */
@JsonType
@DatabaseTable(tableName = "Laborcraftrate")
public class Laborcraftrate extends Entity {
    private static final String TAG = "Laborcraftrate";
    private static final long serialVersionUID = 2015050105L;
    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "laborcode")
    @DatabaseField(columnName = "laborcode")
    public String laborcode;//员工
    @JsonField(fieldName = "contractnum")
    @DatabaseField(columnName = "contractnum")
    public String contractnum;//合同
    @JsonField(fieldName = "craft")
    @DatabaseField(columnName = "craft")
    public String craft;//工种
    @JsonField(fieldName = "skilllevel")
    @DatabaseField(columnName = "skilllevel")
    public String skilllevel;//技能级别
    @JsonField(fieldName = "vendor")
    @DatabaseField(columnName = "vendor")
    public String vendor;//供应商
}
