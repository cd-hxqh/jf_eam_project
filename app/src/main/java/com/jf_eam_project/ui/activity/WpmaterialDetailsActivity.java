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
import android.widget.RelativeLayout;
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
import com.jf_eam_project.model.Option;
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.model.Wpmaterial;
import com.jf_eam_project.ui.widget.CumTimePickerDialog;
import com.jf_eam_project.utils.GetNowTime;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by think on 2015/12/7.
 * 计划物料详情页面
 */
public class WpmaterialDetailsActivity extends BaseActivity{
    private Wpmaterial wpmaterial;

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

//    private TextView taskid;//任务
    private TextView itemnum;//项目  项目/物料
    private EditText itemqty;//数量
    private TextView location;//库房
    private TextView storelocsite;//库房地点
    private TextView requestby;//请求者
    private TextView requiredate;//要求日期
    private Button ok;//确认
    private Button delete;//删除

//    private ArrayList<Woactivity> woactivityList = new ArrayList<>();
    private int position;
//    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
//    private BaseAnimatorSet mBasIn;
//    private BaseAnimatorSet mBasOut;

    private DatePickerDialog datePickerDialog;
    private CumTimePickerDialog timePickerDialog;
    StringBuffer sb;
    private int layoutnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wpmaterial_details);

        geiIntentData();
        findViewById();
        initView();
    }

    /**
     * 获取数据*
     */
    private void geiIntentData() {
        wpmaterial = (Wpmaterial) getIntent().getSerializableExtra("wpmaterial");
//        woactivityList = (ArrayList<Woactivity>) getIntent().getSerializableExtra("woactivityList");
        position = getIntent().getIntExtra("position",0);
    }
    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        editImageView = (ImageView) findViewById(R.id.title_add);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

//        taskid = (TextView) findViewById(R.id.wpmaterial_taskid);
        itemnum = (TextView) findViewById(R.id.wpmaterial_itemnum);
        itemqty = (EditText) findViewById(R.id.wpmaterial_itemqty);
        location = (TextView) findViewById(R.id.wpmaterial_location);
        storelocsite = (TextView) findViewById(R.id.wpmaterial_storelocsite);
        requestby = (TextView) findViewById(R.id.wpmaterial_requestby);
        requiredate = (TextView) findViewById(R.id.wpmaterial_requiredate);

        ok = (Button) findViewById(R.id.wpmaterial_ok);
        delete = (Button) findViewById(R.id.wpmaterial_delete);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_wpmaterial_details);
        editImageView.setVisibility(View.VISIBLE);
        editImageView.setImageResource(R.drawable.edit_query);
        editImageView.setOnClickListener(editImageViewOnClickListener);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        taskid.setText(wpmaterial.taskid);
        itemnum.setText(wpmaterial.itemnum);
        itemqty.setText(wpmaterial.itemqty);
        location.setText(wpmaterial.location);
        storelocsite.setText(wpmaterial.storelocsite);
        requestby.setText(wpmaterial.requestby);
        requiredate.setText(wpmaterial.requiredate);

        itemnum.setEnabled(false);
        itemqty.setFocusable(false);
        itemqty.setFocusableInTouchMode(false);
        location.setEnabled(false);
        storelocsite.setEnabled(false);

//        mBasIn = new BounceTopEnter();
//        mBasOut = new SlideBottomExit();
//        addTaskData();
//        taskid.setOnClickListener(taskidlayoutOnClickListener);
        itemnum.setOnClickListener(new LayoutOnClickListener(Constants.ITEM));
        location.setOnClickListener(new LayoutOnClickListener(Constants.LOCATIONSCODE));
        setDataListener();
        requiredate.setOnClickListener(new MydateListener());

        ok.setOnClickListener(okOnClickListener);
        ok.setVisibility(View.GONE);
        delete.setOnClickListener(deleteOnClickListener);
    }

    private View.OnClickListener editImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ok.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
            statusEdit();
        }
    };

    /**
     * 设置状态编辑*
     */
    private void statusEdit() {
        itemnum.setEnabled(true);
        itemqty.setFocusable(true);
        itemqty.setFocusableInTouchMode(true);
        location.setEnabled(true);
        storelocsite.setEnabled(true);
    }

//    private View.OnClickListener taskidlayoutOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            NormalListDialog();
//        }
//    };
//
//    private void NormalListDialog() {
//        final NormalListDialog dialog = new NormalListDialog(WpmaterialDetailsActivity.this, mMenuItems);
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

    private class LayoutOnClickListener implements View.OnClickListener {
        int requestCode;

        private LayoutOnClickListener(int requestCode) {
            this.requestCode = requestCode;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(WpmaterialDetailsActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", requestCode);
            startActivityForResult(intent, requestCode);
        }
    }

    private View.OnClickListener okOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (itemnum.getText().toString().equals("")) {
                Toast.makeText(WpmaterialDetailsActivity.this, "请输入项目", Toast.LENGTH_SHORT).show();
            } else if (itemqty.getText().toString().equals("")) {
                Toast.makeText(WpmaterialDetailsActivity.this,"请输入数量",Toast.LENGTH_SHORT).show();
            } else if(location.getText().toString().equals("")){
                Toast.makeText(WpmaterialDetailsActivity.this,"请输入库房",Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = getIntent();
                if (!wpmaterial.itemnum.equals(itemnum.getText().toString())
                        || !wpmaterial.itemqty.equals(itemqty.getText().toString())
                        || !wpmaterial.location.equals(location.getText().toString())
                        || !wpmaterial.storelocsite.equals(storelocsite.getText().toString())
                        || !wpmaterial.requestby.equals(requestby.getText().toString())
                        || !wpmaterial.requiredate.equals(requiredate.getText().toString())) {
//                wpmaterial.taskid = taskid.getText().toString();
                    wpmaterial.itemnum = itemnum.getText().toString();
                    wpmaterial.itemqty = itemqty.getText().toString();
                    wpmaterial.location = location.getText().toString();
                    wpmaterial.storelocsite = storelocsite.getText().toString();
                    wpmaterial.requestby = requestby.getText().toString();
                    wpmaterial.requiredate = requiredate.getText().toString();
                    if (wpmaterial.type == null || !wpmaterial.type.equals("add")) {
                        wpmaterial.type = "update";
                        Toast.makeText(WpmaterialDetailsActivity.this, "计划物料本地修改成功", Toast.LENGTH_SHORT).show();
                    }
                }
                intent.putExtra("wpmaterial", wpmaterial);
                intent.putExtra("position", position);
                WpmaterialDetailsActivity.this.setResult(6, intent);
                finish();
            }
        }
    };

    private View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            if(wpmaterial.type!=null&&wpmaterial.type.equals("add")){//本地新增任务
                intent.putExtra("position",position);
                WpmaterialDetailsActivity.this.setResult(11, intent);
                Toast.makeText(WpmaterialDetailsActivity.this, "计划物料删除成功", Toast.LENGTH_SHORT).show();
                finish();
            }else if (wpmaterial.type==null){//服务器接收的任务
                wpmaterial.type = "delete";
                intent.putExtra("wpmaterial", wpmaterial);
                intent.putExtra("position",position);
                WpmaterialDetailsActivity.this.setResult(12, intent);
                Toast.makeText(WpmaterialDetailsActivity.this, "计划物料本地删除成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    };

    /**
     * 设置时间选择器*
     */
    private void setDataListener() {

        final Calendar objTime = Calendar.getInstance();
        int iYear = objTime.get(Calendar.YEAR);
        int iMonth = objTime.get(Calendar.MONTH);
        int iDay = objTime.get(Calendar.DAY_OF_MONTH);
        int hour = objTime.get(Calendar.HOUR_OF_DAY);

        int minute = objTime.get(Calendar.MINUTE);


        datePickerDialog = new DatePickerDialog(this, new datelistener(), iYear, iMonth, iDay);
        timePickerDialog = new CumTimePickerDialog(this, new timelistener(), hour, minute, true);
    }

    private class MydateListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            layoutnum = 0;
            sb = new StringBuffer();
            layoutnum = view.getId();
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
            timePickerDialog.show();
        }
    }

    private class timelistener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            sb.append(" ");
            if (i1 < 10) {
                sb.append(i + ":" + "0" + i1 + ":00");
            } else {
                sb.append(i + ":" + i1 + ":00");
            }
            if (layoutnum == requiredate.getId()) {
                requiredate.setText(sb);
            }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        switch (resultCode) {
            case Constants.ITEM:
                option = (Option) data.getSerializableExtra("option");
                itemnum.setText(option.getName());
                break;
            case Constants.LOCATIONSCODE:
                option = (Option) data.getSerializableExtra("option");
                location.setText(option.getName());
                break;
        }
    }
}
