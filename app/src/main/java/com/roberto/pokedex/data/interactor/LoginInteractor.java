package com.roberto.pokedex.data.interactor;

import android.os.Handler;
import android.text.TextUtils;

import com.roberto.pokedex.domain.User;

import java.util.Objects;

/**
 * Created by robertofz on 6/26/18.
 */

public class LoginInteractor {

    private static final String EMAIL = "test@gmail.com";
    private static final String PASSWORD = "testemail";

    public interface OnLoginFinishedListener {

        void onCredentialsError();

        void onSuccess(User user);
    }

    public void login(final String email, final String password, final OnLoginFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                if(!Objects.equals(email, EMAIL) || !Objects.equals(password, PASSWORD)){
                    listener.onCredentialsError();
                }
                User user = new User();
                user.setEmail(EMAIL);
                user.setFirstName("Test");
                user.setLastName("User");
                listener.onSuccess(user);
            }
        }, 2000);
    }

}