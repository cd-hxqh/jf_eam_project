package com.jf_eam_project.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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
import com.jf_eam_project.ui.adapter.UdinspojxxmNewListAdapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;
import com.jf_eam_project.utils.MessageUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 检修项目标准
 */
public class UdinspojxxmNew_Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static final String TAG = "Udinspojxxm_Activity";

    @Bind(R.id.title_name)
    TextView titleView;//标题
    @Bind(R.id.title_back_id)
    ImageView backImageView;//返回


    /**
     * 设备编号
     */
    private String udinspoassetnum;


    LinearLayoutManager layoutManager;

    @Bind(R.id.recyclerView_id)
    RecyclerView recyclerView;//RecyclerView

    @Bind(R.id.have_not_data_id)
    LinearLayout nodatalayout;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout refresh_layout;

    private UdinspojxxmNewListAdapter udinspojxxmNewListAdapter;
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
    @Bind(R.id.submit_btn_id)
    Button submitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udispoxxmnew);
        ButterKnife.bind(this);
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
    }

    @Override
    protected void initView() {
        titleView.setText(getResources().getString(R.string.udinspojxxm_title));


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

    }

    /**
     * 获取本地数据*
     */
    private void getLocalData() {

        final ArrayList<Udinspojxxm> list = (ArrayList<Udinspojxxm>) new UdinspojxxmDao(UdinspojxxmNew_Activity.this).queryByUdinspoassetnum(udinspoassetnum);


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


    /**
     * 返回点击
     */
    @OnClick(R.id.title_back_id)
    void setBackImageViewOnClickListenrer() {
        finish();
    }


    @Override
    public void onLoad() {
        page = 1;
        refresh_layout.setRefreshing(false);
        refresh_layout.setLoading(false);
    }

    @Override
    public void onRefresh() {
        page++;
        refresh_layout.setRefreshing(false);
        refresh_layout.setLoading(false);
    }


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
    @OnClick(R.id.submit_btn_id)
    void setSubmitbtnOnClickListener() {
        if (!isAbnormal()) {
            alertDialog();
        } else {
            setResult(Constants.REFRESH);
            MessageUtils.showMiddleToast(UdinspojxxmNew_Activity.this, "数据保存成功");
            finish();
        }
    }


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
