package com.jf_eam_project.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.ui.fragment.WoactivityFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/12/3.
 * 工单计划页面
 */
public class Work_PlanActivity extends BaseActivity {

    private TextView titlename;
    private ImageView menuImageView;
    private ImageView backimg;
    private Button woactivity;//任务
    private Button wplabor;//员工
    private Button wpservice;//物料
    private Button wpmaterial;//服务
    private Button wptool;//工具
    private ViewPager mViewPager;
    private int currentIndex = 0;
    private List<Fragment> fragmentlist = new ArrayList<>();
    private WoactivityFragment woactivityFragment;
//    private WplaborFragment wplaborFragment;
//    private WpitemFragment wpitemFragment;

    public WorkOrder workOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workplan);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        workOrder = getIntent().getParcelableExtra("workOrder");
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        backimg = (ImageView) findViewById(R.id.title_back_id);
        woactivity = (Button) findViewById(R.id.work_woactivity);
        wplabor = (Button) findViewById(R.id.work_wplabor);
        wpservice = (Button) findViewById(R.id.work_wpservice);
        wpmaterial = (Button) findViewById(R.id.work_wpmaterial);
        wptool = (Button) findViewById(R.id.work_wptool);
        mViewPager = (ViewPager) findViewById(R.id.pager);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initView() {
        mViewPager.setCurrentItem(currentIndex);
        mViewPager.setOffscreenPageLimit(2);
        titlename.setText(getResources().getString(R.string.work_plan));
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        menuImageView.setImageResource(R.drawable.add_ico);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        woactivity.setBackground(getResources().getDrawable(R.drawable.ab_solid_example));
        woactivity.setOnClickListener(new Buttonlistener());
        wplabor.setOnClickListener(new Buttonlistener());
        wpservice.setOnClickListener(new Buttonlistener());
        wpmaterial.setOnClickListener(new Buttonlistener());
        wptool.setOnClickListener(new Buttonlistener());
        fragmentlist = new ArrayList<>();
        woactivityFragment = new WoactivityFragment(workOrder);
//        wplaborFragment = new WplaborFragment(workOrder);
//        wpitemFragment = new WpitemFragment(workOrder);
        fragmentlist.add(woactivityFragment);
//        fragmentlist.add(woactivityFragment);
//        fragmentlist.add(woactivityFragment);
//        fragmentlist.add(woactivityFragment);
//        fragmentlist.add(woactivityFragment);
        mViewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager()));//设置ViewPager的适配器
        mViewPager.setOnPageChangeListener(new MyPagerOnPageChangeListener());
        woactivity.performClick();
    }

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            if (currentIndex == 0) {

            } else if (currentIndex == 1) {
//                intent = new Intent(Work_PlanActivity.this,AddWplaborActivity.class);
//                startActivity(intent);
            } else if (currentIndex == 2) {

            }
        }
    };

    public class Buttonlistener implements View.OnClickListener {
        public Buttonlistener() {

        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View view) {
            resetTextView();
            if (view.getId() == woactivity.getId()) {
                view.setBackground(getResources().getDrawable(R.drawable.ab_solid_example));
                woactivity.setTextColor(getResources().getColor(R.color.white));
                currentIndex = 0;
            } else if (view.getId() == wplabor.getId()) {
                view.setBackground(getResources().getDrawable(R.drawable.ab_solid_example));
                wplabor.setTextColor(getResources().getColor(R.color.white));
                currentIndex = 1;
            } else if (view.getId() == wpservice.getId()) {
                view.setBackground(getResources().getDrawable(R.drawable.ab_solid_example));
                wpservice.setTextColor(getResources().getColor(R.color.white));
                currentIndex = 2;
            } else if (view.getId() == wpmaterial.getId()) {
                view.setBackground(getResources().getDrawable(R.drawable.ab_solid_example));
                wpmaterial.setTextColor(getResources().getColor(R.color.white));
                currentIndex = 3;
            }else if (view.getId() == wptool.getId()) {
                view.setBackground(getResources().getDrawable(R.drawable.ab_solid_example));
                wptool.setTextColor(getResources().getColor(R.color.white));
                currentIndex = 4;
            }
            mViewPager.setCurrentItem(currentIndex);
        }
    }

    /**
     * ViewPager的适配器
     */
    class MyFrageStatePagerAdapter extends FragmentStatePagerAdapter {

        public MyFrageStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentlist.get(position);
        }

        @Override
        public int getCount() {
            return fragmentlist.size();
        }
    }

    /**
     * ViewPager的PageChangeListener(页面改变的监听器)
     */
    private class MyPagerOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int position, float offset, int offsetPixels) {

        }

        /**
         * 滑动ViewPager的时候
         */
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onPageSelected(int position) {
            resetTextView();
            switch (position) {
                case 0:
                    woactivity.performClick();
                    break;
                case 1:
                    wplabor.performClick();
                    break;
                case 2:
                    wpservice.performClick();
                    break;
                case 3:
                    wpmaterial.performClick();
                    break;
                case 4:
                    wptool.performClick();
                    break;
            }
            currentIndex = position;
        }
    }

    /**
     * 重置颜色
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void resetTextView() {
        woactivity.setTextColor(getResources().getColor(R.color.black));
        wplabor.setTextColor(getResources().getColor(R.color.black));
        wpservice.setTextColor(getResources().getColor(R.color.black));
        wpmaterial.setTextColor(getResources().getColor(R.color.black));
        wptool.setTextColor(getResources().getColor(R.color.black));
    }
}
