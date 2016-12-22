package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * 风电场年度上网电量
 *
 */
@JsonType
public class Fdcnudlview extends Entity {
    private static final String TAG = "FDCNUDLVIEW";
    private static final long serialVersionUID = 2015050105L;
    @JsonField(fieldName = "BRANCH")
    public String BRANCH;//编号
    @JsonField(fieldName = "FDCDES")
    public String FDCDES;//分公司名称
    @JsonField(fieldName = "SWDL")
    public int SWDL;//上网电量
    @JsonField(fieldName = "UDBELONG")
    public int UDBELONG;//风电场编号
    @JsonField(fieldName = "XDL")
    public String XDL;//限电量
    @JsonField(fieldName = "YEAR")
    public String YEAR;//年


    public String getBRANCH() {
        return BRANCH;
    }

    public void setBRANCH(String BRANCH) {
        this.BRANCH = BRANCH;
    }

    public String getFDCDES() {
        return FDCDES;
    }

    public void setFDCDES(String FDCDES) {
        this.FDCDES = FDCDES;
    }

    public int getSWDL() {
        return SWDL;
    }

    public void setSWDL(int SWDL) {
        this.SWDL = SWDL;
    }

    public int getUDBELONG() {
        return UDBELONG;
    }

    public void setUDBELONG(int UDBELONG) {
        this.UDBELONG = UDBELONG;
    }

    public String getXDL() {
        return XDL;
    }

    public void setXDL(String XDL) {
        this.XDL = XDL;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }
}
