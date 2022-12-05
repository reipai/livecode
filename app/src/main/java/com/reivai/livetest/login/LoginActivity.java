package com.reivai.livetest.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import com.reivai.livetest.main.MainActivity;
import com.reivai.livetest.R;
import com.reivai.livetest.databinding.ActivityLoginBinding;
import com.reivai.livetest.network.model.LoginResponse;
import com.reivai.livetest.session.Session;
import com.reivai.sweetalertdialog.SweetAlertDialog;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private ActivityLoginBinding binding;
    LoginPresenter presenter;
    SweetAlertDialog alertDialog;
    Context context;
    Session session;
    String email, password;
    boolean submitStat = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        context = this;
        presenter = new LoginPresenter(this, context);
        session = new Session(context);

        binding.btnLogin.setOnClickListener(v -> {
            if (TextUtils.isEmpty(Objects.requireNonNull(binding.etEmail.getText()).toString())) {
                binding.layoutEmail.setError(getString(R.string.email_error));
                submitStat = false;
            } else {
                if (Patterns.EMAIL_ADDRESS.matcher(Objects.requireNonNull(binding.etEmail.getText())).matches()) {
                    binding.layoutEmail.setError(null);
                    email = binding.etEmail.getText().toString();
                    submitStat = true;
                } else {
                    binding.layoutEmail.setError(getString(R.string.email_validator));
                    submitStat = false;
                }
            }

            if (TextUtils.isEmpty(Objects.requireNonNull(binding.etPass.getText()).toString())) {
                binding.layoutPassword.setError(getString(R.string.password_error));
                submitStat = false;
            }else {
                binding.layoutPassword.setError(null);
                password = binding.etPass.getText().toString();
            }

            if (submitStat) {
                alertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
                alertDialog.setTitleText("Loading").setCancelable(false);
                alertDialog.show();

                presenter.login(email, password);
            }
        });

        binding.btnRegister.setOnClickListener(v -> {
            alertDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
            alertDialog.setCustomImage(getDrawable(R.drawable.rounded_error))
                    .setTitleText("Register is not available")
                    .setContentText("You can't register new user. Please contact admin to register manually")
                    .setConfirmText("Ok").show();
        });
    }

    @Override
    public void success(LoginResponse response) {
        alertDialog.dismiss();

        if (!response.getToken().isEmpty()) {
            session.saveToken(response);

            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void error(String error) {
        alertDialog.dismiss();

        alertDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
        alertDialog.setCustomImage(getDrawable(R.drawable.rounded_error))
                .setTitleText("Login Failed")
                .setContentText("Username and password is not match")
                .setConfirmText("Ok").show();

        Log.d("wakacaw", "error: " + error);
    }
}