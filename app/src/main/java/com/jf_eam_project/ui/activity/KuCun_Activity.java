package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jf_eam_project.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 库存管理
 */

public class KuCun_Activity extends BaseActivity {

    @Bind(R.id.title_name)
    TextView titleText;//标题
    @Bind(R.id.title_back_id)
    ImageView backImageView;//返回按钮

    @Bind(R.id.inventory_linear_id)
    LinearLayout inventory_layout; //库存
    @Bind(R.id.wzff_layout_id)
    LinearLayout wzff_layout;//物资发放

    @Bind(R.id.material_layout_id)
    LinearLayout material_layout;//领料单

    @Bind(R.id.uditemreq_linear_id)
    LinearLayout uditemreq_layout;//编码申请


    @Bind(R.id.material_up_layout_id)
    LinearLayout material_up_layout;//调出单

    @Bind(R.id.material_in_layout_id)
    LinearLayout material_into_layout;//调入单
    @Bind(R.id.udbr_linearlayout_id)
    LinearLayout udbr_layout;//物资借用归还


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_kucun);
        ButterKnife.bind(this);
        findViewById();

        initView();

    }

    @Override
    protected void findViewById() {

    }

    @Override
    protected void initView() {
        titleText.setText(getString(R.string.kcgl_text));
    }

    //返回事件
    @OnClick(R.id.title_back_id)
    void setBackImageViewOnClickListener() {
        finish();
    }

    //库存
    @OnClick(R.id.inventory_linear_id)
    void setInventory_layoutOnClickListener() {
        Intent intent = new Intent(KuCun_Activity.this, Inventory_Activity.class);
        startActivityForResult(intent, 0);
    }

    //物资发放
    @OnClick(R.id.wzff_layout_id)
    void setWzff_layoutOnClickListener() {
        Intent intent = new Intent(KuCun_Activity.this, Wzff_ListActivity.class);
        startActivityForResult(intent, 0);
    }

    //领料单
    @OnClick(R.id.material_layout_id)
    void setMaterial_layoutOnClickListener() {
        Intent intent = new Intent(KuCun_Activity.this, Material_ListActivity.class);
        startActivityForResult(intent, 0);
    }

    //物资编码申请
    @OnClick(R.id.uditemreq_linear_id)
    void setUditemreq_layoutOnClickListener() {
        Intent intent = new Intent(KuCun_Activity.this, Uditemreq_listactivity.class);
        startActivityForResult(intent, 0);
    }

    //调出单
    @OnClick(R.id.material_up_layout_id)
    void setMaterial_up_layoutOnClickListener() {
        Intent intent = new Intent(KuCun_Activity.this, Materials_up_ListActivity.class);
        startActivityForResult(intent, 0);
    }

    //调入单
    @OnClick(R.id.material_in_layout_id)
    void setMaterial_into_layoutOnClickListener() {
        Intent intent = new Intent(KuCun_Activity.this, Materials_Into_ListActivity.class);
        startActivityForResult(intent, 0);
    }

    //物资借用归还
    @OnClick(R.id.udbr_linearlayout_id)
    void setUdbr_layoutOnClickListener() {
        Intent intent = new Intent(KuCun_Activity.this, Udbr_ListActivity.class);
        startActivityForResult(intent, 0);

    }


}
