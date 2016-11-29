package com.jf_eam_project.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.jf_eam_project.R;
import com.jf_eam_project.ui.fragment.SSDL_fragment;
import com.jf_eam_project.ui.fragment.SWDL_fragment;
import com.jf_eam_project.ui.fragment.XDL_fragment;

/**
 * 风电场电量统计
 **/
public class WindActivity extends BaseActivity {
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
     * 上网电量
     **/
    private SWDL_fragment swdl_fragment;
    /**
     * 损失电量
     **/
    private SSDL_fragment ssdl_fragment;
    /**
     * 限电量
     **/
    private XDL_fragment xdl_fragment;

    /**
     * 上网电量
     **/
    private TextView swdlBtn;
    /**
     * 损失电量
     **/
    private TextView ssdlBtn;
    /**
     * 限电量
     **/
    private TextView xdlBtn;


    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;


    /**分公司编号**/
    private String branch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity);
        getInitData();
        findViewById();
        initView();
        fragmentManager = getFragmentManager();
        // 第一次启动时选中第0个tab
        setTabSelection(0);
    }


    /**获取分公司电量**/
    private void getInitData() {
        branch=getIntent().getStringExtra("branch");
        Log.i("WindActivity","branch="+branch);
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        mainBtn = (Button) findViewById(R.id.main_btn_id);

        swdlBtn = (TextView) findViewById(R.id.swdl_btn_id);
        ssdlBtn = (TextView) findViewById(R.id.ssdl_btn_id);
        xdlBtn = (TextView) findViewById(R.id.xdl_btn_id);

    }

    @Override
    protected void initView() {

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        backImageView.setVisibility(View.VISIBLE);
        titleView.setText(R.string.dltj_text);
        mainBtn.setVisibility(View.GONE);

        backImageView.setOnClickListener(backImageViewOnClickListener);
        swdlBtn.setOnClickListener(swdlBtnOnClickListener);
        ssdlBtn.setOnClickListener(ssdlBtnOnClickListener);
        xdlBtn.setOnClickListener(xdlBtnOnClickListener);
    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };




    private View.OnClickListener swdlBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setTabSelection(0);
        }
    };
    private View.OnClickListener ssdlBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setTabSelection(1);
        }
    };
    private View.OnClickListener xdlBtnOnClickListener = new View.OnClickListener() {
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
                swdlBtn.setBackgroundColor(getResources().getColor(R.color.blue_color));

                if (swdl_fragment == null) {
                    swdl_fragment = new SWDL_fragment();
                    transaction.add(R.id.content, swdl_fragment);
                } else {
                    transaction.show(swdl_fragment);
                }
                break;
            case 1:
                ssdlBtn.setBackgroundColor(getResources().getColor(R.color.blue_color));
                if (ssdl_fragment == null) {
                    ssdl_fragment = new SSDL_fragment();
                    transaction.add(R.id.content, ssdl_fragment);
                } else {
                    transaction.show(ssdl_fragment);
                }
                break;
            case 2:
                xdlBtn.setBackgroundColor(getResources().getColor(R.color.blue_color));
                if (xdl_fragment == null) {
                    xdl_fragment = new XDL_fragment();
                    transaction.add(R.id.content, xdl_fragment);
                } else {
                    transaction.show(xdl_fragment);
                }
                break;

            default:
                if (swdl_fragment == null) {
                    swdl_fragment = new SWDL_fragment();
                    transaction.add(R.id.content, swdl_fragment);
                } else {
                    transaction.show(swdl_fragment);
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
        if (swdl_fragment != null) {
            transaction.hide(swdl_fragment);
        }
        if (ssdl_fragment != null) {
            transaction.hide(ssdl_fragment);
        }
        if (xdl_fragment != null) {
            transaction.hide(xdl_fragment);
        }

    }


    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        swdlBtn.setBackgroundColor(getResources().getColor(R.color.color0));
        ssdlBtn.setBackgroundColor(getResources().getColor(R.color.color0));
        xdlBtn.setBackgroundColor(getResources().getColor(R.color.color0));
    }




}
