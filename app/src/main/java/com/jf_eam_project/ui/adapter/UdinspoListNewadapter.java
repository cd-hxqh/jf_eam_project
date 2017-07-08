package com.jf_eam_project.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jf_eam_project.Dao.UdinspoAssetDao;
import com.jf_eam_project.Dao.UdinspoDao;
import com.jf_eam_project.Dao.UdinspojxxmDao;
import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.model.Udinspo;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.model.Udinspojxxm;
import com.jf_eam_project.utils.MessageUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by think on 2015/11/26.
 * 巡检单
 */
public class UdinspoListNewadapter extends RecyclerView.Adapter<UdinspoListNewadapter.ViewHolder> {
    private static final String TAG = "UdinspoListNewadapter";
    Context mContext;
    List<Udinspo> udinspoList = new ArrayList<>();


    public UdinspoListNewadapter(Context context) {

        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.udinspo_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.bindData(udinspoList.get(position));

    }

    @Override
    public int getItemCount() {
        return udinspoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_num_text)
        TextView itemNum;
        @Bind(R.id.item_desc_text)
        TextView itemDesc;//描述
        @Bind(R.id.bt_action)
        Button actionBtn;
        @Bind(R.id.tv_status)
        TextView statusTextView;
        @Bind(R.id.pb)
        ProgressBar progressBar;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @SuppressWarnings("unchecked")
        public void bindData(final Udinspo udinspo) {
            itemNum.setText(udinspo.insponum);
            itemDesc.setText(udinspo.description);
            if (isExists(udinspo.insponum, udinspo.inspotype)) {
//                actionBtn.setClickable(false);
//                actionBtn.setFocusable(false);
                statusTextView.setText("下载完成");
                statusTextView.setTextColor(mContext.getResources().getColor(R.color.press_button_color));
            }


            actionBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (statusTextView.getText().toString().equals("下载完成")) {
                        MessageUtils.showMiddleToast(mContext, "该任务已下载,不能重复下载");
                    } else {
                        statusTextView.setText("正在下载");
                        progressBar.setVisibility(View.VISIBLE);
                        actionBtn.setClickable(false);
                        actionBtn.setFocusable(false);
                        getUdinspoassetData(progressBar, statusTextView, udinspo.insponum, udinspo);
                    }


                }
            });


        }


        /**
         * 判断数据是否下载*
         */
        private boolean isExists(String insponum, String inspotype) {


            List<Udinspo> list = new UdinspoDao(mContext).findByInspotype(inspotype);
            if (list != null && list.size() != 0) {
                for (Udinspo udinspo : list) {
                    if (udinspo.getInsponum().equals(insponum))
                        return true;
                }
            }

            return false;
        }


        //根据主表ID获取子表数据
        private void getUdinspoassetData(final ProgressBar p, final TextView statusTextView, final String insponum, final Udinspo udinspo) {
            HttpManager.getData(mContext, HttpManager.getUdinspoasseturl1(insponum), new HttpRequestHandler<String>() {
                @Override
                public void onSuccess(String data) {

                    ArrayList<Udinspoasset> items = null;

                    try {
                        items = Ig_Json_Model.parseUdinspoassetString(data);
                        if (items == null || items.isEmpty()) {
                           MessageUtils.showMiddleToast(mContext,"下载失败，没有定（巡）检标准可以下载，请重新创建");
                            p.setVisibility(View.GONE);
                            statusTextView.setText("下载失败");
                            statusTextView.setTextColor(mContext.getResources().getColor(R.color.holo_red_light));
                        } else {

                            String udinspoassetnum = "";
                            for (Udinspoasset udinspoasset : items) {
                                udinspoassetnum += "=" + udinspoasset.udinspoassetnum + ",";

                            }
                            udinspoassetnum = udinspoassetnum.substring(0, udinspoassetnum.length() - 1);

                            getUdinspojxxmData1(p, statusTextView, udinspoassetnum, udinspo, items);

                        }
                    } catch (IOException e) {
                    }
                }

                @Override
                public void onSuccess(String data, int totalPages, int currentPage) {
                }

                @Override
                public void onFailure(String error) {
                }
            });
        }


        /**
         * 根据 Udinspoasset udinspoassetnum获取Udinspojxxm的信息
         * 孙表
         */
        private void getUdinspojxxmData1(final ProgressBar p, final TextView statusTextView, final String udinspoassetnum, final Udinspo udinspo, final ArrayList<Udinspoasset> udinspoassets) {
            HttpManager.getData(mContext, HttpManager.getUdinspojxxmUrl1(udinspoassetnum), new HttpRequestHandler<String>() {
                @Override
                public void onSuccess(String data) {

                    ArrayList<Udinspojxxm> items = null;

                    try {
                        items = Ig_Json_Model.parseUdinspojxxmString(data);
                        if (items == null || items.isEmpty()) {
                        } else {
                            p.setVisibility(View.GONE);
                            statusTextView.setText("下载完成");
                            statusTextView.setTextColor(mContext.getResources().getColor(R.color.press_button_color));
                            new UdinspoDao(mContext).deleteInsponum(udinspo.insponum); //删除主表
                            new UdinspoDao(mContext).insert(udinspo); //添加主表
                            new UdinspoAssetDao(mContext).create(udinspoassets); //添加子表
                            new UdinspojxxmDao(mContext).create(items); //添加孙表
                            notifyDataSetChanged();
                        }


                    } catch (IOException e) {
                        p.setVisibility(View.GONE);
                        statusTextView.setText("下载失败");
                        statusTextView.setTextColor(mContext.getResources().getColor(R.color.holo_red_light));
                    }
                }

                @Override
                public void onSuccess(String data, int totalPages, int currentPage) {

                }

                @Override
                public void onFailure(String error) {
                    p.setVisibility(View.GONE);
                    statusTextView.setText("下载失败");
                    statusTextView.setTextColor(mContext.getResources().getColor(R.color.holo_red_light));
                }
            });
        }
    }


    public void update(ArrayList<Udinspo> data, boolean merge) {
        if (merge && udinspoList.size() > 0) {
            for (int i = 0; i < udinspoList.size(); i++) {
                Udinspo udinspo = udinspoList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j).insponum == udinspo.insponum) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(udinspo);
            }
        }
        udinspoList = data;
        notifyDataSetChanged();
    }

    public void adddate(ArrayList<Udinspo> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!udinspoList.contains(data.get(i))) {
                    udinspoList.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }


    public void removeAllData() {
        if (udinspoList.size() > 0) {
            udinspoList.removeAll(udinspoList);
        }
    }


}
