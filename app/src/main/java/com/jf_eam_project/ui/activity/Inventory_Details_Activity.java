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
import com.jf_eam_project.model.Inventory;
import com.jf_eam_project.model.Invoice;

/**
 * 库存详情
 */
public class Inventory_Details_Activity extends BaseActivity {

    private static final String TAG = "Inventory_Details_Activity";

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
    private TextView itemnumText; //项目
    private TextView descriptionText; //描述
    private TextView locationText; //库房
    private TextView lottypeText; //批次类型
    private TextView statusText; //状态
    private TextView siteidText; //地点
    private TextView issueunitsText; //发放单位
    private TextView curbaltotalText; //余量


    private Inventory inventory; //库存

//    private PopupWindow popupWindow;
//
//    private LinearLayout polineLinearLayout; //采购单行

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_details);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        inventory = (Inventory) getIntent().getSerializableExtra("inventory");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        menuImageView = (ImageView) findViewById(R.id.title_add);

        itemnumText = (TextView) findViewById(R.id.inventory_itemnum_text);
        descriptionText = (TextView) findViewById(R.id.inventory_description_text);
        locationText = (TextView) findViewById(R.id.inventory_location_text);
        lottypeText = (TextView) findViewById(R.id.inventory_lottype_text);
        statusText = (TextView) findViewById(R.id.inventory_status_text);
        siteidText = (TextView) findViewById(R.id.inventory_siteid_text);
        issueunitsText = (TextView) findViewById(R.id.inventory_issueunit_text);
        curbaltotalText = (TextView) findViewById(R.id.curbaltotal_text_id);

    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.inventory_detail_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);

        menuImageView.setImageResource(R.drawable.ic_drawer);
//        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);


        if (inventory != null) {
            itemnumText.setText(inventory.getItemnum() == null ? "" : inventory.getItemnum());
            descriptionText.setText(inventory.getDescription() == null ? "" : inventory.getDescription());
            locationText.setText(inventory.getLocation() == null ? "" : inventory.getLocation());
            lottypeText.setText(inventory.getLottype() == null ? "" : inventory.getLottype());
            statusText.setText(inventory.getStatus() == null ? "" : inventory.getStatus() );
            siteidText.setText(inventory.getSiteid() == null ? "" : inventory.getSiteid());
            issueunitsText.setText(inventory.getIssueunit() == null ? "" : inventory.getIssueunit());
            curbaltotalText.setText(inventory.curbal == null ? "" : inventory.curbal);
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
//            showPopupWindow(menuImageView);
        }
    };

//    /**
//     * 显示PopupWindow*
//     */
//    private void showPopupWindow(View view) {
//
//        View contentView = LayoutInflater.from(Inventory_Details_Activity.this).inflate(
//                R.layout.popup_window, null);
//
//
//        popupWindow = new PopupWindow(contentView,
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//        popupWindow.setTouchable(true);
//        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
//
//        popupWindow.setBackgroundDrawable(getResources().getDrawable(
//                R.drawable.popup_background_mtrl_mult));
//        popupWindow.showAsDropDown(view,0,-40);
//
//        polineLinearLayout = (LinearLayout) contentView.findViewById(R.id.poline_linearlayout_id);
//        polineLinearLayout.setOnClickListener(polineOnClickListener);
//
//    }
//
//
//    private View.OnClickListener polineOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(Inventory_Details_Activity.this, Poline_Activity.class);
//            intent.putExtra("invoicenum", invoice.invoicenum);
//            startActivityForResult(intent, 0);
//            popupWindow.dismiss();
//        }
//    };

}
