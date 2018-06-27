package com.roberto.pokedex.data;

/**
 * Created by robertofz on 6/26/18.
 */

public class APIConstants {
    public static final String API_BASE_URL = "https://pokeapi.co/api/v2/";

    public static final String ID = "id";
    public static final String COUNT = "count";
    public static final String PREVIOUS = "previous";
    public static final String NEXT = "next";
    public static final String RESULTS = "results";
    public static final String NAME = "name";
    public static final String URL = "url";

    public static final String POKEMONS_ENDPOINT = "pokemon";
    public static final String POKEMON_ENDPOINT = POKEMONS_ENDPOINT + "/{" + ID + "}/";
}
