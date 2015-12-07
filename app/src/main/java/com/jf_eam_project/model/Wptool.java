package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/4.
 * 计划工具
 */
@JsonType
public class Wptool extends Entity{
    private static final String TAG = "Wptool";
    private static final long serialVersionUID = 2015050105L;

    @JsonField(fieldName = "itemnum")
    public String itemnum;
    @JsonField(fieldName = "itemqty")
    public String itemqty;
    @JsonField(fieldName = "rate")
    public String rate;
    @JsonField(fieldName = "taskid")
    public String taskid;
}
