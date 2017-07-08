package com.jf_eam_project.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jf_eam_project.Dao.UdinspoAssetDao;
import com.jf_eam_project.Dao.UdinspoDao;
import com.jf_eam_project.Dao.UdinspojxxmDao;
import com.jf_eam_project.R;
import com.jf_eam_project.api.JsonUtils;
import com.jf_eam_project.model.Udinspo;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.model.Udinspojxxm;
import com.jf_eam_project.ui.adapter.UdinspoLocationadapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;
import com.jf_eam_project.utils.MessageUtils;
import com.jf_eam_project.utils.NetWorkHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 巡检单列表
 */
public class UdinspoLocation_Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {

    private static final String TAG = "UdinspoLocation_Activity";


    @Bind(R.id.title_name)
    TextView titlename; //标题

    @Bind(R.id.title_back_id)
    ImageView backImageView;//返回按钮


    LinearLayoutManager layoutManager;


    @Bind(R.id.recyclerView_id)
    RecyclerView recyclerView;//RecyclerView

    @Bind(R.id.have_not_data_id)
    LinearLayout nodatalayout;//暂无数据

    @Bind(R.id.swipe_container)
    SwipeRefreshLayout refresh_layout;//界面刷新
    /**
     * 适配器*
     */
    private UdinspoLocationadapter udinspoLocationadapter;

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
    private String assettype = "";
    /**
     * checktype*
     */
    private String checktype = "";


    @Bind(R.id.all_choose_id)
    TextView allTextView;//全选

    @Bind(R.id.upload_choose_id)
    TextView uploadTextView;//上传

    @Bind(R.id.delete_choose_id)
    TextView deleteTextView;//删除

    /**
     * 判断是否全选*
     */
    private boolean aBoolean;


    ArrayList<Udinspo> list = new ArrayList<Udinspo>();
    ArrayList<Udinspo> chooseList = new ArrayList<Udinspo>();

    private ProgressDialog mProgressDialog;

    private UdinspoDao udinspoDao;


    /**
     * 被选中的Udinspojxxm数据*
     */
    private List<Udinspojxxm> chooseUdinspojxxmList = new ArrayList<Udinspojxxm>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);
        initDao();
        initData();
        findViewById();
        initView();
    }

    /**
     * 初始化DAO*
     */
    private void initDao() {
        udinspoDao = new UdinspoDao(UdinspoLocation_Activity.this);

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
    }

    @Override
    protected void initView() {


        titlename.setText(title);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        udinspoLocationadapter = new UdinspoLocationadapter(this);
        recyclerView.setAdapter(udinspoLocationadapter);
        refresh_layout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        refresh_layout.setRefreshing(true);
        getLocalData();

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);


    }

    /**
     * 获取本地数据*
     */
    private void getLocalData() {
        if (assettype.equals("") && checktype.equals("")) {
            list = (ArrayList<Udinspo>) new UdinspoDao(UdinspoLocation_Activity.this).findByInspotype(inspotype);

        } else {
            list = (ArrayList<Udinspo>) new UdinspoDao(UdinspoLocation_Activity.this).findByType(assettype, checktype);
        }
        refresh_layout.setRefreshing(false);
        refresh_layout.setLoading(false);
        if (list == null || list.isEmpty()) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {

            udinspoLocationadapter.adddate(list);
        }

        udinspoLocationadapter.setOnCheckedChangeListener(new UdinspoLocationadapter.OnCheckedChangeListener() {
            @Override
            public void cOnCheckedChangeListener(int postion) {
                chooseList.add(list.get(postion));
            }
        });

    }

    //返回事件
    @OnClick(R.id.title_back_id)
    void setBackImageViewOnClickListener() {
        finish();
    }

    //全选
    @OnClick(R.id.all_choose_id)
    void setAllOnClickListener() {
        if (aBoolean) {
            aBoolean = false;
            allTextView.setText("全选");
            chooseList = new ArrayList<Udinspo>();
//                addListData();
        } else {
            allTextView.setText("全不选");
            aBoolean = true;

        }
        udinspoLocationadapter.setAllChoose(aBoolean);
        udinspoLocationadapter.notifyDataSetChanged();
    }

    //上传
    @OnClick(R.id.upload_choose_id)
    void setUploadOnClickListener() {
        if (chooseList.size() == 0) {
            MessageUtils.showMiddleToast(UdinspoLocation_Activity.this, "请选择上传数据...");
        } else {


//            encapsulationData(chooseList);

            alerDialog(chooseList);
        }
    }

    //删除
    @OnClick(R.id.delete_choose_id)
    void setDeleteOnClickListener() {
        if (chooseList != null && chooseList.size() != 0) {
            deleteData(chooseList);
        } else {
            MessageUtils.showMiddleToast(UdinspoLocation_Activity.this, "请选择需要删除的任务");
        }
    }


    @Override
    public void onLoad() {
        page = 1;

        refresh_layout.setRefreshing(false);
        refresh_layout.setLoading(false);
    }

    @Override
    public void onRefresh() {
        page++;
        refresh_layout.setRefreshing(false);
        refresh_layout.setLoading(false);
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
     * 数据删除
     */
    private void deleteData(final ArrayList<Udinspo> chooseList) {

        AlertDialog.Builder builder = new AlertDialog.Builder(UdinspoLocation_Activity.this);
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
                mProgressDialog = ProgressDialog.show(UdinspoLocation_Activity.this, null, "删除中...", true, true);
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setCancelable(false);


                //根据insponum删除Udinspoasset信息
                deleteListUdinspoasset(chooseList);


                udinspoLocationadapter.removeAllData();

                ArrayList<Udinspo> list1 = null;

                if (assettype.equals("") && checktype.equals("")) {
                    list1 = (ArrayList<Udinspo>) new UdinspoDao(UdinspoLocation_Activity.this).findByInspotype(inspotype);

                } else {
                    list1 = (ArrayList<Udinspo>) new UdinspoDao(UdinspoLocation_Activity.this).findByType(assettype, checktype);
                }

                if (list1 == null || list1.size() == 0) {
                    nodatalayout.setVisibility(View.VISIBLE);
                }
                udinspoLocationadapter.adddate(list1);
                udinspoLocationadapter.notifyDataSetChanged();
                mProgressDialog.dismiss();
            }
        }).create().show();


    }


    /**
     * 根据insponum删除Udinspoasset的信息*
     */
    private void deleteListUdinspoasset(List<Udinspo> list) {
        //删除Udinspo数据
        udinspoDao.deleteList(list);
        if (null != list && list.size() != 0) {
            for (Udinspo udinspo : list) {
                String insponum = udinspo.insponum;
                //根据insponum删除Udinspoasset
                delUdinspoasset(insponum);
            }
        }
    }


    /**
     * 删除Udinspoasset*
     */
    private void delUdinspoasset(String insponum) {

        List<Udinspoasset> list = new UdinspoAssetDao(UdinspoLocation_Activity.this).queryByInsponum(insponum);
        new UdinspoAssetDao(UdinspoLocation_Activity.this).deleteInsponum(insponum);
        if (null != list && list.size() != 0) {
            for (Udinspoasset udinspoasset : list) {
                String udinspoassetnum = udinspoasset.udinspoassetnum;
                //根据udinspoassetnum查询Udinspojxxm
                delUdinspojxxm(udinspoassetnum);

            }
        }


    }

    /**
     * 根据udinspoassetnum编号删除Udinspojxxm信息
     **/
    private void delUdinspojxxm(String udinspoassetnum) {
        List<Udinspojxxm> list = new UdinspojxxmDao(this).queryByUdinspoassetnum(udinspoassetnum);
        new UdinspojxxmDao(this).deleteList(list);
    }


    /**
     * 上传选中的数据*
     */
    private void encapsulationData(List<Udinspo> list) {


        if (null != list && list.size() != 0) {
            for (Udinspo udinspo : list) {
                findByUdinspoasset(udinspo.getInsponum());
            }

        }

    }


    /**
     * 根据巡检编号查询备件信息*
     */
    private List<Udinspojxxm> findByUdinspoasset(String insponum) {
        List<Udinspojxxm> lists = new ArrayList<Udinspojxxm>();
        List<Udinspoasset> list = new UdinspoAssetDao(UdinspoLocation_Activity.this).queryByInsponum(insponum);
        if (null != list && list.size() != 0) {
            for (Udinspoasset udinspoasset : list) {
                List<Udinspojxxm> udlist = findByUdinspojxxm(udinspoasset.getUdinspoassetnum());
                for (Udinspojxxm udinspojxxm : udlist) {
                    lists.add(udinspojxxm);
                }
            }
        }

        return lists;
    }

    /**
     * 根据备件编号查询检修项目详情*
     */
    private List<Udinspojxxm> findByUdinspojxxm(String udinspoassetnum) {
        List<Udinspojxxm> list = new UdinspojxxmDao(UdinspoLocation_Activity.this).findByLocation(udinspoassetnum, 1);
        return list;
    }


    /**
     * 上传弹出框*
     */
    private void alerDialog(final ArrayList<Udinspo> chooseList) {
        AlertDialog.Builder builder = new AlertDialog.Builder(UdinspoLocation_Activity.this);
        builder.setMessage("已选择" + chooseList.size() + "条记录，确定上传吗？").setTitle("提示")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                mProgressDialog = ProgressDialog.show(UdinspoLocation_Activity.this, null,
                        getString(R.string.inputing), true, true);
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setCancelable(false);

                if (NetWorkHelper.isNetwork(UdinspoLocation_Activity.this)) {
                    MessageUtils.showMiddleToast(UdinspoLocation_Activity.this, "暂无网络,上传失败");
                } else {

                    new AsyncTask<String, String, String>() {
                        @Override
                        protected String doInBackground(String... strings) {
                            String result = null;

                            for (Udinspo udinspo : chooseList) {
                                List<Udinspojxxm> udinspojxxms = findByUdinspoasset(udinspo.insponum);
                                String data = JsonUtils.udinspojxxmJson(udinspojxxms);
                                if (data != null || !data.isEmpty()) {
                                    result = getBaseApplication().getWsService().UpdatePO(UdinspoLocation_Activity.this, data, udinspo.insponum);

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
                                    MessageUtils.showMiddleToast(UdinspoLocation_Activity.this, "上传成功");
//                                    deleteListUdinspoasset(chooseList);

//                                    udinspoLocationadapter.removeAllData();
//                                    ArrayList<Udinspo> list1=new ArrayList<Udinspo>();
//                                    if (assettype.equals("") && checktype.equals("")) {
//                                        list1 = (ArrayList<Udinspo>) new UdinspoDao(UdinspoLocation_Activity.this).findByInspotype(inspotype);
//
//                                    } else {
//                                        list1 = (ArrayList<Udinspo>) new UdinspoDao(UdinspoLocation_Activity.this).findByType(assettype, checktype);
//                                    }
//                                    if (list1 == null || list1.size() == 0) {
//                                        nodatalayout.setVisibility(View.VISIBLE);
//                                    }
//                                    udinspoLocationadapter.adddate(list1);
//                                    udinspoLocationadapter.notifyDataSetChanged();
                                } else {
                                    MessageUtils.showMiddleToast(UdinspoLocation_Activity.this, "上传失败");
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

}
