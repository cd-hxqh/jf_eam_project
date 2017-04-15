package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.jf_eam_project.R;
import com.jf_eam_project.manager.AppManager;
import com.jf_eam_project.utils.AccountUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新的主菜单信息
 */

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    /**
     * 标题*
     */
    private TextView titleText;
    /**
     * 返回按钮
     **/
    private ImageView backImageView;
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    private GridView gridView;

    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    // 图片封装为一个数组
    private int[] icon = null;
    private String[] iconName = null;

    private int permissions = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);
        findViewById();

        initView();

    }

    @Override
    protected void findViewById() {
        titleText = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        gridView = (GridView) findViewById(R.id.noScrollgridview);
    }

    @Override
    protected void initView() {
        titleText.setText(getString(R.string.title_activity_main));
        backImageView.setVisibility(View.GONE);
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();


        isShowPage();

        //新建List
        data_list = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        //新建适配器
        String[] from = {"image", "text"};
        int[] to = {R.id.image, R.id.text};
        sim_adapter = new SimpleAdapter(this, data_list, R.layout.gridview_item, from, to);
        //配置适配器
        gridView.setAdapter(sim_adapter);
        gridView.setOnItemClickListener(gridViewOnItemClickListener);
    }


    public List<Map<String, Object>> getData() {
        //cion和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }


    //判断需要显示的页面
    private void isShowPage() {
        permissions = AccountUtils.getPermissions(MainActivity.this);
        if (permissions == 1) {
            icon = new int[]{R.drawable.ic_lcsp, R.drawable.ic_cggl,R.drawable.ic_tmsm,
                    R.drawable.ic_gzqx, R.drawable.ic_sz};
            iconName = new String[]{"流程审批", "采购管理","二维码/条码", "Kpi统计",
                    "设置"};
        } else if (permissions == 2) {
            icon = new int[]{R.drawable.ic_lcsp, R.drawable.ic_cggl,
                    R.drawable.ic_gzqx, R.drawable.ic_sz};
            iconName = new String[]{"流程审批", "采购管理", "二维码/条码","Kpi统计",
                    "设置"};
        } else if (permissions == 3) {
            icon = new int[]{R.drawable.ic_lcsp, R.drawable.ic_gdgl,
                    R.drawable.ic_xjgl, R.drawable.ic_kcgl, R.drawable.ic_cggl,
                    R.drawable.ic_gzqx, R.drawable.ic_tmsm, R.drawable.ic_sz};
            iconName = new String[]{"流程审批", "工单管理", "巡检管理", "库存管理", "采购管理", "故障缺陷", "二维码/条码",
                    "设置"};

        } else if (permissions == 4 || permissions == 5) {
            icon = new int[]{R.drawable.ic_lcsp, R.drawable.ic_gdgl,
                    R.drawable.ic_xjgl, R.drawable.ic_kcgl, R.drawable.ic_cggl,
                    R.drawable.ic_gzqx, R.drawable.ic_tmsm, R.drawable.ic_kpitj, R.drawable.ic_sz};
            iconName = new String[]{"流程审批", "工单管理", "巡检管理", "库存管理", "采购管理", "故障缺陷", "二维码/条码", "Kpi统计",
                    "设置"};

        }
    }

    private AdapterView.OnItemClickListener gridViewOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int postion, long l) {
            Intent intent = null;
            if (permissions == 1) {
                switch (postion) {
                    case 0:
                        intent = new Intent(MainActivity.this, Wfment_Activity.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, CaiGou_Activity.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, MipcaActivityCapture.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, LeadershipActivity.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, SheZhi_Activity.class);
                        startActivityForResult(intent, 0);
                        break;
                }
            } else if (permissions == 2) {
                switch (postion) {
                    case 0:
                        intent = new Intent(MainActivity.this, Wfment_Activity.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, CaiGou_Activity.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, MipcaActivityCapture.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, LeadershipActivity.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, SheZhi_Activity.class);
                        startActivityForResult(intent, 0);
                        break;
                }
            } else if (permissions == 3) {
                switch (postion) {
                    case 0:
                        intent = new Intent(MainActivity.this, Wfment_Activity.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, WorkOrderActivity.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, XunJan_Activity.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, KuCun_Activity.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, CaiGou_Activity.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 5:
                        intent = new Intent(MainActivity.this, GuZhang_Activity.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 6:
                        intent = new Intent(MainActivity.this, MipcaActivityCapture.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 7:
                        intent = new Intent(MainActivity.this, SheZhi_Activity.class);
                        startActivityForResult(intent, 0);
                        break;

                }
            } else if (permissions == 4 || permissions == 5) {
                switch (postion) {
                    case 0:
                        intent = new Intent(MainActivity.this, Wfment_Activity.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, WorkOrderActivity.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, XunJan_Activity.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, KuCun_Activity.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, CaiGou_Activity.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 5:
                        intent = new Intent(MainActivity.this, GuZhang_Activity.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 6:
                        intent = new Intent(MainActivity.this, MipcaActivityCapture.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 7:
                        intent = new Intent(MainActivity.this, LeadershipActivity.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 8:
                        intent = new Intent(MainActivity.this, SheZhi_Activity.class);
                        startActivityForResult(intent, 0);
                        break;
                }
            }

        }
    };


    /**
     * 退出程序
     */
    public void showAlertDialog() {
        final NormalDialog dialog = new NormalDialog(MainActivity.this);
        dialog.content("确定退出程序吗")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {


                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        AppManager.AppExit(MainActivity.this);
                    }
                });

    }


    @Override
    public void onBackPressed() {
        showAlertDialog();
    }


}
