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
import com.jf_eam_project.model.Uditemreqline;
import com.jf_eam_project.ui.adapter.UditemreqlineListAdapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by think on 2015/11/20.
 * 物资编码申请行表
 */
public class UditemreqLine_details_activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {

    private static final String TAG="UditemreqLine_listactivity";
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
    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private SwipeRefreshLayout refresh_layout = null;
    private UditemreqlineListAdapter uditemreqlineListAdapter;
    private EditText search;
    private String searchText = "";
    private int page = 1;

    /**物资申请编号**/
    private String uditemreqnum;
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
        uditemreqnum = getIntent().getExtras().getString("uditemreqnum");

        Log.i(TAG,"uditemreqnum="+uditemreqnum);
    }


    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImage = (ImageView) findViewById(R.id.title_back_id);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        search = (EditText) findViewById(R.id.search_edit);
    }

    @Override
    protected void initView() {
        setSearchEdit();
        titlename.setText(getString(R.string.uditemreq_title_text));


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
        uditemreqlineListAdapter = new UditemreqlineListAdapter(this);
        recyclerView.setAdapter(uditemreqlineListAdapter);
        refresh_layout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        refresh_layout.setRefreshing(true);
        getData(searchText);

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);
    }

    private void getData(String search) {
        HttpManager.getDataPagingInfo(this, HttpManager.getUditemreqlineUrl(search,uditemreqnum, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<Uditemreqline> items = null;
                if (totalPages != 0 && currentPage != 0) {
                    try {
                        items = Ig_Json_Model.parsingUditemreqline(results.getResultlist());
                        refresh_layout.setRefreshing(false);
                        refresh_layout.setLoading(false);
                        if (items == null || items.isEmpty()) {
                            nodatalayout.setVisibility(View.VISIBLE);
                        } else {
                            if (page == 1) {
                                uditemreqlineListAdapter = new UditemreqlineListAdapter(UditemreqLine_details_activity.this);
                                recyclerView.setAdapter(uditemreqlineListAdapter);
                                nodatalayout.setVisibility(View.GONE);
                            }
                            if (totalPages == page) {
                                uditemreqlineListAdapter.adddate(items);
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
                                    UditemreqLine_details_activity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    refresh_layout.setRefreshing(true);
                    searchText = search.getText().toString();
                    uditemreqlineListAdapter = new UditemreqlineListAdapter(UditemreqLine_details_activity.this);
                    recyclerView.setAdapter(uditemreqlineListAdapter);
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
