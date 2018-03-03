package com.example.letsseatinmetro;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yong on 2018-03-03.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<MainCardItem> items;
    int item_layout;

    public MainRecyclerAdapter(Context context, ArrayList<MainCardItem> items, int item_layout) {
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

        holder.lineColor.setImageResource(item.getLineColor());
        holder.lineName.setText(item.getLineName());
        holder.lineRange.setText(item.getLineRange());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LineActivity.class);
                intent.putExtra("lineName", item.getLineName());
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
