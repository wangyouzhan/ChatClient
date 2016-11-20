package com.imooc.wangyouzhan.chatclient.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wangyouzhan on 2016/11/14.
 * Email 936804097@qq.com
 */

public abstract class TypeAbstractViewHolder<T> extends RecyclerView.ViewHolder {

    public TypeAbstractViewHolder(View itemView) {
        super(itemView);

    }


    public abstract void bindHolder(T model);



}
















