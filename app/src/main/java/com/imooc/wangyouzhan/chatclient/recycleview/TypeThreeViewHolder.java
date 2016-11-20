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

public class TypeThreeViewHolder extends TypeAbstractViewHolder<DataModelThree> {

    public ImageView avatar;
    public TextView name;

    public TextView content;
    public ImageView contentImage;



    public TypeThreeViewHolder(View itemView) {
        super(itemView);

        itemView.setBackgroundColor(Color.GREEN);
        avatar = (ImageView) itemView.findViewById(R.id.avatar);
        name = (TextView) itemView.findViewById(R.id.name);
        content = (TextView) itemView.findViewById(R.id.content);
        contentImage = (ImageView) itemView.findViewById(R.id.contentImage);
    }


    @Override
    public void bindHolder(DataModelThree model){
        avatar.setBackgroundResource(model.avatarColor);
        name.setText(model.name);
        contentImage.setBackgroundResource(model.contentColor);
        content.setText(model.content);
    }



}
















