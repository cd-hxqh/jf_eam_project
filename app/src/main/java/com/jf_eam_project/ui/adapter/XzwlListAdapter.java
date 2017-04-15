package com.jf_eam_project.ui.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.CompoundButton;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Xzwl;
import com.jf_eam_project.ui.widget.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by apple on 15/10/26
 * 选择物料
 */
public class XzwlListAdapter extends BaseQuickAdapter<Xzwl> {
    private List data;// 原数据
    private static final String TAG = "XzwlListAdapter";
    private List<Integer> checkPositionlist = new ArrayList<>();
    private List<Xzwl> checkeddata = new ArrayList<Xzwl>();// 选中的数据

    public XzwlListAdapter(Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
        this.data=data;
    }

    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);

        if (index < 5)
            anim.setStartDelay(index * 150);
    }


    @Override
    protected void convert(final BaseViewHolder helper, final Xzwl item) {
        CardView cardView = helper.getView(R.id.card_container);
        helper.setText(R.id.item_num_text, item.getITEMNUM());
        helper.setText(R.id.item_desc_text, item.getITEMDESC());

        Log.i(TAG, "postition=" + helper.getPosition());
        helper.setTag(R.id.checkbox_id, new Integer(helper.getPosition()));
        if (checkPositionlist != null) {
            helper.setChecked(R.id.checkbox_id, (checkPositionlist.contains(new Integer(helper.getPosition())) ? true : false));
        } else {
            helper.setChecked(R.id.checkbox_id, false);
        }
        helper.setOnCheckedChangeListener(R.id.checkbox_id, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {


                        if (isChecked) {
                            if (!checkPositionlist.contains(helper.getView(R.id.checkbox_id).getTag())) {
                                checkeddata.add(item);
                                checkPositionlist.add(new Integer(helper.getPosition()));
                            }


                        } else {
                            if (checkPositionlist.contains(helper.getView(R.id.checkbox_id).getTag())) {
                                checkeddata.remove(item);
                                checkPositionlist.remove(new Integer(helper.getPosition()));
                            }

                        }

                    }
                }


        );
    }


    public interface MycheckListener {
        void getcheckeddata(List<Xzwl> listBean);
    }

    public void getdata(MycheckListener listener) {
        listener.getcheckeddata(checkeddata);
    }
}
