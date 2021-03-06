package com.jf_eam_project.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.PRLine;
import com.jf_eam_project.model.PoLine;

/**
 * 采购单行
 */
public class PRLine_Details_Activity extends BaseActivity {

    /**
     * 标题
     */
    private TextView titleView;
    /**
     *返回
     */
    private ImageView backImageView;
    /**
     *界面说明
     */
    private TextView prlinenumText; //行编号
    private TextView linetypeText; //行类型
    private TextView itemnumText; //项目编号
    private TextView descriptionText; //描述
    private TextView storelocText; //仓库位置
    private TextView categoryText; //
    private TextView orderqtyText; //
    private TextView orderunitText; //
    private TextView conversionText; //
    private TextView unitcostText; //
    private TextView linecostText; //
    private TextView tax1Text; //
    private TextView enterbyText; //
    private TextView enterdateText; //
    private TextView requestedbyText; //

    /**
     * PRLine
     */
    private PRLine prLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr_line);
        initData();
        findViewById();
        initView();
    }

    private void initData() {
        prLine = (PRLine) getIntent().getSerializableExtra("prLine");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);


        prlinenumText = (TextView) findViewById(R.id.prline_num_text);
        linetypeText = (TextView) findViewById(R.id.prline_linetype_text);
        itemnumText = (TextView) findViewById(R.id.prline_itemnum_text);
        descriptionText = (TextView) findViewById(R.id.prline_description_text);
        storelocText = (TextView) findViewById(R.id.prline_storeloc_text);
        categoryText = (TextView) findViewById(R.id.prline_category_text);
        orderqtyText = (TextView) findViewById(R.id.prline_orderqty_text);
        orderunitText = (TextView) findViewById(R.id.prline_orderunit_text);
        conversionText = (TextView) findViewById(R.id.prline_conversion_text);
        unitcostText = (TextView) findViewById(R.id.prline_unitcost_text);
        linecostText = (TextView) findViewById(R.id.prline_linecost_text);
        tax1Text = (TextView) findViewById(R.id.prline_tax1_text);
        enterbyText = (TextView) findViewById(R.id.prline_enterby_text);
        enterdateText = (TextView) findViewById(R.id.prline_enterdate_text);
        requestedbyText = (TextView) findViewById(R.id.prline_requestedby_text);
    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.prline_detail_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);

        if (prLine != null) {
            prlinenumText.setText(prLine.getPrlinenum()== null ? "" : prLine.getPrlinenum());
            linetypeText.setText(prLine.getLinetype() == null ? "" : prLine.getLinetype());
            itemnumText.setText(prLine.getItemnum() == null ? "" : prLine.getItemnum());
            descriptionText.setText(prLine.getDescription() == null ? "" : prLine.getDescription());

            storelocText.setText(prLine.getDescription() == null ? "" : prLine.getDescription()); //

            categoryText.setText(prLine.getCategory() == null ? "" : prLine.getCategory());
            orderqtyText.setText(prLine.getOrderqty() == null ? "" : prLine.getOrderqty());
            orderunitText.setText(prLine.getOrderunit() == null ? "" : prLine.getOrderunit());
            conversionText.setText(prLine.getConversion() == null ? "" : prLine.getConversion());
            unitcostText.setText(prLine.getUnitcost() == null ? "" : prLine.getUnitcost());
            linecostText.setText(prLine.getLinecost()== null ? "" : prLine.getLinecost());
            tax1Text.setText(prLine.getTax1()== null ? "" : prLine.getTax1());


            enterbyText.setText(prLine.getEnterby() == null ? "" : prLine.getEnterby());
            enterdateText.setText(prLine.getEnterdate() == null ? "" : prLine.getEnterdate());
            requestedbyText.setText(prLine.getRequestedby() == null ? "" : prLine.getRequestedby());
        }

    }


    /**
     * 返回事件监听
     */
    private View.OnClickListener backImageViewOnClickListenrer = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
