package com.jf_eam_project.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Assets;
import com.jf_eam_project.utils.MessageUtils;

import org.w3c.dom.Text;


/**
 * 查询结果界面*
 */
public class Results_Activity extends BaseActivity {
    private static final String TAG = "Results_Activity";
    /**
     * 返回按钮*
     */
    private ImageView backImageView;
    /**
     * 标题*
     */
    private TextView titleText;
    /**
     * 扫描结果*
     */
    String result;

    /**
     * 扫描结果*
     */
    private TextView resultText;

    /**
     * 资产详情*
     */
    private Button assetBtn;
    /**
     * 检修记录*
     */
    private Button checkBtn;

    /**
     * 资产类*
     */

    private Assets assets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getInit();
        findViewById();
        initView();
    }

    /**
     * 获取上个界面的数据*
     */
    private void getInit() {
        result = getIntent().getExtras().getString("result");

        Log.i(TAG, "result=" + result);

    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleText = (TextView) findViewById(R.id.title_name);
        resultText = (TextView) findViewById(R.id.results_id);
        assetBtn = (Button) findViewById(R.id.asset_desc_id);
        checkBtn = (Button) findViewById(R.id.check_record_id);

    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleText.setText("扫描结果");
        resultText.setText(result);

        assetBtn.setOnClickListener(assetBtnOnClickListener);
        checkBtn.setOnClickListener(checkBtnOnClickListener);

        if (result.indexOf("资产:") == -1) {
            assetBtn.setVisibility(View.GONE);
            checkBtn.setVisibility(View.GONE);

            MessageUtils.showMiddleToast(Results_Activity.this,"格式不是标准的资产二维码格式");
        }
    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


    /**
     * 资产详情*
     */
    private View.OnClickListener assetBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Assets assets = parsingAssets(result);
            Intent intent = new Intent(Results_Activity.this, AssetsDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("assets", assets);
            intent.putExtras(bundle);
            startActivityForResult(intent, 0);
        }
    };

    /**
     * 检修记录*
     */
    private View.OnClickListener checkBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Assets assets = parsingAssets(result);
            //跳转至工单界面
            Intent intent = new Intent(Results_Activity.this, Work_ListActivity.class);

            intent.putExtra("assetnum", assets.getAssetnum());
            startActivityForResult(intent, 0);

        }
    };


    /**
     * 解析扫描结果*
     */
    private Assets parsingAssets(String result) {

            String[] sourceStrArray = result.split(",");
            String assetNum = sourceStrArray[0].replace("资产:", "");
            String assetdesc = sourceStrArray[1].replace("描述:", "");
            String assetLocation = sourceStrArray[2].replace("位置:", "");

            assets = new Assets();
            assets.setAssetnum(assetNum);
            assets.setDescription(assetdesc);
            assets.setLocation(assetLocation);


        return assets;
    }

}
