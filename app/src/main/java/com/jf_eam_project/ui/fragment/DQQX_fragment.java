
package com.jf_eam_project.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.jf_eam_project.utils.AccountUtils;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 电气缺陷**
 */

public class DQQX_fragment extends BaseFragment {

    private View view;

    @Bind(R.id.dqgz_20_text_id)
    TextView dqgz20TextView; //电气缺陷统计20天以上
    @Bind(R.id.dengji_text_id)
    TextView dj20TextView; //缺陷等级20天以上

    private LinearLayoutManager dq20layoutManager;
    @Bind(R.id.dqgz20_recyclerView_id)
    RecyclerView dq20recyclerView;

    @Bind(R.id.gztj20have_not_data_id)
    LinearLayout dq20nodatalayout;

    private Fjdq20viewListAdapter fjdq20viewListAdapter;

    @Bind(R.id.dqgz_10_text_id)
    TextView dqgz10TextView; //电气缺陷统计10-20天以上
    @Bind(R.id.dengji10_text_id)
    TextView dj10TextView; //缺陷等级10-20天以上

    private LinearLayoutManager dq10layoutManager;
    @Bind(R.id.dqgz10_recyclerView_id)
    RecyclerView dq10recyclerView; //dq10recyclerView
    @Bind(R.id.gztj10have_not_data_id)
    LinearLayout dq10nodatalayout;
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
        ButterKnife.bind(this, view);

        initView();
        mProgressDialog = ProgressDialog.show(getActivity(), null,
                "正在获取数据中...", true, true);

        getfjdq20VIEW();
        getfjdq10to20VIEW();

        return view;
    }


    private void initView() {

        dqgz20TextView.setText(R.string.dqqx1_text);
        dj20TextView.setText(R.string.qxdj_text);
        dq20layoutManager = new LinearLayoutManager(getActivity());
        dq20layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dq20layoutManager.scrollToPosition(0);

        dq20recyclerView.setLayoutManager(dq20layoutManager);
        dq20recyclerView.setItemAnimator(new DefaultItemAnimator());
        fjdq20viewListAdapter = new Fjdq20viewListAdapter(getActivity());
        dq20recyclerView.setAdapter(fjdq20viewListAdapter);

        dqgz10TextView.setText(R.string.dqqx_10_text);
        dj10TextView.setText(R.string.qxdj_text);
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
        HttpManager.getDataPagingInfo(getActivity(), HttpManager.getFjdq20VIEW(AccountUtils.getDepartment(getActivity()), "电气", "HIDDEN", 1, 20), new HttpRequestHandler<Results>() {
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
        HttpManager.getDataPagingInfo(getActivity(), HttpManager.getFjdq10VIEW(AccountUtils.getDepartment(getActivity()), "电气", "HIDDEN", 1, 20), new HttpRequestHandler<Results>() {
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
