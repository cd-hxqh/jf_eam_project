package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 获取分公司上网电量
 *
 */
@JsonType
@DatabaseTable(tableName = "FGSNUDLVIEW")
public class Fgsnudlview extends Entity {
    private static final String TAG = "FGSNUDLVIEW";
    private static final long serialVersionUID = 2015050105L;
    @JsonField(fieldName = "BRANCH")
    public String BRANCH;//编号
    @JsonField(fieldName = "FGSDES")
    public String FGSDES;//分公司名称
    @JsonField(fieldName = "SWDL")
    public int SWDL;//上网电量
    @JsonField(fieldName = "XDL")
    public int XDL;//限电量
    @JsonField(fieldName = "YEAR")
    public String YEAR;//年

    public int getXDL() {
        return XDL;
    }

    public void setXDL(int XDL) {
        this.XDL = XDL;
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
