package com.erenalparslan.apijava;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CyriptoApi {
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    Observable<List<CyriptoModel>> getData();

    // Call<List<CyriptoModel>> getData();
}
