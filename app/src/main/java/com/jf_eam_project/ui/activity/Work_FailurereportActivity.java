package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.model.Assignment;
import com.jf_eam_project.model.Failurereport;
import com.jf_eam_project.model.WorkOrder;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by think on 2015/12/10.
 * 故障报告页面
 */
public class Work_FailurereportActivity extends BaseActivity {
    /**标题**/
    private TextView titlename;
    /**返回**/
    private ImageView backImage;

    private TextView failurecode;//故障类
    private TextView faildate;//故障日期
    private TextView remarkdesc;//备注
    private TextView remarkenterdate;//备注日期
    private TextView problem;//问题
    private TextView cause;//原因
    private TextView remedy;//措施

    private WorkOrder workOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_failurereport);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
    }
    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImage = (ImageView) findViewById(R.id.title_back_id);

        failurecode = (TextView) findViewById(R.id.failurereport_failurecode);
        faildate = (TextView) findViewById(R.id.failurereport_faildate);
        remarkdesc = (TextView) findViewById(R.id.failurereport_remarkdesc);
        remarkenterdate = (TextView) findViewById(R.id.failurereport_remarkenterdate);
        problem = (TextView) findViewById(R.id.failurereport_problem);
        cause = (TextView) findViewById(R.id.failurereport_cause);
        remedy = (TextView) findViewById(R.id.failurereport_remedy);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_work_failurereport);

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getdata();
    }

    private void getdata() {
        HttpManager.getDataPagingInfo(Work_FailurereportActivity.this, HttpManager.getFailurereportUrl(workOrder.wonum, 1, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int currentPage, int showcount) {
                Failurereport failurereport = new Failurereport();
                try {
                    failurereport = Ig_Json_Model.parsingFailurereport(results.getResultlist()).get(0);
                    problem.setText(failurereport.problem);
                    cause.setText(failurereport.cause);
                    remedy.setText(failurereport.remedy);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    private void addListData(ArrayList<Assignment> list) {

    }
}
