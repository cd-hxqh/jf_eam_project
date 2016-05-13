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
import com.jf_eam_project.model.Udinspo;
import com.jf_eam_project.ui.activity.Udinspo_Details_activity;
import com.jf_eam_project.utils.MessageUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/11/26.
 * 巡检单
 */
public class UdinspoListadapter extends RecyclerView.Adapter<UdinspoListadapter.ViewHolder> {
    Context mContext;
    List<Udinspo> udinspoList = new ArrayList<>();

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

    public UdinspoListadapter(Context context, int cMark) {

        this.mContext = context;
        this.cMark = cMark;
    }

    /**
     * 长按事件*
     */
    public OnLongClickListener onLongClickListener;

    /**
     * 选中事件*
     */
    public OnCheckedChangeListener onCheckedChangeListener;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Udinspo udinspo = udinspoList.get(position);
        holder.itemNumTitle.setText(mContext.getString(R.string.djdh_text));
        holder.itemDescTitle.setText(mContext.getString(R.string.prline_description));
        holder.itemNum.setText(udinspo.insponum);
        holder.itemDesc.setText(udinspo.description);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Udinspo_Details_activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("udinspo", udinspo);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
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
        return udinspoList.size();
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

    public void update(ArrayList<Udinspo> data, boolean merge) {
        if (merge && udinspoList.size() > 0) {
            for (int i = 0; i < udinspoList.size(); i++) {
                Udinspo udinspo = udinspoList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j).insponum == udinspo.insponum) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(udinspo);
            }
        }
        udinspoList = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<Udinspo> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!udinspoList.contains(data.get(i))) {
                    udinspoList.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }


    public void removeAllData() {
        if (udinspoList.size() > 0) {
            udinspoList.removeAll(udinspoList);
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
