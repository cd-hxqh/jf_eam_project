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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.model.Udinspojxxm;
import com.jf_eam_project.ui.adapter.UdinspoassetListAdapter;
import com.jf_eam_project.ui.adapter.UdinspojxxmListAdapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 检修项目标准
 */
public class Add_Udinspojxxm_Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static final String TAG = "Add_Udinspojxxm_Activity";

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
     * 确认*
     */
    private Button affirmBtn;



    ArrayList<Udinspojxxm> uditems=new ArrayList<Udinspojxxm>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udinspoasset);

        initData();
        findViewById();
        initView();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        udinspoassetnum = getIntent().getExtras().getString("udinspoassetnum");
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


        affirmBtn = (Button) findViewById(R.id.submit_btn_id);
    }

    @Override
    protected void initView() {
        titleView.setText(getResources().getString(R.string.udinspojxxm_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);
        addImageView.setVisibility(View.VISIBLE);
        addImageView.setImageResource(R.drawable.add_ico);
        setSearchEdit();


        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        udinspojxxmListAdapter = new UdinspojxxmListAdapter(this,0);
        recyclerView.setAdapter(udinspojxxmListAdapter);
        refresh_layout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        refresh_layout.setRefreshing(true);
        getData(searchText);

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);

        addImageView.setOnClickListener(addImageViewOnClickListener);

        affirmBtn.setOnClickListener(affirmBtnOnClickListener);
    }


    private View.OnClickListener affirmBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {



            Intent intent = getIntent();
            intent.putExtra("udinspojxxms", (Serializable) uditems);
            setResult(1, intent);
            finish();
        }
    };











    private void getData(String search) {
        HttpManager.getDataPagingInfo(this, HttpManager.getUdinspojxxmUrl(udinspoassetnum, search, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
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
                            udinspojxxmListAdapter = new UdinspojxxmListAdapter(Add_Udinspojxxm_Activity.this,0);
                            recyclerView.setAdapter(udinspojxxmListAdapter);
                        }
                        if (totalPages == page) {
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
                                    Add_Udinspojxxm_Activity.this.getCurrentFocus()
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


    private View.OnClickListener addImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(Add_Udinspojxxm_Activity.this, AddUdinspojxxmActivity.class);
            intent.putExtra("udinspoassetlinenum", udinspojxxmListAdapter.getItemCount());
            intent.putExtra("udinspoassetnum", udinspoassetnum);
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
        page = 1;

        getData(searchText);
    }

    @Override
    public void onRefresh() {
        page++;
        getData(searchText);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case 0:
                Udinspojxxm udinspojxxm = (Udinspojxxm) data.getSerializableExtra("udinspojxxm");

                uditems.add(udinspojxxm);
                udinspojxxmListAdapter.adddate(uditems);
                nodatalayout.setVisibility(View.GONE);
                break;


            default:
                break;
        }
    }








}
