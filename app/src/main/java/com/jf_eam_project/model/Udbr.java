package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by think on 2015/12/25.
 * 物资借用归还主表
 */
@JsonType
public class Udbr extends Entity {
    private static final String TAG = "Udbr";
    private static final long serialVersionUID = 2015050105L;
    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "UDBRNUM")
    public String udbrnum;//申请单号
    @JsonField(fieldName = "DESCRIPTION")
    public String description;//描述
    @JsonField(fieldName = "BRANCH")
    public String branch;//分公司
    @JsonField(fieldName = "UDBELONG")
    public String udbelong;//风电场
    @JsonField(fieldName = "PERSON.DISPLAYNAME")
    public String person_displayname;//借用人
    @JsonField(fieldName = "CREATEDATE")
    public String createdate;//借用日期
    @JsonField(fieldName = "FROMBRANCH")
    public String frombranch;//借出分公司
    @JsonField(fieldName = "FROMBELONG")
    public String frombelong;//借出风电场
    @JsonField(fieldName = "STATUS")
    public String status;//状态
}
