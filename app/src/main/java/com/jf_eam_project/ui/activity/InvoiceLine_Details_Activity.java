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
import com.jf_eam_project.model.Invoice;
import com.jf_eam_project.model.InvoiceLine;

/**
 * 发票行详情
 */
public class InvoiceLine_Details_Activity extends BaseActivity {

    private static final String TAG = "InvoiceLine_Details_Activity";

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
    private TextView invoicelinenumText; //行
    private TextView invoicenumText; //发票编号
    private TextView linetypeText; //行类型
    private TextView itemnumText; //项目
    private TextView descriptionText; //描述
    private TextView enterbyText; //输入人
    private TextView enterdateText; //输入日期
    private TextView categoryText; //类别
    private TextView qtyforuiText; //数量
    private TextView invoiceunitText; //订购单位
    private TextView issueunitText; //发放单位
    private TextView conversionText; //换算系数
    private TextView unitcostText; //单位成本
    private TextView linecostforuiText; //行成本
    private TextView tax1foruiText; //税
    private TextView proratecostText; //摊派成本
    private TextView poline_storelocText; //库房
    private TextView invoicecost_tositeidnonperText; //地点


    private InvoiceLine invoiceLine; //发票行


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoiceline_details);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        invoiceLine = (InvoiceLine) getIntent().getSerializableExtra("invoiceLine");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        invoicelinenumText = (TextView) findViewById(R.id.invoicelinenum_text_id);
        invoicenumText = (TextView) findViewById(R.id.invoicenum_text_id);
        linetypeText = (TextView) findViewById(R.id.linetype_text_id);
        itemnumText = (TextView) findViewById(R.id.invoiceline_itemnum_text_id);
        descriptionText = (TextView) findViewById(R.id.inv_description_text_id);
        enterbyText = (TextView) findViewById(R.id.enterby_text_id);
        enterdateText = (TextView) findViewById(R.id.enterdate_text_id);
        categoryText = (TextView) findViewById(R.id.category_text_id);
        qtyforuiText = (TextView) findViewById(R.id.qtyforui_text_id);
        invoiceunitText = (TextView) findViewById(R.id.invoiceunit_text_id);
        issueunitText = (TextView) findViewById(R.id.inventory_issueunit_text_id);
        conversionText = (TextView) findViewById(R.id.conversion_text_id);
        unitcostText = (TextView) findViewById(R.id.unitcost_text_id);
        linecostforuiText = (TextView) findViewById(R.id.linecostforui_text_id);
        tax1foruiText = (TextView) findViewById(R.id.tax1forui_text_id);
        proratecostText = (TextView) findViewById(R.id.proratecost_text_id);
        poline_storelocText = (TextView) findViewById(R.id.poline_storeloc_text_id);
        invoicecost_tositeidnonperText = (TextView) findViewById(R.id.invoicecost_tositeidnonper_text);

    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.invoiceline_details_text));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);



        if (invoiceLine != null) {
            invoicelinenumText.setText(invoiceLine.invoicelinenum == null ? "" : invoiceLine.invoicelinenum);
            invoicenumText.setText(invoiceLine.invoicenum== null ? "" : invoiceLine.invoicenum);
            linetypeText.setText(invoiceLine.linetype == null ? "" : invoiceLine.linetype);
            itemnumText.setText(invoiceLine.itemnum == null ? "" : invoiceLine.itemnum);
            descriptionText.setText(invoiceLine.description== null ? "" : invoiceLine.description );
            enterbyText.setText(invoiceLine.enterby == null ? "" : invoiceLine.enterby);
            enterdateText.setText(invoiceLine.enterdate == null ? "" : invoiceLine.enterdate);
            categoryText.setText(invoiceLine.category == null ? "" : invoiceLine.category);
            qtyforuiText.setText(invoiceLine.qtyforui == null ? "" : invoiceLine.qtyforui);
            invoiceunitText.setText(invoiceLine.invoiceunit == null ? "" : invoiceLine.invoiceunit);
            issueunitText.setText(invoiceLine.inventory_issueunit == null ? "" : invoiceLine.inventory_issueunit);
            conversionText.setText(invoiceLine.conversion== null ? "" : invoiceLine.category);
            unitcostText.setText(invoiceLine.unitcost == null ? "" : invoiceLine.unitcost);
            linecostforuiText.setText(invoiceLine.linecostforui == null ? "" : invoiceLine.linecostforui);
            tax1foruiText.setText(invoiceLine.tax1forui == null ? "" : invoiceLine.tax1forui);
            proratecostText.setText(invoiceLine.proratecost == null ? "" : invoiceLine.proratecost);
            poline_storelocText.setText(invoiceLine.poline_storeloc == null ? "" : invoiceLine.poline_storeloc);
            invoicecost_tositeidnonperText.setText(invoiceLine.invoicecost_tositeidnonper == null ? "" : invoiceLine.invoicecost_tositeidnonper);
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



}
