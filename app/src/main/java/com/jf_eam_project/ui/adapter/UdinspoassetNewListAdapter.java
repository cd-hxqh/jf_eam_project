package com.jf_eam_project.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jf_eam_project.Dao.UdinspojxxmDao;
import com.jf_eam_project.R;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.model.Udinspojxxm;
import com.jf_eam_project.ui.activity.Udinspoassetnew_Activity;
import com.jf_eam_project.ui.activity.UdinspojxxmNew_Activity;
import com.jf_eam_project.ui.activity.Udinspojxxm_Activity;

import java.util.ArrayList;
import java.util.List;


/**
 * 设备备件
 */
public class UdinspoassetNewListAdapter extends RecyclerView.Adapter<UdinspoassetNewListAdapter.ViewHolder> {
    private static final String TAG = "UdinspoassetNewListAdapter";
    Context mContext;
    List<Udinspoasset> udinspoassetList = new ArrayList<>();
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


    /**
     * 点击事件*
     */
    public OnClickListener onClickListener;

    public UdinspoassetNewListAdapter(Context context) {
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
        final Udinspoasset udinspoasset = udinspoassetList.get(position);

        holder.checkBox.setVisibility(View.GONE);
        holder.itemNumTitle.setText(mContext.getString(R.string.udinspoasset_udinspoassetlinenum_title));
        holder.itemDescTitle.setText(mContext.getString(R.string.udinspojxxm_desciption_text));
        holder.itemNum.setText(udinspoasset.udinspoassetlinenum);
        holder.itemDesc.setText(udinspoasset.childassetnum);
        holder.completionText.setText("完成率:" + getCom(udinspoasset.udinspoassetnum) + "%");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(mContext, UdinspojxxmNew_Activity.class);
//                intent.putExtra("udinspoassetnum", udinspoasset.udinspoassetnum);
//                intent.putExtra("branch", branch);
//                intent.putExtra("udbelong", udbelong);
//                intent.putExtra("assettype", assettype);
//                mContext.startActivity(intent);
                onClickListener.cOnClickListener(udinspoasset);
            }
        });
    }

    @Override
    public int getItemCount() {
        return udinspoassetList.size();
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
         * 完成率*
         */
        public TextView completionText;
        /**
         * 选择框*
         */
        public CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_container);

            itemNumTitle = (TextView) view.findViewById(R.id.item_num_title);
            itemDescTitle = (TextView) view.findViewById(R.id.item_desc_title);


            itemNum = (TextView) view.findViewById(R.id.item_num_text);
            itemDesc = (TextView) view.findViewById(R.id.item_desc_text);
            completionText = (TextView) view.findViewById(R.id.is_operation);
            checkBox = (CheckBox) view.findViewById(R.id.checkbox_id);
        }
    }

    public void update(ArrayList<Udinspoasset> data, boolean merge) {
        if (merge && udinspoassetList.size() > 0) {
            for (int i = 0; i < udinspoassetList.size(); i++) {
                Udinspoasset udinspoasset = udinspoassetList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j).udinspoassetnum == udinspoasset.getUdinspoassetnum()) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(udinspoasset);
            }
        }
        udinspoassetList = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<Udinspoasset> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!udinspoassetList.contains(data.get(i))) {
                    udinspoassetList.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }


    public void removeAllData() {
        if (udinspoassetList.size() > 0) {
            udinspoassetList.removeAll(udinspoassetList);
        }
    }


    /**
     * 计算完成率*
     */
    private String getCom(String udinspoassetnum) {

        //计算总数
        double sizeCount = -1;
        //完成数
        double endCount = 0;

        //总数
        List<Udinspojxxm> udinspojxxms = new UdinspojxxmDao(mContext).queryByUdinspoassetnum(udinspoassetnum);
        if (null != udinspojxxms && udinspojxxms.size() != 0) {
            sizeCount = udinspojxxms.size();
            for (Udinspojxxm udinspojxxm : udinspojxxms) {
            }
        }

        //完成数
        List<Udinspojxxm> endudinspojxxms = new UdinspojxxmDao(mContext).findBycompletion(udinspoassetnum, 1);
        if (null != endudinspojxxms && endudinspojxxms.size() != 0) {
            endCount = endudinspojxxms.size();
        }


        String com = (endCount / sizeCount) * 100 + "";


        return com;
    }


    public interface OnClickListener {
        public void cOnClickListener(Udinspoasset udinspoasset);
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


}
