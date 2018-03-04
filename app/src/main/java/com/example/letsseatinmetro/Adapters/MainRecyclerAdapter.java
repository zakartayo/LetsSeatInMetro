package com.example.letsseatinmetro.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.letsseatinmetro.Activities.LineActivity;
import com.example.letsseatinmetro.CardItem.MainCardItem;
import com.example.letsseatinmetro.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yong on 2018-03-03.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    Context context;
    List<MainCardItem> items;
    int item_layout;

    public MainRecyclerAdapter(Context context, List<MainCardItem> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MainCardItem item = items.get(position);

        holder.lineColor.setImageResource((int)item.getLineColor());
        holder.lineName.setText(item.getLineName());
        holder.lineRange.setText(item.getLineRange());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LineActivity.class);
                intent.putExtra("lineName", item.getLineName());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView lineColor;
        TextView lineName;
        TextView lineRange;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            lineColor = (ImageView) itemView.findViewById(R.id.lineColor);
            lineName = (TextView) itemView.findViewById(R.id.lineName);
            lineRange = (TextView) itemView.findViewById(R.id.lineRange);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}
