package com.example.liban.giphysearch.mvp.model;

import com.example.liban.giphysearch.Api;
import com.example.liban.giphysearch.dto.ListData;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liban on 15.08.2018.
 */

public class DataSource {
    private Api api;
    private static final String BASE_URL = "http://api.giphy.com/v1/gifs/";
    private static final String API_KEY = "t5Roj1Z4Lnp0xIQj707W98dEOxz0S95o";
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public interface DataSourceResult {
        void onDataTrending(ListData listData);

        void onError(String messageError);

        void onDataSearch(ListData listDataSearch);
    }


    public DataSource() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }


    public void requestTrending(final DataSourceResult dataSourceResult, int offset) {
        mCompositeDisposable.add(api.getTrending(API_KEY, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataSourceResult::onDataTrending,
                        throwable -> dataSourceResult.onError(throwable.getMessage())));
    }

    public void requestSearch(final DataSourceResult dataSourceResult, String query, int offset) {
        mCompositeDisposable.add(api.search(query, API_KEY, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataSourceResult::onDataSearch,
                        throwable -> dataSourceResult.onError(throwable.getMessage())));
    }


}
