package com.imooc.wangyouzhan.chatclient.asynloader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.imooc.wangyouzhan.chatclient.R;

import java.util.List;

public class NewsAdapter extends BaseAdapter implements OnScrollListener {

    private List<NewsBean> mList;
    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;
    private int mStart, mEnd;
    public static String[] URLS;
    private boolean mFirstIn;

    public NewsAdapter(List<NewsBean> data, Context context, ListView listview) {
        mList = data;
        mInflater = LayoutInflater.from(context);
        mImageLoader = new ImageLoader(listview);
        URLS = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            URLS[i] = data.get(i).getNewsIconUrl();
        }
        mFirstIn = true;
        listview.setOnScrollListener(this);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_layout, parent, false);
            holder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ivIcon.setImageResource(R.mipmap.icon_app);

        String url = mList.get(position).newsIconUrl;

        //这里为每一个Imageview设置tag
        holder.ivIcon.setTag(url);
//		new ImageLoader().showImageByThread(holder.ivIcon,
//				mList.get(position).newsIconUrl);
        mImageLoader.showImageByAsyncTask(holder.ivIcon, url);

        holder.tvTitle.setText(mList.get(position).getNewsTitle());
        holder.tvContent.setText(mList.get(position).getNewsContent());
        return convertView;
    }

    class ViewHolder {
        TextView tvTitle, tvContent;
        ImageView ivIcon;
    }


    /**
     * onscroll 会执行很多次,而onScrollStateChanged只有滚动的时候才调用。
     * 将显示图片的逻辑移动到滑动完成的时候,解决滑动过程卡顿的问题
     * @param view
     * @param firstVisibleItem
     * @param visibleItemCount
     * @param totalItemCount
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mStart = firstVisibleItem;
        mEnd = firstVisibleItem + visibleItemCount;
        //第一次启动的时候并且第一屏的item被画出来了。
        if (mFirstIn && visibleItemCount > 0) {
            mImageLoader.loadImages(mStart, mEnd);
            mFirstIn = false;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        /**
         * 当listview滚动状态的时候,停止下载图片;
         * 当listview停止滚动的时候,开启图片下载。
         * 这样避免滚动的时候下载完成的图片在UI线程中更新View,从而产生卡顿的bug
         *
         */
        if (scrollState == SCROLL_STATE_IDLE) {
            mImageLoader.loadImages(mStart, mEnd);
        } else {
            //停止任务
            mImageLoader.cancelAllTast();
        }
    }

}
