package com.roberto.pokedex.domain;

import com.google.gson.annotations.SerializedName;

import static com.roberto.pokedex.data.APIConstants.ID;
import static com.roberto.pokedex.data.APIConstants.NAME;
import static com.roberto.pokedex.data.APIConstants.URL;

/**
 * Created by robertofz on 6/26/18.
 */

public class Pokemon {
    @SerializedName(ID)
    private int id;
    @SerializedName(NAME)
    private String name;
    @SerializedName(URL)
    private String url;

    public String getName(){
        return name;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getUrl(){
        return url;
    }
}
