package com.jf_eam_project.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Invreserve;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/12/4.
 * 预留项目
 */
public class InvreserveAdapter extends RecyclerView.Adapter<InvreserveAdapter.ViewHolder> {
    Context mContext;
    public List<Invreserve> invreserveList = new ArrayList<>();

    public InvreserveAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_invreserve_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Invreserve invreserve = invreserveList.get(position);
        holder.itemnumTextView.setText(invreserve.itemnum);
        holder.descriptionTextView.setText(invreserve.description);

    }

    @Override
    public int getItemCount() {
        return invreserveList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * 选择
         **/
        private CheckBox mCheckBox;
        /**
         * 项目*
         */
        public TextView itemnumTextView;
        /**
         * 描述*
         */
        public TextView descriptionTextView;

        public ViewHolder(View view) {
            super(view);


            mCheckBox = (CheckBox) view.findViewById(R.id.checkbox_id);
            itemnumTextView = (TextView) view.findViewById(R.id.itemnum_text_id);
            descriptionTextView = (TextView) view.findViewById(R.id.item_desc_id);
        }
    }

    public void update(ArrayList<Invreserve> data, boolean merge) {
        if (merge && invreserveList.size() > 0) {
            for (int i = 0; i < invreserveList.size(); i++) {
                Invreserve wptool = invreserveList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == wptool) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(wptool);
            }
        }
        invreserveList = data;
        notifyDataSetChanged();
    }


    public void adddate(ArrayList<Invreserve> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                invreserveList.add(data.get(i));
            }
        }
        notifyDataSetChanged();
    }

    public void adddate(Invreserve assignment) {
        invreserveList.add(assignment);
        notifyDataSetChanged();
    }

    public void removeAllData() {
        if (invreserveList.size() > 0) {
            invreserveList.removeAll(invreserveList);
        }
    }
}
