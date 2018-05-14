package com.example.letsseatinmetro.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.letsseatinmetro.DialogItem.Congestion;
import com.example.letsseatinmetro.R;

import java.util.List;

/**
 * Created by multimedia on 2018-05-10.
 */

public class DialogListAdapter extends BaseAdapter{
    private Context context;
    private List<Congestion> dataList;

    public DialogListAdapter(Context context, List<Congestion> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount()
    {
        return dataList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dialog_list_row, null);
        }

        final Congestion data = dataList.get(position);

        ImageView congestionImage = (ImageView)view.findViewById(R.id.congestionImage);
        TextView congestionTitle = (TextView)view.findViewById(R.id.congestionTitle);
        TextView vacancyTitle = (TextView)view.findViewById(R.id.vacancyTitle);
        TextView vacancyCount = (TextView)view.findViewById(R.id.vacancyCount);


        congestionImage.setImageDrawable(context.getResources().getDrawable(data.getCongestionImageResource()));
        congestionTitle.setText(data.getCongestionTitle());
        vacancyTitle.setText(data.getVacancyTitle());
        vacancyCount.setText(data.getVacancyCount());

        return view;
    }
}