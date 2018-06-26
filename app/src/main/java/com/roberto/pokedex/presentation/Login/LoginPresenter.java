package com.roberto.pokedex.presentation.Login;

import android.text.TextUtils;

import com.roberto.pokedex.data.UserSessionManager;
import com.roberto.pokedex.data.interactor.LoginInteractor;
import com.roberto.pokedex.domain.User;

/**
 * Created by robertofz on 6/26/18.
 */

public class LoginPresenter implements LoginContract.UserActions, LoginInteractor.OnLoginFinishedListener{
    private LoginContract.View loginView;
    private LoginInteractor loginInteractor;
    private UserSessionManager userSessionManager;

    public LoginPresenter(LoginContract.View loginView, LoginInteractor loginInteractor, UserSessionManager userSessionManager) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
        this.userSessionManager = userSessionManager;
    }

    @Override
    public void validateCredentials(String email, String password) {
        if (loginView != null) {
            loginView.showProgress();
            loginView.hideLoginButton();
            loginView.hideCredentialsError();
        }
        if(!isEmptyCredentials(email, password)){
            loginInteractor.login(email, password, this);
        }
    }

    @Override
    public void onDestroy() {
        loginView = null;
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

    private boolean isEmptyCredentials(String email, String password){
        if (TextUtils.isEmpty(email)) {
            loginView.setEmailError();
            loginView.hideProgress();
            loginView.showLoginButton();
            return true;
        }
        if (TextUtils.isEmpty(password)) {
            loginView.setPasswordError();
            loginView.hideProgress();
            loginView.showLoginButton();
            return true;
        }
        return false;
    }
}
