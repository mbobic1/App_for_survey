package ba.etf.rma22.projekat.data.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiAdapter {
    private val apiConfig : ApiConfig = ApiConfig

    val retrofit  : Api = Retrofit.Builder()
            .baseUrl(apiConfig.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Api::class.java)

}