package com.reivai.livetest.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;

import com.reivai.livetest.R;
import com.reivai.livetest.databinding.ActivityMainBinding;
import com.reivai.livetest.main.adapter.MainAdapter;
import com.reivai.livetest.network.model.Data;
import com.reivai.livetest.network.model.UserResponse;
import com.reivai.livetest.session.Session;
import com.reivai.sweetalertdialog.SweetAlertDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    private ActivityMainBinding binding;
    private MainPresenter presenter;
    private List<Data> userData;
    private MainAdapter adapter;
    SweetAlertDialog alertDialog;
    Context context;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        context = this;
        session = new Session(context);
        presenter = new MainPresenter(this, context);

        userData = new ArrayList<>();
        adapter = new MainAdapter(context, userData);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);

        binding.swipeRL.setOnRefreshListener(this::loadStory);
        binding.swipeRL.post(() -> {
            binding.swipeRL.setRefreshing(true);
            loadStory();
        });

    }

    private void loadStory() {
        presenter.getUser("Bearer " + session.getToken(), 1);
    }

    @Override
    public void success(UserResponse response) {
        userData.clear();

        if (response.getData().size() != 0) {
            userData.addAll(response.getData());
            if (!userData.isEmpty()) {
                binding.recyclerView.scrollToPosition(userData.size());
            }
        }

        if (binding.swipeRL.isRefreshing()) {
            binding.swipeRL.setRefreshing(false);
        }
    }

    @Override
    public void error(String message) {
        alertDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
        alertDialog.setTitleText(getString(R.string.error_getuser))
                .setContentText(message)
                .show();
    }
}