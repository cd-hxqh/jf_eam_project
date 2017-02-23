package com.jf_eam_project.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.model.Assets;

import java.io.IOException;
import java.util.ArrayList;


/**
 * 资产详情
 */
public class AssetsDetailActivity extends BaseActivity {

    private static final String TAG = "AssetsDetailActivity";

    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;

    /**界面说明**/

    /**
     * 资产编号*
     */
    private TextView assetnumText;
    /**
     * 资产描述*
     */
    private TextView descriptionText;
    /**
     * 地点*
     */
    private TextView siteidText;
    /**
     * 位置*
     */
    private TextView locationText;
    /**
     * 设备类型
     */
    private TextView udassettypeText;
    /**
     * 大类
     */
    private TextView firstclassText;

    /**
     * 中类
     */
    private TextView secondclassText;

    /**
     * 小类
     */
    private TextView thirdclassText;

    /**
     * 安装日期
     */
    private TextView azrqText;

    /**
     * 保修到期日
     */
    private TextView bxdqrText;
    /**
     * 采购价格
     */
    private TextView purchasepriceText;

    /**
     * 投运日期
     */
    private TextView tyrqText;

    /**
     * 已使用年限
     */
    private TextView ysynxText;


    /**资产编号**/
    private String assetnum;
    /**
     * Asset*
     */
    private Assets assets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets_detail);
        initData();
        showProgressDialog("正在加载...");

        getData();
        findViewById();
        initView();


    }


    /**获取基础数据**/
    private void getData() {
            HttpManager.getDataPagingInfo(this, HttpManager.getAssetUrl(assetnum), new HttpRequestHandler<Results>() {
                @Override
                public void onSuccess(Results results) {
                    Log.i(TAG, "data=" + results);
                }

                @Override
                public void onSuccess(Results results, int totalPages, int currentPage) {

                    Log.i(TAG, "results=" + results.getResultlist());
                    closeProgressDialog();
                    ArrayList<Assets> items = null;
                    try {
                        items = Ig_Json_Model.parsingAsset(results.getResultlist());
                        if (items == null || items.isEmpty()) {
                            Log.i(TAG,"is null");
                        } else {

                            assets=items.get(0);
                            setData();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(String error) {
                    closeProgressDialog();
                }
            });
    }

    private void initData() {
        assetnum =  getIntent().getStringExtra("assetnum");
    }


    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);


        assetnumText = (TextView) findViewById(R.id.asset_num_text_id);
        descriptionText = (TextView) findViewById(R.id.asset_description_text_id);
        siteidText = (TextView) findViewById(R.id.asset_sited_text_id);
        locationText = (TextView) findViewById(R.id.asset_loction_text_id);

        udassettypeText = (TextView) findViewById(R.id.udassettype_text_id);
        firstclassText = (TextView) findViewById(R.id.firstclass_text_id);
        secondclassText = (TextView) findViewById(R.id.secondclass_text_id);
        thirdclassText = (TextView) findViewById(R.id.thirdclass_text_id);
        azrqText = (TextView) findViewById(R.id.azrq_text_id);
        bxdqrText = (TextView) findViewById(R.id.bxdqr_text_id);
        purchasepriceText = (TextView) findViewById(R.id.purchaseprice_text_id);
        tyrqText = (TextView) findViewById(R.id.tyrq_text_id);
        ysynxText = (TextView) findViewById(R.id.ysynx_text_id);

    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.assets_title_text));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);



        setData();

    }

    private void setData() {


        if (assets != null) {
            assetnumText.setText(assets.assetnum == null ? "" : assets.assetnum);
            descriptionText.setText(assets.description == null ? "" : assets.description);
            siteidText.setText(assets.siteid == null ? "" : assets.siteid);
            locationText.setText(assets.location == null ? "" : assets.location);


            udassettypeText.setText(assets.udassettype == null ? "" : assets.udassettype);
            firstclassText.setText(assets.firstclass == null ? "" : assets.firstclass);
            secondclassText.setText(assets.secondclass == null ? "" : assets.secondclass);
            thirdclassText.setText(assets.thirdclass == null ? "" : assets.thirdclass);
            azrqText.setText(assets.azrq == null ? "" : assets.azrq);
            bxdqrText.setText(assets.bxdqr == null ? "" : assets.bxdqr);
            purchasepriceText.setText(assets.purchaseprice == null ? "" : assets.purchaseprice);
            tyrqText.setText(assets.tyrq == null ? "" : assets.tyrq);
            ysynxText.setText(assets.ysynx == null ? "" : assets.ysynx);
        }
    }

    private View.OnClickListener backImageViewOnClickListenrer = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

}
