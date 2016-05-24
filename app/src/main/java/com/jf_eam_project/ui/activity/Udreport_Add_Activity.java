package com.jf_eam_project.ui.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.dialog.widget.NormalListDialog;
import com.jf_eam_project.Dao.CreatereportDao;
import com.jf_eam_project.Dao.LocationDao;
import com.jf_eam_project.Dao.PersonDao;
import com.jf_eam_project.Dao.UdreportDao;
import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Assignment;
import com.jf_eam_project.model.Createreport;
import com.jf_eam_project.model.Option;
import com.jf_eam_project.model.Person;
import com.jf_eam_project.model.Udreport;
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.model.Wplabor;
import com.jf_eam_project.model.Wpmaterial;
import com.jf_eam_project.ui.adapter.GridAdapter;
import com.jf_eam_project.ui.widget.CumTimePickerDialog;
import com.jf_eam_project.utils.AccountUtils;
import com.jf_eam_project.utils.DataUtils;
import com.jf_eam_project.utils.GetNowTime;
import com.jf_eam_project.utils.JsonUtils;
import com.jf_eam_project.utils.MessageUtils;
import com.jf_eam_project.utils.NetWorkHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * 新增故障和缺陷提报单
 */
public class Udreport_Add_Activity extends BaseActivity {

    private static final String TAG = "Udreport_Add_Activity";

    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;


    /**
     * 故障提报单界面信息显示*
     */
    private TextView descriptionText; //描述
    private TextView assettypeText; //设备专业
    private TextView culevelText; //故障等级

    private TextView assetnumText; //设备
    private TextView locationText; //位置

    private TextView stoptimeText; //设备停机时间

    private TextView branchText; //分公司
    private TextView udbelongText; //运行单位

    private TextView failurecodeText; //故障类
    private EditText cudescribeText; //故障、隐患描述


    /**
     * 缺陷提报单*
     */
    private TextView qxassettypeText; //设备专业
    private TextView qxtypeText; //缺陷类型
    private TextView qxsjlyText; //数据来源
    private TextView qxculevelText;//缺陷等级
    private TextView qxlocationText;//位置
    private TextView qxassenumText;//设备
    private TextView qxfailurecodeText;//故障类

    private TextView qxbranchText; //分公司
    private TextView qxudbelongText; //运行单位

    private TextView qxdescriptionText;//描述
    private TextView qxcudescribeText; //故障,隐患描述


    /**
     * apptype*
     */
    private String apptype;


    /**故障**/

    /**
     * 设备专业*
     */
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
    /**
     * 故障等级*
     */
    private ArrayList<DialogMenuItem> tMenuItems = new ArrayList<>();


    /**缺陷**/
    /**
     * 设备专业*
     */
    private ArrayList<DialogMenuItem> aMenuItems = new ArrayList<>();
    /**
     * 缺陷类型*
     */
    private ArrayList<DialogMenuItem> qMenuItems = new ArrayList<>();
    /**
     * 数据来源*
     */
    private ArrayList<DialogMenuItem> qxsjlyMenuItems = new ArrayList<>();
    /**
     * 缺陷等级*
     */
    private ArrayList<DialogMenuItem> qxculevelMenuItems = new ArrayList<>();


    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    /**
     * 位置*
     */
    private String location = "";


    private DatePickerDialog datePickerDialog;
    private CumTimePickerDialog timePickerDialog;
    StringBuffer sb;


    /**
     * 新增按钮*
     */
    private Button addButton;


    /**
     * 图片*
     */
    private GridView noScrollgridview;
    private GridAdapter adapter; //相片选择

    /**
     * 照片*
     */
    public static int max = 0;
    public static List<Bitmap> bmp = new ArrayList<Bitmap>();
    public static HashMap<String, Boolean> mHashMap = new HashMap<String, Boolean>();

    //图片sd地址  上传服务器时把图片调用下面方法压缩后 保存到临时文件夹 图片压缩后小于100KB，失真度不明显
    private List<String> drr = new ArrayList<String>();


    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        if (apptype.equals("FAULT")) {
            setContentView(R.layout.activity_udreport);
            findViewById();
            initView();

            mBasIn = new BounceTopEnter();
            mBasOut = new SlideBottomExit();
            addassetTypeData(); //设备专业
            addCulevelData(); //故障等级
            setDataListener();//设置日期
        } else if (apptype.equals("HIDDEN")) {
            setContentView(R.layout.activity_qxudreport);
            findViewByIdQX();
            initViewQX();
            addqxassetTypeData(); //缺陷设备专业
            addqxtypeData();  //缺陷类型
            addqxsjlyData(); //数据来源
            addqxculevelData(); //缺陷等级

        }
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();


    }


    /**
     * 获取初始话数据*
     */
    private void initData() {
        apptype = getIntent().getExtras().getString("apptype");
        Log.i(TAG, "apptype=" + apptype);

    }

    /**
     * 故障*
     */
    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        assettypeText = (TextView) findViewById(R.id.assettype_text_id);
        culevelText = (TextView) findViewById(R.id.culevel_text_id);
        assetnumText = (TextView) findViewById(R.id.assetnum_text_id);
        locationText = (TextView) findViewById(R.id.locaton_text_id);
        stoptimeText = (TextView) findViewById(R.id.stoptime_text);
        failurecodeText = (TextView) findViewById(R.id.failurecode_text_id);
        branchText = (TextView) findViewById(R.id.branch_description_text_id);
        udbelongText = (TextView) findViewById(R.id.udbelong_text_id);


        descriptionText = (EditText) findViewById(R.id.description_text_id);
        cudescribeText = (EditText) findViewById(R.id.cudescribe_text_id);
        noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);

        addButton = (Button) findViewById(R.id.createreport_add_button);


    }

    /**
     * 缺陷*
     */
    private void findViewByIdQX() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        qxassettypeText = (TextView) findViewById(R.id.assettype_text_id);
        qxtypeText = (TextView) findViewById(R.id.qxtype_text_id);
        qxsjlyText = (TextView) findViewById(R.id.qxsjly_text_id);
        qxculevelText = (TextView) findViewById(R.id.culevel_text_id);
        qxlocationText = (TextView) findViewById(R.id.locaton_text_id);
        qxassenumText = (TextView) findViewById(R.id.assetnum_text_id);
        qxfailurecodeText = (TextView) findViewById(R.id.failurecode_text_id);

        qxbranchText = (TextView) findViewById(R.id.branch_description_text_id);
        qxudbelongText = (TextView) findViewById(R.id.udbelong_text_id);


        qxdescriptionText = (TextView) findViewById(R.id.description_text_id);
        qxcudescribeText = (TextView) findViewById(R.id.cudescribe_text_id);

        noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);
        addButton = (Button) findViewById(R.id.createreport_add_button);
    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.gztbd_title));


        backImageView.setOnClickListener(backImageViewOnClickListenrer);


        branchText.setText("华北分公司");
        udbelongText.setText(findByPersionId().getDepartmentms());


        assettypeText.setOnClickListener(assettypeTextOnClickListener);
        culevelText.setOnClickListener(culevelTextOnClickListener);

        assetnumText.setOnClickListener(asstnumTextOnClickListener);
        locationText.setOnClickListener(locationTextOnClickListener);

        stoptimeText.setOnClickListener(stoptimeTextOnClickListener);


        failurecodeText.setOnClickListener(failurecodeTextOnClickListener);


        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));

        adapter = new GridAdapter(this, readFile());
//        adapter.update();
        noScrollgridview.setAdapter(adapter);
        noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (arg2 == drr.size()) {
                    ActionSheetDialog();
                } else {
                    Intent intent = new Intent(Udreport_Add_Activity.this,
                            PhotoActivity.class);
                    intent.putExtra("ID", arg2);
                    intent.putStringArrayListExtra("drr", (ArrayList<String>) drr);
                    startActivityForResult(intent, 1000);
                }
            }
        });


        addButton.setOnClickListener(addButtonOnClickListener);


    }

    /**
     * 缺陷*
     */
    private void initViewQX() {
        titleView.setText(getString(R.string.qxtbd_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);
        qxassettypeText.setOnClickListener(qxassettypeTextOnClickListener);
        qxtypeText.setOnClickListener(qxtypeTextOnClickListener);
        qxsjlyText.setOnClickListener(qxsjlyTextOnClickListener);
        qxculevelText.setOnClickListener(qxculevelTextOnClickListener);
        qxlocationText.setOnClickListener(asstnumTextOnClickListener);
        qxassenumText.setOnClickListener(locationTextOnClickListener);
        qxfailurecodeText.setOnClickListener(failurecodeTextOnClickListener);


        qxbranchText.setText("华北分公司");
        qxudbelongText.setText(findByPersionId().getDepartmentms());

        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));

        adapter = new GridAdapter(this, readFile());
//        adapter.update();
        noScrollgridview.setAdapter(adapter);
        noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (arg2 == drr.size()) {
                    ActionSheetDialog();
                } else {
                    Intent intent = new Intent(Udreport_Add_Activity.this,
                            PhotoActivity.class);
                    intent.putExtra("ID", arg2);
                    intent.putStringArrayListExtra("drr", (ArrayList<String>) drr);
                    startActivityForResult(intent, 1000);
                }
            }
        });


        addButton.setOnClickListener(addButtonOnClickListener);

    }


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
     * 设备专业*
     */
    private View.OnClickListener assettypeTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NormalListDialog();
        }
    };


    /**
     * 故障等级*
     */
    private View.OnClickListener culevelTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NormalListDialog2();
        }
    };

    /**
     * 故障类*
     */
    private View.OnClickListener failurecodeTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Udreport_Add_Activity.this, OptionActivity.class);
            intent.putExtra("requestCode", Constants.FAILURECODE);
            startActivityForResult(intent, Constants.FAILURECODE);
        }
    };


    /**
     * 设备*
     */

    private View.OnClickListener asstnumTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Udreport_Add_Activity.this, OptionActivity.class);
            intent.putExtra("requestCode", Constants.ASSETCODE);
            startActivityForResult(intent, Constants.ASSETCODE);
        }
    };

    /**
     * 位置*
     */

    private View.OnClickListener locationTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Udreport_Add_Activity.this, OptionActivity.class);
            intent.putExtra("requestCode", Constants.LOCATIONCODE);
            startActivityForResult(intent, Constants.LOCATIONCODE);
        }
    };


    /**
     * 停机时间*
     */
    private View.OnClickListener stoptimeTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sb = new StringBuffer();
            datePickerDialog.show();
        }
    };


    /**
     * 设备类别*
     */
    private void NormalListDialog() {
        final NormalListDialog dialog = new NormalListDialog(Udreport_Add_Activity.this, mMenuItems);
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


    /**
     * 故障等级*
     */
    private void NormalListDialog2() {
        final NormalListDialog dialog = new NormalListDialog(Udreport_Add_Activity.this, tMenuItems);
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
     * 添加设备专业数据*
     */
    private void addassetTypeData() {
        String[] lctypes = getResources().getStringArray(R.array.lctype_tab_titles);

        for (int i = 0; i < lctypes.length; i++)
            mMenuItems.add(new DialogMenuItem(lctypes[i], 0));


    }

    /**
     * 添加数据*
     */
    private void addCulevelData() {
        String[] lctypes = getResources().getStringArray(R.array.culevel_tab_titles);

        for (int i = 0; i < lctypes.length; i++)
            tMenuItems.add(new DialogMenuItem(lctypes[i], 0));


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        switch (resultCode) {
            case Constants.ASSETCODE:
                option = (Option) data.getSerializableExtra("option");
                if (apptype.equals("FAULT")) {
                    assetnumText.setText(option.getName());
                    location = option.getValue();
                    locationText.setText(option.getDescription());
                    if (new LocationDao(Udreport_Add_Activity.this).queryLocation(option.getValue()) != null) {
                        locationText.setText(new LocationDao(Udreport_Add_Activity.this).queryLocation(option.getValue()).description);
                    }
                } else if (apptype.equals("HIDDEN")) {
                    qxassenumText.setText(option.getName());
                    location = option.getValue();
                    qxlocationText.setText(option.getDescription());
                    if (new LocationDao(Udreport_Add_Activity.this).queryLocation(option.getValue()) != null) {
                        qxlocationText.setText(new LocationDao(Udreport_Add_Activity.this).queryLocation(option.getValue()).description);
                    }
                }
                break;
            case Constants.LOCATIONCODE:
                option = (Option) data.getSerializableExtra("option");
                location = option.getName();
                if (apptype.equals("FAULT")) {
                    locationText.setText(option.getDescription());
                } else if (apptype.equals("HIDDEN")) {
                    locationText.setText(option.getDescription());
                }
                break;
            case Constants.FAILURECODE:
                option = (Option) data.getSerializableExtra("option");
                if (apptype.equals("FAULT")) {
                    failurecodeText.setText(option.getName());
                } else if (apptype.equals("HIDDEN")) {
                    qxfailurecodeText.setText(option.getName());
                }
                break;
            default:
                break;
        }


        switch (requestCode) {
            case TAKE_PICTURE:
                if (drr.size() < 9 && resultCode == -1) {
                    drr.add(path);
                }
            case 1000:
                adapter = new GridAdapter(this, readFile());
                noScrollgridview.setAdapter(adapter);
//                adapter.update();
                break;
        }


    }


    private class datelistener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            sb = new StringBuffer();
            monthOfYear = monthOfYear + 1;
            if (dayOfMonth < 10) {
                sb.append(year % 100 + "-" + monthOfYear + "-" + "0" + dayOfMonth);
            } else {
                sb.append(year % 100 + "-" + monthOfYear + "-" + dayOfMonth);
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

            stoptimeText.setText(sb);
        }
    }


    /**
     * 拍照弹出框*
     */
    private void ActionSheetDialog() {
        final String[] stringItems = {"拍照"};
        final ActionSheetDialog dialog = new ActionSheetDialog(Udreport_Add_Activity.this, stringItems, null);

        dialog.titleTextColor(getResources().getColor(R.color.light_blue_color_1));
        dialog.show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) { //拍照
                    photo();
                }
//                else if (position == 1) { //相册
//                    PhotoAlbum();
//                }
                dialog.dismiss();
            }
        });

    }


    private static final int TAKE_PICTURE = 0x000000;

    private String path = "";


    /**
     * 拍照*
     */
    public void photo() {
        String fileName = DataUtils.getFileImagePath(Udreport_Add_Activity.this, "addreport");
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(fileName, String.valueOf(System.currentTimeMillis())
                + ".JPEG");
        path = file.getPath();

        Uri imageUri = Uri.fromFile(file);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    /**
     * 相册选择*
     */
    private void PhotoAlbum() {
        Intent intent = new Intent(Udreport_Add_Activity.this, ShowImageActivity.class);
        startActivityForResult(intent, 0);
    }


    @Override
    protected void onRestart() {

//        adapter.update();
        adapter = new GridAdapter(this, readFile());
        noScrollgridview.setAdapter(adapter);
        super.onRestart();
    }

    /**
     * 读取文件*
     */
    private List<String> readFile() {
        String fileName = DataUtils.getFileImagePath(Udreport_Add_Activity.this, "addreport");
        File file = new File(fileName);
        drr = new ArrayList<String>();
        if (file.exists()) {

            if (file.isDirectory()) {
                for (String name : file.list()) {

                    File file1 = new File(fileName, name);
                    if (file1.exists()) {
                        drr.add(file1.getPath());
                    }

                }
            }


        }
        return drr;
    }


    /**
     * 保存提报单信息*
     */
    private View.OnClickListener addButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (apptype.equals("FAULT")) {
                if (judge()) {
                    encapsulationData();
                }
            } else if (apptype.equals("HIDDEN")) {
                if (judgeqx()) {
                    encapsulationData();
                }
            }

        }
    };


    /**
     * 判断提交的信息*
     */
    private boolean judge() {

        if (assettypeText.getText().toString().equals("")) {
            MessageUtils.showErrorMessage(Udreport_Add_Activity.this, "设备专业必填");
            return false;
        }
        if (culevelText.getText().toString().equals("")) {
            MessageUtils.showErrorMessage(Udreport_Add_Activity.this, "故障等级必填");
            return false;
        }
        if (location.equals("")) {
            MessageUtils.showErrorMessage(Udreport_Add_Activity.this, "位置必填");
            return false;
        }
        if (descriptionText.getText().toString().equals("")) {
            MessageUtils.showErrorMessage(Udreport_Add_Activity.this, "描述必填");
            descriptionText.setError("描述必填");
            return false;
        }
        if (cudescribeText.getText().toString().equals("")) {
            MessageUtils.showErrorMessage(Udreport_Add_Activity.this, "故障,隐患描述必填");
            cudescribeText.setError("故障,隐患描述必填");
            return false;
        }
        return true;

    }

    /**
     * 判断缺陷提交的信息*
     */
    private boolean judgeqx() {

        if (qxassettypeText.getText().toString().equals("")) {
            MessageUtils.showErrorMessage(Udreport_Add_Activity.this, "设备专业必填");
            return false;
        }
        if (qxtypeText.getText().toString().equals("")) {
            MessageUtils.showErrorMessage(Udreport_Add_Activity.this, "缺陷类型必填");
            return false;
        }
        if (qxsjlyText.getText().toString().equals("")) {
            MessageUtils.showErrorMessage(Udreport_Add_Activity.this, "缺陷数据来源必填");
            return false;
        }
        if (location.equals("")) {
            MessageUtils.showErrorMessage(Udreport_Add_Activity.this, "位置必填");
            return false;
        }
        if (qxdescriptionText.getText().toString().equals("")) {
            MessageUtils.showErrorMessage(Udreport_Add_Activity.this, "描述必填");
            qxdescriptionText.setError("描述必填");
            return false;
        }
        if (qxcudescribeText.getText().toString().equals("")) {
            MessageUtils.showErrorMessage(Udreport_Add_Activity.this, "故障,隐患描述必填");
            qxcudescribeText.setError("故障,隐患描述必填");
            return false;
        }
        return true;

    }


    /**
     * 数据封装*
     */
    private void encapsulationData() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Udreport_Add_Activity.this);
        builder.setMessage("确定新增提报单吗？").setTitle("提示")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                mProgressDialog = ProgressDialog.show(Udreport_Add_Activity.this, null,
                        getString(R.string.inputing), true, true);
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setCancelable(false);

                if (NetWorkHelper.isNetwork(Udreport_Add_Activity.this)) {
                    MessageUtils.showMiddleToast(Udreport_Add_Activity.this, "暂无网络,现离线保存数据!");
                    Udreport udreport = null;
                    if (apptype.equals("FAULT")) {
                        udreport = saveReport();
                    } else if (apptype.equals("HIDDEN")) {
                        udreport = saveQxReport();
                    }
                    udreport.setLoc(1);
                    new UdreportDao(Udreport_Add_Activity.this).insert(udreport);
                    mProgressDialog.dismiss();
                    setResult(Constants.REFRESH);
                    finish();
                } else {


                    new AsyncTask<String, String, String>() {
                        @Override
                        protected String doInBackground(String... strings) {
                            String data = null;
                            Udreport udreport = null;
                            if (apptype.equals("FAULT")) {
                                udreport = saveReport();
                                data = JsonUtils.saveUdreport(udreport);
                            } else if (apptype.equals("HIDDEN")) {
                                udreport = saveQxReport();
                                data = JsonUtils.saveQxUdreport(udreport);
                            }


                            Log.i(TAG, "data=" + data);

                            String result = getBaseApplication().getWsService().addReport(Udreport_Add_Activity.this, data, "");
                            return result;
                        }

                        @Override
                        protected void onPostExecute(String s) {

                            super.onPostExecute(s);
                            mProgressDialog.dismiss();
                            Log.i(TAG, "s=" + s);
                            try {
                                if (s != null) {
                                    JSONObject jsonObject = new JSONObject(s);
                                    String Msg = jsonObject.getString("Msg");
                                    String errorNo = jsonObject.getString("errorNo");
                                    if (errorNo.equals("0")) {
                                        MessageUtils.showMiddleToast(Udreport_Add_Activity.this, "数据新增成功");

                                    } else {
                                        MessageUtils.showMiddleToast(Udreport_Add_Activity.this, Msg);
                                    }
                                    setResult(Constants.REFRESH);
                                    finish();
                                } else {
                                    MessageUtils.showMiddleToast(Udreport_Add_Activity.this, "数据新增失败");
                                    setResult(Constants.REFRESH);
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                MessageUtils.showMiddleToast(Udreport_Add_Activity.this, "数据新增失败");
                                setResult(Constants.REFRESH);
                                finish();
                            }


                        }
                    }.execute();
                }
            }
        }).create().show();


    }


    /**
     * 报错故障单*
     */
    private Udreport saveReport() {

        String assettype = assettypeText.getText().toString();//设备专业

        String culevel = culevelText.getText().toString(); //故障等级

//        String location = locationText.getText().toString(); //位置

        String assetnum = assetnumText.getText().toString(); //设备

        String stoptime = stoptimeText.getText().toString(); //停机时间

        String failurecode = failurecodeText.getText().toString(); //故障类


        String description = descriptionText.getText().toString(); //描述


        String cudescribe = cudescribeText.getText().toString(); //故障，隐患描述


        Udreport udreport = new Udreport();

        udreport.setApptype(apptype); //类型

        udreport.setAssettype(assettype); //设备专业

        udreport.setCulevel(culevel); //故障等级

        udreport.setLocation(location); //位置

        udreport.setAssetnum(assetnum); //设备

        udreport.setStoptime(stoptime); //停机时间

        udreport.setFailurecode(failurecode); //故障类

        udreport.setDescription(description); //描述

        udreport.setCudescribe(cudescribe); //故障，隐患描述


        udreport.setCreateby_displayname(AccountUtils.getUserName(Udreport_Add_Activity.this)); //提报人

        udreport.setCreatedate(GetNowTime.getTime()); //提报日期
        udreport.setBranch_description("01001"); //分公司
        String udbelong = findByPersionId().department;
        Log.i(TAG, "udbelong=" + udbelong);

        udreport.setUdbelong(udbelong); //运行单位
        return udreport;


    }

    /**
     * 缺陷单*
     */
    private Udreport saveQxReport() {

        String assettype = qxassettypeText.getText().toString();//设备专业

        String qxtype = qxtypeText.getText().toString(); //缺陷类型

        String qxsjly = qxsjlyText.getText().toString(); //数据来源

        String qxculevel = qxculevelText.getText().toString(); //缺陷等级

        String assetnum = qxassenumText.getText().toString(); //设备

        String failurecode = qxfailurecodeText.getText().toString(); //故障类


        String description = qxdescriptionText.getText().toString(); //描述


        String cudescribe = qxcudescribeText.getText().toString(); //故障，隐患描述


        Udreport udreport = new Udreport();

        udreport.setApptype(apptype); //类型

        udreport.setAssettype(assettype); //设备专业

        udreport.setQxtype(qxtype); //缺陷类型

        udreport.setQxsjly(qxsjly); //数据来源

        udreport.setQxsjly(qxculevel); //缺陷等级

        udreport.setLocation(location); //位置

        udreport.setAssetnum(assetnum); //设备


        udreport.setFailurecode(failurecode); //故障类

        udreport.setDescription(description); //描述

        udreport.setCudescribe(cudescribe); //故障，隐患描述


        udreport.setCreateby_displayname(AccountUtils.getUserName(Udreport_Add_Activity.this)); //提报人

        udreport.setCreatedate(GetNowTime.getTime()); //提报日期

        udreport.setBranch_description("01001"); //分公司
        String udbelong = findByPersionId().department;
        Log.i(TAG, "udbelong=" + udbelong);

        udreport.setUdbelong(udbelong); //运行单位
        return udreport;


    }


    /**缺陷设置**/


    /**
     * 设备专业*
     */
    private View.OnClickListener qxassettypeTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NormaAssetialog();
        }
    };
    /**
     * 缺陷类型*
     */
    private View.OnClickListener qxtypeTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NormaQxtypes();
        }
    };
    /**
     * 数据来源*
     */
    private View.OnClickListener qxsjlyTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NormaQxsjly();
        }
    };
    /**
     * 缺陷等级*
     */
    private View.OnClickListener qxculevelTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NormaQxculevel();
        }
    };


    /**
     * 添加设备专业数据*
     */
    private void addqxassetTypeData() {
        String[] asstypes = getResources().getStringArray(R.array.assettype_tab_titles);

        for (int i = 0; i < asstypes.length; i++)
            aMenuItems.add(new DialogMenuItem(asstypes[i], 0));


    }

    /**
     * 添加缺陷类型数据*
     */
    private void addqxtypeData() {
        String[] qxtypes = getResources().getStringArray(R.array.qxtype_tab_titles);

        for (int i = 0; i < qxtypes.length; i++)
            qMenuItems.add(new DialogMenuItem(qxtypes[i], 0));


    }

    /**
     * 添加数据来源数据*
     */
    private void addqxsjlyData() {
        String[] qxsjlys = getResources().getStringArray(R.array.qxsjly_tab_titles);

        for (int i = 0; i < qxsjlys.length; i++)
            qxsjlyMenuItems.add(new DialogMenuItem(qxsjlys[i], 0));


    }

    /**
     * 添加缺陷等级*
     */
    private void addqxculevelData() {
        String[] qxculevel = getResources().getStringArray(R.array.qxculevel_tab_titles);

        for (int i = 0; i < qxculevel.length; i++)
            qxculevelMenuItems.add(new DialogMenuItem(qxculevel[i], 0));


    }


    /**
     * 设备类别*
     */
    private void NormaAssetialog() {
        final NormalListDialog dialog = new NormalListDialog(Udreport_Add_Activity.this, aMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                qxassettypeText.setText(aMenuItems.get(position).mOperName);

                dialog.dismiss();
            }
        });
    }

    /**
     * 缺陷类型*
     */
    private void NormaQxtypes() {
        final NormalListDialog dialog = new NormalListDialog(Udreport_Add_Activity.this, qMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                qxtypeText.setText(qMenuItems.get(position).mOperName);

                dialog.dismiss();
            }
        });
    }

    /**
     * 数据来源*
     */
    private void NormaQxsjly() {
        final NormalListDialog dialog = new NormalListDialog(Udreport_Add_Activity.this, qxsjlyMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                qxsjlyText.setText(qxsjlyMenuItems.get(position).mOperName);

                dialog.dismiss();
            }
        });
    }

    /**
     * 缺陷等级*
     */
    private void NormaQxculevel() {
        final NormalListDialog dialog = new NormalListDialog(Udreport_Add_Activity.this, qxculevelMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                qxculevelText.setText(qxculevelMenuItems.get(position).mOperName);

                dialog.dismiss();
            }
        });
    }


    /**
     * 根据PersionId查询所属部门*
     */
    private Person findByPersionId() {
        Person person = new PersonDao(Udreport_Add_Activity.this).getPersionId(AccountUtils.getPersonId(Udreport_Add_Activity.this));
        return null == person ? null : person;
    }

}
