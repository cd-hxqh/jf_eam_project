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
import com.jf_eam_project.model.Labtrans;
import com.jf_eam_project.ui.activity.LabtransDetailsActivity;
import com.jf_eam_project.ui.activity.Work_LabtransActivity;
import com.jf_eam_project.ui.activity.WplaborDetailsActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/12/18.
 * 实际员工
 */
public class LabtransAdapter extends RecyclerView.Adapter<LabtransAdapter.ViewHolder> {
    Work_LabtransActivity mContext;
    public List<Labtrans> labtransList = new ArrayList<>();

    public LabtransAdapter(Work_LabtransActivity context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Labtrans labtrans = labtransList.get(position);
        holder.itemNumTitle.setText("员工:");
        holder.itemDescTitle.setText("开始日期:");
        holder.itemNum.setText(labtrans.laborcode);
        holder.itemDesc.setText(labtrans.startdate);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LabtransDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("labtrans", labtrans);
                bundle.putSerializable("position", position);
                intent.putExtras(bundle);
                mContext.startActivityForResult(intent,0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return labtransList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout relativeLayout;
        /**
         * CardView*
         */
        public CardView cardView;
        /**
         * 编号*
         */
        public TextView itemNumTitle;
        /**
         * 描述*
         */
        public TextView itemDescTitle;
        /**
         * 编号内容*
         */
        public TextView itemNum;
        /**
         * 描述内容*
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

    public void update(ArrayList<Labtrans> data, boolean merge) {
        if (merge && labtransList.size() > 0) {
            for (int i = 0; i < labtransList.size(); i++) {
                Labtrans labtrans = labtransList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == labtrans) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(labtrans);
            }
        }
        labtransList = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<Labtrans> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                labtransList.add(data.get(i));
            }
        }
        notifyDataSetChanged();
    }

    public void adddate(Labtrans data) {
        labtransList.add(data);
        notifyDataSetChanged();
    }
}
