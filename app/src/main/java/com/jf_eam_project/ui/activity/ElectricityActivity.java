package com.jf_eam_project.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.jf_eam_project.R;
import com.jf_eam_project.ui.fragment.SSDL_fragment;
import com.jf_eam_project.ui.fragment.SWDL_fragment;
import com.jf_eam_project.ui.fragment.XDL_fragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 电量统计
 **/
public class ElectricityActivity extends BaseActivity {
    @Bind(R.id.title_name)
    TextView titleView; //标题
    @Bind(R.id.title_back_id)
    ImageView backImageView;//返回按钮

    @Bind(R.id.title_add)
    ImageView refreshImageView;//刷新

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
    @Bind(R.id.swdl_btn_id)
    TextView swdlBtn;//上网电量
    /**
     *
     **/
    @Bind(R.id.ssdl_btn_id)
    TextView ssdlBtn;//损失电量
    /**
     *
     **/
    @Bind(R.id.xdl_btn_id)
    TextView xdlBtn;//限电量


    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity);
        ButterKnife.bind(this);
        findViewById();
        initView();
        fragmentManager = getFragmentManager();
        // 第一次启动时选中第0个tab
        setTabSelection(0);
    }

    @Override
    protected void findViewById() {

    }

    @Override
    protected void initView() {

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        titleView.setText(R.string.dltj_text);
        refreshImageView.setImageResource(R.drawable.ic_refresh);
        refreshImageView.setVisibility(View.VISIBLE);

    }

    @OnClick(R.id.title_back_id)
    void setBackImageViewOnClickListener() {
        finish();
    }


    @OnClick(R.id.swdl_btn_id)
    void setSwdlBtnOnClickListener() {
        setTabSelection(0);
    }

    @OnClick(R.id.ssdl_btn_id)
    void setSsdlBtnOnClickListener() {
        setTabSelection(1);
    }

    @OnClick(R.id.xdl_btn_id)
    void setXdlBtnOnClickListener() {
        setTabSelection(2);
    }


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
