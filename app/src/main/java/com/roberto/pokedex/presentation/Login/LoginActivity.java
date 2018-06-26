package com.roberto.pokedex.presentation.Login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.roberto.pokedex.R;
import com.roberto.pokedex.data.UserSessionManager;
import com.roberto.pokedex.data.interactor.LoginInteractor;

/**
 * Created by robertofz on 6/26/18.
 */

public class LoginActivity extends Activity implements LoginContract.View{
    private ProgressBar progressBar;
    private TextView credentialsErrorTextView;
    private EditText emailTextView;
    private EditText passwordTextView;
    private Button loginButton;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        credentialsErrorTextView = findViewById(R.id.credentials_error);
        progressBar = findViewById(R.id.progress);
        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);

        presenter = new LoginPresenter(this, new LoginInteractor(), new UserSessionManager(this));
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailTextView.getText().toString();
                String password = passwordTextView.getText().toString();
                presenter.validateCredentials(email, password);
            }
        });
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
    public void showLoginButton() {
        loginButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoginButton() {
        loginButton.setVisibility(View.GONE);
    }

    @Override
    public void setEmailError() {
        emailTextView.setError(getResources().getString(R.string.email_error));
    }

    @Override
    public void setPasswordError() {
        passwordTextView.setError(getResources().getString(R.string.password_error));
    }

    @Override
    public void showCredentialsError() {
        credentialsErrorTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCredentialsError() {
        credentialsErrorTextView.setVisibility(View.GONE);
    }

    @Override
    public void navigateToHome() {
        // TODO: LAUNCH HOME ACTIVITY
    }
}
