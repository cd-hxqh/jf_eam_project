package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Labtrans;
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.model.Wplabor;
import com.jf_eam_project.ui.adapter.LabtransAdapter;
import com.jf_eam_project.ui.adapter.WplaborAdapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by think on 2015/12/22.
 * 工单实际员工列表界面
 */
public class Work_LabtransActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
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
    private ImageView addimg;

    private LinearLayout confirmBtn;
    private Button revise;//
    private Button wfservice;
    private WorkOrder workOrder;
    //    private ArrayList<Woactivity> woactivityList = new ArrayList<>();
    private ArrayList<Labtrans> labtransList = new ArrayList<>();

    //    private TextView wonum;
//    private TextView status;
//    private TextView parent;
    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private SwipeRefreshLayout refresh_layout = null;
    private LabtransAdapter labtransAdapter;
    private int page = 1;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtrans);

        getIntentData();
        findViewById();
        initView();
    }

    private void getIntentData() {
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
//        woactivityList = (ArrayList<Woactivity>) getIntent().getSerializableExtra("woactivityList");
        labtransList = (ArrayList<Labtrans>) getIntent().getSerializableExtra("labtransList");
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        addimg = (ImageView) findViewById(R.id.title_add);
        backImage = (ImageView) findViewById(R.id.title_back_id);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
//        wonum = (TextView) findViewById(R.id.work_wonum);
//        status = (TextView) findViewById(R.id.work_status);
//        parent = (TextView) findViewById(R.id.work_parent);

        confirmBtn = (LinearLayout) findViewById(R.id.buttom_layout);
        revise = (Button) findViewById(R.id.work_revise);
        wfservice = (Button) findViewById(R.id.wfservice);

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();

    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_work_labtrans);
        addimg.setImageResource(R.drawable.add_ico);
        addimg.setVisibility(View.VISIBLE);
        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Work_LabtransActivity.this, LabtransAddNewActivity.class);
//                intent.putExtra("woactivityList", woactivityList);
                startActivityForResult(intent, 1);
            }
        });
        if (workOrder.wonum!=null&&!workOrder.wonum.equals("")&&!workOrder.status.equals(Constants.WAIT_APPROVAL)){
            addimg.setVisibility(View.GONE);
        }

//        wonum.setText(workOrder.wonum == null ? "" : workOrder.wonum);
//        status.setText(workOrder.status == null ? "" : workOrder.status);
//        parent.setText(workOrder.parent == null ? "" : workOrder.parent);

        backImage.setOnClickListener(backOnClickListener);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        labtransAdapter = new LabtransAdapter(Work_LabtransActivity.this);
        recyclerView.setAdapter(labtransAdapter);
        refresh_layout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        if (!workOrder.isnew && (labtransList == null || labtransList.size() == 0)) {
            refresh_layout.setRefreshing(true);
            getdata();
        }
        if (labtransList.size()!=0){
            labtransAdapter.update(labtransList,true);
        }

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);

        revise.setText(getResources().getString(R.string.ok));
        revise.setOnClickListener(okOnClickListener);
        wfservice.setVisibility(View.GONE);

        setNodataLayout();
    }

    private View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final NormalDialog dialog = new NormalDialog(Work_LabtransActivity.this);
            dialog.content("确定放弃修改吗?")//
                    .showAnim(mBasIn)//
                    .dismissAnim(mBasOut)//
                    .show();

            dialog.setOnBtnClickL(
                    new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {
                            dialog.dismiss();
                        }
                    },
                    new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {
                            Work_LabtransActivity.this.finish();
//                            dialog.dismiss();
                        }
                    });
        }
    };

    private View.OnClickListener okOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            intent.putExtra("labtransList", (Serializable) labtransAdapter.labtransList);
            setResult(3000, intent);
            finish();
        }
    };

    private void getdata() {
        HttpManager.getDataPagingInfo(Work_LabtransActivity.this, HttpManager.getLabtransUrl(page, 20, workOrder.wonum), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int currentPage, int showcount) {
                ArrayList<Labtrans> labtranses = null;
                if (currentPage == page) {
                    try {
                        labtranses = Ig_Json_Model.parsingLabtrans(results.getResultlist());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                addListData(labtranses);
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
            }

            @Override
            public void onFailure(String error) {
                if (page == 1) {
                    nodatalayout.setVisibility(View.VISIBLE);
                }
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
            }
        });
    }

    private void addListData(ArrayList<Labtrans> list) {
        if (nodatalayout.getVisibility() == View.VISIBLE) {
            nodatalayout.setVisibility(View.GONE);
        }
        if (page == 1 && labtransAdapter.getItemCount() != 0) {
            labtransAdapter = new LabtransAdapter(Work_LabtransActivity.this);
            recyclerView.setAdapter(labtransAdapter);
        }
        if ((list == null || list.size() == 0) && page == 1) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {
            labtransAdapter.adddate(list);
        }
    }

    private void setNodataLayout() {
        if (labtransAdapter.getItemCount()==0) {
            nodatalayout.setVisibility(View.VISIBLE);
        }else {
            nodatalayout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (resultCode) {
            case 2://修改
                if (data != null) {
                    Labtrans labtrans = (Labtrans) data.getSerializableExtra("labtrans");
                    int position = data.getIntExtra("position", 0);
//                    labtransList.set(position, labtrans);
                    labtransAdapter.labtransList.set(position, labtrans);
                    labtransAdapter.notifyDataSetChanged();
                }
                confirmBtn.setVisibility(View.VISIBLE);
                break;
            case 1://新增
                if (data != null) {
                    Labtrans labtrans = (Labtrans) data.getSerializableExtra("labtrans");
//                    labtransList.add(labtrans);
                    labtransAdapter.adddate(labtrans);
                    nodatalayout.setVisibility(View.GONE);
                }
                confirmBtn.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 3://本地员工删除
                if (data != null){
                    int position = data.getIntExtra("position",0);
                    labtransAdapter.labtransList.remove(position);
                    labtransAdapter.notifyDataSetChanged();
                }
                confirmBtn.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            default:
                break;
        }
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        if (!workOrder.isnew && (labtransAdapter.labtransList == null || labtransAdapter.labtransList.size() == 0)) {
            page = 1;
            getdata();
        } else {
            refresh_layout.setRefreshing(false);
        }
    }

    //上拉加载
    @Override
    public void onLoad() {
        if (!workOrder.isnew) {
            page++;
            getdata();
        }
    }
}
