package com.example.devcom.sportnews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.devcom.sportnews.activity.ArticleActivity;
import com.example.devcom.sportnews.model.Event;
import com.example.devcom.sportnews.R;

import java.util.List;

public class RvCategoryAdapter extends RecyclerView.Adapter<RvCategoryAdapter.MyViewHolder> {

    private Context mContext;
    private List<Event> mData;

    public RvCategoryAdapter(Context mContext, List<Event> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.event_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tv_title.setText(mData.get(position).getTitle());
        holder.tv_coefficient.setText(mData.get(position).getCoefficient());
        holder.tv_time.setText(mData.get(position).getTime());
        holder.tv_place.setText(mData.get(position).getPlace());
        holder.tv_preview.setText(mData.get(position).getPreview());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ArticleActivity.class);
                intent.putExtra("article",mData.get(position).getArticle());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_coefficient;
        TextView tv_time;
        TextView tv_place;
        TextView tv_preview;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.title);
            tv_coefficient = itemView.findViewById(R.id.coefficient);
            tv_time = itemView.findViewById(R.id.time);
            tv_place = itemView.findViewById(R.id.place);
            tv_preview = itemView.findViewById(R.id.preview);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }


}
