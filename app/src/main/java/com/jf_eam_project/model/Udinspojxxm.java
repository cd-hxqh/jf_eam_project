package com.jf_eam_project.model;


import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;


/**
 * 设备及部件
 *
 */
@JsonType
public class Udinspojxxm extends Entity {


    @JsonField(fieldName = "udinspojxxmlinenum")
    public String udinspojxxmlinenum;//序号
    @JsonField(fieldName = "udinspojxxm4")
    public String udinspojxxm4;//数值（C(相)）
    @JsonField(fieldName = "udinspojxxm3")
    public String udinspojxxm3;//数值（B(相)）
    @JsonField(fieldName = "udinspojxxm2")
    public String udinspojxxm2;//数值（A(相)）
    @JsonField(fieldName = "udinspoassetnum")
    public String udinspoassetnum;//设备编号
    @JsonField(fieldName = "fillmethod")
    public String fillmethod;//计量单位
    @JsonField(fieldName = "execution")
    public String execution;//巡检情况描述
    @JsonField(fieldName = "description")
    public String description;//巡检项目标准


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
}
