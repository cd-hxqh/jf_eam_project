package com.jf_eam_project.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Uditemreqline;
import com.jf_eam_project.ui.activity.UditemreqLine_Detail_activity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/11/26.
 * 物资编码申请行表
 */
public class UditemreqlineListAdapter extends RecyclerView.Adapter<UditemreqlineListAdapter.ViewHolder> {
    Context mContext;
    List<Uditemreqline> udbrList = new ArrayList<>();


    public UditemreqlineListAdapter(Context context) {

        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Uditemreqline uditemreqline = udbrList.get(position);
        holder.itemNumTitle.setText("序号:");
        holder.itemDescTitle.setText("名称:");
        holder.itemNum.setText(uditemreqline.udlinenum);
        holder.itemDesc.setText(uditemreqline.name);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, UditemreqLine_Detail_activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("uditemreqLine", uditemreqline);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return udbrList.size();
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
        /**
         * 选择*
         */
        private CheckBox checkBox;
        /**
         * 更多*
         */
        private ImageView item_more;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_container);

            itemNumTitle = (TextView) view.findViewById(R.id.item_num_title);
            itemDescTitle = (TextView) view.findViewById(R.id.item_desc_title);


            itemNum = (TextView) view.findViewById(R.id.item_num_text);
            itemDesc = (TextView) view.findViewById(R.id.item_desc_text);
            checkBox = (CheckBox) view.findViewById(R.id.checkbox_id);
            item_more = (ImageView) view.findViewById(R.id.avatar);
        }
    }

    public void update(ArrayList<Uditemreqline> data, boolean merge) {
        if (merge && udbrList.size() > 0) {
            for (int i = 0; i < udbrList.size(); i++) {
                Uditemreqline uditemreqline = udbrList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == uditemreqline) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(uditemreqline);
            }
        }
        udbrList = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<Uditemreqline> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!udbrList.contains(data.get(i))) {
                    udbrList.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }

    public void removeAllData() {
        if (udbrList.size() > 0) {
            udbrList.removeAll(udbrList);
        }
    }





}
