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
import com.jf_eam_project.model.Udbr;
import com.jf_eam_project.model.Udbrline;
import com.jf_eam_project.ui.activity.UdbrLine_Details_Activity;
import com.jf_eam_project.ui.activity.Udbr_Details_Activity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/11/26.
 * 物资借用归还
 */
public class UdbrLineListAdapter extends RecyclerView.Adapter<UdbrLineListAdapter.ViewHolder> {
    Context mContext;
    List<Udbrline> udbrList = new ArrayList<>();


    public UdbrLineListAdapter(Context context) {

        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Udbrline udbrline = udbrList.get(position);
        holder.itemNumTitle.setText(mContext.getString(R.string.udbrlinenum_text));
        holder.itemDescTitle.setText(mContext.getString(R.string.fromstoreloc_text));
        holder.itemNum.setText(udbrline.udbrlinenum);
        holder.itemDesc.setText(udbrline.fromstoreloc);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, UdbrLine_Details_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("udbrline", udbrline);
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

    public void update(ArrayList<Udbrline> data, boolean merge) {
        if (merge && udbrList.size() > 0) {
            for (int i = 0; i < udbrList.size(); i++) {
                Udbrline udbrline = udbrList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == udbrline) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(udbrline);
            }
        }
        udbrList = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<Udbrline> data) {
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
