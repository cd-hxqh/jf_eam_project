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
import com.jf_eam_project.model.Wpmaterial;
import com.jf_eam_project.model.Wpservice;
import com.jf_eam_project.ui.activity.WpserviceDetailsActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/12/4.
 * 计划服务
 */
public class WpserviceAdapter extends RecyclerView.Adapter<WpserviceAdapter.ViewHolder> {
    Context mContext;
    List<Wpservice> wpserviceList = new ArrayList<>();
    public WpserviceAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Wpservice wpservice = wpserviceList.get(position);
        holder.itemNumTitle.setText(mContext.getString(R.string.item_num_title));
        holder.itemDescTitle.setText(mContext.getString(R.string.item_desc_title));
        holder.itemNum.setText(wpservice.taskid);
        holder.itemDesc.setText(wpservice.linetype);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WpserviceDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("wpservice", wpservice);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wpserviceList.size();
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

    public void update(ArrayList<Wpservice> data, boolean merge) {
        if (merge && wpserviceList.size() > 0) {
            for (int i = 0; i < wpserviceList.size(); i++) {
                Wpservice wpservice = wpserviceList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == wpservice) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(wpservice);
            }
        }
        wpserviceList = data;
        notifyDataSetChanged();
    }
//
    public void adddate(ArrayList<Wpservice> data){
        if(data.size()>0){
            for(int i = 0;i < data.size();i++){
                wpserviceList.add(data.get(i));
            }
        }
        notifyDataSetChanged();
    }
}
