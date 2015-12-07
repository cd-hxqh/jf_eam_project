package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/4.
 * 计划服务
 */
@JsonType
public class Wpservice extends Entity{
    private static final String TAG = "Wpservice";
    private static final long serialVersionUID = 2015050105L;

    @JsonField(fieldName = "linetype")
    public String linetype;
    @JsonField(fieldName = "requestby")
    public String requestby;
    @JsonField(fieldName = "requiredate")
    public String requiredate;
    @JsonField(fieldName = "taskid")
    public String taskid;
}
