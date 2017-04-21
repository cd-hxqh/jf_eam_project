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
import com.jf_eam_project.model.Udinspo;
import com.jf_eam_project.ui.adapter.UdinspoListNewadapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;
import com.jf_eam_project.utils.AccountUtils;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 巡检单列表
 */
public class Udinspo_Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {

    private static final String TAG = "Udinspo_Activity";


    @Bind(R.id.title_name)
    TextView titlename; //标题
    @Bind(R.id.title_back_id)
    ImageView backImageView;//返回按钮


    LinearLayoutManager layoutManager;


    @Bind(R.id.recyclerView_id)
    RecyclerView recyclerView; //RecyclerView
    /**
     * 暂无数据*
     */

    @Bind(R.id.have_not_data_id)
    LinearLayout nodatalayout;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout refresh_layout; //界面刷新
    /**
     * 适配器*
     */
    private UdinspoListNewadapter udinspoListNewadapter;

    @Bind(R.id.search_edit)
    EditText search; //编辑框
    /**
     * 查询条件*
     */
    private String searchText = "";
    private int page = 1;

    /**
     * 获取巡检单标题*
     */
    private String title;
    /**
     * 巡检单类型*
     */
    private String inspotype;
    /**
     * assettype*
     */
    private String assettype;
    /**
     * checktype*
     */
    private String checktype;


    ArrayList<Udinspo> list = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.udinspo_activity);
        ButterKnife.bind(this);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取巡检单*
     */
    private void initData() {
        title = getIntent().getStringExtra("title");
        inspotype = getIntent().getStringExtra("inspotype");
        if (inspotype.equals("05")) {
            assettype = getIntent().getStringExtra("assettype");
            checktype = getIntent().getStringExtra("checktype");
        }
    }

    @Override
    protected void findViewById() {

    }

    @Override
    protected void initView() {
        setSearchEdit();
        titlename.setText(getString(R.string.online_text));

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        udinspoListNewadapter = new UdinspoListNewadapter(this);
        recyclerView.setAdapter(udinspoListNewadapter);


        refresh_layout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        refresh_layout.setRefreshing(true);
        getData(searchText);
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);

    }

    //返回事件
    @OnClick(R.id.title_back_id)
    void setBackImageViewOnClickListener() {
        finish();
    }

    //待执行任务
    @OnClick(R.id.wait_operating_task_id)void setWaitOperatingOnClickListener(){
        Intent intent = getIntent();
        intent.setClass(Udinspo_Activity.this, UdinspoLocation_Activity.class);
        startActivityForResult(intent, 0);
    }


    @Override
    public void onLoad() {
        page++;

        getData(searchText);
    }

    @Override
    public void onRefresh() {
        page = 1;
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
                                    Udinspo_Activity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString();
                    udinspoListNewadapter.removeAllData();
                    list = new ArrayList<Udinspo>();
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
            url = HttpManager.getUdinspourl1(search, page, 20);
        } else {
            String[] depatments = separatedString(AccountUtils.getDepartment(Udinspo_Activity.this));
            String department = "";
            if (depatments.length == 1) {
                department = depatments[0];
            } else {
                for (String s : depatments) {
                    department += s + ",=";
                }
            }
            url = HttpManager.getUdinspourl1(inspotype, assettype, checktype, department, search, page, 20);
        }
        HttpManager.getDataPagingInfo(this, url, new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {


                try {

                    list = Ig_Json_Model.parseUdinspoString(results.getResultlist());
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    if (list == null || list.isEmpty()) {
                        nodatalayout.setVisibility(View.VISIBLE);
                    } else {
                        nodatalayout.setVisibility(View.GONE);
                        if (page == 1) {
                            udinspoListNewadapter = new UdinspoListNewadapter(Udinspo_Activity.this);
                            recyclerView.setAdapter(udinspoListNewadapter);
                        }
                        udinspoListNewadapter.adddate(list);
                        udinspoListNewadapter.notifyDataSetChanged();

                    }

                } catch (IOException e) {
                    e.printStackTrace();
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


}
