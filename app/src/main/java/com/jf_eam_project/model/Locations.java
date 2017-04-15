package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by think on 2015/12/25.
 * 位置发放
 */
@JsonType
public class Locations extends Entity {
    private static final long serialVersionUID = 2015050105L;
    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "branch")
    public String branch;//分公司
    @JsonField(fieldName = "description")
    @DatabaseField(columnName = "description")
    public String description;//描述
    @JsonField(fieldName = "location")
    public String location;//原库房
    @JsonField(fieldName = "siteid")
    public String siteid;//地点
    @JsonField(fieldName = "type")
    public String type;//类型
    @JsonField(fieldName = "udbelong")
    public String udbelong;//运行单位
}
