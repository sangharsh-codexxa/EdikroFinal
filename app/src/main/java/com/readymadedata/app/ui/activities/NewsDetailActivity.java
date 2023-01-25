package com.readymadedata.app.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;

import com.readymadedata.app.Ads.BannerAdManager;
import com.readymadedata.app.R;
import com.readymadedata.app.binding.GlideBinding;
import com.readymadedata.app.databinding.ActivityNewsDetailBinding;
import com.readymadedata.app.items.NewsItem;
import com.readymadedata.app.utils.Constant;

public class NewsDetailActivity extends AppCompatActivity {

    ActivityNewsDetailBinding binding;
    NewsItem newsItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewsDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BannerAdManager.showBannerAds(this, binding.llAdview);
        setUpUi();
    }

    private void setUpUi() {



        binding.toolbar.toolName.setText(getResources().getString(R.string.menu_news));
        binding.toolbar.toolbarIvMenu.setBackground(getResources().getDrawable(R.drawable.ic_back));
        binding.toolbar.toolbarIvMenu.setOnClickListener(v -> {
            onBackPressed();
        });

        if(getIntent().getExtras()!=null) {

            newsItem = (NewsItem) getIntent().getSerializableExtra(Constant.INTENT_NEWS_ITEM);
            GlideBinding.bindImage(binding.ivNews, newsItem.imageUrl);

            binding.tvTitle.setText(newsItem.title);
            binding.tvDate.setText(newsItem.date);

            binding.wvNews.getSettings().setJavaScriptEnabled(true);
            String encodedHtml = Base64.encodeToString(newsItem.description.getBytes(),
                    Base64.NO_PADDING);
            binding.wvNews.loadData(encodedHtml, "text/html", "base64");
        }
    }
}