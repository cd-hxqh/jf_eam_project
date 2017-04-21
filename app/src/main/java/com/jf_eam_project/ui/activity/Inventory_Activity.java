package com.jf_eam_project.ui.activity;

import android.content.Context;
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

import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.model.Inventory;
import com.jf_eam_project.ui.adapter.InventoryListAdapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 库存记录Acitivity*
 */
public class Inventory_Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {


    @Bind(R.id.title_name)
    TextView titlename;//标题
    @Bind(R.id.title_back_id)
    ImageView backImageView;//返回按钮


    LinearLayoutManager layoutManager;


    @Bind(R.id.recyclerView_id)
    RecyclerView recyclerView;//RecyclerView
    @Bind(R.id.have_not_data_id)
    LinearLayout nodatalayout; //暂无数据

    @Bind(R.id.swipe_container)
    SwipeRefreshLayout refresh_layout = null;//界面刷新
    /**
     * 适配器*
     */
    private InventoryListAdapter inventoryListAdapter;

    @Bind(R.id.search_edit)
    EditText search; //编辑框
    /**
     * 查询条件*
     */
    private String searchText = "";
    private int page = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        ButterKnife.bind(this);
        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
    }

    @Override
    protected void initView() {
        setSearchEdit();
        titlename.setText(getString(R.string.inventory_title_1));
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        inventoryListAdapter = new InventoryListAdapter(this);
        recyclerView.setAdapter(inventoryListAdapter);
        refresh_layout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        refresh_layout.setRefreshing(true);
        getData(searchText);

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);
    }

    @OnClick(R.id.title_back_id)
    void setBackImageViewOnClickListener() {
        finish();
    }


    @Override
    public void onLoad() {
        page = 1;

        getData(searchText);
    }

    @Override
    public void onRefresh() {
        page++;
        getData(searchText);
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
                    // 先隐藏键盘
                    ((InputMethodManager) search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    Inventory_Activity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString();
                    inventoryListAdapter.removeAllData();
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
     * 获取数据*
     */
    private void getData(String search) {

        String url = "";
        if (isCount(search)) {
            url = HttpManager.getInventory1Url(search, page, 20);
        } else {

            url = HttpManager.getInventoryUrl(search, page, 20);
        }


        HttpManager.getDataPagingInfo(this, url, new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {


                ArrayList<Inventory> items = null;
                try {
                    items = Ig_Json_Model.parseFromInventoryString(results.getResultlist());
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    if (items == null || items.isEmpty()) {
                        nodatalayout.setVisibility(View.VISIBLE);
                    } else {
                        if (page == 1) {
                            inventoryListAdapter = new InventoryListAdapter(Inventory_Activity.this);
                            recyclerView.setAdapter(inventoryListAdapter);
                        }
                        if (totalPages == page) {
                            inventoryListAdapter.adddate(items);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(String error) {
                refresh_layout.setRefreshing(false);
                nodatalayout.setVisibility(View.VISIBLE);
            }
        });
    }
}
