package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.model.Assignment;
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.ui.adapter.AssignmentAdapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by think on 2015/12/4.
 * 任务分配页面
 */
public class Work_AssignmentActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,SwipeRefreshLayout.OnLoadListener{
    /**标题**/
    private TextView titlename;
    /**返回**/
    private ImageView backImage;

    /**菜单按钮**/
    private ImageView addimg;
    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private SwipeRefreshLayout refresh_layout = null;
    private AssignmentAdapter assignmentAdapter;
    private int page = 1;

    private WorkOrder workOrder;
    private ArrayList<Woactivity> woactivityList = new ArrayList<>();
    private ArrayList<Assignment> assignmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_assignment);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
        woactivityList = (ArrayList<Woactivity>) getIntent().getSerializableExtra("woactivityList");
        assignmentList = (ArrayList<Assignment>) getIntent().getSerializableExtra("assignmentList");
    }
    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        addimg = (ImageView) findViewById(R.id.title_add);
        backImage = (ImageView) findViewById(R.id.title_back_id);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_work_assignment);
        addimg.setImageResource(R.drawable.add_ico);
        addimg.setVisibility(View.VISIBLE);
        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Work_AssignmentActivity.this, AssigmentAddNewActivity.class);
                intent.putExtra("woactivityList",woactivityList);
                startActivityForResult(intent, 1);
            }
        });

        backImage.setOnClickListener(backOnClickListener);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        assignmentAdapter = new AssignmentAdapter(this);
        recyclerView.setAdapter(assignmentAdapter);

        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
//        refresh_layout.setProgressViewOffset(false, 0,
//                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);
        if(workOrder.wonum!=null&&!workOrder.wonum.equals("")) {
            refresh_layout.setRefreshing(true);
            getdata();
        }
        if(assignmentList.size()!=0){
            assignmentAdapter.update(assignmentList,true);
        }
    }

    private View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            intent.putExtra("assignmentList", assignmentList);
            setResult(2000,intent);
            finish();
        }
    };

    private void getdata() {
        HttpManager.getDataPagingInfo(Work_AssignmentActivity.this, HttpManager.getAssignmentUrl(page, 20,workOrder.wonum), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int currentPage, int showcount) {
                ArrayList<Assignment> assignments = null;
                if (currentPage == page) {
                    try {
                        assignments = Ig_Json_Model.parsingAssignment(results.getResultlist());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                addListData(assignments);
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

    private void addListData(ArrayList<Assignment> list) {
        if (nodatalayout.getVisibility() == View.VISIBLE) {
            nodatalayout.setVisibility(View.GONE);
        }
        if (page == 1 && assignmentAdapter.getItemCount() != 0) {
            assignmentAdapter = new AssignmentAdapter(this);
            recyclerView.setAdapter(assignmentAdapter);
        }
        if ((list == null || list.size() == 0) && page == 1) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {
            assignmentAdapter.adddate(list);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (resultCode) {
            case 0://修改
                Assignment assignment1 = (Assignment) data.getSerializableExtra("assignment");
                int position = data.getIntExtra("position",0);
                assignmentList.set(position,assignment1);
                assignmentAdapter.assignmentList.set(position,assignment1);
                assignmentAdapter.notifyDataSetChanged();
                break;
            case 1://新增
                if (data != null) {
                    Assignment assignment = (Assignment) data.getSerializableExtra("assignment");
                    assignmentList.add(assignment);
                    assignmentAdapter.adddate(assignment);
                    nodatalayout.setVisibility(View.GONE);
                }
                break;
        }
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        if(workOrder.wonum!=null&&!workOrder.wonum.equals("")) {
            page = 1;
            getdata();
        }
    }

    @Override
    public void onLoad(){
        if(workOrder.wonum!=null&&!workOrder.wonum.equals("")) {
            page++;
            getdata();
        }
    }

}
