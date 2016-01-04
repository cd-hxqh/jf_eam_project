package com.jf_eam_project.ui.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Option;
import com.jf_eam_project.model.Webservice_result;
import com.jf_eam_project.ui.widget.CumTimePickerDialog;
import com.jf_eam_project.utils.AccountUtils;
import com.jf_eam_project.utils.GetNowTime;
import com.jf_eam_project.utils.MessageUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 巡检单新增
 */
public class AddUdinspoActivity extends BaseActivity {

    private static final String TAG = "AddUdinspoActivity";

    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;

    /**
     * 菜单按钮*
     */
    private ImageView menuImageView;

    /**
     * 提交按钮*
     */
    private Button submitBtn;

    /**
     * 新增界面说明*
     */

    private EditText descEditText; //描述
    private TextView inspotypeText; //巡检单类型
    private TextView createbyText; //创建人
    private TextView createdateText; //创建时间
    private TextView inspobyText; //巡检人
    private TextView inspodateText; //巡检日期


    private String inspotype;

    private DatePickerDialog datePickerDialog;
    private CumTimePickerDialog timePickerDialog;

    StringBuffer sb;

    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
    private String[] inspotypeTexts;
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;


    private ProgressDialog mProgressDialog;

    private String result;

    private PopupWindow popupWindow;

    private TextView udinspoasset; //设备部件
    private  String insponum; //巡检单编号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_udinspo);
        findViewById();
        initView();


        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        addInspotypeData();
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        menuImageView = (ImageView) findViewById(R.id.title_add);


        descEditText = (EditText) findViewById(R.id.udinspo_description_text);
        inspotypeText = (TextView) findViewById(R.id.udinspo_inspotype_text);
        createbyText = (TextView) findViewById(R.id.udinspo_createby_text);
        createdateText = (TextView) findViewById(R.id.udinspo_createdate_text);
        inspobyText = (TextView) findViewById(R.id.udinspo_inspoby_text);
        inspodateText = (TextView) findViewById(R.id.udinspo_inspodate_text);

        submitBtn = (Button) findViewById(R.id.submit_btn_id);

    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.udinspo_detail_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);
        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);

        createbyText.setText(AccountUtils.getUserName(AddUdinspoActivity.this));
        createdateText.setText(GetNowTime.getTime());
        setDataListener();
        inspodateText.setOnClickListener(inspodateOnClickListener);

        inspotypeText.setOnClickListener(inspotypeOnClickListener);

        inspobyText.setOnClickListener(inspobyOnClickListener);

        submitBtn.setOnClickListener(submitBtnOnClickListener);

        menuImageView.setOnClickListener(menuImageViewOnClickListener);

    }

    /**
     * 菜单按钮*
     */
    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupWindow(menuImageView);
        }
    };


    private View.OnClickListener inspodateOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            sb = new StringBuffer();
            datePickerDialog.show();
        }
    };


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

            inspodateText.setText(sb);
        }
    }


    private View.OnClickListener inspotypeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            NormalListDialog();
        }
    };

    private void NormalListDialog() {
        final NormalListDialog dialog = new NormalListDialog(AddUdinspoActivity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                inspotypeText.setText(mMenuItems.get(position).mOperName);

                inspotype=inspotypeTexts[position];
                dialog.dismiss();
            }
        });
    }


    /**
     * 添加巡检数据*
     */
    private void addInspotypeData() {
        String[] inspotypes = getResources().getStringArray(R.array.inspotype_tab_titles);
        inspotypeTexts = getResources().getStringArray(R.array.inspotype_tab_text);

        for (int i = 0; i < inspotypes.length; i++)
            mMenuItems.add(new DialogMenuItem(inspotypes[i], 0));



    }


    private View.OnClickListener inspobyOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AddUdinspoActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", Constants.PERSON);
            startActivityForResult(intent, Constants.PERSON);
        }
    };


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


    private View.OnClickListener submitBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            encapsulationData();
        }
    };


    /**
     * 数据封装*
     */
    private void encapsulationData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddUdinspoActivity.this);
        builder.setMessage("确定新增巡检单吗？").setTitle("提示")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                mProgressDialog = ProgressDialog.show(AddUdinspoActivity.this, null,
                        getString(R.string.inputing), true, true);
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setCancelable(false);
                new AsyncTask<String, String, String>() {
                    @Override
                    protected String doInBackground(String... strings) {
                        String data = submitData();

                        result = getBaseApplication().getWsService().InsertPO(data);

                        return result;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        mProgressDialog.dismiss();


                        Log.i(TAG, "s=" + s);

                        try {
                            JSONObject jsonObject=new JSONObject(s);
                            String insponum=jsonObject.getString("INSPONUM");
                            String success=jsonObject.getString("success");
                            String errorNo=jsonObject.getString("errorNo");

                            if (success.equals("成功")&&errorNo.equals("0")) {
                                MessageUtils.showMiddleToast(AddUdinspoActivity.this, "提交成功");
                            } else {
                                MessageUtils.showMiddleToast(AddUdinspoActivity.this, "提交失败");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }.execute();
            }
        }).create().show();


    }

    /**
     * 封装数据*
     */
    private String submitData() {

        String desc = descEditText.getText().toString();
        String createby = createbyText.getText().toString();
        String createdate = createdateText.getText().toString();
        String inspoby = inspobyText.getText().toString();
        String inspodate = inspodateText.getText().toString();


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("DESCRIPTION", desc);
            jsonObject.put("INSPOTYPE", inspotype);
            jsonObject.put("CREATEBY", createby);
            jsonObject.put("CREATEDATE", createdate);
            jsonObject.put("INSPOBY", inspoby);
            jsonObject.put("INSPODATE", inspodate);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }


    /**
     * 显示PopupWindow*
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void showPopupWindow(View view) {

        View contentView = LayoutInflater.from(AddUdinspoActivity.this).inflate(
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
            Intent intent = new Intent(AddUdinspoActivity.this, Udinspoasset_Activity.class);
            intent.putExtra("insponum",insponum);
            startActivityForResult(intent, 0);
            popupWindow.dismiss();
        }
    };




}
