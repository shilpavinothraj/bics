package com.example.bicsglobal.api


import product_response_Base
import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface CartInterface {

        @GET("everything?q=android&from=2021-04-01&sortBy=publishedAt&apiKey=d1129ee7cc7f409eb324c6228ce11726")
        fun products(): Call<product_response_Base?>?


}