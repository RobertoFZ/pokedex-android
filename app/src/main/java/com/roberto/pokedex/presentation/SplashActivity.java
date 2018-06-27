package com.roberto.pokedex.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.roberto.pokedex.data.UserSessionManager;
import com.roberto.pokedex.presentation.Home.HomeActivity;
import com.roberto.pokedex.presentation.Login.LoginActivity;

/**
 * Created by robertofz on 6/26/18.
 */

public class SplashActivity extends Activity {
    private UserSessionManager userSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userSessionManager = new UserSessionManager(this);

        if(userSessionManager.isUserLoggedIn()){
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        }else{
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }
        finish();
    }
}
