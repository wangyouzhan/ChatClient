package com.imooc.wangyouzhan.chatclient.recycleview;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.imooc.wangyouzhan.chatclient.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能优先,性能是没有达到一定的瓶颈的话,是不必要的。
 * 设计的优雅,主要是数据结构的优雅。
 *
 */
public class RecycleViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DemoAdapter mAdapter;

    int colors[] = {android.R.color.holo_red_dark,
            android.R.color.holo_blue_dark,
            android.R.color.holo_orange_dark};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view2);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = mRecyclerView.getAdapter().getItemViewType(position);
                if (type == DataModel.TYPE_THREE){
                    return gridLayoutManager.getSpanCount();
                }else {
                    return 1;
                }
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new DemoAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        //设置item的间隔
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration(){
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams)view.getLayoutParams();
                int spanSize  = layoutParams.getSpanSize();
                int spanIndex = layoutParams.getSpanIndex();
                outRect.top = 20;
                if (spanSize != gridLayoutManager.getSpanCount()){
                    if (spanIndex == 1){
                        outRect.left = 10;
                    }else{
                        outRect.right = 20;
                    }
                }

            }
        });

        initData();


    }

    private void initData() {

        List<DataModel> list = new ArrayList<DataModel>();

        for (int i = 0; i < 30; i++) {

//            int type = (int) ((Math.random() * 3) + 1);
            int type;
            if (i < 5 || (i > 15 && i < 20)){
                type = 1;
            }else if(i < 10 || i > 26){
                type = 2;
            }else {
                type = 3;
            }
            DataModel data = new DataModel();
            data.avatarColor = colors[type - 1];
            data.type = type;
            data.name = "name : " + i;
            data.content = "content : " + i;
            data.contentColor = colors[(type + 1) % 3];
            list.add(data);
        }


        List<DataModelOne> list1 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            DataModelOne data = new DataModelOne();
            data.avatarColor = colors[0];
            data.name = "name : " + 1;
            list1.add(data);
        }

        List<DataModelTwo> list2 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            DataModelTwo data = new DataModelTwo();
            data.avatarColor = colors[0];
            data.name = "name : " + 1;
            data.content = "content ";
            list2.add(data);
        }

        List<DataModelThree> list3 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            DataModelThree data = new DataModelThree();
            data.avatarColor = colors[0];
            data.name = "name : " + 1;
            data.content = "content ";
            data.contentColor = colors[2];
            list3.add(data);
        }



        mAdapter.addList(list1,list2,list3);
        mAdapter.notifyDataSetChanged();

    }
}





















