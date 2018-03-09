package com.example.letsseatinmetro.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.letsseatinmetro.Adapters.LineRecyclerAdapter;
import com.example.letsseatinmetro.CardItem.LineCardItem;
import com.example.letsseatinmetro.Datahouse.DataHouse;
import com.example.letsseatinmetro.Network.Remote;
import com.example.letsseatinmetro.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LineActivity extends AppCompatActivity {
    private ImageView refresh;
    private int dataLength;
    private RecyclerView recyclerView;
    private LineRecyclerAdapter lineRecyclerAdapter;
    private ArrayList<String> directionData = new ArrayList<>();
    private ArrayList<String> destinationData = new ArrayList<>();
    private ArrayList<String> trimmingDestination = new ArrayList<>();
    private ArrayList<String> updownData = new ArrayList<>();
    private List<LineCardItem> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        refresh = (ImageView)findViewById(R.id.refresh_btn);
        Intent intent = getIntent();
        String lineName = intent.getStringExtra("lineName");

        if(lineName.equals("1호선")){
            recyclerView = (RecyclerView) findViewById(R.id.line_recyclerview);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);

            List<LineCardItem> items = new ArrayList<>();
            items = DataHouse.line1;

            recyclerView.setAdapter(new LineRecyclerAdapter(getApplicationContext(), items, R.layout.activity_line));
        }else if(lineName.equals("경의·중앙선")){
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.line_recyclerview);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);

            items = DataHouse.kyungei;
            lineRecyclerAdapter = new LineRecyclerAdapter(getApplicationContext(), items, R.layout.activity_line);
            recyclerView.setAdapter(lineRecyclerAdapter);
        }

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getApi();
            }
        });

    }
    public void getApi(){
        new MyTask(this).execute();
    }
    private class MyTask extends AsyncTask<Void, Void, String> {
        private WeakReference<LineActivity> activityReference;
        MyTask(LineActivity context){
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
                    String subwayId = result.getString("subwayId");
                    if(subwayId.equals("1063")){
                        String trainLineName = result.getString("trainLineNm");
                        StringTokenizer st = new StringTokenizer(trainLineName, " ");

                        String direction = st.nextToken();
                        directionData.add(direction);
                        Log.d("direction", direction);
                        st.nextToken();

                        String destination = st.nextToken();
                        destinationData.add(destination);
                        dataTrim(destination);
                        Log.d("destination", destination);

                        String updown = result.getString("updnLine");
                        updownData.add(updown);
                    }
                }
                dataLength = destinationData.size();
                compareData();
            }catch (Exception e ){}
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
    public void dataTrim(String data){
        StringBuffer bf = new StringBuffer();
        bf.append(data.charAt(0));
        bf.append(data.charAt(1));
        trimmingDestination.add(bf.toString());
    }
    public void compareData(){
        for(int i=0; i<items.size(); i++){
            for(int j=0; j<dataLength; j++){
                if(items.get(i).getStation().charAt(0)==trimmingDestination.get(j).charAt(0) && items.get(i).getStation().charAt(1)==trimmingDestination.get(j).charAt(1)){
                    if(updownData.get(j).equals("상행")){
                        Log.d("abc",Integer.toString(i));
                        items.get(i).setTop_img(R.drawable.train);
                        lineRecyclerAdapter.notifyItemChanged(i);
                    }else if(updownData.get(j).equals("하행")){
                        Log.d("abc",Integer.toString(i));
                        /*items.get(i).setBottom_img(R.drawable.train);
                        lineRecyclerAdapter.notifyItemChanged(i);*/
                    }
                }
            }
        }
        //recyclerView.setAdapter(new LineRecyclerAdapter(getApplicationContext(), items, R.layout.activity_line));
    }
}
