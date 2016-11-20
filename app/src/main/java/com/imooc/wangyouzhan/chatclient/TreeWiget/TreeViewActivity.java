package com.imooc.wangyouzhan.chatclient.TreeWiget;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.imooc.wangyouzhan.chatclient.R;

import java.util.ArrayList;
import java.util.List;

public class TreeViewActivity extends AppCompatActivity {


    private ListView mTree;
    private SimpleTreeListViewAdapter<FileBean> mAdapter;
    private List<FileBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_view);
        mTree = (ListView) findViewById(R.id.listview);

        initDatas();
        mAdapter = new SimpleTreeListViewAdapter<FileBean>(mTree,this,mDatas,1);
        mTree.setAdapter(mAdapter);
        onEvent();

    }

    private void onEvent() {
        mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
            @Override
            public void onClick(Node node, int position) {
                if (node.isLeaf()){
                    Toast.makeText(TreeViewActivity.this, node.getName(), Toast.LENGTH_SHORT).show();;
                }
            }
        });

        mTree.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final EditText et  = new EditText(TreeViewActivity.this);
                new AlertDialog.Builder(TreeViewActivity.this).setTitle("Add node").setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mAdapter.addExtraNode(position, et.getText().toString());
                    }
                }).setNegativeButton("cancel",null).show();

                return true;
            }
        });


    }


    private void initDatas() {

        mDatas = new ArrayList<FileBean>();
        FileBean bean = new FileBean(1, 0 ,"根目录1");
        mDatas.add(bean);
        bean = new FileBean(2, 0 ,"根目录2");
        mDatas.add(bean);
        bean = new FileBean(3, 0 ,"根目录3");
        mDatas.add(bean);
        bean = new FileBean(4, 1 ,"根目录1-1");
        mDatas.add(bean);
        bean = new FileBean(5, 1 ,"根目录1-2");
        mDatas.add(bean);
        bean = new FileBean(6, 1 ,"根目录1-2");
        mDatas.add(bean);
        bean = new FileBean(7, 5 ,"根目录1-2-1");
        mDatas.add(bean);
        bean = new FileBean(7, 3 ,"根目录1-3");
        mDatas.add(bean);

    }

}

























