package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/12/25.
 * 物资编码申请
 */
@JsonType
public class Uditemreq extends Entity {
    private static final String TAG = "Uditemreq";
    private static final long serialVersionUID = 2015050105L;
    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "UDITEMREQNUM")
    public String uditemreqnum;//申请单号
    @JsonField(fieldName = "DESCRIPTION")
    public String description;//描述
    @JsonField(fieldName = "REASON")
    public String reason;//申请原因
    @JsonField(fieldName = "ALNDOMAIN_DESCRIPTION")
    public String alndomain_description;//状态
    @JsonField(fieldName = "BRANCH")
    public String branch;//分公司
    @JsonField(fieldName = "UDBELONG")
    public String udbelong;//风电场
    @JsonField(fieldName = "PERSON_DISPLAYNAME")
    public String person_displayname;//创建人
    @JsonField(fieldName = "CREATEDATE")
    public String createdate;//创建日期
}
