package com.jf_eam_project.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
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
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnBtnEditClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.flyco.dialog.widget.NormalEditTextDialog;
import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.model.Wfassignment;
import com.jf_eam_project.ui.adapter.WfmListAdapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;
import com.jf_eam_project.utils.AccountUtils;
import com.jf_eam_project.webserviceclient.AndroidClientService;

import java.io.IOException;
import java.util.ArrayList;


/**
 * 流程审批的fragment
 */
public class Wfment_Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {


    private static final String TAG = "Wfment_Activity";

    /**
     * 返回按钮
     **/
    private ImageView backImageView;
    /**
     * 标题
     **/
    private TextView titleTextView;


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
    private WfmListAdapter wfmListAdapter;
    /**
     * 编辑框*
     */
    private EditText search;
    /**
     * 查询条件*
     */
    private String searchText = "";
    private int page = 1;


    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    private ProgressDialog mProgressDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wfm);
        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        search = (EditText) findViewById(R.id.search_edit);


        titleTextView.setText(getString(R.string.lcsp_text));
        backImageView.setOnClickListener(backImageViewOnLickListener);
    }


    /**
     * 设置事件监听*
     */
    @Override
    protected void initView() {
        setSearchEdit();

        layoutManager = new LinearLayoutManager(Wfment_Activity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        wfmListAdapter = new WfmListAdapter(Wfment_Activity.this);
        recyclerView.setAdapter(wfmListAdapter);
        refresh_layout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        refresh_layout.setRefreshing(true);

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);

        getData(searchText);

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
    }


    private View.OnClickListener backImageViewOnLickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };


    @Override
    public void onLoad() {
        page++;

        getData(searchText);
    }

    @Override
    public void onRefresh() {

        page = 1;
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
                                    getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString();
                    wfmListAdapter.removeAllData();
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
        HttpManager.getDataPagingInfo(Wfment_Activity.this, HttpManager.getWfmUrl(AccountUtils.getUserName(Wfment_Activity.this), search, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {


                ArrayList<Wfassignment> items = null;
                try {
                    items = Ig_Json_Model.parseWfmFromString(results.getResultlist());
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    if (items == null || items.isEmpty()) {
                        if (wfmListAdapter.getItemCount() != 0) {
                            wfmListAdapter.removeAllData();
                        }
                        nodatalayout.setVisibility(View.VISIBLE);
                    } else {
                        if (page == 1) {
                            wfmListAdapter = new WfmListAdapter(Wfment_Activity.this);
                            recyclerView.setAdapter(wfmListAdapter);
                        }
                        if (totalPages == page) {
                            wfmListAdapter.adddate(items);
                        }

                        wfmListAdapter.onClickListener = new WfmListAdapter.OnClickListener() {
                            @Override
                            public void cOnClickListener(int postion, Wfassignment wfassignment) {
                                MaterialDialogOneBtn(wfassignment);
                            }
                        };


                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(String error) {
                refresh_layout.setRefreshing(false);
                nodatalayout.setVisibility(View.VISIBLE);
            }
        });
    }


    private void MaterialDialogOneBtn(final Wfassignment wfassignment) {//审批工作流
        final MaterialDialog dialog = new MaterialDialog(Wfment_Activity.this);
        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(false);
        dialog.isTitleShow(false)//
                .btnNum(2)
                .content("是否填写输入意见")//
                .btnText("是", "否，直接提交")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {//是
                    @Override
                    public void onBtnClick() {
                        EditDialog(wfassignment, true);
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {//否
                    @Override
                    public void onBtnClick() {
                        wfgoon(wfassignment, wfassignment.getOwnerid(), "1", "");
                        dialog.dismiss();
                    }
                }
        );
    }

    private void EditDialog(final Wfassignment wfassignment, final boolean isok) {//输入审核意见
        final NormalEditTextDialog dialog = new NormalEditTextDialog(Wfment_Activity.this);
//        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(false);
        dialog.isTitleShow(false)//
                .btnNum(2)
                .content(isok ? "通过" : "不通过")//
                .btnText("提交", "取消")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnEditClickL() {
                    @Override
                    public void onBtnClick(String text) {
                        wfgoon(wfassignment, wfassignment.getOwnerid(), "1", text);

                        dialog.dismiss();
                    }
                },
                new OnBtnEditClickL() {
                    @Override
                    public void onBtnClick(String text) {

                        dialog.dismiss();
                    }
                }
        );
    }


    /**
     * 审批工作流
     *
     * @param id
     * @param zx
     */
    private void wfgoon(final Wfassignment wfassignment, final String id, final String zx, final String desc) {
        mProgressDialog = ProgressDialog.show(Wfment_Activity.this, null,
                getString(R.string.inputing), true, true);
//        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String result = null;
                if (wfassignment.app.equals("UDUPRAPP") && wfassignment.ownertable.equals("UDREPORT") && wfassignment.processname.equals("UDQXTB1")) {
                    Log.i(TAG, "缺陷单");
                    result = AndroidClientService.wfGoOn1(Wfment_Activity.this, "UDQXTB", "UDREPORT", wfassignment.ownerid, "UDREPORTID", zx, desc);
                } else {
                    result = AndroidClientService.wfGoOn1(Wfment_Activity.this, wfassignment.getProcessname(), wfassignment.getOwnertable(), id, wfassignment.getOwnertable() + "ID", zx, desc);
                }
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s == null || s.equals("")) {
                    Toast.makeText(Wfment_Activity.this, "审批失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Wfment_Activity.this, "审批成功", Toast.LENGTH_SHORT).show();
                }
                mProgressDialog.dismiss();
            }
        }.execute();
    }


}
