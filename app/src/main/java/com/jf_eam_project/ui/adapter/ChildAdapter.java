package com.jf_eam_project.ui.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.jf_eam_project.R;
import com.jf_eam_project.imagetools.LocalImageView;
import com.jf_eam_project.utils.Bimp;
import com.jf_eam_project.utils.ImageDownLoader;

@SuppressWarnings("unused")
@SuppressLint("UseSparseArrays")
public class ChildAdapter extends BaseAdapter {
    private static final String TAG = "ChildAdapter";
    private Point mPoint = new Point(0, 0);//用来封装ImageView的宽和高的对象
    /**
     * 用来存储图片的选中情况
     */
    private GridView mGridView;
    private LinkedList<String> list;
    private Context context;
    protected LayoutInflater mInflater;
    private TextCallback textcallback = null;
    private ListCallback mListCallback = null;
    private List<String> mPathList;

    private HashMap<String, Boolean> mStateHashMap;
    private int intCount = 1;

    public LinkedList<String> getList() {
        return list;
    }

    public void setList(LinkedList<String> list) {
        this.list = list;
    }

    public ChildAdapter(Context context, LinkedList<String> list, GridView mGridView) {
        this.list = list;
        this.mGridView = mGridView;
        this.context = context;
        mInflater = LayoutInflater.from(context);
        //		mHashMap = new HashMap<String, View>();
        mPathList = new ArrayList<String>();
        mStateHashMap = new HashMap<String, Boolean>();
        //		selected=new HashMap<Integer,Integer>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        final ViewHolder viewHolder;
        final String path = list.get(position);

        Log.i(TAG, "path=" + path);
        View rootView = convertView;
        if (rootView == null) {

            viewHolder = new ViewHolder();
            rootView = mInflater.inflate(R.layout.grid_child_item, null);
            viewHolder.mImageView = (LocalImageView) rootView.findViewById(R.id.child_image);
            viewHolder.mCheckBox = (ImageView) rootView.findViewById(R.id.child_checkbox);
            viewHolder.view = rootView.findViewById(R.id.view_ImageView_up);

            //用来监听ImageView的宽和高
            viewHolder.mImageView.setOnMeasureListener(new LocalImageView.OnMeasureListener() {

                @Override
                public void onMeasureSize(int width, int height) {
                    mPoint.set(width, height);
                }
            });
            rootView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rootView.getTag();
        }

        if (!"".equals(path)) {
            viewHolder.mImageView.setVisibility(View.VISIBLE);
            viewHolder.mCheckBox.setVisibility(View.VISIBLE);

            if (Bimp.mHashMap.containsKey(path)) {
                viewHolder.mCheckBox.setImageResource(R.drawable.btn_check_on);
                viewHolder.view.setVisibility(View.VISIBLE);
            } else {
                viewHolder.mCheckBox.setImageResource(R.drawable.btn_check_on_disable);
                viewHolder.view.setVisibility(View.GONE);
            }
            viewHolder.mCheckBox.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (intCount % 2 != 0 && !Bimp.mHashMap.containsKey(path)) {
                        if (Bimp.mHashMap.size() == (20 - Bimp.drr.size())) {
                            Toast.makeText(context, "您最多只能选择" + (20 - Bimp.drr.size()) + "张", Toast.LENGTH_SHORT).show();

                            viewHolder.mCheckBox.setImageResource(R.drawable.btn_check_on_disable);
                            viewHolder.view.setVisibility(View.GONE);
                        } else {
                            intCount++;
                            Bimp.mHashMap.put(path, true);
                            viewHolder.mCheckBox.setImageResource(R.drawable.btn_check_on);
                            viewHolder.view.setVisibility(View.VISIBLE);
                            mPathList.add(path);
                        }
                    } else if (intCount % 2 == 0 && Bimp.mHashMap.containsKey(path)) {
                        Bimp.mHashMap.remove(path);
                        viewHolder.mCheckBox.setImageResource(R.drawable.btn_check_on_disable);
                        viewHolder.view.setVisibility(View.GONE);
                        mPathList.remove(path);
                    } else {
                        if (Bimp.mHashMap.size() == (20 - Bimp.drr.size())) {

                            Toast.makeText(context, "您最多只能选择" + (20 - Bimp.drr.size()) + "张", Toast.LENGTH_SHORT).show();
                            viewHolder.mCheckBox.setImageResource(R.drawable.btn_check_on_disable);
                            viewHolder.view.setVisibility(View.GONE);
                        } else {
                            Bimp.mHashMap.put(path, true);
                            viewHolder.mCheckBox.setImageResource(R.drawable.btn_check_on);
                            viewHolder.view.setVisibility(View.VISIBLE);
                            mPathList.add(path);
                        }
                    }
                    mListCallback.onListener(mPathList, path);
                    mPathList.clear();
                    if ((20 - Bimp.drr.size()) > 0) {
                        if (textcallback != null) {
                            textcallback.onListen(Bimp.mHashMap.size());
                        }
                    } else {
                        if (textcallback != null) {
                            textcallback.onListen(Bimp.mHashMap.size());
                        }
                    }
                }
            });

            ImageDownLoader.showLocationImage
                    (path, viewHolder.mImageView, R.mipmap.ic_launcher);
        } else {
            viewHolder.mImageView.setVisibility(View.GONE);
            viewHolder.mCheckBox.setVisibility(View.GONE);
        }
        return rootView;
    }


    public static class ViewHolder {
        public ImageView mCheckBox;
        public LocalImageView mImageView;
        private View view;
    }

    public interface TextCallback {
        public void onListen(int count);
    }

    public void setTextCallback(TextCallback listener) {
        textcallback = listener;
    }

    public interface ListCallback {
        public void onListener(List<String> path, String pathStr);
    }

    public void setListCallback(ListCallback callback) {
        this.mListCallback = callback;
    }

}
