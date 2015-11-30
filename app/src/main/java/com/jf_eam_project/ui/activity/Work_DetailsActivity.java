package com.jf_eam_project.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.WorkOrder;

/**
 * Created by think on 2015/11/27.
 * 工单详情页面
 */
public class Work_DetailsActivity extends BaseActivity {
    private WorkOrder workOrder;

    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 菜单按钮*
     */
    private ImageView menuImageView;
    /**
     * 返回*
     */
    private ImageView backImageView;
    private TextView wonum;//工单号
    private TextView actfinish;//实际完成时间
    private TextView actstart;//实际开始时间
    private TextView assetdesc;//设备描述
    private TextView assetnum;//设备编号
    private TextView description;//描述
    private TextView estdur; //剩余时间
    private TextView jpnum; //作业计划
    private TextView jpnumdesc;//作业计划描述
    private TextView location; //位置
    private TextView locationdesc;//位置描述
    private TextView onbehalfof; //录入人工号
    private TextView pmdesc;
    private TextView pmnum;
    private TextView reportdate; //汇报日期
    private TextView status; //状态
    private TextView statusdesc; //状态描述
    private TextView udwotype; //工单类型
    private TextView udwotypedesc; //工单类型描述
    private TextView worktype; //工作类型

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workdetails);

        geiIntentData();
        findViewById();
        initView();
    }

    /**
     * 获取数据*
     */
    private void geiIntentData() {
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        wonum = (TextView) findViewById(R.id.work_wonum);
        actfinish = (TextView) findViewById(R.id.work_actfinish);
        actstart = (TextView) findViewById(R.id.work_acstart);
        assetnum = (TextView) findViewById(R.id.work_assetnum);
        assetdesc = (TextView) findViewById(R.id.work_assetdesc);
        description = (TextView) findViewById(R.id.work_desc);
        estdur = (TextView) findViewById(R.id.work_estdur);
        jpnum = (TextView) findViewById(R.id.work_jpnum);
        jpnumdesc = (TextView) findViewById(R.id.work_jpnumdesc);
        location = (TextView) findViewById(R.id.work_location);
        locationdesc = (TextView) findViewById(R.id.work_locationdesc);
        onbehalfof = (TextView) findViewById(R.id.work_onbehalfof);
        pmnum = (TextView) findViewById(R.id.work_pmnum);
        pmdesc = (TextView) findViewById(R.id.work_pmdesc);
        reportdate = (TextView) findViewById(R.id.work_reportdate);
        status = (TextView) findViewById(R.id.work_status);
        statusdesc = (TextView) findViewById(R.id.work_statusdesc);
        udwotype = (TextView) findViewById(R.id.work_udwotype);
        worktype = (TextView) findViewById(R.id.work_worktype);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_work_details);
        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        wonum.setText(workOrder.getWonum());
        actstart.setText(workOrder.getActstart());
        actfinish.setText(workOrder.getActfinish());
        assetnum.setText(workOrder.getAssetnum());
        assetdesc.setText(workOrder.getAssetdesc());
        description.setText(workOrder.getDescription());
        estdur.setText(workOrder.getEstdur());
        jpnum.setText(workOrder.getJpnum());
        jpnumdesc.setText(workOrder.getJpnumdesc());
        location.setText(workOrder.getLocation());
        locationdesc.setText(workOrder.getLocationdesc());
        onbehalfof.setText(workOrder.getOnbehalfof());
        pmnum.setText(workOrder.getPmnum());
        pmdesc.setText(workOrder.getPmdesc());
        reportdate.setText(workOrder.getReportdate());
        status.setText(workOrder.getStatus());
        statusdesc.setText(workOrder.getStatusdesc());
        udwotype.setText(workOrder.getUdwotype());
        worktype.setText(workOrder.getWorktype());
    }
}
