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
import com.jf_eam_project.model.Wptool;
import com.jf_eam_project.ui.activity.WptoolDetailsActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/12/4.
 * 计划服务
 */
public class WptoolAdapter extends RecyclerView.Adapter<WptoolAdapter.ViewHolder> {
    Context mContext;
    List<Wptool> wptoolList = new ArrayList<>();
    public WptoolAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Wptool wptool = wptoolList.get(position);
        holder.itemNumTitle.setText(mContext.getString(R.string.item_num_title));
        holder.itemDescTitle.setText(mContext.getString(R.string.item_desc_title));
        holder.itemNum.setText(wptool.taskid);
        holder.itemDesc.setText(wptool.itemnum);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WptoolDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("wptool", wptool);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wptoolList.size();
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

            itemNumTitle=(TextView) view.findViewById(R.id.item_num_title);
            itemDescTitle=(TextView) view.findViewById(R.id.item_desc_title);


            itemNum = (TextView) view.findViewById(R.id.item_num_text);
            itemDesc = (TextView) view.findViewById(R.id.item_desc_text);
        }
    }

    public void update(ArrayList<Wptool> data, boolean merge) {
        if (merge && wptoolList.size() > 0) {
            for (int i = 0; i < wptoolList.size(); i++) {
                Wptool wptool = wptoolList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == wptool) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(wptool);
            }
        }
        wptoolList = data;
        notifyDataSetChanged();
    }
//
    public void adddate(ArrayList<Wptool> data){
        if(data.size()>0){
            for(int i = 0;i < data.size();i++){
                wptoolList.add(data.get(i));
            }
        }
        notifyDataSetChanged();
    }
}
