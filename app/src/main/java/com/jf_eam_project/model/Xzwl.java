package com.jf_eam_project.model;


import com.instagram.common.json.annotation.JsonType;


/**
 * Created by think on 2015/12/10
 * 选择物料
 *
 */
@JsonType
public class Xzwl extends Entity {


    public String COSTTYPE;//
    public String CURBAL;//余量
    public String INVENTORYID;//项目
    public String ISSUEUNIT;//发放单位
    public String ITEMDESC;//项目描述
    public String ITEMNUM;//项目
    public String LOCATION;//录入时间
    public String LOCATIONDESC;//库房描述
    public String ITEMLOTTYPE;//批次类型
    public String SITEID;//地点
    public String STATUS;//状态

    public String getCOSTTYPE() {
        return COSTTYPE;
    }

    public void setCOSTTYPE(String COSTTYPE) {
        this.COSTTYPE = COSTTYPE;
    }

    public String getCURBAL() {
        return CURBAL;
    }

    public void setCURBAL(String CURBAL) {
        this.CURBAL = CURBAL;
    }

    public String getINVENTORYID() {
        return INVENTORYID;
    }

    public void setINVENTORYID(String INVENTORYID) {
        this.INVENTORYID = INVENTORYID;
    }

    public String getISSUEUNIT() {
        return ISSUEUNIT;
    }

    public void setISSUEUNIT(String ISSUEUNIT) {
        this.ISSUEUNIT = ISSUEUNIT;
    }

    public String getITEMDESC() {
        return ITEMDESC;
    }

    public void setITEMDESC(String ITEMDESC) {
        this.ITEMDESC = ITEMDESC;
    }

    public String getITEMNUM() {
        return ITEMNUM;
    }

    public void setITEMNUM(String ITEMNUM) {
        this.ITEMNUM = ITEMNUM;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getLOCATIONDESC() {
        return LOCATIONDESC;
    }

    public void setLOCATIONDESC(String LOCATIONDESC) {
        this.LOCATIONDESC = LOCATIONDESC;
    }

    public String getITEMLOTTYPE() {
        return ITEMLOTTYPE;
    }

    public void setITEMLOTTYPE(String ITEMLOTTYPE) {
        this.ITEMLOTTYPE = ITEMLOTTYPE;
    }

    public String getSITEID() {
        return SITEID;
    }

    public void setSITEID(String SITEID) {
        this.SITEID = SITEID;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
}
