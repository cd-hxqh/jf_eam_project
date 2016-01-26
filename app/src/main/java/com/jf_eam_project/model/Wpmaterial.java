package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/12/4.
 * 物料
 */
@JsonType
@DatabaseTable(tableName = "Wpmaterial")
public class Wpmaterial extends Entity{
    private static final String TAG = "Wpmaterial";
    private static final long serialVersionUID = 2015050105L;

    @DatabaseField(generatedId = true)
    public int id;
    @JsonField(fieldName = "itemnum")
    @DatabaseField(columnName = "itemnum")
    public String itemnum;//项目
    @JsonField(fieldName = "wpitemid")
    @DatabaseField(columnName = "wpitemid")
    public String wpitemid;//物料id
    @JsonField(fieldName = "itemqty")
    @DatabaseField(columnName = "itemqty")
    public String itemqty;//数量
    @JsonField(fieldName = "location")
    @DatabaseField(columnName = "location")
    public String location;//库房
    @JsonField(fieldName = "requiredate")
    @DatabaseField(columnName = "requiredate")
    public String requiredate;//要求日期
    @JsonField(fieldName = "restype")
    @DatabaseField(columnName = "restype")
    public String restype;//预留类型
    @JsonField(fieldName = "storelocsite")
    @DatabaseField(columnName = "storelocsite")
    public String storelocsite;//库房地点
    @JsonField(fieldName = "unitcost")
    @DatabaseField(columnName = "unitcost")
    public String unitcost;//单位成本
    @JsonField(fieldName = "taskid")
    @DatabaseField(columnName = "taskid")
    public String taskid;//任务
    @JsonField(fieldName = "requestby")
    @DatabaseField(columnName = "requestby")
    public String requestby;//请求者
    @DatabaseField(columnName = "type")
    public String type;//add/update
    @DatabaseField(columnName = "belongid")
    public int belongid;//
}
