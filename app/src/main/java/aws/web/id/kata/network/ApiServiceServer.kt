package aws.web.id.kata.network

import aws.web.id.kata.helper.Config
import aws.web.id.kata.network.response.DefaultResponse
import aws.web.id.kata.network.response.KategloResponse
import io.reactivex.Observable
import retrofit2.http.*

interface ApiServiceServer {

    @GET("")
    fun get(
        @Url url:String
    ): Observable<KategloResponse>



    @GET("")
    fun default(
    ): Observable<DefaultResponse>



}