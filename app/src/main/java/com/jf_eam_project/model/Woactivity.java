package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/3.
 * 工单任务
 */
@JsonType
public class Woactivity extends Entity{
    private static final String TAG = "Woactivity";
    private static final long serialVersionUID = 2015050105L;

    @JsonField(fieldName = "assetnum")
    public String assetnum;
    @JsonField(fieldName = "assetdesc")
    public String assetdesc;
    @JsonField(fieldName = "location")
    public String location;
    @JsonField(fieldName = "locationdesc")
    public String locationdesc;
    @JsonField(fieldName = "woclass")
    public String woclass;
    @JsonField(fieldName = "wosequence")
    public String wosequence;
    @JsonField(fieldName = "wonum")
    public String wonum;

}
