package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/4.
 * 物料
 */
@JsonType
public class Wpmaterial extends Entity{
    private static final String TAG = "Wpmaterial";
    private static final long serialVersionUID = 2015050105L;

    @JsonField(fieldName = "itemnum")
    public String itemnum;
    @JsonField(fieldName = "itemqty")
    public String itemqty;
    @JsonField(fieldName = "location")
    public String location;
    @JsonField(fieldName = "requiredate")
    public String requiredate;
    @JsonField(fieldName = "restype")
    public String restype;
    @JsonField(fieldName = "storelocsite")
    public String storelocsite;
    @JsonField(fieldName = "unitcost")
    public String unitcost;
}
