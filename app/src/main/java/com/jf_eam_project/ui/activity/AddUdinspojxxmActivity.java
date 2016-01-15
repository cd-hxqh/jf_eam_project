package com.jf_eam_project.ui.activity;

import android.annotation.TargetApi;
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

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.jf_eam_project.Dao.UdinspojxxmDao;
import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.model.Udinspojxxm;
import com.jf_eam_project.utils.MessageUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 检修项目标准新建
 */
public class AddUdinspojxxmActivity extends BaseActivity {

    private static final String TAG = "AddUdinspojxxmActivity";

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
    private TextView udinspojxxmlinenumText; //序号
    private EditText descriptionText; //巡检项目标准
    private EditText executionText; //巡检情况描述
    private EditText checkbyText; //巡检人员


    private Udinspojxxm udinspojxxm; //设备备件


    private Button submitBtn; //确认按钮

    /**
     * 序号*
     */
    private int linenum;
    /**
     * 巡检备件*
     */
    private String udinspoassetnum;
    /**
     * 入口*
     */
    private int mark;


    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udinspojxxm_add);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        linenum = getIntent().getIntExtra("udinspoassetlinenum", 0);
        udinspoassetnum = getIntent().getStringExtra("udinspoassetnum");
        mark = getIntent().getIntExtra("mark", 0);
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        udinspojxxmlinenumText = (TextView) findViewById(R.id.udinspoasset_udinspoassetlinenum_text);
        descriptionText = (EditText) findViewById(R.id.udinspojxxm_description_text);
        executionText = (EditText) findViewById(R.id.udinspojxxm_execution_text);
        checkbyText = (EditText) findViewById(R.id.ud_udinspojxxm4_text);


        submitBtn = (Button) findViewById(R.id.submit_btn_id);

    }


    @Override
    protected void initView() {
        titleView.setText(getString(R.string.add_udinspojxxm_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);


        udinspojxxmlinenumText.setText(linenum == 0 ? "1" : (linenum + 1) + "");

        submitBtn.setOnClickListener(submitBtnOnClickListener);


        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();

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
     * 确认按钮*
     */
    private View.OnClickListener submitBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (mark == Constants.ENTRANCE_1) {
                submitDataInfo();
            } else {
                Udinspojxxm udinspojxxm = saveUdinspojxxmInfo();

                Intent intent = getIntent();
                intent.putExtra("udinspojxxm", udinspojxxm);
                setResult(0, intent);
                finish();
            }
        }


    };


    /**
     * 提交数据*
     */

    private void submitDataInfo() {

        final NormalDialog dialog = new NormalDialog(AddUdinspojxxmActivity.this);
        dialog.content("确定新增项目标准吗？")//
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

                String result = getBaseApplication().getWsService().UpdatePO(data, udinspoassetnum);

                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                closeProgressDialog();
                if (s != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        String success = jsonObject.getString("status");
                        String errorNo = jsonObject.getString("errorNo");
                        MessageUtils.showMiddleToast(AddUdinspojxxmActivity.this, "新建成功");
                        setResult(Constants.REFRESH);
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        MessageUtils.showMiddleToast(AddUdinspojxxmActivity.this, "新建失败");
                        setResult(Constants.REFRESH);
                        finish();
                    }

                } else {
                    MessageUtils.showMiddleToast(AddUdinspojxxmActivity.this, "新增失败");
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
        String description = descriptionText.getText().toString();
        String udinspojxxmlinenum = udinspojxxmlinenumText.getText().toString();
        String execution = executionText.getText().toString();
        String checkby = checkbyText.getText().toString();

        JSONObject json = new JSONObject();

        json.put("UDINSPOJXXMLINENUM", udinspojxxmlinenum);
        json.put("UDINSPOASSETNUM", udinspoassetnum);
        json.put("TYPE", Constants.ADD);
        if (!description.equals("")) {
            json.put("DESCRIPTION", description);
        }
        if (!execution.equals("")) {
            json.put("EXECUTION", execution);
        }
        if (!checkby.equals("")) {
            json.put("CHECKBY", checkby);
        }


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("GRADESON", jsonUdinspojxxmInfo(json.toString()));


        return jsonObject.toString();
    }


    /**
     * 封装udinspojxxm信息*
     */
    private JSONArray jsonUdinspojxxmInfo(String str) {

        Log.i(TAG, "str=" + str);
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
     * 保存信息*
     */
    private Udinspojxxm saveUdinspojxxmInfo() {
        udinspojxxm = new Udinspojxxm();
        udinspojxxm.setUdinspojxxmlinenum(udinspojxxmlinenumText.getText().toString());
        udinspojxxm.setUdinspoassetnum(udinspoassetnum);
        udinspojxxm.setDescription(descriptionText.getText().toString());
        udinspojxxm.setExecution(executionText.getText().toString());
        udinspojxxm.setCheckby(checkbyText.getText().toString());
        new UdinspojxxmDao(AddUdinspojxxmActivity.this).insert(udinspojxxm);
        return udinspojxxm;
    }


}
