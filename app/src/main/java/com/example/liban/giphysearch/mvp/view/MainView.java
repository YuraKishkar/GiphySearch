package com.example.liban.giphysearch.mvp.view;

import com.example.liban.giphysearch.DTO.Data;
import com.example.liban.giphysearch.DTO.ListData;

import java.util.List;

/**
 * Created by liban on 15.08.2018.
 */

public interface MainView {

        void onRequestTrending(ListData listData);
        void onError(String messageError);
        void showProgress(boolean flag);
        void onRequestSearch(ListData listDataSearch);
        void onRefresh();

}
