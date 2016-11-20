package com.imooc.wangyouzhan.chatclient.asynloader;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.imooc.wangyouzhan.chatclient.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * 1、使用的LruCache类来缓存图片,
 * 2、在listview滚动的时候停止图片下载,在listview不滚动的时候下载图片
 *      这样避免滚动卡顿的现象。(为什么出现卡顿,因为在滚动的时候,异步线程的下载好的图片要去UI
 *      线程中更新视图)
 *
 */
public class AsynLoaderActivity extends Activity {

    private ListView mListView;
    private static String URL =
            "http://www.imooc.com/api/teacher?type=4&num=30";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_three);
        mListView = (ListView) findViewById(R.id.lv_main);
        new NewsAsyncTask().execute(URL);
    }

    class NewsAsyncTask extends AsyncTask<String, Void, List<NewsBean>> {
        @Override
        protected List<NewsBean> doInBackground(String... params) {
            return getJosnData(params[0]);
        }

        @Override
        protected void onPostExecute(List<NewsBean> newsbean) {
            // TODO Auto-generated method stub
            super.onPostExecute(newsbean);
            NewsAdapter adapter = new NewsAdapter(newsbean, AsynLoaderActivity.this, mListView);
            mListView.setAdapter(adapter);
        }

        private String readString(InputStream is) {
            InputStreamReader isr;
            String result = "";
            try {
                String line = "";
                isr = new InputStreamReader(is, "utf-8");
                BufferedReader br = new BufferedReader(isr);
                while ((line = br.readLine()) != null) {
                    result += line;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }


        private List<NewsBean> getJosnData(String url) {
            List<NewsBean> newsBeanList = new ArrayList<NewsBean>();
            try {
                String jsonString = readString(new URL(url).
                        openConnection().getInputStream());
//				String jsonString = readString(new URL(url).openStream());
//				Log.i("arvin", jsonString);
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(jsonString);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        NewsBean newsBean = new NewsBean();
                        newsBean.setNewsIconUrl(jsonObject.getString("picSmall"));
                        newsBean.setNewsTitle(jsonObject.getString("name"));
                        newsBean.setNewsContent(jsonObject.getString("description"));
                        newsBeanList.add(newsBean);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newsBeanList;
        }

    }
}
