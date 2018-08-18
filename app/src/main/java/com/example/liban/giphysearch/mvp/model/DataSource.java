package com.example.liban.giphysearch.mvp.model;

import com.example.liban.giphysearch.Api;
import com.example.liban.giphysearch.DTO.ListData;
import com.example.liban.giphysearch.RecyclerAdapter;

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
    private static final String API_KEY = "RxMCpX8XMCAXA33xI6XgT4KwwzYKgTxG";




    public interface DataSourceResult {
        void onDataTrending(ListData listData);

        void onError(String messageError);
        void onDataSearch(ListData listDataSearch);
    }


    public DataSource() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }


    public void requestTrending(final DataSourceResult dataSourceResult, int offset) {
        api.getTrending(API_KEY,offset).enqueue(new Callback<ListData>() {
            @Override
            public void onResponse(Call<ListData> call, Response<ListData> response) {
                dataSourceResult.onDataTrending(response.body());

            }

            @Override
            public void onFailure(Call<ListData> call, Throwable t) {
                dataSourceResult.onError(t.getMessage());
            }
        });
    }

    public void requestSearch(final DataSourceResult dataSourceResult, String query, int offset) {
        api.search(query, API_KEY, offset).enqueue(new Callback<ListData>() {
            @Override
            public void onResponse(Call<ListData> call, Response<ListData> response) {
                dataSourceResult.onDataSearch(response.body());

            }

            @Override
            public void onFailure(Call<ListData> call, Throwable t) {
                dataSourceResult.onError(t.getMessage());
            }
        });
    }


}
