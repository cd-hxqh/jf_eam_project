package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Option;
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.model.Wplabor;

import java.util.ArrayList;

/**
 * Created by think on 2015/12/7.
 * 计划员工详情页面
 */
public class WplaborDetailsActivity extends BaseActivity {
    private Wplabor wplabor;
    private int position;

    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 返回*
     */
    private ImageView backImageView;

//    private TextView taskid;//任务
    private TextView craft;//工种
    private TextView quantity;//数量
    private TextView laborhrs;//常规时数
    private Button ok;//确认

//    private ArrayList<Woactivity> woactivityList = new ArrayList<>();
//    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
//    private BaseAnimatorSet mBasIn;
//    private BaseAnimatorSet mBasOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wplabor_details);

        geiIntentData();
        findViewById();
        initView();
    }

    /**
     * 获取数据*
     */
    private void geiIntentData() {
        wplabor = (Wplabor) getIntent().getSerializableExtra("wplabor");
//        woactivityList = (ArrayList<Woactivity>) getIntent().getSerializableExtra("woactivityList");
        position = getIntent().getIntExtra("position", 0);
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

//        taskid = (TextView) findViewById(R.id.wplabor_taskid);
        craft = (TextView) findViewById(R.id.wplabor_craft);
        quantity = (TextView) findViewById(R.id.wplabor_quantity);
        laborhrs = (TextView) findViewById(R.id.wplabor_laborhrs);
        ok = (Button) findViewById(R.id.wplabor_ok);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_wplabor_details);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        taskid.setText(wplabor.taskid);
        craft.setText(wplabor.craft);
        quantity.setText(wplabor.quantity);
        laborhrs.setText(wplabor.laborhrs);

//        mBasIn = new BounceTopEnter();
//        mBasOut = new SlideBottomExit();
//        addTaskData();
//        taskid.setOnClickListener(taskidlayoutOnClickListener);
        craft.setOnClickListener(new LayoutOnClickListener(Constants.CRAFTRATE));
        ok.setOnClickListener(okOnClickListener);

    }

//    private View.OnClickListener taskidlayoutOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            NormalListDialog();
//        }
//    };
//
//    private void NormalListDialog() {
//        final NormalListDialog dialog = new NormalListDialog(WplaborDetailsActivity.this, mMenuItems);
//        dialog.title("请选择")//
//                .showAnim(mBasIn)//
//                .dismissAnim(mBasOut)//
//                .show();
//        dialog.setOnOperItemClickL(new OnOperItemClickL() {
//            @Override
//            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                taskid.setText(mMenuItems.get(position).mOperName);
//                dialog.dismiss();
//            }
//        });
//    }

    private View.OnClickListener okOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            if (!wplabor.craft.equals(craft.getText().toString())
                    || !wplabor.quantity.equals(quantity.getText().toString())
                    || !wplabor.laborhrs.equals(laborhrs.getText().toString())) {
                wplabor = new Wplabor();
//                wplabor.taskid = taskid.getText().toString();
                wplabor.craft = craft.getText().toString();
                wplabor.quantity = quantity.getText().toString();
                wplabor.laborhrs = laborhrs.getText().toString();
                wplabor.type = "update";
            }
            intent.putExtra("wplabor", wplabor);
            intent.putExtra("position", position);
            WplaborDetailsActivity.this.setResult(5, intent);
            finish();
        }
    };

    private class LayoutOnClickListener implements View.OnClickListener {
        int requestCode;

        private LayoutOnClickListener(int requestCode) {
            this.requestCode = requestCode;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(WplaborDetailsActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", requestCode);
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        switch (resultCode) {
            case Constants.CRAFTRATE:
                option = (Option) data.getSerializableExtra("option");
                craft.setText(option.getName());
                break;
        }
    }

//    /**
//     * 添加任务数据*
//     */
//    private void addTaskData() {
//        if (woactivityList != null && woactivityList.size() != 0) {
//            for (int i = 0; i < woactivityList.size(); i++) {
//                mMenuItems.add(new DialogMenuItem(woactivityList.get(i).getTaskid(), 0));
//            }
//        }
//    }
}
