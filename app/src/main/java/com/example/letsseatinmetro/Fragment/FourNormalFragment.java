package com.example.letsseatinmetro.Fragment;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.letsseatinmetro.Adapters.LineRecyclerAdapter;
import com.example.letsseatinmetro.CardItem.LineCardItem;
import com.example.letsseatinmetro.Datahouse.DataHouse;
import com.example.letsseatinmetro.Network.LineList;
import com.example.letsseatinmetro.Network.Remote;
import com.example.letsseatinmetro.Network.ServiceGenerator;
import com.example.letsseatinmetro.Network.Station;
import com.example.letsseatinmetro.Network.SubwayApiService;
import com.example.letsseatinmetro.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 이승헌 on 2018-03-18.
 */

public class FourNormalFragment extends Fragment {
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
    private ArrayList<LineList> stationList; //데이터를 담을 리스트
    private static final String API_KEY = "574a706754646c673936684d555778";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        View v = inflater.inflate(R.layout.normal_tab, container, false);
        listview = (ListView) v.findViewById(R.id.normalList);
        final View header = getLayoutInflater().inflate(R.layout.line_listview_header, null, false);

        TextView up = header.findViewById(R.id.up);
        TextView down = header.findViewById(R.id.down);

        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/yabold.ttf");

        up.setTypeface(typeface);
        down.setTypeface(typeface);

        listview.addHeaderView(header);
        refresh = (ImageView)v.findViewById(R.id.refresh_normal_btn);
        items = DataHouse.line4;
        lineRecyclerAdapter = new LineRecyclerAdapter(items);
        listview.setAdapter(lineRecyclerAdapter);
        setStnListAPI(API_KEY, "4호선");

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStnListAPI(API_KEY, "4호선");
            }
        });
        return v;
    }
    private void setStnListAPI(String apikey, final String linenum){

        SubwayApiService api = ServiceGenerator.getListApiService();
        Call<Station> call = api.getStationList(apikey, linenum);

        call.enqueue(new Callback<Station>() {
            @Override
            public void onResponse(Call<Station> call, Response<Station> response) {

                if(response.isSuccessful()) {

                    stationList = response.body().getLineList();
                    dataLength = stationList.size();

                    Log.d("dataLength", Integer.toString(dataLength));

                    trainPosition = new ArrayList<>();
                    destinationData = new ArrayList<>();
                    updownData = new ArrayList<>();
                    trainState = new ArrayList<>();
                    trainNums = new ArrayList<>();

                    int normalCount = 0;

                    for(int i=0; i < dataLength; i++){
                        //현재 지하철역명 저장
                        String currentPosition = stationList.get(i).getStatnNm();
                        Log.d("currentPosition", currentPosition);

                        //열차번호 저장
                        String trainNum = stationList.get(i).getTrainNo();

                        //종착역 저장
                        String destination = stationList.get(i).getStatnTnm();

                        //상하행 저장
                        String updown = stationList.get(i).getUpdnLine();

                        //열차 상태 저장
                        String state = stationList.get(i).getTrainSttus();

                        String directAt = stationList.get(i).getDirectAt();

                        if(directAt.equals("0")){
                            trainPosition.add(currentPosition);
                            destinationData.add(destination);
                            updownData.add(updown);
                            trainState.add(state);
                            trainNums.add(trainNum);
                            normalCount++;
                        }else{
                            System.out.println("급행입니다");
                        }
                    }
                    Log.d("trainpositionsize", Integer.toString(trainPosition.size()));
                    compareData(normalCount);
                } else {
                    Log.v("SearchActivity",linenum);
                    Toast.makeText(getActivity(),"서울열린데이터 네트워크가 불안정",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Station> call, Throwable t) {
                Log.v("SearchActivity","onFailure"+linenum);
                Toast.makeText(getActivity(),linenum+"onFailure",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void compareData(int normalCount) {
        refreshData();
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i);
            Log.d("itemSize", Integer.toString(items.size()));
            for (int j = 0; j < normalCount; j++) {
                Log.d("datalength", Integer.toString(dataLength));
                Log.d("normalCount", Integer.toString(normalCount));

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
                            items.get(i).setline1(R.drawable.line_four_1);
                            items.get(i).setDestination_top_1(destinationData.get(j));
                        } else if (trainState.get(j).equals("1")) {
                            items.get(i).setline1(R.drawable.line_four_2);
                            items.get(i).setDestination_top_2(destinationData.get(j));
                        } else {
                            items.get(i).setline1(R.drawable.line_four_3);
                            items.get(i).setDestination_top_3(destinationData.get(j));
                        }
                        lineRecyclerAdapter.notifyDataSetChanged();
                        Log.d("upAdapterNoti", "upAdapterNoti");

                    } else {
                        Log.d("하행", items.get(i).getStation());
                        items.get(i).setDownTrainNum(trainNums.get(j), true);
                        if (trainState.get(j).equals("0")) {
                            items.get(i).setline2(R.drawable.line_four_1);
                            items.get(i).setDestination_bottom_1(destinationData.get(j));
                        } else if (trainState.get(j).equals("1")) {
                            items.get(i).setline2(R.drawable.line_four_2);
                            items.get(i).setDestination_bottom_2(destinationData.get(j));
                        } else {
                            items.get(i).setline2(R.drawable.line_four_3);
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
            items.get(i).setline1(R.drawable.line_img_4);
            items.get(i).setline2(R.drawable.line_img_4);
            items.get(i).setDestination_top_1("");
            items.get(i).setDestination_top_2("");
            items.get(i).setDestination_top_3("");
            items.get(i).setDestination_bottom_1("");
            items.get(i).setDestination_bottom_2("");
            items.get(i).setDestination_bottom_3("");
        }
    }
}
