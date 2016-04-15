package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.jf_eam_project.R;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.utils.AccountUtils;
import com.jf_eam_project.utils.GetNowTime;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 缺陷与故障提报单
 */
public class Createreport_Activity extends BaseActivity {

    private static final String TAG = "Createreport_Activity";

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
    private TextView reporttypeText; //提报单类别
    private TextView culevelText; //缺陷或故障等级
    private TextView assettypeText; //设备类别
    private TextView assetnumText; //设备
    private TextView locationText; //位置
    private TextView descriptionText; //描述
    private TextView descriptionxxText; //详细描述
    private TextView reportbyText; //提报人
    private TextView reporttimeText; //提报日期

    /**
     * 新增*
     */
    private Button addButton;


    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    /**设备类型**/
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
    /**提报单类别**/
    private ArrayList<DialogMenuItem> cMenuItems = new ArrayList<>();
    /**故障等级**/
    private ArrayList<DialogMenuItem> tMenuItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createreport);
        initData();
        findViewById();
        initView();
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        addReporttypeData(); //提报单
        addLctypeData(); //设备类别
        addCulevelData(); //故障等级
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {

    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        reporttypeText = (TextView) findViewById(R.id.reporttype_text_id);
        culevelText = (TextView) findViewById(R.id.culevel_text_id);
        assettypeText = (TextView) findViewById(R.id.assettype_text_id);
        assetnumText = (TextView) findViewById(R.id.assetnum_text_id);
        locationText = (TextView) findViewById(R.id.locaton_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        descriptionxxText = (TextView) findViewById(R.id.descriptionxx_text_id);
        reportbyText = (TextView) findViewById(R.id.reportby_text_id);
        reporttimeText = (TextView) findViewById(R.id.reporttime_text_id);

        addButton = (Button) findViewById(R.id.createreport_add_button);

    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.createreport_text));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);

        reporttypeText.setOnClickListener(reporttypeTextOnClickListener);
        culevelText.setOnClickListener(culevelTextOnClickListener);

        assettypeText.setOnClickListener(assettypeTextOnClickListener);

        reportbyText.setText(AccountUtils.getUserName(Createreport_Activity.this));
        reporttimeText.setText(GetNowTime.getTime());

        addButton.setOnClickListener(addButtonOnClickListener);
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

    /**提报单类别**/
    private View.OnClickListener reporttypeTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            NormalListDialog1();
        }
    };
    /**故障等级**/
    private View.OnClickListener culevelTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            NormalListDialog2();
        }
    };


    /**设备类别**/
    private View.OnClickListener assettypeTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            NormalListDialog();
        }
    };






    /**设备类别**/
    private void NormalListDialog() {
        final NormalListDialog dialog = new NormalListDialog(Createreport_Activity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                assettypeText.setText(mMenuItems.get(position).mOperName);

                dialog.dismiss();
            }
        });
    }

    /**提报单类别**/
    private void NormalListDialog1() {
        final NormalListDialog dialog = new NormalListDialog(Createreport_Activity.this, cMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                reporttypeText.setText(cMenuItems.get(position).mOperName);

                dialog.dismiss();
            }
        });
    }

    /**故障等级**/
    private void NormalListDialog2() {
        final NormalListDialog dialog = new NormalListDialog(Createreport_Activity.this, tMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                culevelText.setText(tMenuItems.get(position).mOperName);

                dialog.dismiss();
            }
        });
    }


    /**
     * 添加数据*
     */
    private void addLctypeData() {
        String[] lctypes = getResources().getStringArray(R.array.lctype_tab_titles);

        for (int i = 0; i < lctypes.length; i++)
            mMenuItems.add(new DialogMenuItem(lctypes[i], 0));


    }
    /**
     * 添加数据*
     */
    private void addReporttypeData() {
        String[] lctypes = getResources().getStringArray(R.array.reporttype_tab_titles);

        for (int i = 0; i < lctypes.length; i++)
            cMenuItems.add(new DialogMenuItem(lctypes[i], 0));


    }
    /**
     * 添加数据*
     */
    private void addCulevelData() {
        String[] lctypes = getResources().getStringArray(R.array.culevel_tab_titles);

        for (int i = 0; i < lctypes.length; i++)
            tMenuItems.add(new DialogMenuItem(lctypes[i], 0));


    }


    /**保存提报单信息**/
    private View.OnClickListener addButtonOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            saveReport();
        }
    };



    private String saveReport(){
        String reporttype=reporttypeText.getText().toString(); //提报单类别
        String culevel=culevelText.getText().toString(); //缺陷或故障等级
        String assettype=assettypeText.getText().toString(); //设备类别
        String assetnum=assetnumText.getText().toString(); //设备
        String location=locationText.getText().toString(); //位置
        String description=descriptionText.getText().toString(); //描述
        String descriptionxx=descriptionxxText.getText().toString(); //详细描述
        String reportby=reportbyText.getText().toString(); //提报人
        String reporttime=reporttimeText.getText().toString(); //提报时间

        JSONObject jsonObject=new JSONObject();

        try {
            jsonObject.put("reporttype",reporttype);
            jsonObject.put("culevel",culevel);
            jsonObject.put("assettype",assettype);
            jsonObject.put("assetnum",assetnum);
            jsonObject.put("location",location);
            jsonObject.put("description",description);
            jsonObject.put("descriptionxx",descriptionxx);
            jsonObject.put("reportby",reportby);
            jsonObject.put("reporttime",reporttime);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i(TAG,"jsonObject="+jsonObject.toString());

        return jsonObject.toString();
    }



}
