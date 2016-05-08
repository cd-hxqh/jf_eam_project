package com.jf_eam_project.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.jf_eam_project.R;
import com.jf_eam_project.ui.activity.Udinspojxxm_Details_Activity;
import com.jf_eam_project.utils.Bimp;
import com.jf_eam_project.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GridAdapter extends BaseAdapter {
    private static final String TAG = "GridAdapter";
    private LayoutInflater inflater; // 视图容器
    private int selectedPosition = -1;// 选中的位置
    private boolean shape;
    private Context context;
    /**
     * 文件存放的路径*
     */
    private List<String> drr = new ArrayList<String>();


    public boolean isShape() {
        return shape;
    }

    public void setShape(boolean shape) {
        this.shape = shape;
    }

    public GridAdapter(Context context, List<String> drr) {
        this.context = context;
        this.drr = drr;
        inflater = LayoutInflater.from(context);
    }

    public void update() {
        loading();
    }

    public int getCount() {
        return (drr.size() + 1);
    }

    public Object getItem(int arg0) {

        return null;
    }

    public long getItemId(int arg0) {

        return 0;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    /**
     * ListView Item设置
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.item_published_grida,
                    parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView
                    .findViewById(R.id.item_grida_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        if (position == drr.size()) {
            holder.image.setImageBitmap(BitmapFactory.decodeResource(
                    context.getResources(), R.drawable.icon_addpic_unfocused));
            if (position == 9) {
                holder.image.setVisibility(View.GONE);
            }
        } else {
//            holder.image.setImageBitmap(Udinspojxxm_Details_Activity.bmp.get(position));
            holder.image.setImageBitmap(getBimap(drr.get(position)));
        }

        return convertView;
    }

    public class ViewHolder {
        public ImageView image;
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public void loading() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if (Udinspojxxm_Details_Activity.max == drr.size()) {
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                        break;
                    } else {
                        try {
                            String path = drr.get(Udinspojxxm_Details_Activity.max);
                            Bitmap bm = Bimp.revitionImageSize(path);
                            Udinspojxxm_Details_Activity.bmp.add(bm);
                            String newStr = path.substring(
                                    path.lastIndexOf("/") + 1,
                                    path.lastIndexOf("."));
                            FileUtils.saveBitmap(bm, "" + newStr);
                            Udinspojxxm_Details_Activity.max += 1;
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }


    /**
     * 根据路径获取bmp对象*
     */
    private Bitmap getBimap(String path) {
        Bitmap bm = null;
        try {
            bm = Bimp.revitionImageSize(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bm;

    }

}