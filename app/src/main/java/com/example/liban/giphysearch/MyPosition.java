package com.example.liban.giphysearch;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;

import com.example.liban.giphysearch.DTO.Data;
import com.example.liban.giphysearch.DTO.ListData;

import java.util.List;

/**
 * Created by liban on 16.08.2018.
 */

public class MyPosition extends PositionalDataSource {
    private ListData listData;

    public MyPosition(ListData listData) {
        this.listData = listData;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {

    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback callback) {

    }
}
