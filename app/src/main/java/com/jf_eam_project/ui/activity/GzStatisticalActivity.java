package com.jf_eam_project.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.ui.fragment.DQGZ_fragment;
import com.jf_eam_project.ui.fragment.DQQX_fragment;
import com.jf_eam_project.ui.fragment.FJGZ_fragment;

/**
 * 故障统计
 **/
public class GzStatisticalActivity extends BaseActivity {
    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;

    /**
     * 主菜单
     **/
    private Button mainBtn;

    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;


    /**
     * 电气故障
     **/
    private DQGZ_fragment dqgz_fragment;
    /**
     * 风机故障
     **/
    private FJGZ_fragment fjgz_fragment;
    /**
     * 电气缺陷
     **/
    private DQQX_fragment dqqx_fragment;

    /**
     * 电气故障
     **/
    private TextView dqgzText;
    /**
     * 风机故障
     **/
    private TextView fjgzText;
    /**
     * 电气缺陷
     **/
    private TextView dqqxText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gzstatistical);
        findViewById();
        initView();
        fragmentManager = getFragmentManager();
        // 第一次启动时选中第0个tab
        setTabSelection(0);
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        mainBtn = (Button) findViewById(R.id.main_btn_id);

        dqgzText = (TextView) findViewById(R.id.gztj_text_id);
        fjgzText = (TextView) findViewById(R.id.fjgz_text_id);
        dqqxText = (TextView) findViewById(R.id.dqqx_text_id);

    }

    @Override
    protected void initView() {

        backImageView.setVisibility(View.VISIBLE);
        titleView.setText(R.string.gz_text);
        mainBtn.setVisibility(View.GONE);

        backImageView.setOnClickListener(backImageViewOnClickListener);
        dqgzText.setOnClickListener(dqgzTextOnClickListener);
        fjgzText.setOnClickListener(fjgzTextOnClickListener);
        dqqxText.setOnClickListener(dqqxTextOnClickListener);
    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };


    private View.OnClickListener dqgzTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setTabSelection(0);
        }
    };
    private View.OnClickListener fjgzTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setTabSelection(1);
        }
    };
    private View.OnClickListener dqqxTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setTabSelection(2);
        }
    };


    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
     */
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了上网tab时，
                dqgzText.setBackgroundColor(getResources().getColor(R.color.blue_color));

                if (dqgz_fragment == null) {
                    dqgz_fragment = new DQGZ_fragment();
                    transaction.add(R.id.content, dqgz_fragment);
                } else {
                    transaction.show(dqgz_fragment);
                }
                break;
            case 1:
                fjgzText.setBackgroundColor(getResources().getColor(R.color.blue_color));
                if (fjgz_fragment == null) {
                    fjgz_fragment = new FJGZ_fragment();
                    transaction.add(R.id.content, fjgz_fragment);
                } else {
                    transaction.show(fjgz_fragment);
                }
                break;
            case 2:
                dqqxText.setBackgroundColor(getResources().getColor(R.color.blue_color));
                if (dqqx_fragment == null) {
                    dqqx_fragment = new DQQX_fragment();
                    transaction.add(R.id.content, dqqx_fragment);
                } else {
                    transaction.show(dqqx_fragment);
                }
                break;

            default:
                if (dqgz_fragment == null) {
                    dqgz_fragment = new DQGZ_fragment();
                    transaction.add(R.id.content, dqgz_fragment);
                } else {
                    transaction.show(dqgz_fragment);
                }
                break;
        }
        transaction.commit();
    }


    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (dqgz_fragment != null) {
            transaction.hide(dqgz_fragment);
        }
        if (fjgz_fragment != null) {
            transaction.hide(fjgz_fragment);
        }
        if (dqqx_fragment != null) {
            transaction.hide(dqqx_fragment);
        }

    }


    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        dqgzText.setBackgroundColor(getResources().getColor(R.color.color0));
        fjgzText.setBackgroundColor(getResources().getColor(R.color.color0));
        dqqxText.setBackgroundColor(getResources().getColor(R.color.color0));
    }


}
