package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.bean.Wlh;
import com.jf_eam_project.model.Xzwl;
import com.jf_eam_project.ui.adapter.WlhListAdapter;
import com.jf_eam_project.utils.AccountUtils;
import com.jf_eam_project.utils.GetNowTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2015/11/20.
 * 领料单物料行列表
 */
public class Wlh_ListActivity extends BaseActivity {

    private static final String TAG = "Wlh_ListActivity";
    public static  final int WLH_CODE=1001; //物料行
    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 返回*
     */
    private ImageView backImage;

    /**
     * 选择物料
     **/
    private Button wlBtn;

    private Button sbumitBtn;


    LinearLayoutManager layoutManager;

    public RecyclerView recyclerView;

    private LinearLayout nodatalayout;

    private WlhListAdapter wlhListAdapter;


    private List<Wlh> wlhs = new ArrayList<Wlh>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlh);

        findViewById();
        initView();
    }


    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImage = (ImageView) findViewById(R.id.title_back_id);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        wlBtn = (Button) findViewById(R.id.main_btn_id);
        sbumitBtn = (Button) findViewById(R.id.submit_btn_id);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.wz_text);

        backImage.setOnClickListener(backOnClickListener);

        wlBtn.setVisibility(View.VISIBLE);
        wlBtn.setText(R.string.xuwl_text);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        wlBtn.setOnClickListener(wlBtnOnClickListener);
        nodatalayout.setVisibility(View.VISIBLE);
        initAdapter(wlhs);
        sbumitBtn.setOnClickListener(sbumitBtnOnClickListener);
    }

    private View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };


    private View.OnClickListener wlBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Wlh_ListActivity.this, Xzwl_Activity.class);
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener sbumitBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            Bundle bundle=new Bundle();
            bundle.putSerializable("wlhs", (Serializable) wlhs);
            intent.putExtras(bundle);
            setResult(WLH_CODE,intent);
            finish();
        }
    };


    /**
     * 获取数据*
     */
    private void initAdapter(final List<Wlh> list) {
        wlhListAdapter = new WlhListAdapter(Wlh_ListActivity.this, R.layout.list_item, list);
        recyclerView.setAdapter(wlhListAdapter);


    }

    /**
     * 添加数据*
     */
    private void addData(final List<Wlh> list) {
        wlhListAdapter.addData(list);
        wlhListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Xzwl_Activity.CHOOSE:
                List<Xzwl> xzwls = (List<Xzwl>) data.getSerializableExtra("xzwl");
                if (xzwls != null || xzwls.size() != 0) {
                    sbumitBtn.setVisibility(View.VISIBLE);
                    nodatalayout.setVisibility(View.GONE);
                    wlhs=addWlh(xzwls);
                    addData(wlhs);
                }
                break;
        }
    }

    //添加物料行信息
    private List<Wlh> addWlh(List<Xzwl> xzwls) {
        List<Wlh> wlhs = new ArrayList<Wlh>();

        for (Xzwl xzwl : xzwls) {
            Wlh wlh = new Wlh();
            wlh.setITEMNUM(xzwl.getITEMNUM());
            wlh.setDESCRIPTION(xzwl.getITEMDESC());
            wlh.setLINETYPE("项目");
            wlh.setRESTYPE("自动");
            wlh.setITEMQTY("1");
            wlh.setORDERUNIT(xzwl.getISSUEUNIT());
            wlh.setLOCATION(xzwl.getLOCATION());
            wlh.setUNITCOST("0");
            wlh.setLINECOST("0");
            wlh.setREQUESTBY(AccountUtils.getPersonId(Wlh_ListActivity.this));
            wlh.setREQUIREDATE(GetNowTime.getTime());
            wlhs.add(wlh);
        }

        return wlhs;
    }
}
