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
import com.jf_eam_project.model.Wpmaterial;
import com.jf_eam_project.ui.activity.Work_PlanActivity;
import com.jf_eam_project.ui.adapter.WpmaterialAdapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;

import java.io.IOException;
import java.util.ArrayList;


/**
 * 工单物料的fragment
 */
@SuppressLint("ValidFragment")
public class WpmaterialFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static String TAG = "WplaborFragment";

    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    public LinearLayout nodatalayout;
    public WpmaterialAdapter wpmaterialAdapter;
    private SwipeRefreshLayout refresh_layout = null;
    private int page = 1;
    private WorkOrder workOrder;
    private ArrayList<Wpmaterial> wpmaterials;

    public WpmaterialFragment() {
    }

    public WpmaterialFragment(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }

    public WpmaterialFragment(WorkOrder workOrder, ArrayList<Wpmaterial> wpmaterials) {
        this.workOrder = workOrder;
        this.wpmaterials = wpmaterials;
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
        wpmaterialAdapter = new WpmaterialAdapter((Work_PlanActivity) getActivity());
        recyclerView.setAdapter(wpmaterialAdapter);

        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);

        if (!workOrder.isnew && (wpmaterials == null || wpmaterials.size() == 0) && !workOrder.ishistory) {
            refresh_layout.setRefreshing(true);
            getdata();
        } else {
            if (wpmaterials != null && wpmaterials.size() != 0) {
                wpmaterialAdapter.update(wpmaterials, true);
            }
        }
    }

    private void getdata() {
        HttpManager.getDataPagingInfo(getActivity(), HttpManager.getWpmaterialUrl("", page, 20, workOrder.wonum), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int currentPage, int showcount) {
                ArrayList<Wpmaterial> wplabors = null;
                if (currentPage == page) {
                    try {
                        wplabors = Ig_Json_Model.parsingWpmaterial(results.getResultlist());
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

    private void addListData(ArrayList<Wpmaterial> list) {
        if (nodatalayout.getVisibility() == View.VISIBLE) {
            nodatalayout.setVisibility(View.GONE);
        }
        if (page == 1 && wpmaterialAdapter.getItemCount() != 0) {
            wpmaterialAdapter = new WpmaterialAdapter((Work_PlanActivity) getActivity());
            recyclerView.setAdapter(wpmaterialAdapter);
        }
        if ((list == null || list.size() == 0) && page == 1) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {
            wpmaterialAdapter.adddate(list);
            ((Work_PlanActivity) getActivity()).setWpmaterialList(list);
        }
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        if (!workOrder.isnew && (wpmaterials == null || wpmaterials.size() == 0) && !workOrder.ishistory) {
            page = 1;
            getdata();
        } else {
            refresh_layout.setRefreshing(false);
        }
    }

    @Override
    public void onLoad() {
        if (!workOrder.isnew && (wpmaterials == null || wpmaterials.size() == 0) && !workOrder.ishistory) {
            page++;
            getdata();
        }
    }
}
