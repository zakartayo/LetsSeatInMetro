package com.example.letsseatinmetro.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.letsseatinmetro.Adapters.LineRecyclerAdapter;
import com.example.letsseatinmetro.Adapters.TabPagerAdapter;
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
    private ListView listview;
    private LineRecyclerAdapter lineRecyclerAdapter;
    private ArrayList<String> trainPosition = new ArrayList<>();
    private ArrayList<String> destinationData = new ArrayList<>();
    private ArrayList<String> updownData = new ArrayList<>();
    private ArrayList<String> trainState = new ArrayList<>();
    private List<LineCardItem> items = new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String lineName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        lineName = intent.getStringExtra("lineName");


        if (lineName.equals("1호선")) {
            setContentView(R.layout.extream_activity_line);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            // Initializing the TabLayout
            tabLayout = (TabLayout) findViewById(R.id.tabLayout);
            tabLayout.addTab(tabLayout.newTab().setText("일반"));
            tabLayout.addTab(tabLayout.newTab().setText("급행"));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            // Initializing ViewPager
            viewPager = (ViewPager) findViewById(R.id.pager);

            // Creating TabPagerAdapter adapter
            TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), 1);
            viewPager.setAdapter(pagerAdapter);
            //getApi();

            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

            // Set TabSelectedListener
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

        } else if (lineName.equals("2호선")) {
            setContentView(R.layout.activity_line);
            refresh = (ImageView) findViewById(R.id.refresh_btn);
            listview = (ListView) findViewById(R.id.mList);
            items = DataHouse.line2;
            lineRecyclerAdapter = new LineRecyclerAdapter(items);
            listview.setAdapter(lineRecyclerAdapter);
            getApi();

            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getApi();
                }
            });
        } else if (lineName.equals("3호선")) {
            setContentView(R.layout.activity_line);
            refresh = (ImageView) findViewById(R.id.refresh_btn);
            listview = (ListView) findViewById(R.id.mList);
            items = DataHouse.line3;
            lineRecyclerAdapter = new LineRecyclerAdapter(items);
            listview.setAdapter(lineRecyclerAdapter);
            getApi();

            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getApi();
                }
            });
        } else if (lineName.equals("4호선")) {
            setContentView(R.layout.extream_activity_line);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            // Initializing the TabLayout
            tabLayout = (TabLayout) findViewById(R.id.tabLayout);
            tabLayout.addTab(tabLayout.newTab().setText("일반"));
            tabLayout.addTab(tabLayout.newTab().setText("급행"));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            // Initializing ViewPager
            viewPager = (ViewPager) findViewById(R.id.pager);

            // Creating TabPagerAdapter adapter
            TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), 4);
            viewPager.setAdapter(pagerAdapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

            // Set TabSelectedListener
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        } else if (lineName.equals("5호선")) {
            setContentView(R.layout.activity_line);
            refresh = (ImageView) findViewById(R.id.refresh_btn);
            listview = (ListView) findViewById(R.id.mList);
            items = DataHouse.line5;
            lineRecyclerAdapter = new LineRecyclerAdapter(items);
            listview.setAdapter(lineRecyclerAdapter);

            getApi();

            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("called", "called");
                    getApi();
                }
            });
        } else if (lineName.equals("6호선")) {
            setContentView(R.layout.activity_line);
            refresh = (ImageView) findViewById(R.id.refresh_btn);
            listview = (ListView) findViewById(R.id.mList);
            items = DataHouse.line6;
            lineRecyclerAdapter = new LineRecyclerAdapter(items);
            listview.setAdapter(lineRecyclerAdapter);
            getApi();

            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getApi();
                }
            });
        } else if (lineName.equals("7호선")) {
            setContentView(R.layout.activity_line);
            refresh = (ImageView) findViewById(R.id.refresh_btn);
            listview = (ListView) findViewById(R.id.mList);
            items = DataHouse.line7;
            lineRecyclerAdapter = new LineRecyclerAdapter(items);
            listview.setAdapter(lineRecyclerAdapter);
            getApi();

            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getApi();
                }
            });
        } else if (lineName.equals("8호선")) {
            setContentView(R.layout.activity_line);
            refresh = (ImageView) findViewById(R.id.refresh_btn);
            listview = (ListView) findViewById(R.id.mList);
            items = DataHouse.line8;
            lineRecyclerAdapter = new LineRecyclerAdapter(items);
            listview.setAdapter(lineRecyclerAdapter);
            getApi();

            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getApi();
                }
            });
        } else if (lineName.equals("9호선")) {
            setContentView(R.layout.extream_activity_line);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            // Initializing the TabLayout
            tabLayout = (TabLayout) findViewById(R.id.tabLayout);
            tabLayout.addTab(tabLayout.newTab().setText("일반"));
            tabLayout.addTab(tabLayout.newTab().setText("급행"));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            // Initializing ViewPager
            viewPager = (ViewPager) findViewById(R.id.pager);

            // Creating TabPagerAdapter adapter
            TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), 9);
            viewPager.setAdapter(pagerAdapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

            // Set TabSelectedListener
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

        } else if (lineName.equals("경의·중앙선")) {
                setContentView(R.layout.activity_line);
                refresh = (ImageView) findViewById(R.id.refresh_btn);
                listview = (ListView) findViewById(R.id.mList);
                items = DataHouse.kyungei;
                lineRecyclerAdapter = new LineRecyclerAdapter(items);
                listview.setAdapter(lineRecyclerAdapter);
                getApi();

                refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getApi();
                }
            });
        } else if (lineName.equals("분당선")) {
            setContentView(R.layout.activity_line);
            refresh = (ImageView) findViewById(R.id.refresh_btn);
            listview = (ListView) findViewById(R.id.mList);
            items = DataHouse.bundang;
            lineRecyclerAdapter = new LineRecyclerAdapter(items);
            listview.setAdapter(lineRecyclerAdapter);
            getApi();

            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getApi();
                }
            });
        } else if (lineName.equals("신분당선")) {
            setContentView(R.layout.activity_line);
            refresh = (ImageView) findViewById(R.id.refresh_btn);
            listview = (ListView) findViewById(R.id.mList);
            items = DataHouse.newbundang;
            lineRecyclerAdapter = new LineRecyclerAdapter(items);
            listview.setAdapter(lineRecyclerAdapter);
            getApi();

            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getApi();
                }
            });
        } else if (lineName.equals("경춘선")) {
            setContentView(R.layout.activity_line);
            refresh = (ImageView) findViewById(R.id.refresh_btn);
            listview = (ListView) findViewById(R.id.mList);
            items = DataHouse.kyungchun;
            lineRecyclerAdapter = new LineRecyclerAdapter(items);
            listview.setAdapter(lineRecyclerAdapter);
            getApi();

            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getApi();
                }
            });
        } else if (lineName.equals("공항철도")) {
            setContentView(R.layout.activity_line);
            refresh = (ImageView) findViewById(R.id.refresh_btn);
            listview = (ListView) findViewById(R.id.mList);
            items = DataHouse.arirail;
            lineRecyclerAdapter = new LineRecyclerAdapter(items);
            listview.setAdapter(lineRecyclerAdapter);
            getApi();

            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getApi();
                }
            });
        }
    }
    public void getApi(){
        Log.d("api called", "api called");
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
                JSONArray rows = json.getJSONArray("realtimePositionList");
                Log.d("json called", "json called");
                dataLength = rows.length();

                trainPosition = new ArrayList<>();
                destinationData = new ArrayList<>();
                updownData = new ArrayList<>();
                trainState = new ArrayList<>();
                Log.d("dataLength", Integer.toString(dataLength));
                for(int i=0; i < dataLength; i ++){
                    JSONObject result = (JSONObject) rows.get(i);

                    //현재 지하철역명 저장
                    String currentPosition = result.getString("statnNm");
                    Log.d("currentPosition", currentPosition);
                    dataTrim(currentPosition);

                    //종착역 저장
                    String destination = result.getString("statnTnm");
                    destinationData.add(destination);
                    Log.d("direction", destination);

                    //상하행 저장
                    String updown = result.getString("updnLine");
                    updownData.add(updown);

                    //열차 상태 저장
                    String state = result.getString("trainSttus");
                    trainState.add(state);
                    Log.d("state", state);
                }
                compareData();
            }catch (Exception e ){}
        }

        @Override
        protected String doInBackground(Void... params) {
            String result = "";
            try {
                //서울시 오픈 API 제공(샘플 주소 json으로 작업)
                result = Remote.getData("http://swopenapi.seoul.go.kr/api/subway/574a706754646c673936684d555778/json/realtimePosition/1/1000/"+lineName);
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
        Log.d("dataTrim", bf.toString());
        trainPosition.add(bf.toString());
    }
    public void compareData(){
        refreshData();
        for(int i=0; i<items.size(); i++){
            for(int j=0; j<dataLength; j++){
                if(items.get(i).getStation().charAt(0)==trainPosition.get(j).charAt(0) && items.get(i).getStation().charAt(1)==trainPosition.get(j).charAt(1)){
                    if(updownData.get(j).equals("0")){
                        Log.d("상행",items.get(i).getStation());

                        if(trainState.get(j).equals("0")){
                            items.get(i).setTop_img_1(R.drawable.train);
                            items.get(i).setDestination_top_1(destinationData.get(j));
                        }else if(trainState.get(j).equals("1")){
                            items.get(i).setTop_img_2(R.drawable.train);
                            items.get(i).setDestination_top_2(destinationData.get(j));
                        }else{
                            items.get(i).setTop_img_3(R.drawable.train);
                            items.get(i).setDestination_top_3(destinationData.get(j));
                        }
                        lineRecyclerAdapter.notifyDataSetChanged();;

                    }else{
                        Log.d("하행",items.get(i).getStation());

                        if(trainState.get(j).equals("0")){
                            items.get(i).setBottom_img_1(R.drawable.train);
                            items.get(i).setDestination_bottom_1(destinationData.get(j));
                        }else if(trainState.get(j).equals("1")){
                            items.get(i).setBottom_img_2(R.drawable.train);
                            items.get(i).setDestination_bottom_2(destinationData.get(j));
                        }else{
                            items.get(i).setBottom_img_3(R.drawable.train);
                            items.get(i).setDestination_bottom_3(destinationData.get(j));
                        }
                        lineRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            }
        }

    }
    public void refreshData(){
        for(int i=0; i<items.size(); i++){
            items.get(i).setTop_img_1(R.drawable.blank_img);
            items.get(i).setTop_img_2(R.drawable.blank_img);
            items.get(i).setTop_img_3(R.drawable.blank_img);
            items.get(i).setBottom_img_1(R.drawable.blank_img);
            items.get(i).setBottom_img_2(R.drawable.blank_img);
            items.get(i).setBottom_img_3(R.drawable.blank_img);
            items.get(i).setDestination_top_1("");
            items.get(i).setDestination_top_2("");
            items.get(i).setDestination_top_3("");
            items.get(i).setDestination_bottom_1("");
            items.get(i).setDestination_bottom_2("");
            items.get(i).setDestination_bottom_3("");
        }
    }
}
