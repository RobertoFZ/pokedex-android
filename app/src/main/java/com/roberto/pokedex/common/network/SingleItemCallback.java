package com.roberto.pokedex.common.network;

/**
 * Created by luisburgos on 9/13/16.
 */
public interface SingleItemCallback<Item> extends ServerCallback{
    void onItemLoaded(Item item);
}
