package aws.web.id.kata.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.text.format.DateUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import aws.web.id.kata.R
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Helper {
    fun convertRupiah(x: Int?): String {

        val localeID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localeID)

        return format.format(x)

    }

    fun convertRupiah(x: String?): String {
        try{
            if (x == null) {
                return "null"
            }

            val localeID = Locale("in", "ID")
            val format = NumberFormat.getCurrencyInstance(localeID)

            return format.format(Integer.valueOf(x))
        }catch (e:NumberFormatException){
            return "Invalid format"
        }

    }

    fun waktulalu(tanggalFeed: String, context: Context): String {
        val returnValue: String? = null
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var time: Long = 0
        try {
            time = sdf.parse(tanggalFeed)!!.time
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val now = System.currentTimeMillis()

        val ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
        return ago.toString()
    }

    fun blackStatusBar(context: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decor = context.window.decorView
            //            if (shouldChangeStatusBarTintToDark) {
            decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            //            } else {
            // We want to change tint color to white again.
            // You can also record the flags in advance so that you can turn UI back completely if
            // you have set other flags before, such as translucent or full screen.
            //                decor.setSystemUiVisibility(0);
            //            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun convertDateTimeFree(date: String, ygDimau: String): String {
        var hasil = ""

        //        final String formatBaru = "dd MMMM yyyy, hh:mm";
        val formatLama = "yyyy-MM-dd kk:mm:ss"
        val dateFormat = SimpleDateFormat(formatLama)

        if (date != "null" || date != null) {
            try {
                val dd = dateFormat.parse(date)
                dateFormat.applyPattern(ygDimau)
                hasil = dateFormat.format(dd)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }

        return hasil
    }


    @SuppressLint("SimpleDateFormat")
    fun convertDateEvent(date: String, ygDimau: String): String {
        var hasil = ""

        //        final String formatBaru = "dd MMMM yyyy, hh:mm";
        val formatLama = "yyyy-MM-dd kk:mm"
        val dateFormat = SimpleDateFormat(formatLama)

        if (date != "null" || date != null) {
            try {
                val dd = dateFormat.parse(date)
                dateFormat.applyPattern(ygDimau)
                hasil = dateFormat.format(dd)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }

        return hasil
    }

    @SuppressLint("SimpleDateFormat")
    fun convertDateHours(date: String, ygDimau: String): String {
        var hasil = ""

        //        final String formatBaru = "dd MMMM yyyy, hh:mm";
        val formatLama = "yyyy-MM-dd kk:mm:ss"
        val dateFormat = SimpleDateFormat(formatLama)

        if (date != "null" || date != null) {
            try {
                val dd = dateFormat.parse(date)
                dateFormat.applyPattern(ygDimau)
                hasil = dateFormat.format(dd)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }

        return hasil
    }

    @SuppressLint("SimpleDateFormat")
    fun convertDate(date: String, ygDimau: String): String {
        var hasil = ""

        //        final String formatBaru = "dd MMMM yyyy, hh:mm";
        val formatLama = "yyyy-MM-dd"
        val dateFormat = SimpleDateFormat(formatLama)

        if (date != "null" || date != null) {
            try {
                val dd = dateFormat.parse(date)
                dateFormat.applyPattern(ygDimau)
                hasil = dateFormat.format(dd)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }

        return hasil
    }

    @SuppressLint("SimpleDateFormat")
    fun convertDateLisa(date: String, ygDimau: String): String {
        var hasil = ""

        //        final String formatBaru = "dd MMMM yyyy, hh:mm";
        val formatLama = "dd-MM-yyyy"
        val dateFormat = SimpleDateFormat(formatLama)

        if (date != "null" || date != null) {
            try {
                val dd = dateFormat.parse(date)
                dateFormat.applyPattern(ygDimau)
                hasil = dateFormat.format(dd)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }

        return hasil
    }

    fun setToolbar(context: Activity, toolbar: Toolbar, title: String) {
        (context as AppCompatActivity).setSupportActionBar(toolbar)
        context.supportActionBar!!.title = title
        context.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        context.supportActionBar!!.setDisplayShowHomeEnabled(true)

//        override fun onSupportNavigateUp(): Boolean {
//            onBackPressed()
//            return true
//        }
    }

    fun pullRefrash(view: SwipeRefreshLayout, listener: Listeners) {
        // Setup refresh listener which triggers new data loading
        view.setOnRefreshListener {
            listener.onRefrash()
        }

        view.setColorSchemeResources(
            R.color.colorAccent,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )
    }

    //MAKE LOG RESUCE
    fun log(place:String, msg:String){
        //CHECK RELEASE MODE [if release log not show]
        if (!Validasi().isRelease()){
            if (place.isBlank()){
                Log.e("MGS", msg)
            }else{
                Log.e("MGS "+place, msg)
            }
        }

    }

    fun getCurrentTime():String{
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(Date())
    }

    interface Listeners {
        fun onRefrash()
    }

    fun compareDatetoDays(date:String):Long {
        val simpleDateFormat = SimpleDateFormat("yyy-MM-dd HH:mm:ss", Locale("ID"))
        val startDate:Date  = simpleDateFormat.parse(date)
        val endDate = simpleDateFormat.parse(simpleDateFormat.format(Date()))
        //milliseconds
        var different = endDate.time - startDate.time

        val secondsInMilli: Long = 1000
        val minutesInMilli = secondsInMilli * 60
        val hoursInMilli = minutesInMilli * 60
        val daysInMilli = hoursInMilli * 24
        val elapsedDays = different / daysInMilli
        different = different % daysInMilli
        val elapsedHours = different / hoursInMilli
        different = different % hoursInMilli
        val elapsedMinutes = different / minutesInMilli
        different = different % minutesInMilli
        val elapsedSeconds = different / secondsInMilli

        log("WAKTU", "days $elapsedDays")
        return elapsedDays
    }

}