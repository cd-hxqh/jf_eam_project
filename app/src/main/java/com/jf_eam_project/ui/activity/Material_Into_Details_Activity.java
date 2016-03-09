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
import com.jf_eam_project.model.WorkOrder;

/**
 * 物资调入单详情
 */
public class Material_Into_Details_Activity extends BaseActivity {

    private static final String TAG = "Material_Into_Details_Activity";

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
    private TextView ponumText; //物资调入单
    private TextView descriptionText; //描述
    private TextView siteidText; //地点
    private TextView statusText; //状态
    private TextView purchaseagentText; //接收人
    private TextView orderdateText; //创建日期
    private TextView udtargetbranchText; //分公司
    private TextView udtargetbelongText; //风电场
    private TextView pretaxtotalText; //税前总计
    private TextView totaltax1Text; //税款总计
    private TextView totalcostText; //成本总计

    private TextView currencycodeText; //货币


    private Po po; //物资调入单

    private PopupWindow popupWindow;

    /**
     * 调入物料明细*
     */
    private TextView wpmaterialText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_in_details);
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

        ponumText = (TextView) findViewById(R.id.ponum_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        siteidText = (TextView) findViewById(R.id.siteid_text_id);
        statusText = (TextView) findViewById(R.id.status_text_id);
        purchaseagentText = (TextView) findViewById(R.id.purchaseagent_text_id);
        orderdateText = (TextView) findViewById(R.id.orderdate_text_id);
        udtargetbranchText = (TextView) findViewById(R.id.uddept_description_text_id);
        udtargetbelongText = (TextView) findViewById(R.id.udtargetbelong_text_id);
        pretaxtotalText = (TextView) findViewById(R.id.pretaxtotal_text_id);
        totaltax1Text = (TextView) findViewById(R.id.totaltax1_text);
        totalcostText = (TextView) findViewById(R.id.totalcost_text_id);


        currencycodeText = (TextView) findViewById(R.id.currencycode_text_id);

    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.material_into_details_text));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);

        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);


        if (po != null) {
            ponumText.setText(po.ponum == null ? "" : po.ponum);
            descriptionText.setText(po.description == null ? "" : po.description);
            siteidText.setText(po.siteid == null ? "" : po.siteid);
            statusText.setText(po.status == null ? "" : po.status);
            purchaseagentText.setText(po.purchaseagent == null ? "" : po.purchaseagent);
            orderdateText.setText(po.orderdate == null ? "" : po.orderdate);
//            udtargetbranchText.setText(po.u == null ? "" : workOrder.getStatus());
//            udtargetbelongText.setText(po.u == null ? "" : workOrder.getStatus());
            pretaxtotalText.setText(po.pretaxtotal == null ? "" : po.pretaxtotal);
            totaltax1Text.setText(po.totaltax1 == null ? "" : po.totaltax1);
            totalcostText.setText(po.totalcost == null ? "" : po.totalcost);
            currencycodeText.setText(po.currencycode == null ? "" : po.currencycode);
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

        View contentView = LayoutInflater.from(Material_Into_Details_Activity.this).inflate(
                R.layout.material_popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.abc_popup_background_mtrl_mult));
        popupWindow.showAsDropDown(view, 0, 20);

        wpmaterialText = (TextView) contentView.findViewById(R.id.wpmaterial_popup_text_id);
        wpmaterialText.setText(getString(R.string.material_into_poline_text));
        wpmaterialText.setOnClickListener(wpmaterialTextOnClickListener);

    }

    private View.OnClickListener wpmaterialTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Material_Into_Details_Activity.this, Poline_Activity.class);

            intent.putExtra("mark", 1);
            intent.putExtra("ponum", po.ponum);
            startActivityForResult(intent, 0);
            popupWindow.dismiss();

        }
    };

}
