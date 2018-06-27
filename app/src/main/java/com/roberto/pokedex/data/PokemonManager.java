package com.roberto.pokedex.data;

import android.support.annotation.Nullable;
import android.util.Log;

import com.roberto.pokedex.domain.Pokemon;
import com.roberto.pokedex.domain.PokemonPage;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by robertofz on 6/26/18.
 */

public class PokemonManager {
    private static final Pattern offsetPattern = Pattern.compile("^.+offset=(\\d+)$");
    private static final Pattern pokemonIdPattern = Pattern.compile("^.+pokemon/(\\d+)/$");

    private static PokemonManager instance;
    private HashMap<Integer, Pokemon> pokemons;
    private int offset;
    private int previous;

    private PokemonManager() {
        this.offset = 0;
        this.previous = 0;
        this.pokemons = new HashMap<Integer, Pokemon>();
    }

    public static PokemonManager getInstance() {
        if (instance == null) {
            instance = new PokemonManager();

        }
        return instance;
    }

    public void addPokemon(Pokemon pokemon) {
        pokemons.put(pokemon.id, pokemon);
    }

    public Pokemon getPokemon(int pokemonId) {
        return pokemons.get(pokemonId);
    }

    public int getPrevious(){
        return previous;
    }

    public int getOffset() {
        return offset;
    }

    public void updatePokemonsList(PokemonPage pokemonPage){
        String previousUrl = pokemonPage.previous;
        String nextUrl = pokemonPage.next;

        updateNextPage(nextUrl);
        updatePreviousPage(previousUrl);

        List<Pokemon> pokemonList = pokemonPage.results;

        for (Pokemon pokemon : pokemonList) {
            String url = pokemon.getUrl();
            Matcher matcher = pokemonIdPattern.matcher(url);
            if(matcher.find()){
                pokemon.setId(Integer.parseInt(matcher.group(1)));
                pokemons.put(pokemon.getId(), pokemon);
            }
        }
    }

    public HashMap<Integer, Pokemon> getPokemons() {
        return pokemons;
    }

    private void updatePreviousPage(String previousUrl){
        if(previousUrl != null){
            Matcher matcher = offsetPattern.matcher(previousUrl);
            if(matcher.find()){
                previous = Integer.parseInt(matcher.group(1));
            }
        }
    }

    private void updateNextPage(String nextUrl){
        Matcher matcher = offsetPattern.matcher(nextUrl);
        if(matcher.find()){
            previous = offset;
            offset = Integer.parseInt(matcher.group(1));
        }else{
            offset = 0;
            previous = 0;
        }
    }
}
