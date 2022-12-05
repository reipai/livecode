package com.reivai.livetest.main.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.reivai.livetest.R;
import com.reivai.livetest.databinding.ActivityDetailMainBinding;

public class DetailMainActivity extends AppCompatActivity {
    private ActivityDetailMainBinding binding;
    Context context;
    String id, name, email, imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.backwhite);
        }

        context = this;

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        imageUrl = intent.getStringExtra("image");

        binding.tvId.setText("ID: " + id);
        binding.tvName.setText("Name: " + name);
        binding.tvEmail.setText("Email: " + email);

        Glide.with(context).load(imageUrl).into(binding.ivPhoto);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}