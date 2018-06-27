package com.roberto.pokedex.presentation.Login;

/**
 * Created by robertofz on 6/26/18.
 */

public interface LoginContract {
    interface View {
        void showProgress();
        void showLoginButton();
        void showCredentialsError();
        void hideCredentialsError();
        void hideProgress();
        void hideLoginButton();
        void navigateToHome();
        void setEmailError();
        void setPasswordError();
    }

    interface UserActions {
        void validateCredentials(String email, String password);
    }
}
