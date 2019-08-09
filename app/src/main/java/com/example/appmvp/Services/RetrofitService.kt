package com.example.appmvp.Services

import android.os.Build
import android.telecom.Call
import androidx.annotation.RequiresApi
import java.net.HttpURLConnection
import java.net.URL
import retrofit2.Retrofit


// Request a string response from the provided URL.




var retrofit = Retrofit.Builder()
    .baseUrl("https://api.github.com/")
    .build()
