package com.sandystudios.weatherapp.data.source.remote

import com.sandystudios.weatherapp.BuildConfig
import com.sandystudios.weatherapp.data.model.LocationModel
import com.sandystudios.weatherapp.data.model.NetworkWeather
import com.sandystudios.weatherapp.data.model.NetworkWeatherForecast
import com.sandystudios.weatherapp.data.source.remote.retrofit.WeatherApi
import com.sandystudios.weatherapp.data.source.remote.retrofit.WeatherApiService
import com.sandystudios.weatherapp.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRemoteDataSourceImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val retrofitClient: WeatherApiService = WeatherApi.retrofitService
) : WeatherRemoteDataSource {
    override suspend fun getSearchWeather(query: String): Result<NetworkWeather> =
        withContext(ioDispatcher) {
            return@withContext try {
                val result = retrofitClient.getSpecificWeather(query, BuildConfig.API_KEY)
                if (result.isSuccessful) {
                    val networkWeather = result.body()
                    Result.Success(networkWeather)
                } else {
                    Result.Success(null)
                }
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }

    override suspend fun getWeather(location: LocationModel): Result<NetworkWeather> =
        withContext(ioDispatcher) {
            return@withContext try {
                val result = retrofitClient.getCurrentWeather(
                    location.latitude, location.longitude, BuildConfig.API_KEY
                )
                if (result.isSuccessful) {
                    val networkWeather = result.body()
                    Result.Success(networkWeather)
                } else {
                    Result.Success(null)
                }
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }

    override suspend fun getWeatherForecast(cityId: Int): Result<List<NetworkWeatherForecast>> =
        withContext(ioDispatcher) {
            return@withContext try {
                val result = retrofitClient.getWeatherForecast(cityId, BuildConfig.API_KEY)
                if (result.isSuccessful) {
                    val networkWeatherForecast = result.body()?.weathers
                    Result.Success(networkWeatherForecast)
                } else {
                    Result.Success(null)
                }
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }
}