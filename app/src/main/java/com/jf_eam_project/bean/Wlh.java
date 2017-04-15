package com.jf_eam_project.bean;


import com.instagram.common.json.annotation.JsonType;
import com.jf_eam_project.model.Entity;


/**
 * Created by think on 2015/12/10
 * 领料单选择物料行
 *
 */
@JsonType
public class Wlh extends Entity {


    public String ITEMNUM;//
    public String DESCRIPTION;//
    public String LINETYPE;//
    public String LOCATION;//
    public String RESTYPE;//
    public String ITEMQTY;//
    public String ORDERUNIT;//
    public String UNITCOST;//
    public String LINECOST;//
    public String REQUESTBY;//
    public String REQUIREDATE;//

    public String getITEMNUM() {
        return ITEMNUM;
    }

    public void setITEMNUM(String ITEMNUM) {
        this.ITEMNUM = ITEMNUM;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getLINETYPE() {
        return LINETYPE;
    }

    public void setLINETYPE(String LINETYPE) {
        this.LINETYPE = LINETYPE;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getRESTYPE() {
        return RESTYPE;
    }

    public void setRESTYPE(String RESTYPE) {
        this.RESTYPE = RESTYPE;
    }

    public String getITEMQTY() {
        return ITEMQTY;
    }

    public void setITEMQTY(String ITEMQTY) {
        this.ITEMQTY = ITEMQTY;
    }

    public String getORDERUNIT() {
        return ORDERUNIT;
    }

    public void setORDERUNIT(String ORDERUNIT) {
        this.ORDERUNIT = ORDERUNIT;
    }

    public String getUNITCOST() {
        return UNITCOST;
    }

    public void setUNITCOST(String UNITCOST) {
        this.UNITCOST = UNITCOST;
    }

    public String getLINECOST() {
        return LINECOST;
    }

    public void setLINECOST(String LINECOST) {
        this.LINECOST = LINECOST;
    }

    public String getREQUESTBY() {
        return REQUESTBY;
    }

    public void setREQUESTBY(String REQUESTBY) {
        this.REQUESTBY = REQUESTBY;
    }

    public String getREQUIREDATE() {
        return REQUIREDATE;
    }

    public void setREQUIREDATE(String REQUIREDATE) {
        this.REQUIREDATE = REQUIREDATE;
    }
}
