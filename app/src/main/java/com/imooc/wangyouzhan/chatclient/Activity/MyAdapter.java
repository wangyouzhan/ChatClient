package com.imooc.wangyouzhan.chatclient.Activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imooc.wangyouzhan.chatclient.R;

/**
 * Created by wangyouzhan on 16/11/8.
 */
class MyAdapter extends RecyclerView.Adapter {
    class ViewHolder extends RecyclerView.ViewHolder {

        private View root;
        private TextView tvTitle,tvContent;

        public ViewHolder(View root) {
            super(root);

            tvTitle = (TextView)root.findViewById(R.id.tvTitle);
            tvContent= (TextView)root.findViewById(R.id.tvContent);
        }


        public TextView getTvContent() {
            return tvContent;
        }

        public TextView getTvTitle() {
            return tvTitle;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_view, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder vh = (ViewHolder) holder;

        CellData temp = data[position];
        vh.getTvTitle().setText(temp.title);
        vh.getTvContent().setText(temp.content);


    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    private CellData[] data = new CellData[]{new CellData("极客学院", "IT职业"),new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
            ,new CellData("新闻","这个新闻真不错")
    };
}



























