package com.jf_eam_project.model;

/**
 * Created by think on 2015/12/8.
 */
public class Webservice_result extends Entity {
    private static final String TAG = "Assignment";
    private static final long serialVersionUID = 2015050105L;

    private String woNum;
    private String errorMsg;
    private String errorNo;

    public String getWoNum() {
        return woNum;
    }

    public void setWoNum(String woNum) {
        this.woNum = woNum;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(String errorNo) {
        this.errorNo = errorNo;
    }
}
