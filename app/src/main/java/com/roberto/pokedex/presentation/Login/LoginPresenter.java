package com.roberto.pokedex.presentation.Login;

import android.text.TextUtils;

import com.roberto.pokedex.data.UserSessionManager;
import com.roberto.pokedex.common.iteractor.LoginIteractor;
import com.roberto.pokedex.domain.User;

/**
 * Created by robertofz on 6/26/18.
 */

public class LoginPresenter implements LoginContract.UserActions, LoginIteractor.OnLoginFinishedListener{
    private LoginContract.View loginView;
    private LoginIteractor loginIteractor;
    private UserSessionManager userSessionManager;

    public LoginPresenter(LoginContract.View loginView, LoginIteractor loginIteractor, UserSessionManager userSessionManager) {
        this.loginView = loginView;
        this.loginIteractor = loginIteractor;
        this.userSessionManager = userSessionManager;
    }

    @Override
    public void validateCredentials(String email, String password) {
        if (loginView != null) {
            loginView.showProgress();
            loginView.hideLoginButton();
            loginView.hideCredentialsError();
        }
        if(!checkEmpyCredentials(email, password)){
            loginIteractor.login(email, password, this);
        }
    }

    @Override
    public void onCredentialsError() {
        loginView.hideProgress();
        loginView.showLoginButton();
        loginView.showCredentialsError();
    }

    @Override
    public void onSuccess(User user) {
        if (loginView != null) {
            try {
                userSessionManager.registerUser(user);
                loginView.navigateToHome();
            } catch (UserSessionManager.NullUserSession nullUserSession) {
                nullUserSession.printStackTrace();
            }
        }
    }

    private boolean checkEmpyCredentials(String email, String password){
        boolean emptyCredentials = false;
        if (TextUtils.isEmpty(email)) {
            loginView.setEmailError();
            loginView.hideProgress();
            loginView.showLoginButton();
            emptyCredentials = true;
        }
        if (TextUtils.isEmpty(password)) {
            loginView.setPasswordError();
            loginView.hideProgress();
            loginView.showLoginButton();
            emptyCredentials = true;
        }
        return emptyCredentials;
    }
}
