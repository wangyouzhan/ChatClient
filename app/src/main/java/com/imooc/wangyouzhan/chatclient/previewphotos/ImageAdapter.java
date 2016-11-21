package com.imooc.wangyouzhan.chatclient.previewphotos;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.imooc.wangyouzhan.chatclient.R;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wangyouzhan on 2016/11/20.
 * Email 936804097@qq.com
 */
 public class ImageAdapter extends BaseAdapter {


    private static final String TAG = "ImageAdapter";
    private static Set<String> mSeletedImg = new HashSet<String>();
    private String mDirPath;
    private List<String> mImgPaths;
    private LayoutInflater mInflater;

    public ImageAdapter(Context context, List<String> mDatas, String dirPath) {
        this.mDirPath = dirPath;
        this.mImgPaths = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mImgPaths.size();
    }

    @Override
    public Object getItem(int position) {
        return mImgPaths.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_photo, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.mImg = (ImageView) convertView.findViewById(R.id.id_item_image);
            viewHolder.mSelect = (ImageButton) convertView.findViewById(R.id.id_item_select);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //重置状态
        viewHolder.mImg.setImageResource(R.mipmap.ic_launcher);
        viewHolder.mSelect.setImageResource(R.mipmap.activity_star_nomal);
        viewHolder.mImg.setColorFilter(null);


        Imageloader.getInstance(3, Imageloader.Type.FIFO).loadImage(mDirPath + "/" + mImgPaths.get(position), viewHolder.mImg);

        Log.d(TAG, "getView: ----------------------------:000" + mDirPath + "/" + mImgPaths.get(position));
        final   String filePath = mDirPath + "/" + mImgPaths.get(position);

        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mSeletedImg.contains(filePath))
                {
                    mSeletedImg.remove(filePath);
                    finalViewHolder.mImg.setColorFilter(null);
                    finalViewHolder.mSelect.setImageResource(R.mipmap.activity_star_nomal);

                }else{//未被选择
                    mSeletedImg.add(filePath);
                    finalViewHolder.mImg.setColorFilter(Color.parseColor("#77000000"));
                    finalViewHolder.mSelect.setImageResource(R.mipmap.activity_star_active);
                }
//                notifyDataSetChanged();
            }
        });

        if (mSeletedImg.contains(filePath)){
            viewHolder.mImg.setColorFilter(Color.parseColor("#77000000"));
            viewHolder.mSelect.setImageResource(R.mipmap.activity_star_active);
        }

        return convertView;
    }

    private class ViewHolder
    {
        ImageView mImg;
        ImageButton mSelect;
    }


}