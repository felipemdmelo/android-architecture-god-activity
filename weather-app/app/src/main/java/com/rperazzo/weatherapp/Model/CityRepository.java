package com.rperazzo.weatherapp.Model;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.rperazzo.weatherapp.Service.WeatherManager;
import com.rperazzo.weatherapp.Service.WeatherService;
import com.rperazzo.weatherapp.Storage.TemperatureSharedPref;
import com.rperazzo.weatherapp.Util.ConnectivityUtil;
import com.rperazzo.weatherapp.Util.IConnectivityUtil;
import com.rperazzo.weatherapp.View.Interface.ISearch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Thiago on 10/11/2018.
 */

public class CityRepository implements ICityRepository {

    @Override
    public boolean searchByName(String name, final ISearch search, String units, String lang, IConnectivityUtil connect) {

        if (!connect.isDeviceConnected()) {
            return false;
        }

        if (name.isEmpty()) {
            return false;
        }

        search.onStartLoading();

        WeatherService wService = WeatherManager.getService();

        final Call<WeatherManager.FindResult> findCall = wService.find(name, units, lang, WeatherManager.API_KEY);
        findCall.enqueue(new Callback<WeatherManager.FindResult>() {
            @Override
            public void onResponse(Call<WeatherManager.FindResult> call, Response<WeatherManager.FindResult> response) {
                search.onFinishLoading(response.body());
            }

            @Override
            public void onFailure(Call<WeatherManager.FindResult> call, Throwable t) {
                search.onFinishLoadingWithError();
            }
        });

        return true;
    }


}
