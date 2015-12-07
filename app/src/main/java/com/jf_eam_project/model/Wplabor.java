package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/4.
 * 员工
 */
@JsonType
public class Wplabor extends Entity{
    private static final String TAG = "Wplabor";
    private static final long serialVersionUID = 2015050105L;

    @JsonField(fieldName = "taskid")
    public String taskid;
    @JsonField(fieldName = "amcrew")
    public String amcrew;
    @JsonField(fieldName = "amcrewtype")
    public String amcrewtype;
    @JsonField(fieldName = "craft")
    public String craft;
    @JsonField(fieldName = "crewworkgroup")
    public String crewworkgroup;
    @JsonField(fieldName = "laborcode")
    public String laborcode;
    @JsonField(fieldName = "laborhrs")
    public String laborhrs;
    @JsonField(fieldName = "quantity")
    public String quantity;
}
