package com.example.letsseatinmetro.Activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.letsseatinmetro.CardItem.MainCardItem;
import com.example.letsseatinmetro.Adapters.MainRecyclerAdapter;
import com.example.letsseatinmetro.R;
import com.example.letsseatinmetro.Network.Remote;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //public static TextView textView;
   // public Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /*btn = (Button) findViewById(R.id.button);
        /extView = (TextView) findViewById(R.id.textView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getApi();
            }
        });*/

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<MainCardItem> items = new ArrayList<>();
        MainCardItem[] item = new MainCardItem[10];
        item[0] = new MainCardItem("1호선", R.drawable.metro, "#1");
        item[1] = new MainCardItem("2호선",R.drawable.metro, "#2");
        item[2] = new MainCardItem("3호선",R.drawable.metro, "#3");
        item[3] = new MainCardItem("4호선",R.drawable.metro, "#4");
        item[4] = new MainCardItem("5호선",R.drawable.metro, "#5");
        item[5] = new MainCardItem("6호선",R.drawable.metro, "#5");
        item[6] = new MainCardItem("7호선",R.drawable.metro, "#5");
        item[7] = new MainCardItem("8호선",R.drawable.metro, "#5");
        item[8] = new MainCardItem("9호선",R.drawable.metro, "#5");
        item[9] = new MainCardItem("10호선",R.drawable.metro, "#5");


        for (int i = 0; i < 10; i++) {
            items.add(item[i]);
        }

        recyclerView.setAdapter(new MainRecyclerAdapter(getApplicationContext(), items, R.layout.activity_main));
    }

    public void getApi(){
        new MyTask(this).execute();
    }
    private class MyTask extends AsyncTask<Void, Void, String>{
        private WeakReference<MainActivity> activityReference;
        MyTask(MainActivity context){
            activityReference = new WeakReference<>(context);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            StringBuffer sb = new  StringBuffer();

            try {
                JSONObject json = new JSONObject(s);
                JSONArray rows = json.getJSONArray("realtimeArrivalList");

                int length = 0;
                length = rows.length();

                for(int i=0; i < length; i ++){
                    JSONObject result = (JSONObject) rows.get(i);
                    String trainName = result.getString("trainLineNm");
                    sb.append(trainName + "\n");
                }

            }catch (Exception e ){}

            //textView.setText(sb.toString());
        }

        @Override
        protected String doInBackground(Void... params) {
            String result = "";
            try {
                //서울시 오픈 API 제공(샘플 주소 json으로 작업)
                result = Remote.getData("http://swopenapi.seoul.go.kr/api/subway/sample/json/realtimeStationArrival/0/5/%EC%84%9C%EC%9A%B8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
    }


}

