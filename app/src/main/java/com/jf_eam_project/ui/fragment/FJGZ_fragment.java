package com.jf_eam_project.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.model.FJDQ20VIEW;
import com.jf_eam_project.ui.adapter.Fjdq20viewListAdapter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 风机故障*
 */

public class FJGZ_fragment extends BaseFragment {
    private final String TAG = "FJGZ_fragment";

    private View view;

    private TextView dqgz20TextView; //电气故障统计20天以上
    private LinearLayoutManager dq20layoutManager;
    public RecyclerView dq20recyclerView;
    public LinearLayout dq20nodatalayout;

    private Fjdq20viewListAdapter fjdq20viewListAdapter;


    private TextView dqgz10TextView; //电气故障统计10-20天以上

    private LinearLayoutManager dq10layoutManager;
    public RecyclerView dq10recyclerView;
    public LinearLayout dq10nodatalayout;
    private Fjdq20viewListAdapter fjdq10viewListAdapter;


    private ProgressDialog mProgressDialog;


    private ArrayList<FJDQ20VIEW> fjdq20VIEW; //获取电气故障统计20天以上的数据


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        view = inflater.inflate(R.layout.fragment_gztj,
                container, false);


        findViewById(view);
        initView();
        mProgressDialog = ProgressDialog.show(getActivity(), null,
                "正在获取数据中...", true, true);

        getfjdq20VIEW();
        getfjdq10to20VIEW();

        return view;
    }


    protected void findViewById(View view) {
        dqgz20TextView = (TextView) view.findViewById(R.id.dqgz_20_text_id);

        dq20recyclerView = (RecyclerView) view.findViewById(R.id.dqgz20_recyclerView_id);
        dq20nodatalayout = (LinearLayout) view.findViewById(R.id.gztj20have_not_data_id);

        dqgz10TextView = (TextView) view.findViewById(R.id.dqgz_10_text_id);

        dq10recyclerView = (RecyclerView) view.findViewById(R.id.dqgz10_recyclerView_id);
        dq10nodatalayout = (LinearLayout) view.findViewById(R.id.gztj10have_not_data_id);

    }

    private void initView() {

        dqgz20TextView.setText(R.string.fjgz1_text);
        dq20layoutManager = new LinearLayoutManager(getActivity());
        dq20layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dq20layoutManager.scrollToPosition(0);

        dq20recyclerView.setLayoutManager(dq20layoutManager);
        dq20recyclerView.setItemAnimator(new DefaultItemAnimator());
        fjdq20viewListAdapter = new Fjdq20viewListAdapter(getActivity());
        dq20recyclerView.setAdapter(fjdq20viewListAdapter);

        dqgz10TextView.setText(R.string.fj_gz_10_text);
        dq10layoutManager = new LinearLayoutManager(getActivity());
        dq10layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dq10layoutManager.scrollToPosition(0);
        dq10recyclerView.setLayoutManager(dq10layoutManager);
        dq10recyclerView.setItemAnimator(new DefaultItemAnimator());
        fjdq10viewListAdapter = new Fjdq20viewListAdapter(getActivity());
        dq10recyclerView.setAdapter(fjdq10viewListAdapter);

    }


    /**
     * 获取电气故障统计20天以上
     **/
    private void getfjdq20VIEW() {
        HttpManager.getDataPagingInfo(getActivity(), HttpManager.getFjdq20VIEW("风机", "FAULT", 1, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                mProgressDialog.dismiss();
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                mProgressDialog.dismiss();

                ArrayList<FJDQ20VIEW> items = null;
                try {
                    items = Ig_Json_Model.parsingFJDQ20VIEW(results.getResultlist());
                    if (items == null || items.isEmpty()) {
                        if (dq20nodatalayout.getVisibility() == View.GONE) {
                            dq20nodatalayout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        fjdq20viewListAdapter.adddate(items);
                        fjdq20viewListAdapter.notifyDataSetChanged();
                    }

                } catch (IOException e) {
                    if (dq20nodatalayout.getVisibility() == View.GONE) {
                        dq20nodatalayout.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onFailure(String error) {
                mProgressDialog.dismiss();
                if (dq20nodatalayout.getVisibility() == View.GONE) {
                    dq20nodatalayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /**
     * 获取电气故障统计10-20天
     **/
    private void getfjdq10to20VIEW() {
        HttpManager.getDataPagingInfo(getActivity(), HttpManager.getFjdq10VIEW("风机", "FAULT", 1, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                mProgressDialog.dismiss();
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                Log.i(TAG,"2123123");
                mProgressDialog.dismiss();

                ArrayList<FJDQ20VIEW> items = null;
                try {
                    items = Ig_Json_Model.parsingFJDQ20VIEW(results.getResultlist());
                    if (items == null || items.isEmpty()) {
                        if (dq10nodatalayout.getVisibility() == View.GONE) {
                            dq10nodatalayout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        fjdq10viewListAdapter.adddate(items);
                        fjdq10viewListAdapter.notifyDataSetChanged();
                    }

                } catch (IOException e) {
                    if (dq10nodatalayout.getVisibility() == View.GONE) {
                        dq10nodatalayout.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onFailure(String error) {
                mProgressDialog.dismiss();
                if (dq10nodatalayout.getVisibility() == View.GONE) {
                    dq10nodatalayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}
