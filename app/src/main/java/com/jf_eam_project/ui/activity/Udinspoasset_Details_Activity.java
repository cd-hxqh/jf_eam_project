package com.jf_eam_project.ui.activity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Option;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.ui.adapter.GridAdapter;
import com.jf_eam_project.utils.MessageUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 设备备件详情
 */
public class Udinspoasset_Details_Activity extends BaseActivity {

    private static final String TAG = "Udinspoasset_Details_Activity";

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
    private TextView udinspoassetlinenumText; //序号
    private TextView udinspoassetnumText; //设备编号
    private TextView locationText; //位置
    private TextView locationDescText; //位置描述
    private TextView assetnumText; //设备
    private TextView assetnumDescText; //设备描述
    private TextView childassetnumText; //设备部件




    private Udinspoasset udinspoasset; //设备备件


    private PopupWindow popupWindow;

    private TextView udinspojxxm; //检修项目标准

    /**
     * 保存按钮*
     */
    private Button submitBtn;
    /**
     * 删除*
     */
    private Button deleteBtn;

    /**
     * 判断修改与删除标识*
     */
    private int mark;


    private ProgressDialog mProgressDialog;


    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udinspoasset_details);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        udinspoasset = (Udinspoasset) getIntent().getSerializableExtra("Udinspoasset");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        editImageView = (ImageView) findViewById(R.id.title_edit);

        udinspoassetlinenumText = (TextView) findViewById(R.id.udinspoasset_udinspoassetlinenum_text);
        udinspoassetnumText = (TextView) findViewById(R.id.ud_udinspoassetnum_text);
        locationText = (TextView) findViewById(R.id.udinspoasset_location_text);
        locationDescText = (TextView) findViewById(R.id.udinspoasset_location_description_text);
        assetnumText = (TextView) findViewById(R.id.udinspoasset_assetnum_text);
        assetnumDescText = (TextView) findViewById(R.id.udinspoasset_assetnum_desc_text);
        childassetnumText = (TextView) findViewById(R.id.udinspoasset_childassetnum_text);


        submitBtn = (Button) findViewById(R.id.submit_btn_id);
        deleteBtn = (Button) findViewById(R.id.delete_btn_id);



    }


    @Override
    protected void initView() {
        titleView.setText(getResources().getString(R.string.udinspoasset_detail_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);
//        editImageView.setVisibility(View.VISIBLE);
        editImageView.setOnClickListener(editImageViewOnClickListener);


        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);


        if (udinspoasset != null) {
            udinspoassetlinenumText.setText(udinspoasset.getUdinspoassetlinenum() == null ? "" : udinspoasset.getUdinspoassetlinenum());
            udinspoassetnumText.setText(udinspoasset.getUdinspoassetnum() == null ? "" : udinspoasset.getUdinspoassetnum());
            locationText.setText(udinspoasset.location == null ? "" : udinspoasset.location);
            locationDescText.setText(udinspoasset.getLocationsdesc() == null ? "" : udinspoasset.getLocationsdesc());
            assetnumText.setText(udinspoasset.assetnum == null ? "" : udinspoasset.assetnum);
            assetnumDescText.setText(udinspoasset.getAssetdesc() == null ? "" : udinspoasset.getAssetdesc());
            childassetnumText.setText(udinspoasset.getChildassetnum() == null ? "" : udinspoasset.getChildassetnum());
        }

        childassetnumText.setFocusable(false);
        childassetnumText.setFocusableInTouchMode(false);


        locationText.setOnClickListener(locationOnClickListener);
        assetnumText.setOnClickListener(assetnumOnClickListener);

        submitBtn.setOnClickListener(submitBtnOnClickListener);
        deleteBtn.setOnClickListener(deleteBtnOnClickListener);


        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();



    }


    private View.OnClickListener editImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            submitBtn.setVisibility(View.VISIBLE);
            deleteBtn.setVisibility(View.VISIBLE);
            //设置编辑状态
            statusEdit();
        }
    };


    /**
     * 设置编辑状态*
     */
    private void statusEdit() {
        childassetnumText.setFocusable(true);
        childassetnumText.setFocusableInTouchMode(true);
        locationText.setClickable(true);
        assetnumText.setClickable(true);
    }

    /**
     * 保存按钮*
     */
    private View.OnClickListener submitBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mark = Constants.MODIFICATION_MARK;
            encapsulationData("确定更新巡检备件吗？", "数据提交中...");
        }
    };

    /**
     * 删除按钮*
     */
    private View.OnClickListener deleteBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mark = Constants.DELETE_MARK;
            encapsulationData("确定删除巡检备件吗？", "数据删除中...");
        }
    };

    /**
     * 数据封装*
     */
    private void encapsulationData(String message, final String progress) {

        final NormalDialog dialog = new NormalDialog(Udinspoasset_Details_Activity.this);
        dialog.content(message)//
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
                        showProgressDialog(progress);
                        startAsyncTask();
                        dialog.dismiss();
                    }
                });


    }

    private void startAsyncTask() {


        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String data = null;
                try {
                    if (mark == Constants.MODIFICATION_MARK) {
                        data = submitBtn();
                    } else if (mark == Constants.DELETE_MARK) {
                        data = deleteBtn();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String result = getBaseApplication().getWsService().UpdatePO(Udinspoasset_Details_Activity.this,data, udinspoasset.udinspoassetnum);

                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeProgressDialog();

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String success = jsonObject.getString("status");
                    String errorNo = jsonObject.getString("errorNo");
                    if (success.equals("数据更新成功") && errorNo.equals("0")) {
                        MessageUtils.showMiddleToast(Udinspoasset_Details_Activity.this, "数据操作成功");
                        setResult(Constants.REFRESH);
                        finish();
                    } else {
                        MessageUtils.showMiddleToast(Udinspoasset_Details_Activity.this, "数据操作失败");
                        setResult(Constants.REFRESH);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    MessageUtils.showMiddleToast(Udinspoasset_Details_Activity.this, "数据操作失败");
                    setResult(Constants.REFRESH);
                    finish();
                }


            }
        }.execute();


    }


    /**
     * 保存数据*
     */
    private String submitBtn() throws JSONException {
        String location = locationText.getText().toString();
        String assetnum = assetnumText.getText().toString();
        String childassetnum = childassetnumText.getText().toString();

        Udinspoasset udinspoasset1 = new Udinspoasset();
        JSONObject json = new JSONObject();
        udinspoasset1.setUdinspoassetnum(udinspoasset.udinspoassetnum);
        json.put("UDINSPOASSETNUM", udinspoasset.udinspoassetnum);
        udinspoasset1.setType(Constants.UPDATE);
        json.put("TYPE", Constants.UPDATE);
        if (!location.equals(udinspoasset.location)) {
            udinspoasset1.setLocation(location);
            json.put("LOCATION", location);
        }
        if (!assetnum.equals(udinspoasset.assetnum)) {
            udinspoasset1.setAssetnum(assetnum);
            json.put("ASSETNUM", assetnum);
        }
        if (!childassetnum.equals(udinspoasset.childassetnum)) {
            udinspoasset1.setChildassetnum(childassetnum);
            json.put("CHILDASSETNUM", childassetnum);
        }


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CHILDREN", jsonUdinPoAssetInfo(json.toString()));


        return jsonObject.toString();
    }

    /**
     * 删除数据*
     */
    private String deleteBtn() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("UDINSPOASSETNUM", udinspoasset.udinspoassetnum);
        json.put("TYPE", Constants.DELETE);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CHILDREN", jsonUdinPoAssetInfo(json.toString()));


        return jsonObject.toString();
    }


    /**
     * 封装udinspoAsset信息*
     */
    private JSONArray jsonUdinPoAssetInfo(String str) {
        JSONArray jsonArray = null;

        String json3 = "";
        try {
            json3 = "[" + str + "]";

            jsonArray = new JSONArray(json3);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return jsonArray;
    }


    /**
     * 位置选择*
     */
    private View.OnClickListener locationOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Udinspoasset_Details_Activity.this, OptionActivity.class);
            intent.putExtra("requestCode", Constants.LOCATIONCODE);
            startActivityForResult(intent, Constants.LOCATIONCODE);
        }
    };

    /**
     * 资产选择*
     */
    private View.OnClickListener assetnumOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Udinspoasset_Details_Activity.this, OptionActivity.class);
            intent.putExtra("requestCode", Constants.ASSETCODE);
            startActivityForResult(intent, Constants.ASSETCODE);
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

        View contentView = LayoutInflater.from(Udinspoasset_Details_Activity.this).inflate(
                R.layout.udinspo_popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.abc_popup_background_mtrl_mult));
        popupWindow.showAsDropDown(view, 0, 20);

        udinspojxxm = (TextView) contentView.findViewById(R.id.udinspoasset_text_id);
        udinspojxxm.setText(getString(R.string.udinspojxxm_title));
        udinspojxxm.setOnClickListener(udinspoassetOnClickListener);

    }


    private View.OnClickListener udinspoassetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            intent.setClass(Udinspoasset_Details_Activity.this, Udinspojxxm_Activity.class);
            intent.putExtra("udinspoassetnum", udinspoasset.udinspoassetnum);
            startActivityForResult(intent, 0);
            popupWindow.dismiss();
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        switch (resultCode) {
            case Constants.ASSETCODE:
                option = (Option) data.getSerializableExtra("option");
                assetnumText.setText(option.getName());
                assetnumDescText.setText(option.getDescription());
                break;
            case Constants.LOCATIONCODE:
                option = (Option) data.getSerializableExtra("option");
                locationText.setText(option.getName());
                locationDescText.setText(option.getDescription());
                break;

            default:
                break;
        }
    }


}
