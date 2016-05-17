package com.jf_eam_project.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.jf_eam_project.Dao.UdinspojxxmDao;
import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Udinspojxxm;
import com.jf_eam_project.ui.adapter.UdinspojxxmListAdapter;
import com.jf_eam_project.ui.adapter.UdinspojxxmNewListAdapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;
import com.jf_eam_project.utils.MessageUtils;
import com.jf_eam_project.utils.NetWorkHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 检修项目标准
 */
public class UdinspojxxmNew_Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
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

    private UdinspojxxmNewListAdapter udinspojxxmNewListAdapter;
    //    private EditText search;
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
     * 巡检类型*
     */
    private String assettype;

    /**
     * 确定按钮*
     */
    private Button submitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udispoxxmnew);

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
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        addImageView = (ImageView) findViewById(R.id.title_add);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        submitbtn = (Button) findViewById(R.id.submit_btn_id);
//        search = (EditText) findViewById(R.id.search_edit);
    }

    @Override
    protected void initView() {
        titleView.setText(getResources().getString(R.string.udinspojxxm_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);

        addImageView.setImageResource(R.drawable.ic_menu_recent_history);
        addImageView.setVisibility(View.GONE);
        addImageView.setOnClickListener(addImageViewOnClickListener);

//        setSearchEdit();


        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        udinspojxxmNewListAdapter = new UdinspojxxmNewListAdapter(this);
        recyclerView.setAdapter(udinspojxxmNewListAdapter);
        udinspojxxmNewListAdapter.setData(branch, udbelong, assettype);
        refresh_layout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        refresh_layout.setRefreshing(true);

        getLocalData();

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);
        submitbtn.setOnClickListener(submitbtnOnClickListener);

    }

    /**
     * 获取本地数据*
     */
    private void getLocalData() {

        ArrayList<Udinspojxxm> list = (ArrayList<Udinspojxxm>) new UdinspojxxmDao(UdinspojxxmNew_Activity.this).queryByUdinspoassetnum(udinspoassetnum);
        refresh_layout.setRefreshing(false);
        refresh_layout.setLoading(false);
        if (list == null || list.isEmpty()) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {

            udinspojxxmNewListAdapter.adddate(list);
        }

        udinspojxxmNewListAdapter.setOnClickListener(new UdinspojxxmNewListAdapter.OnClickListener() {
            @Override
            public void cOnClickListener(Udinspojxxm udinspojxxm) {
                Intent intent = new Intent(UdinspojxxmNew_Activity.this, Udinspojxxm_Details_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Udinspojxxm", udinspojxxm);
                bundle.putSerializable("branch", branch);
                bundle.putSerializable("udbelong", udbelong);
                bundle.putSerializable("assettype", assettype);
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
            }
        });

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
                            udinspojxxmNewListAdapter = new UdinspojxxmNewListAdapter(UdinspojxxmNew_Activity.this);
                            udinspojxxmNewListAdapter.setData(branch, udbelong, assettype);
                            recyclerView.setAdapter(udinspojxxmNewListAdapter);
                        }
                        if (totalPages == page) {
                            new UdinspojxxmDao(UdinspojxxmNew_Activity.this).create(items);
                            udinspojxxmNewListAdapter.adddate(items);
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


//    private void setSearchEdit() {
//
//        SpannableString msp = new SpannableString("XX搜索");
//        Drawable drawable = getResources().getDrawable(R.drawable.ic_search);
//        msp.setSpan(new ImageSpan(drawable), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//        search.setHint(msp);
//        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    // 隐藏软件盘
//                    ((InputMethodManager) search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
//                            .hideSoftInputFromWindow(
//                                    UdinspojxxmNew_Activity.this.getCurrentFocus()
//                                            .getWindowToken(),
//                                    InputMethodManager.HIDE_NOT_ALWAYS);
//                    searchText = search.getText().toString();
//                    udinspojxxmNewListAdapter.removeAllData();
//                    nodatalayout.setVisibility(View.GONE);
//                    refresh_layout.setRefreshing(true);
//                    page = 1;
//                    getData(searchText);
//                    return true;
//                }
//                return false;
//            }
//        });
//    }

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

        if (!NetWorkHelper.isNetwork(UdinspojxxmNew_Activity.this)) { //没有网络
            getData(searchText);
        } else {
            refresh_layout.setRefreshing(false);
            refresh_layout.setLoading(false);
        }
    }

    @Override
    public void onRefresh() {
        page++;
        if (!NetWorkHelper.isNetwork(UdinspojxxmNew_Activity.this)) { //没有网络
            getData(searchText);
        } else {
            refresh_layout.setRefreshing(false);
            refresh_layout.setLoading(false);
        }
    }


    private View.OnClickListener addImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            Intent intent = new Intent(UdinspojxxmNew_Activity.this, Udinspojxxm_History_Activity.class);
            intent.putExtra("udinspoassetnum", udinspoassetnum);
            startActivityForResult(intent, 0);
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {

            case Constants.REFRESH:
                udinspojxxmNewListAdapter.removeAllData();
                getLocalData();
                udinspojxxmNewListAdapter.notifyDataSetChanged();
                break;
        }

    }


    /**
     * 判断是否提交数据*
     */
    private View.OnClickListener submitbtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!isAbnormal()) {
                alertDialog();
            } else {
                setResult(Constants.REFRESH);
                finish();
                MessageUtils.showMiddleToast(UdinspojxxmNew_Activity.this, "数据保存成功");
            }
        }
    };


    private void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UdinspojxxmNew_Activity.this);
        builder.setMessage("你有未操作完成的单子,确定要提交吗？").setTitle("提示")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setResult(Constants.REFRESH);
                finish();
                dialogInterface.dismiss();


            }
        }).create().show();
    }


    /**
     * 判断是否有异常的单子*
     */
    private boolean isAbnormal() {

        List<Udinspojxxm> list = new UdinspojxxmDao(UdinspojxxmNew_Activity.this).findByabnormal(udinspoassetnum, "异常");
        if (null == list || list.size() == 0) {
            //表示正常
            return true;
        } else {
            return false;
        }


    }
}
