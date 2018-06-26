package com.roberto.pokedex.presentation.Login;

/**
 * Created by robertofz on 6/26/18.
 */

public interface LoginContract {
    interface View {
        void showProgress();
        void hideProgress();
        void setEmailError();
        void setPasswordError();
        void navigateToHome();
    }

    interface UserActions {
        void validateCredentials(String email, String password);
        void onDestroy();
    }
}
