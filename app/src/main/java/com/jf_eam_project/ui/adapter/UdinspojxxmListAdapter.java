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
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.model.Udinspojxxm;
import com.jf_eam_project.ui.activity.Udinspoasset_Details_Activity;
import com.jf_eam_project.ui.activity.Udinspojxxm_Details_Activity;

import java.util.ArrayList;
import java.util.List;


/**
 * 巡检项目标准
 */
public class UdinspojxxmListAdapter extends RecyclerView.Adapter<UdinspojxxmListAdapter.ViewHolder> {
    Context mContext;
    List<Udinspojxxm> udinspojxxmList = new ArrayList<>();

    public UdinspojxxmListAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Udinspojxxm udinspojxxm = udinspojxxmList.get(position);
        holder.itemNumTitle.setText(mContext.getString(R.string.udinspoasset_udinspoassetlinenum_title));
        holder.itemDescTitle.setText(mContext.getString(R.string.ud_description_title));
        holder.itemNum.setText(udinspojxxm.udinspojxxmlinenum);
        holder.itemDesc.setText(udinspojxxm.description);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Udinspojxxm_Details_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Udinspojxxm", udinspojxxm);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return udinspojxxmList.size();
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

    public void update(ArrayList<Udinspojxxm> data, boolean merge) {
        if (merge && udinspojxxmList.size() > 0) {
            for (int i = 0; i < udinspojxxmList.size(); i++) {
                Udinspojxxm udinspojxxm = udinspojxxmList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j).udinspojxxmlinenum == udinspojxxm.udinspojxxmlinenum) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(udinspojxxm);
            }
        }
        udinspojxxmList = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<Udinspojxxm> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!udinspojxxmList.contains(data.get(i))) {
                    udinspojxxmList.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }


    public void removeAllData() {
        if (udinspojxxmList.size() > 0) {
            udinspojxxmList.removeAll(udinspojxxmList);  
        }
    }
}
