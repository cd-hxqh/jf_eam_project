package com.jf_eam_project.model;

/**
 * Created by think on 2015/12/8.
 */
public class Webservice_result extends Entity {
    private static final String TAG = "Assignment";
    private static final long serialVersionUID = 2015050105L;

    public String woNum;
    public String success;
    public int errorNo;

    public String getWoNum() {
        return woNum;
    }

    public void setWoNum(String woNum) {
        this.woNum = woNum;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public int getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(int errorNo) {
        this.errorNo = errorNo;
    }
}
