package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 分公司当月单日电量
 *
 */
@JsonType
@DatabaseTable(tableName = "FGSRUDLVIEW")
public class Fgsrudlview extends Entity {
    private static final String TAG = "FGSRUDLVIEW";
    @JsonField(fieldName = "BRANCH")
    public String BRANCH;//编号
    @JsonField(fieldName = "DAY")
    public String DAY;//天
    @JsonField(fieldName = "FGSDES")
    public String FGSDES;//分公司
    @JsonField(fieldName = "MONTH")
    public String MONTH;//月
    @JsonField(fieldName = "SWDL")
    public int SWDL;//上网电量
    @JsonField(fieldName = "XDL")
    public int XDL;//限电量
    @JsonField(fieldName = "YEAR")
    public String YEAR;//年


    public String getBRANCH() {
        return BRANCH;
    }

    public void setBRANCH(String BRANCH) {
        this.BRANCH = BRANCH;
    }

    public String getDAY() {
        return DAY;
    }

    public void setDAY(String DAY) {
        this.DAY = DAY;
    }

    public String getFGSDES() {
        return FGSDES;
    }

    public void setFGSDES(String FGSDES) {
        this.FGSDES = FGSDES;
    }

    public String getMONTH() {
        return MONTH;
    }

    public void setMONTH(String MONTH) {
        this.MONTH = MONTH;
    }

    public int getSWDL() {
        return SWDL;
    }

    public void setSWDL(int SWDL) {
        this.SWDL = SWDL;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }
}
