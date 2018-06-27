package com.roberto.pokedex.presentation.Home;

import android.util.Log;

import com.roberto.pokedex.common.iteractor.PokemonIteractor;
import com.roberto.pokedex.common.network.SingleItemCallback;
import com.roberto.pokedex.data.PokemonManager;
import com.roberto.pokedex.data.UserSessionManager;
import com.roberto.pokedex.domain.PokemonPage;

import static com.roberto.pokedex.data.APIConstants.LIST_LIMIT;

/**
 * Created by robertofz on 6/26/18.
 */

public class HomePresenter implements HomeContract.UserActions, SingleItemCallback {
    private PokemonManager pokemonManager = PokemonManager.getInstance();
    private HomeContract.View homeView;
    private UserSessionManager userSessionManager;
    private PokemonIteractor pokemonInteractor;

    public HomePresenter(HomeContract.View homeView, UserSessionManager userSessionManager, PokemonIteractor pokemonInteractor) {
        this.homeView = homeView;
        this.userSessionManager = userSessionManager;
        this.pokemonInteractor = pokemonInteractor;
        showUserName();
    }

    @Override
    public void onNetworkError(String networkErrorMessage) {
        homeView.hideProgress();
        homeView.showError();
        Log.d("HOME_PRESENTER", networkErrorMessage);
    }

    @Override
    public void onServerError(String serverErrorMessage) {
        homeView.hideProgress();
        homeView.showError();
        Log.d("HOME_PRESENTER", serverErrorMessage);
    }

    @Override
    public void onItemLoaded(Object response) {
        PokemonPage pokemonPage = (PokemonPage) response;
        pokemonManager.updatePokemonsList(pokemonPage);

        homeView.updatePokemonList(pokemonManager.getPokemons());
        homeView.hideProgress();
    }

    @Override
    public void loadPokemonPage() {
        homeView.hideError();
        homeView.showProgress();
        pokemonInteractor.getPokemonPage(LIST_LIMIT, pokemonManager.getOffset(), this);
    }

    @Override
    public void loadNextPokemonPage() {
        Integer count = pokemonManager.getCount();
        Integer currentListSize = pokemonManager.getPokemons().size();
        if (count > currentListSize) {
            homeView.showLoadMore();
            pokemonInteractor.getPokemonPage(LIST_LIMIT, pokemonManager.getOffset(), this);
        }
    }

    @Override
    public void logoutUser() {
        userSessionManager.logoutUser();
        homeView.sendToLogin();
    }

    private void showUserName() {
        String firstName = userSessionManager.getCurrentUser().getFirstName();
        String lastName = userSessionManager.getCurrentUser().getLastName();
        String fullName = firstName + " " + lastName;
        homeView.showUserName(fullName);
    }
}
