package com.jf_eam_project.ui.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Compcontact;
import com.jf_eam_project.ui.activity.Compcontact_Choose_Activity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/11/26.
 * 联系人
 */
public class CompcontactAdapter extends RecyclerView.Adapter<CompcontactAdapter.ViewHolder> {
    Compcontact_Choose_Activity activity;
    List<Compcontact> curList = new ArrayList<>();
    public CompcontactAdapter(Compcontact_Choose_Activity activity) {
        this.activity=activity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Compcontact compcontact = curList.get(position);
        holder.itemNumTitle.setText(R.string.contact_text);
        holder.itemDescTitle.setText(R.string.voicephone_text);
        holder.itemNum.setText(compcontact.getContact());
        holder.itemDesc.setText(compcontact.getVoicephone());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.responseCompcontactData(compcontact);
            }
        });
    }

    @Override
    public int getItemCount() {
        return curList.size();
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

    public void update(ArrayList<Compcontact> data, boolean merge) {
        if (merge && curList.size() > 0) {
            for (int i = 0; i < curList.size(); i++) {
                Compcontact compcontact = curList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == compcontact) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(compcontact);
            }
        }
        curList = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<Compcontact> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!curList.contains(data.get(i))) {
                    curList.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }


    public void removeAllData() {
        if (curList.size() > 0) {
            curList.removeAll(curList);
        }
    }
}
