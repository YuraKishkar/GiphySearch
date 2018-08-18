package com.example.liban.giphysearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.liban.giphysearch.mvp.presenter.Presenter;

/**
 * Created by liban on 17.08.2018.
 */

public class AddListenerTextChanged implements TextWatcher {
    private Presenter mPresenter;
    private EditText mEditText;


    public AddListenerTextChanged(EditText editText, Presenter presenter) {
        this.mPresenter = presenter;
        this.mEditText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mPresenter.requestSearch(mEditText.getText().toString(),0);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
