package com.roberto.pokedex.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import static com.roberto.pokedex.data.APIConstants.COUNT;
import static com.roberto.pokedex.data.APIConstants.NEXT;
import static com.roberto.pokedex.data.APIConstants.PREVIOUS;
import static com.roberto.pokedex.data.APIConstants.RESULTS;

/**
 * Created by robertofz on 6/26/18.
 */

public class PokemonPage {
    @SerializedName(COUNT)
    public int count;
    @SerializedName(PREVIOUS)
    public String previous;
    @SerializedName(RESULTS)
    public List<Pokemon> results;
    @SerializedName(NEXT)
    public String next;

    public List<Pokemon> getList(){
        return results;
    }
}
