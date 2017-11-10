package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Po;

/**
 * 采购订单详情
 */
public class PO_Details_Activity extends BaseActivity {

    private static final String TAG = "Po_Details_Activity";

    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;
    /**
     * 菜单*
     */
    private ImageView menuImageView;


    /**
     * 界面信息显示*
     */
    private TextView ponumText; //po编号
    private TextView descriptionText; //描述
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


    private Po po; //采购订单

    private PopupWindow popupWindow;

    private TextView poLineTextView; //采购单行

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_po_details);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        po = (Po) getIntent().getSerializableExtra("po");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        menuImageView = (ImageView) findViewById(R.id.title_add);

        ponumText = (TextView) findViewById(R.id.po_ponum_text);
        descriptionText = (TextView) findViewById(R.id.po_description_text);
        lrrText = (TextView) findViewById(R.id.lrr_text_id);
        lrrqText = (TextView) findViewById(R.id.lrrq_text_id);
        buyercompanyText = (TextView) findViewById(R.id.po_buyercompany_text);
        contractrefnumText = (TextView) findViewById(R.id.contractrefnum_text_id);
        requireddateText = (TextView) findViewById(R.id.requireddate_text_id);
        followupdateText = (TextView) findViewById(R.id.followupdate_text_id);
        currencycodeText = (TextView) findViewById(R.id.po_currencycode_text_id);
        pretaxtotalText = (TextView) findViewById(R.id.po_pretaxtotal_text_id);
        totaltax1Text = (TextView) findViewById(R.id.po_totaltax1_text_id);
        totalcostText = (TextView) findViewById(R.id.po_totalcost_text_id);
        vendorText = (TextView) findViewById(R.id.vendor_text_id);
        contactText = (TextView) findViewById(R.id.contact_text_id);

    }

    @Override
    protected void initView() {
        titleView.setText(getResources().getString(R.string.title_activity_po_details_));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);

        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);


        if (po != null) {
            ponumText.setText(po.getPonum() == null ? "" : po.getPonum());
            descriptionText.setText(po.getDescription() == null ? "" : po.getDescription());
            lrrText.setText(po.getSiteid() == null ? "" : po.getPurchaseagent());
            lrrqText.setText(po.getOrderdate() == null ? "" : po.getOrderdate());
            buyercompanyText.setText(po.getBuyercompany() == null ? "" : po.getBuyercompany());
            contractrefnumText.setText(po.getContractrefnum() == null ? "" : po.getContractrefnum());
            requireddateText.setText(po.getRequireddate() == null ? "" : po.getRequireddate());
            followupdateText.setText(po.getFollowupdate() == null ? "" : po.getFollowupdate());
            currencycodeText.setText(po.getCurrencycode() == null ? "" : po.getCurrencycode());
            pretaxtotalText.setText(po.getPretaxtotal() == null ? "" : po.getPretaxtotal());
            totaltax1Text.setText(po.getTotaltax1() == null ? "" : po.getTotaltax1());
            totalcostText.setText(po.getTotalcost() == null ? "" : po.getTotalcost());
            vendorText.setText(po.getVendordesc() == null ? "" : po.getVendordesc());
            contactText.setText(po.getContact() == null ? "" : po.getContact());
        }


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
     * 显示PopupWindow*
     */
    private void showPopupWindow(View view) {

        View contentView = LayoutInflater.from(PO_Details_Activity.this).inflate(
                R.layout.popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.abc_popup_background_mtrl_mult));
        popupWindow.showAsDropDown(view, 0, 20);

        poLineTextView = (TextView) contentView.findViewById(R.id.popup_text_id);
        poLineTextView.setOnClickListener(polineOnClickListener);

    }


    private View.OnClickListener polineOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PO_Details_Activity.this, Poline_Activity.class);
            intent.putExtra("ponum", po.ponum);
            startActivityForResult(intent, 0);
            popupWindow.dismiss();
        }
    };

}
