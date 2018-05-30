package com.example.letsseatinmetro.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.letsseatinmetro.Activities.LineActivity;
import com.example.letsseatinmetro.CardItem.MainCardItem;
import com.example.letsseatinmetro.R;

import java.util.List;


public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>{
    static Context context;
    List<MainCardItem> items;
    int item_layout;
    int lastPosition = -1;

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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView lineColor;
        TextView lineName;
        TextView lineRange;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            Typeface typeface2 = Typeface.createFromAsset(context.getAssets(), "fonts/yaregular.ttf");
            lineColor = (ImageView) itemView.findViewById(R.id.lineColor);
            lineName = (TextView) itemView.findViewById(R.id.lineName);
            lineName.setTypeface(typeface2);
            lineRange = (TextView) itemView.findViewById(R.id.lineRange);
            lineRange.setTypeface(typeface2);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}
