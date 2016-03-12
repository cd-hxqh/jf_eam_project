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
import com.jf_eam_project.model.Invoice;
import com.jf_eam_project.model.InvoiceLine;
import com.jf_eam_project.ui.activity.InvoiceLine_Details_Activity;
import com.jf_eam_project.ui.activity.Invoice_Details_Activity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/11/26.
 * 发票行
 */
public class InvoiceLineListAdapter extends RecyclerView.Adapter<InvoiceLineListAdapter.ViewHolder> {
    Context mContext;
    List<InvoiceLine> invoices = new ArrayList<>();

    public InvoiceLineListAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final InvoiceLine invoiceLine = invoices.get(position);
        holder.itemNumTitle.setText(mContext.getString(R.string.invoicelinenum_text));
        holder.itemDescTitle.setText(mContext.getString(R.string.invoiceline_itemnum_text));
        holder.itemNum.setText(invoiceLine.invoicelinenum);
        holder.itemDesc.setText(invoiceLine.itemnum);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, InvoiceLine_Details_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("invoiceLine", invoiceLine);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return invoices.size();
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

    public void update(ArrayList<InvoiceLine> data, boolean merge) {
        if (merge && invoices.size() > 0) {
            for (int i = 0; i < invoices.size(); i++) {
                InvoiceLine invoiceLine = invoices.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == invoiceLine) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(invoiceLine);
            }
        }
        invoices = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<InvoiceLine> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!invoices.contains(data.get(i))) {
                    invoices.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }


    public void removeAllData() {
        if (invoices.size() > 0) {
            invoices.removeAll(invoices);
        }
    }
}
