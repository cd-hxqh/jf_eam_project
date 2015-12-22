package com.jf_eam_project.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.model.Labtrans;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.model.Wplabor;
import com.jf_eam_project.ui.adapter.LabtransAdapter;
import com.jf_eam_project.ui.adapter.WplaborAdapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by think on 2015/12/22.
 * 工单实际员工列表界面
 */
public class Work_LabtransActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 返回*
     */
    private ImageView backImage;

    /**
     * 菜单按钮*
     */
    private ImageView addimg;
    private WorkOrder workOrder;

    private TextView wonum;
    private TextView status;
    private TextView parent;
    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private SwipeRefreshLayout refresh_layout = null;
    private LabtransAdapter labtransAdapter;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtrans);

        getIntentData();
        findViewById();
        initView();
    }

    private void getIntentData() {
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        addimg = (ImageView) findViewById(R.id.title_add);
        backImage = (ImageView) findViewById(R.id.title_back_id);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        wonum = (TextView) findViewById(R.id.work_wonum);
        status = (TextView) findViewById(R.id.work_status);
        parent = (TextView) findViewById(R.id.work_parent);

    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_work_labtrans);
        addimg.setImageResource(R.drawable.add_ico);
        addimg.setVisibility(View.VISIBLE);
        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        wonum.setText(workOrder.wonum == null ? "" : workOrder.wonum);
        status.setText(workOrder.status == null ? "" : workOrder.status);
        parent.setText(workOrder.parent == null ? "" : workOrder.parent);

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        labtransAdapter = new LabtransAdapter(Work_LabtransActivity.this);
        recyclerView.setAdapter(labtransAdapter);
        refresh_layout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        refresh_layout.setRefreshing(true);
        getdata();

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);
    }

    private void getdata() {
        HttpManager.getDataPagingInfo(Work_LabtransActivity.this, HttpManager.getLabtransUrl(page, 20,workOrder.wonum), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int currentPage, int showcount) {
                ArrayList<Labtrans> labtranses = null;
                if (currentPage == page) {
                    try {
                        labtranses = Ig_Json_Model.parsingLabtrans(results.getResultlist());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                addListData(labtranses);
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
            }

            @Override
            public void onFailure(String error) {
                if (page == 1) {
                    nodatalayout.setVisibility(View.VISIBLE);
                }
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
            }
        });
    }

    private void addListData(ArrayList<Labtrans> list) {
        if (nodatalayout.getVisibility() == View.VISIBLE) {
            nodatalayout.setVisibility(View.GONE);
        }
        if (page == 1 && labtransAdapter.getItemCount() != 0) {
            labtransAdapter = new LabtransAdapter(Work_LabtransActivity.this);
            recyclerView.setAdapter(labtransAdapter);
        }
        if ((list == null || list.size() == 0) && page == 1) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {
            labtransAdapter.adddate(list);
        }
    }


    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        page = 1;
        getdata();
    }

    //上拉加载
    @Override
    public void onLoad() {
        page++;
        getdata();
    }
}