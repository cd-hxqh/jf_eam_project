package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Invoice;
import com.jf_eam_project.model.Po;

/**
 * 发票详情
 */
public class Invoice_Details_Activity extends BaseActivity {

    private static final String TAG = "Invoice_Details_Activity";

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
    private TextView invoicenumText; //发票
    private TextView descriptionText; //描述
    private TextView sitedescText; //地点
    private TextView documenttypeText; //类型
    private TextView statusText; //状态
    private TextView enterbyText; //输入人
    private TextView enterdateText; //输入日期
    private TextView invoicedateText; //发票日期
    private TextView glpostdateText; //发布日期
    private TextView duedateText; //到期日
    private TextView vendorText; //公司
    private TextView pretaxtotalforuiText; //税前总计
    private TextView totaltax1foruiText; //税款总计
    private TextView totalcostforuiText; //发票总计
    private TextView currencycodeText; //货币


    private Invoice invoice; //发票

    private PopupWindow popupWindow;


    private TextView invoicelineText; //发票行

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_details);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        invoice = (Invoice) getIntent().getSerializableExtra("invoice");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        menuImageView = (ImageView) findViewById(R.id.title_add);

        invoicenumText = (TextView) findViewById(R.id.invoice_invoicenum_text);
        descriptionText = (TextView) findViewById(R.id.invoice_description_text);
        sitedescText = (TextView) findViewById(R.id.invoice_siteid_text);
        documenttypeText = (TextView) findViewById(R.id.invoice_documenttype_text);
        statusText = (TextView) findViewById(R.id.invoice_status_text);
        enterbyText = (TextView) findViewById(R.id.invoice_enterby_text);
        enterdateText = (TextView) findViewById(R.id.invoice_enterdate_text);
        invoicedateText = (TextView) findViewById(R.id.invoice_invoicedate_text);
        glpostdateText = (TextView) findViewById(R.id.invoice_glpostdate_text);
        duedateText = (TextView) findViewById(R.id.invoice_duedate_text);
        vendorText = (TextView) findViewById(R.id.invoice_vendor_text);
        pretaxtotalforuiText = (TextView) findViewById(R.id.invoice_pretaxtotalforui_text);
        totaltax1foruiText = (TextView) findViewById(R.id.invoice_totaltax1forui_text);
        totalcostforuiText = (TextView) findViewById(R.id.invoice_totalcostforui_text);
        currencycodeText = (TextView) findViewById(R.id.invoice_currencycode_text);

    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.invoice_details_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);

        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);


        if (invoice != null) {
            invoicenumText.setText(invoice.getInvoicenum() == null ? "" : invoice.getInvoicenum());
            descriptionText.setText(invoice.getDescription() == null ? "" : invoice.getDescription());
            sitedescText.setText(invoice.getSiteid() == null ? "" : invoice.getSiteid());
            documenttypeText.setText(invoice.getDocumenttype() == null ? "" : invoice.getDocumenttype());
            statusText.setText(invoice.getStatus() == null ? "" : invoice.getStatus() );
            enterbyText.setText(invoice.getEnterby() == null ? "" : invoice.getEnterby());
            enterdateText.setText(invoice.getEnterdate() == null ? "" : invoice.getEnterdate());
            invoicedateText.setText(invoice.getInvoicedate() == null ? "" : invoice.getInvoicedate());
            glpostdateText.setText(invoice.getGlpostdate() == null ? "" : invoice.getGlpostdate());
//            duedateText.setText(invoice.getDocumenttype() == null ? "" : invoice.getOrderdate());
            vendorText.setText(invoice.getVendor() == null ? "" : invoice.getVendor());
            pretaxtotalforuiText.setText(invoice.getPretaxtotalforui() == null ? "" : invoice.getPretaxtotalforui());
            totaltax1foruiText.setText(invoice.getTotaltax1forui() == null ? "" : invoice.getTotaltax1forui());
            totalcostforuiText.setText(invoice.getTotalcostforui() == null ? "" : invoice.getTotalcostforui());
            currencycodeText.setText(invoice.getCurrencycode() == null ? "" : invoice.getCurrencycode());
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

        View contentView = LayoutInflater.from(Invoice_Details_Activity.this).inflate(
                R.layout.popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.abc_popup_background_mtrl_mult));
        popupWindow.showAsDropDown(view,0,20);

        invoicelineText = (TextView) contentView.findViewById(R.id.popup_text_id);
        invoicelineText.setText(getString(R.string.invoiceline_text));
        invoicelineText.setOnClickListener(polineOnClickListener);

    }


    private View.OnClickListener polineOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Invoice_Details_Activity.this, InvoiceLine_Activity.class);
            intent.putExtra("invoicenum", invoice.invoicenum);
            startActivityForResult(intent, 0);
            popupWindow.dismiss();
        }
    };

}
