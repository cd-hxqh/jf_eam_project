package com.jf_eam_project.ui.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnBtnEditClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.flyco.dialog.widget.NormalEditTextDialog;
import com.jf_eam_project.R;
import com.jf_eam_project.model.Po;
import com.jf_eam_project.model.Udreport;
import com.jf_eam_project.model.Wfassignment;
import com.jf_eam_project.ui.activity.PO_Details_Activity;
import com.jf_eam_project.ui.activity.QxUdreport_Details_Activity;
import com.jf_eam_project.ui.activity.Wfm_Details_Activity;

import java.util.ArrayList;
import java.util.List;


/**
 * 流程审批
 */
public class WfmListAdapter extends RecyclerView.Adapter<WfmListAdapter.ViewHolder> {
    private static final String TAG = "WfmListAdapter";

    Context mContext;
    List<Wfassignment> wfmList = new ArrayList<>();


    /**
     * 审批流程点击事件*
     */
    public OnClickListener onClickListener;

    public WfmListAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.wfm_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Wfassignment wfm = wfmList.get(position);
        Log.i(TAG, "wfm=" + wfm.app + ",ownertable=" + wfm.ownertable + ",processname=" + wfm.processname + ",ownerid=" + wfm.ownerid.replace(",", ""));

        holder.itemNumTitle.setText(mContext.getString(R.string.wfm_ownertable_title));
        holder.itemDescTitle.setText(mContext.getString(R.string.prline_description));
        holder.itemNum.setText(wfm.ownertable);
        holder.itemDesc.setText(wfm.description);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**判断跳转至相应的流程界面进行审批**/
                isProcess(wfm);


            }
        });

        holder.approvalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "点击了一下审批");
                onClickListener.cOnClickListener(position, wfm);
            }
        });
    }


    @Override
    public int getItemCount() {
        return wfmList.size();
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
         * 审批*
         */
        public Button approvalButton;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_container);

            itemNumTitle = (TextView) view.findViewById(R.id.item_num_title);
            itemDescTitle = (TextView) view.findViewById(R.id.item_desc_title);


            itemNum = (TextView) view.findViewById(R.id.item_num_text);
            itemDesc = (TextView) view.findViewById(R.id.item_desc_text);

            approvalButton = (Button) view.findViewById(R.id.approval_id);
        }
    }

    public void update(ArrayList<Wfassignment> data, boolean merge) {
        if (merge && wfmList.size() > 0) {
            for (int i = 0; i < wfmList.size(); i++) {
                Wfassignment wfm = wfmList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j).wfassignmentid == wfm.wfassignmentid) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(wfm);
            }
        }
        wfmList = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<Wfassignment> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!wfmList.contains(data.get(i))) {
                    wfmList.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }


    public void removeAllData() {
        if (wfmList.size() > 0) {
            wfmList.removeAll(wfmList);
        }
    }

    /**
     * 判断进入相应的流程界面进行审批*
     */
    private void isProcess(Wfassignment wfassigment) {


        if (wfassigment.app.equals("UDUPRAPP") && wfassigment.ownertable.equals("UDREPORT") && wfassigment.processname.equals("UDQXTB1")) { //跳转至缺陷提报单界面
            Log.i(TAG, "缺陷单");
            Intent intent = new Intent(mContext, QxUdreport_Details_Activity.class);
            Bundle bundle = new Bundle();
            bundle.putString("udreportid", wfassigment.ownerid.replace(",", ""));
            intent.putExtras(bundle);
            mContext.startActivity(intent);

        } else {
            Log.i(TAG, "其它");
            Intent intent = new Intent(mContext, Wfm_Details_Activity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("wfassignment", wfassigment);
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        }


    }


    /**
     * 点击*
     */
    public interface OnClickListener {
        public void cOnClickListener(int postion, Wfassignment wfassignment);
    }


    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


}
