package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/12/29.
 * 故障类
 */
@JsonType
@DatabaseTable(tableName = "Failurecode")
public class Failurecode extends Entity {
    private static final String TAG = "Failurecode";
    private static final long serialVersionUID = 2015050105L;
    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "failurecode")
    @DatabaseField(columnName = "failurecode")
    public String failurecode;//故障名称
    @JsonField(fieldName = "description")
    @DatabaseField(columnName = "description")
    public String description;//描述
    @JsonField(fieldName = "failurecodeid")
    @DatabaseField(columnName = "failurecodeid")
    public String failurecodeid;//唯一标识
    @JsonField(fieldName = "orgid")
    @DatabaseField(columnName = "orgid")
    public String orgid;//组织标识
}
