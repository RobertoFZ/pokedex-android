package com.roberto.pokedex.presentation.Home;

import com.roberto.pokedex.domain.Pokemon;

import java.util.HashMap;
import java.util.List;

/**
 * Created by robertofz on 6/26/18.
 */

public class HomeContract {
    interface View {
        void sendToLogin();
        void updatePokemonList(HashMap<Integer, Pokemon> pokemonList);
        void showError();
        void hideError();
        void showLoadMore();
        void showProgress();
        void hideProgress();
        void showUserName(String fullName);
        void sendToListBottom(int itemPosition);
    }

    interface UserActions {
        void setCurrentVisibleItem(int firstVisible);
        void loadPokemonPage();
        void loadNextPokemonPage();
        void logoutUser();
    }
}
