package com.example.devcom.sportnews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.devcom.sportnews.model.Article;
import com.example.devcom.sportnews.R;

import java.util.List;

public class RvArticleAdapter extends RecyclerView.Adapter<RvArticleAdapter.MyViewHolder>{

    private Context mContext;
    private List<Article> mData;

    public RvArticleAdapter(Context mContext, List<Article> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.article_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tv_header.setText(mData.get(position).getHeader());
        holder.tv_text.setText(mData.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_header;
        TextView tv_text;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_text = itemView.findViewById(R.id.tv_text);
            tv_header = itemView.findViewById(R.id.tv_header);
        }
    }
}

