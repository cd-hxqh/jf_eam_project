package com.jf_eam_project.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jf_eam_project.Dao.AssetDao;
import com.jf_eam_project.Dao.LocationDao;
import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Assets;
import com.jf_eam_project.model.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2015/12/25.
 * 基础数据下载页面
 */
public class DownloadActivity extends BaseActivity{
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
        tempArray01.add("任务");
        tempArray01.add("员工");

        List<String> tempArray02 = new ArrayList<String>();
        tempArray02.add("巡检单类型");
        tempArray02.add("设备");

        childArray.add(tempArray01);
        childArray.add(tempArray02);

        expandableListView.setAdapter(new MyExpandableListViewAdapter(this));
        Toast.makeText(DownloadActivity.this,new AssetDao(DownloadActivity.this).queryForAll().size()+"",Toast.LENGTH_SHORT).show();
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
            if(buttonText.equals(childArray.get(0).get(0))){
                downloaddata(HttpManager.getUrl(Constants.LOCATION_APPID,Constants.LOCATION_NAME),buttonText,button);
            }else if(buttonText.equals(childArray.get(0).get(1))){
                downloaddata(HttpManager.getUrl(Constants.ASSET_APPID,Constants.ASSET_NAME),buttonText,button);
            }
        }
    }

    private void downloaddata(String url, final String buttonText, final Button button){
        HttpManager.getData(DownloadActivity.this, url, new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {
                if(data != null) {
                    try {
                        if(buttonText.equals(childArray.get(0).get(0))) {
                            List<Location> locations = Ig_Json_Model.parsingLocation(data);
                            new LocationDao(DownloadActivity.this).create(locations);
                        }else if(buttonText.equals(childArray.get(0).get(1))){
                            List<Assets> assets = Ig_Json_Model.parsingAsset(data);
                            new AssetDao(DownloadActivity.this).create(assets);
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
