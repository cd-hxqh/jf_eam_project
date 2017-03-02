package com.jf_eam_project.ui.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Companies;
import com.jf_eam_project.ui.activity.Companies_Choose_Activity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/11/26.
 * 供应商
 */
public class CompaniesAdapter extends RecyclerView.Adapter<CompaniesAdapter.ViewHolder> {
    Companies_Choose_Activity activity;
    List<Companies> curList = new ArrayList<>();
    public CompaniesAdapter(Companies_Choose_Activity activity) {
        this.activity=activity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Companies companies = curList.get(position);
        holder.itemDescTitle.setText(R.string.companies_title);
        holder.itemNum.setText(companies.getCompany());
        holder.itemDesc.setText(companies.getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.responseCompaniesData(companies);
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

    public void update(ArrayList<Companies> data, boolean merge) {
        if (merge && curList.size() > 0) {
            for (int i = 0; i < curList.size(); i++) {
                Companies currency = curList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == currency) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(currency);
            }
        }
        curList = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<Companies> data) {
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
