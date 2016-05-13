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
import com.jf_eam_project.Dao.UdinspojxxmDao;
import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.model.Udinspojxxm;
import com.jf_eam_project.ui.adapter.UdinspoassetListAdapter;
import com.jf_eam_project.ui.adapter.UdinspojxxmListAdapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;
import com.jf_eam_project.utils.MessageUtils;
import com.jf_eam_project.utils.NetWorkHelper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 检修项目标准
 */
public class Udinspojxxm_Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static final String TAG = "Udinspojxxm_Activity";

    /**
     * 标题
     */
    private TextView titleView;
    /**
     * 返回
     */
    private ImageView backImageView;
    /**
     * 历史数据*
     */
    private ImageView addImageView;


    /**
     * 设备编号
     */
    private String udinspoassetnum;


    LinearLayoutManager layoutManager;
    /**
     * RecyclerView*
     */
    public RecyclerView recyclerView;

    private LinearLayout nodatalayout;
    private SwipeRefreshLayout refresh_layout = null;

    private UdinspojxxmListAdapter udinspojxxmListAdapter;
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
    /**巡检类型**/
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
        udinspoassetnum = getIntent().getExtras().getString("udinspoassetnum");
        branch = getIntent().getExtras().getString("branch");
        udbelong = getIntent().getExtras().getString("udbelong");
        assettype = getIntent().getExtras().getString("assettype");
        Log.i(TAG,"assettype="+assettype);
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
        titleView.setText(getResources().getString(R.string.udinspojxxm_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);

        addImageView.setImageResource(R.drawable.ic_menu_recent_history);
        addImageView.setVisibility(View.VISIBLE);
        addImageView.setOnClickListener(addImageViewOnClickListener);

        setSearchEdit();


        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        udinspojxxmListAdapter = new UdinspojxxmListAdapter(this, 0);
        recyclerView.setAdapter(udinspojxxmListAdapter);
        udinspojxxmListAdapter.setData(branch,udbelong,assettype);
        refresh_layout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        refresh_layout.setRefreshing(true);
        if (NetWorkHelper.isNetwork(Udinspojxxm_Activity.this)) {//没网
            MessageUtils.showMiddleToast(Udinspojxxm_Activity.this, "世界上最遥远的距离就是没网。检查设置");
            getLocalData();
        } else {
            getData(searchText);
        }

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);

    }

    /**
     * 获取本地数据*
     */
    private void getLocalData() {

        ArrayList<Udinspojxxm> list = (ArrayList<Udinspojxxm>) new UdinspojxxmDao(Udinspojxxm_Activity.this).queryByUdinspoassetnum(udinspoassetnum);
        refresh_layout.setRefreshing(false);
        refresh_layout.setLoading(false);
        if (list == null || list.isEmpty()) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {

            udinspojxxmListAdapter.adddate(list);
        }

    }



    private void getData(String search) {
        HttpManager.getDataPagingInfo(this, HttpManager.getUdinspojxxmUrl(udinspoassetnum, search, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<Udinspojxxm> items = null;
                try {
                    items = Ig_Json_Model.parseUdinspojxxmString(results.getResultlist());
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    if (items == null || items.isEmpty()) {
                        nodatalayout.setVisibility(View.VISIBLE);
                    } else {
                        if (page == 1) {
                            udinspojxxmListAdapter = new UdinspojxxmListAdapter(Udinspojxxm_Activity.this, 0);
                            udinspojxxmListAdapter.setData(branch,udbelong,assettype);
                            recyclerView.setAdapter(udinspojxxmListAdapter);
                        }
                        if (totalPages == page) {
                            new UdinspojxxmDao(Udinspojxxm_Activity.this).create(items);
                            udinspojxxmListAdapter.adddate(items);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String error) {
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
                nodatalayout.setVisibility(View.VISIBLE);
            }
        });
    }


    private void setSearchEdit() {

        SpannableString msp = new SpannableString("XX搜索");
        Drawable drawable = getResources().getDrawable(R.drawable.ic_search);
        msp.setSpan(new ImageSpan(drawable), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        search.setHint(msp);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 隐藏软件盘
                    ((InputMethodManager) search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    Udinspojxxm_Activity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString();
                    udinspojxxmListAdapter.removeAllData();
                    nodatalayout.setVisibility(View.GONE);
                    refresh_layout.setRefreshing(true);
                    page = 1;
                    getData(searchText);
                    return true;
                }
                return false;
            }
        });
    }

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
        page = 1;

        if (!NetWorkHelper.isNetwork(Udinspojxxm_Activity.this)) { //没有网络
            getData(searchText);
        } else {
            refresh_layout.setRefreshing(false);
            refresh_layout.setLoading(false);
        }
    }

    @Override
    public void onRefresh() {
        page++;
        if (!NetWorkHelper.isNetwork(Udinspojxxm_Activity.this)) { //没有网络
            getData(searchText);
        } else {
            refresh_layout.setRefreshing(false);
            refresh_layout.setLoading(false);
        }
    }


    private View.OnClickListener addImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            Intent intent = new Intent(Udinspojxxm_Activity.this, Udinspojxxm_History_Activity.class);
            intent.putExtra("udinspoassetnum", udinspoassetnum);
            startActivityForResult(intent, 0);
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "requestCode=" + requestCode + "resultCode=" + resultCode);
        switch (resultCode) {

            case Constants.REFRESH:
                udinspojxxmListAdapter.removeAllData();
                getData(searchText);
                break;
        }

    }
}
