package com.imooc.wangyouzhan.chatclient.TreeWiget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.imooc.wangyouzhan.chatclient.R;

import java.util.List;

/**
 * Created by wangyouzhan on 16/11/13.
 */

public class SimpleTreeListViewAdapter<T> extends TreeListViewAdapter<T> {

    public SimpleTreeListViewAdapter(ListView tree, Context context, List<T> datas, int defaultExpandLevel) {
        super(tree, context, datas, defaultExpandLevel);
    }

    @Override
    public View getConvertView(Node node, int postion, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView =  mInflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.id_item_icon);
            holder.textView = (TextView) convertView.findViewById(R.id.id_item_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (node.getIcon() == -1) {
            holder.imageView.setVisibility(View.VISIBLE);
        } else {
            holder.imageView.setVisibility(View.VISIBLE);
            holder.imageView.setImageResource(node.getIcon());
        }

        holder.textView.setText(node.getName());

        return convertView;
    }

    /**
     * 动态插入节点
     * @param position
     * @param s
     */
    public void addExtraNode(int position, String s) {

        Node node = mVisibleNodes.get(position);
        int indexOf = mAllNodes.indexOf(node);

        Node extraNode = new Node(-1, node.getId(), s);
        extraNode.setParent(node);
        node.getChildren().add(extraNode);
        mAllNodes.add(indexOf + 1, extraNode);

        mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);

        notifyDataSetChanged();

    }


    private class ViewHolder {

        ImageView imageView;
        TextView textView;

    }


}
