package com.example.liban.giphysearch;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.liban.giphysearch.mvp.presenter.Presenter;

/**
 * Created by liban on 17.08.2018.
 */

public class AddListenerTextChanged implements TextWatcher {
    private Presenter mPresenter;

    public AddListenerTextChanged(Presenter presenter) {
        this.mPresenter = presenter;

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mPresenter.requestSearch(s.toString(),0);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
