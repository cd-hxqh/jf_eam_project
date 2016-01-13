package com.jf_eam_project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/**
 * 设备及部件
 *
 */
@JsonType
@DatabaseTable(tableName = "Udinspojxxm")
public class Udinspojxxm extends Entity {
    @DatabaseField(generatedId = true)
    @JsonIgnore
    public int id;

    @DatabaseField(columnName = "udinspojxxmlinenum")
    @JsonField(fieldName = "udinspojxxmlinenum")
    public String udinspojxxmlinenum;//序号

    @DatabaseField(columnName = "udinspojxxm4")
    @JsonField(fieldName = "udinspojxxm4")
    @JsonIgnore
    public String udinspojxxm4;//数值（C(相)）

    @DatabaseField(columnName = "udinspojxxm3")
    @JsonField(fieldName = "udinspojxxm3")
    @JsonIgnore
    public String udinspojxxm3;//数值（B(相)）

    @DatabaseField(columnName = "udinspojxxm2")
    @JsonField(fieldName = "udinspojxxm2")
    @JsonIgnore
    public String udinspojxxm2;//数值（A(相)）

    @DatabaseField(columnName = "udinspoassetnum")
    @JsonField(fieldName = "udinspoassetnum")
    public String udinspoassetnum;//设备编号

    @DatabaseField(columnName = "fillmethod")
    @JsonField(fieldName = "fillmethod")
    @JsonIgnore
    public String fillmethod;//计量单位

    @DatabaseField(columnName = "execution")
    @JsonField(fieldName = "execution")
    public String execution;//巡检情况描述


    @DatabaseField(columnName = "description")
    @JsonField(fieldName = "description")
    public String description;//巡检项目标准


    @DatabaseField(columnName = "checkby")
    @JsonField(fieldName = "checkby")
    public String checkby;//巡检人员

    public String type; //操作类型


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheckby() {
        return checkby;
    }

    public void setCheckby(String checkby) {
        this.checkby = checkby;
    }

    public String getUdinspojxxmlinenum() {
        return udinspojxxmlinenum;
    }

    public void setUdinspojxxmlinenum(String udinspojxxmlinenum) {
        this.udinspojxxmlinenum = udinspojxxmlinenum;
    }

    public String getUdinspojxxm4() {
        return udinspojxxm4;
    }

    public void setUdinspojxxm4(String udinspojxxm4) {
        this.udinspojxxm4 = udinspojxxm4;
    }

    public String getUdinspojxxm3() {
        return udinspojxxm3;
    }

    public void setUdinspojxxm3(String udinspojxxm3) {
        this.udinspojxxm3 = udinspojxxm3;
    }

    public String getUdinspojxxm2() {
        return udinspojxxm2;
    }

    public void setUdinspojxxm2(String udinspojxxm2) {
        this.udinspojxxm2 = udinspojxxm2;
    }

    public String getUdinspoassetnum() {
        return udinspoassetnum;
    }

    public void setUdinspoassetnum(String udinspoassetnum) {
        this.udinspoassetnum = udinspoassetnum;
    }

    public String getFillmethod() {
        return fillmethod;
    }

    public void setFillmethod(String fillmethod) {
        this.fillmethod = fillmethod;
    }

    public String getExecution() {
        return execution;
    }

    public void setExecution(String execution) {
        this.execution = execution;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
