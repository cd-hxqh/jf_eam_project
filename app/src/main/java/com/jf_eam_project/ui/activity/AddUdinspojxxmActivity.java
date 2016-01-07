package com.jf_eam_project.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jf_eam_project.Dao.UdinspojxxmDao;
import com.jf_eam_project.R;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.model.Udinspojxxm;

/**
 * 检修项目标准新建
 */
public class AddUdinspojxxmActivity extends BaseActivity {

    private static final String TAG = "AddUdinspojxxmActivity";

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
    private TextView udinspojxxmlinenumText; //序号
    private EditText descriptionText; //巡检项目标准
    private EditText executionText; //巡检情况描述
    private EditText checkbyText; //巡检人员


    private Udinspojxxm udinspojxxm; //设备备件


    private Button submitBtn; //确认按钮

    /**
     * 序号*
     */
    private int linenum;
    /**巡检备件**/
    private String udinspoassetnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udinspojxxm_add);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        udinspojxxm = (Udinspojxxm) getIntent().getSerializableExtra("Udinspojxxm");
        linenum = getIntent().getIntExtra("udinspoassetlinenum", 0);
        udinspoassetnum = getIntent().getStringExtra("udinspoassetnum");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        udinspojxxmlinenumText = (TextView) findViewById(R.id.udinspoasset_udinspoassetlinenum_text);
        descriptionText = (EditText) findViewById(R.id.udinspojxxm_description_text);
        executionText = (EditText) findViewById(R.id.udinspojxxm_execution_text);
        checkbyText = (EditText) findViewById(R.id.ud_udinspojxxm4_text);


        submitBtn = (Button) findViewById(R.id.submit_btn_id);

    }


    @Override
    protected void initView() {
        titleView.setText(getString(R.string.add_udinspojxxm_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);


        udinspojxxmlinenumText.setText(linenum == 0 ? "1" : (linenum + 1) + "");

        submitBtn.setOnClickListener(submitBtnOnClickListener);

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
     * 确认按钮*
     */
    private View.OnClickListener submitBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Udinspojxxm udinspojxxm=saveUdinspojxxmInfo();

            Intent intent = getIntent();
            intent.putExtra("udinspojxxm", udinspojxxm);
            setResult(0, intent);
            finish();
        }


    };

    /**
     * 保存信息*
     */
    private Udinspojxxm saveUdinspojxxmInfo() {
        udinspojxxm = new Udinspojxxm();
        udinspojxxm.setUdinspojxxmlinenum(udinspojxxmlinenumText.getText().toString());
        udinspojxxm.setUdinspoassetnum(udinspoassetnum);
        udinspojxxm.setDescription(descriptionText.getText().toString());
        udinspojxxm.setExecution(executionText.getText().toString());
        udinspojxxm.setCheckby(checkbyText.getText().toString());
        new UdinspojxxmDao(AddUdinspojxxmActivity.this).insert(udinspojxxm);
        return udinspojxxm;
    }




}
