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
    private ImageView direction_top;
    private ImageView line1;
    private ImageView line2;
    private ImageView direction_bottom;
    private TextView station;
    private TextView destination_top;
    private TextView destination_bottom;
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
        direction_top = (ImageView) convertView.findViewById(R.id.direction_top);
        line1 = (ImageView) convertView.findViewById(R.id.line1);
        line2 = (ImageView) convertView.findViewById(R.id.line2);
        direction_bottom = (ImageView) convertView.findViewById(R.id.direction_bottom);
        station = (TextView) convertView.findViewById(R.id.station);
        destination_top = (TextView) convertView.findViewById(R.id.top_destination);
        destination_bottom = (TextView) convertView.findViewById(R.id.bottom_destination);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        LineCardItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        line1.setImageResource(listViewItem.getLine1());
        line2.setImageResource(listViewItem.getLine2());
        station.setText(listViewItem.getStation());
        direction_top.setImageResource(listViewItem.getTop_img());
        direction_bottom.setImageResource(listViewItem.getBottom_img());
        destination_bottom.setText(listViewItem.getDestination_bottom());
        destination_top.setText(listViewItem.getDestination_top());


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
