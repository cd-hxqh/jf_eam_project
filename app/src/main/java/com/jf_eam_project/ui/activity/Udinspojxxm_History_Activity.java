package com.jf_eam_project.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jf_eam_project.Dao.UdinspoDao;
import com.jf_eam_project.Dao.UdinspojxxmDao;
import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Udinspo;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.model.Udinspojxxm;
import com.jf_eam_project.ui.adapter.UdinspoListadapter;
import com.jf_eam_project.ui.adapter.UdinspojxxmListAdapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;
import com.jf_eam_project.utils.MessageUtils;
import com.jf_eam_project.utils.NetWorkHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 巡检项目标准本地历史
 */
public class Udinspojxxm_History_Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static final String TAG = "Udinspo_History_Activity";

    /**
     * 标题
     */
    private TextView titleView;
    /**
     * 返回
     */
    private ImageView backImageView;


    LinearLayoutManager layoutManager;
    /**
     * RecyclerView*
     */
    public RecyclerView recyclerView;

    private LinearLayout nodatalayout;
    private SwipeRefreshLayout refresh_layout = null;

    private UdinspojxxmListAdapter udinspojxxmListAdapter;
    private EditText search;
    private String searchText = "";
    private int page = 1;


    private UdinspojxxmDao udinspojxxmDao;

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


    private ProgressDialog mProgressDialog;

    ArrayList<Udinspojxxm> list = new ArrayList<Udinspojxxm>();
    ArrayList<Udinspojxxm> chooseList = new ArrayList<Udinspojxxm>();

    ArrayList<Udinspoasset> udinspoassets = new ArrayList<Udinspoasset>();

    private String udinspoassetnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initDao();
        getInitData();
        findViewById();
        initView();
    }

    /**
     * 获取上个界面的数据*
     */
    private void getInitData() {
        udinspoassetnum = getIntent().getExtras().getString("udinspoassetnum");
    }

    /**
     * 初始化DAO*
     */
    private void initDao() {
        udinspojxxmDao = new UdinspojxxmDao(Udinspojxxm_History_Activity.this);
    }


    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

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
        titleView.setText(getString(R.string.udinspojxxm_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);

        setSearchEdit();


        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        udinspojxxmListAdapter = new UdinspojxxmListAdapter(this, 1);
        recyclerView.setAdapter(udinspojxxmListAdapter);
        refresh_layout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        getData(searchText);
        refresh_layout.setRefreshing(false);
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);

        allTextView.setOnClickListener(allOnClickListener);
        uploadTextView.setOnClickListener(uploadOnClickListener);
        deleteTextView.setOnClickListener(deleteOnClickListener);

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
                chooseList = new ArrayList<Udinspojxxm>();
            }
            udinspojxxmListAdapter.setAllChoose(aBoolean);
            udinspojxxmListAdapter.notifyDataSetChanged();

        }
    };

    /**
     * 上传*
     */
    private View.OnClickListener uploadOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (chooseList.size() == 0) {
                MessageUtils.showMiddleToast(Udinspojxxm_History_Activity.this, "请选择上传数据...");
            } else {
                encapsulationData(chooseList.size());
            }
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

    private void getData(String search) {
        if (search.equals("")) {
            list = (ArrayList<Udinspojxxm>) udinspojxxmDao.queryByNum(udinspoassetnum);
        } else {
            list = (ArrayList<Udinspojxxm>) udinspojxxmDao.queryByDesc(search);
        }
        if (list != null && list.size() != 0) {
            udinspojxxmListAdapter.adddate(list);
        } else {
            nodatalayout.setVisibility(View.VISIBLE);
        }
        udinspojxxmListAdapter.setOnLongClickListener(new UdinspojxxmListAdapter.OnLongClickListener() {
            @Override
            public void cOnLongClickListener() {
                chooseLinearLayout.setVisibility(View.VISIBLE);
                chooseLinearLayout.setAnimation((AnimationUtils.loadAnimation(Udinspojxxm_History_Activity.this, R.anim.slide_bottom_to_top)));
                udinspojxxmListAdapter.setMark(1);
                udinspojxxmListAdapter.notifyDataSetChanged();
            }
        });

        udinspojxxmListAdapter.setOnCheckedChangeListener(new UdinspojxxmListAdapter.OnCheckedChangeListener() {
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
                    // 隐藏软件盘
                    ((InputMethodManager) search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    Udinspojxxm_History_Activity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString();
                    udinspojxxmListAdapter.removeAllData();
                    nodatalayout.setVisibility(View.GONE);
                    page = 1;
                    getData(searchText);
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 返回点击
     */
    private View.OnClickListener backImageViewOnClickListenrer = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    @Override
    public void onLoad() {
    }

    @Override
    public void onRefresh() {

        refresh_layout.setRefreshing(false);
    }

    @Override
    public void onBackPressed() {
        if (chooseLinearLayout.getVisibility() == View.VISIBLE) {
            chooseLinearLayout.setAnimation((AnimationUtils.loadAnimation(Udinspojxxm_History_Activity.this, R.anim.slide_top_to_bottom)));
            chooseLinearLayout.setVisibility(View.GONE);
            udinspojxxmListAdapter.setMark(0);
            udinspojxxmListAdapter.notifyDataSetChanged();
        }

        return;
    }


    /**
     * 数据封装*
     */
    private void encapsulationData(int size) {

        AlertDialog.Builder builder = new AlertDialog.Builder(Udinspojxxm_History_Activity.this);
        builder.setMessage("已选择" + size + "条记录，确定上传吗？").setTitle("提示")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                mProgressDialog = ProgressDialog.show(Udinspojxxm_History_Activity.this, null,
                        getString(R.string.inputing), true, true);
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setCancelable(false);

                if (NetWorkHelper.isNetwork(Udinspojxxm_History_Activity.this)) {
                    MessageUtils.showMiddleToast(Udinspojxxm_History_Activity.this, "暂无网络,上传失败");
                    mProgressDialog.dismiss();
                } else {


                    new AsyncTask<String, String, String>() {
                        @Override
                        protected String doInBackground(String... strings) {
                            String result = null;
                            if (chooseList != null && chooseList.size() != 0) {
                                for (int i = 0; i < chooseList.size(); i++) {
                                    String data = null;
                                    try {
                                        data = submitData(chooseList.get(i));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    result = getBaseApplication().getWsService().UpdatePO(Udinspojxxm_History_Activity.this,data, "");
                                }
                            }
                            return result;
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            mProgressDialog.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                String success = jsonObject.getString("status");
                                String errorNo = jsonObject.getString("errorNo");

                                if (success.equals("数据更新成功") && errorNo.equals("0")) {
                                    MessageUtils.showMiddleToast(Udinspojxxm_History_Activity.this, "提交成功");
                                    udinspojxxmDao.deleteList(chooseList);

                                    udinspojxxmListAdapter.removeAllData();

                                    ArrayList<Udinspojxxm> list1 = (ArrayList<Udinspojxxm>) udinspojxxmDao.queryByNum(udinspoassetnum);
                                    if (list1 == null || list1.size() == 0) {
                                        nodatalayout.setVisibility(View.VISIBLE);
                                    }
                                    udinspojxxmListAdapter.adddate(list1);
                                    udinspojxxmListAdapter.notifyDataSetChanged();
                                } else {
                                    MessageUtils.showMiddleToast(Udinspojxxm_History_Activity.this, "提交失败");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }.execute();
                }
            }
        }).create().show();


    }

    /**
     * 封装数据*
     */
    private String submitData(Udinspojxxm udinspo) throws JSONException {


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("UDINSPOJXXMID", udinspo.udinspojxxmid + "");
        jsonObject.put("UDINSPOASSETNUM", udinspo.udinspoassetnum);
        jsonObject.put("TYPE", Constants.UPDATE);
        jsonObject.put("DESCRIPTION", udinspo.description);
        jsonObject.put("EXECUTION", udinspo.execution);
        jsonObject.put("CHECKBY", udinspo.checkby);

        JSONObject jsons = new JSONObject();
        jsons.put("GRADESON", jsonUdinspojxxmInfo(jsonObject.toString()));


        return jsons.toString();
    }

    /**
     * 封装udinspojxxm信息*
     */
    private JSONArray jsonUdinspojxxmInfo(String str) {
        JSONArray jsonArray = null;

        String json3 = "";
        try {
            json3 = "[" + str + "]";

            jsonArray = new JSONArray(json3);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return jsonArray;
    }


    /**
     * 数据删除
     */
    private void deleteData(int size) {

        AlertDialog.Builder builder = new AlertDialog.Builder(Udinspojxxm_History_Activity.this);
        builder.setMessage("已选择" + size + "条记录，确定删除吗吗？").setTitle("提示")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                mProgressDialog = ProgressDialog.show(Udinspojxxm_History_Activity.this, null, "删除中...", true, true);
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setCancelable(false);
                udinspojxxmDao.deleteList(chooseList);

                udinspojxxmListAdapter.removeAllData();

                ArrayList<Udinspojxxm> list1 = (ArrayList<Udinspojxxm>) udinspojxxmDao.queryByNum(udinspoassetnum);
                if (list1 == null || list1.size() == 0) {
                    nodatalayout.setVisibility(View.VISIBLE);
                }
                udinspojxxmListAdapter.adddate(list1);
                udinspojxxmListAdapter.notifyDataSetChanged();
                mProgressDialog.dismiss();
            }
        }).create().show();


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


}
