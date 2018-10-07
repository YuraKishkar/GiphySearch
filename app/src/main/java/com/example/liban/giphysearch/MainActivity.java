package com.example.liban.giphysearch;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liban.giphysearch.dto.ListData;
import com.example.liban.giphysearch.mvp.presenter.Presenter;
import com.example.liban.giphysearch.mvp.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private ProgressDialog mProgressDialog;
    private EditText mEditText;
    private Presenter mPresenter;
    private RecyclerAdapter mRecyclerAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mTextView;
    private boolean isRefresh;

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new Presenter(this, this);
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView = findViewById(R.id.recycler_id);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mEditText = findViewById(R.id.edit_search_query_id);
        mTextView = findViewById(R.id.status_text_id);
        mSwipeRefreshLayout = findViewById(R.id.swipe_id);
        initListeners();

    }


    private void initListeners() {
        mEditText.addTextChangedListener(new AddListenerTextChanged(mPresenter));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mPresenter.refresh();
        });
    }


    @Override
    public void onRequestTrending(ListData listData) {

        if (mRecyclerAdapter == null) {
            mRecyclerAdapter = new RecyclerAdapter(listData, this);
            mRecyclerAdapter.setTrendingContains(true);
            mRecyclerView.setAdapter(mRecyclerAdapter);
        }
        if (isRefresh) {
            mRecyclerAdapter.setTrendingContains(true);
            mRecyclerAdapter.clearData();
            mRecyclerAdapter.addNewGifs(listData.getData());

            setRefresh(false);
        }

        onScrollRecycler(listData, new AddListener() {
            @Override
            public void onEnd() {
                mOffsetCount += 25;
                mPresenter.requestTrending(mOffsetCount);
            }
        });
    }

    private int mOffsetCount = 0;

    private void onScrollRecycler(final ListData listData, AddListener addListener) {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {

                    if (mRecyclerAdapter.isTrendingContains()) {
                        addListener.onEnd();
                        mRecyclerAdapter.addNewGifs(listData.getData());
                    }

                }
            }
        });
    }


    @Override
    public void onRequestSearch(ListData listDataSearch) {
        mTextView.setText(getResources().getString(R.string.search_gif));
        mRecyclerAdapter.clearData();
        mRecyclerAdapter.addNewGifs(listDataSearch.getData());
        mRecyclerAdapter.setTrendingContains(false);

    }

    @Override
    public void onRefresh() {
        setRefresh(true);
        mTextView.setText(getResources().getString(R.string.trending_gifs));
        mTextView.setVisibility(View.VISIBLE);
        mRecyclerAdapter.setTrendingContains(true);
        mPresenter.requestTrending(0);
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onError(String messageError) {
        Toast.makeText(this, "You are not connected to the Internet", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress(boolean flag) {
        if (flag)
            mProgressDialog = ProgressDialog.show(this, "Loading", "Wait");
        else
            mProgressDialog.dismiss();

    }


}
