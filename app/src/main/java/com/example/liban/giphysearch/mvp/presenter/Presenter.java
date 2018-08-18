package com.example.liban.giphysearch.mvp.presenter;

import com.example.liban.giphysearch.DTO.Data;
import com.example.liban.giphysearch.DTO.ListData;
import com.example.liban.giphysearch.mvp.model.DataSource;
import com.example.liban.giphysearch.mvp.view.MainView;

import java.util.List;

/**
 * Created by liban on 15.08.2018.
 */

public class Presenter implements DataSource.DataSourceResult {
    private MainView mView;
    private DataSource mDataSource;

    public Presenter(MainView mView) {
        this.mView = mView;
        mDataSource = new DataSource();
        mDataSource.requestTrending(this,  0);
        mView.showProgress(true);

    }


    @Override
    public void onDataTrending(ListData listData) {
        mView.showProgress(false);
        mView.onRequestTrending(listData);
    }

    @Override
    public void onError(String messageError) {
        mView.showProgress(false);
        mView.onError(messageError);
    }

    @Override
    public void onDataSearch(ListData listDataSearch) {
        mView.onRequestSearch(listDataSearch);
    }

    public void requestTrending(int offset){
        mDataSource.requestTrending(this,offset);
    }

    public void requestSearch(String query, int offset) {
        mDataSource.requestSearch(this, query, offset);
    }
    public void refresh(){
        mView.onRefresh();
    }



}
