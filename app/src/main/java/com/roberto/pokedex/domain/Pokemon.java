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
    public int id;
    @SerializedName(NAME)
    public String name;
    @SerializedName(URL)
    public String url;

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
