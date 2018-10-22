package com.example.liban.giphysearch;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class DetailGif extends AppCompatActivity {

    public static final String URL = "URL_GIF";
    private ImageView mImageView;
    private Button mButton;
    private String mStringUrl;
    private ShareActionProvider shareActionProvider;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gif);
        mImageView = findViewById(R.id.big_gif_id);
        mProgressBar = findViewById(R.id.id_bar_detail);
        Intent intent = getIntent();
        mStringUrl = intent.getStringExtra(URL);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Glide.with(this)
                .asGif()
                .load(mStringUrl)
                .listener(new RequestListener<GifDrawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<GifDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, Object model,
                                                   Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                        mProgressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(mImageView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.id_share);
        shareActionProvider = new ShareActionProvider(this);
        MenuItemCompat.setActionProvider(menuItem, shareActionProvider);
        GoIntent();
        return super.onCreateOptionsMenu(menu);
    }

    private void GoIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/gif");
        intent.putExtra(Intent.EXTRA_STREAM, mStringUrl);
        shareActionProvider.setShareIntent(intent);
    }
}
