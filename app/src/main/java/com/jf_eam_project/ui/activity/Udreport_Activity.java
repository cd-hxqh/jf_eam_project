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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.jf_eam_project.Dao.UdreportDao;
import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.model.Udreport;
import com.jf_eam_project.ui.adapter.UdreportListAdapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;
import com.jf_eam_project.utils.AccountUtils;
import com.jf_eam_project.utils.JsonUtils;
import com.jf_eam_project.utils.MessageUtils;
import com.jf_eam_project.utils.NetWorkHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提报单列表
 */
public class Udreport_Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {

    private static final String TAG = "Udreport_Activity";
    private static final int ADD_REPORT = 1; //提报单新增

    @Bind(R.id.title_name)
    TextView titlename; //标题
    @Bind(R.id.title_back_id)
    ImageView backImageView; //返回按钮
    @Bind(R.id.title_add)
    ImageView menuImageView; //菜单按钮
    PopupWindow popupWindow;

    LinearLayoutManager layoutManager;


    @Bind(R.id.recyclerView_id)
    RecyclerView recyclerView; //RecyclerView

    @Bind(R.id.have_not_data_id)
    LinearLayout nodatalayout; //暂无数据
    /**
     * 界面刷新*
     */
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout refresh_layout;

    @Bind(R.id.search_edit)
    EditText search; //编辑框
    /**
     * 适配器*
     */
    private UdreportListAdapter udreportListAdapter;


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
     * 提报单类型*
     */
    private String apptype;


    @Bind(R.id.online_button_id)
    Button onLineButton; //在线

    /**
     * 离线*
     */
    @Bind(R.id.offline_button_id)
    Button offLineButton;//离线

    /**
     * 是否在线*
     */
    private boolean isShow;


    /**
     * 在线离线*
     */
    @Bind(R.id.loc_operation_linearlayout_id)
    LinearLayout isShowLinearLayout;

    /**
     * 本地操作*
     */
    @Bind(R.id.choose_linearlayout_id)
    LinearLayout operationLinearLayout;


    /**
     * 判断操作界面是否显示*
     */
    private boolean isoperationShow;


    /**
     * 选中的list*
     */
    private List<Udreport> chooseList = new ArrayList<Udreport>();

    /**
     * 全选*
     */
    @Bind(R.id.all_choose_id)
    Button allButton;
    /**
     * 上传*
     */
    @Bind(R.id.upload_choose_id)
    Button uploadButton;
    /**
     * 删除*
     */
    @Bind(R.id.delete_choose_id)
    Button deleteButton;


    /**
     * 判断是否全选*
     */
    private boolean aBoolean;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udreport_list);
        ButterKnife.bind(this);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取巡检单*
     */
    private void initData() {
        title = getIntent().getStringExtra("title");
        apptype = getIntent().getStringExtra("apptype");
    }

    @Override
    protected void findViewById() {

    }

    @Override
    protected void initView() {
        setSearchEdit();


        titlename.setText(title);
        menuImageView.setImageResource(R.drawable.ic_more);
        menuImageView.setVisibility(View.VISIBLE);


        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        /**在线**/
        udreportListAdapter = new UdreportListAdapter(this);
        udreportListAdapter.setIsShow(0);
        recyclerView.setAdapter(udreportListAdapter);
        refresh_layout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        refresh_layout.setRefreshing(true);
        if (NetWorkHelper.isNetwork(Udreport_Activity.this)) {
            MessageUtils.showMiddleToast(Udreport_Activity.this, getString(R.string.hava_not_netwok_text));
            getLocalData();
        } else {
            getData(searchText);
        }

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);

        /**默认在线选中**/


        isShowLinearLayout.setVisibility(View.VISIBLE);
        operationLinearLayout.setVisibility(View.GONE);


        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();

    }

    //在线
    @OnClick(R.id.online_button_id)
    void setOnLineButtonOnClickListener() {
        isShow = true;
        isShow(true);
    }

    //离线
    @OnClick(R.id.offline_button_id)
    void setOffLineButtonOnClickListener() {
        isShow = false;
        isShow(false);
    }


    /**
     * 判断在线离线*
     */
    private void isShow(boolean isShow) {
        if (isShow) { //在线
            onLineButton.setBackgroundColor(getResources().getColor(R.color.check_button_color2));
            offLineButton.setBackgroundColor(getResources().getColor(R.color.color0));
            getUdreport();
        } else { //本地提报单
            onLineButton.setBackgroundColor(getResources().getColor(R.color.color0));
            offLineButton.setBackgroundColor(getResources().getColor(R.color.check_button_color2));
            findByUdreport(apptype, 1);
        }
    }

    /**
     * 获取服务器提报单*
     */
    private void getUdreport() {
        refresh_layout.setRefreshing(true);
        udreportListAdapter.removeAllData();
        nodatalayout.setVisibility(View.GONE);
        isShowLinearLayout.setVisibility(View.VISIBLE);
        operationLinearLayout.setVisibility(View.GONE);

        if (NetWorkHelper.isNetwork(Udreport_Activity.this)) {
            getLocalData();
        } else {
            getData(searchText);
        }
    }


    /**
     * 查询本地未上传的提报单*
     */
    private void findByUdreport(String apptype, int loc) {
        udreportListAdapter.removeAllData();
        udreportListAdapter.setIsShow(1);
        final ArrayList<Udreport> list = (ArrayList<Udreport>) new UdreportDao(Udreport_Activity.this).queryByApptypeandLoc(apptype, loc);
        if (null == list || list.size() == 0) {
            nodatalayout.setVisibility(View.VISIBLE);

        } else {
            nodatalayout.setVisibility(View.GONE);

            udreportListAdapter.adddate(list);

        }

        udreportListAdapter.notifyDataSetChanged();

        /**长按事件**/
        udreportListAdapter.setOnLongClickListener(new UdreportListAdapter.OnLongClickListener() {
            @Override
            public void cOnLongClickListener() {
                isoperationShow = true;
                operationIsShow(isoperationShow, null);
                udreportListAdapter.setMark(1);
                udreportListAdapter.notifyDataSetChanged();
            }
        });


        /**点击事件**/
        udreportListAdapter.setOnClickListener(new UdreportListAdapter.OnClickListener() {
            @Override
            public void cOnClickListener(int postion, Udreport udreport) {

                if (operationLinearLayout.isShown()) {
                    operationLinearLayout.setVisibility(View.GONE);
                    operationLinearLayout.setAnimation((AnimationUtils.loadAnimation(Udreport_Activity.this, R.anim.slide_top_to_bottom)));
                    isShowLinearLayout.setVisibility(View.VISIBLE);
                    isShowLinearLayout.setAnimation((AnimationUtils.loadAnimation(Udreport_Activity.this, R.anim.slide_bottom_to_top)));
                    udreportListAdapter.setMark(0);
                    udreportListAdapter.notifyDataSetChanged();
                } else if (isShowLinearLayout.isShown()) {
                    startActivity(udreport);
                }


            }
        });

        /**选中事件**/
        udreportListAdapter.setOnCheckedChangeListener(new UdreportListAdapter.OnCheckedChangeListener() {
            @Override
            public void cOnCheckedChangeListener(boolean b, int postion) {
                if (b) {
                    chooseList.add(list.get(postion));
                } else {
                    if (null != chooseList && chooseList.size() != 0) {
                        chooseList.remove(postion);
                    }
                }
            }


        });

    }


    private void startActivity(Udreport udreport) {
        Intent intent = new Intent(Udreport_Activity.this, Udreport_Details_Activity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("mark", 0);
        bundle.putSerializable("udreport", udreport);
        intent.putExtras(bundle);
        startActivityForResult(intent, 0);
    }


    /**
     * 判断操作界面是否显示*
     */

    private void operationIsShow(boolean isShow, Udreport udreport) {

        if (isShow) {
            isShowLinearLayout.setVisibility(View.GONE);
            operationLinearLayout.setVisibility(View.VISIBLE);

            isShowLinearLayout.setAnimation((AnimationUtils.loadAnimation(Udreport_Activity.this, R.anim.slide_top_to_bottom)));
            operationLinearLayout.setAnimation((AnimationUtils.loadAnimation(Udreport_Activity.this, R.anim.slide_bottom_to_top)));

        } else {
            isShowLinearLayout.setVisibility(View.VISIBLE);
            operationLinearLayout.setVisibility(View.GONE);

            isShowLinearLayout.setAnimation((AnimationUtils.loadAnimation(Udreport_Activity.this, R.anim.slide_bottom_to_top)));
            operationLinearLayout.setAnimation((AnimationUtils.loadAnimation(Udreport_Activity.this, R.anim.slide_top_to_bottom)));
            startActivity(udreport);

        }
    }


    /**
     * 获取本地数据*
     */
    private void getLocalData() {

        ArrayList<Udreport> list = (ArrayList<Udreport>) new UdreportDao(Udreport_Activity.this).queryByapptype(apptype);
        refresh_layout.setRefreshing(false);
        refresh_layout.setLoading(false);
        if (list == null || list.isEmpty()) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {
            nodatalayout.setVisibility(View.GONE);
            udreportListAdapter.adddate(list);
            udreportListAdapter.setIsShow(0);
        }

        /**点击事件**/
        udreportListAdapter.setOnClickListener(new UdreportListAdapter.OnClickListener() {
            @Override
            public void cOnClickListener(int postion, Udreport udreport) {
                if (operationLinearLayout.isShown()) {
                    operationLinearLayout.setVisibility(View.GONE);
                    operationLinearLayout.setAnimation((AnimationUtils.loadAnimation(Udreport_Activity.this, R.anim.slide_top_to_bottom)));
                    isShowLinearLayout.setVisibility(View.VISIBLE);
                    isShowLinearLayout.setAnimation((AnimationUtils.loadAnimation(Udreport_Activity.this, R.anim.slide_bottom_to_top)));
                    udreportListAdapter.setMark(0);
                    udreportListAdapter.notifyDataSetChanged();
                } else if (isShowLinearLayout.isShown()) {
                    startActivity(udreport);
                }


            }
        });

    }

    //点击返回
    @OnClick(R.id.title_back_id)
    void setBackImageViewOnClickListener() {
        finish();
    }

    //菜单事件
    @OnClick(R.id.title_add)
    void setMenuImageViewOnClickListener() {
        showPopupWindow(menuImageView);
    }


    @Override
    public void onLoad() {
        page++;

        if (!NetWorkHelper.isNetwork(Udreport_Activity.this)) { //没有网络
            getData(searchText);
        } else {
            refresh_layout.setRefreshing(false);
            refresh_layout.setLoading(false);
        }
    }

    @Override
    public void onRefresh() {
        page = 1;

        if (!NetWorkHelper.isNetwork(Udreport_Activity.this)) { //没有网络
            udreportListAdapter.removeAllData();
            getData(searchText);
        } else {
            refresh_layout.setRefreshing(false);
            refresh_layout.setLoading(false);
        }
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
                                    Udreport_Activity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString();
                    udreportListAdapter.removeAllData();
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
        String statustype = "=未提报,=已提报,=处理中,=已处理";
        HttpManager.getDataPagingInfo(this, HttpManager.getUdreport(apptype, AccountUtils.getDepartment(Udreport_Activity.this), statustype, search, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {


                ArrayList<Udreport> items = null;
                try {
                    items = Ig_Json_Model.parsingUdreport(results.getResultlist());
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    if (items == null || items.isEmpty()) {
                        nodatalayout.setVisibility(View.VISIBLE);
                    } else {
                        if (page == 1) {
                            udreportListAdapter = new UdreportListAdapter(Udreport_Activity.this);
                            recyclerView.setAdapter(udreportListAdapter);
                        }
                        if (totalPages == page) {
                            new UdreportDao(Udreport_Activity.this).create(items);
                            udreportListAdapter.adddate(items);
                        }


                        /**点击事件**/
                        udreportListAdapter.setOnClickListener(new UdreportListAdapter.OnClickListener() {
                            @Override
                            public void cOnClickListener(int postion, Udreport udreport) {

                                if (operationLinearLayout.isShown()) {
                                    operationLinearLayout.setVisibility(View.GONE);
                                    operationLinearLayout.setAnimation((AnimationUtils.loadAnimation(Udreport_Activity.this, R.anim.slide_top_to_bottom)));
                                    isShowLinearLayout.setVisibility(View.VISIBLE);
                                    isShowLinearLayout.setAnimation((AnimationUtils.loadAnimation(Udreport_Activity.this, R.anim.slide_bottom_to_top)));
                                    udreportListAdapter.setMark(0);
                                    udreportListAdapter.notifyDataSetChanged();
                                } else if (isShowLinearLayout.isShown()) {
                                    startActivity(udreport);
                                }


                            }
                        });
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


    /**
     * 全选*
     */
    @OnClick(R.id.all_choose_id)
    void setAllOnClickListener() {
        if (aBoolean) {
            aBoolean = false;
            allButton.setText("全选");
            chooseList = new ArrayList<Udreport>();
        } else {
            allButton.setText("全不选");
            aBoolean = true;
        }
        udreportListAdapter.setAllChoose(aBoolean);
        udreportListAdapter.notifyDataSetChanged();
    }


    /**
     * 上传*
     */
    @OnClick(R.id.upload_choose_id)
    void setUploadOnClickListener() {
        {
            if (null == chooseList || chooseList.size() == 0) {
                MessageUtils.showMiddleToast(Udreport_Activity.this, "请选择上传数据...");
            } else {
                alerDialog(chooseList.size());
            }
        }
    }


    /**
     * 删除*
     */
    @OnClick(R.id.delete_choose_id)
    void setDeleteOnClickListener() {
        if (chooseList != null && chooseList.size() != 0) {
            deleteData(chooseList);
        } else {
            MessageUtils.showMiddleToast(Udreport_Activity.this, "请选择需要删除的任务");
        }
    }


    /**
     * 上传弹出框*
     */
    private void alerDialog(int size) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Udreport_Activity.this);
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


                if (NetWorkHelper.isNetwork(Udreport_Activity.this)) {
                    MessageUtils.showMiddleToast(Udreport_Activity.this, "暂无网络,上传失败");
                } else {

                    mProgressDialog = ProgressDialog.show(Udreport_Activity.this, null,
                            getString(R.string.inputing), true, true);
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.setCancelable(false);
                    new AsyncTask<String, String, String>() {
                        @Override
                        protected String doInBackground(String... strings) {
                            String result = null;
                            if (chooseList != null && chooseList.size() != 0) {
                                for (int i = 0; i < chooseList.size(); i++) {
                                    String data = JsonUtils.saveUdreport(chooseList.get(i));

                                    if (data != null || !data.isEmpty()) {
                                        result = getBaseApplication().getWsService().addReport(Udreport_Activity.this, data, "");

                                    }
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
                                String success = jsonObject.getString("Msg");
                                String errorNo = jsonObject.getString("errorNo");

                                if (success.equals("数据插入成功") && errorNo.equals("0")) {
                                    MessageUtils.showMiddleToast(Udreport_Activity.this, "上传成功");
                                    deleteListUdreport(chooseList);
                                    udreportListAdapter.removeAllData();

                                    ArrayList<Udreport> list1 = (ArrayList<Udreport>) new UdreportDao(Udreport_Activity.this).queryByApptypeandLoc(apptype, 1);
                                    if (list1 == null || list1.size() == 0) {

                                        nodatalayout.setVisibility(View.VISIBLE);
                                        if (nodatalayout.isShown()) {
                                            operationLinearLayout.setVisibility(View.GONE);
                                            operationLinearLayout.setAnimation((AnimationUtils.loadAnimation(Udreport_Activity.this, R.anim.slide_top_to_bottom)));
                                            isShowLinearLayout.setVisibility(View.VISIBLE);
                                            isShowLinearLayout.setAnimation((AnimationUtils.loadAnimation(Udreport_Activity.this, R.anim.slide_bottom_to_top)));

                                        }
                                    }
                                    udreportListAdapter.adddate(list1);
                                    udreportListAdapter.notifyDataSetChanged();
                                } else {
                                    MessageUtils.showMiddleToast(Udreport_Activity.this, "上传失败");
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
     * 数据删除
     */
    private void deleteData(final List<Udreport> chooseList) {

        AlertDialog.Builder builder = new AlertDialog.Builder(Udreport_Activity.this);
        builder.setMessage("已选择" + chooseList.size() + "条记录，确定删除吗？").setTitle("提示")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                mProgressDialog = ProgressDialog.show(Udreport_Activity.this, null, "删除中...", true, true);
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setCancelable(false);


                deleteListUdreport(chooseList);


                udreportListAdapter.removeAllData();

                ArrayList<Udreport> list1 = null;

                list1 = (ArrayList<Udreport>) new UdreportDao(Udreport_Activity.this).queryByApptypeandLoc(apptype, 1);


                if (list1 == null || list1.size() == 0) {
                    nodatalayout.setVisibility(View.VISIBLE);
                }
                udreportListAdapter.adddate(list1);
                udreportListAdapter.notifyDataSetChanged();
                mProgressDialog.dismiss();
            }
        }).create().show();


    }


    /**
     * 根据删除选中的数据*
     */
    private void deleteListUdreport(List<Udreport> list) {
        //删除Udreport
        new UdreportDao(Udreport_Activity.this).deleteList(list);

    }


    /**
     * 初始化showPopupWindow*
     */
    private void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(Udreport_Activity.this).inflate(
                R.layout.workorder_popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.popup_background_mtrl_mult));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);
        TextView addTextView = (TextView) contentView.findViewById(R.id.add_text_id);

        TextView lsTextView = (TextView) contentView.findViewById(R.id.workorder_ls_text_id);
        addTextView.setOnClickListener(addTextViewOnClickListener);
        lsTextView.setOnClickListener(lsTextViewOnClickListener);

    }

    private View.OnClickListener addTextViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Udreport_Activity.this, Udreport_Add_Activity.class);
            intent.putExtra("apptype", apptype);
            startActivityForResult(intent, 0);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener lsTextViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            intent.setClass(Udreport_Activity.this, UdreportHis_Activity.class);
            intent.putExtra("apptype", apptype);
            startActivityForResult(intent, 0);
            popupWindow.dismiss();
        }
    };

}
