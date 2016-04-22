package com.jf_eam_project.ui.adapter;

import java.util.HashMap;
import java.util.LinkedList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.imagetools.ImageBean;
import com.jf_eam_project.utils.ImageDownLoader;
import com.jf_eam_project.utils.PreferenceUtil;

@SuppressWarnings("unused")
public class GroupImageAdapter extends BaseAdapter {

	private Point mPoint = new Point(0, 0);//用来封装ImageView的宽和高的对象
	private LinkedList<ImageBean> mBeanList;
	private LayoutInflater mInflater;
	private ListView mListView;
	private Context mContext;
	private PreferenceUtil preferenceUtil;
	private HashMap<Integer, View> stateMap;
	@SuppressLint("UseSparseArrays")
	public GroupImageAdapter(LinkedList<ImageBean> mBeenList,Context context,ListView listview){
		this.mBeanList = mBeenList;
		this.mInflater = LayoutInflater.from(context);
		this.mListView = listview;
		this.mContext = context;
		this.preferenceUtil = PreferenceUtil.getInstance(mContext);
		this.stateMap = new HashMap<Integer, View>();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mBeanList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mBeanList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, final ViewGroup parent) {
		// TODO Auto-generated method stub
		View rootView = convertView;
		ViewHolder holder = null;
		if(rootView == null){
			holder = new ViewHolder();
			rootView = mInflater.inflate(R.layout.popwindow_item_layout, null);
			holder.mMyImageView = (ImageView) rootView.findViewById(R.id.imageView_parent_show);
			holder.mNameText = (TextView) rootView.findViewById(R.id.textView_parent_name);
			holder.mNumText = (TextView) rootView.findViewById(R.id.textView_nums);
			holder.mOkImage = (ImageView) rootView.findViewById(R.id.imageViewOk);
			rootView.setTag(holder);
		}else{
			holder = (ViewHolder) rootView.getTag();
		}
		ImageBean bean = mBeanList.get(position);
		String path = bean.getTopImagePath();
		if(path.equals(preferenceUtil.getString("path_showImage", ""))){
			holder.mOkImage.setVisibility(View.VISIBLE);
		}else{
			holder.mOkImage.setVisibility(View.GONE);
		}


		/*BitmapUtils bitmapUtils = new BitmapUtils(mContext);
		bitmapUtils.configDefaultLoadingImage(R.drawable.friends_sends_pictures_no);
		bitmapUtils.configDefaultLoadFailedImage(R.drawable.friends_sends_pictures_no);

		// 加载本地图片(路径以/开头， 绝对路径)
		bitmapUtils.display(holder.mMyImageView, bean.getTopImagePath());
		// 使用ListView等容器展示图片时可通过PauseOnScrollListener控制滑动和快速滑动过程中时候暂停加载图片
		mListView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
		 */
		
		if("".equals(path)){
			ImageDownLoader.showLocationImage(mBeanList.get(0).getTopImagePath(),
					holder.mMyImageView, R.mipmap.ic_launcher);
		}else{
			ImageDownLoader.showLocationImage(path,
					holder.mMyImageView, R.mipmap.ic_launcher);
		}
		/*holder.mMyImageView.setTag(path);
				//利用NativeImageLoader类加载本地图片
				Bitmap bitmap = NativeImageLoader.getInstance().loadNativeImage(path, mPoint, new NativeImageCallBack() {

					@Override
					public void onImageLoader(Bitmap bitmap, String path) {
						ImageView mImageView = (ImageView) parent.findViewWithTag(path);
						if(bitmap != null && mImageView != null){
							mImageView.setImageBitmap(bitmap);
						}
					}
				});

				if(bitmap != null){
					holder.mMyImageView.setImageBitmap(bitmap);
				}else{
					holder.mMyImageView.setImageResource(R.drawable.friends_sends_pictures_no);
				}*/
		holder.mNameText.setText(bean.getFolderName());
		if(bean.getImageCounts() != 0){
			holder.mNumText.setText(bean.getImageCounts()+"");
		}else{
			holder.mNumText.setText("");
		}

		return rootView;
	}

	static class ViewHolder{
		private ImageView mMyImageView;
		private ImageView mOkImage;
		private TextView mNameText,mNumText;
	}

}
