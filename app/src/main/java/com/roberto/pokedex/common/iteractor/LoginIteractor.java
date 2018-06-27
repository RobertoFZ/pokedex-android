package com.roberto.pokedex.common.iteractor;

import android.os.Handler;

import com.roberto.pokedex.domain.User;

import java.util.Objects;

import static com.roberto.pokedex.data.APIConstants.PASSWORD;
import static com.roberto.pokedex.data.APIConstants.USER_EMAIL;

/**
 * Created by robertofz on 6/26/18.
 */

public class LoginIteractor {

    public interface OnLoginFinishedListener {

        void onCredentialsError();

        void onSuccess(User user);
    }

    public void login(final String email, final String password, final OnLoginFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!Objects.equals(email, USER_EMAIL) || !Objects.equals(password, PASSWORD)) {
                    listener.onCredentialsError();
                } else {
                    User user = new User();
                    user.setEmail(USER_EMAIL);
                    user.setFirstName("Test");
                    user.setLastName("User");
                    listener.onSuccess(user);
                }
            }
        }, 2000);
    }

}