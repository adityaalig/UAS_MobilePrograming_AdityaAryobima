package com.adityaaryobima.uas_mobileprograming;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("endemik.json")
    Call<List<Endemik>> getEndemikData();
}
