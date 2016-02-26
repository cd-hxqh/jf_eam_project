package com.jf_eam_project.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jf_eam_project.R;


/**
 * 查询结果界面*
 */
public class Results_Activity extends BaseActivity {
    private static final String TAG = "Results_Activity";
    /**
     * 返回按钮*
     */
    private ImageView backImageView;
    /**
     * 标题*
     */
    private TextView titleText;
    /**
     * 扫描结果*
     */
    String result;

    /**扫描结果**/
    private TextView resultText;

    /**资产详情**/
    private Button assetBtn;
    /**检修记录**/
    private Button checkBtn;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getInit();
        findViewById();
        initView();
    }

    /**
     * 获取上个界面的数据*
     */
    private void getInit() {
        result = getIntent().getExtras().getString("result");

        Log.i(TAG,"result="+result);

    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleText = (TextView) findViewById(R.id.title_name);
        resultText=(TextView)findViewById(R.id.results_id);

    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleText.setText("扫描结果");
        resultText.setText(result);


    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };




}
