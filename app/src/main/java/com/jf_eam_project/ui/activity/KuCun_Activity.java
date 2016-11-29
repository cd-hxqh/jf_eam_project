package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jf_eam_project.R;

/**
 * 库存管理
 */

public class KuCun_Activity extends BaseActivity {

    private static final String TAG = "KuCun_Activity";
    /**
     * 标题*
     */
    private TextView titleText;
    /**
     * 返回按钮
     **/
    private ImageView backImageView;

    /**
     * 库存
     */
    private LinearLayout inventory_layout;

    /**
     * 领料单
     */
    private LinearLayout material_layout;

    /**
     * 物资编码测试
     */
    private LinearLayout uditemreq_layout;


    /**
     * 调出单
     */
    private LinearLayout material_up_layout;

    /**
     * 调出单
     */
    private LinearLayout material_into_layout;
    /**
     * 物资借用归还
     */
    private LinearLayout udbr_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_kucun);

        findViewById();

        initView();

    }

    @Override
    protected void findViewById() {
        titleText = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        inventory_layout = (LinearLayout) findViewById(R.id.inventory_linear_id);
        material_layout = (LinearLayout) findViewById(R.id.material_layout_id);
        uditemreq_layout = (LinearLayout) findViewById(R.id.uditemreq_linear_id);
        material_up_layout = (LinearLayout) findViewById(R.id.material_up_layout_id);
        material_into_layout = (LinearLayout)findViewById(R.id.material_in_layout_id);
        udbr_layout = (LinearLayout) findViewById(R.id.udbr_linearlayout_id);
    }

    @Override
    protected void initView() {
        titleText.setText(getString(R.string.kcgl_text));
        backImageView.setOnClickListener(backImageViewOnClickListener);
        inventory_layout.setOnClickListener(onClickListener);
        material_layout.setOnClickListener(onClickListener);
        uditemreq_layout.setOnClickListener(onClickListener);
        material_up_layout.setOnClickListener(onClickListener);
        material_into_layout.setOnClickListener(onClickListener);
        udbr_layout.setOnClickListener(onClickListener);
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
                case R.id.inventory_linear_id: //库存

                    Intent intent = new Intent(KuCun_Activity.this, Inventory_Activity.class);
                    startActivityForResult(intent, 0);

                    break;
                case R.id.uditemreq_linear_id: //物资编码申请

                    Intent intent1 = new Intent(KuCun_Activity.this, Uditemreq_listactivity.class);
                    startActivityForResult(intent1, 0);

                    break;

                case R.id.material_layout_id: //领料单
                    Intent intent2 = new Intent(KuCun_Activity.this, Material_ListActivity.class);
                    startActivityForResult(intent2, 0);
                    break;
                case R.id.material_up_layout_id: //调出单
                    Intent intent3 = new Intent(KuCun_Activity.this, Materials_up_ListActivity.class);
                    startActivityForResult(intent3, 0);
                    break;
                case R.id.material_in_layout_id: //调入单
                    Intent intent4 = new Intent(KuCun_Activity.this, Materials_Into_ListActivity.class);
                    startActivityForResult(intent4, 0);
                    break;
                case R.id.udbr_linearlayout_id: //物资借用归还
                    Intent intent5 = new Intent(KuCun_Activity.this, Udbr_ListActivity.class);
                    startActivityForResult(intent5, 0);
                    break;

            }
        }
    };


}
