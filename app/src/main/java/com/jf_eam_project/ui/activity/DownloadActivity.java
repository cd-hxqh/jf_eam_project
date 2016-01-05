package com.jf_eam_project.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jf_eam_project.Dao.AssetDao;
import com.jf_eam_project.Dao.CraftrateDao;
import com.jf_eam_project.Dao.FailurecodeDao;
import com.jf_eam_project.Dao.FailurelistDao;
import com.jf_eam_project.Dao.ItemDao;
import com.jf_eam_project.Dao.JobplanDao;
import com.jf_eam_project.Dao.LaborDao;
import com.jf_eam_project.Dao.LocationDao;
import com.jf_eam_project.Dao.PersonDao;
import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Assets;
import com.jf_eam_project.model.Craftrate;
import com.jf_eam_project.model.Failurecode;
import com.jf_eam_project.model.Failurelist;
import com.jf_eam_project.model.Item;
import com.jf_eam_project.model.Jobplan;
import com.jf_eam_project.model.Labor;
import com.jf_eam_project.model.Location;
import com.jf_eam_project.model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2015/12/25.
 * 基础数据下载页面
 */
public class DownloadActivity extends BaseActivity{

    private static final String TAG="DownloadActivity";
    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;
    private ExpandableListView expandableListView;
    //标题
    List<String> groupArray = new ArrayList<String>();
    //子标题
    List<List<String>> childArray = new ArrayList<List<String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        findViewById();
        initView();

    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        expandableListView = (ExpandableListView) findViewById(R.id.expendlist);
    }

    @Override
    protected void initView() {
        titleView.setText(getResources().getString(R.string.data_download));
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        groupArray.add("工单");
        groupArray.add("巡检");
        List<String> tempArray01 = new ArrayList<String>();
        tempArray01.add("位置");
        tempArray01.add("资产");
        tempArray01.add("故障类");
        tempArray01.add("问题代码");
        tempArray01.add("作业计划");
        tempArray01.add("人员");
        tempArray01.add("员工");
        tempArray01.add("工种");
        tempArray01.add("项目");

        List<String> tempArray02 = new ArrayList<String>();
        tempArray02.add("巡检单类型");
        tempArray02.add("设备");

        childArray.add(tempArray01);
        childArray.add(tempArray02);

        expandableListView.setAdapter(new MyExpandableListViewAdapter(this));
    }

    class MyExpandableListViewAdapter extends BaseExpandableListAdapter {

        private Context context;

        public MyExpandableListViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getGroupCount() {
            return groupArray.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return childArray.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupArray.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childArray.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }


        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            GroupHolder groupHolder = null;
            if (convertView == null) {
                convertView = getLayoutInflater().from(context).inflate(
                        R.layout.list_group, null);
                groupHolder = new GroupHolder();
                groupHolder.groupText = (TextView) convertView.findViewById(R.id.group_text);
                groupHolder.downAll = (Button) convertView.findViewById(R.id.download_all);
                convertView.setTag(groupHolder);
            } else {
                groupHolder = (GroupHolder) convertView.getTag();
            }
            groupHolder.groupText.setText(groupArray.get(groupPosition));
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            ItemHolder itemHolder = null;
            if (convertView == null) {
                convertView = getLayoutInflater().from(context).inflate(
                        R.layout.list_child, null);
                itemHolder = new ItemHolder();
                itemHolder.childText = (TextView) convertView.findViewById(R.id.child_text);
                itemHolder.down = (Button) convertView.findViewById(R.id.download);
                convertView.setTag(itemHolder);
            } else {
                itemHolder = (ItemHolder) convertView.getTag();
            }
            itemHolder.childText.setText(childArray.get(groupPosition).get(
                    childPosition));
            itemHolder.down.setOnClickListener(new DownloadOnclickListener(groupPosition,childPosition,itemHolder.down));
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }

    class GroupHolder {
        public TextView groupText;
        public Button downAll;
    }

    class ItemHolder {
        public Button down;
        public TextView childText;
    }

    private class DownloadOnclickListener implements View.OnClickListener {
        int group;
        int child;
        Button button;
        private DownloadOnclickListener(int group,int child,Button button){
            this.group = group;
            this.child = child;
            this.button = button;
        }
        @Override
        public void onClick(View view) {
            String buttonText = childArray.get(group).get(child);
            if(buttonText.equals(childArray.get(0).get(0))){//位置
                downloaddata(HttpManager.getUrl(Constants.LOCATION_APPID,Constants.LOCATION_NAME),buttonText,button);
            }else if(buttonText.equals(childArray.get(0).get(1))){//资产
                downloaddata(HttpManager.getUrl(Constants.ASSET_APPID,Constants.ASSET_NAME),buttonText,button);
            }else if(buttonText.equals(childArray.get(0).get(2))){//故障类
                downloaddata(HttpManager.getUrl(Constants.UDWOCM_APPID,Constants.FAILURECODE_NAME),buttonText,button);
            }else if(buttonText.equals(childArray.get(0).get(3))){//问题代码
                downloaddata(HttpManager.getUrl(Constants.UDWOCM_APPID,Constants.FAILURELIST_NAME),buttonText,button);
            }else if(buttonText.equals(childArray.get(0).get(4))){//作业计划
                downloaddata(HttpManager.getUrl(Constants.UDWOCM_APPID,Constants.JOBPLAN_NAME),buttonText,button);
            }else if(buttonText.equals(childArray.get(0).get(5))){//人员
                downloaddata(HttpManager.getUrl(Constants.PERSON_APPID,Constants.PERSON_NAME),buttonText,button);
            }else if(buttonText.equals(childArray.get(0).get(6))){//员工
                downloaddata(HttpManager.getUrl(Constants.LABOR_APPID,Constants.LABOR_NAME),buttonText,button);
            }else if(buttonText.equals(childArray.get(0).get(7))){//工种
                downloaddata(HttpManager.getUrl(Constants.CRAFTRATE_APPID,Constants.CRAFTRATE_NAME),buttonText,button);
            }else if(buttonText.equals(childArray.get(0).get(8))){//项目
                downloaddata(HttpManager.getUrl(Constants.ITEM_APPID,Constants.ITEM_NAME),buttonText,button);
            }
        }
    }

    private void downloaddata(String url, final String buttonText, final Button button){
        HttpManager.getData(DownloadActivity.this, url, new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {

                Log.i(TAG,"data="+data);
                if(data != null) {
                    try {
                        if(buttonText.equals(childArray.get(0).get(0))) {//位置
                            Toast.makeText(DownloadActivity.this,"位置已下载",Toast.LENGTH_SHORT).show();
                            List<Location> locations = Ig_Json_Model.parsingLocation(data);
                            Toast.makeText(DownloadActivity.this,"位置已解析",Toast.LENGTH_SHORT).show();
                            new LocationDao(DownloadActivity.this).create(locations);
                        }else if(buttonText.equals(childArray.get(0).get(1))){//资产
                            List<Assets> assets = Ig_Json_Model.parsingAsset(data);
                            new AssetDao(DownloadActivity.this).create(assets);
                        }else if(buttonText.equals(childArray.get(0).get(2))){//故障类
                            List<Failurecode> failurecodes = Ig_Json_Model.parsingFailurecode(data);
                            new FailurecodeDao(DownloadActivity.this).create(failurecodes);
                        }else if(buttonText.equals(childArray.get(0).get(3))){//问题代码
                            List<Failurelist> failurelists = Ig_Json_Model.parsingFailurelist(data);
                            new FailurelistDao(DownloadActivity.this).create(failurelists);
                        }else if(buttonText.equals(childArray.get(0).get(4))){//作业计划
                            List<Jobplan> jobplans = Ig_Json_Model.parsingJobplan(data);
                            new JobplanDao(DownloadActivity.this).create(jobplans);
                        }else if(buttonText.equals(childArray.get(0).get(5))){//人员
                            List<Person> jobplans = Ig_Json_Model.parsingPerson(data);
                            new PersonDao(DownloadActivity.this).create(jobplans);
                        }else if(buttonText.equals(childArray.get(0).get(6))){//员工
                            List<Labor> jobplans = Ig_Json_Model.parsingLabor(data);
                            new LaborDao(DownloadActivity.this).create(jobplans);
                        }else if(buttonText.equals(childArray.get(0).get(7))){//工种
                            List<Craftrate> craftrates = Ig_Json_Model.parsingCraftrate(data);
                            new CraftrateDao(DownloadActivity.this).create(craftrates);
                        }else if(buttonText.equals(childArray.get(0).get(8))){//项目
                            List<Item> craftrates = Ig_Json_Model.parsingItem(data);
                            new ItemDao(DownloadActivity.this).create(craftrates);
                        }
                        button.setText(getResources().getString(R.string.downloaded));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(DownloadActivity.this,"下载数据出现问题",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {

            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(DownloadActivity.this,"下载失败",Toast.LENGTH_SHORT).show();
            }

        });
    }

}
