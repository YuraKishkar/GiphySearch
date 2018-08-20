package com.example.liban.giphysearch;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.liban.giphysearch.dto.Data;
import com.example.liban.giphysearch.dto.ListData;
import com.example.liban.giphysearch.mvp.model.DataSource;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by liban on 15.08.2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ListData mListData;
    private Context mContext;
    private boolean isTrendingContains;

    public ListData getListData() {
        return mListData;
    }

    public boolean isTrendingContains() {
        return isTrendingContains;
    }

    public void setTrendingContains(boolean trendingContains) {
        isTrendingContains = trendingContains;
    }


    public RecyclerAdapter(ListData listData, Context context) {
        mListData = listData;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cv = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.gif_card, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(mContext)
                .asGif()
                .load(Uri.parse(mListData.getData().get(position)
                        .getImages().getFixedHeight().getUrl()))

                .into(holder.mImageView);


    }

    public void addNewGifs(List<Data> newGifsData) {
        mListData.getData().addAll(newGifsData);
        notifyDataSetChanged();
    }

    public void clearData() {
        mListData.getData().clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mListData != null) {
            return mListData.getData().size();
        } else {
            return 0;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageView;
        private ProgressBar mProgressBar;

        public ViewHolder(View v) {
            super(v);
            mImageView = v.findViewById(R.id.gif_id);
            mProgressBar = v.findViewById(R.id.progressBar_id);
            v.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Intent intent = new Intent(mContext, DetailGif.class);
                intent.putExtra(DetailGif.URL, mListData.getData().get(position)
                        .getImages().getFixedHeight().getUrl());
                mContext.startActivity(intent);
            }
        }
    }
}


