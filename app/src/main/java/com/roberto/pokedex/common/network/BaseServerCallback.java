package com.roberto.pokedex.common.network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by robertofz on 6/26/18.
 */

public class BaseServerCallback<ServerResponse> implements Callback<ServerResponse> {

    private ServerCallback callback;

    public BaseServerCallback(ServerCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
        //Needs to override on concrete class.
    }

    @Override
    public void onFailure(Call<ServerResponse> call, Throwable t) {
        t.printStackTrace();
        callback.onServerError(t.getMessage());
    }

}
