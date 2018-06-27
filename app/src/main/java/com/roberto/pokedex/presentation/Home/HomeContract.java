package com.roberto.pokedex.presentation.Home;

import com.roberto.pokedex.domain.Pokemon;

import java.util.HashMap;

/**
 * Created by robertofz on 6/26/18.
 */

public class HomeContract {
    interface View {
        void hideError();
        void hideProgress();
        void sendToLogin();
        void showError();
        void showLoadMore();
        void showProgress();
        void showUserName(String fullName);
        void updatePokemonList(HashMap<Integer, Pokemon> pokemonList);
    }

    interface UserActions {
        void loadPokemonPage();
        void loadNextPokemonPage();
        void logoutUser();
    }
}
