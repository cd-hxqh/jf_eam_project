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
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.ui.adapter.WorkListAdapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;
import com.jf_eam_project.utils.AccountUtils;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by think on 2015/11/20.
 * 工单历史界面
 */
public class Work_History_ListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {

    private static final String TAG = "Work_History_ListActivity";
    @Bind(R.id.title_name)
    TextView titlename; //标题

    @Bind(R.id.title_back_id)
    ImageView backImage;//返回

    private String worktype;

    LinearLayoutManager layoutManager;

    @Bind(R.id.recyclerView_id)
    RecyclerView recyclerView; //recyclerView
    @Bind(R.id.have_not_data_id)
    LinearLayout nodatalayout;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout refresh_layout;
    @Bind(R.id.search_edit)
    EditText search;
    private WorkListAdapter workListAdapter;
    private String searchText = "";
    private int page = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        ButterKnife.bind(this);
        initData();
        findViewById();
        initView();
    }

    //获取界面数据
    private void initData() {
        worktype = getIntent().getStringExtra("worktype");
    }


    @Override
    protected void findViewById() {
    }

    @Override
    protected void initView() {
        setSearchEdit();
        titlename.setText(R.string.title_activity_work_list);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        workListAdapter = new WorkListAdapter(this, 0);
        recyclerView.setAdapter(workListAdapter);
        refresh_layout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        refresh_layout.setRefreshing(true);
        getData(searchText);

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);
    }

    //返回
    @OnClick(R.id.title_back_id)
    void setBackImageOnClickListener() {
        finish();
    }


    private void getData(String search) {

        HttpManager.getDataPagingInfo(this, HttpManager.getWORKORDERHisUrl(search, worktype, AccountUtils.getDepartment(Work_History_ListActivity.this), page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<WorkOrder> items = null;
                if (totalPages != 0 && currentPage != 0) {
                    try {
                        items = Ig_Json_Model.parsingWorkOrder(results.getResultlist());
                        refresh_layout.setRefreshing(false);
                        refresh_layout.setLoading(false);
                        if (items == null || items.isEmpty()) {
                            nodatalayout.setVisibility(View.VISIBLE);
                        } else {
                            if (page == 1) {
                                workListAdapter = new WorkListAdapter(Work_History_ListActivity.this, 0);
                                recyclerView.setAdapter(workListAdapter);
                                nodatalayout.setVisibility(View.GONE);
                            }
                            if (totalPages == page) {
                                workListAdapter.adddate(items);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    refresh_layout.setRefreshing(false);
                    nodatalayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(String error) {
                refresh_layout.setRefreshing(false);
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
                    // 先隐藏键盘
                    ((InputMethodManager) search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    Work_History_ListActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    refresh_layout.setRefreshing(true);
                    searchText = search.getText().toString();
                    workListAdapter = new WorkListAdapter(Work_History_ListActivity.this, 0);
                    recyclerView.setAdapter(workListAdapter);
                    getData(searchText);
                    return true;
                }
                return false;
            }
        });
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        page = 1;
        getData(search.getText().toString());
    }

    //上拉加载
    @Override
    public void onLoad() {
        page++;
        getData(searchText);
    }

}
