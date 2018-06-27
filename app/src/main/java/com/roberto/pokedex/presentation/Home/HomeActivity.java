package com.roberto.pokedex.presentation.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.roberto.pokedex.R;
import com.roberto.pokedex.common.EndlessScrollListener;
import com.roberto.pokedex.common.PokemonAdapter;
import com.roberto.pokedex.common.iteractor.PokemonIteractor;
import com.roberto.pokedex.data.UserSessionManager;
import com.roberto.pokedex.domain.Pokemon;
import com.roberto.pokedex.presentation.Login.LoginActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by robertofz on 6/26/18.
 */

public class HomeActivity extends Activity implements HomeContract.View, View.OnClickListener {
    private HomePresenter presenter;
    private LinearLayout errorContainerLayour;
    private ProgressBar progressBar;
    private ListView pokemonListView;
    private Button tryAgainButton;
    private TextView userNameTextView;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        errorContainerLayour = findViewById(R.id.error_container);
        progressBar = findViewById(R.id.progress);
        pokemonListView = findViewById(R.id.pokemon_list);

        userNameTextView = findViewById(R.id.user_name);

        tryAgainButton = findViewById(R.id.try_again_button);

        floatingActionButton = findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(this);

        presenter = new HomePresenter(this, new UserSessionManager(this), PokemonIteractor.getInstance());
        presenter.loadPokemonPage();

        initListeners();
    }

    @Override
    public void onClick(View view) {
        createLogOutConfirm(view);
    }

    @Override
    public void sendToLogin() {
        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);
        finish();
    }

    @Override
    public void updatePokemonList(HashMap<Integer, Pokemon> pokemonList) {
        PokemonAdapter adapter = new PokemonAdapter(this, pokemonList);
        pokemonListView.setAdapter(adapter);
    }

    @Override
    public void showError() {
        errorContainerLayour.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        errorContainerLayour.setVisibility(View.GONE);
    }

    @Override
    public void showLoadMore() {
        Toast.makeText(this, getResources().getString(R.string.loading_more), Toast.LENGTH_SHORT).show();
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
    public void showUserName(String fullName) {
        userNameTextView.setText(fullName);
    }

    @Override
    public void sendToListBottom(int itemPosition) {
        Adapter adapter = pokemonListView.getAdapter();
        pokemonListView.setSelection(itemPosition);
    }

    private void createLogOutConfirm(View view) {
        Snackbar snackbar = Snackbar.make(view, getResources().getString(R.string.logout_confirm), Snackbar.LENGTH_LONG);
        snackbar.setAction(getResources().getString(R.string.yes), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.logoutUser();
            }
        });
        snackbar.show();
    }

    private void initListeners() {
        pokemonListView.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int totalItemsCount) {
                Log.d("Activity", "Page: " + totalItemsCount);
                presenter.setCurrentVisibleItem(totalItemsCount);
                presenter.loadNextPokemonPage();
                return true; // Always need to be true
            }
        });

        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadPokemonPage();
            }
        });

    }
}
