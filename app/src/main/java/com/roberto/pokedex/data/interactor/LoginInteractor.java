package com.roberto.pokedex.data.interactor;

import android.os.Handler;
import android.text.TextUtils;

/**
 * Created by robertofz on 6/26/18.
 */

public class LoginInteractor {

    public interface OnLoginFinishedListener {
        void onEmailError();

        void onPasswordError();

        void onSuccess();
    }

    public void login(final String email, final String password, final OnLoginFinishedListener listener) {
        // TODO: IMPLEMENT DUMMY LOGIN WHIT STATIC DATA
    }

}