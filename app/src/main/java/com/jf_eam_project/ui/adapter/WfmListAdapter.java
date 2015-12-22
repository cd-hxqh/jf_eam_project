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
import com.jf_eam_project.model.Po;
import com.jf_eam_project.model.Wfassignment;
import com.jf_eam_project.ui.activity.PO_Details_Activity;
import com.jf_eam_project.ui.activity.Wfm_Details_Activity;

import java.util.ArrayList;
import java.util.List;


/**
 * 流程审批
 */
public class WfmListAdapter extends RecyclerView.Adapter<WfmListAdapter.ViewHolder> {
    Context mContext;
    List<Wfassignment> wfmList = new ArrayList<>();

    public WfmListAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Wfassignment wfm = wfmList.get(position);
        holder.itemNumTitle.setText(mContext.getString(R.string.wfm_ownertable_title));
        holder.itemDescTitle.setText(mContext.getString(R.string.prline_description));
        holder.itemNum.setText(wfm.ownertable);
        holder.itemDesc.setText(wfm.description);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Wfm_Details_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("wfassignment", wfm);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wfmList.size();
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

    public void update(ArrayList<Wfassignment> data, boolean merge) {
        if (merge && wfmList.size() > 0) {
            for (int i = 0; i < wfmList.size(); i++) {
                Wfassignment wfm = wfmList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j).wfassignmentid == wfm.wfassignmentid) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(wfm);
            }
        }
        wfmList = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<Wfassignment> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!wfmList.contains(data.get(i))) {
                    wfmList.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }


    public void removeAllData() {
        if (wfmList.size() > 0) {
            wfmList.removeAll(wfmList);
        }
    }
}
