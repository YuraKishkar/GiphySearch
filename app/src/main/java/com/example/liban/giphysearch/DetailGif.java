package com.example.liban.giphysearch;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DetailGif extends AppCompatActivity {

    public static final String URL = "URL_GIF";
    private ImageView mImageView;
    private Button mButton;
    private String mStringUrl;
    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gif);
        mImageView = findViewById(R.id.big_gif_id);
        Intent intent = getIntent();
        mStringUrl = intent.getStringExtra(URL);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Glide.with(this)
                .asGif()
                .load(mStringUrl)
                .into(mImageView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.id_share);
        shareActionProvider = new ShareActionProvider(this);
        MenuItemCompat.setActionProvider(menuItem, shareActionProvider);
        GoIntent();
        return super.onCreateOptionsMenu(menu);
    }

    private void GoIntent(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/gif");
        intent.putExtra(Intent.EXTRA_STREAM, mStringUrl);
        shareActionProvider.setShareIntent(intent);
    }
}
