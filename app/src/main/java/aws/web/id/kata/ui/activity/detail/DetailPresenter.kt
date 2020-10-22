package aws.web.id.kata.ui.activity.detail

import android.content.Context
import aws.web.id.kata.R
import aws.web.id.kata.helper.BaseHelper
import aws.web.id.kata.helper.Helper.log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailPresenter(val context:Context, var views:DetailViews) : BaseHelper()  {

    fun get(kata:String){
        val url =  "http://kateglo.com/api.php?format=json&phrase=${kata}"
        views.onLoading()
        disposable = ApiServiceServer.get(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    views.onSuccess(result.kateglo)
                },
                { error ->
                    log("ERROR", "error : ${error.message}")
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