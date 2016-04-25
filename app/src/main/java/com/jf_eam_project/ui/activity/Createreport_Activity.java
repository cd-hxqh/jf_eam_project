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
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.jf_eam_project.Dao.CreatereportDao;
import com.jf_eam_project.Dao.LocationDao;
import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Assignment;
import com.jf_eam_project.model.Createreport;
import com.jf_eam_project.model.Option;
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.model.Wplabor;
import com.jf_eam_project.model.Wpmaterial;
import com.jf_eam_project.ui.widget.CumTimePickerDialog;
import com.jf_eam_project.utils.AccountUtils;
import com.jf_eam_project.utils.GetNowTime;
import com.jf_eam_project.utils.JsonUtils;
import com.jf_eam_project.utils.MessageUtils;
import com.jf_eam_project.utils.NetWorkHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 缺陷与故障提报单
 */
public class Createreport_Activity extends BaseActivity {

    private static final String TAG = "Createreport_Activity";

    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;


    /**
     * 界面信息显示*
     */
    private TextView reporttypeText; //提报单类别
    private TextView culevelText; //缺陷或故障等级
    private TextView assettypeText; //设备类别
    private TextView assetnumText; //设备
    private TextView locationText; //位置
    private TextView descriptionText; //描述
    private TextView descriptionxxText; //详细描述
    private TextView reportbyText; //提报人
    private TextView reporttimeText; //提报日期

    /**
     * 巡检项目标准id*
     */
    private String udinspojxxmid;
    /**
     * 新增*
     */
    private Button addButton;


    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    /**
     * 位置*
     */
    private String location = "";

    /**
     * 设备类型*
     */
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
    /**
     * 提报单类别*
     */
    private ArrayList<DialogMenuItem> cMenuItems = new ArrayList<>();
    /**
     * 故障等级*
     */
    private ArrayList<DialogMenuItem> tMenuItems = new ArrayList<>();


    private DatePickerDialog datePickerDialog;
    private CumTimePickerDialog timePickerDialog;
    StringBuffer sb;


    private ProgressDialog mProgressDialog;

    /**
     * 详情与新增标识*
     */
    private int mark;
    private Createreport report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createreport);
        initData();
        findViewById();
        initView();
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        addReporttypeData(); //提报单
        addLctypeData(); //设备类别
        addCulevelData(); //故障等级
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        mark = getIntent().getExtras().getInt("mark");
        Log.i(TAG, "mark=" + mark);
        if (mark == 1) { //新增
            udinspojxxmid = getIntent().getExtras().getString("udinspojxxmid");
        } else { //详情
            report = (Createreport) getIntent().getSerializableExtra("createreport");
        }


    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        reporttypeText = (TextView) findViewById(R.id.reporttype_text_id);
        culevelText = (TextView) findViewById(R.id.culevel_text_id);
        assettypeText = (TextView) findViewById(R.id.assettype_text_id);
        assetnumText = (TextView) findViewById(R.id.assetnum_text_id);
        locationText = (TextView) findViewById(R.id.locaton_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        descriptionxxText = (TextView) findViewById(R.id.descriptionxx_text_id);
        reportbyText = (TextView) findViewById(R.id.reportby_text_id);
        reporttimeText = (TextView) findViewById(R.id.reporttime_text_id);

        addButton = (Button) findViewById(R.id.createreport_add_button);

    }

    @Override
    protected void initView() {
        if (mark == 1) {
            titleView.setText(getString(R.string.createreport_text));
        } else {
            titleView.setText(getString(R.string.report_details_text));
            reporttypeText.setText(report.getReporttype());
            culevelText.setText(report.getCulevel());
            assettypeText.setText(report.getAssettype());
            assetnumText.setText(report.getAssetnum());
            locationText.setText(report.getLocation());
            descriptionText.setText(report.getDescription());
            descriptionxxText.setText(report.getDescriptionxx());
            reportbyText.setText(report.getReportby());
            reporttimeText.setText(report.getReporttime());
        }


        backImageView.setOnClickListener(backImageViewOnClickListenrer);

        reporttypeText.setOnClickListener(reporttypeTextOnClickListener);
        culevelText.setOnClickListener(culevelTextOnClickListener);

        assettypeText.setOnClickListener(assettypeTextOnClickListener);
        assetnumText.setOnClickListener(asstnumTextOnClickListener);
        locationText.setOnClickListener(locationTextOnClickListener);


        reportbyText.setText(AccountUtils.getUserName(Createreport_Activity.this));
        reporttimeText.setText(GetNowTime.getTime());
        setDataListener();

        reporttimeText.setOnClickListener(new MydateListener());
        addButton.setOnClickListener(addButtonOnClickListener);
    }

    /**
     * 返回按钮*
     */
    private View.OnClickListener backImageViewOnClickListenrer = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    /**
     * 提报单类别*
     */
    private View.OnClickListener reporttypeTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            NormalListDialog1();
        }
    };
    /**
     * 故障等级*
     */
    private View.OnClickListener culevelTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            NormalListDialog2();
        }
    };


    /**
     * 设备类别*
     */
    private View.OnClickListener assettypeTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            NormalListDialog();
        }
    };

    /**
     * 设备*
     */

    private View.OnClickListener asstnumTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Createreport_Activity.this, OptionActivity.class);
            intent.putExtra("requestCode", Constants.ASSETCODE);
            startActivityForResult(intent, Constants.ASSETCODE);
        }
    };

    /**
     * 位置*
     */

    private View.OnClickListener locationTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Createreport_Activity.this, OptionActivity.class);
            intent.putExtra("requestCode", Constants.LOCATIONCODE);
            startActivityForResult(intent, Constants.LOCATIONCODE);
        }
    };


    /**
     * 设备类别*
     */
    private void NormalListDialog() {
        final NormalListDialog dialog = new NormalListDialog(Createreport_Activity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                assettypeText.setText(mMenuItems.get(position).mOperName);

                dialog.dismiss();
            }
        });
    }

    /**
     * 提报单类别*
     */
    private void NormalListDialog1() {
        final NormalListDialog dialog = new NormalListDialog(Createreport_Activity.this, cMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                reporttypeText.setText(cMenuItems.get(position).mOperName);

                dialog.dismiss();
            }
        });
    }

    /**
     * 故障等级*
     */
    private void NormalListDialog2() {
        final NormalListDialog dialog = new NormalListDialog(Createreport_Activity.this, tMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                culevelText.setText(tMenuItems.get(position).mOperName);

                dialog.dismiss();
            }
        });
    }


    /**
     * 添加数据*
     */
    private void addLctypeData() {
        String[] lctypes = getResources().getStringArray(R.array.lctype_tab_titles);

        for (int i = 0; i < lctypes.length; i++)
            mMenuItems.add(new DialogMenuItem(lctypes[i], 0));


    }

    /**
     * 添加数据*
     */
    private void addReporttypeData() {
        String[] lctypes = getResources().getStringArray(R.array.reporttype_tab_titles);

        for (int i = 0; i < lctypes.length; i++)
            cMenuItems.add(new DialogMenuItem(lctypes[i], 0));


    }

    /**
     * 添加数据*
     */
    private void addCulevelData() {
        String[] lctypes = getResources().getStringArray(R.array.culevel_tab_titles);

        for (int i = 0; i < lctypes.length; i++)
            tMenuItems.add(new DialogMenuItem(lctypes[i], 0));


    }


    /**
     * 保存提报单信息*
     */
    private View.OnClickListener addButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (judge()) {

                encapsulationData();
            }
        }
    };


    /**
     * 判断提交的信息*
     */
    private boolean judge() {
        if (mark == 1) {
            if (reporttypeText.getText().toString().equals("")) {
                MessageUtils.showErrorMessage(Createreport_Activity.this, "提报单类别必填");
                return false;
            }
            if (culevelText.getText().toString().equals("")) {
                MessageUtils.showErrorMessage(Createreport_Activity.this, "等级必填");
                return false;
            }
            if (location.equals("")) {
                MessageUtils.showErrorMessage(Createreport_Activity.this, "位置必填");
                return false;
            }
            if (descriptionText.getText().toString().equals("")) {
                MessageUtils.showErrorMessage(Createreport_Activity.this, "描述必填");
                return false;
            }
            return true;
        } else {
            return true;
        }
    }


    private Createreport saveReport() {
        String reporttype = reporttypeText.getText().toString(); //提报单类别
        String culevel = culevelText.getText().toString(); //缺陷或故障等级
        String assettype = assettypeText.getText().toString(); //设备类别
        String assetnum = assetnumText.getText().toString(); //设备
//        String location1 = locationText.getText().toString(); //位置
        String description = descriptionText.getText().toString(); //描述
        String descriptionxx = descriptionxxText.getText().toString(); //详细描述
        String reportby = reportbyText.getText().toString(); //提报人
        String reporttime = reporttimeText.getText().toString(); //提报时间
        Createreport createreport = null;
        if (mark == 1) { //新增
            createreport = new Createreport();
            if (reporttype.equals("故障提报单")) {
                createreport.setReporttype("FAULT");
            } else {
                createreport.setReporttype("HIDDEN");
            }
            createreport.setUdinspojxxmid(udinspojxxmid);
            createreport.setLocation(location);


            createreport.setCulevel(culevel);
            createreport.setAssettype(assettype);
            createreport.setAssetnum(assetnum);


            createreport.setDescription(description);
            createreport.setDescriptionxx(descriptionxx);
            createreport.setReportby(reportby);
            createreport.setReporttime(reporttime);
            return createreport;

        } else if (mark == 2) { //更新
            if (reporttype.equals("故障提报单")) {
                report.setReporttype("FAULT");
            } else {
                report.setReporttype("HIDDEN");
            }
            if (!location.equals("")) {
                report.setLocation(location);

            }
            report.setCulevel(culevel);
            report.setAssettype(assettype);
            report.setAssetnum(assetnum);


            report.setDescription(description);
            report.setDescriptionxx(descriptionxx);
            report.setReportby(reportby);
            report.setReporttime(reporttime);
            return report;
        }
        return null;

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
            sb = new StringBuffer();
            datePickerDialog.show();
        }
    }

    private class datelistener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            sb = new StringBuffer();
            monthOfYear = monthOfYear + 1;
            if (dayOfMonth < 10) {
                sb.append(year + "-" + monthOfYear + "-" + "0" + dayOfMonth);
            } else {
                sb.append(year + "-" + monthOfYear + "-" + dayOfMonth);
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
            reporttimeText.setText(sb);
        }
    }


    /**
     * 数据封装*
     */
    private void encapsulationData() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Createreport_Activity.this);
        builder.setMessage("确定新增提报单吗？").setTitle("提示")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                mProgressDialog = ProgressDialog.show(Createreport_Activity.this, null,
                        getString(R.string.inputing), true, true);
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setCancelable(false);

                if (NetWorkHelper.isNetwork(Createreport_Activity.this)) {
                    MessageUtils.showMiddleToast(Createreport_Activity.this, "暂无网络,现离线保存数据!");
                    Createreport createreport = saveReport();
                    Log.i(TAG, "createreport dd=" + createreport.getDescriptionxx());
                    Log.i(TAG, "createreport id=" + createreport.getId());

                    if (mark == 1) { //创建
                        Log.i(TAG, "111111");
                        new CreatereportDao(Createreport_Activity.this).create(createreport);
                    } else if (mark == 2) { //更新
                        new CreatereportDao(Createreport_Activity.this).update(createreport);
                        Log.i(TAG, "22222");

                    }
                    mProgressDialog.dismiss();
                    setResult(Constants.REFRESH);
                    finish();
                } else {


                    new AsyncTask<String, String, String>() {
                        @Override
                        protected String doInBackground(String... strings) {
                            String data = null;
                            Createreport createreport = saveReport();
                            data = JsonUtils.saveReport(createreport);
                            String result = getBaseApplication().getWsService().addReport(Createreport_Activity.this, data, "");
                            return result;
                        }

                        @Override
                        protected void onPostExecute(String s) {

                            super.onPostExecute(s);
                            mProgressDialog.dismiss();
                            try {
                                if (s != null) {
                                    JSONObject jsonObject = new JSONObject(s);
                                    String Msg = jsonObject.getString("Msg");
                                    String errorNo = jsonObject.getString("errorNo");
                                    if (errorNo.equals("0")) {
                                        MessageUtils.showMiddleToast(Createreport_Activity.this, "数据新增成功");

                                    } else {
                                        MessageUtils.showMiddleToast(Createreport_Activity.this, Msg);
                                    }
                                    setResult(Constants.REFRESH);
                                    finish();
                                } else {
                                    MessageUtils.showMiddleToast(Createreport_Activity.this, "数据新增失败");
                                    setResult(Constants.REFRESH);
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                MessageUtils.showMiddleToast(Createreport_Activity.this, "数据新增失败");
                                setResult(Constants.REFRESH);
                                finish();
                            }


                        }
                    }.execute();
                }
            }
        }).create().show();


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        switch (resultCode) {
            case Constants.ASSETCODE:
                option = (Option) data.getSerializableExtra("option");
                assetnumText.setText(option.getName());
                location = option.getValue();
                locationText.setText(option.getValue());
                if (new LocationDao(Createreport_Activity.this).queryLocation(option.getValue()) != null) {
                    locationText.setText(new LocationDao(Createreport_Activity.this).queryLocation(option.getValue()).description);
                }
                break;
            case Constants.LOCATIONCODE:
                option = (Option) data.getSerializableExtra("option");
                locationText.setText(option.getName());
                break;

            default:
                break;
        }
    }


}
