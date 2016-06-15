package com.jf_eam_project.ui.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnBtnEditClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalEditTextDialog;
import com.flyco.dialog.widget.NormalListDialog;
import com.jf_eam_project.Dao.AssignmentDao;
import com.jf_eam_project.Dao.LabtransDao;
import com.jf_eam_project.Dao.LocationDao;
import com.jf_eam_project.Dao.WoactivityDao;
import com.jf_eam_project.Dao.WorkOrderDao;
import com.jf_eam_project.Dao.WplaborDao;
import com.jf_eam_project.Dao.WpmeterialDao;
import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.JsonUtils;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Assignment;
import com.jf_eam_project.model.Labtrans;
import com.jf_eam_project.model.Option;
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.model.Wplabor;
import com.jf_eam_project.model.Wpmaterial;
import com.jf_eam_project.ui.widget.CumTimePickerDialog;
import com.jf_eam_project.utils.MessageUtils;
import com.jf_eam_project.utils.NetWorkHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by think on 2015/11/27.
 * 工单详情页面
 */
public class Work_DetailsActivity extends BaseActivity {

    private static final String TAG="Work_DetailsActivity";
    private WorkOrder workOrder;

    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 编辑*
     */
    private ImageView editImageView;
    /**
     * 菜单按钮*
     */
    private ImageView menuImageView;
    /**
     * 返回*
     */
    private ImageView backImageView;
    private PopupWindow popupWindow;

    private DatePickerDialog datePickerDialog;
    private CumTimePickerDialog timePickerDialog;
    StringBuffer sb;
    private int layoutnum;
    private ProgressDialog mProgressDialog;
    /**
     * 计划员工*
     */
    private LinearLayout planLinearlayout;
    private ArrayList<Woactivity> woactivityList = new ArrayList<>();
    private ArrayList<Wplabor> wplaborList = new ArrayList<>();
    private ArrayList<Wpmaterial> wpmaterialList = new ArrayList<>();
    private ArrayList<Assignment> assignmentList = new ArrayList<>();
    private ArrayList<Labtrans> labtransList = new ArrayList<>();
    /**
     * 任务分配*
     */
    private LinearLayout taskLinearLayout;
    /**
     * 实际情况
     */
    private LinearLayout realinfoLinearLayout;

    private TextView wonum;//工单号
    private TextView description;//描述
    //    private TextView parent;//父工单
    private TextView udwotype; //工单类型
    private TextView assetnum;//资产编号
    private TextView assetdesc;//资产描述
    private TextView location; //位置
    private TextView locationdesc;//位置描述
    private CheckBox isxq;//消缺
    private CheckBox isyhpc;//排查隐患
    private TextView status; //状态
//    private TextView statusdate; //状态日期
    private TextView lctype; //设备专业
    private EditText powerloss;//损失电量
    private EditText speed;//平均风速
    private TextView failurecode; //故障类
    private TextView problemcode; //问题代码
    private TextView displayname; //创建人
    private TextView createdate; //创建时间
    private TextView uddescription; //分公司
    private TextView udbelong; //风电场
    private TextView largepart;//大部件发放
    private CheckBox issuematerial;//物料发放
    private CheckBox shutdown;//停机
    private EditText longdescription;//详细信息

    private TextView jpnum; //作业计划
    private TextView targstartdate;//计划开始时间
    private TextView targcompdate;//计划完成时间
    private TextView actstart;//实际开始时间
    private TextView actfinish;//实际完成时间
    private TextView reportedby; //报告人
    private TextView reportdate; //汇报日期

    private Button revise;//修改
    private Button wfservice;//工作流
    private LinearLayout confirmBtn;
    private String reviseresult;

    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
    private ArrayList<DialogMenuItem> largepartItems = new ArrayList<>();
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workdetails);

        geiIntentData();
        findViewById();
        initView();
    }

    /**
     * 获取数据*
     */
    private void geiIntentData() {
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        editImageView = (ImageView) findViewById(R.id.title_edit);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        wonum = (TextView) findViewById(R.id.work_wonum);
        description = (TextView) findViewById(R.id.work_desc);
//        parent = (TextView) findViewById(R.id.work_parent);
        udwotype = (TextView) findViewById(R.id.work_udwotype);
        assetnum = (TextView) findViewById(R.id.work_assetnum);
        assetdesc = (TextView) findViewById(R.id.work_assetdesc);
        location = (TextView) findViewById(R.id.work_location);
        locationdesc = (TextView) findViewById(R.id.work_locationdesc);
        status = (TextView) findViewById(R.id.work_status);
//        statusdate = (TextView) findViewById(R.id.work_statusdate);
        isxq = (CheckBox) findViewById(R.id.work_isxq);
        isyhpc = (CheckBox) findViewById(R.id.work_isyhpc);
        lctype = (TextView) findViewById(R.id.work_lctype);
        powerloss = (EditText) findViewById(R.id.work_powerloss);
        speed = (EditText) findViewById(R.id.work_speed);
//        woclass = (TextView) findViewById(R.id.work_woclass);
        failurecode = (TextView) findViewById(R.id.work_failurecode);
        problemcode = (TextView) findViewById(R.id.work_problemcode);
        displayname = (TextView) findViewById(R.id.work_displayname);
        createdate = (TextView) findViewById(R.id.work_createdate);
        uddescription=(TextView)findViewById(R.id.uddeptdescription_id);
        udbelong=(TextView)findViewById(R.id.work_udbelong_id);
        largepart = (TextView) findViewById(R.id.work_largepart);
        issuematerial = (CheckBox) findViewById(R.id.work_issuematerial);
        shutdown = (CheckBox) findViewById(R.id.work_shutdown);
        longdescription = (EditText) findViewById(R.id.work_longdescription);

        jpnum = (TextView) findViewById(R.id.work_jpnum);
        targstartdate = (TextView) findViewById(R.id.work_targstartdate);
        targcompdate = (TextView) findViewById(R.id.work_targcompdate);
        actstart = (TextView) findViewById(R.id.work_acstart);
        actfinish = (TextView) findViewById(R.id.work_actfinish);
        reportedby = (TextView) findViewById(R.id.work_reportedby);
        reportdate = (TextView) findViewById(R.id.work_reportdate);

        confirmBtn = (LinearLayout) findViewById(R.id.buttom_layout);
        revise = (Button) findViewById(R.id.work_revise);
        wfservice = (Button) findViewById(R.id.wfservice);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_work_details);
        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        editImageView.setVisibility(View.VISIBLE);
        editImageView.setOnClickListener(editImageViewOnClickListener);
        backImageView.setOnClickListener(backOnClickListener);

        wonum.setText(workOrder.wonum);
        workOrder.isnew = false;
        description.setText(workOrder.description);
//        parent.setText(workOrder.parent);
        if(workOrder.udwotype.equals("PLAN")){
            udwotype.setText(getString(R.string.work_plan_type));
        }else if(workOrder.udwotype.equals("UNPLAN")){
            udwotype.setText(getString(R.string.work_unplan_type));
        }

        assetnum.setText(workOrder.assetnum);
        assetdesc.setText(workOrder.assetdesc);
        location.setText(workOrder.location);
        locationdesc.setText(workOrder.locationdesc);
        status.setText(workOrder.status);
//        statusdate.setText(workOrder.statusdate);
        isxq.setChecked(workOrder.isxq!=null&&workOrder.isxq.equals("Y"));
        isyhpc.setChecked(workOrder.isyhpc != null && workOrder.isyhpc.equals("Y"));
        lctype.setText(workOrder.lctype);
        powerloss.setText(workOrder.powerloss);
        speed.setText(workOrder.speed);
//        woclass.setText(workOrder.woclass);
        failurecode.setText(workOrder.failurecode);
        problemcode.setText(workOrder.problemcode);
        displayname.setText(workOrder.displayname);
        createdate.setText(workOrder.createdate);
        uddescription.setText(workOrder.uddeptdescription);
        udbelong.setText(workOrder.udbelong);
        largepart.setText(workOrder.largepart);
        issuematerial.setChecked(workOrder.issuematerial != null && workOrder.issuematerial.equals("Y"));
        shutdown.setChecked(workOrder.shutdown != null && workOrder.shutdown.equals("Y"));
        longdescription.setText(workOrder.description_longdescription);

        jpnum.setText(workOrder.jpnum);
        targstartdate.setText(workOrder.targstartdate);
        targcompdate.setText(workOrder.targcompdate);
        actstart.setText(workOrder.actstart);
        actfinish.setText(workOrder.actfinish);
        reportedby.setText(workOrder.reportedby);
        reportdate.setText(workOrder.reportdate);

        description.setFocusable(false);
        description.setFocusableInTouchMode(false);
        udwotype.setEnabled(false);
        assetnum.setEnabled(false);
        location.setEnabled(false);
        lctype.setEnabled(false);
        isxq.setClickable(false);
        isyhpc.setClickable(false);
        powerloss.setEnabled(false);
        speed.setEnabled(false);
        largepart.setEnabled(false);
        issuematerial.setClickable(false);
        shutdown.setClickable(false);
        longdescription.setFocusable(false);
        longdescription.setFocusableInTouchMode(false);
        failurecode.setEnabled(false);
        problemcode.setEnabled(false);
        jpnum.setEnabled(false);
        targstartdate.setEnabled(false);
        targcompdate.setEnabled(false);
        actstart.setEnabled(false);
        actfinish.setEnabled(false);


        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        addTaskData();
        addlargepartData();
        setDataListener();
        lctype.setOnClickListener(lctypeOnClickListener);
        largepart.setOnClickListener(largepartOnClickListener);
        targstartdate.setOnClickListener(new MydateListener());
        targcompdate.setOnClickListener(new MydateListener());
        actstart.setOnClickListener(new MydateListener());
        actfinish.setOnClickListener(new MydateListener());

        assetnum.setOnClickListener(new LayoutOnClickListener(Constants.ASSETCODE));
        location.setOnClickListener(new LayoutOnClickListener(Constants.LOCATIONCODE));
        failurecode.setOnClickListener(new LayoutOnClickListener(Constants.FAILURECODE));
        problemcode.setOnClickListener(new LayoutOnClickListener(Constants.FAILURELIST));
        jpnum.setOnClickListener(new LayoutOnClickListener(Constants.JOBPLAN));
        isxq.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isyhpc.setChecked(!b);
                }
            }
        });
        isyhpc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isxq.setChecked(!b);
                }
            }
        });

        revise.setOnClickListener(reviseOnClickListener);
        wfservice.setOnClickListener(wfserviceOnClickListener);

        if (workOrder.udwotype.equals(Constants.UNPLAN)) {
            wfservice.setVisibility(View.VISIBLE);
        } else {
            wfservice.setVisibility(View.GONE);
        }
        if(workOrder.id!=0){
            getLocationData(workOrder.id);
        }
    }

    //如果为历史数据，则获取本地子表信息
    private void getLocationData(int id){
        woactivityList = (ArrayList<Woactivity>) new WoactivityDao(Work_DetailsActivity.this).queryByWonum(id);
        wplaborList = (ArrayList<Wplabor>) new WplaborDao(Work_DetailsActivity.this).queryByWonum(id);
        wpmaterialList = (ArrayList<Wpmaterial>) new WpmeterialDao(Work_DetailsActivity.this).queryByWonum(id);
        assignmentList = (ArrayList<Assignment>) new AssignmentDao(Work_DetailsActivity.this).queryByWonum(id);
        labtransList = (ArrayList<Labtrans>) new LabtransDao(Work_DetailsActivity.this).queryByWonum(id);
    }

    private View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (confirmBtn.getVisibility()==View.VISIBLE) {
                final NormalDialog dialog = new NormalDialog(Work_DetailsActivity.this);
                dialog.content("确定放弃修改吗?")//
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
                                finish();
//                                dialog.dismiss();
                            }
                        });
            }else {
                finish();
            }
        }
    };

    private void ActivityFinish(){
        Work_DetailsActivity.this.finish();
    }

    private View.OnClickListener lctypeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            NormalListDialog();
        }
    };

    private View.OnClickListener largepartOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            largepartListDialog();
        }
    };

    private void NormalListDialog() {
        final NormalListDialog dialog = new NormalListDialog(Work_DetailsActivity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                lctype.setText(mMenuItems.get(position).mOperName);
                dialog.dismiss();
            }
        });
    }

    private void largepartListDialog() {
        final NormalListDialog dialog = new NormalListDialog(Work_DetailsActivity.this, largepartItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                largepart.setText(largepartItems.get(position).mOperName);
                dialog.dismiss();
            }
        });
    }

    /**
     * 添加任务数据*
     */
    private void addTaskData() {
        ArrayList<String> list = new ArrayList<>();
        list.add("风机");
        list.add("电气");
        for (int i = 0; i < list.size(); i++) {
            mMenuItems.add(new DialogMenuItem(list.get(i), 0));
        }
    }

    /**
     * 添加任务数据*
     */
    private void addlargepartData() {
        ArrayList<String> list = new ArrayList<>();
        list.add("是");
        list.add("否");
        for (int i = 0; i < list.size(); i++) {
            largepartItems.add(new DialogMenuItem(list.get(i), 0));
        }
    }

    private View.OnClickListener editImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            confirmBtn.setVisibility(View.VISIBLE);
            statusEdit();
        }
    };

    /**
     * 设置状态编辑*
     */
    private void statusEdit() {
        description.setFocusable(true);
        description.setFocusableInTouchMode(true);
        udwotype.setEnabled(true);
        assetnum.setEnabled(true);
        location.setEnabled(true);
        lctype.setEnabled(true);
        isxq.setClickable(true);
        isyhpc.setClickable(true);
        powerloss.setEnabled(true);
        speed.setEnabled(true);
        largepart.setEnabled(true);
        issuematerial.setClickable(true);
        shutdown.setClickable(true);
        longdescription.setFocusable(true);
        longdescription.setFocusableInTouchMode(true);
        failurecode.setEnabled(true);
        problemcode.setEnabled(true);
        jpnum.setEnabled(true);
        targstartdate.setEnabled(true);
        targcompdate.setEnabled(true);
        actstart.setEnabled(true);
        actfinish.setEnabled(true);
    }

    private View.OnClickListener reviseOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (targstartdate.getText().equals("") || targcompdate.getText().equals("")
                    || actstart.getText().equals("") || actfinish.getText().equals("")) {
                Toast.makeText(Work_DetailsActivity.this, "请输入日期时间", Toast.LENGTH_SHORT).show();
            } else if (status.getText().toString().equals(Constants.WAIT_APPROVAL) || status.getText().toString().equals(Constants.APPROVALED)) {
                submitDataInfo();
            } else {
                MessageUtils.showMiddleToast(Work_DetailsActivity.this, "工单状态不允许修改");
            }
        }
    };


    /**
     * 提交数据*
     */
    private void submitDataInfo() {

        final NormalDialog dialog = new NormalDialog(Work_DetailsActivity.this);
        dialog.content("确定修改工单吗?")//
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
                        showProgressDialog("数据提交中...");
                        startAsyncTask();
                        dialog.dismiss();
                    }
                });
    }


    /**
     * 提交数据*
     */
    private void startAsyncTask() {
        if (NetWorkHelper.isNetwork(Work_DetailsActivity.this)) {
            MessageUtils.showMiddleToast(Work_DetailsActivity.this, "暂无网络,现离线保存数据!");
            saveWorkOrder();
            closeProgressDialog();
        } else {
            String updataInfo = null;
            if (workOrder.status.equals(Constants.WAIT_APPROVAL)) {
                updataInfo = JsonUtils.WorkToJson(getWorkOrder(), getWoactivityList(), getWplaborList(), getWpmaterialList(), getAssignmentList(), null);
            } else if (workOrder.status.equals(Constants.APPROVALED)) {
                updataInfo = JsonUtils.WorkToJson(getWorkOrder(), null, null, null, null, getLabtransList());
            }
            final String finalUpdataInfo = updataInfo;
            new AsyncTask<String, String, String>() {
                @Override
                protected String doInBackground(String... strings) {
                    reviseresult = getBaseApplication().getWsService().UpdataWO(Work_DetailsActivity.this,finalUpdataInfo, wonum.getText().toString());
                    return reviseresult;
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    if (s.equals("成功")) {
                        Toast.makeText(Work_DetailsActivity.this, "修改工单成功", Toast.LENGTH_SHORT).show();
                        if (workOrder.ishistory){
                            new WorkOrderDao(Work_DetailsActivity.this).deleteById(workOrder.id);
                        }
                    } else if (s.equals("")) {
                        Toast.makeText(Work_DetailsActivity.this, "修改工单失败", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Work_DetailsActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                    closeProgressDialog();
                }
            }.execute();
        }

    }


    private void saveWorkOrder() {
        WorkOrder workOrder = getWorkOrder();
        workOrder.ishistory = true;
        new WorkOrderDao(Work_DetailsActivity.this).Update(workOrder);
        int id = workOrder.id;
        if (id != 0) {
            if (woactivityList.size() != 0) {
                for (Woactivity woactivity : woactivityList) {
                    woactivity.belongid = id;
                }
                new WoactivityDao(Work_DetailsActivity.this).create(woactivityList);
            }
            if (wplaborList.size() != 0) {
                for (Wplabor wplabor : wplaborList) {
                    wplabor.belongid = id;
                }
                new WplaborDao(Work_DetailsActivity.this).create(wplaborList);
            }
            if (wpmaterialList.size() != 0) {
                for (Wpmaterial wpmaterial : wpmaterialList) {
                    wpmaterial.belongid = id;
                }
                new WpmeterialDao(Work_DetailsActivity.this).create(wpmaterialList);
            }
            if (assignmentList.size() != 0) {
                for (Assignment assignment : assignmentList) {
                    assignment.belongid = id;
                }
                new AssignmentDao(Work_DetailsActivity.this).create(assignmentList);
            }
            if (labtransList.size() != 0) {
                for (Labtrans labtrans : labtransList) {
                    labtrans.belongid = id;
                }
                new LabtransDao(Work_DetailsActivity.this).create(labtransList);
            }
        }
    }

    private View.OnClickListener wfserviceOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            MaterialDialogOneBtn();
        }
    };

    private void MaterialDialogOneBtn() {//选择审核结果
        final MaterialDialog dialog = new MaterialDialog(Work_DetailsActivity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.isTitleShow(false)//
                .btnNum(3)
                .content("请选择审核结果")//
                .btnText("通过", "取消", "不通过")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {//通过
                    @Override
                    public void onBtnClick() {
                        MaterialDialogOneBtn1(true);
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {//取消
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                }
                ,
                new OnBtnClickL() {//不通过
                    @Override
                    public void onBtnClick() {//不通过
                        MaterialDialogOneBtn1(false);
                        dialog.dismiss();
                    }
                }
        );
    }

    private void MaterialDialogOneBtn1(final boolean isok) {//是否输入审核意见
        final MaterialDialog dialog = new MaterialDialog(Work_DetailsActivity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
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
                        EditDialog(isok);
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {//否
                    @Override
                    public void onBtnClick() {
                        getwfstatus(isok, "不通过");
                        dialog.dismiss();
                    }
                }
        );
    }

    private void EditDialog(final boolean isok) {//输入审核意见
        final NormalEditTextDialog dialog = new NormalEditTextDialog(Work_DetailsActivity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
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
                        getwfstatus(isok, text);
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
     * 判断工作流是否已启动
     *
     * @return
     */
    private void getwfstatus(final boolean isok, final String desc) {
        HttpManager.getDataPagingInfo(this, HttpManager.getWfStatusUrl(1, 20, workOrder.workorderid,"","WORKORDER"), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                String result = JsonUtils.parsingwfstatusResult(results.getResultlist());


//                if (result != null && result.equals("Y")) {
//                    wfstart(workOrder.wonum);
//                } else if (result != null && result.equals("N")) {
                    wfgoon(workOrder.wonum, isok ? "1" : "0", desc);
//                }
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(Work_DetailsActivity.this, "查询工作流状态失败，审核中止", Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
            }
        });

    }

    /**
     * 开始工作流
     *
     * @param wonum
     */
    private void wfstart(final String wonum) {
        mProgressDialog = ProgressDialog.show(Work_DetailsActivity.this, null,
                getString(R.string.inputing), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String result = getBaseApplication().getWfService().startwf(Work_DetailsActivity.this,"UDFJHWO", "WORKORDER", wonum, "WONUM");
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s == null || s.equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "审批失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Work_DetailsActivity.this, s, Toast.LENGTH_SHORT).show();
                }
                mProgressDialog.dismiss();
            }
        }.execute();
    }

    /**
     * 审批工作流
     *
     * @param wonum
     * @param zx
     */
    private void wfgoon(final String wonum, final String zx, final String desc) {
        Log.i(TAG,"wonum="+wonum+",zx="+zx+",desc="+desc);

        mProgressDialog = ProgressDialog.show(Work_DetailsActivity.this, null,
                getString(R.string.inputing), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String result = getBaseApplication().getWfService().wfGoOn(Work_DetailsActivity.this,"UDFJHWO", "WORKORDER", wonum, "WONUM", zx, desc);
                Log.i(TAG,"result="+result);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                Log.i(TAG,"s="+s);

                super.onPostExecute(s);
                if (s == null || s.equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "审批失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Work_DetailsActivity.this, s, Toast.LENGTH_SHORT).show();
                    status.setText(s);
                }
                mProgressDialog.dismiss();
            }
        }.execute();
    }

    //获取界面上工单内容
    private WorkOrder getWorkOrder() {
        workOrder.wonum = wonum.getText().toString();
        workOrder.description = description.getText().toString();
        workOrder.udwotype = udwotype.getText().toString();
        workOrder.assetnum = assetnum.getText().toString();
        workOrder.location = location.getText().toString();
        workOrder.status = status.getText().toString();
        workOrder.isxq = isxq.isChecked()?"Y":"N";
        workOrder.isyhpc = isyhpc.isChecked()?"Y":"N";
        workOrder.issuematerial = issuematerial.isChecked()?"Y":"N";
        workOrder.shutdown = shutdown.isChecked()?"Y":"N";
        workOrder.description_longdescription = longdescription.getText().toString().trim();
//        workOrder.statusdate = statusdate.getText().toString();
        workOrder.lctype = lctype.getText().toString();
        workOrder.powerloss = powerloss.getText().toString().trim();
        workOrder.speed = speed.getText().toString().trim();
        workOrder.failurecode = failurecode.getText().toString();
        workOrder.problemcode = problemcode.getText().toString();
        workOrder.createdate = createdate.getText().toString();
        workOrder.jpnum = jpnum.getText().toString();
        workOrder.targstartdate = targstartdate.getText().toString();
        workOrder.targcompdate = targcompdate.getText().toString();
        workOrder.actstart = actstart.getText().toString();
        workOrder.actfinish = actfinish.getText().toString();
        workOrder.reportedby = reportedby.getText().toString();
        workOrder.reportdate = reportdate.getText().toString();
        return workOrder;
    }

    //过滤变化过的任务数据
    private ArrayList<Woactivity> getWoactivityList() {
        ArrayList<Woactivity> woactivities = new ArrayList<>();
        for (int i = 0; i < woactivityList.size(); i++) {
            if (woactivityList.get(i).type != null && !woactivityList.get(i).type.equals("")) {//如果此条数据是变动后的
                woactivities.add(woactivityList.get(i));
            }
        }
        return woactivities;
    }

    //过滤变化过的计划员工数据
    private ArrayList<Wplabor> getWplaborList() {
        ArrayList<Wplabor> wplabors = new ArrayList<>();
        for (int i = 0; i < wplaborList.size(); i++) {
            if (wplaborList.get(i).type != null && !wplaborList.get(i).equals("")) {//如果此条数据是变动后的
                wplabors.add(wplaborList.get(i));
            }
        }
        return wplabors;
    }

    //过滤变化过的计划物料数据
    private ArrayList<Wpmaterial> getWpmaterialList() {
        ArrayList<Wpmaterial> wpmaterials = new ArrayList<>();
        for (int i = 0; i < wpmaterialList.size(); i++) {
            if (wpmaterialList.get(i).type != null && !wpmaterialList.get(i).type.equals("")) {//如果此条数据是变动后的
                wpmaterials.add(wpmaterialList.get(i));
            }
        }
        return wpmaterials;
    }

    //过滤变化过的任务分配数据
    private ArrayList<Assignment> getAssignmentList() {
        ArrayList<Assignment> assignments = new ArrayList<>();
        for (int i = 0; i < assignmentList.size(); i++) {
            if (assignmentList.get(i).type != null && !assignmentList.get(i).type.equals("")) {//如果此条数据是变动后的
                assignments.add(assignmentList.get(i));
            }
        }
        return assignments;
    }

    //过滤变化过的实际数据
    private ArrayList<Labtrans> getLabtransList() {
        ArrayList<Labtrans> labtranses = new ArrayList<>();
        for (int i = 0; i < labtransList.size(); i++) {
            if (labtransList.get(i).type != null && labtransList.get(i).type.equals("add")) {//如果此条数据是变动后的
                labtranses.add(labtransList.get(i));
            }
        }
        return labtranses;
    }

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupWindow(menuImageView);
        }
    };

    /**
     * 初始化showPopupWindow*
     */
    private void showPopupWindow(View view) {
        View contentView = LayoutInflater.from(Work_DetailsActivity.this).inflate(
                R.layout.work_popup_window, null);

        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.abc_popup_background_mtrl_mult));
        popupWindow.showAsDropDown(view, 0, 20);

        planLinearlayout = (LinearLayout) contentView.findViewById(R.id.work_wplabor_id);
        taskLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_task_id);
        realinfoLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_labtrans_id);
        planLinearlayout.setOnClickListener(planOnClickListener);
        taskLinearLayout.setOnClickListener(taskOnClickListener);
        realinfoLinearLayout.setOnClickListener(realinfoOnClickListener);

        if (workOrder.status.equals(Constants.APPROVALED) || workOrder.status.equals(Constants.APPDONE)) {
            realinfoLinearLayout.setVisibility(View.VISIBLE);
        } else {
            realinfoLinearLayout.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener planOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_DetailsActivity.this, Work_PlanActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            bundle.putSerializable("woactivityList", woactivityList);
            bundle.putSerializable("wplaborList", wplaborList);
            bundle.putSerializable("wpmaterialList", wpmaterialList);
            intent.putExtras(bundle);
            startActivityForResult(intent, 0);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener taskOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_DetailsActivity.this, Work_AssignmentActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            bundle.putSerializable("assignmentList", assignmentList);
            intent.putExtras(bundle);
            startActivityForResult(intent, 2000);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener realinfoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_DetailsActivity.this, Work_LabtransActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            bundle.putSerializable("labtransList", labtransList);
            intent.putExtras(bundle);
            startActivityForResult(intent, 3000);
            popupWindow.dismiss();
        }
    };

    private class LayoutOnClickListener implements View.OnClickListener {
        int requestCode;

        private LayoutOnClickListener(int requestCode) {
            this.requestCode = requestCode;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_DetailsActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", requestCode);
            startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 设置时间选择器*
     */
    private void setDataListener() {

        final Calendar objTime = Calendar.getInstance();
        int iYear = objTime.get(Calendar.YEAR);
        int iMonth = objTime.get(Calendar.MONTH);
        int iDay = objTime.get(Calendar.DAY_OF_MONTH);
        int hour = objTime.get(Calendar.HOUR_OF_DAY);

        int minute = objTime.get(Calendar.MINUTE);


        datePickerDialog = new DatePickerDialog(this, new datelistener(), iYear, iMonth, iDay);
        timePickerDialog = new CumTimePickerDialog(this, new timelistener(), hour, minute, true);
    }

    private class MydateListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            layoutnum = 0;
            sb = new StringBuffer();
            layoutnum = view.getId();
            datePickerDialog.show();
        }
    }

    private class datelistener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            sb = new StringBuffer();
            monthOfYear = monthOfYear + 1;
            if (dayOfMonth < 10) {
                sb.append(year % 100 + "-" + monthOfYear + "-" + "0" + dayOfMonth);
            } else {
                sb.append(year % 100 + "-" + monthOfYear + "-" + dayOfMonth);
            }
            timePickerDialog.show();
        }
    }

    private class timelistener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            sb.append(" ");
            if (i1 < 10) {
                sb.append(i + ":" + "0" + i1 + ":00");
            } else {
                sb.append(i + ":" + i1 + ":00");
            }

//            Log.i(TAG,"sb="+sb);
            if (layoutnum == targstartdate.getId()) {
                targstartdate.setText(sb);
            } else if (layoutnum == targcompdate.getId()) {
                targcompdate.setText(sb);
            } else if (layoutnum == actstart.getId()) {
                actstart.setText(sb);
            } else if (layoutnum == actfinish.getId()) {
                actfinish.setText(sb);
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        switch (resultCode) {
            case Constants.ASSETCODE:
                option = (Option) data.getSerializableExtra("option");
                assetnum.setText(option.getName());
                assetdesc.setText(option.getDescription());
                location.setText(option.getValue());
                if (new LocationDao(Work_DetailsActivity.this).queryLocation(option.getValue()) != null) {
                    locationdesc.setText(new LocationDao(Work_DetailsActivity.this).queryLocation(option.getValue()).description);
                }
                break;
            case Constants.LOCATIONCODE:
                option = (Option) data.getSerializableExtra("option");
                location.setText(option.getName());
                locationdesc.setText(option.getDescription());
                break;
            case Constants.FAILURECODE:
                option = (Option) data.getSerializableExtra("option");
//                failurecode.setText(option.getName());
                failurecode.setText(option.getDescription());
                break;
            case Constants.FAILURELIST:
                option = (Option) data.getSerializableExtra("option");
//                problemcode.setText(option.getName());
                problemcode.setText(option.getDescription());
                break;
            case Constants.JOBPLAN:
                option = (Option) data.getSerializableExtra("option");
                jpnum.setText(option.getName());
                break;
            case 1000:
                woactivityList = (ArrayList<Woactivity>) data.getSerializableExtra("woactivityList");
                wplaborList = (ArrayList<Wplabor>) data.getSerializableExtra("wplaborList");
                wpmaterialList = (ArrayList<Wpmaterial>) data.getSerializableExtra("wpmaterialList");
                editImageView.performClick();
                break;
            case 2000:
                assignmentList = (ArrayList<Assignment>) data.getSerializableExtra("assignmentList");
                editImageView.performClick();
                break;
            case 3000:
                labtransList = (ArrayList<Labtrans>) data.getSerializableExtra("labtransList");
                editImageView.performClick();
                break;
            default:
                break;
        }
    }
}
