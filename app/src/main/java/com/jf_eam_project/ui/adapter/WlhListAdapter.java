package com.jf_eam_project.ui.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.CardView;

import com.jf_eam_project.R;
import com.jf_eam_project.bean.Wlh;
import com.jf_eam_project.ui.widget.BaseViewHolder;

import java.util.List;


/**
 * Created by apple on 15/10/26
 * 选择物料
 */
public class WlhListAdapter extends BaseQuickAdapter<Wlh> {

    public WlhListAdapter(Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);

        if (index < 5)
            anim.setStartDelay(index * 150);
    }


    @Override
    protected void convert(BaseViewHolder helper, Wlh item) {
        CardView cardView = helper.getView(R.id.card_container);
        helper.setText(R.id.item_num_title, mContext.getString(R.string.xm_text));
        helper.setText(R.id.item_num_text, item.getITEMNUM());
        helper.setText(R.id.item_desc_text, item.getDESCRIPTION());

    }
}
