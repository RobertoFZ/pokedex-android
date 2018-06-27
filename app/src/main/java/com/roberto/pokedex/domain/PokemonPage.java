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
    private int count;
    @SerializedName(PREVIOUS)
    private String previous;
    @SerializedName(RESULTS)
    private List<Pokemon> results;
    @SerializedName(NEXT)
    private String next;

    public int getCount(){
        return count;
    }

    public String getNext(){
        return next;
    }

    public String getPrevious(){
        return previous;
    }

    public List<Pokemon> getList(){
        return results;
    }

}
