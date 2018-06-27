package com.roberto.pokedex.common.network;

import com.roberto.pokedex.domain.Pokemon;
import com.roberto.pokedex.domain.PokemonPage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.roberto.pokedex.data.APIConstants.ID;
import static com.roberto.pokedex.data.APIConstants.POKEMONS_ENDPOINT;
import static com.roberto.pokedex.data.APIConstants.POKEMON_ENDPOINT;

/**
 * Created by robertofz on 6/26/18.
 */

public interface PokemonService {
    @GET(POKEMONS_ENDPOINT)
    Call<PokemonPage> getPokemonsPage(@Query("limit") int limit, @Query("offset") int offset);

    @GET(POKEMON_ENDPOINT)
    Call<Pokemon> getPokemon(@Path(ID) long pokemonId);
}
