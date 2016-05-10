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
import com.jf_eam_project.model.Udreport;
import com.jf_eam_project.ui.activity.Material_Into_Details_Activity;
import com.jf_eam_project.ui.activity.PO_Details_Activity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/11/26.
 * 故障，缺陷单
 */
public class UdreportListAdapter extends RecyclerView.Adapter<UdreportListAdapter.ViewHolder> {
    Context mContext;
    List<Udreport> udreports = new ArrayList<>();

    public UdreportListAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Udreport udreport = udreports.get(position);

        holder.itemNumTitle.setText(mContext.getString(R.string.udbrnum_text));
        holder.itemDescTitle.setText(mContext.getString(R.string.prline_description));
        holder.itemNum.setText(udreport.reportnum);
        holder.itemDesc.setText(udreport.description);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = null;
//                if (mark == 0) {
//                    intent = new Intent(mContext, PO_Details_Activity.class);
//                } else {
//                    intent = new Intent(mContext, Material_Into_Details_Activity.class);
//                }
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("po", po);
//                intent.putExtras(bundle);
//                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return udreports.size();
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

    public void update(ArrayList<Udreport> data, boolean merge) {
        if (merge && udreports.size() > 0) {
            for (int i = 0; i < udreports.size(); i++) {
                Udreport udreport = udreports.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == udreport) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(udreport);
            }
        }
        udreports = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<Udreport> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!udreports.contains(data.get(i))) {
                    udreports.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }


    public void removeAllData() {
        if (udreports.size() > 0) {
            udreports.removeAll(udreports);
        }
    }
}
