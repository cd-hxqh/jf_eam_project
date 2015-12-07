package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/4.
 * 任务分配
 */
@JsonType
public class Assignment extends Entity {
    private static final String TAG = "Assignment";
    private static final long serialVersionUID = 2015050105L;

    @JsonField(fieldName = "taskid")
    public String taskid;
    @JsonField(fieldName = "laborcode")
    public String laborcode;
    @JsonField(fieldName = "laborhrs")
    public String laborhrs;
    @JsonField(fieldName = "amcrew")
    public String amcrew;
    @JsonField(fieldName = "amcrewtype")
    public String amcrewtype;
    @JsonField(fieldName = "contractnum")
    public String contractnum;
    @JsonField(fieldName = "craft")
    public String craft;
    @JsonField(fieldName = "crewworkgroup")
    public String crewworkgroup;
    @JsonField(fieldName = "scheduledate")
    public String scheduledate;
    @JsonField(fieldName = "skilllevel")
    public String skilllevel;
    @JsonField(fieldName = "status")
    public String status;
    @JsonField(fieldName = "vendor")
    public String vendor;
}
