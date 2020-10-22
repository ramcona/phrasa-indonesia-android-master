package aws.web.id.kata.ui.defaults

import android.content.Context
import aws.web.id.kata.R
import aws.web.id.kata.helper.BaseHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DefaultPresenter(val context:Context, var views:DefaultViews) : BaseHelper()  {

    fun cek(){
//        init(context)

        views.onLoading()
        disposable = ApiServiceServer.default()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result.error == false) {

                        when(result.code){
                            1 -> {
                                views.onSuccess()
                            }
                            2 -> {
                                views.onFailed(result.message, result.code)
                            }
                            3 -> {
                                views.onFailed(result.message, result.code)
                            }
                            4 -> {
                                views.onFailed(result.message, result.code)
                            }
                        }

                    }else{
                        views.onFailed(result.message, result.code)
                    }
                },
                { error ->
                    if (error.message!!.contains("Failed to connect", true)) {
                        views.onFailed(context.getString(R.string.teks_gagal_internet), 0)
                    } else {
                        views.onFailed(
                            context.getString(R.string.teks_gagal_kesalahan),
                            0
                        )
                    }
                }

            )
    }
}