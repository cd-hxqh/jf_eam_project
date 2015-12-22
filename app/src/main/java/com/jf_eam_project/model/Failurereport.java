package com.jf_eam_project.model;

/**
 * Created by think on 2015/12/10.
 * 故障汇报
 */
public class Failurereport extends Entity{
    private static final String TAG = "Failurereport";
    private static final long serialVersionUID = 2015050105L;

    public String problem;//问题
    public String cause;//原因
    public String remedy;//补救措施
    public String belongto;//所属工单
}
