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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
 * 工单列表界面
 */
public class Work_ListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {

    private static final String TAG = "Work_ListActivity";
    @Bind(R.id.title_name)
    TextView titlename; //标题
    @Bind(R.id.title_back_id)
    ImageView backImage;//返回

    @Bind(R.id.title_add)
    ImageView meunImageView;//菜单
    PopupWindow popupWindow;


    private String worktype;
    LinearLayoutManager layoutManager;

    @Bind(R.id.recyclerView_id)
    RecyclerView recyclerView; //recyclerView
    @Bind(R.id.have_not_data_id) //暂无数据
            LinearLayout nodatalayout;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout refresh_layout;
    @Bind(R.id.search_edit)
    EditText search;
    private WorkListAdapter workListAdapter;
    private String searchText = "";
    private int page = 1;

    /**
     * 资产编号
     **/
    private String assetsNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        ButterKnife.bind(this);
        getIntentData();
        findViewById();
        initView();
    }

    private void getIntentData() {
        worktype = getIntent().getStringExtra("worktype");
        assetsNum = getIntent().getStringExtra("assetnum");


    }

    @Override
    protected void findViewById() {

    }

    @Override
    protected void initView() {
        setSearchEdit();
        titlename.setText(R.string.title_activity_work_list);
        meunImageView.setImageResource(R.drawable.ic_more);
        meunImageView.setVisibility(View.VISIBLE);
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


    //菜单
    @OnClick(R.id.title_add)
    void setAddimgOnClickListener() {
        showPopupWindow(meunImageView);
    }


    private void getData(String search) {
        String url = "";
        if (isCount(search)) {
            url = HttpManager.getWorkorderByNumUrl(search, page, 20);
        } else {
            String[] depatments = separatedString(AccountUtils.getDepartment(Work_ListActivity.this));
            String department = "";
            if (depatments.length == 1) {
                department = depatments[0];
            } else {
                for (String s : depatments) {
                    department += s + ",=";
                }
            }
            url = HttpManager.getworkorderUrl(worktype, department, search, assetsNum, page, 20);
        }
        HttpManager.getDataPagingInfo(this, url, new HttpRequestHandler<Results>() {
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
                                workListAdapter = new WorkListAdapter(Work_ListActivity.this, 0);
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
                                    Work_ListActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    refresh_layout.setRefreshing(true);
                    searchText = search.getText().toString();
                    workListAdapter = new WorkListAdapter(Work_ListActivity.this, 0);
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


    /**
     * 初始化showPopupWindow*
     */
    private void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(Work_ListActivity.this).inflate(
                R.layout.workorder_popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.popup_background_mtrl_mult));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);
        TextView addTextView = (TextView) contentView.findViewById(R.id.add_text_id);

        TextView lsTextView = (TextView) contentView.findViewById(R.id.workorder_ls_text_id);
        addTextView.setOnClickListener(addTextViewOnClickListener);
        lsTextView.setOnClickListener(lsTextViewOnClickListener);

    }

    private View.OnClickListener addTextViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_ListActivity.this, Work_AddNewActivity.class);
            intent.putExtra("worktype", worktype);
            startActivityForResult(intent, 0);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener lsTextViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_ListActivity.this, Work_History_ListActivity.class);
            intent.putExtra("worktype", worktype);
            startActivityForResult(intent, 0);
            popupWindow.dismiss();
        }
    };


}
