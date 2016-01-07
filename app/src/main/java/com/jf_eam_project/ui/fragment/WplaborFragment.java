package com.jf_eam_project.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.model.Wplabor;
import com.jf_eam_project.ui.adapter.WplaborAdapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * 工单计划员工的fragment
 */
@SuppressLint("ValidFragment")
public class WplaborFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static String TAG = "WplaborFragment";

    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    public LinearLayout nodatalayout;
    public WplaborAdapter wplaborAdapter;
    private SwipeRefreshLayout refresh_layout = null;
    private int page = 1;
    private WorkOrder workOrder;
    private ArrayList<Wplabor> wplabors;

    public WplaborFragment() {
    }

    public WplaborFragment(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }

    public WplaborFragment(WorkOrder workOrder,ArrayList<Wplabor> wplabors) {
        this.workOrder = workOrder;
        this.wplabors = wplabors;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work_plan, container,
                false);

        findByIdView(view);
        initView();
        return view;
    }

    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) view.findViewById(R.id.have_not_data_id);
    }

    private void initView() {
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        wplaborAdapter = new WplaborAdapter(getActivity());
        recyclerView.setAdapter(wplaborAdapter);

        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);
        if (workOrder.wonum != null && !workOrder.equals("")) {
            refresh_layout.setRefreshing(true);
            getdata();
        }else {
            if (wplabors!=null&&wplabors.size()!=0){
                wplaborAdapter.update(wplabors,true);
            }
        }
    }

    private void getdata() {
        HttpManager.getDataPagingInfo(getActivity(), HttpManager.getWplaborUrl(page, 20,""), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int currentPage, int showcount) {
                ArrayList<Wplabor> wplabors = null;
                if (currentPage == page) {
                    try {
                        wplabors = Ig_Json_Model.parsingWplabor(results.getResultlist());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                addListData(wplabors);
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

    private void addListData(ArrayList<Wplabor> list) {
        if (nodatalayout.getVisibility() == View.VISIBLE) {
            nodatalayout.setVisibility(View.GONE);
        }
        if (page == 1 && wplaborAdapter.getItemCount() != 0) {
            wplaborAdapter = new WplaborAdapter(getActivity());
            recyclerView.setAdapter(wplaborAdapter);
        }
        if ((list == null || list.size() == 0) && page == 1) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {
            wplaborAdapter.adddate(list);
        }
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        if (workOrder.wonum != null && !workOrder.equals("")) {
            page = 1;
            getdata();
        }
    }

    @Override
    public void onLoad() {
        if (workOrder.wonum != null && !workOrder.equals("")) {
            page++;
            getdata();
        }
    }
}
