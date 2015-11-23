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
import android.widget.TextView;
import android.widget.Toast;

import com.jf_eam_project.R;
import com.jf_eam_project.manager.AppManager;
import com.jf_eam_project.ui.fragment.NavigationDrawerFragment;
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

    private TextView titleText;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();


    }

    @Override
    protected void findViewById() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        titleText=(TextView)findViewById(R.id.title_id);

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

    }

    int mSelectPos = 0;

    @Override
    public void onNavigationDrawerItemSelected(final int position) {
        mSelectPos = position;

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:

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

}
