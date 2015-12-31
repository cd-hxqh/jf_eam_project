package com.jf_eam_project.ui.activity;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jf_eam_project.R;
import com.jf_eam_project.manager.AppManager;
import com.jf_eam_project.ui.fragment.Inventory_fragment;
import com.jf_eam_project.ui.fragment.NavigationDrawerFragment;
import com.jf_eam_project.ui.fragment.Po_Fragment;
import com.jf_eam_project.ui.fragment.Polling_Fragment;
import com.jf_eam_project.ui.fragment.Setting_Fragment;
import com.jf_eam_project.ui.fragment.Udinspo_fragment;
import com.jf_eam_project.ui.fragment.Wfment_fragment;
import com.jf_eam_project.ui.fragment.WorkFragment;
import com.jf_eam_project.ui.widget.CustomDialog;


public class MainActivity extends BaseActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private static final String TAG = "MainActivity";
    //    private SpinnerAdapter mSpinnerAdapter;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private ViewGroup mDrawerLayout;
    private View mActionbarCustom;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private String[] mFavoriteTabTitles;
    private String[] mFavoriteTabPaths;
    private String[] mMainTitles;

    /**
     * 标题*
     */
    private TextView titleText;
    /**
     * 新增*
     */
    private ImageView addImageView;
    /**
     * 流程管理*
     */
    private Wfment_fragment wfment_fragment;
    /**
     * 工单管理*
     */
    private WorkFragment mWorkFragment;

    /**
     * 巡检管理*
     */
    private Udinspo_fragment udinspo_fragment;
    /**
     * 库存管理*
     */
    private Inventory_fragment newInventory_fragment;
    /**
     * 采购管理*
     */
    private Po_Fragment po_fragment;
    /**
     * 关于*
     */
    private Setting_Fragment settingFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();

        initView();

    }

    @Override
    protected void findViewById() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        titleText = (TextView) findViewById(R.id.title_id);
        addImageView = (ImageView) findViewById(R.id.title_add);

        mDrawerLayout = (ViewGroup) findViewById(R.id.drawer_layout);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.left_drawer);
        mTitle = getTitle();

        mMainTitles = getResources().getStringArray(R.array.drawer_tab_titles);


        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.left_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    protected void initView() {
        addImageView.setOnClickListener(addOnClickListner);
    }

    int mSelectPos = 0;

    @Override
    public void onNavigationDrawerItemSelected(final int position) {
        mSelectPos = position;
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        switch (position) {
            case 0: //流程管理
                if (wfment_fragment == null) {
                    wfment_fragment = new Wfment_fragment();
                    Bundle bundle = new Bundle();
                    wfment_fragment.setArguments(bundle);
                }
                fragmentTransaction.replace(R.id.container, wfment_fragment).commit();

                break;
            case 1: //工单管理
                if (mWorkFragment == null) {
                    mWorkFragment = new WorkFragment();
                    Bundle bundle = new Bundle();
                    mWorkFragment.setArguments(bundle);
                }
                fragmentTransaction.replace(R.id.container, mWorkFragment).commit();
                break;
            case 2: //巡检管理

                if (udinspo_fragment == null) {
                    udinspo_fragment = new Udinspo_fragment();
                    Bundle bundle = new Bundle();
                    udinspo_fragment.setArguments(bundle);
                }
                fragmentTransaction.replace(R.id.container, udinspo_fragment).commit();

                break;
            case 3:
                if (newInventory_fragment == null) {
                    newInventory_fragment = new Inventory_fragment();
                    Bundle bundle = new Bundle();
                    newInventory_fragment.setArguments(bundle);
                }
                fragmentTransaction.replace(R.id.container, newInventory_fragment).commit();

                break;
            case 4:
                if (po_fragment == null) {
                    po_fragment = new Po_Fragment();
                    Bundle bundle = new Bundle();
                    po_fragment.setArguments(bundle);
                }
                fragmentTransaction.replace(R.id.container, po_fragment).commit();
                break;
            case 5:
                if (settingFragment == null) {
                    settingFragment = new Setting_Fragment();
                    Bundle bundle = new Bundle();
                    settingFragment.setArguments(bundle);
                }
                fragmentTransaction.replace(R.id.container, settingFragment).commit();
                break;
            case 6:
                mNavigationDrawerFragment.closeDrawer();
                showAlertDialog();
                break;
        }

    }


    /**
     * 退出程序
     */
    public void showAlertDialog() {

        CustomDialog.Builder builder = new CustomDialog.Builder(MainActivity.this);
        builder.setMessage(getString(R.string.exit_dialog_hint));
        builder.setTitle(getString(R.string.exit_dialog_title));
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                AppManager.AppExit(MainActivity.this);
            }
        });

        builder.setNegativeButton(getString(R.string.canel),
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();

    }


    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        mTitle = mMainTitles[mSelectPos];
        actionBar.setDisplayShowCustomEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("");
        titleText.setText(mTitle);
        if (mSelectPos == 2) {
            addImageView.setVisibility(View.VISIBLE);
        } else {
            addImageView.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            if (mSelectPos == 3) {
//                getMenuInflater().inflate(R.menu.menu_main, menu);
            }
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if (mSelectPos == 3&&item.getItemId() == R.id.action_add) {
//            Intent intent=new Intent();
//            intent.setClass(MainActivity.this,AddinvuseActivity.class);
//            startActivityForResult(intent,0);
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    private long exitTime = 0;

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen()) {
            mNavigationDrawerFragment.closeDrawer();
            return;
        }

        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, getResources().getString(R.string.exit_text), Toast.LENGTH_LONG).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.AppExit(MainActivity.this);
        }
    }


    private View.OnClickListener addOnClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, AddUdinspoActivity.class);
            startActivityForResult(intent, 0);
        }
    };


}
