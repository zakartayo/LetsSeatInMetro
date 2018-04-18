package com.example.letsseatinmetro.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.letsseatinmetro.CardItem.LineCardItem;
import com.example.letsseatinmetro.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 이승헌 on 2018-03-06.
 */

public class LineRecyclerAdapter  extends BaseAdapter{

    private List<LineCardItem> listViewItemList = new ArrayList<LineCardItem>() ;
    private ImageView line1;
    private ImageView line2;
    private TextView station;
    private TextView destination_top_1;
    private TextView destination_top_2;
    private TextView destination_top_3;
    private TextView destination_bottom_1;
    private TextView destination_bottom_2;
    private TextView destination_bottom_3;
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
        final Context context = parent.getContext();

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

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        LineCardItem listViewItem = listViewItemList.get(position);

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

}
