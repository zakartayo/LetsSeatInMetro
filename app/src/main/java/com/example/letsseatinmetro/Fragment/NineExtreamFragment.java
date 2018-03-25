package com.example.letsseatinmetro.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.letsseatinmetro.Adapters.LineRecyclerAdapter;
import com.example.letsseatinmetro.CardItem.LineCardItem;
import com.example.letsseatinmetro.Datahouse.DataHouse;
import com.example.letsseatinmetro.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yong on 2018-03-21.
 */

public class NineExtreamFragment extends Fragment {
    private ListView listview;
    private LineRecyclerAdapter lineRecyclerAdapter;
    private List<LineCardItem> items = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.extream_tab, container, false);
        listview = (ListView) v.findViewById(R.id.extreamList);
        items = DataHouse.extreamLine9;
        lineRecyclerAdapter = new LineRecyclerAdapter(items);
        listview.setAdapter(lineRecyclerAdapter);
        return v;
    }
}