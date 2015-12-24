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
import com.jf_eam_project.model.Inventory;
import com.jf_eam_project.model.Invoice;
import com.jf_eam_project.ui.activity.Inventory_Details_Activity;
import com.jf_eam_project.ui.activity.Invoice_Details_Activity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/11/26.
 * 库存
 */
public class InventoryListAdapter extends RecyclerView.Adapter<InventoryListAdapter.ViewHolder> {
    Context mContext;
    List<Inventory> inventorys = new ArrayList<>();

    public InventoryListAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Inventory inventory = inventorys.get(position);
        holder.itemNumTitle.setText(mContext.getString(R.string.inventory_itemnum_title));
        holder.itemDescTitle.setText(mContext.getString(R.string.prline_description));
        holder.itemNum.setText(inventory.getItemnum());
        holder.itemDesc.setText(inventory.getDescription());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Inventory_Details_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("inventory", inventory);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return inventorys.size();
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

    public void update(ArrayList<Inventory> data, boolean merge) {
        if (merge && inventorys.size() > 0) {
            for (int i = 0; i < inventorys.size(); i++) {
                Inventory  inventory = inventorys.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j).inventoryid== inventory.inventoryid) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(inventory);
            }
        }
        inventorys = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<Inventory> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!inventorys.contains(data.get(i))) {
                    inventorys.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }


    public void removeAllData() {
        if (inventorys.size() > 0) {
            inventorys.removeAll(inventorys);
        }
    }
}
