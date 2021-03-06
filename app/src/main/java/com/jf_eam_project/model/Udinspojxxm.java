package com.jf_eam_project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/**
 * 项目标准
 */
@JsonType
@DatabaseTable(tableName = "Udinspojxxm")
public class Udinspojxxm extends Entity {
    @DatabaseField(generatedId = true)
    @JsonIgnore
    public int id;

    @DatabaseField(columnName = "udinspojxxmid")
    @JsonField(fieldName = "udinspojxxmid")
    public int udinspojxxmid;//唯一ID

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


    @DatabaseField(columnName = "writemethod")
    @JsonField(fieldName = "writemethod")
    public String writemethod;//填写方式


    @DatabaseField(columnName = "udinspojxxm1")
    @JsonField(fieldName = "udinspojxxm1")
    public String udinspojxxm1;//正常异常


    @DatabaseField(columnName = "udinspojxxm7")
    @JsonField(fieldName = "udinspojxxm7")
    public String udinspojxxm7;//检查内容

    @DatabaseField(columnName = "udinspojxxm8")
    @JsonField(fieldName = "udinspojxxm8")
    public String udinspojxxm8;//检查标准


    @DatabaseField(columnName = "udinspojxxm9")
    @JsonField(fieldName = "udinspojxxm9")
    public String udinspojxxm9;//检查标准

    @DatabaseField(columnName = "reportnum")
    @JsonField(fieldName = "reportnum")
    public String reportnum;//

    public String type; //操作类型

    @DatabaseField(columnName = "local")
    @JsonField(fieldName = "local")
    public int local;//本地操作(0:未操作，1:已操作)


    @DatabaseField(columnName = "completion")
    @JsonField(fieldName = "completion")
    public int completion;//完成状态




    public int getLocal() {
        return local;
    }

    public void setLocal(int local) {
        this.local = local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUdinspojxxmid() {
        return udinspojxxmid;
    }

    public void setUdinspojxxmid(int udinspojxxmid) {
        this.udinspojxxmid = udinspojxxmid;
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

    public String getCheckby() {
        return checkby;
    }

    public void setCheckby(String checkby) {
        this.checkby = checkby;
    }

    public String getWritemethod() {
        return writemethod;
    }

    public void setWritemethod(String writemethod) {
        this.writemethod = writemethod;
    }

    public String getUdinspojxxm1() {
        return udinspojxxm1;
    }

    public void setUdinspojxxm1(String udinspojxxm1) {
        this.udinspojxxm1 = udinspojxxm1;
    }

    public String getUdinspojxxm7() {
        return udinspojxxm7;
    }

    public void setUdinspojxxm7(String udinspojxxm7) {
        this.udinspojxxm7 = udinspojxxm7;
    }


    public String getUdinspojxxm8() {
        return udinspojxxm8;
    }

    public void setUdinspojxxm8(String udinspojxxm8) {
        this.udinspojxxm8 = udinspojxxm8;
    }

    public String getUdinspojxxm9() {
        return udinspojxxm9;
    }

    public void setUdinspojxxm9(String udinspojxxm9) {
        this.udinspojxxm9 = udinspojxxm9;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getReportnum() {
        return reportnum;
    }

    public void setReportnum(String reportnum) {
        this.reportnum = reportnum;
    }


    public int getCompletion() {
        return completion;
    }

    public void setCompletion(int completion) {
        this.completion = completion;
    }
}
