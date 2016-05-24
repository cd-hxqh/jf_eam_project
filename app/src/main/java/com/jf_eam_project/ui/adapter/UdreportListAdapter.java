package com.jf_eam_project.ui.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.jf_eam_project.model.Po;
import com.jf_eam_project.model.Udreport;
import com.jf_eam_project.ui.activity.Material_Into_Details_Activity;
import com.jf_eam_project.ui.activity.PO_Details_Activity;
import com.jf_eam_project.ui.activity.Udreport_Details_Activity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/11/26.
 * 故障，缺陷单
 */
public class UdreportListAdapter extends RecyclerView.Adapter<UdreportListAdapter.ViewHolder> {

    private static final String TAG = "UdreportListAdapter";
    Context mContext;
    List<Udreport> udreports = new ArrayList<>();

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
    private int cMark; //判断是在线还是离线状态; 0:在线，1:离线


    /**
     * 长按事件*
     */
    public OnLongClickListener onLongClickListener;

    /**
     * 选中事件*
     */
    public OnCheckedChangeListener onCheckedChangeListener;

    /**
     * 点击事件*
     */
    public OnClickListener onClickListener;


    public UdreportListAdapter(Context context) {

        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Udreport udreport = udreports.get(position);

        holder.itemNumTitle.setText(mContext.getString(R.string.udbrnum_text));
        holder.itemDescTitle.setText(mContext.getString(R.string.prline_description));
        holder.itemNum.setText(udreport.reportnum);
        holder.itemDesc.setText(udreport.description);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(mContext, Udreport_Details_Activity.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("mark", 0);
//                bundle.putSerializable("udreport", udreport);
//                intent.putExtras(bundle);
//                mContext.startActivity(intent);
                Log.i(TAG, "22222");
                onClickListener.cOnClickListener(position, udreport);
            }
        });

        if (cMark == 1) { //离线
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
                    onCheckedChangeListener.cOnCheckedChangeListener(b, position);
                }
            });

        }


    }


    @Override
    public int getItemCount() {
        return udreports.size();
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

    public void update(ArrayList<Udreport> data, boolean merge) {
        if (merge && udreports.size() > 0) {
            for (int i = 0; i < udreports.size(); i++) {
                Udreport udreport = udreports.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == udreport) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(udreport);
            }
        }
        udreports = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<Udreport> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!udreports.contains(data.get(i))) {
                    udreports.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }


    public void removeAllData() {
        if (udreports.size() > 0) {
            udreports.removeAll(udreports);
        }
    }


    /**
     * 设置是否显示在线离线*
     */
    public void setIsShow(int cmark) {
        this.cMark = cmark;
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

    /**
     * 长按*
     */
    public interface OnLongClickListener {
        public void cOnLongClickListener();
    }

    /**
     * 选中*
     */
    public interface OnCheckedChangeListener {
        public void cOnCheckedChangeListener(boolean b, int postion);
    }

    /**
     * 点击*
     */
    public interface OnClickListener {
        public void cOnClickListener(int postion, Udreport udreport);
    }


    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
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
