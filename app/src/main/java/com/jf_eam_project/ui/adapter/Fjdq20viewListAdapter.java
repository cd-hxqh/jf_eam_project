package com.jf_eam_project.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.FJDQ20VIEW;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/11/26.
 * 故障统计20天以上
 */
public class Fjdq20viewListAdapter extends RecyclerView.Adapter<Fjdq20viewListAdapter.ViewHolder> {
    Context mContext;
    List<FJDQ20VIEW> fjdq20VIEWs = new ArrayList<>();

    public Fjdq20viewListAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_fjdq20view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final FJDQ20VIEW fjdq20VIEW = fjdq20VIEWs.get(position);
        holder.gztbbhTextView.setText(fjdq20VIEW.reportnum);
        holder.fdcTextView.setText(fjdq20VIEW.udbelong);
        holder.msTextView.setText(fjdq20VIEW.description);
        holder.gzdjTextView.setText(fjdq20VIEW.culevel);
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mContext, PoLine_Details_Activity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("poLine", poline);
//                bundle.putInt("mark", mark);
//                intent.putExtras(bundle);
//                mContext.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return fjdq20VIEWs.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout relativeLayout;

        /**
         * 编号*
         */
        public TextView gztbbhTextView;
        /**
         * 风电场*
         */
        public TextView fdcTextView;
        /**
         * 描述*
         */
        public TextView msTextView;
        /**
         * 故障等级
         */
        public TextView gzdjTextView;

        public ViewHolder(View view) {
            super(view);

            gztbbhTextView = (TextView) view.findViewById(R.id.gztbbh_text_id);
            fdcTextView = (TextView) view.findViewById(R.id.fdc_text_id);


            msTextView = (TextView) view.findViewById(R.id.ms_text_id);
            gzdjTextView = (TextView) view.findViewById(R.id.gzdj_text_id);
        }
    }

    public void update(ArrayList<FJDQ20VIEW> data, boolean merge) {
        if (merge && fjdq20VIEWs.size() > 0) {
            for (int i = 0; i < fjdq20VIEWs.size(); i++) {
                FJDQ20VIEW po = fjdq20VIEWs.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == po) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(po);
            }
        }
        fjdq20VIEWs = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<FJDQ20VIEW> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!fjdq20VIEWs.contains(data.get(i))) {
                    fjdq20VIEWs.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }


    public void removeAllData() {
        if (fjdq20VIEWs.size() > 0) {
            fjdq20VIEWs.removeAll(fjdq20VIEWs);
        }
    }
}
