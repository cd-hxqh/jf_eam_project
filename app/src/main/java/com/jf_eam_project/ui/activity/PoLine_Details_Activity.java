package com.jf_eam_project.ui.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Po;
import com.jf_eam_project.model.PoLine;

/**�ɹ�����������**/
public class PoLine_Details_Activity extends BaseActivity {
    private static final String TAG = "PoLine_Details_Activity";

    /**
     * ����*
     */
    private TextView titleView;
    /**
     * ���ذ�ť*
     */
    private ImageView backImageView;
    /**������Ϣ��ʾ**/
    private TextView polinenumText; //��
    private TextView linetypeText; //������
    private TextView itemnumText; //��Ŀ
    private TextView descriptionText; //��Ŀ����
    private TextView conversionText; //����ϵ��
    private TextView categoryText; //���
    private TextView orderqtyText; //����
    private TextView orderunitText; //������λ
    private TextView enterbyText; //������
    private TextView enterdateText; //��������
    private TextView requestedbyText; //������
    private TextView shiptoattnText; //������
    private TextView tositeidText; //�����ص�

    /**�ɹ�������**/
    private PoLine poLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_po_line);
        initData();
        findViewById();
        initView();
    }

    private void initData() {
        poLine = (PoLine) getIntent().getSerializableExtra("poLine");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);


        polinenumText = (TextView) findViewById(R.id.po_ponum_text);
        linetypeText = (TextView) findViewById(R.id.poline_linetype_text);
        itemnumText = (TextView) findViewById(R.id.poline_itemnum_text);
        descriptionText = (TextView) findViewById(R.id.poline_description_text);
        conversionText = (TextView) findViewById(R.id.poline_conversion_text);
        categoryText = (TextView) findViewById(R.id.poline_category_text);
        orderqtyText = (TextView) findViewById(R.id.poline_orderqty_text);
        orderunitText = (TextView) findViewById(R.id.poline_orderunit_text);
        enterbyText = (TextView) findViewById(R.id.poline_enterby_text);
        enterdateText = (TextView) findViewById(R.id.poline_enterdate_text);
        requestedbyText = (TextView) findViewById(R.id.poline_requestedby_text);
        shiptoattnText = (TextView) findViewById(R.id.poline_shiptoattn_text);
        tositeidText = (TextView) findViewById(R.id.po_pretaxtotal_text);
    }

    @Override
    protected void initView() {
        titleView.setText(getResources().getString(R.string.title_activity_po_line_details));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);

    }


    /**
     * ���ذ�ť*
     */
    private View.OnClickListener backImageViewOnClickListenrer = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
