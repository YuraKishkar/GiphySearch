package com.example.liban.giphysearch;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liban.giphysearch.DTO.Data;
import com.example.liban.giphysearch.DTO.ListData;
import com.example.liban.giphysearch.mvp.presenter.Presenter;
import com.example.liban.giphysearch.mvp.view.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private ProgressDialog mProgressDialog;
    private EditText mEditText;
    private Presenter mPresenter;
    private RecyclerAdapter mRecyclerAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new Presenter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView = findViewById(R.id.recycler_id);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mEditText = findViewById(R.id.edit_search_query_id);
        mSwipeRefreshLayout = findViewById(R.id.swipe_id);
        initListeners();


    }

    private void initListeners() {
        mEditText.addTextChangedListener(new AddListenerTextChanged(mEditText, mPresenter));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mPresenter.refresh();
//            mPresenter.requestTrending(0);
        });
    }


    @Override
    public void onRequestTrending(ListData listData) {
        //if (mRecyclerAdapter == null) {
        mRecyclerAdapter = new RecyclerAdapter(listData, this);
        mRecyclerAdapter.setTrendingContains(true);
        mRecyclerView.setAdapter(mRecyclerAdapter);
//        mSwipeRefreshLayout.setRefreshing(false);


//            onScrollRecycler(listData, new OnPositionListEnd() {
//                @Override
//                public void onEnd() {
//                    mOffsetCount += 25;
//                    mPresenter.requestTrending(mOffsetCount);
//                }
//            });
        //}
        onScrollRecycler(listData);
//        if (mRecyclerAdapter.getListData().getData().size() != 0) {
//            mRecyclerAdapter.addNewGifs(listData.getData());
//        }


    }

    private int mOffsetCount;

    private void onScrollRecycler(final ListData listData) {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    mOffsetCount += 25;
                    mPresenter.requestTrending(mOffsetCount);
                    if (mRecyclerAdapter.getListData().getData().size() != 0) {
                        mRecyclerAdapter.addNewGifs(listData.getData());
                    }
//                    if (mRecyclerAdapter.isTrendingContains()) {
//                        onPositionListEnd.onEnd();
//                    } else {
//
//                    }
                }
            }
        });
    }


    @Override
    public void onRequestSearch(ListData listDataSearch) {
        mRecyclerAdapter.clearData();
        mRecyclerAdapter.addNewGifs(listDataSearch.getData());
        mRecyclerAdapter.setTrendingContains(false);
        onScrollRecycler(listDataSearch);
    }

    @Override
    public void onRefresh() {
        mPresenter.requestTrending(0);
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onError(String messageError) {
        Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(boolean flag) {
        if (flag)
            mProgressDialog = ProgressDialog.show(this, "Loading", "Wait");
        else
            mProgressDialog.dismiss();

    }


}



