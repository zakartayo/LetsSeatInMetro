package com.example.letsseatinmetro.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.letsseatinmetro.CardItem.LineCardItem;
import com.example.letsseatinmetro.DialogItem.Congestion;
import com.example.letsseatinmetro.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 이승헌 on 2018-03-06.
 */

public class LineRecyclerAdapter  extends BaseAdapter{

    private List<LineCardItem> listViewItemList = new ArrayList<LineCardItem>() ;
    private List<String> congestions = new ArrayList<>();
    private List<Integer> vacancies = new ArrayList<>();
    private ListView listView;
    private ImageView line1;
    private ImageView line2;
    private TextView station;
    private TextView destination_top_1;
    private TextView destination_top_2;
    private TextView destination_top_3;
    private TextView destination_bottom_1;
    private TextView destination_bottom_2;
    private TextView destination_bottom_3;
    private Context context;
    private static String LOG_TAG = "LineRecyclerAdapter";
    // ListViewAdapter의 생성자
    public LineRecyclerAdapter(List<LineCardItem> listItem) {
        listViewItemList = listItem;
    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.line_item_cardview, parent, false);
        }
        line1 = (ImageView) convertView.findViewById(R.id.line1);
        line2 = (ImageView) convertView.findViewById(R.id.line2);
        station = (TextView) convertView.findViewById(R.id.station);
        destination_top_1 = (TextView) convertView.findViewById(R.id.top_destination_1);
        destination_top_2 = (TextView) convertView.findViewById(R.id.top_destination_2);
        destination_top_3 = (TextView) convertView.findViewById(R.id.top_destination_3);
        destination_bottom_1 = (TextView) convertView.findViewById(R.id.bottom_destination_1);
        destination_bottom_2 = (TextView) convertView.findViewById(R.id.bottom_destination_2);
        destination_bottom_3 = (TextView) convertView.findViewById(R.id.bottom_destination_3);

        listView = new ListView(context);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final LineCardItem listViewItem = listViewItemList.get(position);

        if(listViewItem.getUpTrainFlag()==true){
            line1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //new MakeNetworkCall().execute("http://eatcoder.iptime.org/"+listViewItem.getUpTrainNum(), "Get");
                    new MakeNetworkCall().execute("http://eatcoder.iptime.org/"+"002" + "&2132", "Get");

                }
            });
        }else{
            line1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("상행 잘못된 클릭", "열차 정보가 없습니다");
                    Handler mHandler = new Handler(Looper.getMainLooper());

                    mHandler.postDelayed(new Runnable() {


                        @Override

                        public void run() {
                            Toast.makeText(context, "열차 정보가 없습니다"
                                    , Toast.LENGTH_LONG).show();
                        }

                    }, 0);
                }
            });
        }

        if(listViewItem.getDownTrainFlag()==true){
            line2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("하행 클릭", "http통신을 시작하세요");
                    //new MakeNetworkCall().execute("http://eatcoder.iptime.org/"+listViewItem.getDownTrainNum(), "Get");
                    new MakeNetworkCall().execute("http://eatcoder.iptime.org/"+"002" + "&2132", "Get");
                }
            });
        }else{
            line2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Handler mHandler = new Handler(Looper.getMainLooper());

                    mHandler.postDelayed(new Runnable() {


                        @Override

                        public void run() {
                            Toast.makeText(context, "열차 정보가 없습니다"
                                    , Toast.LENGTH_LONG).show();
                        }

                    }, 0);
                }
            });
        }

        // 아이템 내 각 위젯에 데이터 반영
        line1.setImageResource(listViewItem.getLine1());
        line2.setImageResource(listViewItem.getLine2());
        station.setText(listViewItem.getStation());
        destination_bottom_1.setText(listViewItem.getDestination_bottom_1());
        destination_bottom_2.setText(listViewItem.getDestination_bottom_2());
        destination_bottom_3.setText(listViewItem.getDestination_bottom_3());
        destination_top_1.setText(listViewItem.getDestination_top_1());
        destination_top_2.setText(listViewItem.getDestination_top_2());
        destination_top_3.setText(listViewItem.getDestination_top_3());


        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    InputStream ByGetMethod(String ServerURL) {

        InputStream DataInputStream = null;
        try {

            URL url = new URL(ServerURL);
            HttpURLConnection cc = (HttpURLConnection)
                    url.openConnection();
            //set timeout for reading InputStream
            cc.setReadTimeout(5000);
            // set timeout for connection
            cc.setConnectTimeout(5000);
            //set HTTP method to GET
            cc.setRequestMethod("GET");
            //set it to true as we are connecting for input
            cc.setDoInput(true);

            //reading HTTP response code
            int response = cc.getResponseCode();

            //if response code is 200 / OK then read Inputstream
            if (response == HttpURLConnection.HTTP_OK) {
                DataInputStream = cc.getInputStream();
            }

        } catch (Exception e) {
            Log.e(LOG_TAG, "Error in GetData", e);
        }
        return DataInputStream;

    }

    String ConvertStreamToString(InputStream stream) {

        InputStreamReader isr = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(isr);
        StringBuilder response = new StringBuilder();
        refreshJSON();

        String line = null;
        try {

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            String data = response.toString();

            JSONArray jarray = new JSONObject(data).getJSONArray("list");

            Log.d("jarray", Integer.toString(jarray.length()));

            for(int i=0; i<jarray.length(); i++){
                HashMap<String, String> map = new HashMap<>();
                JSONObject jObject = jarray.getJSONObject(i);

                String congestion= Integer.toString(jObject.optInt("CONGESTION"));
                congestions.add(i, congestion);

                int vacancy = jObject.optInt("VACANCY");
                vacancies.add(i, vacancy);


                Log.d("congestion", congestion);
                Log.d("vacancy", Integer.toString(vacancy));
            }

            Handler mHandler = new Handler(Looper.getMainLooper());

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialogInItOpen();
                }

            }, 0);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error in ConvertStreamToString", e);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error in ConvertStreamToString", e);
        } finally {

            try {
                stream.close();

            } catch (IOException e) {
                Log.e(LOG_TAG, "Error in ConvertStreamToString", e);

            } catch (Exception e) {
                Log.e(LOG_TAG, "Error in ConvertStreamToString", e);
            }
        }
        return response.toString();


    }
    private class MakeNetworkCall extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //DisplayMessage("Please Wait ...");
        }

        @Override
        protected String doInBackground(String... arg) {

            InputStream is = null;
            String URL = arg[0];
            Log.d(LOG_TAG, "URL: " + URL);
            String res = "";

                is = ByGetMethod(URL);

            if (is != null) {
                res = ConvertStreamToString(is);
            } else {
                res = "Something went wrong";
            }
            return res;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // DisplayMessage(result);
            Log.d(LOG_TAG, "Result: " + result);
        }
    }
    public void dialogInItOpen(){
        final List<Congestion> dialogItems = new ArrayList<Congestion>();

        for(int i=0; i<congestions.size(); i++){

            Congestion congestion = new Congestion();

            congestion.setCongestionTitle(Integer.toString(i+1)+"번 칸");
            switch (congestions.get(i)){
                case "1":
                    congestion.setCongestionCode(i);
                    congestion.setVacancyTitle("좌석 수:");
                    congestion.setVacancyCount(Integer.toString(vacancies.get(i)));
                    congestion.setCongestionImageResource(R.drawable.level1);
                    break;
                case "2":
                    congestion.setCongestionCode(i);
                    congestion.setVacancyTitle("좌석 수:");
                    congestion.setVacancyCount(Integer.toString(vacancies.get(i)));
                    congestion.setCongestionImageResource(R.drawable.level2);
                    break;
                case "3":
                    congestion.setCongestionCode(i);
                    congestion.setCongestionImageResource(R.drawable.level3);
                    break;
                case "4":
                    congestion.setCongestionCode(i);
                    congestion.setCongestionImageResource(R.drawable.level4);
                    break;
                case "5":
                    congestion.setCongestionCode(i);
                    congestion.setCongestionImageResource(R.drawable.level5);
                    break;
                default:
                    return;
            }
            dialogItems.add(congestion);
        }

        final DialogListAdapter adapter = new DialogListAdapter(context, dialogItems);
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setAdapter(adapter, null)
                .setTitle("")
                .setPositiveButton("닫기", new DialogInterface.OnClickListener() { // 버튼은 테마에 따라서 모양이 다르게 모임
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

        final AlertDialog alertDialog = builder.create();
        final ListView listView = alertDialog.getListView();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View header = inflater.inflate(R.layout.dialog_listview_header, null, false);

        listView.addHeaderView(header);
        listView.setAdapter(adapter);
        //listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); // 여러 상품 선택을 위해 - 이 형태에서는 필요 없다
        listView.setDivider(new ColorDrawable(Color.LTGRAY));
        listView.setDividerHeight(1);
        listView.setFocusable(false); // false를 해줘야 row touch event 가능
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }
    public void refreshJSON(){
        congestions.clear();
        vacancies.clear();
    }
}
