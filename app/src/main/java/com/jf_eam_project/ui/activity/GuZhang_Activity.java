package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jf_eam_project.R;

/**
 * 故障缺陷界面
 */

public class GuZhang_Activity extends BaseActivity {

    private static final String TAG = "GuZhang_Activity";
    /**
     * 标题*
     */
    private TextView titleText;
    /**
     * 返回按钮
     **/
    private ImageView backImageView;

    /**
     * 故障提报单
     */
    private LinearLayout gztbd_layout;


    /**
     * 缺陷提报单
     */
    private LinearLayout qxtbd_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_guzhang);

        findViewById();

        initView();

    }

    @Override
    protected void findViewById() {
        titleText = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        gztbd_layout = (LinearLayout) findViewById(R.id.udinspo_dqdjd_id);
        qxtbd_layout = (LinearLayout) findViewById(R.id.udinspo_fjdjd_id);
    }

    @Override
    protected void initView() {
        titleText.setText(getString(R.string.gzqx_text));
        backImageView.setOnClickListener(backImageViewOnClickListener);
        gztbd_layout.setOnClickListener(onClickListener);
        qxtbd_layout.setOnClickListener(onClickListener);
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
                case R.id.udinspo_dqdjd_id: //故障提报单

                    Intent intent = new Intent(GuZhang_Activity.this, Udreport_Activity.class);
                    intent.putExtra("title", GuZhang_Activity.this.getString(R.string.gztbd_text));
                    intent.putExtra("apptype", "FAULT");
                    startActivityForResult(intent, 0);

                    break;
                case R.id.udinspo_fjdjd_id: //缺陷提报单

                    Intent intent1 = new Intent(GuZhang_Activity.this, Udreport_Activity.class);
                    intent1.putExtra("title", GuZhang_Activity.this.getString(R.string.qxtbd_text));
                    intent1.putExtra("apptype", "HIDDEN");
                    startActivityForResult(intent1, 0);

                    break;


            }
        }
    };


}
