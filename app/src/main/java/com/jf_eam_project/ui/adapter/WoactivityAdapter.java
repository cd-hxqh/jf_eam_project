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
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.ui.activity.WoactivityDetailsActivity;
import com.jf_eam_project.ui.activity.Work_PlanActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/11/3.
 * 任务
 */
public class WoactivityAdapter extends RecyclerView.Adapter<WoactivityAdapter.ViewHolder> {
    Work_PlanActivity mContext;
    public ArrayList<Woactivity> woactivityList = new ArrayList<>();

    public WoactivityAdapter(Work_PlanActivity context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Woactivity woactivity = woactivityList.get(position);
        holder.itemNumTitle.setText("任务:");
        holder.itemDescTitle.setText("摘要:");
        holder.itemNum.setText(woactivity.taskid);
        holder.itemDesc.setText(woactivity.description);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WoactivityDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("woactivity", woactivity);
                bundle.putSerializable("position", position);
                intent.putExtras(bundle);
                mContext.startActivityForResult(intent, 4);
            }
        });
    }

    @Override
    public int getItemCount() {
        return woactivityList.size();
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

    public void update(ArrayList<Woactivity> data, boolean merge) {
        if (merge && woactivityList.size() > 0) {
            for (int i = 0; i < woactivityList.size(); i++) {
                Woactivity workOrder = woactivityList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == workOrder) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(workOrder);
            }
        }
        woactivityList = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<Woactivity> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                woactivityList.add(data.get(i));
            }
        }
        notifyDataSetChanged();
    }

    public void adddate(Woactivity woactivity) {
        woactivityList.add(woactivity);
        notifyDataSetChanged();
    }
}
