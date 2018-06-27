package com.roberto.pokedex.common;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.roberto.pokedex.R;
import com.roberto.pokedex.domain.Pokemon;

import java.util.HashMap;
import java.util.List;

/**
 * Created by robertofz on 6/26/18.
 */

public class PokemonAdapter extends BaseAdapter {
    private Activity activity;
    private HashMap<Integer, Pokemon> pokemons;
    private Integer[] mapKeys;

    public PokemonAdapter(Activity activity, HashMap<Integer, Pokemon> pokemons) {
        this.activity = activity;
        this.pokemons = pokemons;
        mapKeys = pokemons.keySet().toArray(new Integer[pokemons.size()]);
    }

    @Override
    public int getCount() {
        return pokemons.size();
    }

    @Override
    public Pokemon getItem(int id) {
        return pokemons.get(id);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View convertView = view;

        if (view == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.pokemon_item, null);
        }

        Integer pokemonId = mapKeys[position];

        Pokemon pokemon = pokemons.get(pokemonId);
        pokemon.setId(position + 1);

        TextView name = convertView.findViewById(R.id.name);

        String lowerCaseName = pokemon.getName();
        String upperCaseName = lowerCaseName.substring(0, 1).toUpperCase() + lowerCaseName.substring(1);
        name.setText(upperCaseName);

        TextView number = convertView.findViewById(R.id.number);
        number.setText(String.valueOf("Nº " + pokemon.getId()));

        return convertView;
    }
}
