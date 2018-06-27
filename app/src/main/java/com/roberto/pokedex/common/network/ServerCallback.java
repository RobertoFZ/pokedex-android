package com.roberto.pokedex.common.network;

/**
 * Created by robertofz on 6/26/18.
 */

public interface ServerCallback {
    void onNetworkError(String networkErrorMessage);

    void onServerError(String serverErrorMessage);
}
