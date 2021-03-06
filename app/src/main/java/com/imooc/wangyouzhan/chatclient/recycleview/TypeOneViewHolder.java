package com.imooc.wangyouzhan.chatclient.recycleview;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.imooc.wangyouzhan.chatclient.R;

/**
 * Created by wangyouzhan on 2016/11/14.
 * Email 936804097@qq.com
 */

public class TypeOneViewHolder extends TypeAbstractViewHolder<DataModelOne> {

    public ImageView avatar;
    public TextView name;


    public TypeOneViewHolder(View itemView) {
        super(itemView);
        itemView.setBackgroundColor(Color.CYAN);
        avatar = (ImageView) itemView.findViewById(R.id.avatar);
        name = (TextView) itemView.findViewById(R.id.name);
    }


    @Override
    public void bindHolder(DataModelOne model) {
        avatar.setBackgroundResource(model.avatarColor);
        name.setText(model.name);
    }


}
















