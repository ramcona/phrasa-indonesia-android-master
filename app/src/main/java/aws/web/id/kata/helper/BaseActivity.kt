package aws.web.id.kata.helper


import android.app.Activity
import android.os.Build
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.layout_toolbar.*

open class BaseActivity : AppCompatActivity() {



    fun statusPutih() {

        supportActionBar?.hide()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decor = window.decorView
            decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }


    }

    fun setToolbar(title: String) {

        toolbar_title.text = title
        toolbar_back.setOnClickListener {
            onBackPressed()
        }

        statusPutih()
    }

    fun failureRequest(context: Activity, msg: String){

        SweetAlert.dismis()
        SweetAlert.onFailure(context, msg)

    }

//    fun pindah(tujuan: Context, context: Context){
//
//        val intent = Intent(context, tujuan::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        startActivity(intent)
//
//    }

}