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
import com.jf_eam_project.model.WorkOrder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/11/26.
 */
public class WorkListAdapter extends RecyclerView.Adapter<WorkListAdapter.ViewHolder> {
    Context mContext;
    List<WorkOrder> workOrderList = new ArrayList<>();
    public WorkListAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final WorkOrder workOrder = workOrderList.get(position);
        holder.itemNumTitle.setText(mContext.getString(R.string.work_wonum));
        holder.itemDescTitle.setText(mContext.getString(R.string.work_desc));
        holder.itemNum.setText(workOrder.getWonum());
        holder.itemDesc.setText(workOrder.getDescription());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(mContext, Work_detailsActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("workOrder", workOrder);
//                intent.putExtras(bundle);
//                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return workOrderList.size();
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

            itemNumTitle=(TextView) view.findViewById(R.id.item_num_title);
            itemDescTitle=(TextView) view.findViewById(R.id.item_desc_title);


            itemNum = (TextView) view.findViewById(R.id.item_num_text);
            itemDesc = (TextView) view.findViewById(R.id.item_desc_text);
        }
    }

    public void update(ArrayList<WorkOrder> data, boolean merge) {
        if (merge && workOrderList.size() > 0) {
            for (int i = 0; i < workOrderList.size(); i++) {
                WorkOrder workOrder = workOrderList.get(i);
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
        workOrderList = data;
        notifyDataSetChanged();
    }
//
    public void adddate(ArrayList<WorkOrder> data){
        if(data.size()>0){
            for(int i = 0;i < data.size();i++){
                if(!workOrderList.contains(data.get(i))){
                    workOrderList.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }
}
