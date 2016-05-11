package com.jf_eam_project.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.PRLine;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.ui.activity.PRLine_Details_Activity;
import com.jf_eam_project.ui.activity.Udinspoasset_Details_Activity;
import com.jf_eam_project.ui.activity.Udinspojxxm_Activity;

import java.util.ArrayList;
import java.util.List;


/**
 * 设备备件
 */
public class UdinspoassetListAdapter extends RecyclerView.Adapter<UdinspoassetListAdapter.ViewHolder> {
    Context mContext;
    List<Udinspoasset> udinspoassetList = new ArrayList<>();
    /**
     * 分公司*
     */
    private String branch;
    /**
     * 运行单位*
     */
    private String udbelong;

    public UdinspoassetListAdapter(Context context) {
        this.mContext = context;
    }


    public void setData(String branch, String udbelong) {
        this.branch = branch;
        this.udbelong = udbelong;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Udinspoasset udinspoasset = udinspoassetList.get(position);
        holder.itemNumTitle.setText(mContext.getString(R.string.udinspoasset_udinspoassetlinenum_title));
        holder.itemDescTitle.setText(mContext.getString(R.string.udinspojxxm_desciption_text));
        holder.itemNum.setText(udinspoasset.udinspoassetlinenum);
        holder.itemDesc.setText(udinspoasset.childassetnum);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(mContext, Udinspojxxm_Activity.class);
                intent.putExtra("udinspoassetnum", udinspoasset.udinspoassetnum);
                intent.putExtra("branch", branch);
                intent.putExtra("udbelong", udbelong);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return udinspoassetList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout relativeLayout;
        /**
         * CardView*
         */
        public CardView cardView;
        /**
         * 编号名称*
         */
        public TextView itemNumTitle;
        /**
         * 描述名称*
         */
        public TextView itemDescTitle;
        /**
         * 编号*
         */
        public TextView itemNum;
        /**
         * 描述*
         */
        public TextView itemDesc;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_container);

            itemNumTitle = (TextView) view.findViewById(R.id.item_num_title);
            itemDescTitle = (TextView) view.findViewById(R.id.item_desc_title);


            itemNum = (TextView) view.findViewById(R.id.item_num_text);
            itemDesc = (TextView) view.findViewById(R.id.item_desc_text);
        }
    }

    public void update(ArrayList<Udinspoasset> data, boolean merge) {
        if (merge && udinspoassetList.size() > 0) {
            for (int i = 0; i < udinspoassetList.size(); i++) {
                Udinspoasset udinspoasset = udinspoassetList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j).udinspoassetlinenum == udinspoasset.udinspoassetlinenum) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(udinspoasset);
            }
        }
        udinspoassetList = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<Udinspoasset> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!udinspoassetList.contains(data.get(i))) {
                    udinspoassetList.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }


    public void removeAllData() {
        if (udinspoassetList.size() > 0) {
            udinspoassetList.removeAll(udinspoassetList);
        }
    }
}
