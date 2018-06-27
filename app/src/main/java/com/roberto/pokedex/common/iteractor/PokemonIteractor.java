package com.roberto.pokedex.common.iteractor;

import android.util.Log;

import com.roberto.pokedex.common.network.APIClient;
import com.roberto.pokedex.common.network.PokemonService;
import com.roberto.pokedex.common.network.SingleItemCallback;
import com.roberto.pokedex.domain.Pokemon;
import com.roberto.pokedex.domain.PokemonPage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by robertofz on 6/26/18.
 */

public class PokemonIteractor {
    private static PokemonIteractor instance;
    private PokemonService pokemonService;

    private PokemonIteractor() {
        this.pokemonService = APIClient.getClient().create(PokemonService.class);
    }

    public static PokemonIteractor getInstance(){
        if(instance == null){
            instance = new PokemonIteractor();
        }
        return instance;
    }

    public void getPokemonPage(int limit, int offset, final SingleItemCallback callback) {
        Call<PokemonPage> call = pokemonService.getPokemonsPage(limit, offset);
        call.enqueue(new Callback<PokemonPage>() {
            @Override
            public void onResponse(Call<PokemonPage> call, Response<PokemonPage> response) {
                Log.d("PokemonInteractor", response.code() + "");
                callback.onItemLoaded(response.body());
            }

            @Override
            public void onFailure(Call<PokemonPage> call, Throwable t) {
                t.printStackTrace();
                callback.onServerError("Error");
            }
        });
    }

    public void getPokemon(long pokemonId, final SingleItemCallback callback){
        Call<Pokemon> call = pokemonService.getPokemon(pokemonId);
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                Log.d("PokemonInteractor", response.code() + "");
                callback.onItemLoaded(response.body());
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                callback.onServerError("Error");
            }
        });
    }
}
