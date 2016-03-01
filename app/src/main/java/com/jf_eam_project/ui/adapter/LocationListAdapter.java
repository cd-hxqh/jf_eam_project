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
import com.jf_eam_project.model.Invoice;
import com.jf_eam_project.model.Location;
import com.jf_eam_project.ui.activity.Invoice_Details_Activity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/11/26.
 * 物资发放
 */
public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.ViewHolder> {
    Context mContext;
    List<Location> locations = new ArrayList<>();

    public LocationListAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Location location = locations.get(position);
        holder.itemNumTitle.setText("库房:");
        holder.itemDescTitle.setText(mContext.getString(R.string.prline_description));
        holder.itemNum.setText(location.location);
        holder.itemDesc.setText(location.description);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(mContext, Invoice_Details_Activity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("invoice", invoice);
//                intent.putExtras(bundle);
//                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
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

    public void update(ArrayList<Location> data, boolean merge) {
        if (merge && locations.size() > 0) {
            for (int i = 0; i < locations.size(); i++) {
                Location location = locations.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == location) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(location);
            }
        }
        locations = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<Location> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!locations.contains(data.get(i))) {
                    locations.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }


    public void removeAllData() {
        if (locations.size() > 0) {
            locations.removeAll(locations);
        }
    }
}
