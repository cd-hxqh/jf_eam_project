package com.jf_eam_project.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by think on 2015/10/28.
 */
public class WorkOrder extends Entity implements Parcelable {
    private static final String TAG = "WorkOrder";
    private static final long serialVersionUID = 2015050105L;

    public String wonum;//工单号
    public String actfinish;//计划完成时间
    public String actstart;//实际开始时间
    public String assetdesc;//设备描述
    public String assetnum;//设备编号
    public String description;//描述
    public String estdur; //剩余时间
    public String jpnum; //作业计划
    public String jpnumdesc;//作业计划描述
    public String location; //位置
    public String locationdesc;//位置描述
    public String onbehalfof; //录入人工号
    public String pmdesc;
    public String pmnum;
    public String reportdate; //汇报日期
    public String status; //状态
    public String statusdesc; //状态描述
    public String udwotype; //工单类型
    public String udwotypedesc; //工单类型描述
    public String worktype; //工作类型


    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        wonum = jsonObject.getString("wonum");
        actfinish = jsonObject.getString("actfinish");
        actstart = jsonObject.getString("actstart");
        assetdesc = jsonObject.getString("assetdesc");
        assetnum = jsonObject.getString("assetnum");
        description = jsonObject.getString("description");
        estdur = jsonObject.getString("estdur");
        jpnum = jsonObject.getString("jpnum");
        jpnumdesc = jsonObject.getString("jpnumdesc");
        location = jsonObject.getString("location");
        locationdesc = jsonObject.getString("locationdesc");
        onbehalfof = jsonObject.getString("onbehalfof");
        pmdesc = jsonObject.getString("pmdesc");
        pmnum = jsonObject.getString("pmnum");
        reportdate = jsonObject.getString("reportdate");
        status = jsonObject.getString("status");
        statusdesc = jsonObject.getString("statusdesc");
        udwotype = jsonObject.getString("udwotype");
        udwotypedesc = jsonObject.getString("udwotypedesc");
        worktype = jsonObject.getString("worktype");

    }

    public WorkOrder() {
    }


    private WorkOrder(Parcel in) {
        wonum = in.readString();
        actfinish = in.readString();
        actstart = in.readString();
        assetdesc = in.readString();
        assetnum = in.readString();
        description = in.readString();
        estdur = in.readString();
        jpnum = in.readString();
        jpnumdesc = in.readString();
        location = in.readString();
        locationdesc = in.readString();
        onbehalfof = in.readString();
        pmdesc = in.readString();
        pmnum = in.readString();
        reportdate = in.readString();
        status = in.readString();
        statusdesc = in.readString();
        udwotype = in.readString();
        udwotypedesc = in.readString();
        worktype = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(wonum);
        dest.writeString(actfinish);
        dest.writeString(actstart);
        dest.writeString(assetdesc);
        dest.writeString(assetnum);
        dest.writeString(description);
        dest.writeString(estdur);
        dest.writeString(jpnum);
        dest.writeString(jpnumdesc);
        dest.writeString(location);
        dest.writeString(locationdesc);
        dest.writeString(onbehalfof);
        dest.writeString(pmdesc);
        dest.writeString(pmnum);
        dest.writeString(reportdate);
        dest.writeString(status);
        dest.writeString(statusdesc);
        dest.writeString(udwotype);
        dest.writeString(udwotypedesc);
        dest.writeString(worktype);

    }

    public static final Creator<WorkOrder> CREATOR = new Creator<WorkOrder>() {
        @Override
        public WorkOrder createFromParcel(Parcel source) {
            return new WorkOrder(source);
        }

        @Override
        public WorkOrder[] newArray(int size) {
            return new WorkOrder[size];
        }
    };
}
