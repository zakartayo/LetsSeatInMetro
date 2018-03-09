package com.example.letsseatinmetro.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.letsseatinmetro.CardItem.LineCardItem;
import com.example.letsseatinmetro.R;

import java.util.List;

/**
 * Created by 이승헌 on 2018-03-06.
 */

public class LineRecyclerAdapter  extends RecyclerView.Adapter<LineRecyclerAdapter.ViewHolder> {
    Context context;
    List<LineCardItem> items;
    int item_layout;

    public LineRecyclerAdapter(Context context, List<LineCardItem> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public LineRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_item_cardview, null);
        return new LineRecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LineRecyclerAdapter.ViewHolder holder, int position) {
        final LineCardItem item = items.get(position);

        if(items.get(position).getTop_img()!=0){
            holder.direction_top.setImageResource(item.getTop_img());
        }
        if(items.get(position).getBottom_img()!=0){
            holder.direction_bottom.setImageResource(item.getBottom_img());
        }

        holder.line1.setImageResource(item.getLine1());
        holder.line2.setImageResource(item.getLine2());
        holder.station.setText(item.getStation());

        // api 결과값을 이용하여 setting

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(context, LineActivity.class);
                intent.putExtra("lineName", item.getLineName());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView direction_top;
        ImageView line1;
        ImageView line2;
        ImageView direction_bottom;
        TextView station;
        TextView destination_top;
        TextView destination_bottom;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            direction_top = (ImageView) itemView.findViewById(R.id.direction_top);
            line1 = (ImageView) itemView.findViewById(R.id.line1);
            line2 = (ImageView) itemView.findViewById(R.id.line2);
            direction_bottom = (ImageView) itemView.findViewById(R.id.direction_bottom);
            station = (TextView) itemView.findViewById(R.id.station);
            destination_top = (TextView) itemView.findViewById(R.id.top_destination);
            destination_bottom = (TextView) itemView.findViewById(R.id.bottom_destination);

            cardview = (CardView) itemView.findViewById(R.id.line_cardview);
        }
    }
}
