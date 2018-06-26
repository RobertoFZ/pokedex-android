package com.roberto.pokedex.presentation.Login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.roberto.pokedex.R;
import com.roberto.pokedex.data.interactor.LoginInteractor;

/**
 * Created by robertofz on 6/26/18.
 */

public class LoginActivity extends Activity implements LoginContract.View, View.OnClickListener {
    private ProgressBar progressBar;
    private EditText email;
    private EditText password;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        findViewById(R.id.button).setOnClickListener(this);

        presenter = new LoginPresenter(this, new LoginInteractor());
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setEmailError() {
        // TODO: SET EMAIL ERROR
    }

    @Override
    public void setPasswordError() {
        // TODO: SET PASSWORD ERROR
    }

    @Override
    public void navigateToHome() {
        // TODO: LAUNCH HOME ACTIVITY
    }

    @Override
    public void onClick(View view) {

    }
}
