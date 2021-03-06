package com.rperazzo.weatherapp.Model;

import android.content.Context;

import com.rperazzo.weatherapp.Util.IConnectivityUtil;
import com.rperazzo.weatherapp.View.Interface.ISearch;

/**
 * Created by Thiago on 10/11/2018.
 */

public interface ICityRepository {

    boolean searchByName(String name, final ISearch search, String units, String lang, IConnectivityUtil connect);

}
