package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 获取分公司月度
 */
@JsonType
@DatabaseTable(tableName = "FGSYUSSDLVIEW")
public class Fgsyussdlview extends Entity {
    private static final String TAG = "FGSYUSSDLVIEW";
    @JsonField(fieldName = "BRANCH")
    public String BRANCH;//编号
    @JsonField(fieldName = "FGSDES")
    public String FGSDES;//分公司名称
    @JsonField(fieldName = "DWGZ")
    public int DWGZ;//电网故障损失电量
    @JsonField(fieldName = "FJFJH")
    public int FJFJH;//风机非计划损失电量
    @JsonField(fieldName = "FJJH")
    public int FJJH;//风机计划损失电量
    @JsonField(fieldName = "SBDFJH")
    public int SBDFJH;//输变电非计划损失电量
    @JsonField(fieldName = "SBDJH")
    public int SBDJH;//输变电计划损失电量
    @JsonField(fieldName = "TOTAL")
    public int TOTAL;//合计
    @JsonField(fieldName = "YEAR")
    public String YEAR;//年
    @JsonField(fieldName = "MONTH")
    public String MONTH;//月
    @JsonField(fieldName = "ZRZH")
    public int ZRZH;//自然灾害损失电量


    public String getMONTH() {
        return MONTH;
    }

    public void setMONTH(String MONTH) {
        this.MONTH = MONTH;
    }

    public String getBRANCH() {
        return BRANCH;
    }

    public void setBRANCH(String BRANCH) {
        this.BRANCH = BRANCH;
    }

    public String getFGSDES() {
        return FGSDES;
    }

    public void setFGSDES(String FGSDES) {
        this.FGSDES = FGSDES;
    }

    public int getDWGZ() {
        return DWGZ;
    }

    public void setDWGZ(int DWGZ) {
        this.DWGZ = DWGZ;
    }

    public int getFJFJH() {
        return FJFJH;
    }

    public void setFJFJH(int FJFJH) {
        this.FJFJH = FJFJH;
    }

    public int getFJJH() {
        return FJJH;
    }

    public void setFJJH(int FJJH) {
        this.FJJH = FJJH;
    }

    public int getSBDFJH() {
        return SBDFJH;
    }

    public void setSBDFJH(int SBDFJH) {
        this.SBDFJH = SBDFJH;
    }

    public int getSBDJH() {
        return SBDJH;
    }

    public void setSBDJH(int SBDJH) {
        this.SBDJH = SBDJH;
    }

    public int getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(int TOTAL) {
        this.TOTAL = TOTAL;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public int getZRZH() {
        return ZRZH;
    }

    public void setZRZH(int ZRZH) {
        this.ZRZH = ZRZH;
    }
}
