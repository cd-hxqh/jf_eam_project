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
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.model.Wplabor;
import com.jf_eam_project.model.Wpmaterial;
import com.jf_eam_project.ui.fragment.WoactivityFragment;
import com.jf_eam_project.ui.fragment.WplaborFragment;
import com.jf_eam_project.ui.fragment.WpmaterialFragment;
import com.jf_eam_project.ui.fragment.WpserviceFragment;
import com.jf_eam_project.ui.fragment.WptoolFragment;

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
    //    private Button wpservice;//服务
    private Button wpmaterial;//物料
    //    private Button wptool;//工具
    private ViewPager mViewPager;
    private int currentIndex = 0;
    private List<Fragment> fragmentlist = new ArrayList<>();
    private WoactivityFragment woactivityFragment;
    private WplaborFragment wplaborFragment;
    private WpmaterialFragment wpmaterialFragment;
//    private WpserviceFragment wpserviceFragment;
//    private WptoolFragment wptoolFragment;

    public WorkOrder workOrder;
    private ArrayList<Woactivity> woactivityList = new ArrayList<>();
    private ArrayList<Wplabor> wplaborList = new ArrayList<>();
    private ArrayList<Wpmaterial> wpmaterialList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workplan);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
        woactivityList = (ArrayList<Woactivity>) getIntent().getSerializableExtra("woactivityList");
        wplaborList = (ArrayList<Wplabor>) getIntent().getSerializableExtra("wplaborList");
        wpmaterialList = (ArrayList<Wpmaterial>) getIntent().getSerializableExtra("wpmaterialList");
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        backimg = (ImageView) findViewById(R.id.title_back_id);
        woactivity = (Button) findViewById(R.id.work_woactivity);
        wplabor = (Button) findViewById(R.id.work_wplabor);
//        wpservice = (Button) findViewById(R.id.work_wpservice);
        wpmaterial = (Button) findViewById(R.id.work_wpmaterial);
//        wptool = (Button) findViewById(R.id.work_wptool);
        mViewPager = (ViewPager) findViewById(R.id.pager);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initView() {
        mViewPager.setCurrentItem(currentIndex);
        mViewPager.setOffscreenPageLimit(1);
        titlename.setText(getResources().getString(R.string.work_plan));
        backimg.setOnClickListener(backOnClickListener);
        menuImageView.setImageResource(R.drawable.add_ico);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        woactivity.setBackground(getResources().getDrawable(R.drawable.ab_solid_example));
        woactivity.setOnClickListener(new Buttonlistener());
        wplabor.setOnClickListener(new Buttonlistener());
//        wpservice.setOnClickListener(new Buttonlistener());
        wpmaterial.setOnClickListener(new Buttonlistener());
//        wptool.setOnClickListener(new Buttonlistener());
        fragmentlist = new ArrayList<>();
        woactivityFragment = new WoactivityFragment(workOrder);
        wplaborFragment = new WplaborFragment(workOrder);
        wpmaterialFragment = new WpmaterialFragment(workOrder);
//        wpserviceFragment = new WpserviceFragment(workOrder);
//        wptoolFragment = new WptoolFragment(workOrder);
        fragmentlist.add(woactivityFragment);
        fragmentlist.add(wplaborFragment);
        fragmentlist.add(wpmaterialFragment);
//        fragmentlist.add(wpserviceFragment);
//        fragmentlist.add(wptoolFragment);
        mViewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager()));//设置ViewPager的适配器
        mViewPager.setOnPageChangeListener(new MyPagerOnPageChangeListener());
        woactivity.performClick();
    }

    private View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            intent.putExtra("woactivityList", woactivityList);
            intent.putExtra("wplaborList", wplaborList);
            intent.putExtra("wpmaterialList", wpmaterialList);
            Work_PlanActivity.this.setResult(1000, intent);
            finish();
        }
    };

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            if (currentIndex == 0) {
                intent = new Intent(Work_PlanActivity.this, WoactivityAddNewActivity.class);
                intent.putExtra("taskid", (woactivityList.size() + 1) * 10);
                startActivityForResult(intent, 0);
            } else if (currentIndex == 1) {
                intent = new Intent(Work_PlanActivity.this, WplaborAddNewActivity.class);
                intent.putExtra("woactivityList", woactivityList);
                startActivityForResult(intent, 1);
            } else if (currentIndex == 2) {
                intent = new Intent(Work_PlanActivity.this, WpmaterialAddNewActivity.class);
                intent.putExtra("woactivityList", woactivityList);
                startActivityForResult(intent, 2);
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
            } else if (view.getId() == wpmaterial.getId()) {
                view.setBackground(getResources().getDrawable(R.drawable.ab_solid_example));
                wpmaterial.setTextColor(getResources().getColor(R.color.white));
                currentIndex = 2;
            }
//            else if (view.getId() == wpservice.getId()) {
//                view.setBackground(getResources().getDrawable(R.drawable.ab_solid_example));
//                wpservice.setTextColor(getResources().getColor(R.color.white));
//                currentIndex = 3;
//            }else if (view.getId() == wptool.getId()) {
//                view.setBackground(getResources().getDrawable(R.drawable.ab_solid_example));
//                wptool.setTextColor(getResources().getColor(R.color.white));
//                currentIndex = 4;
//            }
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
                    wpmaterial.performClick();
                    break;
//                case 3:
//                    wpservice.performClick();
//                    break;
//                case 4:
//                    wptool.performClick();
//                    break;
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
        woactivity.setBackground(getResources().getDrawable(R.color.light_gray));
        wplabor.setTextColor(getResources().getColor(R.color.black));
        wplabor.setBackground(getResources().getDrawable(R.color.light_gray));
//        wpservice.setTextColor(getResources().getColor(R.color.black));
//        wpservice.setBackground(getResources().getDrawable(R.color.light_gray));
        wpmaterial.setTextColor(getResources().getColor(R.color.black));
        wpmaterial.setBackground(getResources().getDrawable(R.color.light_gray));
//        wptool.setTextColor(getResources().getColor(R.color.black));
//        wptool.setBackground(getResources().getDrawable(R.color.light_gray));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (resultCode) {
            case 0:
                if(data != null) {
                    Woactivity woactivity = (Woactivity) data.getSerializableExtra("woactivity");
                    woactivityList.add(woactivity);
                    woactivityFragment.woactivityAdapter.update(woactivityList, true);
                    woactivityFragment.nodatalayout.setVisibility(View.GONE);
                }
                break;
            case 1:
                if(data!=null) {
                    Wplabor wplabor = (Wplabor) data.getSerializableExtra("wplabor");
                    wplaborList.add(wplabor);
                    wplaborFragment.wplaborAdapter.update(wplaborList, true);
                    wplaborFragment.nodatalayout.setVisibility(View.GONE);
                }
                break;
            case 2:
                if(data != null) {
                    Wpmaterial wpmaterial = (Wpmaterial) data.getSerializableExtra("wpmaterial");
                    wpmaterialList.add(wpmaterial);
                    wpmaterialFragment.wpmaterialAdapter.update(wpmaterialList, true);
                    wpmaterialFragment.nodatalayout.setVisibility(View.GONE);
                }
                break;
        }
    }
}
