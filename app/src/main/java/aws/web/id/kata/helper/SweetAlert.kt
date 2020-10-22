package aws.web.id.kata.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import aws.web.id.kata.R
import com.google.android.material.snackbar.Snackbar
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog

object SweetAlert {

    @SuppressLint("StaticFieldLeak")
    private var asd: SweetAlertDialog? = null
    private var alertDialog: AlertDialog? = null

    fun onFailure(context: Activity?, pesan: String?) {
        val message: String = when {
            pesan === "timeout" -> {
                "Koneksi Timeout"
            }
            pesan?.contains("Expected BEGIN_OBJECT")!! -> {
                "Gagal Mengirim Data : BEGIN_OBJECT"
            }
            pesan.contains("network security policy") -> {
                "Not permitted by network security policy"

            }
            pesan.contains("CLEARTEXT communication") -> {
                "Not permitted by network security policy"

            }
            else -> "Gagal Mengirim Data"
        }

        Log.d("Respons", ""+ pesan)
        if (context != null) {
            SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Oops")
                .setContentText(message)
                .show()
        }
    }

    fun success(context: Activity, title: String, pesan: String) {
        SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText(title)
            .setContentText(pesan)
            .show()
    }

    fun success(context: Activity, title: String, pesan: String, callback: Callbacks) {
        val pDialog = SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
        pDialog.setTitleText(title)
            .setContentText(pesan)
            .setConfirmClickListener {
                callback.onPositiveCliked()
                pDialog.dismiss()
            }
            .show()
    }

    fun warning(context: Activity, title: String, pesan: String) {
        SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
            .setTitleText(title)
            .setContentText(pesan)
            .show()
    }

    fun onLoading(context: Activity?) {
        try{
            asd = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
            asd?.progressHelper?.barColor = Color.parseColor("#A5DC86")
            asd?.titleText = "Loading"
            asd?.setCancelable(false)
            asd?.show()
        }catch (e:WindowManager.BadTokenException){

        }

    }

    fun snackBar(view: View, pesan: String) {
//      val view: View = findViewById(android.R.id.content)
        val snackbar = Snackbar.make(
            view, pesan,
            Snackbar.LENGTH_SHORT
        )
        snackbar.setActionTextColor(Color.WHITE)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.WHITE)
        val textView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.BLACK)
//        textView.textSize = 28f
        snackbar.show()
    }

    fun error(context: Activity, title: String, _pesan: String) {
        val pesan = _pesan
        SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText(title)
            .setContentText(pesan)
            .show()
    }

    fun error(context: Activity, title: String, _pesan: String, listener: Callbacks) {
        val pesan = _pesan
        val sweet = SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
        sweet.setTitleText(title)
        sweet.setContentText(pesan)
        sweet.setTitleText(title)
        sweet.setConfirmClickListener {
            listener.onPositiveCliked()
            sweet.dismiss()
        }.show()
    }

    @SuppressLint("ResourceAsColor")
    fun snackBarColor(view: View, pesan: String) {
//      val view: View = findViewById(android.R.id.content)
        val snackbar = Snackbar.make(
            view, pesan,
            Snackbar.LENGTH_SHORT
        )
        snackbar.setActionTextColor(Color.GRAY)
val snackbarView = snackbar.view
snackbarView.setBackgroundResource(R.color.colorPrimary)
val textView =
    snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
textView.setTextColor(Color.BLACK)
//        textView.textSize = 28f
snackbar.show()
}

fun dismis() {
    if (asd != null) {
        asd?.dismiss()
    }
}

fun alertDismis() {
    if (alertDialog != null) {
        if (alertDialog!!.isShowing) {
            alertDialog?.dismiss()
        }
    }
}


    interface Callbacks {

    fun onPositiveCliked()

    fun onCanceled()

    fun onFilterClicked(status: String)
}

open class DefaultCallback : Callbacks {
    override fun onFilterClicked(status: String) {
    }

    override fun onPositiveCliked() {
    }

    override fun onCanceled() {
    }
}


}