package aws.web.id.kata.network

import aws.web.id.kata.helper.Config.BASE_API
import aws.web.id.kata.helper.Config.BASE_API_SANDBOX
import aws.web.id.kata.helper.Validasi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ClientService {

    fun create(file:Boolean): ApiServiceServer {
        var cTO:Long = 30
        var wTO:Long = 30
        var rTO:Long = 30

        if (file){
            cTO = 60
            wTO = 60
            rTO = 60
        }
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient: OkHttpClient
        if (Validasi().isRelease()) {
            okHttpClient = OkHttpClient.Builder()
                .connectTimeout(cTO, TimeUnit.SECONDS)
                .writeTimeout(wTO, TimeUnit.SECONDS)
                .readTimeout(rTO, TimeUnit.SECONDS)
                .build()
        } else {
        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor) //iterceptor hanya untuk debug link tidak untuk release
            .connectTimeout(cTO, TimeUnit.SECONDS)
            .writeTimeout(wTO, TimeUnit.SECONDS)
            .readTimeout(rTO, TimeUnit.SECONDS)
            .build()
        }

        var url = ""
        if(Validasi().isRelease()){
            url = BASE_API
        }else{
            url = BASE_API_SANDBOX
        }


        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create())
            .baseUrl(url)
            .client(okHttpClient)
            .build()

        return retrofit.create(ApiServiceServer::class.java)
    }

}