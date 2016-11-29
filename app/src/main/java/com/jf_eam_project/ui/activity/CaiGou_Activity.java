package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jf_eam_project.R;

/**
 * 采购管理界面
 */

public class CaiGou_Activity extends BaseActivity {

    private static final String TAG = "CaiGou_Activity";
    /**
     * 标题*
     */
    private TextView titleText;
    /**
     * 返回按钮
     **/
    private ImageView backImageView;

    /**
     * 采购计划*
     */
    private LinearLayout po_plan_layout;
    /**
     * 采购订单*
     */
    private LinearLayout po_order_layout;
    /**
     * 发票*
     */
    private LinearLayout po_invoice_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_caigou);

        findViewById();

        initView();

    }

    @Override
    protected void findViewById() {
        titleText = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        po_plan_layout = (LinearLayout) findViewById(R.id.po_linear_plan_id);
        po_order_layout = (LinearLayout) findViewById(R.id.po_linear_order_id);
        po_invoice_layout = (LinearLayout) findViewById(R.id.po_linear_invoice_id);
    }

    @Override
    protected void initView() {
        titleText.setText(getString(R.string.gcgl_text));
        backImageView.setOnClickListener(backImageViewOnClickListener);
        po_plan_layout.setOnClickListener(onClickListener);
        po_order_layout.setOnClickListener(onClickListener);
        po_invoice_layout.setOnClickListener(onClickListener);
    }















    private View.OnClickListener backImageViewOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };







    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            switch (v.getId()) {
                case R.id.po_linear_plan_id: //采购计划

                    Intent intent = new Intent(CaiGou_Activity.this, Pr_Activity.class);
                    startActivityForResult(intent, 0);

                    break;

                case R.id.po_linear_order_id: //采购订单
                    Intent intent1 = new Intent(CaiGou_Activity.this, Po_order_Activity.class);
                    startActivityForResult(intent1, 0);
                    break;
                case R.id.po_linear_invoice_id: //发票
                    Intent intent2 = new Intent(CaiGou_Activity.this, Invoice_Activity.class);
                    startActivityForResult(intent2, 0);
                    break;

            }
        }
    };


}
