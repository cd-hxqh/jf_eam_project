package com.jf_eam_project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.model.Locations;
import com.jf_eam_project.model.Xzwl;
import com.jf_eam_project.ui.adapter.XzwlListAdapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;
import com.jf_eam_project.utils.AccountUtils;
import com.jf_eam_project.utils.JsonUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 选择物料
 */
public class Xzwl_Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {

    private static final String TAG = "Xzwl_Activity";

    public static final int CHOOSE=1000; //选择项


    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;

    /**
     * 确定按钮*
     */
    private Button submitBtn;


    LinearLayoutManager layoutManager;


    /**
     * RecyclerView*
     */
    public RecyclerView recyclerView;
    /**
     * 暂无数据*
     */
    private LinearLayout nodatalayout;
    /**
     * 界面刷新*
     */
    private SwipeRefreshLayout refresh_layout = null;
    /**
     * 适配器*
     */
    private XzwlListAdapter xzwlListAdapter;
    /**
     * 编辑框*
     */
    private EditText search;
    /**
     * 查询条件*
     */
    private String searchText = "";
    private int page = 1;

    private String locationstr = "";

    ArrayList<Xzwl> items = new ArrayList<Xzwl>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        findViewById();
        initView();
    }


    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        submitBtn = (Button) findViewById(R.id.main_btn_id);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        search = (EditText) findViewById(R.id.search_edit);
    }

    @Override
    protected void initView() {
        setSearchEdit();
        titlename.setText(getString(R.string.xuwl_text));
        backImageView.setOnClickListener(backImageViewOnClickListener);
        submitBtn.setVisibility(View.VISIBLE);
        submitBtn.setText(getString(R.string.confirm_btn));
        submitBtn.setOnClickListener(submitBtnOnClckListener);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        refresh_layout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        refresh_layout.setRefreshing(true);
        initAdapter(items);

        getLocationData();
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);
    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


    /**确定按钮**/
    private View.OnClickListener submitBtnOnClckListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            xzwlListAdapter.getdata(new XzwlListAdapter.MycheckListener() {
                @Override
                public void getcheckeddata(List<Xzwl> listBean) {
                    Intent intent=getIntent();
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("xzwl", (Serializable) listBean);
                    intent.putExtras(bundle);
                    setResult(CHOOSE,intent);
                    finish();
                }
            });
        }
    };

    @Override
    public void onLoad() {
        page++;
        getINVENTORYData();
    }

    @Override
    public void onRefresh() {
        page = 1;
        getINVENTORYData();
    }


    private void setSearchEdit() {
        SpannableString msp = new SpannableString("XX搜索");
        Drawable drawable = getResources().getDrawable(R.drawable.ic_search);
        msp.setSpan(new ImageSpan(drawable), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        search.setHint(msp);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    ((InputMethodManager) search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    Xzwl_Activity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    refresh_layout.setRefreshing(true);
                    searchText = search.getText().toString();
                    xzwlListAdapter.removeAll(items);
                    items = new ArrayList<Xzwl>();
                    nodatalayout.setVisibility(View.GONE);
                    refresh_layout.setRefreshing(true);
                    page = 1;
                    getINVENTORYData();
                    return true;
                }
                return false;
            }
        });
    }


    /**
     * 根据udbelong获取库房信息
     */
    private void getLocationData() {
        HttpManager.getDataPagingInfo(this, HttpManager.getLocatiosUrl("", AccountUtils.getDepartment(Xzwl_Activity.this), page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {


                ArrayList<Locations> items = null;
                try {
                    items = Ig_Json_Model.parsingLocations(results.getResultlist());
                    if (items == null || items.isEmpty()) {
                    } else {

                        for (Locations locations : items) {
                            locationstr += locations.location + ",=";
                        }
                        locationstr = locationstr.substring(0, locationstr.length() - 2);
                        getINVENTORYData();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(String error) {
                refresh_layout.setRefreshing(false);
                nodatalayout.setVisibility(View.VISIBLE);
            }
        });
    }


    /**
     * 根据所属库房查询备件
     */
    private void getINVENTORYData() {
        HttpManager.getDataPagingInfo(this, HttpManager.getINVENTORYUrl(searchText, locationstr, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                Log.i(TAG, "data=" + results);
                ArrayList<Xzwl> item = JsonUtils.parsingXzwl(Xzwl_Activity.this, results.getResultlist());
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
                if (item == null || item.isEmpty()) {
                    nodatalayout.setVisibility(View.VISIBLE);
                } else {

                    if (item != null || item.size() != 0) {
                        if (page == 1) {
                            items = new ArrayList<Xzwl>();
                            initAdapter(items);
                        }
                        for (int i = 0; i < item.size(); i++) {
                            items.add(item.get(i));
                        }
                        addData(item);
                        xzwlListAdapter.notifyDataSetChanged();
                    }
                    nodatalayout.setVisibility(View.GONE);

//                    initAdapter(items);
                }
            }

            @Override
            public void onFailure(String error) {
                refresh_layout.setRefreshing(false);
                nodatalayout.setVisibility(View.VISIBLE);
            }
        });
    }


    /**
     * 获取数据*
     */
    private void initAdapter(final List<Xzwl> list) {
        xzwlListAdapter = new XzwlListAdapter(Xzwl_Activity.this, R.layout.xzwl_list_item, list);
        recyclerView.setAdapter(xzwlListAdapter);



    }

    /**
     * 添加数据*
     */
    private void addData(final List<Xzwl> list) {
        xzwlListAdapter.addData(list);
    }


}
