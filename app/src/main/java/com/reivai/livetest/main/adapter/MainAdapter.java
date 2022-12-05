package com.reivai.livetest.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reivai.livetest.R;
import com.reivai.livetest.main.detail.DetailMainActivity;
import com.reivai.livetest.network.model.Data;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private List<Data> userData;

    public MainAdapter(Context context, List<Data> userData) {
        this.context = context;
        this.userData = userData;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        Data dataUser = userData.get(position);
        holder.id.setText("ID: " + dataUser.getId());
        holder.name.setText("Name: " + dataUser.getFirst_name() + " " + dataUser.getLast_name());
        holder.email.setText("Email: " + dataUser.getEmail());

        String imageUrl = dataUser.getAvatar();
        Glide.with(context).load(imageUrl).into(holder.ivPhoto);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailMainActivity.class);
            intent.putExtra("id", holder.id.getText());
            intent.putExtra("name", holder.name.getText());
            intent.putExtra("email", holder.email.getText());
            intent.putExtra("image", imageUrl);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return userData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView id, name, email;
        private ImageView ivPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tvId);
            name = itemView.findViewById(R.id.tvName);
            email = itemView.findViewById(R.id.tvEmail);
            ivPhoto = itemView.findViewById(R.id.ivPhoto);
        }
    }
}
