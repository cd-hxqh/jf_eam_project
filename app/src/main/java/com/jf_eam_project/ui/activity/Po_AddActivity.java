package com.jf_eam_project.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.jf_eam_project.R;
import com.jf_eam_project.api.JsonUtils;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Companies;
import com.jf_eam_project.model.Compcontact;
import com.jf_eam_project.model.Currency;
import com.jf_eam_project.model.Po;
import com.jf_eam_project.ui.widget.CumTimePickerDialog;
import com.jf_eam_project.utils.AccountUtils;
import com.jf_eam_project.utils.GetNowTime;
import com.jf_eam_project.utils.MessageUtils;

import java.util.Calendar;

/**
 * Created by think on 2015/11/30.
 * 新增采购订单
 */
public class Po_AddActivity extends BaseActivity {
    private static final String TAG = "Po_AddActivity";
    public static final int CURRENCY_CODE = 1010; //货币请求码
    public static final int COMPANIES_CODE = 1011; //供应商请求码
    public static final int COMPCONTACT_CODE = 1012; //联系人请求码
    private TextView titlename; //标题
    private ImageView menuImageView; //菜单按钮
    private ImageView backlayout; //返回按钮

    private EditText description;//描述

    private TextView lrrText; //录入人

    private TextView lrrqText; //录入日期

    private TextView buyercompanyText; //买方公司

    private TextView contractrefnumText; //合同引用

    private TextView requireddateText; //计划到货日期

    private TextView followupdateText; //实际到货日期

    private TextView currencycodeText; //货币

    private TextView pretaxtotalText; //税前总计

    private TextView totaltax1Text; //税款总计

    private TextView totalcostText; //成本总计

    private TextView vendorText; //供应商

    private TextView contactText; //联系人

    private DatePickerDialog datePickerDialog;
    private CumTimePickerDialog timePickerDialog;
    StringBuffer sb;
    private int layoutnum;

    private Button submitBtn; //提交按钮

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    /**供应商编号**/
    private String vendornum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_po_add);

        findViewById();
        initView();
    }


    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backlayout = (ImageView) findViewById(R.id.title_back_id);
        menuImageView = (ImageView) findViewById(R.id.title_add);

        description = (EditText) findViewById(R.id.description_text);
        lrrText = (TextView) findViewById(R.id.lrr_text_id);
        lrrqText = (TextView) findViewById(R.id.lrrq_text_id);
        buyercompanyText = (TextView) findViewById(R.id.po_buyercompany_text_id);
        contractrefnumText = (TextView) findViewById(R.id.contractrefnum_text_id);
        requireddateText = (TextView) findViewById(R.id.requireddate_text_id);
        followupdateText = (TextView) findViewById(R.id.followupdate_text_id);
        currencycodeText = (TextView) findViewById(R.id.po_currencycode_text_id);
        pretaxtotalText = (TextView) findViewById(R.id.po_pretaxtotal_text_id);
        totaltax1Text = (TextView) findViewById(R.id.po_totaltax1_text_id);
        totalcostText = (TextView) findViewById(R.id.po_totalcost_text_id);
        vendorText = (TextView) findViewById(R.id.vendor_text_id);
        contactText = (TextView) findViewById(R.id.contact_text_id);

        submitBtn = (Button) findViewById(R.id.submit_add_id);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.po_add_title);
//        menuImageView.setImageResource(R.drawable.ic_drawer);
//        menuImageView.setVisibility(View.VISIBLE);
//        menuImageView.setOnClickListener(menuImageViewOnClickListener);

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();

        backlayout.setOnClickListener(backlayoutOnClickListener);
        lrrText.setText(AccountUtils.getDisplayName(Po_AddActivity.this));
        lrrqText.setText(GetNowTime.getTime());
        requireddateText.setOnClickListener(new MydateListener());
        followupdateText.setOnClickListener(new MydateListener());
        currencycodeText.setText("CNY");

        setDataListener();
        currencycodeText.setOnClickListener(currencycodeTextOnClickListener);
        vendorText.setOnClickListener(vendorTextOnClickListener);
        contactText.setOnClickListener(contactTextOnClickListener);

        submitBtn.setOnClickListener(submitBtnOnClickListener);
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


    private View.OnClickListener backlayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };


    /**
     * 选择货币
     **/
    private View.OnClickListener currencycodeTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Po_AddActivity.this, Currencycode_Choose_Activity.class);
            startActivityForResult(intent, CURRENCY_CODE);
        }
    };

    /**
     * 选择供应商
     **/
    private View.OnClickListener vendorTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Po_AddActivity.this, Companies_Choose_Activity.class);
            startActivityForResult(intent, COMPANIES_CODE);
        }
    };
    /**
     * 选择联系人
     **/
    private View.OnClickListener contactTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Po_AddActivity.this, Compcontact_Choose_Activity.class);
            startActivityForResult(intent, COMPCONTACT_CODE);
        }
    };

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

            if (layoutnum == requireddateText.getId()) {
                requireddateText.setText(sb);
            } else if (layoutnum == followupdateText.getId()) {
                followupdateText.setText(sb);
            }

        }
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "requestCode" + requestCode + ",resultCode=" + resultCode);
        switch (resultCode) {
            case CURRENCY_CODE:
                Currency currency = (Currency) data.getSerializableExtra("option");
                currencycodeText.setText(currency.getCurrencycode());
                break;
            case COMPANIES_CODE:
                Companies companies = (Companies) data.getSerializableExtra("option");
                Log.i(TAG,"vendornum="+vendornum);
                vendornum=companies.getCompaniescode();
                vendorText.setText(companies.getName());
                break;
            case COMPCONTACT_CODE:
                Compcontact compcontact = (Compcontact) data.getSerializableExtra("option");
                contactText.setText(compcontact.getContact());
                break;
        }
    }

    //提交数据
    private View.OnClickListener submitBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final NormalDialog dialog = new NormalDialog(Po_AddActivity.this);
            dialog.content("确定新增采购订单吗?")//
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

        };
    };

    /**
     * 提交数据*
     */
    private void startAsyncTask() {

        final String updataInfo = JsonUtils.potoJson(getPoInfo());
        Log.i(TAG, "updataInfo=" + updataInfo);
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String addresult = getBaseApplication().getWsService().InsertGENERAL(Po_AddActivity.this, updataInfo,"PO","PONUM",AccountUtils.getPersonId(Po_AddActivity.this));
                Log.i(TAG, "addresult=" + addresult);
                return addresult;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s == null || s.equals("")) {
                    MessageUtils.showMiddleToast(Po_AddActivity.this, "新增失败");
                } else if (!s.isEmpty()) {
                    MessageUtils.showMiddleToast(Po_AddActivity.this, s);
                } else {
                    Toast.makeText(Po_AddActivity.this, "新增" + s + "成功", Toast.LENGTH_SHORT).show();
                }
                closeProgressDialog();
            }
        }.execute();
    }

    //获取界面信息
    private Po getPoInfo() {
        String desction = description.getText().toString(); //描述
        String orderdate = lrrqText.getText().toString(); //录入日期
        String buyercompany = buyercompanyText.getText().toString(); //买方公司
        String contractrefnum = contractrefnumText.getText().toString(); //合同引用
        String requireddate = requireddateText.getText().toString(); //计划到货日期
        String followupdate = followupdateText.getText().toString(); //实际到货日期
        String currencycode = currencycodeText.getText().toString(); //货币
        String pretaxtotal = pretaxtotalText.getText().toString(); //税前总计
        String totaltax1 = totaltax1Text.getText().toString(); //税款总计
        String totalcost = totalcostText.getText().toString(); //成本总计
        String contact = contactText.getText().toString(); //联系人
        Po po = new Po();

        po.setDescription(desction);
        po.setPurchaseagent(AccountUtils.getPersonId(Po_AddActivity.this));  //录入人
        po.setOrderdate(orderdate); //录入日期
        po.setBuyercompany(buyercompany);
        po.setContractrefnum(contractrefnum);
        po.setRequireddate(requireddate);
        po.setFollowupdate(followupdate);
        po.setCurrencycode(currencycode);
        po.setPretaxtotal(pretaxtotal);
        po.setTotaltax1(totaltax1);
        po.setTotalcost(totalcost);
        po.setVendor(vendornum);  //供应商
        po.setContact(contact); //联系人

        po.setSiteid(AccountUtils.getSite(Po_AddActivity.this)); //站点
        po.setUdappname(Constants.PO_APPID); //Udappname
        po.setBranch(com.jf_eam_project.utils.JsonUtils.CutOutBranch(AccountUtils.getDepartment(Po_AddActivity.this))); //分公司
        po.setUdbelong(AccountUtils.getDepartment(Po_AddActivity.this)); //运行单位
        return po;

    }
}
