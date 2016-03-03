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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.ui.activity.Material_Details_Activity;
import com.jf_eam_project.ui.activity.Work_DetailsActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/11/26.
 */
public class WorkListAdapter extends RecyclerView.Adapter<WorkListAdapter.ViewHolder> {
    Context mContext;
    List<WorkOrder> workOrderList = new ArrayList<>();

    /**
     * checkbox
     * 隐藏/显示
     */
    private int mark = 0;
    /**
     * 全选*
     */
    private boolean allChoose;

    /**
     * 入口*
     */
    private int cMark;

    /**
     * 长按事件*
     */
    public OnLongClickListener onLongClickListener;

    /**
     * 选中事件*
     */
    public OnCheckedChangeListener onCheckedChangeListener;

    public WorkListAdapter(Context context, int cMark) {

        this.mContext = context;
        this.cMark = cMark;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final WorkOrder workOrder = workOrderList.get(position);
        if (cMark == 2) { //领料单
            holder.itemNumTitle.setText(mContext.getString(R.string.requisition_text));
        } else {
            holder.itemNumTitle.setText(mContext.getString(R.string.work_wonum));
        }
        holder.itemDescTitle.setText(mContext.getString(R.string.work_desc));
        holder.itemNum.setText(workOrder.getWonum());
        holder.itemDesc.setText(workOrder.getDescription());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cMark == 2){
                    Intent intent = new Intent(mContext, Material_Details_Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("workOrder", workOrder);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }else {
                    Intent intent = new Intent(mContext, Work_DetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("workOrder", workOrder);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            }
        });

        if (cMark == 1) { //历史记录
            holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onLongClickListener.cOnLongClickListener();

                    return true;
                }
            });

            if (mark == 0) {
                holder.checkBox.setVisibility(View.GONE);
                holder.item_more.setVisibility(View.VISIBLE);
            } else {
                holder.checkBox.setVisibility(View.VISIBLE);
                holder.item_more.setVisibility(View.GONE);
            }

            holder.checkBox.setChecked(allChoose);


            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        onCheckedChangeListener.cOnCheckedChangeListener(position);
                    }
                }
            });

        }
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
    public void adddate(ArrayList<WorkOrder> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!workOrderList.contains(data.get(i))) {
                    workOrderList.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }

    public void removeAllData() {
        if (workOrderList.size() > 0) {
            workOrderList.removeAll(workOrderList);
        }
    }

    /**
     * 传递值*
     */
    public void setMark(int mark) {
        this.mark = mark;
    }

    /**
     * 设置全选*
     */
    public void setAllChoose(boolean allChoose) {
        this.allChoose = allChoose;
    }


    public interface OnLongClickListener {
        public void cOnLongClickListener();
    }

    public interface OnCheckedChangeListener {
        public void cOnCheckedChangeListener(int postion);
    }


    public OnLongClickListener getOnLongClickListener() {
        return onLongClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }


    public OnCheckedChangeListener getOnCheckedChangeListener() {
        return onCheckedChangeListener;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }
}
