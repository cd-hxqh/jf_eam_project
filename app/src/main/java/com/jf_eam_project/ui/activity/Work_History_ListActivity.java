package com.jf_eam_project.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
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
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.jf_eam_project.Dao.AssignmentDao;
import com.jf_eam_project.Dao.LabtransDao;
import com.jf_eam_project.Dao.WoactivityDao;
import com.jf_eam_project.Dao.WorkOrderDao;
import com.jf_eam_project.Dao.WplaborDao;
import com.jf_eam_project.Dao.WpmeterialDao;
import com.jf_eam_project.R;
import com.jf_eam_project.api.JsonUtils;
import com.jf_eam_project.model.Assignment;
import com.jf_eam_project.model.Labtrans;
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.model.Wplabor;
import com.jf_eam_project.model.Wpmaterial;
import com.jf_eam_project.ui.adapter.WorkListAdapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;
import com.jf_eam_project.utils.MessageUtils;
import com.jf_eam_project.utils.NetWorkHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2016/1/15.
 * 工单本地历史列表界面
 */
public class Work_History_ListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
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
    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private SwipeRefreshLayout refresh_layout = null;
    private WorkListAdapter workListAdapter;
    private EditText search;
    private String searchText = "";

    /**
     * 选项操作*
     */
    private LinearLayout chooseLinearLayout;


    /**
     * 全选*
     */
    private TextView allTextView;
    /**
     * 上传*
     */
    private TextView uploadTextView;
    /**
     * 删除*
     */
    private TextView deleteTextView;


    /**
     * 判断是否全选*
     */
    private boolean aBoolean;

    private WorkOrderDao workOrderDao;

    private ProgressDialog mProgressDialog;
    ArrayList<WorkOrder> list = new ArrayList<>();
    ArrayList<WorkOrder> chooseList = new ArrayList<WorkOrder>();

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initDao();
        findViewById();
        initView();
    }

    /**
     * 初始化DAO*
     */
    private void initDao() {
        workOrderDao = new WorkOrderDao(Work_History_ListActivity.this);
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImage = (ImageView) findViewById(R.id.title_back_id);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        search = (EditText) findViewById(R.id.search_edit);

        chooseLinearLayout = (LinearLayout) findViewById(R.id.choose_linearlayout_id);
        allTextView = (TextView) findViewById(R.id.all_choose_id);
        uploadTextView = (TextView) findViewById(R.id.upload_choose_id);
        deleteTextView = (TextView) findViewById(R.id.delete_choose_id);
    }

    @Override
    protected void initView() {
        setSearchEdit();
        titlename.setText(R.string.title_activity_work_list);

        backImage.setOnClickListener(new View.OnClickListener() {
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
        workListAdapter = new WorkListAdapter(this, 1);
        recyclerView.setAdapter(workListAdapter);
        refresh_layout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        refresh_layout.setRefreshing(false);
        getData(searchText);

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);

        allTextView.setOnClickListener(allOnClickListener);
        uploadTextView.setOnClickListener(uploadOnClickListener);
        deleteTextView.setOnClickListener(deleteOnClickListener);

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
    }

    private void getData(String search) {
        if (search.equals("")) {
            list = (ArrayList<WorkOrder>) workOrderDao.queryForLoc();
        } else {
            list = (ArrayList<WorkOrder>) workOrderDao.queryByWonumForLoc(search);
        }
        if (list != null && list.size() != 0) {
            workListAdapter.adddate(list);
        } else {
            nodatalayout.setVisibility(View.VISIBLE);
        }
        workListAdapter.setOnLongClickListener(new WorkListAdapter.OnLongClickListener() {
            @Override
            public void cOnLongClickListener() {

                chooseLinearLayout.setVisibility(View.VISIBLE);
                chooseLinearLayout.setAnimation((AnimationUtils.loadAnimation(Work_History_ListActivity.this, R.anim.slide_bottom_to_top)));
                workListAdapter.setMark(1);
                workListAdapter.notifyDataSetChanged();
            }
        });
        workListAdapter.setOnCheckedChangeListener(new WorkListAdapter.OnCheckedChangeListener() {
            @Override
            public void cOnCheckedChangeListener(int postion) {
                chooseList.add(list.get(postion));
            }
        });
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
                                    Work_History_ListActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString();
                    workListAdapter = new WorkListAdapter(Work_History_ListActivity.this, 1);
                    recyclerView.setAdapter(workListAdapter);
                    getData(searchText);
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 全选*
     */
    private View.OnClickListener allOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (aBoolean) {
                aBoolean = false;
                allTextView.setText("全选");
                addListData();
            } else {
                allTextView.setText("全不选");
                aBoolean = true;
                chooseList = new ArrayList<WorkOrder>();
            }
            workListAdapter.setAllChoose(aBoolean);
            workListAdapter.notifyDataSetChanged();

        }
    };

    /**
     * 上传*
     */
    private View.OnClickListener uploadOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            encapsulationData(chooseList.size());
        }
    };
    /**
     * 删除*
     */
    private View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            deleteData(chooseList.size());
        }
    };

    @Override
    public void onBackPressed() {
        if (chooseLinearLayout.getVisibility() == View.VISIBLE) {
            chooseLinearLayout.setAnimation((AnimationUtils.loadAnimation(Work_History_ListActivity.this, R.anim.slide_top_to_bottom)));
            chooseLinearLayout.setVisibility(View.GONE);
            workListAdapter.setMark(0);
            workListAdapter.notifyDataSetChanged();
        }

        return;
    }

    /**
     * 全选*
     */
    private void addListData() {
        if (list != null && list.size() == 0) {
            for (int i = 0; i < list.size(); i++) {
                chooseList.add(list.get(i));
            }
        }
    }

    /**
     * 数据封装*
     */
    private void encapsulationData(int size) {
        final NormalDialog dialog = new NormalDialog(Work_History_ListActivity.this);
        dialog.content("已选择" + size + "条记录，确定上传吗？")//
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
                        dialog.dismiss();
                        mProgressDialog = ProgressDialog.show(Work_History_ListActivity.this, null,
                                getString(R.string.inputing), true, true);
                        mProgressDialog.setCanceledOnTouchOutside(false);
                        mProgressDialog.setCancelable(false);

                        if (NetWorkHelper.isNetwork(Work_History_ListActivity.this)) {
                            MessageUtils.showMiddleToast(Work_History_ListActivity.this, "暂无网络,上传失败");
                            mProgressDialog.dismiss();
                        } else {

                            new AsyncTask<String, String, String>() {
                                @Override
                                protected String doInBackground(String... strings) {
                                    String result = null;
                                    if (chooseList != null && chooseList.size() != 0) {
                                        for (int i = 0; i < chooseList.size(); i++) {
                                            final String updataInfo = JsonUtils.WorkToJson(chooseList.get(i), getWoactivityList(chooseList.get(i).id),
                                                    getWplaborList(chooseList.get(i).id), getWpmaterialList(chooseList.get(i).id),
                                                    getAssignmentList(chooseList.get(i).id), getLabtransList(chooseList.get(i).id));
                                            result = getBaseApplication().getWsService().InsertWO(Work_History_ListActivity.this, updataInfo, getBaseApplication().getUsername());
                                        }
                                    }
                                    return result;
                                }

                                @Override
                                protected void onPostExecute(String s) {
                                    super.onPostExecute(s);
                                    mProgressDialog.dismiss();

                                    if (s != null && !s.equals("")) {
                                        MessageUtils.showMiddleToast(Work_History_ListActivity.this, "工单" + s + "提交成功");
                                        deleteList(chooseList);
                                        workListAdapter.removeAllData();
                                        ArrayList<WorkOrder> list1 = (ArrayList<WorkOrder>) workOrderDao.queryForAll();
                                        if (list1 == null && list1.size() == 0) {
                                            nodatalayout.setVisibility(View.VISIBLE);
                                        }
                                        workListAdapter.adddate(list1);
                                        workListAdapter.notifyDataSetChanged();

                                    } else {
                                        MessageUtils.showMiddleToast(Work_History_ListActivity.this, "提交失败");
                                    }
                                }
                            }.execute();
                        }
                    }
                });
    }

    private void deleteList(ArrayList<WorkOrder> list) {
        for (int i = 0; i < list.size(); i++) {
            new WoactivityDao(Work_History_ListActivity.this).deleteByWonum(list.get(i).id);
            new WplaborDao(Work_History_ListActivity.this).deleteByWonum(list.get(i).id);
            new WpmeterialDao(Work_History_ListActivity.this).deleteByWonum(list.get(i).id);
            new AssignmentDao(Work_History_ListActivity.this).deleteByWonum(list.get(i).id);
            new LabtransDao(Work_History_ListActivity.this).deleteByWonum(list.get(i).id);
        }
        new WorkOrderDao(Work_History_ListActivity.this).deleteList(list);
    }

    private ArrayList<Woactivity> getWoactivityList(int workorderid) {
        return (ArrayList<Woactivity>) new WoactivityDao(Work_History_ListActivity.this).queryByWonum(workorderid);
    }

    private ArrayList<Wplabor> getWplaborList(int workorderid) {
        return (ArrayList<Wplabor>) new WplaborDao(Work_History_ListActivity.this).queryByWonum(workorderid);
    }

    private ArrayList<Wpmaterial> getWpmaterialList(int workorderid) {
        return (ArrayList<Wpmaterial>) new WpmeterialDao(Work_History_ListActivity.this).queryByWonum(workorderid);
    }

    private ArrayList<Assignment> getAssignmentList(int workorderid) {
        return (ArrayList<Assignment>) new AssignmentDao(Work_History_ListActivity.this).queryByWonum(workorderid);
    }

    private ArrayList<Labtrans> getLabtransList(int workorderid) {
        return (ArrayList<Labtrans>) new LabtransDao(Work_History_ListActivity.this).queryByWonum(workorderid);
    }

    /**
     * 数据删除  已选择" + size + "条记录，确定删除吗吗？
     */
    private void deleteData(int size) {
        final NormalDialog dialog = new NormalDialog(Work_History_ListActivity.this);
        dialog.content("已选择" + size + "条记录，确定删除吗？")//
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
                        dialog.dismiss();
                        mProgressDialog = ProgressDialog.show(Work_History_ListActivity.this, null, "删除中...", true, true);
                        mProgressDialog.setCanceledOnTouchOutside(false);
                        mProgressDialog.setCancelable(false);
                        Log.i(TAG, "chooseList size=" + chooseList.size());
                        deleteList(chooseList);
                        workListAdapter.removeAllData();
                        ArrayList<WorkOrder> list1 = (ArrayList<WorkOrder>) workOrderDao.queryForAll();
                        if (list1 == null && list1.size() == 0) {
                            nodatalayout.setVisibility(View.VISIBLE);
                        }
                        workListAdapter.adddate(list1);
                        workListAdapter.notifyDataSetChanged();
                        mProgressDialog.dismiss();
                    }
                });


    }


    @Override
    public void onLoad() {
    }

    @Override
    public void onRefresh() {

        refresh_layout.setRefreshing(false);
    }
}
