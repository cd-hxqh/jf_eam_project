package com.jf_eam_project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jf_eam_project.Dao.UdinspoAssetDao;
import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.ui.adapter.UdinspoListNewadapter;
import com.jf_eam_project.ui.adapter.UdinspoassetListAdapter;
import com.jf_eam_project.ui.adapter.UdinspoassetNewListAdapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;
import com.jf_eam_project.utils.MessageUtils;
import com.jf_eam_project.utils.NetWorkHelper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 设备备件
 */
public class Udinspoassetnew_Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static final String TAG = "Udinspoassetnew_Activity";

    /**
     * 标题
     */
    private TextView titleView;
    /**
     * 返回
     */
    private ImageView backImageView;

    /**
     * 新增*
     */
    private ImageView addImageView;
    /**
     * 巡检单
     */
    private String insponum = "";


    LinearLayoutManager layoutManager;
    /**
     * RecyclerView*
     */
    public RecyclerView recyclerView;

    private LinearLayout nodatalayout;
    private SwipeRefreshLayout refresh_layout = null;

    private UdinspoassetNewListAdapter udinspoassetNewListAdapter;
    private EditText search;
    private String searchText = "";
    private int page = 1;


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
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        addImageView = (ImageView) findViewById(R.id.title_add);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        search = (EditText) findViewById(R.id.search_edit);
    }

    @Override
    protected void initView() {
        search.setVisibility(View.GONE);
        titleView.setText(getResources().getString(R.string.udinspoasset_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);
        addImageView.setImageResource(R.drawable.add_ico);
//        addImageView.setVisibility(View.VISIBLE);
        addImageView.setOnClickListener(addImageViewOnClickListener);


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

    }


    private View.OnClickListener addImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            Intent intent = new Intent(Udinspoassetnew_Activity.this, AddUdinspoAssetActivity.class);
            intent.putExtra("insponum", insponum);
            intent.putExtra("udinspoassetlinenum", udinspoassetNewListAdapter.getItemCount());
            startActivityForResult(intent, 0);
        }
    };


    /**
     * 返回点击
     */
    private View.OnClickListener backImageViewOnClickListenrer = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

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
                break;
        }

    }
}
