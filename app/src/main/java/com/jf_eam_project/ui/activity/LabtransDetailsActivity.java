package com.jf_eam_project.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Labtrans;
import com.jf_eam_project.model.Option;
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.model.Wplabor;
import com.jf_eam_project.ui.widget.CumTimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by think on 2015/12/22.
 * 实际员工详情页面
 */
public class LabtransDetailsActivity extends BaseActivity {
    private Labtrans labtrans;
    private int position;

    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 编辑*
     */
    private ImageView editImageView;
    /**
     * 返回*
     */
    private ImageView backImageView;
    //员工，开始日期，常规时数，工种，费率，类型
//    private TextView taskid;//任务
    private TextView laborcode;//员工
    private TextView startdate;//开始日期
    private EditText regularhrs;//常规时数
    private TextView craft;//工种
    //    private TextView payrate;//费率
    private Button ok;//确定
    private Button delete;//删除

//    private ArrayList<Woactivity> woactivityList = new ArrayList<>();
//    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
//    private BaseAnimatorSet mBasIn;
//    private BaseAnimatorSet mBasOut;

    private DatePickerDialog datePickerDialog;
    StringBuffer sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtrans_details);

        geiIntentData();
        findViewById();
        initView();
    }

    /**
     * 获取数据*
     */
    private void geiIntentData() {
//        woactivityList = (ArrayList<Woactivity>) getIntent().getSerializableExtra("woactivityList");
        labtrans = (Labtrans) getIntent().getSerializableExtra("labtrans");
        position = getIntent().getIntExtra("position", 0);
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        editImageView = (ImageView) findViewById(R.id.title_add);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

//        taskid = (TextView) findViewById(R.id.labtrans_taskid);
        laborcode = (TextView) findViewById(R.id.labtrans_laborcode);
        craft = (TextView) findViewById(R.id.labtrans_craft);
//        payrate = (TextView) findViewById(R.id.labtrans_payrate);
        startdate = (TextView) findViewById(R.id.labtrans_startdate);
        regularhrs = (EditText) findViewById(R.id.labtrans_regularhrs);
        ok = (Button) findViewById(R.id.labtrans_ok);
        delete = (Button) findViewById(R.id.labtrans_delete);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_wplabor_details);
        editImageView.setVisibility(View.VISIBLE);
        editImageView.setImageResource(R.drawable.edit_query);
        editImageView.setOnClickListener(editImageViewOnClickListener);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        laborcode.setText(labtrans.laborcode);
        craft.setText(labtrans.craft);
        startdate.setText(labtrans.startdate);
        regularhrs.setText(labtrans.regularhrs);

        laborcode.setEnabled(false);
        craft.setEnabled(false);
        startdate.setEnabled(false);
        regularhrs.setFocusable(false);
        regularhrs.setFocusableInTouchMode(false);

        setDataListener();
        laborcode.setOnClickListener(new LayoutOnClickListener(Constants.LABORCRAFTRATE));
        craft.setOnClickListener(new LayoutOnClickListener(Constants.CRAFTRATE));
        startdate.setOnClickListener(new MydateListener());
        ok.setVisibility(View.GONE);
        ok.setOnClickListener(okOnClickListener);
        delete.setOnClickListener(deleteOnClickListener);

        if (labtrans.labtransid != null && !labtrans.labtransid.equals("")) {
            editImageView.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener editImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ok.setVisibility(View.VISIBLE);
            statusEdit();
        }
    };

    /**
     * 设置状态编辑*
     */
    private void statusEdit() {
        laborcode.setEnabled(true);
        craft.setEnabled(true);
        startdate.setEnabled(true);
        regularhrs.setFocusable(true);
        regularhrs.setFocusableInTouchMode(true);
    }

    private View.OnClickListener okOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            if (!labtrans.laborcode.equals(laborcode.getText().toString())
                    || !labtrans.craft.equals(craft.getText().toString())
                    || !labtrans.startdate.equals(startdate.getText().toString())
                    || !labtrans.regularhrs.equals(regularhrs.getText().toString())) {
//                assignment.taskid = taskid.getText().toString();
                labtrans.laborcode = laborcode.getText().toString();
                labtrans.craft = craft.getText().toString();
                labtrans.startdate = startdate.getText().toString();
                labtrans.regularhrs = regularhrs.getText().toString();
            }
            intent.putExtra("labtrans", labtrans);
            intent.putExtra("position", position);
            LabtransDetailsActivity.this.setResult(2, intent);
            Toast.makeText(LabtransDetailsActivity.this, "实际员工修改成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    };

    private View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            if(labtrans.type!=null&&labtrans.type.equals("add")){//本地新增任务
                intent.putExtra("position",position);
                LabtransDetailsActivity.this.setResult(3, intent);
                Toast.makeText(LabtransDetailsActivity.this, "实际员工删除成功", Toast.LENGTH_SHORT).show();
                finish();
            }else if (labtrans.type==null){//服务器接收的任务
                Toast.makeText(LabtransDetailsActivity.this, "已有实际员工不能删除!", Toast.LENGTH_SHORT).show();
            }
        }
    };

//    private View.OnClickListener taskidlayoutOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            NormalListDialog();
//        }
//    };
//
//    private void NormalListDialog() {
//        final NormalListDialog dialog = new NormalListDialog(LabtransDetailsActivity.this, mMenuItems);
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

    /**
     * 设置时间选择器*
     */
    private void setDataListener() {

        final Calendar objTime = Calendar.getInstance();
        int iYear = objTime.get(Calendar.YEAR);
        int iMonth = objTime.get(Calendar.MONTH);
        int iDay = objTime.get(Calendar.DAY_OF_MONTH);


        datePickerDialog = new DatePickerDialog(this, new datelistener(), iYear, iMonth, iDay);
    }

    private class MydateListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            sb = new StringBuffer();
            datePickerDialog.show();
        }
    }

    private class datelistener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            sb = new StringBuffer();
            monthOfYear = monthOfYear + 1;
            if (dayOfMonth < 10) {
                sb.append(year + "-" + monthOfYear + "-" + "0" + dayOfMonth);
            } else {
                sb.append(year + "-" + monthOfYear + "-" + dayOfMonth);
            }
            startdate.setText(sb);
        }
    }


    private class LayoutOnClickListener implements View.OnClickListener {
        int requestCode;

        private LayoutOnClickListener(int requestCode) {
            this.requestCode = requestCode;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LabtransDetailsActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", requestCode);
            if (requestCode == Constants.LABORCRAFTRATE) {
                intent.putExtra("craft", craft.getText().toString());
            }
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        switch (resultCode) {
            case Constants.LABORCRAFTRATE:
                option = (Option) data.getSerializableExtra("option");
                laborcode.setText(option.getName());
                craft.setText(option.getDescription());
                break;
            case Constants.CRAFTRATE:
                option = (Option) data.getSerializableExtra("option");
                craft.setText(option.getName());
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
