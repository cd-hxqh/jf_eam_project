package com.jf_eam_project.ui.activity;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.jf_eam_project.Dao.UdinspoDao;
import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Option;
import com.jf_eam_project.model.Udinspo;
import com.jf_eam_project.ui.widget.CumTimePickerDialog;
import com.jf_eam_project.utils.GetNowTime;
import com.jf_eam_project.utils.MessageUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 巡检单详情
 */
public class Udinspo_Details_activity extends BaseActivity {

    private static final String TAG = "Udinspo_Details_activity";

    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;

    /**
     * 编辑*
     */
    private ImageView editImageView;
    /**
     * 菜单*
     */
    private ImageView menuImageView;

    /**
     * 巡检备件_btn*
     */
    private Button udinspoassetBtn;


    /**
     * 界面信息显示*
     */
    private TextView insponumText; //巡检单
    private TextView descriptionText; //描述
    private TextView inspplannumText; //计划编号
    private TextView inspschemenumText; //检修方案编号
    private TextView inspobyText; //检修人
    private TextView inspodateText; //检修日期
    private TextView startdateText; //开始时间
    private TextView enddateText; //结束时间
    private TextView statusText; //状态
    private TextView branchText; //分公司
    private TextView farmText; //风电场
    private TextView weatherText; //天气
    private TextView temperatureText; //温度


    private Button confirmBtn; //确认


    private Udinspo udinspo;


    private PopupWindow popupWindow;


    private TextView udinspoasset; //设备部件


    private ProgressDialog mProgressDialog;


    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
    private String[] inspotypeTexts;
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    private String inspotype;

    StringBuffer sb;
    private DatePickerDialog datePickerDialog;
    private CumTimePickerDialog timePickerDialog;

    /**
     * 日期选择标识*
     */
    private int dateMark = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udinspo_details);
        initData();
        findViewById();
        initView();

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        addInspotypeData();
        setDataListener();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        udinspo = (Udinspo) getIntent().getSerializableExtra("udinspo");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        editImageView = (ImageView) findViewById(R.id.title_edit);

        insponumText = (TextView) findViewById(R.id.udinspo_insponum_text);
        descriptionText = (TextView) findViewById(R.id.udinspo_description_text);
        inspplannumText = (TextView) findViewById(R.id.udinspo_inspplannum_text);
        inspschemenumText = (TextView) findViewById(R.id.inspschemenum_text_id);
        inspobyText = (TextView) findViewById(R.id.udinspo_inspoby_text);
        inspodateText = (TextView) findViewById(R.id.udinspo_inspodate_text);
        startdateText = (TextView) findViewById(R.id.startdate_text_id);
        enddateText = (TextView) findViewById(R.id.enddate_text_id);
        statusText = (TextView) findViewById(R.id.statu_text_id);
        branchText = (TextView) findViewById(R.id.description_text_id);
        farmText = (TextView) findViewById(R.id.uddept_description_text_id);
        weatherText = (TextView) findViewById(R.id.weather_text_id);
        temperatureText = (TextView) findViewById(R.id.temperature_text_id);


        confirmBtn = (Button) findViewById(R.id.submit_btn_id);
        udinspoassetBtn = (Button) findViewById(R.id.udinspoasset_btn_id);

    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.details_text));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);

        menuImageView.setImageResource(R.drawable.ic_drawer);
//        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
//        editImageView.setVisibility(View.VISIBLE);
        editImageView.setOnClickListener(editImageViewOnClickListener);
        descriptionText.setFocusable(false);
        descriptionText.setFocusableInTouchMode(false);
        if (udinspo != null) {
            insponumText.setText(udinspo.getInsponum() == null ? "" : udinspo.getInsponum());
            descriptionText.setText(udinspo.getDescription() == null ? "" : udinspo.getDescription());
            inspplannumText.setText(udinspo.udinspmainplandesc == null ? "" : udinspo.udinspmainplandesc);
            inspschemenumText.setText(udinspo.inspschemenumdesc == null ? "" : udinspo.inspschemenumdesc);
            inspobyText.setText(udinspo.inspobydisplayname == null ? "" : udinspo.inspobydisplayname);
            inspodateText.setText(udinspo.getInspodate() == null ? "" : udinspo.getInspodate());
            startdateText.setText(udinspo.getStartdate() == null ? "" : udinspo.getStartdate());
            enddateText.setText(udinspo.getEnddate() == null ? "" : udinspo.getEnddate());


            statusText.setText(udinspo.statusdesc == null ? "" : udinspo.statusdesc);
//            setStatus();


            String branch = udinspo.getBranch();
            if (branch.equals("01001")) {
                branchText.setText("华北分公司");
            } else {
                branchText.setText(udinspo.getBranch() == null ? "" : udinspo.getBranch());
            }
            farmText.setText(udinspo.getUdbelongdesc() == null ? "" : udinspo.getUdbelongdesc());
            weatherText.setText(udinspo.getWeather() == null ? "" : udinspo.getWeather());
            temperatureText.setText(udinspo.getTemperature() == null ? "" : udinspo.getTemperature());
        }

//        inspotypeText.setOnClickListener(inspotypeOnClickListener);

        inspobyText.setOnClickListener(inspobyOnClickListener);

        inspodateText.setOnClickListener(inspodateOnClickListener);

        enddateText.setOnClickListener(inspodateOnClickListener);

        confirmBtn.setOnClickListener(confirmBtnOnClickListener);
        udinspoassetBtn.setOnClickListener(udinspoassetBtnOnClickListener);

    }

//    /**
//     * 设置状态*
//     */
//    private void setStatus(String status) {
//        String statusText = null;
//        if (status.equals("APPR")) { //已批准
//            statusText = "已批准";
//        }else if(status.equals("CAN")){ //	取消
//            statusText = "取消";
//        }else if(status.equals("CLOSE")){ //关闭
//            statusText = "关闭";
//        }else if(status.equals("CLOSE")){ //关闭
//            statusText = "关闭";
//        }
//
//
//    }


    private View.OnClickListener inspodateOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            sb = new StringBuffer();
            datePickerDialog.show();
            if (view.getId() == R.id.udinspo_inspodate_text) {
                dateMark = 1;
            } else if (view.getId() == R.id.enddate_text_id) {
                dateMark = 2;
            }
        }
    };


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
            if (dateMark == 1) {
                inspodateText.setText(sb);
            } else if (dateMark == 2) {
                enddateText.setText(sb);
            }
        }
    }


    private View.OnClickListener inspobyOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Udinspo_Details_activity.this, OptionActivity.class);
            intent.putExtra("requestCode", Constants.PERSON);
            startActivityForResult(intent, Constants.PERSON);
        }
    };


    /**
     * 添加巡检数据*
     */
    private void addInspotypeData() {
        String[] inspotypes = getResources().getStringArray(R.array.inspotype_tab_titles);
        inspotypeTexts = getResources().getStringArray(R.array.inspotype_tab_text);

        for (int i = 0; i < inspotypes.length; i++)
            mMenuItems.add(new DialogMenuItem(inspotypes[i], 0));


    }

//    private void NormalListDialog() {
//        final NormalListDialog dialog = new NormalListDialog(Udinspo_Details_activity.this, mMenuItems);
//        dialog.title("请选择")//
//                .showAnim(mBasIn)//
//                .dismissAnim(mBasOut)//
//                .show();
//        dialog.setOnOperItemClickL(new OnOperItemClickL() {
//            @Override
//            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
//                inspotype = inspotypeTexts[position];
//                inspotypeText.setText(inspotype);
//
//
//                dialog.dismiss();
//            }
//        });
//    }


    /**
     * 类型选择*
     */
    private View.OnClickListener inspotypeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            NormalListDialog();
        }
    };


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
//        descriptionText.setFocusable(true);
//        descriptionText.setFocusableInTouchMode(true);
//        inspotypeText.setEnabled(true);
        inspobyText.setEnabled(true);
        inspodateText.setEnabled(true);
        enddateText.setEnabled(true);
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
     * 菜单按钮*
     */
    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupWindow(menuImageView);
        }
    };

    /**
     * 删除操作*
     */
    private View.OnClickListener deleteBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };


    /**
     * 显示PopupWindow*
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void showPopupWindow(View view) {

        View contentView = LayoutInflater.from(Udinspo_Details_activity.this).inflate(
                R.layout.udinspo_popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.abc_popup_background_mtrl_mult));
        popupWindow.showAsDropDown(view, 0, 20);

        udinspoasset = (TextView) contentView.findViewById(R.id.udinspoasset_text_id);
        udinspoasset.setOnClickListener(udinspoassetOnClickListener);

    }


    private View.OnClickListener udinspoassetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Udinspo_Details_activity.this, Udinspoasset_Activity.class);
            intent.putExtra("insponum", udinspo.insponum);
            startActivityForResult(intent, 0);
            popupWindow.dismiss();
        }
    };


    private View.OnClickListener confirmBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            encapsulationData();
        }
    };


    /**
     * 执行*
     */
    private View.OnClickListener udinspoassetBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //更新状态
            updateUdinspo();

            Intent intent = new Intent(Udinspo_Details_activity.this, Udinspoassetnew_Activity.class);
            intent.putExtra("insponum", udinspo.insponum);
            intent.putExtra("branch", udinspo.branch);
            intent.putExtra("udbelong", udinspo.udbelong);
            intent.putExtra("assettype", udinspo.assettype);
            startActivityForResult(intent, 0);
        }
    };


    /**
     * 更新操作状态*
     */
    private void updateUdinspo() {

        if (udinspo.getStartdate() == null||udinspo.getStartdate().equals("")) {
            udinspo.setStartdate(GetNowTime.getTime());
        }
        if (udinspo.getOperation() == null) {
            udinspo.setOperation("执行中");
        }
        new UdinspoDao(Udinspo_Details_activity.this).update(udinspo);
    }


    /**
     * 数据封装*
     */
    private void encapsulationData() {

        final NormalDialog dialog = new NormalDialog(Udinspo_Details_activity.this);
        dialog.content("确定更新巡检单吗？")//
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

    private void startAsyncTask() {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String data = submitData();

                String result = getBaseApplication().getWsService().UpdatePO(Udinspo_Details_activity.this, data, udinspo.insponum);

                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeProgressDialog();

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String insponum = jsonObject.getString("INSPONUM");
                    String success = jsonObject.getString("status");
                    String errorNo = jsonObject.getString("errorNo");

                    if (success.equals("数据更新成功") && errorNo.equals("0")) {
                        MessageUtils.showMiddleToast(Udinspo_Details_activity.this, "数据更新成功");
                        setResult(Constants.REFRESH);
                        finish();
                    } else {
                        MessageUtils.showMiddleToast(Udinspo_Details_activity.this, "数据更新失败");
                        setResult(Constants.REFRESH);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }.execute();
    }


    /**
     * 封装数据*
     */
    private String submitData() {


        String insponum = udinspo.insponum;
        String description = descriptionText.getText().toString();
        String inspplannum = inspplannumText.getText().toString();
//        String inspotype = inspotypeText.getText().toString();
//        String createby = createbyText.getText().toString();
//        String createdate = createdateText.getText().toString();
        String inspoby = inspobyText.getText().toString();
        String inspodate = inspodateText.getText().toString();
        String enddate = enddateText.getText().toString();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("INSPONUM", insponum);
            if (!description.equals(udinspo.description)) {
                jsonObject.put("DESCRIPTION", description);
            }
            if (!inspplannum.equals(udinspo.inspplannum)) {
                jsonObject.put("INSPPLANNUM", inspplannum);
            }
            if (!inspotype.equals(udinspo.inspotype)) {
                jsonObject.put("INSPOTYPE", inspotype);
            }
//            if (!createby.equals(udinspo.createby)) {
//                jsonObject.put("CREATEBY", createby);
//            }
//            if (!createdate.equals(udinspo.createdate)) {
//                jsonObject.put("CREATEDATE", createdate);
//            }
            if (!inspoby.equals(udinspo.inspoby)) {
                jsonObject.put("INSPOBY", inspoby);
            }
            if (!inspodate.equals(udinspo.inspodate)) {
                jsonObject.put("INSPODATE", inspodate);
            }
            if (!inspodate.equals(udinspo.enddate)) {
                jsonObject.put("ENDDATE", enddate);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        switch (resultCode) {

            case Constants.PERSON:
                option = (Option) data.getSerializableExtra("option");
                inspobyText.setText(option.getName());
                break;

        }
    }

}
