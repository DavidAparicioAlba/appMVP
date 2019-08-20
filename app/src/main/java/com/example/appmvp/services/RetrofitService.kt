package com.example.appmvp.services

import retrofit2.Retrofit


// Request a string response from the provided URL.




var retrofit = Retrofit.Builder()
    .baseUrl("https://api.github.com/")
    .build()
