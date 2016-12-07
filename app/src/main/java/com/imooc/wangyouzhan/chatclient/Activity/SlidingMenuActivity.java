package com.imooc.wangyouzhan.chatclient.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.imooc.wangyouzhan.chatclient.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlidingMenuActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{


    private DrawerLayout drawerLayout;
    private FrameLayout flContent;
    private ListView lv;

    private List<String> datas;
    private ArrayAdapter<String> adapter;
    private String title;

    private String[] cities;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_menu);

        drawerLayout = (DrawerLayout) findViewById(R.id.activity_sliding_menu);
        lv = (ListView) findViewById(R.id.listview);
        flContent  = (FrameLayout) findViewById(R.id.flContent);

        datas = new ArrayList<>();
        cities = new String[]{"上海","山东","浙江","江苏"};
        datas.addAll(Arrays.asList(cities));

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,datas);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle("请选择城市");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(title);
                invalidateOptionsMenu();

            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        getSupportActionBar().setTitle(datas.get(position));

        Fragment fragment = new SlibingFragment();
        Bundle args = new Bundle();
        args.putString("text", datas.get(position));
        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fragment).commit();

        drawerLayout.closeDrawer(lv);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        boolean isDrawOpen = drawerLayout.isDrawerOpen(lv);
        menu.findItem(R.id.action_websearch).setVisible(!isDrawOpen);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }else {

            return super.onOptionsItemSelected(item);
        }
    }
}


































