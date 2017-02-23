package com.jf_eam_project.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.autoupdatesdk.BDAutoUpdateSDK;
import com.baidu.autoupdatesdk.UICheckUpdateCallback;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnBtnEditClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalEditTextDialog;
import com.flyco.dialog.widget.NormalListDialog;
import com.jf_eam_project.Dao.PersonDao;
import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.manager.AppManager;
import com.jf_eam_project.model.Person;
import com.jf_eam_project.utils.AccountUtils;
import com.jf_eam_project.utils.MessageUtils;

import java.io.IOException;
import java.util.ArrayList;


/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private static final String TAG = "Activity_Login";
    private EditText mUsername;
    private EditText mPassword;
    private Button mLogin;
    private ProgressDialog mProgressDialog;
    private CheckBox checkBox; //记住密码
    private boolean isRemember; //记住密码

    private TextView setIpTextView; //设置服务器地址


    String userName; //用户名

    String userPassWorld; //密码

    String imei; //imei

    /**
     * 服务器地址
     */
    private ArrayList<DialogMenuItem> cMenuItems = new ArrayList<>();
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        BDAutoUpdateSDK.uiUpdateAction(this, new MyUICheckUpdateCallback());
        if (AccountUtils.getIpAddress(LoginActivity.this).equals("")) {
            AccountUtils.setIpAddress(LoginActivity.this, Constants.HTTP_API_IP);
        }
        imei = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
                .getDeviceId();

        findViewById();
        initView();
        addAddressData();
    }

    /**
     * 添加数据*
     */
    private void addAddressData() {
        String[] lctypes = getResources().getStringArray(R.array.address_text);

        for (int i = 0; i < lctypes.length; i++)
            cMenuItems.add(new DialogMenuItem(lctypes[i], 0));


    }

    /**
     * 设置服务器地址
     */
    private void NormalListDialog() {
        final NormalListDialog dialog = new NormalListDialog(LoginActivity.this, cMenuItems);
        dialog.title("请选择服务器地址")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                AccountUtils.setIpAddress(LoginActivity.this, cMenuItems.get(position).mOperName);
                dialog.dismiss();
            }
        });
    }


    /**
     * 设置服务器地址
     */
    private void NormalEditTextDialog() {
        final NormalEditTextDialog dialog = new NormalEditTextDialog(LoginActivity.this);
        dialog.title("设置服务器地址");
        String ip = AccountUtils.getIpAddress(LoginActivity.this);
        ip = ip.replaceAll("http://", "");
        ip = ip.replaceAll("/", "");
        dialog.content(ip)
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();

        dialog.setOnBtnClickL(new OnBtnEditClickL() {

                                  @Override
                                  public void onBtnClick(String text) {
                                      dialog.dismiss();
                                  }
                              },
                new OnBtnEditClickL() {
                    @Override
                    public void onBtnClick(String text) {
                        String ips = "http://" + text + "/";
                        Log.i(TAG, "text=" + text);
                        AccountUtils.setIpAddress(LoginActivity.this, ips);
                        dialog.dismiss();
                    }
                }


        );

    }

    @Override
    protected void findViewById() {
        mUsername = (EditText) findViewById(R.id.user_login_id);
        mPassword = (EditText) findViewById(R.id.user_login_password);
        checkBox = (CheckBox) findViewById(R.id.isremenber_password);
        mLogin = (Button) findViewById(R.id.user_login);

        setIpTextView = (TextView) findViewById(R.id.ip_address_text);
    }

    @Override
    protected void initView() {

        isRemember = AccountUtils.getIsChecked(LoginActivity.this);
        mUsername.setText(AccountUtils.getUserName(LoginActivity.this));
        if (isRemember) {

            mPassword.setText(AccountUtils.getUserPassword(LoginActivity.this));
            checkBox.setChecked(isRemember);
        }
        checkBox.setOnCheckedChangeListener(cheBoxOnCheckedChangListener);
        mLogin.setOnClickListener(this);


        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();

        setIpTextView.setOnClickListener(setIpTextViewOnClickListener);
    }

    private View.OnClickListener setIpTextViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            NormalEditTextDialog();
        }
    };


    private CompoundButton.OnCheckedChangeListener cheBoxOnCheckedChangListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            isRemember = isChecked;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_login:
                if (mUsername.getText().length() == 0) {
                    mUsername.setError(getString(R.string.login_error_empty_user));
                    mUsername.requestFocus();
                } else if (mPassword.getText().length() == 0) {
                    mPassword.setError(getString(R.string.login_error_empty_passwd));
                    mPassword.requestFocus();
                } else {
                    userName = mUsername.getText().toString();
                    userPassWorld = mPassword.getText().toString();
                    login();
                }
                break;

        }
    }


    /**
     * 登录界面
     */
    private void login() {
        mProgressDialog = ProgressDialog.show(LoginActivity.this, null,
                getString(R.string.login_loging), true, true);

        HttpManager.loginWithUsername(LoginActivity.this,
                mUsername.getText().toString(),
                mPassword.getText().toString(), imei,
                new HttpRequestHandler<String>() {
                    @Override
                    public void onSuccess(String data) {

                        MessageUtils.showMiddleToast(LoginActivity.this, data);

                        mProgressDialog.dismiss();

                        if (data.equals("用户名或密码错误")) {
                            return;
                        } else {
                            AccountUtils.setChecked(LoginActivity.this, isRemember);
                            AccountUtils.setUserNameAndPassWord(LoginActivity.this, mUsername.getText().toString(), mPassword.getText().toString());
                            getBaseApplication().setUsername(mUsername.getText().toString());

                            /**根据yy**/
                            String personId = AccountUtils.getPersonId(LoginActivity.this);

                            getPersion(personId);

                            startIntent();
                        }

                    }

                    @Override
                    public void onSuccess(String data, int totalPages, int currentPage) {
                        MessageUtils.showMiddleToast(LoginActivity.this, getString(R.string.login_successful_hint));

                        startIntent();
                    }

                    @Override
                    public void onFailure(String error) {
                        MessageUtils.showErrorMessage(LoginActivity.this, error);
                        mProgressDialog.dismiss();
                    }
                });
    }


    private void startIntent() {
        Intent inetnt = new Intent();
        inetnt.setClass(this, MainActivity.class);
        startActivity(inetnt);
    }


    private long exitTime = 0;

    @Override
    public void onBackPressed() {


        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, getString(R.string.exit_text), Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.AppExit(LoginActivity.this);
        }
    }


    /**
     * 根据persionid查询persion信息*
     */

    private void getPersion(final String personId) {
        HttpManager.getData(this, HttpManager.getPersion(personId), new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {

                ArrayList<Person> items = null;

                try {
                    Log.i(TAG,"data="+data);
                    items = Ig_Json_Model.parsingPerson(data);
                    if (items == null || items.isEmpty()) {

                    } else {
                        AccountUtils.setPerson(LoginActivity.this, items.get(0));
                        new PersonDao(LoginActivity.this).create(items);
                    }
                } catch (IOException e) {

                }
            }

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {
            }

            @Override
            public void onFailure(String error) {
                Log.i(TAG, "bdata=" + error);
            }
        });
    }

    private class MyUICheckUpdateCallback implements UICheckUpdateCallback {
        @Override
        public void onCheckComplete() {
            Log.i(TAG, "onCheckComplete");
        }

    }
}
