package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/25.
 * 故障统计
 *
 */
@JsonType
public class FJDQ20VIEW extends Entity {
    private static final String TAG = "FJDQ20VIEW";
    private static final long serialVersionUID = 2015050105L;
    @JsonField(fieldName = "apptype")
    public String apptype;//类型
    @JsonField(fieldName = "assettype")
    public String assettype;//类型
    @JsonField(fieldName = "branch")
    public String branch;//分公司
    @JsonField(fieldName = "createdate")
    public String createdate;//提报时间
    @JsonField(fieldName = "culevel")
    public String culevel;//等级
    @JsonField(fieldName = "description")
    public String description;//描述
    @JsonField(fieldName = "reportnum")
    public String reportnum; //编号
    @JsonField(fieldName = "statustype")
    public String statustype; //状态
    @JsonField(fieldName = "udbelong")
    public String udbelong; //风电场


    public String getApptype() {
        return apptype;
    }

    public void setApptype(String apptype) {
        this.apptype = apptype;
    }

    public String getAssettype() {
        return assettype;
    }

    public void setAssettype(String assettype) {
        this.assettype = assettype;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getCulevel() {
        return culevel;
    }

    public void setCulevel(String culevel) {
        this.culevel = culevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReportnum() {
        return reportnum;
    }

    public void setReportnum(String reportnum) {
        this.reportnum = reportnum;
    }

    public String getStatustype() {
        return statustype;
    }

    public void setStatustype(String statustype) {
        this.statustype = statustype;
    }

    public String getUdbelong() {
        return udbelong;
    }

    public void setUdbelong(String udbelong) {
        this.udbelong = udbelong;
    }
}
