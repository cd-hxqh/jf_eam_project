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
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.jf_eam_project.Dao.UdinspoAssetDao;
import com.jf_eam_project.Dao.UdinspoDao;
import com.jf_eam_project.Dao.UdinspojxxmDao;
import com.jf_eam_project.Dao.UdreportDao;
import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.model.Udinspo;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.model.Udinspojxxm;
import com.jf_eam_project.model.Udreport;
import com.jf_eam_project.ui.adapter.UdinspoListNewadapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;
import com.jf_eam_project.utils.MessageUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 巡检单列表
 */
public class UdinspoNew_Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {

    private static final String TAG = "UdinspoNew_Activity";


    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;

    /**
     * 全选*
     */
    private CheckBox allCheckBox;


    LinearLayoutManager layoutManager;


    /**
     * RecyclerView*
     */
    public RecyclerView recyclerView;
    /**
     * 暂无数据*
     */
    private LinearLayout nodatalayout;
    /**
     * 界面刷新*
     */
    private SwipeRefreshLayout refresh_layout = null;
    /**
     * 适配器*
     */
    private UdinspoListNewadapter udinspoListNewadapter;
    /**
     * 编辑框*
     */
    private EditText search;
    /**
     * 查询条件*
     */
    private String searchText = "";
    private int page = 1;

    /**
     * 获取巡检单标题*
     */
    private String title;
    /**
     * 巡检单类型*
     */
    private String inspotype;
    /**
     * assettype*
     */
    private String assettype;
    /**
     * checktype*
     */
    private String checktype;


    ArrayList<Udinspo> list = new ArrayList<>();
    /**
     * 未下载的巡检单子*
     */
    ArrayList<Udinspo> notDownlist = new ArrayList<>();

    ArrayList<Udinspo> chooseList = new ArrayList<Udinspo>();

    /**
     * 下载任务*
     */
    private TextView downBtn;
    /**
     * 等待操作*
     */
    private TextView waitTask;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    /**
     * 是否全选*
     */
    private boolean isAllChoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.udinspo_activity);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取巡检单*
     */
    private void initData() {
        title = getIntent().getStringExtra("title");
        inspotype = getIntent().getStringExtra("inspotype");
        if (inspotype.equals("05")) {
            assettype = getIntent().getStringExtra("assettype");
            checktype = getIntent().getStringExtra("checktype");
        }
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        allCheckBox = (CheckBox) findViewById(R.id.all_checkbox);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        search = (EditText) findViewById(R.id.search_edit);
        downBtn = (TextView) findViewById(R.id.upload_choose_id);
        waitTask = (TextView) findViewById(R.id.wait_operating_task_id);
    }

    @Override
    protected void initView() {
        setSearchEdit();
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        allCheckBox.setVisibility(View.VISIBLE);
        titlename.setText(getString(R.string.online_text));
        backImageView.setOnClickListener(backImageViewOnClickListener);
        allCheckBox.setOnCheckedChangeListener(allCheckBoxOmCheckedChangeListener);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        udinspoListNewadapter = new UdinspoListNewadapter(this);
        recyclerView.setAdapter(udinspoListNewadapter);


        refresh_layout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        refresh_layout.setRefreshing(true);
        getData(searchText);
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);

        downBtn.setOnClickListener(downBtnOnClickListener);
        waitTask.setOnClickListener(waitTaskOnClickListener);
    }

    /**
     * 获取本地数据*
     */
    private void getLocalData() {

        ArrayList<Udinspo> list = (ArrayList<Udinspo>) new UdinspoDao(UdinspoNew_Activity.this).findByType(assettype, checktype);
        refresh_layout.setRefreshing(false);
        refresh_layout.setLoading(false);
        if (list == null || list.isEmpty()) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {

            udinspoListNewadapter.adddate(list);
        }

    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener downBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (chooseList != null && chooseList.size() != 0) {
                NormalDialogStyleTwo(chooseList);
            } else {
                MessageUtils.showMiddleToast(UdinspoNew_Activity.this, "请选择需要下载的任务");
            }
        }
    };


    private View.OnClickListener waitTaskOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity();
        }
    };


    /**
     * 跳转界面*
     */
    private void startActivity() {
        Intent intent = getIntent();
        intent.setClass(UdinspoNew_Activity.this, UdinspoLocation_Activity.class);
        startActivityForResult(intent, 0);
    }

    private void NormalDialogStyleTwo(final ArrayList<Udinspo> list) {
        final NormalDialog dialog = new NormalDialog(UdinspoNew_Activity.this);
        dialog.content("已选中" + list.size() + "条任务，是否需要下载")//
                .style(NormalDialog.STYLE_TWO)//
                .titleTextSize(23)//
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
                        downUdinspo(list);
                        dialog.dismiss();
                    }
                });

    }


    /**
     * 下载选择的数据*
     *
     * @param list
     */

    private void downUdinspo(ArrayList<Udinspo> list) {
        for (int i = 0; i < list.size(); i++) {
            getUdinspoData(list.get(i).insponum);
        }
    }


    /**
     * 点击全选*
     */
    private CompoundButton.OnCheckedChangeListener allCheckBoxOmCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            if (b) {

                allCheckBox.setText("取消");
//                addListData();
            } else {
                allCheckBox.setText("全选");
                chooseList = new ArrayList<Udinspo>();
            }
            udinspoListNewadapter.setAllChoose(b);
            udinspoListNewadapter.notifyDataSetChanged();
        }
    };

    /**
     * 全选*
     */
    private void addListData() {
        chooseList = new ArrayList<Udinspo>();
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                chooseList.add(list.get(i));
            }
        }
    }


    @Override
    public void onLoad() {
        page++;

        getData(searchText);
    }

    @Override
    public void onRefresh() {
        page = 1;
        allCheckBox.setChecked(false);
        notDownlist = new ArrayList<Udinspo>();
        getData(searchText);
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
                                    UdinspoNew_Activity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString();
                    udinspoListNewadapter.removeAllData();
                    nodatalayout.setVisibility(View.GONE);
                    refresh_layout.setRefreshing(true);
                    page = 1;
                    getData(searchText);
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 获取数据*
     */
    private void getData(String search) {
        HttpManager.getDataPagingInfo(this, HttpManager.getUdinspourl1(inspotype, assettype, checktype, search, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {


                try {
                    Log.i(TAG, "results=" + results.getResultlist());

                    list = Ig_Json_Model.parseUdinspoString(results.getResultlist());
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    if (list == null || list.isEmpty()) {
                        nodatalayout.setVisibility(View.VISIBLE);
                    } else {
                        nodatalayout.setVisibility(View.GONE);
                        if (page == 1) {
                            udinspoListNewadapter = new UdinspoListNewadapter(UdinspoNew_Activity.this);
                            recyclerView.setAdapter(udinspoListNewadapter);
                        }

                        if (totalPages == page) {
                            for (Udinspo udinspo : list) {
                                if (!isExists(udinspo.insponum, udinspo.inspotype))
                                    notDownlist.add(udinspo);
                            }

                            if (notDownlist == null || notDownlist.isEmpty()) {
                                nodatalayout.setVisibility(View.VISIBLE);
                            } else {

                                udinspoListNewadapter.adddate(notDownlist);
                            }
                        }

                        udinspoListNewadapter.setOnCheckedChangeListener(new UdinspoListNewadapter.OnCheckedChangeListener() {
                            @Override
                            public void cOnCheckedChangeListener(int postion) {
                                chooseList.add(list.get(postion));
                            }
                        });
                    }

                } catch (IOException e) {
                    e.printStackTrace();
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


    /**
     * 判断数据是否下载*
     */
    private boolean isExists(String insponum, String inspotype) {


        List<Udinspo> list = new UdinspoDao(UdinspoNew_Activity.this).findByInspotype(inspotype);
        if (list != null && list.size() != 0) {
            for (Udinspo udinspo : list) {
                if (udinspo.getInsponum().equals(insponum))
                    return true;
            }
        }

        return false;
    }


    /**
     * 根据 编号下载数据
     */
    private void getUdinspoData(final String insponum) {
        HttpManager.getDataPagingInfo(this, HttpManager.getUdinspo(inspotype, assettype, checktype, insponum, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {

            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {


                try {
                    ArrayList<Udinspo> list1 = Ig_Json_Model.parseUdinspoString(results.getResultlist());
                    if (list1 == null || list1.isEmpty()) {
                    } else {
                        new UdinspoDao(UdinspoNew_Activity.this).create(list1);
                        for (Udinspo udinspo : list1) {
                            getUdinspoassetData1(udinspo.insponum);
                        }
                        udinspoListNewadapter.notifyDataSetChanged();
                        chooseList = new ArrayList<Udinspo>();
                        MessageUtils.showMiddleToast(UdinspoNew_Activity.this, "数据下载成功");
                        startActivity();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(String error) {
            }
        });
    }


    /**
     * 根据 Udinspo insponum获取Udinspoasset的信息
     * 孙表
     */
    private void getUdinspoassetData1(final String insponum) {
        HttpManager.getData(this, HttpManager.getUdinspoasseturl1(insponum), new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {
                Log.i(TAG, "sdata=" + data);

                ArrayList<Udinspoasset> items = null;

                try {
                    items = Ig_Json_Model.parseUdinspoassetString(data);
                    if (items == null || items.isEmpty()) {

                    } else {
                        new UdinspoAssetDao(UdinspoNew_Activity.this).create(items);
                        for (Udinspoasset udinspoasset : items) {
                            getUdinspojxxmData1(udinspoasset.udinspoassetnum);

                        }
                    }
                } catch (IOException e) {
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    nodatalayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {
                Log.i(TAG, "adata=" + data);
            }

            @Override
            public void onFailure(String error) {
                Log.i(TAG, "bdata=" + error);
            }
        });
    }





    /**
     * 根据 Udinspoasset udinspoassetnum获取Udinspojxxm的信息
     * 孙表
     */
    private void getUdinspojxxmData(final String udinspoassetnum) {
        HttpManager.getDataPagingInfo(this, HttpManager.getUdinspojxxmUrl(udinspoassetnum, "", page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<Udinspojxxm> items = null;
                Log.i(TAG, "JXXM data=" + results);
                try {
                    items = Ig_Json_Model.parseUdinspojxxmString(results.getResultlist());
                    if (items == null || items.isEmpty()) {
                    } else {
                        new UdinspojxxmDao(UdinspoNew_Activity.this).create(items);
                        for (Udinspojxxm udinspojxxm : items) {
                            if (!udinspojxxm.reportnum.equals("")) {
                                getUdreportData(udinspojxxm.reportnum);
                            }
                        }
                    }
                    MessageUtils.showMiddleToast(UdinspoNew_Activity.this, "数据下载成功");
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
     * 根据 Udinspoasset udinspoassetnum获取Udinspojxxm的信息
     * 孙表
     */
    private void getUdinspojxxmData1(final String udinspoassetnum) {
        HttpManager.getData(this, HttpManager.getUdinspojxxmUrl1(udinspoassetnum), new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {

                Log.i(TAG,"jxxm data="+data);
                ArrayList<Udinspojxxm> items = null;

                try {
                    items = Ig_Json_Model.parseUdinspojxxmString(data);
                    if (items == null || items.isEmpty()) {
                    } else {
                        new UdinspojxxmDao(UdinspoNew_Activity.this).create(items);
                        for (Udinspojxxm udinspojxxm : items) {
                            if (!udinspojxxm.reportnum.equals("")) {
                                getUdreportData(udinspojxxm.reportnum);
                            }
                        }
                    }

                } catch (IOException e) {
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    nodatalayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {

            }

            @Override
            public void onFailure(String error) {

            }
        });
    }


    /**
     * 根据 Udinspoasset udinspoassetnum获取Udreport的信息
     * 孙表
     */
    private void getUdreportData(final String reportnum) {
        Log.i(TAG, "reportnum=" + reportnum);
        HttpManager.getDataPagingInfo(this, HttpManager.getUdreport(reportnum), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<Udreport> items = null;
                Log.i(TAG, "Udreport =" + results.getResultlist());

                try {
                    items = Ig_Json_Model.parsingUdreport(results.getResultlist());
                    if (items == null || items.isEmpty()) {
                    } else {
                        new UdreportDao(UdinspoNew_Activity.this).create(items);

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


}
