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
import com.jf_eam_project.model.Wplabor;
import com.jf_eam_project.model.Wpmaterial;
import com.jf_eam_project.ui.activity.Work_PlanActivity;
import com.jf_eam_project.ui.activity.WpmaterialDetailsActivity;
import com.jf_eam_project.ui.activity.Wpmaterial_DetailsActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/12/4.
 * 物料
 */
public class WpmaterialAdapter extends RecyclerView.Adapter<WpmaterialAdapter.ViewHolder> {
    Work_PlanActivity mContext=null;
    Context mContext1;
    public ArrayList<Wpmaterial> wpmaterialList = new ArrayList<>();
    public ArrayList<Wpmaterial> deleteList = new ArrayList<>();

    public WpmaterialAdapter(Work_PlanActivity context) {
        this.mContext = context;
    }
    public WpmaterialAdapter(Context context) {
        this.mContext1 = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Wpmaterial wpmaterial = wpmaterialList.get(position);
        holder.itemNumTitle.setText("项目");
        holder.itemDescTitle.setText("数量");
        holder.itemNum.setText(wpmaterial.itemnum);
        holder.itemDesc.setText(wpmaterial.itemqty);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mContext!=null) {
                    Intent intent = new Intent(mContext, WpmaterialDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("wpmaterial", wpmaterial);
                    bundle.putSerializable("position", position);
                    bundle.putSerializable("woactivityList", mContext.woactivityList);
                    intent.putExtras(bundle);
                    mContext.startActivityForResult(intent,6);
                }else{
                    Intent intent = new Intent(mContext1, Wpmaterial_DetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("wpmaterial", wpmaterial);
                    intent.putExtras(bundle);
                    mContext1.startActivity(intent);
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return wpmaterialList.size();
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

    public void update(ArrayList<Wpmaterial> data, boolean merge) {
        if (merge && wpmaterialList.size() > 0) {
            for (int i = 0; i < wpmaterialList.size(); i++) {
                Wpmaterial wpmaterial = wpmaterialList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == wpmaterial) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(wpmaterial);
            }
        }
        wpmaterialList = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<Wpmaterial> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                wpmaterialList.add(data.get(i));
            }
        }
        notifyDataSetChanged();
    }

    public void adddate(Wpmaterial wpmaterial) {
        wpmaterialList.add(wpmaterial);
        notifyDataSetChanged();
    }

    public ArrayList<Wpmaterial> getList(){
        ArrayList<Wpmaterial> list = new ArrayList<>();
        if(wpmaterialList.size()!=0){
            list.addAll(wpmaterialList);
        }
        if (deleteList.size()!=0){
            list.addAll(deleteList);
        }
        return list;
    }
}
