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

import com.jf_eam_project.Dao.UdreportDao;
import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.model.Udreport;
import com.jf_eam_project.ui.adapter.UdreportListAdapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;
import com.jf_eam_project.utils.AccountUtils;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提报单历史列表
 */
public class UdreportHis_Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {

    private static final String TAG = "UdreportHis_Activity";

    @Bind(R.id.title_name)
    TextView titlename; //标题
    @Bind(R.id.title_back_id)
    ImageView backImageView; //返回按钮

    LinearLayoutManager layoutManager;


    @Bind(R.id.recyclerView_id)
    RecyclerView recyclerView; //RecyclerView

    @Bind(R.id.have_not_data_id)
    LinearLayout nodatalayout; //暂无数据
    /**
     * 界面刷新*
     */
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout refresh_layout;

    @Bind(R.id.search_edit)
    EditText search; //编辑框
    /**
     * 适配器*
     */
    private UdreportListAdapter udreportListAdapter;


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
     * 提报单类型*
     */
    private String apptype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udreport_list);
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
        apptype = getIntent().getStringExtra("apptype");
    }

    @Override
    protected void findViewById() {

    }

    @Override
    protected void initView() {
        setSearchEdit();


        titlename.setText(title);


        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        /**在线**/
        udreportListAdapter = new UdreportListAdapter(this);
        udreportListAdapter.setIsShow(0);
        recyclerView.setAdapter(udreportListAdapter);
        refresh_layout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        refresh_layout.setRefreshing(true);

        getData(searchText);

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);


    }


    private void startActivity(Udreport udreport) {
        Intent intent = new Intent(UdreportHis_Activity.this, Udreport_Details_Activity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("mark", 0);
        bundle.putSerializable("udreport", udreport);
        intent.putExtras(bundle);
        startActivityForResult(intent, 0);
    }


    //点击返回
    @OnClick(R.id.title_back_id)
    void setBackImageViewOnClickListener() {
        finish();
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
                                    UdreportHis_Activity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString();
                    udreportListAdapter.removeAllData();
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
        String statustype = "=已消除";
        HttpManager.getDataPagingInfo(this, HttpManager.getUdreport(apptype, AccountUtils.getDepartment(UdreportHis_Activity.this), statustype, search, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {


                ArrayList<Udreport> items = null;
                try {
                    items = Ig_Json_Model.parsingUdreport(results.getResultlist());
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    if (items == null || items.isEmpty()) {
                        nodatalayout.setVisibility(View.VISIBLE);
                    } else {
                        if (page == 1) {
                            udreportListAdapter = new UdreportListAdapter(UdreportHis_Activity.this);
                            recyclerView.setAdapter(udreportListAdapter);
                        }
                        if (totalPages == page) {
                            new UdreportDao(UdreportHis_Activity.this).create(items);
                            udreportListAdapter.adddate(items);
                        }


                        /**点击事件**/
                        udreportListAdapter.setOnClickListener(new UdreportListAdapter.OnClickListener() {
                            @Override
                            public void cOnClickListener(int postion, Udreport udreport) {

                                startActivity(udreport);


                            }
                        });
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
