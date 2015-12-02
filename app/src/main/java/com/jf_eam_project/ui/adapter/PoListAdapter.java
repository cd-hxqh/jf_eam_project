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
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.ui.activity.Work_DetailsActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/11/26.
 * 采购
 */
public class PoListAdapter extends RecyclerView.Adapter<PoListAdapter.ViewHolder> {
    Context mContext;
    List<Po> poList = new ArrayList<>();

    public PoListAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Po po = poList.get(position);
        holder.itemNumTitle.setText(mContext.getString(R.string.po_number_text));
        holder.itemDescTitle.setText(mContext.getString(R.string.work_desc));
        holder.itemNum.setText(po.getPonum());
        holder.itemDesc.setText(po.getDescription());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(mContext, Work_DetailsActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("workOrder", workOrder);
//                intent.putExtras(bundle);
//                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return poList.size();
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

    public void update(ArrayList<Po> data, boolean merge) {
        if (merge && poList.size() > 0) {
            for (int i = 0; i < poList.size(); i++) {
                Po po = poList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == po) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(po);
            }
        }
        poList = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<Po> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!poList.contains(data.get(i))) {
                    poList.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }
}
