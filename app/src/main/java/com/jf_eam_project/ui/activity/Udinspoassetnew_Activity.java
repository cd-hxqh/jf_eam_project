package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jf_eam_project.Dao.UdinspoAssetDao;
import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.ui.adapter.UdinspoassetNewListAdapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设备备件
 */
public class Udinspoassetnew_Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static final String TAG = "Udinspoassetnew_Activity";

    @Bind(R.id.title_name)
    TextView titleView;//标题
    @Bind(R.id.title_back_id)
    ImageView backImageView;//返回


    /**
     * 巡检单
     */
    private String insponum = "";


    LinearLayoutManager layoutManager;

    @Bind(R.id.recyclerView_id)
    RecyclerView recyclerView;
    @Bind(R.id.have_not_data_id)
    LinearLayout nodatalayout;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout refresh_layout;

    private UdinspoassetNewListAdapter udinspoassetNewListAdapter;


    /**
     * 分公司*
     */
    private String branch;
    /**
     * 运行单位*
     */
    private String udbelong;
    /**
     * 类型*
     */
    private String assettype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        ButterKnife.bind(this);
        initData();
        findViewById();
        initView();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        insponum = getIntent().getExtras().getString("insponum");
        branch = getIntent().getExtras().getString("branch");
        udbelong = getIntent().getExtras().getString("udbelong");
        assettype = getIntent().getExtras().getString("assettype");

    }

    @Override
    protected void findViewById() {
    }

    @Override
    protected void initView() {
        titleView.setText(getResources().getString(R.string.udinspoasset_title));


        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        udinspoassetNewListAdapter = new UdinspoassetNewListAdapter(this);
        recyclerView.setAdapter(udinspoassetNewListAdapter);
        udinspoassetNewListAdapter.setData(branch, udbelong, assettype);
        refresh_layout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        refresh_layout.setRefreshing(true);
        getLocalData();
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);

    }

    /**
     * 获取本地数据*
     */
    private void getLocalData() {

        ArrayList<Udinspoasset> list = (ArrayList<Udinspoasset>) new UdinspoAssetDao(Udinspoassetnew_Activity.this).queryByInsponum(insponum);
        refresh_layout.setRefreshing(false);
        refresh_layout.setLoading(false);
        if (list == null || list.isEmpty()) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {
            udinspoassetNewListAdapter.setData(branch, udbelong, assettype);
            udinspoassetNewListAdapter.adddate(list);
        }


        udinspoassetNewListAdapter.setOnClickListener(new UdinspoassetNewListAdapter.OnClickListener() {
            @Override
            public void cOnClickListener(Udinspoasset udinspoasset) {
                Intent intent = new Intent();
                intent.setClass(Udinspoassetnew_Activity.this, UdinspojxxmNew_Activity.class);
                intent.putExtra("udinspoassetnum", udinspoasset.udinspoassetnum);
                intent.putExtra("branch", branch);
                intent.putExtra("udbelong", udbelong);
                intent.putExtra("assettype", assettype);
                startActivityForResult(intent, 0);
            }
        });
    }


    /**
     * 返回点击
     */

    @OnClick(R.id.title_back_id)
    void setBackImageViewOnClickListenrer() {
        finish();
    }

    @Override
    public void onLoad() {

        refresh_layout.setRefreshing(false);
        refresh_layout.setLoading(false);
    }

    @Override
    public void onRefresh() {

        refresh_layout.setRefreshing(false);
        refresh_layout.setLoading(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Constants.REFRESH:
                udinspoassetNewListAdapter.removeAllData();
                getLocalData();
                udinspoassetNewListAdapter.notifyDataSetChanged();
                break;
        }

    }
}
