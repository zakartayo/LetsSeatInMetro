package com.example.letsseatinmetro.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.letsseatinmetro.CardItem.MainCardItem;
import com.example.letsseatinmetro.Adapters.MainRecyclerAdapter;
import com.example.letsseatinmetro.Datahouse.DataHouse;
import com.example.letsseatinmetro.R;
import com.example.letsseatinmetro.Network.Remote;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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

        List<MainCardItem> items = new ArrayList<>();
        items = DataHouse.mainCardItems;

        recyclerView.setAdapter(new MainRecyclerAdapter(getApplicationContext(), items, R.layout.activity_main));
    }
}

