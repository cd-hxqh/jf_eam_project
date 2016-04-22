package com.jf_eam_project.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jf_eam_project.R;
import com.jf_eam_project.imagetools.ImageBean;
import com.jf_eam_project.imagetools.ImageSort;
import com.jf_eam_project.ui.adapter.ChildAdapter;
import com.jf_eam_project.ui.adapter.GroupImageAdapter;
import com.jf_eam_project.utils.Bimp;
import com.jf_eam_project.utils.PreferenceUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

@SuppressLint({"ResourceAsColor", "HandlerLeak"})
@SuppressWarnings("unused")
public class ShowImageActivity extends Activity implements OnItemClickListener, OnClickListener {
    private static final String TAG = "ShowImageActivity";
    /**
     * 拍照的照片存储位置
     */
    private static final File PHOTO_DIR = new File(Environment.getExternalStorageDirectory()
            + "/Camera/");
    /**
     * 用来标识请求照相功能的activity
     */
    public static final int CAMERA_WITH_DATA = 168;
    private static File mCurrentPhotoFile;// 照相机拍照得到的图片
    private GridView mGridView;
    private LinkedList<String> list, chileList, mPauseList;
    private LinkedList<ImageBean> mBeenList;
    private ChildAdapter adapter;
    private int flag;
    private HashMap<String, LinkedList<String>> mGruopMap = new HashMap<String, LinkedList<String>>();
    private final static int SCAN_OK = 1;
    private Context mContext;
    private Button upButton, imageview_select;
    private PopupWindow mPopupWindow;
    private int windowWitdh, windowHeight;
    private RelativeLayout mLayout;
    private Button mOkButton;
    /**返回按钮**/
    private ImageView mCancelButton;
    private ArrayList<File> fileList;
    private ImageLoader imageLoader;
    private PreferenceUtil preferenceUtil;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SCAN_OK:
                    mBeenList = subGroupOfImage(mGruopMap);
                    upButton.setText("全部图片(" + (list.size() - 1) + ")");
                    adapter = new ChildAdapter(mContext, list, mGridView);
                    mGridView.setAdapter(adapter);
                    adapter.setListCallback(new ChildAdapter.ListCallback() {

                        @Override
                        public void onListener(List<String> path, String pathStr) {
                            if (path != null && path.size() > 0) {
                                mPauseList.add(path.get(0));
                            } else {
                                mPauseList.remove(pathStr);
                            }
                        }
                    });
                    adapter.setTextCallback(new ChildAdapter.TextCallback() {

                        @Override
                        public void onListen(int count) {
                            if (count > 0) {
                                mOkButton.setEnabled(true);
                                mOkButton.setBackgroundColor(Color.TRANSPARENT);
                                if ((20 - Bimp.drr.size()) > 0)
                                    mOkButton.setText("(" + count + "/" + (20 - Bimp.drr.size()) + ") 确定");
                                else
                                    mOkButton.setText("(" + count + "/" + 20 + ") 完成");

                            } else {
                                mOkButton.setEnabled(false);
                                mOkButton.setBackgroundColor(Color.TRANSPARENT);
                                mOkButton.setText("确定");
                            }
                        }
                    });
                    //关闭进度条
                    break;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mContext = this;
        setContentView(R.layout.show_image_activity);
        if (imageLoader != null) {
            imageLoader.clearMemoryCache();
            System.gc();
        }
        initViews();
    }

    private void initViews() {
        Intent intent = getIntent();
        Bundle bun = intent.getExtras();
        if (bun != null) {
            flag = bun.getInt("flag");
        }
        imageview_select = (Button) findViewById(R.id.imageview_select);
        preferenceUtil = PreferenceUtil.getInstance(mContext);
        preferenceUtil.saveString("path_showImage", "1");
        fileList = new ArrayList<File>();
        mPauseList = new LinkedList<String>();
        mGridView = (GridView) findViewById(R.id.gridview_child);
        upButton = (Button) findViewById(R.id.imageview_up_select);
        mLayout = (RelativeLayout) findViewById(R.id.bottom_nav_relative);
        mOkButton = (Button) findViewById(R.id.registerbut_navigation);
        mOkButton.setEnabled(false);
        mOkButton.setBackgroundColor(Color.TRANSPARENT);
        mOkButton.setText("确定");
        mCancelButton = (ImageView) findViewById(R.id.tv_cancel_navigation);
        mGridView.setOnItemClickListener(this);
        list = new LinkedList<String>();
        //		list.add("");
        getImages();
        mCancelButton.setOnClickListener(this);
        upButton.setOnClickListener(this);
        mOkButton.setOnClickListener(this);
        imageview_select.setOnClickListener(this);

    }

	/*.
     * 点击事件
	 */

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.imageview_up_select:
                getPopWindows();
                break;
            case R.id.registerbut_navigation:
                //确定按钮的事件
                if (mPauseList != null && Bimp.drr != null) {
                    Bimp.drr.addAll(mPauseList);
                }
                if (Bimp.mHashMap != null) {
                    Bimp.mHashMap.clear();
                }
                if (flag == 10) {
                    finish();
                } else {
                    Toast.makeText(mContext, "跳转", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_cancel_navigation:
                if (Bimp.mHashMap != null) {
                    Bimp.mHashMap.clear();
                }
                finish();
                ImageLoader.getInstance().clearMemoryCache();
                System.gc();
                break;
            case R.id.imageview_select:
                if (mPauseList == null || mPauseList.size() <= 0) {
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", mPauseList);
                Intent intent = new Intent(mContext, ViewPagerActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;

        }
    }

    @SuppressWarnings("deprecation")
    private void getPopWindows() {
        View contentView = setPopWindowViews();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        windowWitdh = dm.widthPixels;
        windowHeight = dm.heightPixels;
        mPopupWindow = new PopupWindow(contentView, windowWitdh, windowHeight * 2 / 3);
        //getWindow().getDecorView()
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setAnimationStyle(R.style.PopupAnimation);
        int[] location = new int[2];
        mLayout.getLocationOnScreen(location);
        mPopupWindow.showAtLocation(mLayout, Gravity.NO_GRAVITY, location[0], location[1] - mPopupWindow.getHeight());
    }

    private View setPopWindowViews() {
        // TODO Auto-generated method stub
        View contentView = getLayoutInflater().inflate(R.layout.popwindow_layout, null);
        ListView popWindowListView = (ListView) contentView.findViewById(R.id.listview_popwindow);
        if (mGruopMap != null && mGruopMap.size() > 0) {
            //			Log.d("TAG", "size: "+mBeenList.size());

            GroupImageAdapter imageAdapter = new GroupImageAdapter(mBeenList, mContext, popWindowListView);
            popWindowListView.setAdapter(imageAdapter);
        } else {
            Log.e("", "image is null");
        }
        if ("1".equals(preferenceUtil.getString("path_showImage", "")) && mBeenList != null && mBeenList.size() > 0) {
            preferenceUtil.saveString("path_showImage", mBeenList.get(1).getTopImagePath());
        }
        popWindowListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                String path = mBeenList.get(arg2).getTopImagePath();
                preferenceUtil.saveString("path_showImage", path);
                ImageView imageOK = (ImageView) arg1.findViewById(R.id.imageViewOk);
                imageOK.setVisibility(View.VISIBLE);
                String fileName = mBeenList.get(arg2).getFolderName();

                if ("全部图片".equals(fileName)) {
                    //					list.clear();
                    //					list.add("");
                    getImages();
                    //					upButton.setText(fileName+"("+list.size()+")");
                    //					Log.d("TAG", "size :"+list.size());
                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
                    list.clear();
                } else if ("最近图片".equals(fileName)) {
                    list = mGruopMap.get("Camera");
                    //					upButton.setText(fileName+"("+list.size()+")");
                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
                } else {
                    list = mGruopMap.get(fileName);

                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
                }
                upButton.setText(fileName + "(" + list.size() + ")");
                mPopupWindow.dismiss();
            }
        });
        return contentView;
    }

    /**
     * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中
     */
    private void getImages() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //			showShortToast("暂无外部存储");
            //			return;
        }

        //显示进度条

        new Thread(new Runnable() {

            @Override
            public void run() {
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = mContext.getContentResolver();
                //只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaColumns.MIME_TYPE + "=? or "
                                + MediaColumns.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"}, MediaColumns.DATE_MODIFIED);
                mGruopMap.clear();

                while (mCursor.moveToNext()) {
                    //获取图片的路径
                    String path = mCursor.getString(mCursor
                            .getColumnIndex(MediaColumns.DATA));
                    //获取该图片的父路径名
                    String parentName = new File(path).getParentFile().getName();
                    if (!mGruopMap.containsKey(parentName)) {
                        chileList = new LinkedList<String>();
                        chileList.add(path);
                        mGruopMap.put(parentName, chileList);
                    } else {
                        if (!chileList.contains(path)) {
                            mGruopMap.get(parentName).add(path);
                        }
                    }
                }

                mCursor.close();
                mGruopMap.keySet();
                Iterator<Entry<String, LinkedList<String>>> it = mGruopMap.entrySet().iterator();
                List<ImageSort> sortList = new LinkedList<ImageSort>();
                List<String> pathArray = new LinkedList<String>();
                while (it.hasNext()) {
                    Entry<String, LinkedList<String>> entry = it.next();
                    String key = entry.getKey();
                    List<String> value = entry.getValue();
                    if ("Camera".equals(key)) {

                        for (String path : value) {
                            File fileImage = new File(path);
                            ImageSort sort = new ImageSort();
                            sort.path = fileImage.getAbsolutePath();
                            sort.lastModified = fileImage.lastModified();
                            sortList.add(sort);
                        }
                        Collections.sort(sortList, new ImageSort());
                        for (int i = 0; i < sortList.size(); i++) {
                            pathArray.add(sortList.get(i).path);

                            Log.i(TAG, "");
                        }
                        list.addAll(0, pathArray);
                    } else {
                        list.addAll(value);
                    }
                }

                //通知Handler扫描图片完成
                mHandler.sendEmptyMessage(SCAN_OK);

            }
        }).start();

    }

    @Override
    public void onBackPressed() {
        //		Toast.makeText(this, "选中 " + adapter.getSelectItems().size() + " item", Toast.LENGTH_LONG).show();
        super.onBackPressed();
    }


    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub//&& SDK16.getAndroidSDKVersion() >= 11
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", list);
        //			bundle.putStringArrayList("list", list);
        bundle.putInt("position", arg2 - 1);
        Intent intent = new Intent(mContext, ViewPagerActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    /**
     * 获取原图片存储路径
     *
     * @return
     */
    private String getPhotopath() {
        // 文件夹路径
        if (!PHOTO_DIR.exists()) {
            PHOTO_DIR.mkdirs();
        }
        mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());
        String pathUrl = mCurrentPhotoFile.getAbsolutePath();
        try {
            mCurrentPhotoFile.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pathUrl;
    }

    /**
     * 拍照获取图片
     */
    private void doTakePhoto() {
        //		Intent it = new Intent("android.media.action.IMAGE_CAPTURE");
        //		it.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        //		File out = new File(getPhotopath());
        //		Uri uri = Uri.fromFile(out);
        //		// 获取拍照后未压缩的原图片，并保存在uri路径中
        //		it.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        //		startActivityForResult(it, Activity.DEFAULT_KEYS_DIALER);


        Intent intentPhote = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentPhote.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        File out = new File(getPhotopath());
        Uri uri = Uri.fromFile(out);
        // 获取拍照后未压缩的原图片，并保存在uri路径中
        intentPhote.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        //         intentPhote.putExtra(MediaStore.Images.Media.ORIENTATION, 180);
        startActivityForResult(intentPhote, 2000);

        //		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //		startActivityForResult(intent, 1);

        //		if(mBeenList != null){
        //
        //			String imagePath = mBeenList.get(0).getTopImagePath();
        //			int charPosition = imagePath.lastIndexOf("/");
        //			String newStr = imagePath.substring(0, charPosition);
        //			File dir = new File(newStr);
        //			try {
        //				if (!dir.exists())
        //					dir.mkdirs();// 创建照片的存储目录
        //				mCurrentPhotoFile = new File(dir, getPhotoFileName());// 给新照的照片文件命名
        //				Intent it = new Intent("android.media.action.IMAGE_CAPTURE");
        //				it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCurrentPhotoFile));
        //				startActivityForResult(it, Activity.DEFAULT_KEYS_DIALER);
        //			} catch (Exception e) {
        //				showShortToast("没有发现图片");
        //			}
        //		}else{
        //			try {
        //				if (!PHOTO_DIR.exists()){
        //					PHOTO_DIR.mkdirs();// 创建照片的存储目录
        //				}
        //						mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());// 给新照的照片文件命名
        //				Intent it = new Intent("android.media.action.IMAGE_CAPTURE");
        //				it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCurrentPhotoFile));
        //				startActivityForResult(it, Activity.DEFAULT_KEYS_DIALER);
        //			} catch (Exception e) {
        //				showShortToast("没有发现图片");
        //
        //			}
        //		}
        //		Log.e("TAG", newStr);
    }

    @SuppressLint("SimpleDateFormat")
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return "/" + dateFormat.format(date) + ".jpg";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2000 && resultCode == Activity.RESULT_OK) {

            String sdStatus = Environment.getExternalStorageState();
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用

                return;
            }
            String fileName = "";
            FileOutputStream b = null;
            try {
                Log.e("TAG", mCurrentPhotoFile.getAbsolutePath());
                //				Bitmap bitmap = Bimp.getImage(mCurrentPhotoFile.getAbsolutePath());
//				Bitmap bitmap = Bimp.getImage(mCurrentPhotoFile.getAbsolutePath());
//
//				fileName = mCurrentPhotoFile.getAbsolutePath();  
//				/*	int degree = FileUtils.readPictureDegree(fileName);  
//				Bitmap newbitmap = FileUtils.rotaingImageView(degree, bitmap); 
//				//				
//				if(bitmap != newbitmap && bitmap != null && !bitmap.isRecycled())  
//				{  
//					bitmap.recycle();  
//					bitmap = null;  
//				}  */
//				b = new FileOutputStream(fileName);  
//				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件  
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (b != null) {
                        b.flush();
                        b.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //		            ((ImageView) findViewById(R.id.imageView)).setImageBitmap(bitmap);// 将图片显示在ImageView里
            /* Log.e("TAG", "222222222222"+path);
	     list.set(0, path);
	     adapter.setList(list);
	     adapter.notifyDataSetChanged();*/
//			if(resultCode == RESULT_OK){
//				Bundle bun = new Bundle();
//				bun.putString("newPath", fileName);
//				bun.putInt("flag", flag);
//				Intent intent = new Intent(mContext ,ShowPhotoActivity.class);
//				intent.putExtras(bun);
//				startActivity(intent);
//				finish();
//			}
        }
    }


    /**
     * 组装分组界面GridView的数据源，因为我们扫描手机的时候将图片信息放在HashMap中
     * 所以需要遍历HashMap将数据组装成List
     *
     * @param mGruopMap
     * @return
     */
    private LinkedList<ImageBean> subGroupOfImage(HashMap<String, LinkedList<String>> mGruopMap) {
        if (mGruopMap.size() == 0) {
            return null;
        }
        LinkedList<ImageBean> listLink = new LinkedList<ImageBean>();
        mGruopMap.keySet();
        //		ImageBean bean = new ImageBean(list.get(0), "全部图片", 0);//这里是全部图片设置
        //		listLink.add(bean);
        Iterator<Entry<String, LinkedList<String>>> it = mGruopMap.entrySet().iterator();

        while (it.hasNext()) {
            Entry<String, LinkedList<String>> entry = it.next();
            ImageBean mImageBean = new ImageBean();
            String key = entry.getKey();
            List<String> value = entry.getValue();

            mImageBean.setFolderName(key);
            mImageBean.setImageCounts(value.size());
            mImageBean.setTopImagePath(value.get(0));//获取该组的第一张图片
            if ("Camera".equals(mImageBean.getFolderName())) {
                mImageBean.setFolderName("最近图片");
                listLink.addFirst(mImageBean);
            } else {
                listLink.add(mImageBean);
            }
        }
        ImageBean bean = new ImageBean(list.get(0), "全部图片", 0);//这里是全部图片设置
        if (listLink.size() > 1) {
            listLink.set(1, bean);
        } else {
            listLink.add(bean);
        }

        return listLink;

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (Bimp.mHashMap != null) {
                Bimp.mHashMap.clear();
            }
            finish();
            ImageLoader.getInstance().clearMemoryCache();
            System.gc();
            return true;
        }
        return true;
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if (imageLoader != null) {
            imageLoader.clearMemoryCache();
        }
        System.gc();
    }

}
