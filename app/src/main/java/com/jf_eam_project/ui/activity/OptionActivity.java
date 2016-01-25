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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jf_eam_project.Dao.AssetDao;
import com.jf_eam_project.Dao.CraftrateDao;
import com.jf_eam_project.Dao.FailurecodeDao;
import com.jf_eam_project.Dao.FailurelistDao;
import com.jf_eam_project.Dao.ItemDao;
import com.jf_eam_project.Dao.JobplanDao;
import com.jf_eam_project.Dao.LaborcraftrateDao;
import com.jf_eam_project.Dao.LocationDao;
import com.jf_eam_project.Dao.PersonDao;
import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Assets;
import com.jf_eam_project.model.Craftrate;
import com.jf_eam_project.model.Failurecode;
import com.jf_eam_project.model.Failurelist;
import com.jf_eam_project.model.Item;
import com.jf_eam_project.model.Jobplan;
import com.jf_eam_project.model.Laborcraftrate;
import com.jf_eam_project.model.Location;
import com.jf_eam_project.model.Option;
import com.jf_eam_project.model.Person;
import com.jf_eam_project.ui.adapter.OptionAdapter;
import com.jf_eam_project.ui.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2015/12/28.
 * 通用选项查询界面
 */
public class OptionActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static final String TAG = "OptionActivity";
    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 返回*
     */
    private ImageView backImage;

    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private OptionAdapter optionAdapter;
    private EditText search;
    private String searchText = "";
    public int requestCode;
    ArrayList<Option> list;

    private String CraftSearch;

    private SwipeRefreshLayout refresh_layout = null;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        getIntentData();
        findViewById();
        initView();
    }

    private void getIntentData() {
        requestCode = getIntent().getIntExtra("requestCode", 0);
        if (requestCode == Constants.LABORCRAFTRATE) {
            CraftSearch = getIntent().getStringExtra("craft");
        }
        Log.i(TAG, "requestCode=" + requestCode);
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImage = (ImageView) findViewById(R.id.title_back_id);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        search = (EditText) findViewById(R.id.search_edit);
        refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_container);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.activity_option_title);

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        optionAdapter = new OptionAdapter(this);
        recyclerView.setAdapter(optionAdapter);

        refresh_layout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        refresh_layout.setRefreshing(false);
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);
        setSearchEdit();
        getData(searchText);
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
                    ((InputMethodManager) search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    OptionActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString();
                    optionAdapter = new OptionAdapter(OptionActivity.this);
                    recyclerView.setAdapter(optionAdapter);
                    getData(searchText);
                    return true;
                }
                return false;
            }
        });
    }

    private void getData(String searchText) {
        list = new ArrayList<Option>();
        Option option;
        switch (requestCode) {
            case Constants.ASSETCODE:
                List<Assets> assets;
//                if (searchText.equals("")) {
                assets = new AssetDao(OptionActivity.this).queryByCount(page, searchText);
//                } else {
//                    assets = new AssetDao(OptionActivity.this).queryByNum(searchText);
//                }
                for (int i = 0; i < assets.size(); i++) {
                    option = new Option();
                    option.setName(assets.get(i).assetnum);
                    option.setDescription(assets.get(i).description);
                    option.setValue(assets.get(i).location);
                    list.add(option);
                }
                break;
            case Constants.LOCATIONCODE:
                List<Location> locations;
//                if (searchText.equals("")) {
                locations = new LocationDao(OptionActivity.this).queryByCount(page, searchText);
//                } else {
//                    locations = new LocationDao(OptionActivity.this).queryByLocation(searchText);
//                }
                for (int i = 0; i < locations.size(); i++) {
                    option = new Option();
                    option.setName(locations.get(i).location);
                    option.setDescription(locations.get(i).description);
                    list.add(option);
                }
                break;
            case Constants.FAILURECODE:
                List<Failurecode> failurecodes;
//                if (searchText.equals("")) {
                failurecodes = new FailurecodeDao(OptionActivity.this).queryByCount(page, searchText);
//                } else {
//                    failurecodes = new FailurecodeDao(OptionActivity.this).queryByFailurecode(searchText);
//                }
                for (int i = 0; i < failurecodes.size(); i++) {
                    option = new Option();
                    option.setName(failurecodes.get(i).failurecode);
                    option.setDescription(failurecodes.get(i).description);
                    list.add(option);
                }
                break;
            case Constants.FAILURELIST:
                List<Failurelist> failurelists;
//                if (searchText.equals("")) {
                failurelists = new FailurelistDao(OptionActivity.this).queryByCount(page, searchText);
//                } else {
//                    failurelists = new FailurelistDao(OptionActivity.this).queryByFailurecode(searchText);
//                }
                for (int i = 0; i < failurelists.size(); i++) {
                    option = new Option();
                    option.setName(failurelists.get(i).failurecode);
                    option.setDescription(failurelists.get(i).flcdescription);
                    list.add(option);
                }
                break;
            case Constants.JOBPLAN:
                List<Jobplan> jobplans;
//                if (searchText.equals("")) {
                jobplans = new JobplanDao(OptionActivity.this).queryByCount(page, searchText);
//                } else {
//                    jobplans = new JobplanDao(OptionActivity.this).queryByJobplan(searchText);
//                }
                for (int i = 0; i < jobplans.size(); i++) {
                    option = new Option();
                    option.setName(jobplans.get(i).jpnum);
                    option.setDescription(jobplans.get(i).description);
                    list.add(option);
                }
                break;
            case Constants.CRAFTRATE:
                List<Craftrate> craftrates;
//                if (searchText.equals("")) {
                craftrates = new CraftrateDao(OptionActivity.this).queryByCount(page, searchText);
//                } else {
//                    craftrates = new CraftrateDao(OptionActivity.this).queryByCraft(searchText);
//                }
                for (int i = 0; i < craftrates.size(); i++) {
                    option = new Option();
                    option.setName(craftrates.get(i).craft);
                    option.setDescription(craftrates.get(i).skilllevel);
                    list.add(option);
                }
                break;
            case Constants.ITEM:
                List<Item> items;
//                if (searchText.equals("")) {
                items = new ItemDao(OptionActivity.this).queryByCount(page, searchText);
//                } else {
//                    items = new ItemDao(OptionActivity.this).queryByItem(searchText);
//                }
                for (int i = 0; i < items.size(); i++) {
                    option = new Option();
                    option.setName(items.get(i).itemnum);
                    option.setDescription(items.get(i).description);
                    list.add(option);
                }
                break;
            case Constants.LOCATIONSCODE:
                List<Location> locationses;
//                if (searchText.equals("")) {
                locationses = new LocationDao(OptionActivity.this).queryByCountForLocations(page, searchText);
//                } else {
//                    locationses = new LocationDao(OptionActivity.this).queryByLocations(searchText);
//                }
                for (int i = 0; i < locationses.size(); i++) {
                    option = new Option();
                    option.setName(locationses.get(i).location);
                    option.setDescription(locationses.get(i).description);
                    list.add(option);
                }
                break;

            case Constants.PERSON:
                List<Person> persons;
//                if (searchText.equals("")) {
                persons = new PersonDao(OptionActivity.this).queryByCount(page, searchText);
//                } else {
//                    persons = new PersonDao(OptionActivity.this).queryByPerson(searchText);
//                }
                for (int i = 0; i < persons.size(); i++) {
                    option = new Option();
                    option.setName(persons.get(i).personid);
                    option.setDescription(persons.get(i).displayname);
                    list.add(option);
                }

                Log.i(TAG, "这是人员");
                break;
            case Constants.LABORCRAFTRATE:
                List<Laborcraftrate> laborcraftrates;

                if (searchText.equals("") && !CraftSearch.equals("")) {
                    laborcraftrates = new LaborcraftrateDao(OptionActivity.this).queryByCraft(page, searchText, CraftSearch);
                } else {
                    laborcraftrates = new LaborcraftrateDao(OptionActivity.this).queryByCount(page, searchText);
                }
                for (int i = 0; i < laborcraftrates.size(); i++) {
                    option = new Option();
                    option.setName(laborcraftrates.get(i).laborcode);
                    option.setDescription(laborcraftrates.get(i).craft);
                    list.add(option);
                }
                break;
        }
        if (page == 1) {
            optionAdapter = new OptionAdapter(OptionActivity.this);
            recyclerView.setAdapter(optionAdapter);
        }
        optionAdapter.adddate(list);
        if (optionAdapter.getItemCount() == 0) {
            nodatalayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        refresh_layout.setLoading(false);
        refresh_layout.setRefreshing(false);
    }

    public void responseData(Option option) {
        Intent intent = getIntent();
        intent.putExtra("option", option);
        OptionActivity.this.setResult(requestCode, intent);
        finish();
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        page = 1;
        getData(searchText);
    }

    //上拉加载
    @Override
    public void onLoad() {
        page++;
        getData(searchText);
    }
}
