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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnBtnEditClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalEditTextDialog;
import com.jf_eam_project.Dao.UdinspoAssetDao;
import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Option;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.model.Udinspojxxm;
import com.jf_eam_project.utils.MessageUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * 设备备件新增
 */
public class AddUdinspoAssetActivity extends BaseActivity {

    private static final String TAG = "AddUdinspoAssetActivity";

    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;


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
    private EditText childassetnumText; //设备部件


    private Udinspoasset udinspoasset = null; //设备备件


    private PopupWindow popupWindow;

    private TextView udinspojxxm; //检修项目标准


    /**
     * 保存按钮*
     */
    private Button submitBtn;
    /**
     * 序号*
     */
    private int linenum;

    /**
     * 巡检单号*
     */
    private String insponum;
    /**
     * udinspoassetnum*
     */
    private String udinspoassetnum;


    ArrayList<Udinspojxxm> udinspojxxms = new ArrayList<Udinspojxxm>();


    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;


    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udinspoasset_add);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        udinspoasset = (Udinspoasset) getIntent().getSerializableExtra("Udinspoasset");
        linenum = getIntent().getIntExtra("udinspoassetlinenum", 0);
        insponum = getIntent().getExtras().getString("insponum");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        menuImageView = (ImageView) findViewById(R.id.title_add);

        udinspoassetlinenumText = (TextView) findViewById(R.id.udinspoasset_udinspoassetlinenum_text);
        udinspoassetnumText = (TextView) findViewById(R.id.ud_udinspoassetnum_text);
        locationText = (TextView) findViewById(R.id.udinspoasset_location_text);
        locationDescText = (TextView) findViewById(R.id.udinspoasset_location_description_text);
        assetnumText = (TextView) findViewById(R.id.udinspoasset_assetnum_text);
        assetnumDescText = (TextView) findViewById(R.id.udinspoasset_assetnum_desc_text);
        childassetnumText = (EditText) findViewById(R.id.udinspoasset_childassetnum_text);

        submitBtn = (Button) findViewById(R.id.submit_btn_id);

    }


    @Override
    protected void initView() {
        titleView.setText(getResources().getString(R.string.add_udinspoasset_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);

        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);

        udinspoassetlinenumText.setText(linenum == 0 ? "1" : (linenum + 1) + "");
        udinspoassetnumText.setText("SC" + getRandomNumber(4));

        locationText.setOnClickListener(locationOnClickListener);
        assetnumText.setOnClickListener(assetnumOnClickListener);


        submitBtn.setOnClickListener(submitBtnOnClickListener);

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();

    }

    /**
     * 位置选择*
     */
    private View.OnClickListener locationOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AddUdinspoAssetActivity.this, OptionActivity.class);
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
            Intent intent = new Intent(AddUdinspoAssetActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", Constants.ASSETCODE);
            startActivityForResult(intent, Constants.ASSETCODE);
        }
    };


    public static int getRandomNumber(int n) {
        int temp = 0;
        int min = (int) Math.pow(10, n - 1);
        int max = (int) Math.pow(10, n);
        Random rand = new Random();

        while (true) {
            temp = rand.nextInt(max);
            if (temp >= min)
                break;
        }

        return temp;
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

        View contentView = LayoutInflater.from(AddUdinspoAssetActivity.this).inflate(
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
            Intent intent = new Intent(AddUdinspoAssetActivity.this, Add_Udinspojxxm_Activity.class);
            intent.putExtra("udinspoassetnum", udinspoassetnumText.getText().toString());
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
            case 1:
                udinspojxxms = (ArrayList<Udinspojxxm>) data.getSerializableExtra("udinspojxxms");
                break;
            default:
                break;
        }
    }

    /**
     * 保存方法*
     */
    private View.OnClickListener submitBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (insponum.equals("")) {
                Udinspoasset udinspoasset = addUdinspoInfo();
                Intent intent = getIntent();
                intent.putExtra("udinspoasset", udinspoasset);
                setResult(1, intent);
                finish();
            } else {
                submitDataInfo();
            }


        }
    };


    /**
     * 提交数据*
     */
    private void submitDataInfo() {

        final NormalDialog dialog = new NormalDialog(AddUdinspoAssetActivity.this);
        dialog.content("确定新增巡检备件吗？")//
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
                        showProgressDialog("数据提交中...");
                        startAsyncTask();
                        dialog.dismiss();
                    }
                });
    }

    /**
     * 提交数据*
     */
    private void startAsyncTask() {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String data = null;
                try {
                    data = submitBtn();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String result = getBaseApplication().getWsService().UpdatePO(data, insponum);

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
                    MessageUtils.showMiddleToast(AddUdinspoAssetActivity.this, success);
                    setResult(Constants.REFRESH);
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
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
        String udinspoassetlinenum = udinspoassetlinenumText.getText().toString();
        String udinspoassetnum = udinspoassetnumText.getText().toString();
        String assetnum = assetnumText.getText().toString();
        String childassetnum = childassetnumText.getText().toString();

        JSONObject json = new JSONObject();
        json.put("INSPONUM", insponum);
        json.put("UDINSPOASSETNUM", udinspoassetnum);
        json.put("UDINSPOASSETLINENUM", udinspoassetlinenum);
        json.put("TYPE", Constants.ADD);
        if (!location.equals("")) {
            json.put("LOCATION", location);
        }
        if (!assetnum.equals("")) {
            json.put("ASSETNUM", assetnum);
        }
        if (!childassetnum.equals("")) {
            json.put("CHILDASSETNUM", childassetnum);
        }


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CHILDREN", jsonUdinPoAssetJson(json.toString()));


        return jsonObject.toString();
    }


    /**
     * 封装udinspoAsset信息*
     */
    private JSONArray jsonUdinPoAssetJson(String str) {
        JSONArray jsonArray = null;

        String json3 = "";
        try {
            json3 = "[" + str + "]";

            Log.i(TAG, "json3=" + json3);
            jsonArray = new JSONArray(json3);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return jsonArray;
    }


    /**
     * 将数据保存至本地数据库*
     */

    private Udinspoasset addUdinspoInfo() {
        Udinspoasset udinspoasset = new Udinspoasset();

        udinspoasset.setUdinspoassetlinenum(udinspoassetlinenumText.getText().toString());
        udinspoasset.setUdinspoassetnum(udinspoassetnumText.getText().toString());
        udinspoasset.setLocation(locationText.getText().toString());
        udinspoasset.setLocationsdesc(locationDescText.getText().toString());
        udinspoasset.setAssetnum(assetnumText.getText().toString());
        udinspoasset.setAssetdesc(assetnumDescText.getText().toString());
        udinspoasset.setChildassetnum(childassetnumText.getText().toString());

        jsonUdinPoAssetInfo(udinspojxxms);
        new UdinspoAssetDao(AddUdinspoAssetActivity.this).insert(udinspoasset);

        return udinspoasset;
    }


    /**
     * 封装Udinspojxxm信息*
     */
    private JSONArray jsonUdinPoAssetInfo(ArrayList<Udinspojxxm> udinspoassets) {

        ObjectMapper mapper = new ObjectMapper();
        JSONArray jsonArray = null;

        String json2 = "";
        try {
            json2 = mapper.writeValueAsString(udinspoassets);
            jsonArray = new JSONArray(json2);
            Log.i(TAG, json2);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return jsonArray;
    }


}
