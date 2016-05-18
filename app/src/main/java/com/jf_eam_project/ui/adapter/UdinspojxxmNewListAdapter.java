package com.jf_eam_project.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Udinspojxxm;
import com.jf_eam_project.ui.activity.Udinspojxxm_Details_Activity;

import java.util.ArrayList;
import java.util.List;


/**
 * 巡检项目标准
 */
public class UdinspojxxmNewListAdapter extends RecyclerView.Adapter<UdinspojxxmNewListAdapter.ViewHolder> {
    private static final String TAG = "UdinspojxxmNewListAdapter";
    Context mContext;
    List<Udinspojxxm> udinspojxxmList = new ArrayList<>();

    /**
     * 点击事件*
     */
    public OnClickListener onClickListener;

    /**
     * 分公司*
     */
    private String branch;
    /**
     * 运行单位*
     */
    private String udbelong;
    /**
     * 类型*
     */
    private String assettype;


    public UdinspojxxmNewListAdapter(Context context) {
        this.mContext = context;
    }


    public void setData(String branch, String udbelong, String assettype) {
        this.branch = branch;
        this.udbelong = udbelong;
        this.assettype = assettype;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.udinspolocation_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Udinspojxxm udinspojxxm = udinspojxxmList.get(position);

        Log.i(TAG,"udinspojxxmlinenum="+udinspojxxm.udinspojxxmlinenum);
        holder.itemNumTitle.setText(mContext.getString(R.string.udinspoasset_udinspoassetlinenum_title));
        holder.itemDescTitle.setText(mContext.getString(R.string.ud_description_title));
        holder.checkBox.setVisibility(View.GONE);

        holder.itemNum.setText(udinspojxxm.udinspojxxmlinenum);
        holder.itemDesc.setText(udinspojxxm.description);
        String udinspojxxm1 = udinspojxxm.udinspojxxm1;

        if (udinspojxxm1.equals("")) {
            holder.udinspojxxm1.setVisibility(View.GONE);
        } else {
            holder.udinspojxxm1.setVisibility(View.VISIBLE);
            holder.udinspojxxm1.setText(udinspojxxm1);
        }


        if (isAbnormal(udinspojxxm1)) {
            holder.itemNumTitle.setTextColor(Color.parseColor("#000000"));
            holder.itemDescTitle.setTextColor(Color.parseColor("#000000"));
            holder.itemNum.setTextColor(Color.parseColor("#000000"));
            holder.itemDesc.setTextColor(Color.parseColor("#000000"));
            holder.udinspojxxm1.setTextColor(Color.parseColor("#ff29549f"));
        } else {


            holder.itemNumTitle.setTextColor(Color.parseColor("#ffff4444"));
            holder.itemDescTitle.setTextColor(Color.parseColor("#ffff4444"));
            holder.itemNum.setTextColor(Color.parseColor("#ffff4444"));
            holder.itemDesc.setTextColor(Color.parseColor("#ffff4444"));
            holder.udinspojxxm1.setTextColor(Color.parseColor("#ffff4444"));
        }


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                onClickListener.cOnClickListener(udinspojxxm);

            }
        });


    }

    @Override
    public int getItemCount() {
        return udinspojxxmList.size();
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
        public CheckBox checkBox;
        /**
         * 更多*
         */
        public ImageView item_more;

        /**
         * 状态*
         */
        public TextView udinspojxxm1;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_container);

            itemNumTitle = (TextView) view.findViewById(R.id.item_num_title);
            itemDescTitle = (TextView) view.findViewById(R.id.item_desc_title);


            itemNum = (TextView) view.findViewById(R.id.item_num_text);
            itemDesc = (TextView) view.findViewById(R.id.item_desc_text);
            checkBox = (CheckBox) view.findViewById(R.id.checkbox_id);
            item_more = (ImageView) view.findViewById(R.id.avatar);
            udinspojxxm1 = (TextView) view.findViewById(R.id.is_operation);
        }
    }


    public void update(ArrayList<Udinspojxxm> data, boolean merge) {
        if (merge && udinspojxxmList.size() > 0) {
            for (int i = 0; i < udinspojxxmList.size(); i++) {
                Udinspojxxm udinspojxxm = udinspojxxmList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j).udinspojxxmlinenum == udinspojxxm.udinspojxxmlinenum) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(udinspojxxm);
            }
        }
        udinspojxxmList = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<Udinspojxxm> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!udinspojxxmList.contains(data.get(i))) {
                    udinspojxxmList.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }


    public void removeAllData() {
        if (udinspojxxmList.size() > 0) {
            udinspojxxmList.removeAll(udinspojxxmList);
        }
    }


    /**
     * 判断巡检项目表是否正常异常*
     */
    private boolean isAbnormal(String udinspojxxm1) {
        if (udinspojxxm1.isEmpty() || udinspojxxm1.equals("") || udinspojxxm1.equals("正常")) {
            return true;
        } else {
            return false;
        }
    }

    public interface OnClickListener {
        public void cOnClickListener(Udinspojxxm udinspojxxm);
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
