package com.sandystudios.weatherapp.data.source.remote

import com.sandystudios.weatherapp.data.model.LocationModel
import com.sandystudios.weatherapp.data.model.NetworkWeather
import com.sandystudios.weatherapp.data.model.NetworkWeatherForecast
import com.sandystudios.weatherapp.utils.Result

interface WeatherRemoteDataSource {
    suspend fun getWeather(location: LocationModel): Result<NetworkWeather>

    suspend fun getWeatherForecast(cityId: Int): Result<List<NetworkWeatherForecast>>

    suspend fun getSearchWeather(query: String): Result<NetworkWeather>
}