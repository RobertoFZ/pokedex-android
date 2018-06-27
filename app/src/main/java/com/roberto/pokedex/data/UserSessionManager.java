package com.roberto.pokedex.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.roberto.pokedex.domain.User;

import java.util.concurrent.ExecutionException;

public class UserSessionManager {

    private SharedPreferences userPreferences;
    private SharedPreferences.Editor editor;
    private Context mContext;

    private final int PRIVATE_MODE = 0;

    private static final String USER_PREFERENCES = "USER_PREFERENCES";
    private static final String IS_USER_LOGIN = "IS_USER_LOGIN";

    private static final String KEY_FIRST_NAME = "KEY_FIRST_NAME";
    private static final String KEY_LAST_NAME = "KEY_LAST_NAME";
    private static final String KEY_EMAIL = "KEY_EMAIL";

    public UserSessionManager(Context context) {
        this.mContext = context;
        userPreferences = mContext.getSharedPreferences(USER_PREFERENCES, PRIVATE_MODE);
        editor = userPreferences.edit();
    }

    public void registerUser(User user) throws NullUserSession {

        if (user == null) {
            throw new NullUserSession();
        }
        saveUser(user);
    }

    public User getCurrentUser() {
        User user = new User();
        user.setFirstName(userPreferences.getString(KEY_FIRST_NAME, null));
        user.setLastName(userPreferences.getString(KEY_LAST_NAME, null));
        user.setEmail(userPreferences.getString(KEY_EMAIL, null));
        return user;
    }

    public boolean isUserLoggedIn() {
        return userPreferences.getBoolean(IS_USER_LOGIN, false);
    }


    public void logoutUser() {
        editor.remove(IS_USER_LOGIN);
        editor.remove(KEY_FIRST_NAME);
        editor.remove(KEY_LAST_NAME);
        editor.remove(KEY_EMAIL);
        editor.commit();
    }

    private void saveUser(User user) {
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_FIRST_NAME, user.getFirstName());
        editor.putString(KEY_LAST_NAME, user.getLastName());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.commit();
    }

    public class NullUserSession extends ExecutionException {

        public NullUserSession() {
            super("The User Object is NULL");
        }

    }
}