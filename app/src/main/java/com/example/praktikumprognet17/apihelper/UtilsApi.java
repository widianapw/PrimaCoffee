package com.example.praktikumprognet17.apihelper;

public class UtilsApi {

    // 10.0.2.2 ini adalah localhost.
//    public static final String BASE_URL_API = "http://widianaputraa.000webhostapp.com/api/";
    public static final String BASE_URL_API = "http://10.0.2.2:8000/api/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}