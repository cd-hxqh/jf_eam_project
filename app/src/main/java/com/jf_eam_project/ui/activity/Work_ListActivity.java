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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;

/**
 * Created by think on 2015/11/20.
 * 工单列表界面
 */
public class Work_ListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,SwipeRefreshLayout.OnLoadListener{

    private TextView titlename;
    private ImageView addimg;
    private RelativeLayout backlayout;
    private String worktype;
    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private SwipeRefreshLayout refresh_layout = null;
    private EditText search;
    private String searchText = "";
    private int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worklist);

        getIntentData();
        findViewById();
        initView();
    }
    private void getIntentData(){
        worktype = getIntent().getStringExtra("worktype");
    }
    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        addimg = (ImageView) findViewById(R.id.title_add);
        backlayout = (RelativeLayout) findViewById(R.id.title_back);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        search = (EditText) findViewById(R.id.search_edit);
    }

    @Override
    protected void initView() {
        setSearchEdit();
        titlename.setText(R.string.title_activity_work_list);
        addimg.setVisibility(View.VISIBLE);

        backlayout.setOnClickListener(new View.OnClickListener() {
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
        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setRefreshing(true);
        String s = HttpManager.getworkorderUrl(1, 20);
//        getData(searchText);

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);
    }

    private void setSearchEdit(){
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
                    searchText = search.getText().toString();
//                    workListAdapter = new WorkListAdapter(Work_ListActivity.this);
//                    recyclerView.setAdapter(workListAdapter);
//                    getData(searchText);
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
//        getData(search.getText().toString());
    }

    @Override
    public void onLoad(){
        page++;
//        getData(searchText);
    }
}
