package com.erenalparslan.apijava;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<CyriptoModel> cyriptoModels;
    private Retrofit retrofit;
    private String baseUrl = "https://raw.githubusercontent.com/";

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    CompositeDisposable compositeDisposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView2);
        Gson gson = new GsonBuilder().setLenient().create();
        setRetrofitSettings(gson);
        loadData();


    }

     private void setRetrofitSettings(Gson gson){
        retrofit = new Retrofit.Builder().baseUrl(baseUrl).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create(gson)).build();

     }
    private void loadData() {

        CyriptoApi cyriptoApi = retrofit.create(CyriptoApi.class);
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(cyriptoApi.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handlerResponse));

      /*  Call<List<CyriptoModel>> call=cyriptoApi.getData();
        call.enqueue(new Callback<List<CyriptoModel>>() {
            @Override
            public void onResponse(Call<List<CyriptoModel>> call, Response<List<CyriptoModel>> response) {

                if(response.isSuccessful()){
                    List<CyriptoModel> responseList=response.body();
                    cyriptoModels=new ArrayList<>(responseList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerAdapter=new RecyclerAdapter(cyriptoModels);
                    recyclerView.setAdapter(recyclerAdapter);


                }
            }

            @Override
            public void onFailure(Call<List<CyriptoModel>> call, Throwable t) {

                t.printStackTrace();

            }
        });*/
    }

    private void handlerResponse(List<CyriptoModel> cyriptoModelList) {

        cyriptoModels = new ArrayList<>(cyriptoModelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerAdapter = new RecyclerAdapter(cyriptoModels);
        recyclerView.setAdapter(recyclerAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}