package com.jf_eam_project.ui.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
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
import com.jf_eam_project.model.Udinspo;
import com.jf_eam_project.utils.MessageUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 巡检单详情
 */
public class Udinspo_Details_activity extends BaseActivity {

    private static final String TAG = "Udinspo_Details_activity";

    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;

    /**
     * 编辑*
     */
    private ImageView editImageView;
    /**
     * 菜单*
     */
    private ImageView menuImageView;


    /**
     * 界面信息显示*
     */
    private TextView insponumText; //巡检单
    private TextView descriptionText; //描述
    private TextView inspplannumText; //计划编号
    private TextView inspotypeText; //巡检单类型
    private TextView createbyText; //创建人
    private TextView createdateText; //创建时间
    private TextView inspobyText; //巡检人
    private TextView inspodateText; //巡检日期
    private TextView lastrundateText; //本次生成日期
    private TextView nextrundateText; //下次运行时间

    private Button confirmBtn; //确认


    private Udinspo udinspo;


    private PopupWindow popupWindow;


    private TextView udinspoasset; //设备部件


    private ProgressDialog mProgressDialog;


    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
    private String[] inspotypeTexts;
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    private String inspotype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udinspo_details);
        initData();
        findViewById();
        initView();

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        addInspotypeData();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        udinspo = (Udinspo) getIntent().getSerializableExtra("udinspo");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        editImageView = (ImageView) findViewById(R.id.title_edit);

        insponumText = (TextView) findViewById(R.id.udinspo_insponum_text);
        descriptionText = (TextView) findViewById(R.id.udinspo_description_text);
        inspplannumText = (TextView) findViewById(R.id.udinspo_inspplannum_text);
        inspotypeText = (TextView) findViewById(R.id.udinspo_inspotype_text);
        createbyText = (TextView) findViewById(R.id.udinspo_createby_text);
        createdateText = (TextView) findViewById(R.id.udinspo_createdate_text);
        inspobyText = (TextView) findViewById(R.id.udinspo_inspoby_text);
        inspodateText = (TextView) findViewById(R.id.udinspo_inspodate_text);
        lastrundateText = (TextView) findViewById(R.id.udinspo_lastrundate_text);
        nextrundateText = (TextView) findViewById(R.id.udinspo_nextrundate_text);


        confirmBtn = (Button) findViewById(R.id.submit_btn_id);

    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.udinspo_detail_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);

        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        editImageView.setVisibility(View.VISIBLE);
        editImageView.setOnClickListener(editImageViewOnClickListener);

        if (udinspo != null) {
            insponumText.setText(udinspo.getInsponum() == null ? "" : udinspo.getInsponum());
            descriptionText.setText(udinspo.getDescription() == null ? "" : udinspo.getDescription());
            inspplannumText.setText(udinspo.getInspplannum() == null ? "" : udinspo.getInspplannum());
            inspotypeText.setText(udinspo.getInspotype() == null ? "" : udinspo.getInspotype());
            createbyText.setText(udinspo.getCreateby() == null ? "" : udinspo.getCreateby());
            createdateText.setText(udinspo.getCreatedate() == null ? "" : udinspo.getCreatedate());
            inspobyText.setText(udinspo.getInspoby() == null ? "" : udinspo.getInspoby());
            inspodateText.setText(udinspo.getInspodate() == null ? "" : udinspo.getInspodate());
            lastrundateText.setText(udinspo.getLastrundate() == null ? "" : udinspo.getLastrundate());
            nextrundateText.setText(udinspo.getNextrundate() == null ? "" : udinspo.getNextrundate());
        }

        inspotypeText.setOnClickListener(inspotypeOnClickListener);





        confirmBtn.setOnClickListener(confirmBtnOnClickListener);
    }


    /**
     * 添加巡检数据*
     */
    private void addInspotypeData() {
        String[] inspotypes = getResources().getStringArray(R.array.inspotype_tab_titles);
        inspotypeTexts = getResources().getStringArray(R.array.inspotype_tab_text);

        for (int i = 0; i < inspotypes.length; i++)
            mMenuItems.add(new DialogMenuItem(inspotypes[i], 0));


    }

    private void NormalListDialog() {
        final NormalListDialog dialog = new NormalListDialog(Udinspo_Details_activity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                inspotypeText.setText(mMenuItems.get(position).mOperName);

                inspotype = inspotypeTexts[position];
                dialog.dismiss();
            }
        });
    }



    /**类型选择**/
    private View.OnClickListener inspotypeOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NormalListDialog();
        }
    };









    private View.OnClickListener editImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            confirmBtn.setVisibility(View.VISIBLE);
        }
    };


    /**
     * 返回按钮*
     */
    private View.OnClickListener backImageViewOnClickListenrer = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    /**
     * 菜单按钮*
     */
    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupWindow(menuImageView);
        }
    };

    /**
     * 显示PopupWindow*
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void showPopupWindow(View view) {

        View contentView = LayoutInflater.from(Udinspo_Details_activity.this).inflate(
                R.layout.udinspo_popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.abc_popup_background_mtrl_mult));
        popupWindow.showAsDropDown(view, 0, 20);

        udinspoasset = (TextView) contentView.findViewById(R.id.udinspoasset_text_id);
        udinspoasset.setOnClickListener(udinspoassetOnClickListener);

    }


    private View.OnClickListener udinspoassetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Udinspo_Details_activity.this, Udinspoasset_Activity.class);
            intent.putExtra("insponum", udinspo.insponum);
            startActivityForResult(intent, 0);
            popupWindow.dismiss();
        }
    };


    private View.OnClickListener confirmBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            encapsulationData();
        }
    };


    /**
     * 数据封装*
     */
    private void encapsulationData() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Udinspo_Details_activity.this);
        builder.setMessage("确定更新巡检单吗？").setTitle("提示")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                mProgressDialog = ProgressDialog.show(Udinspo_Details_activity.this, null,
                        getString(R.string.inputing), true, true);
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setCancelable(false);
                new AsyncTask<String, String, String>() {
                    @Override
                    protected String doInBackground(String... strings) {
                        String data = submitData();

                        String result = getBaseApplication().getWsService().UpdatePO(data);

                        return result;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        mProgressDialog.dismiss();


                        Log.i(TAG, "s=" + s);

                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            String insponum = jsonObject.getString("INSPONUM");
                            String success = jsonObject.getString("status");
                            String errorNo = jsonObject.getString("errorNo");

                            if (success.equals("数据更新成功") && errorNo.equals("0")) {
                                MessageUtils.showMiddleToast(Udinspo_Details_activity.this, "数据更新成功");
                            } else {
                                MessageUtils.showMiddleToast(Udinspo_Details_activity.this, "数据更新失败");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }.execute();
            }
        }).create().show();


    }


    /**
     * 封装数据*
     */
    private String submitData() {


        String insponum = udinspo.insponum;
        String description = descriptionText.getText().toString();
        String inspplannum = inspplannumText.getText().toString();
        String inspotype = inspotypeText.getText().toString();
        String createby = createbyText.getText().toString();
        String createdate = createdateText.getText().toString();
        String inspoby = inspobyText.getText().toString();
        String inspodate = inspodateText.getText().toString();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("INSPONUM", insponum);
            if (!description.equals(udinspo.description)) {
                jsonObject.put("DESCRIPTION", description);
            }
            if (!inspplannum.equals(udinspo.inspplannum)) {
                jsonObject.put("INSPPLANNUM", inspplannum);
            }
            if (!inspotype.equals(udinspo.inspotype)) {
                jsonObject.put("INSPOTYPE", inspotype);
            }
            if (!createby.equals(udinspo.createby)) {
                jsonObject.put("CREATEBY", createby);
            }
            if (!createdate.equals(udinspo.createdate)) {
                jsonObject.put("CREATEDATE", createdate);
            }
            if (!inspoby.equals(udinspo.inspoby)) {
                jsonObject.put("INSPOBY", inspoby);
            }
            if (!inspodate.equals(udinspo.inspodate)) {
                jsonObject.put("INSPODATE", inspodate);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }




}
