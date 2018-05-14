package com.example.letsseatinmetro.Fragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

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

/**
 * Created by 이승헌 on 2018-03-18.
 */

public class NineExtreamFragment extends Fragment {
    private ListView listview;
    private LineRecyclerAdapter lineRecyclerAdapter;
    private List<LineCardItem> items = new ArrayList<>();
    private ImageView refresh;
    private ArrayList<String> trainPosition;
    private ArrayList<String> destinationData;
    private ArrayList<String> updownData;
    private ArrayList<String> trainState;
    private ArrayList<String> trainNums;
    private int dataLength;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.extream_tab, container, false);
        listview = (ListView) v.findViewById(R.id.extreamList);
        refresh = (ImageView)v.findViewById(R.id.refresh_extream_btn);
        items = DataHouse.line9;
        lineRecyclerAdapter = new LineRecyclerAdapter(items);
        listview.setAdapter(lineRecyclerAdapter);
        getApi();

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getApi();
            }
        });
        return v;
    }public void getApi(){
        Log.d("extreamFragment api", "api called extreamFragment");
        new MyTask(getActivity()).execute();
    }
    private class MyTask extends AsyncTask<Void, Void, String> {
        private WeakReference<Activity> activityReference;
        MyTask(Activity context){
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
                trainNums = new ArrayList<>();


                Log.d("dataLength", Integer.toString(dataLength));
                int extreamCount=0;
                for(int i=0; i < dataLength; i ++){

                    JSONObject result = (JSONObject) rows.get(i);
                    //급행 여부 0=일반, 1=급행
                    String directAt = result.getString("directAt");
                    //현재 지하철역명 저장
                    String currentPosition = result.getString("statnNm");
                    //열차번호 저장
                    String trainNum = result.getString("trainNo");
                    //종착역 저장
                    String destination = result.getString("statnTnm");
                    //상하행 저장
                    String updown = result.getString("updnLine");
                    //열차 상태 저장
                    String state = result.getString("trainSttus");

                    if(directAt.equals("1")) {
                        Log.d("directAt", directAt);
                        Log.d("destination", destination);
                        Log.d("currentPosition", currentPosition);
                        Log.d("direction", destination);
                        Log.d("state", state);
                        System.out.println("=======================");
                        trainPosition.add(currentPosition);
                        destinationData.add(destination);
                        updownData.add(updown);
                        trainState.add(state);
                        trainNums.add(trainNum);
                        extreamCount++;
                    }else if(directAt.equals("1")){
                        System.out.println("일반입니다");
                    }
                }
                compareData(extreamCount);
            }catch (Exception e ){}
        }

        @Override
        protected String doInBackground(Void... params) {
            String result = "";
            try {
                //서울시 오픈 API 제공(샘플 주소 json으로 작업)
                result = Remote.getData("http://swopenapi.seoul.go.kr/api/subway/574a706754646c673936684d555778/json/realtimePosition/1/1000/"+"9호선");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
    }
    /*public void dataTrim(String data){
        StringBuffer bf = new StringBuffer();
        bf.append(data.charAt(0));
        bf.append(data.charAt(1));
        Log.d("dataTrim", bf.toString());
        trainPosition.add(bf.toString());
    }*/
    public void compareData(int extreamCount) {
        refreshData();
        if(extreamCount==0){
            refreshData();
            listview.setAdapter(lineRecyclerAdapter);
        }
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i);
            Log.d("itemSize", Integer.toString(items.size()));
            for (int j = 0; j < extreamCount; j++) {
                Log.d("datalength", Integer.toString(dataLength));
                Log.d("extreamCount", Integer.toString(extreamCount));
                System.out.println(j);
                //System.out.println(String.valueOf(items.get(i).getStation().charAt(0))+String.valueOf(trainPosition.get(j).charAt(0)));
                //System.out.println(String.valueOf(items.get(i).getStation().charAt(1))+String.valueOf(trainPosition.get(j).charAt(1)));
                if (items.get(i).getStation().equals(trainPosition.get(j))) {
                    System.out.println("일치");
                    if (updownData.get(j).equals("0")) {
                        Log.d("상행", items.get(i).getStation());
                        // 해당 열차가 있을 시 열차번호와 flag값 입력
                        items.get(i).setUpTrainNum(trainNums.get(j), true);
                        if (trainState.get(j).equals("0")) {
                            items.get(i).setline1(R.drawable.line_nine_1);
                            items.get(i).setDestination_top_1(destinationData.get(j));
                        } else if (trainState.get(j).equals("1")) {
                            items.get(i).setline1(R.drawable.line_nine_2);
                            items.get(i).setDestination_top_2(destinationData.get(j));
                        } else {
                            items.get(i).setline1(R.drawable.line_nine_3);
                            items.get(i).setDestination_top_3(destinationData.get(j));
                        }
                        lineRecyclerAdapter.notifyDataSetChanged();
                        Log.d("upAdapterNoti", "upAdapterNoti");

                    } else {
                        Log.d("하행", items.get(i).getStation());
                        items.get(i).setDownTrainNum(trainNums.get(j), true);
                        if (trainState.get(j).equals("0")) {
                            items.get(i).setline2(R.drawable.line_nine_1);
                            items.get(i).setDestination_bottom_1(destinationData.get(j));
                        } else if (trainState.get(j).equals("1")) {
                            items.get(i).setline2(R.drawable.line_nine_2);
                            items.get(i).setDestination_bottom_2(destinationData.get(j));
                        } else {
                            items.get(i).setline2(R.drawable.line_nine_3);
                            items.get(i).setDestination_bottom_3(destinationData.get(j));
                        }
                        lineRecyclerAdapter.notifyDataSetChanged();
                        Log.d("downAdapterNoti", "downAdapterNoti");
                    }
                }

            }
            System.out.println("end");
        }
    }
    public void refreshData(){
        for(int i=0; i<items.size(); i++){
            items.get(i).setline1(R.drawable.line_img_9);
            items.get(i).setline2(R.drawable.line_img_9);
            items.get(i).setDestination_top_1("");
            items.get(i).setDestination_top_2("");
            items.get(i).setDestination_top_3("");
            items.get(i).setDestination_bottom_1("");
            items.get(i).setDestination_bottom_2("");
            items.get(i).setDestination_bottom_3("");
        }
    }
}